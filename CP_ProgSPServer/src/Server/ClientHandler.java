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
                case "Регистрация":
                    int s  = (int) userDataHandler.addInDatabase();
                    employeeDataHandler.setOfflineEmployee();
                    sendMessage(s);
                    clientDataHandler.addInDatabase();
                    break;
                case "Вход":
                    ArrayList<User> user9 = userDataHandler.сheckInDatabase();
                    sendMessage(user9);
                    break;
                case "онлайнК":
                    userDataHandler.setOnlineUser();
                    break;
                case "офлайнК":
                    userDataHandler.setOfflineUser();
                    break;
                case "аккаунтП":
                    ArrayList<User> user2 = userDataHandler.idUserReturn();//возвращает весь объект, не только айди юзера
                    sendMessage(user2);
                    break;
                case "аккаунтК":
                    ArrayList<Client> client = clientDataHandler.clientReturn();
                    sendMessage(client);
                    break;
                case "сохранитьП":
                    int k = userDataHandler.saveUserData();
                    sendMessage(k);
                    break;
                case "сохранитьК":
                    clientDataHandler.saveClientData();
                    break;
                case "добавитьС":
                    int l = userDataHandler.addInDatabase();
                    employeeDataHandler.setOfflineEmployee();
                    sendMessage(l);
                    employeeDataHandler.addInDatabase();
                    break;
                case "сохранитьС":
                    employeeDataHandler.saveEmployeeData();
                    break;
                case  "вывестиКлиентов":
                    ArrayList<Client> clients = clientDataHandler.showAll();
                    sendMessage(clients);
                    break;
                case "изменитьК":
                    clientDataHandler.changeInDatabase();
                    break;
                case "удалитьК":
                    clientDataHandler.delete();
                    break;
                case "инфоАккаунтаС":
                    ArrayList<AccountEmployee> empl = employeeDataHandler.accountEmployee();
                    sendMessage(empl);
                    break;
                case "обновитьАккС":
                    ArrayList<Employee> employees = employeeDataHandler.returnEmployee();
                    sendMessage(employees);
                    employeeDataHandler.saveEmployeeAccount();
                    break;
                case "рольДляАккаунтаС":
                    int f = userDataHandler.roleUserReturn();
                    System.out.println(f);
                    sendMessage(f);
                    break;
                case "обновитьДанныеК":
                    clientDataHandler.ChangeUserData();
                    break;
                case "новыйК":
                    int p = userDataHandler.addInDatabase();
                    sendMessage(p);
                    clientDataHandler.addInDatabase();
                    break;
                case "всеЗаказы":
                    ArrayList<Orders> orders = (ArrayList<Orders>) orderdataHandler.showAll();
                    sendMessage(orders);
                    break;
                case "непринятыеЗаказы":
                    ArrayList<Orders> orders4 = employeeDataHandler.notAcceptOrder();
                    sendMessage(orders4);
                    break;
                case "принятьЗаказ":
                    employeeDataHandler.acceptOrder();
                    break;
                case "показатьЗаказыКлиента":
                    ArrayList<Orders> order2 = orderdataHandler.showOrderClient();
                    sendMessage(order2);
                    break;
                case "удалитьЗаказ":
                    orderdataHandler.deleteOrder();
                    break;
                case "заказК":
                    orderdataHandler.addInDatabase();
                    break;
                case "всеКатегории":
                    ArrayList<TechniqueCategory> categories = techniqueDataHandler.showCategories();
                    sendMessage(categories);
                    break;
                case "удалитьКатегорию":
                    techniqueDataHandler.deleteCategory();
                    break;
                case "обновитьКатегорию":
                    techniqueDataHandler.updateCategory();
                    break;
                case "добавитьКатегорию":
                    ArrayList<TechniqueCategory> categories1 = techniqueDataHandler.addCategory();
                    sendMessage(categories1);
                    break;
                case "всеБренды":
                    ArrayList<TechniqueBrand> brands = techniqueDataHandler.showBrand();
                    sendMessage(brands);
                    break;
                case "удалитьБренд":
                    techniqueDataHandler.deleteBrand();
                    break;
                case "обновитьБренд":
                    techniqueDataHandler.updateBrand();
                    break;
                case "добавитьБренд":
                    ArrayList<TechniqueBrand> brands1 = techniqueDataHandler.addBrand();//добавить возврат айди
                    sendMessage(brands1);
                    break;
                case "всяТехника":
                    ArrayList<CatalogProduct> products = (ArrayList<CatalogProduct>) techniqueDataHandler.showAll();
                    sendMessage(products);
                    break;
                case "добавитьТехнику":
                    ArrayList<CatalogProduct> catalog = (ArrayList<CatalogProduct>) techniqueDataHandler.addTechnique();
                    sendMessage(catalog);
                    break;
                case "статистикаТехники":
                    ArrayList<Integer> numbers = orderdataHandler.getStatisticsTechnique();
                    sendMessage(numbers);
                    break;
                case "заблокироватьКлиента":
                    employeeDataHandler.blockedClient();
                    break;
                case "расчетБонусов":
                    ArrayList<Salary> salarie = employeeDataHandler.bonusesCount();
                    sendMessage(salarie);
                    break;
                case "расчетЗП":
                    ArrayList<Salary> salaries = employeeDataHandler.countSalary();
                    sendMessage(salaries);
                    break;
                case "информЗП":
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
