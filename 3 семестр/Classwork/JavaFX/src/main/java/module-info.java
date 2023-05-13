module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    exports fx;
    opens fx to javafx.fxml;
}