package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sourse.cp_progsp.Main;

public class MenuAdminDelete {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void btnDeleteClient(ActionEvent event) throws IOException {
        Main.setRoot("viewClients");
    }

    @FXML
    void btnDeleteEmployee(ActionEvent event) throws IOException{
        Main.setRoot("employeeView");
    }

    @FXML
    void btnDeleteOrder(ActionEvent event) throws IOException{
        Main.setRoot("viewOrders");
    }

    @FXML
    void btnExitMenuAdmin(ActionEvent event) throws IOException{
        Main.setRoot("menuAdmin");
    }

    @FXML
    void initialize() {

    }

}
