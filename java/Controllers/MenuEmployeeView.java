package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sourse.cp_progsp.Main;

public class MenuEmployeeView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void btnMenuEmployee(ActionEvent event) throws IOException{
        Main.setRoot("menuEmployee");
    }

    @FXML
    void btnViewClient(ActionEvent event) throws IOException{
        Main.setRoot("viewClients");
    }

    @FXML
    void btnViewOrder(ActionEvent event) throws IOException {
        Main.setRoot("viewOrders");
    }

    @FXML
    void initialize() {

    }

}
