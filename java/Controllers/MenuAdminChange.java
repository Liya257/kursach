package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sourse.cp_progsp.Main;

public class MenuAdminChange {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void btnChangeBrand(ActionEvent event) throws IOException{
        Main.setRoot("addBrand");
    }

    @FXML
    void btnChangeCategory(ActionEvent event) {

    }

    @FXML
    void btnChangeClient(ActionEvent event) throws IOException {
        Main.setRoot("clientsView");
    }

    @FXML
    void btnChangeEmployee(ActionEvent event) throws IOException{
        Main.setRoot("employeeView");
    }

    @FXML
    void btnChangeTechnique(ActionEvent event) {

    }

    @FXML
    void btnMenuAdmin(ActionEvent event) throws IOException{
        Main.setRoot("menuAdmin");
    }

    @FXML
    void initialize() {

    }

}
