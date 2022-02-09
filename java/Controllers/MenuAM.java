package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Actions.Client;
import Windows.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import sample.Classes.User;
import sourse.cp_progsp.Main;

public class MenuAM {
    int role = Client.interactionsWithServer.returnRole();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    public MenuAM() throws IOException, ClassNotFoundException {
    }

    @FXML
    void btnAccount(ActionEvent event) throws IOException {
        Main.setRoot("menuAMAccount");
    }

    @FXML
    void btnClients(ActionEvent event) throws IOException{
        System.out.println(role);
        if (role == 2) {
            Main.setRoot("viewClients");
        }else {Main.setRoot("workWithClients");}
    }

    @FXML
    void btnExit(ActionEvent event) throws IOException{

        User user = new User();
        user.setOnline(0);
        Client.interactionsWithServer.setOfflineUser(user);
        Main.setRoot("startWindow");
    }

    @FXML
    void btnOrders(ActionEvent event) throws IOException, ClassNotFoundException{

        if (role ==0) {
            Main.setRoot("viewOrdersAdmin");
        } else if (role == 2) {
            Main.setRoot("viewOrders");
        } else {
            Alerts.SetAlert(false, "Это меню для вас не доступно!");
            Main.setRoot("startWindow");
        }
    }


    @FXML
    void btnProducts(ActionEvent event) throws IOException, ClassNotFoundException {
        if (role == 0) {
            Main.setRoot("menuAdminProduct");
        } else if(role == 2) {
            Main.setRoot("viewTechniqueEmployee");
        } else {
            Alerts.SetAlert(false, "Это меню для вас не доступно!");
            Main.setRoot("startWindow");
        }
    }

    @FXML
    void btnReports(ActionEvent event) throws IOException{
        if (role == 0) {
            Main.setRoot("reportsAdmin");
        }else {
            Alerts.SetAlert(false, "Это меню для вас не доступно!");
        }

    }

    @FXML
    void btnStatistics(ActionEvent event) throws IOException{
        Main.setRoot("viewStatistics");
    }

    @FXML
    void initialize() {
    }

}
