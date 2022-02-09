package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import Actions.Client;
import Windows.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Classes.User;
import sourse.cp_progsp.Main;

public class StartWindow {



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSignInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    void btnEnter(ActionEvent event) throws IOException, ClassNotFoundException {

        User user = new User();


        if (login_field.getText().equals("") || password_field.getText().equals(""))
        {
            Alerts.SetAlert(false, "Введены не все данные");
            return;
        }

        user.setLogin(login_field.getText().trim());
        user.setPassword(password_field.getText().trim());
        user.setOnline(1);

        Client.interactionsWithServer.setOnlineUser(user);
        ArrayList<User> user1 = (ArrayList<User>) Client.interactionsWithServer.CheckUserInDataBase(user);
        for (int i = 0; i < user1.size(); i++) {
            user = user1.get(i);
        }
        if (user.getRole() == 1) {

            Main.setRoot("menuClient");

        }
        else if (user.getRole() == 0) {
            Main.setRoot("menuAM");

        }
        else if (user.getRole() == 2) {
            Main.setRoot("menuAM");

        }
        else if (user.getRole() == -3)
        {
            Alerts.SetAlert(false, "Пользователь не найден, проверьте введенные данные");
            return;
        }
        else if(user.getRole() == 3)
        {
            Alerts.SetAlert(false, "Вы были заблокированы, обратитесь к администратору");
            return;
        }
    }

    @FXML
    void btnReg(ActionEvent event) throws IOException {
        Main.setRoot("registration");

    }

    @FXML
    void initialize() {
        assert authSignInButton != null : "fx:id=\"authSignInButton\" was not injected: check your FXML file 'startwindow.fxml'.";
        assert loginSignUpButton != null : "fx:id=\"loginSignUpButton\" was not injected: check your FXML file 'startwindow.fxml'.";
        assert login_field != null : "fx:id=\"login_field\" was not injected: check your FXML file 'startwindow.fxml'.";
        assert password_field != null : "fx:id=\"password_field\" was not injected: check your FXML file 'startwindow.fxml'.";


    }


}
