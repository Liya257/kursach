package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Actions.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import sourse.cp_progsp.Main;

public class ViewStatistics {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PieChart statTechnique;

    @FXML
    void btnExitMenu(ActionEvent event) throws IOException {
        Main.setRoot("menuAM");
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        assert statTechnique != null : "fx:id=\"statTechnique\" was not injected: check your FXML file 'viewStatistics.fxml'.";
        statTechnique.clockwiseProperty().set(true);
        ArrayList<Integer> numbers = Client.interactionsWithServer.getStatisticsTechnique();
        ObservableList<PieChart.Data> category = FXCollections.observableArrayList(
                new PieChart.Data("notebook", numbers.get(0)),
                new PieChart.Data("ultrabook", numbers.get(1))
        );
        statTechnique.setData(category);
    }

}
