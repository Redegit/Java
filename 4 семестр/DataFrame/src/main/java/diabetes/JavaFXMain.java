package diabetes;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;

public class JavaFXMain extends Application {

    public static ObservableList<PieChart.Data> pieChartData;

    @Override
    public void start(Stage stage) throws Exception {

        pieChartData.forEach(slice -> {
            slice.setName(slice.getName() + ", " + slice.getPieValue());
        });
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Распределение по признаку Class");

        Group root = new Group(chart);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(ObservableList<PieChart.Data> data) {
        pieChartData = data;
        launch();
    }
}
