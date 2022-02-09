package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Actions.Client;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sourse.cp_progsp.Main;
import tableClasses.CatalogProduct;

public class AddTechnique {

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
    private ChoiceBox choiceBrand;

    @FXML
    private ChoiceBox choiceCategory;

    @FXML
    private TableColumn<CatalogProduct, Integer> codeOfProduct;

    @FXML
    private TableColumn<CatalogProduct, String> color;

    @FXML
    private TextField colorText;

    @FXML
    private DatePicker dataOfRelease;

    @FXML
    private TableColumn<CatalogProduct, String> dataOfReleaseCol;

    @FXML
    private TableColumn<CatalogProduct, String> model;

    @FXML
    private TextField modelText;

    @FXML
    private TableColumn<CatalogProduct, Integer> price;

    @FXML
    private TextField priceText;

    @FXML
    void btnAdd(ActionEvent event) throws IOException, ClassNotFoundException {
        CatalogProduct product = new CatalogProduct();
        product.setCategory((String) choiceCategory.getValue());
        product.setBrand((String) choiceBrand.getValue());
        product.setModel(modelText.getText().trim());
        product.setPrice(Integer.parseInt(priceText.getText().trim()));
        product.setDataOfSale(String.valueOf(dataOfRelease.getValue()));
        product.setColor(colorText.getText().trim());
        ArrayList<CatalogProduct> products = Client.interactionsWithServer.addTechnique(product);
        modelText.clear();
        priceText.clear();
        colorText.clear();
       // dataOfRelease.set
        for (int i = 0; i < products.size(); i++) {
            Tableview.getItems().add(products.get(i));
        }

    }

    @FXML
    void btnChange(ActionEvent event) {


    }

    @FXML
    void btnDelete(ActionEvent event) {

    }

    @FXML
    void btnExit(ActionEvent event) throws IOException{
        Main.setRoot("menuAM");
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        assert Tableview != null : "fx:id=\"Tableview\" was not injected: check your FXML file 'addTechnique.fxml'.";
        assert brand != null : "fx:id=\"brand\" was not injected: check your FXML file 'addTechnique.fxml'.";
        assert category != null : "fx:id=\"category\" was not injected: check your FXML file 'addTechnique.fxml'.";
        assert choiceBrand != null : "fx:id=\"choiceBrand\" was not injected: check your FXML file 'addTechnique.fxml'.";
        assert choiceCategory != null : "fx:id=\"choiceCategory\" was not injected: check your FXML file 'addTechnique.fxml'.";
        assert codeOfProduct != null : "fx:id=\"codeOfProduct\" was not injected: check your FXML file 'addTechnique.fxml'.";
        assert color != null : "fx:id=\"color\" was not injected: check your FXML file 'addTechnique.fxml'.";
        assert colorText != null : "fx:id=\"colorText\" was not injected: check your FXML file 'addTechnique.fxml'.";
        assert dataOfRelease != null : "fx:id=\"dataOfRelease\" was not injected: check your FXML file 'addTechnique.fxml'.";
        assert dataOfReleaseCol != null : "fx:id=\"dataOfReleaseCol\" was not injected: check your FXML file 'addTechnique.fxml'.";
        assert model != null : "fx:id=\"model\" was not injected: check your FXML file 'addTechnique.fxml'.";
        assert modelText != null : "fx:id=\"modelText\" was not injected: check your FXML file 'addTechnique.fxml'.";
        assert price != null : "fx:id=\"price\" was not injected: check your FXML file 'addTechnique.fxml'.";
        assert priceText != null : "fx:id=\"priceText\" was not injected: check your FXML file 'addTechnique.fxml'.";

        codeOfProduct.setCellValueFactory(new PropertyValueFactory<>("CodeofProduct"));
        model.setCellValueFactory(new PropertyValueFactory<>("model"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        dataOfReleaseCol.setCellValueFactory(new PropertyValueFactory<>("dateOfRelease"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        Tableview.setEditable(true);

        ArrayList<CatalogProduct> products = Client.interactionsWithServer.showProducts();
        ArrayList<String> categories = new ArrayList<>();
        ArrayList<String> brands = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            Tableview.getItems().add(products.get(i));
            categories.add(i, products.get(i).getBrand());
            brands.add(i, products.get(i).getCategory());
        }
        choiceBrand.setItems(FXCollections.observableList(categories));
        choiceCategory.setItems(FXCollections.observableList(brands));

    }

}
