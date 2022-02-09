package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sourse.cp_progsp.Main;

public class MenuEmployeeEdit {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void btnEditClient(ActionEvent event) throws IOException{

    }

    @FXML
    void btnEditOrder(ActionEvent event) throws IOException{

    }

    @FXML
    void btnMenuEmployee(ActionEvent event) throws IOException {
        Main.setRoot("menuEmployee");
    }

    @FXML
    void initialize() {

    }

}
