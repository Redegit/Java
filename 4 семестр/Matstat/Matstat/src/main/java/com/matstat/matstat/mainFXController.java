package com.matstat.matstat;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class mainFXController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label disp;

    @FXML
    private LineChart<?, ?> distGraph;

    @FXML
    private LineChart<?, ?> freqGraph;

    @FXML
    private Label mean;

    @FXML
    private Button runMatstat;

    @FXML
    private TextField sampleIn;

    @FXML
    private Label std;

    @FXML
    private Label tableMatstat;

    @FXML
    void initialize() {
        assert disp != null : "fx:id=\"disp\" was not injected: check your FXML file 'mainFX.fxml'.";
        assert distGraph != null : "fx:id=\"distGraph\" was not injected: check your FXML file 'mainFX.fxml'.";
        assert freqGraph != null : "fx:id=\"freqGraph\" was not injected: check your FXML file 'mainFX.fxml'.";
        assert mean != null : "fx:id=\"mean\" was not injected: check your FXML file 'mainFX.fxml'.";
        assert runMatstat != null : "fx:id=\"runMatstat\" was not injected: check your FXML file 'mainFX.fxml'.";
        assert sampleIn != null : "fx:id=\"sampleIn\" was not injected: check your FXML file 'mainFX.fxml'.";
        assert std != null : "fx:id=\"std\" was not injected: check your FXML file 'mainFX.fxml'.";
        assert tableMatstat != null : "fx:id=\"tableMatstat\" was not injected: check your FXML file 'mainFX.fxml'.";

    }

    public void runMatstatAction() {
        List<String> sample = (List<String>) sampleIn.getText().split(" ");
    }
}
