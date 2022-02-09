package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Actions.Client;
import Windows.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sourse.cp_progsp.Main;
import tableClasses.CatalogProduct;


public class CatalogProducts {

    @FXML
    private ResourceBundle resources;

    @FXML
    private TableView<CatalogProduct> TableView;

    @FXML
    private URL location;

    @FXML
    private TableColumn<CatalogProduct, Integer> id;

    @FXML
    private TableColumn<CatalogProduct, String> brandView;

    @FXML
    private TableColumn<CatalogProduct, String> categoryView;

    @FXML
    private TableColumn<CatalogProduct, String> colorView;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableColumn<CatalogProduct, String> modelView;

    @FXML
    private TableColumn<CatalogProduct, Integer> priceView;

    @FXML
    private TextField searchBar;

    @FXML
    void btnAdd(ActionEvent event) throws IOException {
        CatalogProduct products = TableView.getSelectionModel().getSelectedItem();
        products.setDataOfSale(String.valueOf(datePicker.getValue()));
        Client.interactionsWithServer.addNewOrder(products);
        Main.setRoot("catalogProducts");
    }

    @FXML
    void btnMenuClient(ActionEvent event) throws IOException {
        Main.setRoot("menuClient");
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        assert brandView != null : "fx:id=\"brandView\" was not injected: check your FXML file 'catalogProducts.fxml'.";
        assert categoryView != null : "fx:id=\"categoryView\" was not injected: check your FXML file 'catalogProducts.fxml'.";
        assert colorView != null : "fx:id=\"colorView\" was not injected: check your FXML file 'catalogProducts.fxml'.";
        assert datePicker != null : "fx:id=\"datePicker\" was not injected: check your FXML file 'catalogProducts.fxml'.";
        assert modelView != null : "fx:id=\"modelView\" was not injected: check your FXML file 'catalogProducts.fxml'.";
        assert priceView != null : "fx:id=\"priceView\" was not injected: check your FXML file 'catalogProducts.fxml'.";
        assert searchBar != null : "fx:id=\"searchBar\" was not injected: check your FXML file 'catalogProducts.fxml'.";
        assert TableView != null : "fx:id=\"TableView\" was not injected: check your FXML file 'catalogProducts.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'catalogProducts.fxml'.";

        id.setCellValueFactory(new PropertyValueFactory<>("CodeofProduct"));
        categoryView.setCellValueFactory(new PropertyValueFactory<>("category"));
        colorView.setCellValueFactory(new PropertyValueFactory<>("color"));
        modelView.setCellValueFactory(new PropertyValueFactory<>("model"));
        priceView.setCellValueFactory(new PropertyValueFactory<>("price"));
        brandView.setCellValueFactory(new PropertyValueFactory<>("brand"));
        TableView.setEditable(true);
        ObservableList<CatalogProduct> tableServices = FXCollections.observableArrayList();
        ArrayList<CatalogProduct> products = Client.interactionsWithServer.showProducts();
        for (int i = 0; i < products.size(); i++) {
            tableServices.add(products.get(i));
        }
        FilteredList<CatalogProduct> filteredList = new FilteredList<>(tableServices, p->true);
        searchBar.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredList.setPredicate(catalogProduct -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                if (catalogProduct.getBrand().contains(newValue)) {
                    return true;
                } else if (catalogProduct.getCategory().contains(newValue)) {
                    return true;
                } else if (catalogProduct.getModel().contains(newValue)) {
                    return true;
                } else if (catalogProduct.getColor().contains(newValue)) {
                    return true;
                } else if (String.valueOf(catalogProduct.getPrice()).contains(newValue)) {
                    return true;
                }
                return false;
            });
        }));
        SortedList<CatalogProduct> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(TableView.comparatorProperty());
        TableView.setItems(sortedList);

       /* for (int i = 0; i < products.size(); i++) {
            TableView.getItems().add(products.get(i));
        }*/
    }

}
