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

    public static void updateService(TextField cell) {
        HBox row = (HBox) cell.getParent();
        var cells = row.getChildren();
        try {
            int id = Integer.parseInt(((TextField) cells.get(0)).getText());
            String date = ((TextField) cells.get(1)).getText();
            String serviceType = ((TextField) cells.get(2)).getText();
            int trainN = Integer.parseInt(((TextField) cells.get(3)).getText());
            Double cost = Double.valueOf((((TextField) cells.get(4)).getText()));
            Main.update(id, date, serviceType, trainN, cost);
            JavaFXMain.createTable(true);
        } catch (Exception e) {
            System.out.println("Некорректный ввод " + e);
            JavaFXMain.createTable(false);
        }
    }
}
