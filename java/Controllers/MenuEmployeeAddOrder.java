package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sourse.cp_progsp.Main;

public class MenuEmployeeAddOrder {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField brand;

    @FXML
    private TextField category;

    @FXML
    private TextField color;

    @FXML
    private DatePicker dataOrder;

    @FXML
    private TextField loginUser;

    @FXML
    private TextField model;

    @FXML
    void btnAdd(ActionEvent event) {

    }

    @FXML
    void btnBackMenu(ActionEvent event) throws IOException {
        Main.setRoot("menuAdminEdit");
    }

    @FXML
    void initialize() {
        assert brand != null : "fx:id=\"brand\" was not injected: check your FXML file 'menuAddOrderEmployee.fxml'.";
        assert category != null : "fx:id=\"category\" was not injected: check your FXML file 'menuAddOrderEmployee.fxml'.";
        //assert codeOfProduct != null : "fx:id=\"codeOfProduct\" was not injected: check your FXML file 'menuAddOrderEmployee.fxml'.";
        assert color != null : "fx:id=\"color\" was not injected: check your FXML file 'menuAddOrderEmployee.fxml'.";
        assert dataOrder != null : "fx:id=\"dataOrder\" was not injected: check your FXML file 'menuAddOrderEmployee.fxml'.";
        assert loginUser != null : "fx:id=\"loginUser\" was not injected: check your FXML file 'menuAddOrderEmployee.fxml'.";
        assert model != null : "fx:id=\"model\" was not injected: check your FXML file 'menuAddOrderEmployee.fxml'.";

    }

}
