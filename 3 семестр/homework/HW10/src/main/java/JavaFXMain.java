import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.Arrays;


public class JavaFXMain extends javafx.application.Application {

    static HBox mainBox = new HBox();
    static VBox leftBox = new VBox();
    static HBox labelsBox = new HBox();

    static String propName = "id";
    static String order = "ASCENDING";

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("База данных TrainServicing");
        primaryStage.setHeight(550);
        primaryStage.setWidth(880);
        primaryStage.setMinWidth(880);
        primaryStage.setMaxWidth(880);
        primaryStage.setMinHeight(550);
        primaryStage.setMaxHeight(550);

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
        }
        labelsBox.setAlignment(Pos.TOP_RIGHT);
        labelsBox.getChildren().addAll(col1, col2, col3, col4, col5);
        leftBox.getChildren().addAll(labelsBox);

        // Создание таблицы
        createTable();

        // Создание кнопок
        VBox rightBox = new VBox();
        var but = new Button("Добавить запись");
        but.setOnAction(e -> {
            Events.addService(primaryStage);
        });
        rightBox.setAlignment(Pos.CENTER);
        rightBox.setMinWidth(200);
        rightBox.setSpacing(50);

        Label note = new Label("Для применения изменений\nв конкретной ячейке\nнеобходимо нажать ENTER");
        note.maxWidth(70);

        rightBox.getChildren().addAll(note, but);

        mainBox.setAlignment(Pos.TOP_CENTER);
        mainBox.getChildren().addAll(leftBox, rightBox);
        Scene mainScene = new Scene(mainBox);
        primaryStage.setScene(mainScene);

        primaryStage.show();
    }

    public static void createTable() {
        VBox tableBox = new VBox();
        for (var servicing : Main.getServicingList(propName, order)) {
            HBox rowBox = new HBox();
            rowBox.setAlignment(Pos.TOP_LEFT);
            rowBox.setSpacing(5);

            var props = servicing.getAllProp();
            String id = props.get(0).toString();

            Button delButton = new Button("Удалить");
            delButton.setOnAction(e -> {
                Main.delete(id);
                createTable();
            });
            delButton.setMinWidth(70);
            rowBox.getChildren().add(delButton);

            for (var attr : props) {
                var cell = new TextField(attr.toString());
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
            leftBox.getChildren().remove(1);
        } catch (
                Exception e) {
            System.out.println(e);
        } finally {
            leftBox.getChildren().add(tableBox);
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

        var labels = labelsBox.getChildren();
        for (int i = 0; i<5; i++) {
            Button button = (Button) labels.get(i);
            String buttonText = button.getText();
            buttonText = buttonText.substring(0, buttonText.length()-1) + "-";
            button.setText(buttonText);
        }
        col.setText(oldText.substring(0, oldText.length()-1) + chr);
        createTable();
    }
    public static void main(String[] args) {
        Application.launch();
    }
}
