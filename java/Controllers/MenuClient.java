package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Actions.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.Classes.User;
import sourse.cp_progsp.Main;

public class MenuClient {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void btnAccount(ActionEvent event) throws IOException {
        Main.setRoot("menuClientAccount");

    }

    @FXML
    void btnCatalog(ActionEvent event) throws IOException {//search, view and add order
        Main.setRoot("catalogProducts");
    }

    @FXML
    void btnOrder(ActionEvent event) throws  IOException{
        Main.setRoot("menuClientAddOrder");//должно быть окно с выводом заказов этого клиента, запрос по айдишнику.
    }

    @FXML
    void exitAction(ActionEvent event) throws IOException, ClassNotFoundException {
        Main.setRoot("startwindow");
        User user = new User();
        user.setOnline(0);
        Client.interactionsWithServer.setOfflineUser(user);

    }

    @FXML
    void initialize() {

    }

}

