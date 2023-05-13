package diabetes;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.apache.spark.sql.functions.*;

public class Main {

    public static void main(String[] args) {
        Logger.getRootLogger().setLevel(Level.OFF);

        SparkSession spark = SparkSession.builder()
                .master("local")
                .getOrCreate();

        Scanner scanner = new Scanner(System.in);
        System.out.print("""
                1) Чтение из CSV
                2) Чтение из Json
                3) Чтение из XML
                0) Выход
                """);
        Dataset<Row> df = null;

        int choice = -1;
        boolean flag = true;
        while (flag) {
            System.out.print(">> ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод");
                continue;
            }
            switch (choice) {
                case 1 -> {
                    System.out.println("Чтение diabetes.csv...");
                    df = readCsv(spark, "src/main/resources/diabetes.csv");
                    flag = false;
                }
                case 2 -> {
                    System.out.println("Чтение diabetes.json...");
                    df = readJson(spark, "src/main/resources/diabetes.json");
                    flag = false;
                }
                case 3 -> {
                    System.out.println("Чтение diabetes.xml...");
                    try {
                        df = readXml(spark, "src/main/resources/diabetes.xml");
                    } catch (ParserConfigurationException | IOException | SAXException e) {
                        throw new RuntimeException(e);
                    }
                    flag = false;
                }
                case 0 -> {
                    System.out.println("Выход...");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Неверный ввод");
                }
            }
        }

        df.show();

        System.out.println("1.\tУ скольких женщин старше 50 лет обнаружен диабет? - " +
                df.filter("Age > 50").filter("Class = 1").count()
        );


        System.out.println("2.\tСколько женщин успели родить 3 или более детей до 30 лет? - " +
                df.filter("Age < 30").filter("Pregnancies >= 3").count()
        );


        System.out.println("3.\tНормальным кровяным давлением будем считать давление в диапазоне [80-89]. " +
                "У какого процента женщин давление нормальное? - " +
                df.filter("BloodPressure BETWEEN 80 and 89").count()
        );


        System.out.println("4.\tСчитается, что BMI >= 30 - это признак ожирения. " +
                "У скольких женщин с признаками ожирения кровяное давление выше среднего? - " +
                df.filter("BMI >= 30 AND BloodPressure > 89").count()
        );


        System.out.println("5.\tСравните средние значения для признаков Glucose, BloodPressure, Insulin " +
                "среди тех, у кого обнаружен диабет, и тех, у кого его нет.");
        var res = df
                .groupBy("Class")
                .avg("Glucose", "BloodPressure", "Insulin")
                .withColumn("avg(Glucose)", format_number(col("avg(Glucose)"), 2))
                .withColumn("avg(BloodPressure)", format_number(col("avg(BloodPressure)"), 2))
                .withColumn("avg(Insulin)", format_number(col("avg(Insulin)"), 2));
        res.show();


        System.out.println("6.\tСравните процент больных диабетом среди женщин, которые были беременны и не были.");

        Dataset<Row> groupedByPregnancies = df.groupBy(
                when(col("Pregnancies").equalTo(0), "No")
                        .otherwise("Yes")
                        .as("WasPregnant")
        ).agg(
                count("*").as("Count"),
                sum(col("Class")).as("DiabetesCount")
        );

        Dataset<Row> result = groupedByPregnancies.selectExpr(
                "WasPregnant", "ROUND(DiabetesCount * 100 / Count, 2) AS DiabetesPercentage"
        );

        result.show();


        System.out.println("7.\tБудем считать \"здоровыми\" тех, у кого нормальный вес и кровяное давление. " +
                "Какой процент \"здоровых\" женщин больны диабетом?");
//      За "нормальный" вес посчитаем 18,5 <= BMI <= 24,9
        Dataset<Row> dfHealthy = df
                .filter(col("BMI").between(18.5, 24.9))
                .filter(col("BloodPressure").between(80, 89));

        double withDiabetes = dfHealthy.filter(col("Class").equalTo(1)).count();
        double total = dfHealthy.count();
        double percentage = withDiabetes / total * 100.0;
        System.out.printf("%.2f", percentage);


        System.out.println("\n8.\tПостройте круговую диаграмму для признака Class.");
        System.out.print("Нажмите Enter для отображения...");
        scanner.nextLine();
        System.out.println("Подождите...");

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        Dataset<Row> counts = df.groupBy("Class").count();

        counts.collectAsList().forEach(row ->
                pieChartData.add(
                        new PieChart.Data(
                                row.getAs("Class").toString().equals("1") ? "Диабет" : "Нет диабета",
                                Double.parseDouble(row.getAs("count").toString())
                        )
                )
        );

        System.out.println("Окно с диаграммой открылось");
        JavaFXMain.main(pieChartData);

    }

    public static Dataset<Row> readCsv(SparkSession spark, String path) {
        return spark.read()
                .format("com.databricks.spark.csv")
                .option("header", "true")
                .option("inferSchema", "true")
                .csv(path);
    }

    public static Dataset<Row> readJson(SparkSession spark, String path) {
        return spark.read()
                .option("multiline", "true")
                .json(path);
    }

    public static Dataset<Row> readXml(SparkSession spark, String path) throws ParserConfigurationException, IOException, SAXException {
        File inputFile = new File("src/main/resources/diabetes.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("row");
        List<Row> rowList = new ArrayList<>();

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Element eElement = (Element) nList.item(temp);

            int pregnancies = Integer.parseInt(eElement.getElementsByTagName("Pregnancies").item(0).getTextContent());
            int glucose = Integer.parseInt(eElement.getElementsByTagName("Glucose").item(0).getTextContent());
            int bloodPressure = Integer.parseInt(eElement.getElementsByTagName("BloodPressure").item(0).getTextContent());
            int skinThickness = Integer.parseInt(eElement.getElementsByTagName("SkinThickness").item(0).getTextContent());
            int insulin = Integer.parseInt(eElement.getElementsByTagName("Insulin").item(0).getTextContent());
            double bmi = Double.parseDouble(eElement.getElementsByTagName("BMI").item(0).getTextContent());
            double diabetesPedigreeFunction = Double.parseDouble(eElement.getElementsByTagName("DiabetesPedigreeFunction").item(0).getTextContent());
            int age = Integer.parseInt(eElement.getElementsByTagName("Age").item(0).getTextContent());
            int diabetes = Integer.parseInt(eElement.getElementsByTagName("Class").item(0).getTextContent());

            Row row = RowFactory.create(pregnancies, glucose, bloodPressure, skinThickness, insulin, bmi, diabetesPedigreeFunction, age, diabetes);
            rowList.add(row);
        }


        StructField[] fields = new StructField[]{
                DataTypes.createStructField("Pregnancies", DataTypes.IntegerType, true),
                DataTypes.createStructField("Glucose", DataTypes.IntegerType, true),
                DataTypes.createStructField("BloodPressure", DataTypes.IntegerType, true),
                DataTypes.createStructField("SkinThickness", DataTypes.IntegerType, true),
                DataTypes.createStructField("Insulin", DataTypes.IntegerType, true),
                DataTypes.createStructField("BMI", DataTypes.DoubleType, true),
                DataTypes.createStructField("DiabetesPedigreeFunction", DataTypes.DoubleType, true),
                DataTypes.createStructField("Age", DataTypes.IntegerType, true),
                DataTypes.createStructField("Class", DataTypes.IntegerType, true)
        };

        StructType schema = DataTypes.createStructType(fields);
        return spark.createDataFrame(rowList, schema);
    }
}