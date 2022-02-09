package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Actions.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Classes.Technique;
import sourse.cp_progsp.Main;
import tableClasses.CatalogProduct;

public class ViewTechniqueEmployee {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<CatalogProduct> Tableview;

    @FXML
    private TableColumn<CatalogProduct, String> brand;

    @FXML
    private TableColumn<CatalogProduct, String> category;

    @FXML
    private TableColumn<CatalogProduct, String> color;

    @FXML
    private TableColumn<CatalogProduct, String> dataOfRelease;

    @FXML
    private TableColumn<CatalogProduct, String> model;

    @FXML
    private TableColumn<CatalogProduct, Integer> price;

    @FXML
    void btnExit(ActionEvent event) throws IOException {
        Main.setRoot("menuAM");
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        assert Tableview != null : "fx:id=\"Tableview\" was not injected: check your FXML file 'viewTechniqueEmployee.fxml'.";
        assert brand != null : "fx:id=\"brand\" was not injected: check your FXML file 'viewTechniqueEmployee.fxml'.";
        assert category != null : "fx:id=\"category\" was not injected: check your FXML file 'viewTechniqueEmployee.fxml'.";
        assert color != null : "fx:id=\"color\" was not injected: check your FXML file 'viewTechniqueEmployee.fxml'.";
        assert dataOfRelease != null : "fx:id=\"dataOfRelease\" was not injected: check your FXML file 'viewTechniqueEmployee.fxml'.";
        assert model != null : "fx:id=\"model\" was not injected: check your FXML file 'viewTechniqueEmployee.fxml'.";
        assert price != null : "fx:id=\"price\" was not injected: check your FXML file 'viewTechniqueEmployee.fxml'.";

        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        model.setCellValueFactory(new PropertyValueFactory<>("model"));
        dataOfRelease.setCellValueFactory(new PropertyValueFactory<>("dateOfRelease"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        ArrayList<CatalogProduct> product =  Client.interactionsWithServer.showProducts();
        for (int i = 0; i < product.size(); i++) {
            Tableview.getItems().add(product.get(i));
        }
    }

}
