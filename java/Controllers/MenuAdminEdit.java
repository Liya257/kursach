package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sourse.cp_progsp.Main;

public class MenuAdminEdit {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void btnAddTechnique(ActionEvent event) {

    }

    @FXML
    void btnAddBrand(ActionEvent event) {

    }

    @FXML
    void btnAddCategory(ActionEvent event) {
       // Main.setRoot("");
    }

    @FXML
    void btnAddClient(ActionEvent event) throws IOException {
        Main.setRoot("AddClient");
    }

    @FXML
    void btnAddEmployee(ActionEvent event) throws  IOException {
        Main.setRoot("menuEmployeeAccount");
    }

    @FXML
    void btnAddOrder(ActionEvent event) throws IOException {
        Main.setRoot("menuAddOrderEmployee");
    }

    @FXML
    void btnMenuAdmin(ActionEvent event) throws IOException {
        Main.setRoot("menuAdmin");
    }

    @FXML
    void initialize() {

    }

}
