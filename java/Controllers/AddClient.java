package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Windows.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.Classes.Client;
import sample.Classes.User;
import sourse.cp_progsp.Main;

public class AddClient {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField addressText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField lastnameText;

    @FXML
    private TextField loginText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField passwordText;

    @FXML
    private TextField phoneText;

    @FXML
    private TextField surnameText;

    @FXML
    void btnAdd(ActionEvent event) {
        User user = new User();
        Client client = new Client();
        try {
            if (loginText.getText().equals("")||passwordText.getText().equals("")||
                    surnameText.getText().equals("")||nameText.getText().equals("")||
                    lastnameText.getText().equals("")||emailText.getText().equals("")||
                    addressText.getText().equals("")||phoneText.getText().equals("")) {
                Alerts.SetAlert(false, "Не все поля заполнены");
                return;
            }
            user.setLogin(loginText.getText());
            user.setPassword(passwordText.getText());
            user.setRole(1);
            user.setOnline(0);
            client.setEmail(emailText.getText());
            client.setSurnameClient(surnameText.getText());
            client.setNameClient(nameText.getText());
            client.setLastnameClient(lastnameText.getText());
            client.setAddress(addressText.getText());
            client.setPhone(phoneText.getText());
            Actions.Client.interactionsWithServer.addNewUserInDataBase(user, client);

            Alerts.SetAlert(true,"Вы успешно зарегистрировались!");

            Main.setRoot("menuAdminEdit");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert addressText != null : "fx:id=\"addressText\" was not injected: check your FXML file 'addClient.fxml'.";
        assert emailText != null : "fx:id=\"emailText\" was not injected: check your FXML file 'addClient.fxml'.";
        assert lastnameText != null : "fx:id=\"lastnameText\" was not injected: check your FXML file 'addClient.fxml'.";
        assert loginText != null : "fx:id=\"loginText\" was not injected: check your FXML file 'addClient.fxml'.";
        assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'addClient.fxml'.";
        assert passwordText != null : "fx:id=\"passwordText\" was not injected: check your FXML file 'addClient.fxml'.";
        assert phoneText != null : "fx:id=\"phoneText\" was not injected: check your FXML file 'addClient.fxml'.";
        assert surnameText != null : "fx:id=\"surnameText\" was not injected: check your FXML file 'addClient.fxml'.";

    }

}
