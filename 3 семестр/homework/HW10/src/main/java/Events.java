import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Events {

    public static void addService(Stage primaryStage) {

        Stage dialog = new Stage();
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(primaryStage);
        dialog.setWidth(550);
        dialog.setHeight(200);
        dialog.setMaxWidth(550);
        dialog.setMaxHeight(200);
        dialog.setTitle("Добавление записи в БД");

        AnchorPane anchorPane = new AnchorPane();

        HBox mainBox = new HBox();
        mainBox.setAlignment(Pos.TOP_CENTER);
        mainBox.setSpacing(5);

        var idCell = new TextField();
        idCell.setPromptText("ID (int)");
        idCell.setMinWidth(100);
        idCell.setMaxWidth(100);
        idCell.setMinHeight(20);

        var dtCell = new TextField();
        dtCell.setPromptText("\"YYYY-MM-DD\"");
        dtCell.setMinWidth(100);
        dtCell.setMaxWidth(100);
        dtCell.setMinHeight(20);

        var typeCell = new TextField();
        typeCell.setPromptText("Тип ТО (Str)");
        typeCell.setMinWidth(100);
        typeCell.setMaxWidth(100);
        typeCell.setMinHeight(20);

        var numberCell = new TextField();
        numberCell.setPromptText("№ состава (int)");
        numberCell.setMinWidth(100);
        numberCell.setMaxWidth(100);
        numberCell.setMinHeight(20);

        var costCell = new TextField();
        costCell.setPromptText("Цена (Double)");
        costCell.setMinWidth(100);
        costCell.setMaxWidth(100);
        costCell.setMinHeight(20);

        mainBox.getChildren().addAll(idCell, dtCell, typeCell, numberCell, costCell);
        mainBox.setMaxWidth(415);
        AnchorPane.setTopAnchor(mainBox, 10d);
        AnchorPane.setLeftAnchor(mainBox, 10d);
        AnchorPane.setRightAnchor(mainBox, 10d);

        anchorPane.getChildren().add(mainBox);

        Label error = new Label("Некорректный ввод");
        error.setMinWidth(550);
        error.setMinHeight(200);
        error.setAlignment(Pos.CENTER);
        error.setVisible(false);
        error.setMouseTransparent(true);

        anchorPane.getChildren().add(error);

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);

        Button okButton = new Button("OK");
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idCell.getText());
                    String date = dtCell.getText();
                    String service_type = typeCell.getText();
                    int train_n = Integer.parseInt(numberCell.getText());
                    Double cost = Double.valueOf(costCell.getText());
                    Main.insert(id, date, service_type, train_n, cost);
                    JavaFXMain.createTable();
                    dialog.close();
                } catch (Exception er) {
                    error.setVisible(true);
                    System.out.println("Некорректный ввод" + er);
                }
            }
        });
        Button declineButton = new Button("Отмена");
        declineButton.setOnAction(e ->

        {
            dialog.close();
        });

        buttonBox.getChildren().

                addAll(okButton, declineButton);
        AnchorPane.setBottomAnchor(buttonBox, 10d);
        AnchorPane.setRightAnchor(buttonBox, 10d);

        anchorPane.getChildren().

                add(buttonBox);

        Scene dialogScene = new Scene(anchorPane, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public static void updateService(TextField cell) {
        HBox row = (HBox) cell.getParent();
        var cells = row.getChildren();
        try {
            int id = Integer.parseInt(((TextField) cells.get(1)).getText());
            String date = ((TextField) cells.get(2)).getText();
            String serviceType = ((TextField) cells.get(3)).getText();
            int trainN = Integer.parseInt(((TextField) cells.get(4)).getText());
            Double cost = Double.valueOf((((TextField) cells.get(5)).getText()));
            Main.update(id, date, serviceType, trainN, cost);
        } catch (Exception e) {
            System.out.println("Некорректный ввод " + e);
            JavaFXMain.createTable();
        }
    }
}
