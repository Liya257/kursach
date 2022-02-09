package Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Actions.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sourse.cp_progsp.Main;
import tableClasses.Orders;

public class ViewOrdersAdmin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Orders> TableView;

   /* @FXML
    private TableColumn<Orders, String> employee;*/

    @FXML
    private TableColumn<Orders, Integer> price;

    @FXML
    private TableColumn<Orders, String> dataSale;

    @FXML
    private TableColumn<Orders, String> nameClient;

    @FXML
    private TableColumn<Orders, String> surnameClient;

    @FXML
    private TableColumn<Orders, String> technique;

    @FXML
    void btnExitMenu(ActionEvent event) throws IOException{
        Main.setRoot("menuAM");
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        assert TableView != null : "fx:id=\"TableView\" was not injected: check your FXML file 'viewOrdersAdmin.fxml'.";
        assert dataSale != null : "fx:id=\"dataSale\" was not injected: check your FXML file 'viewOrdersAdmin.fxml'.";
        assert nameClient != null : "fx:id=\"nameClient\" was not injected: check your FXML file 'viewOrdersAdmin.fxml'.";
        assert surnameClient != null : "fx:id=\"surnameClient\" was not injected: check your FXML file 'viewOrdersAdmin.fxml'.";
        assert technique != null : "fx:id=\"technique\" was not injected: check your FXML file 'viewOrdersAdmin.fxml'.";
        assert price != null : "fx:id=\"price\" was not injected: check your FXML file 'viewOrdersAdmin.fxml'.";
        //assert employee != null : "fx:id=\"employee\" was not injected: check your FXML file 'viewOrdersAdmin.fxml'.";

        dataSale.setCellValueFactory(new PropertyValueFactory<>("dateOfSale"));
        nameClient.setCellValueFactory(new PropertyValueFactory<>("nameClient"));
        surnameClient.setCellValueFactory(new PropertyValueFactory<>("surnameClient"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
      //  employee.setCellValueFactory(new PropertyValueFactory<>(""));
        technique.setCellValueFactory(new PropertyValueFactory<>("category"));
        ArrayList<Orders> orders = Client.interactionsWithServer.showAllOrders();
      //  Orders orders1 = new Orders();

        for (int i = 0; i < orders.size(); i++) {
            TableView.getItems().add(orders.get(i));
        }


    }

}
