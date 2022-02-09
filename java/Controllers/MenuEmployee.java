package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Actions.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.Classes.User;
import sourse.cp_progsp.Main;

public class MenuEmployee {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void btnAdd(ActionEvent event) throws IOException {
        Main.setRoot("menuEmployeeEdit");
    }

    @FXML
    void btnChange(ActionEvent event) throws IOException{
        Main.setRoot("menuEmployeeChange");
    }

    @FXML
    void btnDelete(ActionEvent event) throws IOException{
        Main.setRoot("menuEmployeeDelete");
    }

    @FXML
    void btnExit(ActionEvent event) throws IOException, ClassNotFoundException {
        User user = new User();
        user.setOnline(0);
        Client.interactionsWithServer.setOfflineUser(user);
        Main.setRoot("startwindow");

    }

    @FXML
    void btnView(ActionEvent event) throws IOException{
        Main.setRoot("menuEmployeeView");
    }

    @FXML
    void initialize() {

    }

}
