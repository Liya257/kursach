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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Classes.TechniqueBrand;
import sample.Classes.TechniqueCategory;
import sourse.cp_progsp.Main;

public class AddBrand {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<TechniqueBrand> Tableview;

    @FXML
    private TableColumn<TechniqueBrand, Integer> idBrand;

    @FXML
    private TableColumn<TechniqueBrand, String> nameBrand;

    @FXML
    private TextField nameText;

    @FXML
    void btnAdd(ActionEvent event) throws IOException, ClassNotFoundException {
        Tableview.getItems().clear();
        TechniqueBrand brand = new TechniqueBrand();
        if (nameText.getText().equals("")) {
            Alerts.SetAlert(false, "Не все поля заполнены");
            return;
        }
        brand.setBrand(nameText.getText().trim());
        ArrayList<TechniqueBrand> brands = Client.interactionsWithServer.addBrand(brand);
        nameText.clear();
        for (int i = 0; i < brands.size(); i++) {
            Tableview.getItems().add(brands.get(i));
        }
        Tableview.refresh();

    }

    @FXML
    void btnChange(ActionEvent event) throws IOException {
        TechniqueBrand selectedRow = Tableview.getSelectionModel().getSelectedItem();
        if (nameText.getText().trim().length() != 0) {
            selectedRow.setBrand(nameText.getText());
            Client.interactionsWithServer.updateBrand(selectedRow);
            nameText.clear();

        } else {
            Alerts.SetAlert(false, "Поля для изменения пусты! Заполните их.");
            Main.setRoot("addBrand");
        }
        Tableview.refresh();

    }

    @FXML
    void btnDelete(ActionEvent event) {
        TechniqueBrand selectedItem = Tableview.getSelectionModel().getSelectedItem();
        ObservableList<TechniqueBrand> brands = Tableview.getItems();
        Client.interactionsWithServer.deleteBrand(selectedItem);
        brands.remove(selectedItem);

    }

    @FXML
    void btnExit(ActionEvent event) throws IOException {
        Main.setRoot("menuAdminProduct");
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        assert Tableview != null : "fx:id=\"Tableview\" was not injected: check your FXML file 'addBrand.fxml'.";
        assert idBrand != null : "fx:id=\"idBrand\" was not injected: check your FXML file 'addBrand.fxml'.";
        assert nameBrand != null : "fx:id=\"nameBrand\" was not injected: check your FXML file 'addBrand.fxml'.";
        assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'addBrand.fxml'.";

        idBrand.setCellValueFactory(new PropertyValueFactory<>("idTechniqueBrand"));
        nameBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        Tableview.setEditable(true);

        Tableview.setEditable(true);
        ArrayList<TechniqueBrand> brands = Client.interactionsWithServer.showBrand();
        for (int i = 0; i < brands.size(); i++) {
            Tableview.getItems().add(brands.get(i));
        }
    }

}
