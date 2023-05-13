import DataBase.PatientEntity;
import DataBase.ProcedureEntity;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

public class JavaFXadditionals {

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

    public static VBox create2ndBox() {
        // Создание подписей столбцов
        var col1 = new Button("ID ↑");
        col1.setOnAction(e -> Ordering(col1, "id"));
        var col2 = new Button("Тип -");
        col2.setOnAction(e -> Ordering(col2, "dt"));
        var col3 = new Button("Дата -");
        col3.setOnAction(e -> Ordering(col3, "serviceType"));
        var col4 = new Button("Кабинет -");
        col4.setOnAction(e -> Ordering(col4, "trainN"));
        var col5 = new Button("Пациент -");
        col5.setOnAction(e -> Ordering(col5, "cost"));
        labelsBox.setSpacing(5);
        for (var col : Arrays.asList(col1, col2, col3, col4, col5)) {
            col.setMinWidth(110);
            col.setAlignment(Pos.CENTER);
            col.setBackground(defaultBackground);
            col.setTextFill(Paint.valueOf("FFFFFF"));
            JavaFXMain.addGlow(col);
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
        PieChart pieChart = new PieChart(numberData);
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
        MenuButton selector = new MenuButton("Кабинет");
        selector.setLayoutX(147);
        selector.setLayoutY(145);
        selector.setPrefHeight(35);
        selector.setPrefWidth(150);
        selector.setBackground(defaultBackground);
        selector.setTextFill(Paint.valueOf("FFFFFF"));
        JavaFXMain.addGlow(selector);

        MenuItem typeSelect = new MenuItem("Тип процедуры");
        typeSelect.setOnAction(e -> {
            selector.setText("Тип процедуры");
            pieChart.setData(typeData);
            caption.setText("");
            addPieCaptions(pieChart, caption);
        });
        MenuItem numberSelect = new MenuItem("Кабинет");
        numberSelect.setOnAction(e -> {
            selector.setText("Кабинет");
            pieChart.setData(numberData);
            caption.setText("");
            addPieCaptions(pieChart, caption);
        });
        MenuItem costSelect = new MenuItem("Пациент");
        costSelect.setOnAction(e -> {
            selector.setText("Пациент");
            pieChart.setData(costData);
            caption.setText("");
            addPieCaptions(pieChart, caption);
        });
        selector.getItems().addAll(typeSelect, numberSelect, costSelect);

        topPane.getChildren().addAll(selector, pieChart, caption);
//        System.out.println(selector.getItems());

        VBox mainBox = new VBox(topPane, mainTableBox);
        mainBox.setAlignment(Pos.TOP_CENTER);


        return mainBox;
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

    public static void createTable(Boolean changePie) {
        VBox tableBox = new VBox();
        ScrollPane scrollPane = new ScrollPane(tableBox);
        scrollPane.setMaxWidth(585);
        scrollPane.setBackground(new Background(new BackgroundFill(
                Color.rgb(255, 255, 255, 0), new CornerRadii(5), Insets.EMPTY
        )));

        var allPatList = Main.getProcList(propName, order);
        if (changePie) fillData(allPatList);

        for (var pat : allPatList) {
            HBox rowBox = new HBox();
            rowBox.setAlignment(Pos.TOP_LEFT);
            rowBox.setSpacing(5);
            System.out.println(pat);

            var props = pat.getAllProp();

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
            JavaFXMain.addGlow(slice);
        }
    }
    static void fillData(List<ProcedureEntity> allServicingList) {
        HashMap<String, Integer> typeMap = new HashMap<>();
        HashMap<String, Integer> numberMap = new HashMap<>();
        HashMap<String, Integer> costMap = new HashMap<>();

        for (var service : allServicingList) {
            String type = service.getType();
            if (typeMap.containsKey(type)) {
                int typeCount = typeMap.get(type);
                typeMap.put(type, typeCount + 1);
            } else {
                typeMap.put(type, 1);
            }

            String number = String.valueOf(service.getCabinet());
            if (numberMap.containsKey(number)) {
                int numberCount = numberMap.get(number);
                numberMap.put(number, numberCount + 1);
            } else {
                numberMap.put(number, 1);
            }


            String cost = String.valueOf(service.getPatientId());
            if (costMap.containsKey(cost)) {
                int costCount = costMap.get(cost);
                costMap.put(String.valueOf(cost), costCount + 1);
            } else {
                costMap.put(String.valueOf(cost), 1);
            }
        }


        yearData.clear();
        typeData.clear();
        numberData.clear();
        costData.clear();
        typeMap.forEach((k, v) -> typeData.add(new PieChart.Data(k, v)));
        numberMap.forEach((k, v) -> numberData.add(new PieChart.Data(k, v)));
        costMap.forEach((k, v) -> costData.add(new PieChart.Data(k, v)));
    }
}
