package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Actions.Client;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sourse.cp_progsp.Main;
import tableClasses.Orders;

public class MenuClientAddOrder {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Orders> Tableview;

    @FXML
    private TableColumn<Orders, String> brand;

    @FXML
    private TableColumn<Orders, String> category;

    @FXML
    private TableColumn<Orders, String> dataCol;

    @FXML
    private TableColumn<Orders, Integer> idOrder;

    @FXML
    private TableColumn<Orders, String> model;

    @FXML
    private TableColumn<Orders, Integer> price;

    @FXML
    void btnDelete(ActionEvent event) {
        Orders order = Tableview.getSelectionModel().getSelectedItem();
        ObservableList<Orders> orders = Tableview.getItems();
        Client.interactionsWithServer.deleteOrder(order);
        orders.remove(order);

    }

    @FXML
    void btnExitMenu(ActionEvent event) throws IOException {
        Main.setRoot("menuClient");
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        assert Tableview != null : "fx:id=\"Tableview\" was not injected: check your FXML file 'menuClientAddOrder.fxml'.";
        assert brand != null : "fx:id=\"brand\" was not injected: check your FXML file 'menuClientAddOrder.fxml'.";
        assert category != null : "fx:id=\"category\" was not injected: check your FXML file 'menuClientAddOrder.fxml'.";
        assert dataCol != null : "fx:id=\"dataCol\" was not injected: check your FXML file 'menuClientAddOrder.fxml'.";
        assert idOrder != null : "fx:id=\"idOrder\" was not injected: check your FXML file 'menuClientAddOrder.fxml'.";
        assert model != null : "fx:id=\"model\" was not injected: check your FXML file 'menuClientAddOrder.fxml'.";
        assert price != null : "fx:id=\"price\" was not injected: check your FXML file 'menuClientAddOrder.fxml'.";

        idOrder.setCellValueFactory(new PropertyValueFactory<>("idOrder"));
        dataCol.setCellValueFactory(new PropertyValueFactory<>("dateOfSale"));
        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        model.setCellValueFactory(new PropertyValueFactory<>("model"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        Tableview.setEditable(true);
        ArrayList<Orders> orders = Client.interactionsWithServer.showOrderClient();
        for (int i = 0; i < orders.size(); i++) {
            Tableview.getItems().add(orders.get(i));
        }
    }

}
