package Server;

import DataHandler.*;
import Database.Database;
import sample.Classes.*;
import tableClasses.AccountEmployee;
import tableClasses.CatalogProduct;
import tableClasses.Orders;
import tableClasses.Salary;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientHandler extends Thread{
    private UserDataHandler userDataHandler;
    private TechniqueDataHandler techniqueDataHandler;
    private EmployeeDataHandler employeeDataHandler;
    private ClientDataHandler clientDataHandler;
    private OrderDataHandler orderdataHandler;

    private Socket socket;
    private ObjectInputStream messageFromServer;
    private ObjectOutputStream writeMessage;

    public ClientHandler(Socket socket, Database database, int idClient) {
        this.socket = socket;
        try {
            messageFromServer = new ObjectInputStream(socket.getInputStream());
            writeMessage = new ObjectOutputStream(socket.getOutputStream());
            userDataHandler = new UserDataHandler(idClient, socket, database, messageFromServer, writeMessage);
            orderdataHandler = new OrderDataHandler(idClient, socket, database, messageFromServer, writeMessage);
            clientDataHandler = new ClientDataHandler(idClient, socket, database, messageFromServer, writeMessage);
            employeeDataHandler = new EmployeeDataHandler(idClient, socket, database, messageFromServer, writeMessage);
            techniqueDataHandler = new TechniqueDataHandler(idClient, socket, database, messageFromServer, writeMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }
    @Override
    public void run() {
        try {
            userHandler();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void userHandler() throws IOException, ClassNotFoundException, SQLException {
        while (true) {

            String command = null;
            try
            {
                command = (String) messageFromServer.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            switch (command)
            {
                case "??????????????????????":
                    int s  = (int) userDataHandler.addInDatabase();
                    employeeDataHandler.setOfflineEmployee();
                    sendMessage(s);
                    clientDataHandler.addInDatabase();
                    break;
                case "????????":
                    ArrayList<User> user9 = userDataHandler.??heckInDatabase();
                    sendMessage(user9);
                    break;
                case "??????????????":
                    userDataHandler.setOnlineUser();
                    break;
                case "??????????????":
                    userDataHandler.setOfflineUser();
                    break;
                case "????????????????":
                    ArrayList<User> user2 = userDataHandler.idUserReturn();//???????????????????? ???????? ????????????, ???? ???????????? ???????? ??????????
                    sendMessage(user2);
                    break;
                case "????????????????":
                    ArrayList<Client> client = clientDataHandler.clientReturn();
                    sendMessage(client);
                    break;
                case "????????????????????":
                    int k = userDataHandler.saveUserData();
                    sendMessage(k);
                    break;
                case "????????????????????":
                    clientDataHandler.saveClientData();
                    break;
                case "??????????????????":
                    int l = userDataHandler.addInDatabase();
                    employeeDataHandler.setOfflineEmployee();
                    sendMessage(l);
                    employeeDataHandler.addInDatabase();
                    break;
                case "????????????????????":
                    employeeDataHandler.saveEmployeeData();
                    break;
                case  "??????????????????????????????":
                    ArrayList<Client> clients = clientDataHandler.showAll();
                    sendMessage(clients);
                    break;
                case "??????????????????":
                    clientDataHandler.changeInDatabase();
                    break;
                case "????????????????":
                    clientDataHandler.delete();
                    break;
                case "??????????????????????????":
                    ArrayList<AccountEmployee> empl = employeeDataHandler.accountEmployee();
                    sendMessage(empl);
                    break;
                case "????????????????????????":
                    ArrayList<Employee> employees = employeeDataHandler.returnEmployee();
                    sendMessage(employees);
                    employeeDataHandler.saveEmployeeAccount();
                    break;
                case "????????????????????????????????":
                    int f = userDataHandler.roleUserReturn();
                    System.out.println(f);
                    sendMessage(f);
                    break;
                case "??????????????????????????????":
                    clientDataHandler.ChangeUserData();
                    break;
                case "????????????":
                    int p = userDataHandler.addInDatabase();
                    sendMessage(p);
                    clientDataHandler.addInDatabase();
                    break;
                case "??????????????????":
                    ArrayList<Orders> orders = (ArrayList<Orders>) orderdataHandler.showAll();
                    sendMessage(orders);
                    break;
                case "????????????????????????????????":
                    ArrayList<Orders> orders4 = employeeDataHandler.notAcceptOrder();
                    sendMessage(orders4);
                    break;
                case "????????????????????????":
                    employeeDataHandler.acceptOrder();
                    break;
                case "??????????????????????????????????????????":
                    ArrayList<Orders> order2 = orderdataHandler.showOrderClient();
                    sendMessage(order2);
                    break;
                case "????????????????????????":
                    orderdataHandler.deleteOrder();
                    break;
                case "????????????":
                    orderdataHandler.addInDatabase();
                    break;
                case "????????????????????????":
                    ArrayList<TechniqueCategory> categories = techniqueDataHandler.showCategories();
                    sendMessage(categories);
                    break;
                case "????????????????????????????????":
                    techniqueDataHandler.deleteCategory();
                    break;
                case "??????????????????????????????????":
                    techniqueDataHandler.updateCategory();
                    break;
                case "??????????????????????????????????":
                    ArrayList<TechniqueCategory> categories1 = techniqueDataHandler.addCategory();
                    sendMessage(categories1);
                    break;
                case "??????????????????":
                    ArrayList<TechniqueBrand> brands = techniqueDataHandler.showBrand();
                    sendMessage(brands);
                    break;
                case "????????????????????????":
                    techniqueDataHandler.deleteBrand();
                    break;
                case "??????????????????????????":
                    techniqueDataHandler.updateBrand();
                    break;
                case "??????????????????????????":
                    ArrayList<TechniqueBrand> brands1 = techniqueDataHandler.addBrand();//???????????????? ?????????????? ????????
                    sendMessage(brands1);
                    break;
                case "????????????????????":
                    ArrayList<CatalogProduct> products = (ArrayList<CatalogProduct>) techniqueDataHandler.showAll();
                    sendMessage(products);
                    break;
                case "??????????????????????????????":
                    ArrayList<CatalogProduct> catalog = (ArrayList<CatalogProduct>) techniqueDataHandler.addTechnique();
                    sendMessage(catalog);
                    break;
                case "??????????????????????????????????":
                    ArrayList<Integer> numbers = orderdataHandler.getStatisticsTechnique();
                    sendMessage(numbers);
                    break;
                case "????????????????????????????????????????":
                    employeeDataHandler.blockedClient();
                    break;
                case "??????????????????????????":
                    ArrayList<Salary> salarie = employeeDataHandler.bonusesCount();
                    sendMessage(salarie);
                    break;
                case "????????????????":
                    ArrayList<Salary> salaries = employeeDataHandler.countSalary();
                    sendMessage(salaries);
                    break;
                case "????????????????":
                    ArrayList<Salary> salariess = employeeDataHandler.returnSalary();
                    sendMessage(salariess);
                    break;



            }
        }
    }
    public void sendMessage(String msg){
        try {
            writeMessage.writeObject(msg);
            writeMessage.flush();
        } catch (IOException ignored) {
        }
    }
    public void sendMessage(int msg){
        try {
            writeMessage.writeObject(msg);
            writeMessage.flush();
        } catch (IOException ignored) {
        }
    }

    public void sendMessage(ArrayList<?> msg){
        try {
            writeMessage.writeObject(msg);
        } catch (IOException ignored) {
        }
    }
}
