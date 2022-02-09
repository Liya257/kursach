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
import sample.Classes.TechniqueCategory;
import sourse.cp_progsp.Main;

public class AddCategory {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<TechniqueCategory> Tableview;

    @FXML
    private TableColumn<TechniqueCategory, Integer> idCategory;

    @FXML
    private TableColumn<TechniqueCategory, String> nameCategory;

    @FXML
    private TextField nameText;

    @FXML
    void btnAdd(ActionEvent event) throws IOException, ClassNotFoundException {

        TechniqueCategory category = new TechniqueCategory();
        if (nameText.getText().equals("")) {
            Alerts.SetAlert(false, "Не все поля заполнены");
            return;
        }
        category.setCategory(nameText.getText().trim());
        ArrayList<TechniqueCategory> categories = (ArrayList<TechniqueCategory>) Client.interactionsWithServer.addCategory(category);
        nameText.clear();
        Tableview.getItems().clear();
        for (int i = 0; i < categories.size(); i++) {
            Tableview.getItems().add(categories.get(i));
        }
        //Tableview.refresh();
    }

    @FXML
    void btnChange(ActionEvent event) throws IOException {
        TechniqueCategory selectesRow = Tableview.getSelectionModel().getSelectedItem();
        if (nameText.getText().trim().length() != 0) {
            selectesRow.setCategory(nameText.getText().trim());
            Client.interactionsWithServer.updateCategory(selectesRow);
            System.out.println(selectesRow.getCategory());
            nameText.clear();

        } else {
            Alerts.SetAlert(false, "Поля для изменения пусты! Заполните их.");
            Main.setRoot("addCategory");
        }
        Tableview.refresh();

    }

    @FXML
    void btnDelete(ActionEvent event) {
        TechniqueCategory selectedItem = Tableview.getSelectionModel().getSelectedItem();
        ObservableList<TechniqueCategory> categories = Tableview.getItems();
        Client.interactionsWithServer.deleteCategory(selectedItem);
        categories.remove(selectedItem);
    }

    @FXML
    void btnExit(ActionEvent event) throws IOException{
        Main.setRoot("menuAdminProduct");
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        assert Tableview != null : "fx:id=\"Tableview\" was not injected: check your FXML file 'addCategory.fxml'.";
        assert idCategory != null : "fx:id=\"idCategory\" was not injected: check your FXML file 'addCategory.fxml'.";
        assert nameCategory != null : "fx:id=\"nameCategory\" was not injected: check your FXML file 'addCategory.fxml'.";
        assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'addCategory.fxml'.";

        idCategory.setCellValueFactory(new PropertyValueFactory<>("idCategory"));
        nameCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        Tableview.setEditable(true);
        ArrayList<TechniqueCategory> categories = Client.interactionsWithServer.showCategory();
        for (int i = 0; i < categories.size(); i++) {
            Tableview.getItems().add(categories.get(i));
        }
    }

}
