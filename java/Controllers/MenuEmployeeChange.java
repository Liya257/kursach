package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sourse.cp_progsp.Main;

public class MenuEmployeeChange {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void btnChangeAccount(ActionEvent event) throws IOException {
        Main.setRoot("menuEmployeeAccount");
    }

    @FXML
    void btnChangeClient(ActionEvent event) throws IOException {

    }

    @FXML
    void btnChangeOrder(ActionEvent event) throws IOException{

    }

    @FXML
    void btnExitMenu(ActionEvent event) throws IOException{

    }

    @FXML
    void initialize() {

    }

}
