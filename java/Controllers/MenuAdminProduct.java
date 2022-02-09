package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sourse.cp_progsp.Main;

public class MenuAdminProduct {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void btnBrand(ActionEvent event) throws IOException {
        Main.setRoot("addBrand");
    }

    @FXML
    void btnCategory(ActionEvent event) throws IOException{
        Main.setRoot("addCategory");
    }

    @FXML
    void btnExit(ActionEvent event) throws IOException{
        Main.setRoot("MenuAM");
    }

    @FXML
    void btnTechnique(ActionEvent event) throws IOException{
        Main.setRoot("addTechnique");
    }

    @FXML
    void initialize() {

    }

}
