package Controllers;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import Actions.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import sourse.cp_progsp.Main;
import tableClasses.Salary;

public class ReportsAdmin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Salary> Tableview;

    @FXML
    private TableColumn<Salary, Integer> idEmployee;

    @FXML
    private TableColumn<Salary, String> nameEmployee;

    @FXML
    private TableColumn<Salary, Integer> orders;

    @FXML
    private TextField salaryText;

    @FXML
    private TableColumn<Salary, String > surnameEmployee;

    @FXML
    private TableColumn<Salary, Integer> wage;

    @FXML
    void btnBonuses(ActionEvent event) throws IOException, ClassNotFoundException {
        Salary item = Tableview.getSelectionModel().getSelectedItem();
        ArrayList<Salary> salaries = Client.interactionsWithServer.returnBonuses(item);
        ObservableList<Salary> sal = Tableview.getItems();
        sal.remove(item);
        for (int i = 0; i < salaries.size(); i++) {
            Tableview.getItems().add(salaries.get(i));
            Salary salary = new Salary();
            salary = salaries.get(i);
            salaryText.setText(String.valueOf(salary.getSalary()));
        }
        Tableview.refresh();
    }


    @FXML
    void btnExitMenu(ActionEvent event) throws IOException {
        Main.setRoot("menuAM");
    }

    @FXML
    void btnSalary(ActionEvent event) throws IOException, ClassNotFoundException {
        Salary item = Tableview.getSelectionModel().getSelectedItem();
        ArrayList<Salary> salary = Client.interactionsWithServer.returnSalary(item);
        ObservableList<Salary> sal = Tableview.getItems();
        sal.remove(item);
        for (int i = 0; i < salary.size(); i++) {
            Tableview.getItems().add(salary.get(i));
            Salary  salary1 = new Salary();
            salary1 = salary.get(i);
            salaryText.setText(String.valueOf(salary1.getSalary()));
        }
        Tableview.refresh();

    }
    @FXML
    void btnReportSave(ActionEvent event) throws IOException {
        String str = "";
        FileChooser textchooser = new FileChooser();
        FileChooser.ExtensionFilter exitFilter = new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");
        textchooser.getExtensionFilters().add(exitFilter);
       // File file = textchooser.showSaveDialog(Main.);
        /*FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        int p = Tableview.getItems().size();
        String str = "Заработная плата сотрдуников с учетом премий и бонусов от компании:";
        String str1 = "Общее число сотрудников - " + p;
        fout = new FileOutputStream("D:\\228\\прогСП\\CP_ProgSP\\REPORT.txt");
        oos = new ObjectOutputStream(fout);

        ObservableList<Salary> salary = Tableview.getItems();
            oos.writeChars(str);
            oos.writeChars("-----------------------------------------------------------------");
            oos.writeChars(str1);
            oos.close();
            fout.close();*/

           /* File file=new File("File.txt");
            file.createNewFile();
            try(FileWriter writer=new FileWriter(file,true)){
                writer.write(String.valueOf(salary.get(0)));
            }*/
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        assert Tableview != null : "fx:id=\"Tableview\" was not injected: check your FXML file 'reportsAdmin.fxml'.";
        assert idEmployee != null : "fx:id=\"idEmployee\" was not injected: check your FXML file 'reportsAdmin.fxml'.";
        assert nameEmployee != null : "fx:id=\"nameEmployee\" was not injected: check your FXML file 'reportsAdmin.fxml'.";
        assert orders != null : "fx:id=\"orders\" was not injected: check your FXML file 'reportsAdmin.fxml'.";
        assert salaryText != null : "fx:id=\"salary\" was not injected: check your FXML file 'reportsAdmin.fxml'.";
        assert surnameEmployee != null : "fx:id=\"surnameEmployee\" was not injected: check your FXML file 'reportsAdmin.fxml'.";
        assert wage != null : "fx:id=\"wage\" was not injected: check your FXML file 'reportsAdmin.fxml'.";
        idEmployee.setCellValueFactory(new PropertyValueFactory<>("idEmployee"));
        surnameEmployee.setCellValueFactory(new PropertyValueFactory<>("surnameEmployee"));
        nameEmployee.setCellValueFactory(new PropertyValueFactory<>("nameEmployee"));
        orders.setCellValueFactory(new PropertyValueFactory<>("orders"));
        wage.setCellValueFactory(new PropertyValueFactory<>("salary"));
        Tableview.setEditable(true);
        ArrayList<Salary> employees = Client.interactionsWithServer.showInform();
        for (int i = 0; i < employees.size(); i++) {
            Tableview.getItems().add(employees.get(i));
        }
    }

}
