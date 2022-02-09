package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EmployeeView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> Tableview;

    @FXML
    private TableColumn<?, ?> idEmployee;

    @FXML
    private TableColumn<?, ?> nameEmployee;

    @FXML
    private TextField nameText;

    @FXML
    private TableColumn<?, ?> position;

    @FXML
    private TextField positoinText;

    @FXML
    private TableColumn<?, ?> surnameEmployee;

    @FXML
    private TextField surnameText;

    @FXML
    void btnChange(ActionEvent event) {

    }

    @FXML
    void btnExitMenu(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert Tableview != null : "fx:id=\"Tableview\" was not injected: check your FXML file 'employeeView.fxml'.";
        assert idEmployee != null : "fx:id=\"idEmployee\" was not injected: check your FXML file 'employeeView.fxml'.";
        assert nameEmployee != null : "fx:id=\"nameEmployee\" was not injected: check your FXML file 'employeeView.fxml'.";
        assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'employeeView.fxml'.";
        assert position != null : "fx:id=\"position\" was not injected: check your FXML file 'employeeView.fxml'.";
        assert positoinText != null : "fx:id=\"positoinText\" was not injected: check your FXML file 'employeeView.fxml'.";
        assert surnameEmployee != null : "fx:id=\"surnameEmployee\" was not injected: check your FXML file 'employeeView.fxml'.";
        assert surnameText != null : "fx:id=\"surnameText\" was not injected: check your FXML file 'employeeView.fxml'.";

    }

}
