import DataBase.TrainServicingEntity;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.time.Year;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;


public class JavaFXMain extends javafx.application.Application {

    static VBox mainTableBox = new VBox();
    static HBox labelsBox = new HBox();
    static String propName = "id";
    static String order = "ASCENDING";

    static Background defaultBackground = new Background(
            new BackgroundFill(
                    Color.valueOf("ff6a14"), new CornerRadii(5), Insets.EMPTY
            )
    );

    static ObservableList<PieChart.Data> yearData = observableArrayList();
    static ObservableList<PieChart.Data> typeData = observableArrayList();
    static ObservableList<PieChart.Data> numberData = observableArrayList();
    static ObservableList<PieChart.Data> costData = observableArrayList();

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Основная сцена
        primaryStage.setTitle("База данных TrainServicing");
        primaryStage.setHeight(580);
        primaryStage.setWidth(880);
        primaryStage.setResizable(false);

        // Иконка окна
        FileInputStream icon = new FileInputStream("src/main/resources/db_icon.png");
        Image image = new Image(icon);
        primaryStage.getIcons().add(image);

        // Создание подписей столбцов
        var col1 = new Button("ID ↑");
        col1.setOnAction(e -> Ordering(col1, "id"));
        var col2 = new Button("Дата -");
        col2.setOnAction(e -> Ordering(col2, "dt"));
        var col3 = new Button("Тип сервиса -");
        col3.setOnAction(e -> Ordering(col3, "serviceType"));
        var col4 = new Button("Номер состава -");
        col4.setOnAction(e -> Ordering(col4, "trainN"));
        var col5 = new Button("Стоимость -");
        col5.setOnAction(e -> Ordering(col5, "cost"));
        labelsBox.setSpacing(5);
        for (var col : Arrays.asList(col1, col2, col3, col4, col5)) {
            col.setMinWidth(110);
            col.setAlignment(Pos.CENTER);
            col.setBackground(defaultBackground);
            col.setTextFill(Paint.valueOf("FFFFFF"));
            addGlow(col);
        }
        labelsBox.getChildren().addAll(col1, col2, col3, col4, col5);
        mainTableBox.getChildren().addAll(labelsBox);
        mainTableBox.setMaxWidth(585);
        mainTableBox.setMaxHeight(240);

        // Создание таблицы
        createTable(true);

        // Контейнер для диаграммы, селектора и подписей данных для диаграммы
        Pane topPane = new Pane();

        // Создание диаграммы
        PieChart pieChart = new PieChart(yearData);
        pieChart.setLayoutX(420);
        pieChart.setMaxHeight(350);
        pieChart.setMaxWidth(350);
        pieChart.setLegendVisible(false);
        pieChart.setStartAngle(30);

        // Подписи данных на диаграмме (по нажатию)
        Label caption = new Label("");
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 12 arial;");
        caption.setBackground(new Background(
                new BackgroundFill(
                        Color.rgb(255, 255, 255, 0.8), new CornerRadii(5), Insets.EMPTY
                )));
        addPieCaptions(pieChart, caption);

        // Создание меню с выпадающим списком
        MenuButton selector = new MenuButton("Год");
        selector.setLayoutX(147);
        selector.setLayoutY(145);
        selector.setPrefHeight(35);
        selector.setPrefWidth(150);
        selector.setBackground(defaultBackground);
        selector.setTextFill(Paint.valueOf("FFFFFF"));
        addGlow(selector);

        MenuItem yearSelect = new MenuItem("Год");
        yearSelect.setOnAction(e -> {
            selector.setText("Год");
            pieChart.setData(yearData);
            caption.setText("");
            addPieCaptions(pieChart, caption);
        });
        MenuItem typeSelect = new MenuItem("Тип сервиса");
        typeSelect.setOnAction(e -> {
            selector.setText("Тип сервиса");
            pieChart.setData(typeData);
            caption.setText("");
            addPieCaptions(pieChart, caption);
        });
        MenuItem numberSelect = new MenuItem("Номер состава");
        numberSelect.setOnAction(e -> {
            selector.setText("Номер состава");
            pieChart.setData(numberData);
            caption.setText("");
            addPieCaptions(pieChart, caption);
        });
        MenuItem costSelect = new MenuItem("Стоимость");
        costSelect.setOnAction(e -> {
            selector.setText("Стоимость");
            pieChart.setData(costData);
            caption.setText("");
            addPieCaptions(pieChart, caption);
        });
        selector.getItems().addAll(yearSelect, typeSelect, numberSelect, costSelect);

        topPane.getChildren().addAll(selector, pieChart, caption);
        System.out.println(selector.getItems());

        VBox mainBox = new VBox(topPane, mainTableBox);
        mainBox.setAlignment(Pos.TOP_CENTER);

        Scene mainScene = new Scene(mainBox);
        primaryStage.setScene(mainScene);

        primaryStage.show();
    }

    public static void createTable(Boolean changePie) {
        VBox tableBox = new VBox();
        ScrollPane scrollPane = new ScrollPane(tableBox);
        scrollPane.setMaxWidth(585);
        scrollPane.setBackground(new Background(new BackgroundFill(
                Color.rgb(255, 255, 255, 0), new CornerRadii(5), Insets.EMPTY
        )));

        var allServicingList = Main.getServicingList(propName, order);
        if (changePie) fillData(allServicingList);

        for (var servicing : allServicingList) {
            HBox rowBox = new HBox();
            rowBox.setAlignment(Pos.TOP_LEFT);
            rowBox.setSpacing(5);

            var props = servicing.getAllProp();

            for (var attr : props) {
                var cell = new TextField(attr.toString());
                cell.setBackground(new Background(
                        new BackgroundFill(Color.WHITE, new CornerRadii(0), Insets.EMPTY)
                ));
                cell.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        Events.updateService(cell);
                    }
                });
                cell.setMinWidth(110);
                cell.setMaxWidth(110);
                cell.setMinHeight(20);
                rowBox.getChildren().add(cell);
            }

            rowBox.setMaxWidth(515);
            tableBox.getChildren().add(rowBox);
        }
        try {
            mainTableBox.getChildren().remove(1);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            mainTableBox.getChildren().add(scrollPane);
        }

    }

    public static void Ordering(Button col, String prop) {
        String oldText = col.getText();
        String chr = "↑";
        if (propName.equals(prop)) {
            if (order.equals("ASCENDING")) {
                order = "DESCENDING";
                chr = "↓";
            } else {
                order = "ASCENDING";
            }
        } else {
            propName = prop;
            order = "ASCENDING";
        }

        ObservableList<Node> labels = labelsBox.getChildren();
        labels.forEach(node -> {
            Button button = (Button) node;
            String buttonText = button.getText();
            buttonText = buttonText.substring(0, buttonText.length() - 1) + "-";
            button.setText(buttonText);
        });
        col.setText(oldText.substring(0, oldText.length() - 1) + chr);
        createTable(false);
    }

    static void fillData(List<TrainServicingEntity> allServicingList) {
        HashMap<String, Integer> yearMap = new HashMap<>();
        HashMap<String, Integer> typeMap = new HashMap<>();
        HashMap<String, Integer> numberMap = new HashMap<>();
        HashMap<String, Integer> costMap = new HashMap<>();
        costMap.put("< 5000", 0);
        costMap.put("5000 - 10000", 0);
        costMap.put("> 10000", 0);

        for (var service : allServicingList) {
            String year = Year.parse(service.getDt().substring(0, 4)).toString();
            if (yearMap.containsKey(year)) {
                int yearCount = yearMap.get(year);
                yearMap.put(year, yearCount + 1);
            } else {
                yearMap.put(year, 1);
            }

            String type = service.getServiceType();
            if (typeMap.containsKey(type)) {
                int typeCount = typeMap.get(type);
                typeMap.put(type, typeCount + 1);
            } else {
                typeMap.put(type, 1);
            }

            String number = String.valueOf(service.getTrainN());
            if (numberMap.containsKey(number)) {
                int numberCount = numberMap.get(number);
                numberMap.put(number, numberCount + 1);
            } else {
                numberMap.put(number, 1);
            }

            Double cost = service.getCost();
            String key = "";
            if (cost < 5000) {
                key = "< 5000";
            } else if (cost <= 10000) {
                key = "5000 - 10000";
            } else {
                key = "> 10000";
            }
            int costCount = costMap.get(key);
            costMap.put(key, costCount + 1);
        }

        yearData.clear();
        typeData.clear();
        numberData.clear();
        costData.clear();
        yearMap.forEach((k, v) -> yearData.add(new PieChart.Data(k, v)));
        typeMap.forEach((k, v) -> typeData.add(new PieChart.Data(k, v)));
        numberMap.forEach((k, v) -> numberData.add(new PieChart.Data(k, v)));
        costMap.forEach((k, v) -> costData.add(new PieChart.Data(k, v)));
    }

    static void addPieCaptions(PieChart pieChart, Label caption) {
        for (final PieChart.Data data : pieChart.getData()) {
            var slice = data.getNode();
            slice.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
                    caption.setText(String.valueOf(data.getPieValue()));
                }
            });
            addGlow(slice);
        }
    }

    static void addGlow(Node node) {
        node.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                node.setEffect(new Glow());
            }
        });
        node.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                node.setEffect(null);
            }
        });
    }
}
