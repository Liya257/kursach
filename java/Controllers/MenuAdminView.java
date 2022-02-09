package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sourse.cp_progsp.Main;

public class MenuAdminView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void btnClientsView(ActionEvent event) {

    }

    @FXML
    void btnEmployeeView(ActionEvent event) throws IOException{
        Main.setRoot("");
    }

    @FXML
    void btnMenuExit(ActionEvent event) throws IOException {
        Main.setRoot("menuAdmin");
    }

    @FXML
    void btnStatView(ActionEvent event) {

    }

    @FXML
    void btnViewOrders(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}
