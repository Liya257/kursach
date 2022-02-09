package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Classes.Client;
import sourse.cp_progsp.Main;

public class ViewClients {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Client, String> address;

    @FXML
    private TextField addressText;

    @FXML
    private TableColumn<Client, Integer> idUser;

    @FXML
    private TableColumn<Client, String> nameClient;

    @FXML
    private TableView<Client> Tableview;

    @FXML
    private TextField nameText;

    @FXML
    private TableColumn<Client, String> surnameClient;

    @FXML
    private TextField surnameText;

    @FXML
    void btnBlocked(ActionEvent event) throws IOException, ClassNotFoundException {
        Client client = Tableview.getSelectionModel().getSelectedItem();
       // ArrayList<Client> clients =
                Actions.Client.interactionsWithServer.blockClient(client);
        ObservableList<Client> allClients = Tableview.getItems();
       allClients.remove(client);
    }

    @FXML
    void btnChange(ActionEvent event) {
        Client client = Tableview.getSelectionModel().getSelectedItem();
        Client client1 = new Client();
        client1.setSurnameClient(surnameText.getText());
        client1.setNameClient(nameText.getText());
        client1.setAddress(addressText.getText());
        client1.setUser_idUser(client.getUser_idUser());
        Actions.Client.interactionsWithServer.changeInDatabaseClient(client1);
        surnameText.clear();
        nameText.clear();
        addressText.clear();

    }


    @FXML
    void btnExitMenu(ActionEvent event) throws IOException, ClassNotFoundException {
        Main.setRoot("menuAM");
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {

        idUser.setCellValueFactory(new PropertyValueFactory<>("user_idUser"));
        surnameClient.setCellValueFactory(new PropertyValueFactory<>("surnameClient"));
        nameClient.setCellValueFactory(new PropertyValueFactory<>("nameClient"));
        address.setCellValueFactory(new PropertyValueFactory<>("email"));

        Tableview.setEditable(true);
        //tableview.getItems().clear();
        ArrayList<Client> clients = Actions.Client.interactionsWithServer.showAllClients();

        System.out.println(clients.size());
        for (int i = 0; i < clients.size(); i++) {
            Tableview.getItems().add(clients.get(i));
        }
    }


}

