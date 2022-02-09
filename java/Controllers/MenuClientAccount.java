package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.Classes.Client;
import sample.Classes.User;
import sourse.cp_progsp.Main;

public class MenuClientAccount {

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
    void btnMainMenu(ActionEvent event) throws IOException {
        loginText.clear();
        passwordText.clear();
        surnameText.clear();
        nameText.clear();
        lastnameText.clear();
        addressText.clear();
        phoneText.clear();
        emailText.clear();
        Main.setRoot("menuClient");

    }

    @FXML
    void btnSave(ActionEvent event) throws IOException, ClassNotFoundException {
        User user = new User();
        user.setLogin(loginText.getText());
        user.setPassword(passwordText.getText());
        int s = Actions.Client.interactionsWithServer.saveUserData(user);
        Client client = new Client();
        client.setUser_idUser(s);
        client.setSurnameClient(surnameText.getText());
        client.setNameClient(nameText.getText());
        client.setLastnameClient(lastnameText.getText());
        client.setEmail(emailText.getText());
        client.setPhone(phoneText.getText());
        client.setAddress(addressText.getText());
        Actions.Client.interactionsWithServer.saveClientData(client);

    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        assert addressText != null : "fx:id=\"addressText\" was not injected: check your FXML file 'menuClientAccount.fxml'.";
        assert emailText != null : "fx:id=\"emailText\" was not injected: check your FXML file 'menuClientAccount.fxml'.";
        assert lastnameText != null : "fx:id=\"lastnameText\" was not injected: check your FXML file 'menuClientAccount.fxml'.";
        assert loginText != null : "fx:id=\"loginText\" was not injected: check your FXML file 'menuClientAccount.fxml'.";
        assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'menuClientAccount.fxml'.";
        assert passwordText != null : "fx:id=\"passwordText\" was not injected: check your FXML file 'menuClientAccount.fxml'.";
        assert phoneText != null : "fx:id=\"phoneText\" was not injected: check your FXML file 'menuClientAccount.fxml'.";
        assert surnameText != null : "fx:id=\"surnameText\" was not injected: check your FXML file 'menuClientAccount.fxml'.";

        User user = new User();
        user.setOnline(1);
        ArrayList<User> users =  Actions.Client.interactionsWithServer.showAccountUser(user);
        for (int i = 0; i < users.size(); i++) {
            user = users.get(i);
        }
        System.out.println(user.getId());
        loginText.setText(user.getLogin());
        passwordText.setText(user.getPassword());
        Client client = new Client();
        client.setUser_idUser(user.getId());
        System.out.println(client.getUser_idUser());
        ArrayList<Client> clients = Actions.Client.interactionsWithServer.showAccountClient(client);
        Client client1 = new Client();
        for (int i = 0; i < clients.size(); i++) {
            client1 = clients.get(i);
        }
        surnameText.setText(client1.getSurnameClient());
        nameText.setText(client1.getNameClient());
        lastnameText.setText(client1.getLastnameClient());
        emailText.setText(client1.getEmail());
        phoneText.setText(client1.getPhone());
        addressText.setText(client1.getAddress());
    }

}
