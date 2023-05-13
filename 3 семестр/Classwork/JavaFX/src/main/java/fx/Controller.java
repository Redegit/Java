package fx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Button addButton;

    @FXML
    protected void onAddButtonClicked() {

        addButton.setText("Еще раз!");
    }
}