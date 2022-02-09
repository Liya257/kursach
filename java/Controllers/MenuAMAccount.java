package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Actions.Client;
import Windows.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import sample.Classes.Employee;
import sample.Classes.User;
import sourse.cp_progsp.Main;
import tableClasses.AccountEmployee;

public class MenuAMAccount {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField lastnameText;

    @FXML
    private TextField loginText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField passwordText;

    @FXML
    private TextField phoneText;

    @FXML
    private TextField positionText;

    @FXML
    private TextField salaryText;

    @FXML
    private TextField surnameText;


    @FXML
    private Label labelTitle;

    @FXML
    void btnMenuEmployee(ActionEvent event) throws IOException {
        Main.setRoot("menuAM");
    }

    @FXML
    void btnSave(ActionEvent event) throws IOException, ClassNotFoundException {
       int role = Client.interactionsWithServer.returnRole();
       if (role == 0) {
           if (loginText.getText().equals("")||passwordText.getText().equals("")||
                   surnameText.getText().equals("")||nameText.getText().equals("")||
                   lastnameText.getText().equals("")||salaryText.getText().equals("")||
                   positionText.getText().equals("")||phoneText.getText().equals("")) {
               Alerts.SetAlert(false, "Не все поля заполнены");
               return;
           }
           Employee employee = new Employee();
           User user = new User();
           user.setLogin(loginText.getText());
           user.setPassword(passwordText.getText());
           user.setOnline(0);
           user.setRole(2);
           employee.setSurnameEmployee(surnameText.getText());
           employee.setNameEmployee(nameText.getText());
           employee.setLastnameEmployee(lastnameText.getText());
           employee.setPhone(phoneText.getText());
           employee.setPosition(positionText.getText());
           employee.setSalary(Integer.parseInt(salaryText.getText()));
           Client.interactionsWithServer.addNewEmployeeInDatabase(user,employee);
           Alerts.SetAlert(true,"Успешное сохранение информации!");
           Main.setRoot("MenuAM");
       }
       else if(role == 2) {
           Employee employee = new Employee();
           User user = new User();
           user.setLogin(loginText.getText());
           user.setPassword(passwordText.getText());
           int g = Client.interactionsWithServer.saveUserData(user);
           employee.setIdUser(g);
           employee.setSurnameEmployee(surnameText.getText());
           employee.setNameEmployee(nameText.getText());
           employee.setLastnameEmployee(lastnameText.getText());
           employee.setPhone(phoneText.getText());
           Client.interactionsWithServer.saveEmployeeAccount(employee);
           Alerts.SetAlert(true,"Успешное изменение информации!");
           Main.setRoot("menuAM");


       }
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        assert lastnameText != null : "fx:id=\"lastnameText\" was not injected: check your FXML file 'menuEmployeeAccount.fxml'.";
        assert loginText != null : "fx:id=\"loginText\" was not injected: check your FXML file 'menuEmployeeAccount.fxml'.";
        assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'menuEmployeeAccount.fxml'.";
        assert passwordText != null : "fx:id=\"passwordText\" was not injected: check your FXML file 'menuEmployeeAccount.fxml'.";
        assert phoneText != null : "fx:id=\"phoneText\" was not injected: check your FXML file 'menuEmployeeAccount.fxml'.";
        assert positionText != null : "fx:id=\"positionText\" was not injected: check your FXML file 'menuEmployeeAccount.fxml'.";
        assert salaryText != null : "fx:id=\"salaryText\" was not injected: check your FXML file 'menuEmployeeAccount.fxml'.";
        assert surnameText != null : "fx:id=\"surnameText\" was not injected: check your FXML file 'menuEmployeeAccount.fxml'.";

        int role = Client.interactionsWithServer.returnRole();
        System.out.println(role);
        if(role == 0) {
            labelTitle.setTextAlignment(TextAlignment.CENTER);
            labelTitle.setLayoutX(50);
            labelTitle.setText("ДОБАВИТЬ СОТРУДНИКА");
            lastnameText.clear();
            loginText.clear();
            nameText.clear();
            passwordText.clear();
            phoneText.clear();
            positionText.clear();
            salaryText.clear();
            surnameText.clear();
        } else if(role == 2) {
            labelTitle.setTextAlignment(TextAlignment.CENTER);
            labelTitle.setLayoutX(125);
            labelTitle.setText("СОТРУДНИК");
            AccountEmployee acc = new AccountEmployee();
            ArrayList<AccountEmployee> empl = Client.interactionsWithServer.AccountEmployee();
            for (int i = 0; i < empl.size(); i++) {
                acc = empl.get(i);
            }
            loginText.setText(acc.getLogin());
            passwordText.setText(acc.getPassword());
            surnameText.setText(acc.getSurnameEmployee());
            nameText.setText(acc.getNameEmployee());
            lastnameText.setText(acc.getLastnameEmployee());
            positionText.setText(acc.getPosition());
            salaryText.setText(String.valueOf(acc.getSalary()));
            salaryText.setEditable(false);
            positionText.setEditable(false);
            phoneText.setText(acc.getPhone());
        }
    }

}
