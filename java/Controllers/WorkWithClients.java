package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Windows.Alerts;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Classes.Client;
import sample.Classes.User;
import sourse.cp_progsp.Main;

public class WorkWithClients {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginText;

    @FXML
    private TextField passwordText;

    @FXML
    private TableView<Client> TableView;

    @FXML
    private TableColumn<Client, String> email;

    @FXML
    private TextField emailText;

    @FXML
    private TableColumn<Client, Integer> idClient;

    @FXML
    private TableColumn<Client, String> nameClient;

    @FXML
    private TextField nameText;

    @FXML
    private TableColumn<Client, String> surnameClient;

    @FXML
    private TextField surnameText;

    @FXML
    void btnAdd(ActionEvent event) {
        User user = new User();
        Client client = new Client();
        try {
            if (loginText.getText().equals("")||passwordText.getText().equals("")||
                    surnameText.getText().equals("")||nameText.getText().equals("")||emailText.getText().equals("")) {
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
          //  Actions.Client.interactionsWithServer.addNewUserInDataBase(user, client);
            Actions.Client.interactionsWithServer.addNewClientInDataBase(user,client);
           // Actions.Client.interactionsWithServer.setOfflineUser(user);
            emailText.clear();
            passwordText.clear();
            loginText.clear();
            surnameText.clear();
            nameText.clear();
            TableView.getItems().add(client);
            Alerts.SetAlert(true,"Пользователь успешно зарегистрирован!");

            Main.setRoot("menuAM");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnChange(ActionEvent event) throws IOException, ClassNotFoundException {
        Client selectedRow = TableView.getSelectionModel().getSelectedItem();
        User user = new User();
        if (nameText.getText().trim().length() != 0 && surnameText.getText().trim().length() != 0
        && emailText.getText().trim().length() != 0) {
            //selectedRow.setIdClient(idClient.getCellData(1));
           // System.out.println(idClient.getCellData(1));
            selectedRow.setEmail(emailText.getText().trim());
            selectedRow.setNameClient(nameText.getText().trim());
            selectedRow.setSurnameClient(surnameText.getText().trim());
            nameText.clear();
            surnameText.clear();
            emailText.clear();
            Actions.Client.interactionsWithServer.changeInDatabaseClient(selectedRow);
            user.setPassword(passwordText.getText().trim());
            user.setLogin(loginText.getText().trim());
           // user.setRole(1);
            Actions.Client.interactionsWithServer.changeCientData(selectedRow,user);

            passwordText.clear();
            loginText.clear();
        }
        else {
            Alerts.SetAlert(false, "Поля для изменения пусты! Заполните их.");
            Main.setRoot("workWithClients");
        }
        TableView.refresh();
    }

    @FXML
    void btnDelete(ActionEvent event) throws IOException, ClassNotFoundException {
        Client selectedRow = TableView.getSelectionModel().getSelectedItem();
        /*selectedRow.setIdClient(Integer.parseInt(String.valueOf(idClient.getCellData(1))));
        selectedRow.setSurnameClient(surnameClient.getCellData(2));
        selectedRow.setNameClient(nameClient.getCellData(3));
        selectedRow.setEmail(email.getCellData(4));*/
        ObservableList<Client> allClients = TableView.getItems();
        Actions.Client.interactionsWithServer.deleteClient(selectedRow);
        allClients.remove(selectedRow);

    }

    @FXML
    void btnExit(ActionEvent event) throws IOException{
        Main.setRoot("menuAM");
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        assert TableView != null : "fx:id=\"TableView\" was not injected: check your FXML file 'workWithClients.fxml'.";
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'workWithClients.fxml'.";
        assert emailText != null : "fx:id=\"emailText\" was not injected: check your FXML file 'workWithClients.fxml'.";
        assert idClient != null : "fx:id=\"id\" was not injected: check your FXML file 'workWithClients.fxml'.";
        assert nameClient != null : "fx:id=\"name\" was not injected: check your FXML file 'workWithClients.fxml'.";
        assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'workWithClients.fxml'.";
        assert surnameClient != null : "fx:id=\"surname\" was not injected: check your FXML file 'workWithClients.fxml'.";
        assert surnameText != null : "fx:id=\"surnameText\" was not injected: check your FXML file 'workWithClients.fxml'.";
        assert passwordText != null : "fx:id=\"passwordText\" was not injected: check your FXML file 'workWithClients.fxml'.";
        assert loginText != null : "fx:id=\"loginText\" was not injected: check your FXML file 'workWithClients.fxml'.";

        idClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        surnameClient.setCellValueFactory(new PropertyValueFactory<>("surnameClient"));
        nameClient.setCellValueFactory(new PropertyValueFactory<>("nameClient"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableView.setEditable(true);
        //tableview.getItems().clear();
        ArrayList<Client> clients = Actions.Client.interactionsWithServer.showAllClients();

       // System.out.println(clients.size());
        for (int i = 0; i < clients.size(); i++) {
            TableView.getItems().add(clients.get(i));
        }
    }

}

