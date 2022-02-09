package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Actions.Client;
import Windows.Alerts;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sourse.cp_progsp.Main;
import tableClasses.Orders;

public class ViewOrders {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Orders> Tableview;

    @FXML
    private TableColumn<Orders, String> dataCol;

    @FXML
    private TableColumn<Orders, Integer> idOrder;

    @FXML
    private TableColumn<Orders, String> name;

    @FXML
    private TableColumn<Orders, String> surname;

    @FXML
    private TableColumn<Orders, String> technique;

    @FXML
    void btnChange(ActionEvent event) {
        Orders selectedRow = Tableview.getSelectionModel().getSelectedItem();
        ObservableList<Orders> orders = Tableview.getItems();
        Client.interactionsWithServer.acceptOrder(selectedRow);
        orders.remove(selectedRow);
        Alerts.SetAlert(true,"Заказ принят!");
    }

    @FXML
    void btnDelete(ActionEvent event) {
        Orders selectedRow = Tableview.getSelectionModel().getSelectedItem();
       /* selectedRow.setIdOrder(idOrder.getCellData(1));
        selectedRow.setDateOfSale(dataCol.getCellData(2));
        selectedRow.setSurnameClient(surname.getCellData(3));
        selectedRow.setNameClient(name.getCellData(4));
        selectedRow.setCategory(technique.getCellData(5));*/


        ObservableList<Orders> allOrders = Tableview.getItems();
        Client.interactionsWithServer.deleteOrder(selectedRow);
        allOrders.remove(selectedRow);
    }

    @FXML
    void btnExitMenu(ActionEvent event) throws IOException{
        Main.setRoot("menuAM");
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        assert Tableview != null : "fx:id=\"Tableview\" was not injected: check your FXML file 'viewOrders.fxml'.";
        assert dataCol != null : "fx:id=\"dataCol\" was not injected: check your FXML file 'viewOrders.fxml'.";
        assert idOrder != null : "fx:id=\"idOrder\" was not injected: check your FXML file 'viewOrders.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'viewOrders.fxml'.";
        assert surname != null : "fx:id=\"surname\" was not injected: check your FXML file 'viewOrders.fxml'.";
        assert technique != null : "fx:id=\"technique\" was not injected: check your FXML file 'viewOrders.fxml'.";

        idOrder.setCellValueFactory(new PropertyValueFactory<>("idOrder"));
        dataCol.setCellValueFactory(new PropertyValueFactory<>("dateOfSale"));
        name.setCellValueFactory(new PropertyValueFactory<>("nameClient"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surnameClient"));
        technique.setCellValueFactory(new PropertyValueFactory<>("category"));
        Tableview.setEditable(true);
        ArrayList<Orders> orders = Client.interactionsWithServer.showNotAcceptOrders();
        for (int i = 0; i < orders.size(); i++) {
            Tableview.getItems().add(orders.get(i));
        }
    }

}


