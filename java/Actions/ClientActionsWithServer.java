package Actions;

import sample.Classes.*;
import sample.Classes.Client;
import tableClasses.AccountEmployee;
import tableClasses.CatalogProduct;
import tableClasses.Orders;
import tableClasses.Salary;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientActionsWithServer {

    private ObjectOutputStream sendMessage;
    private ObjectInputStream acceptMessage;


    public ClientActionsWithServer(Socket clientSocket) {
        try {
            sendMessage = new ObjectOutputStream(clientSocket.getOutputStream());
            acceptMessage = new ObjectInputStream(clientSocket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void send(Object user) {
        try {
            sendMessage.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void send(String text) {
        try {
            sendMessage.writeObject(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int returnRole() throws IOException, ClassNotFoundException {
        send("рольДляАккаунтаС");
        User user = new User();
        user.setOnline(1);
        send(user);
        int role = (int) acceptMessage.readObject();
        return role;
    }

    public ArrayList<AccountEmployee> AccountEmployee() throws IOException, ClassNotFoundException {
        send("инфоАккаунтаС");
        ArrayList<AccountEmployee> acc = (ArrayList<AccountEmployee>) acceptMessage.readObject();
        return acc;
    }

    public ArrayList<User> CheckUserInDataBase(User user) throws IOException, ClassNotFoundException {
        send("Вход");
        send(user);
        ArrayList<User> user5 = (ArrayList<User>) acceptMessage.readObject();
        return user5;
    }
    //////////////////////////////////////////////////////////////////////

    public ArrayList<CatalogProduct> addTechnique(CatalogProduct product) throws IOException, ClassNotFoundException {
        send("добавитьТехнику");
        send(product);
        ArrayList<CatalogProduct> products = (ArrayList<CatalogProduct>) acceptMessage.readObject();
        return products;
    }


    //////////////////////////////////////////////////////////////////////
    public ArrayList<TechniqueBrand> addBrand(TechniqueBrand brand) throws IOException, ClassNotFoundException {
        send("добавитьБренд");
        send(brand);
        ArrayList<TechniqueBrand> brands = (ArrayList<TechniqueBrand>) acceptMessage.readObject();
        return brands;
    }
    public void updateBrand(TechniqueBrand brand) {
        send("обновитьБренд");
        send(brand);
    }
    public void deleteBrand(TechniqueBrand brand) {
        send("удалитьБренд");
        send(brand);
    }
    public ArrayList<TechniqueBrand> showBrand() throws IOException, ClassNotFoundException {
        send("всеБренды");
        ArrayList<TechniqueBrand> brands = (ArrayList<TechniqueBrand>) acceptMessage.readObject();
        return brands;
    }

////////////////////////////////////////////////////////////////////////////
    public ArrayList<TechniqueCategory> addCategory(TechniqueCategory category) throws IOException, ClassNotFoundException {
        send("добавитьКатегорию");
        send(category);
        ArrayList<TechniqueCategory> categories = (ArrayList<TechniqueCategory>) acceptMessage.readObject();
        return categories;
    }
    public void updateCategory(TechniqueCategory category) {
        send("обновитьКатегорию");
        send(category);
    }
    public void deleteCategory(TechniqueCategory category) {
        send("удалитьКатегорию");
        send(category);
    }
    public ArrayList<TechniqueCategory> showCategory() throws IOException, ClassNotFoundException {
        send("всеКатегории");
        ArrayList<TechniqueCategory> categories = (ArrayList<TechniqueCategory>) acceptMessage.readObject();
        return categories;
    }
    ////////////////////////////////////////////////////
    public void addNewOrder(CatalogProduct products) {
        send("заказК");
        send(products);
    }
    public ArrayList<Orders> showOrderClient() throws IOException, ClassNotFoundException {
        send("показатьЗаказыКлиента");
        ArrayList<Orders> orders = (ArrayList<Orders>) acceptMessage.readObject();
        return orders;
    }
    public ArrayList<Orders> showAllOrders() throws IOException, ClassNotFoundException {//+
        send("всеЗаказы");
        ArrayList<Orders> orders = (ArrayList<Orders>) acceptMessage.readObject();
        return orders;
    }
    public ArrayList<Orders> showNotAcceptOrders() throws IOException, ClassNotFoundException {
        send("непринятыеЗаказы");
        ArrayList<Orders> orders = (ArrayList<Orders>) acceptMessage.readObject();
        return orders;
    }
    public void acceptOrder(Orders orders) {
        send("принятьЗаказ");
        send(orders);
    }
    public void deleteOrder(Orders orders) {//+
        send("удалитьЗаказ");
        send(orders);
    }
    public ArrayList<Integer> getStatisticsTechnique() throws IOException, ClassNotFoundException {
        send("статистикаТехники");
        ArrayList<Integer> numbers = (ArrayList<Integer>) acceptMessage.readObject();
        return numbers;
    }
    //////////////////////////////////////////////////////
    public ArrayList<Salary> returnBonuses(Salary salary) throws IOException, ClassNotFoundException {
        send("расчетБонусов");
        send(salary);
        ArrayList<Salary> salaryy = (ArrayList<Salary>) acceptMessage.readObject();
        return salaryy;
    }
    public ArrayList<Salary> returnSalary(Salary salary) throws IOException, ClassNotFoundException {
        send("расчетЗП");
        send(salary);
        ArrayList<Salary> salary1 = (ArrayList<Salary>) acceptMessage.readObject();
        return salary1;
    }
    public ArrayList<Salary> showInform() throws IOException, ClassNotFoundException {
        send("информЗП");
        ArrayList<Salary> salary = (ArrayList<Salary>) acceptMessage.readObject();
        return salary;
    }
    public ArrayList<CatalogProduct> showProducts() throws IOException, ClassNotFoundException {
        send("всяТехника");
        ArrayList<CatalogProduct> products =(ArrayList<CatalogProduct>) acceptMessage.readObject();
        return products;
    }

    public void addNewEmployeeInDatabase(User user, Employee employee) throws IOException, ClassNotFoundException {
        send("добавитьС");
        send(user);
        send(user);
        int p = (int) acceptMessage.readObject();
        System.out.println(p);
        employee.setIdUser(p);
        send(employee);
    }
    public void blockClient(Client client) throws IOException, ClassNotFoundException {
        send("заблокироватьКлиента");
        send(client);
        //ArrayList<Client> clients = (ArrayList<Client>) acceptMessage.readObject();
        //return clients;
    }
    public void addNewUserInDataBase(User user, Client client) throws IOException, ClassNotFoundException {
        send("Регистрация");
        send(user);
        send(user);
        int k = (int) acceptMessage.readObject();
        client.setUser_idUser(k);
        send(client);
    }
    public void addNewClientInDataBase(User user, Client client) throws IOException, ClassNotFoundException {
        send("новыйК");
        send(user);
        //send(user);
        int k = (int) acceptMessage.readObject();
        client.setUser_idUser(k);
        client.setLastnameClient("");
        client.setAddress("");
        client.setPhone("");
        send(client);
    }
    public ArrayList<User> showAccountUser(User user) throws IOException, ClassNotFoundException {
        send("аккаунтП");
        user.setOnline(1);
        send(user);
        ArrayList<User> users = (ArrayList<User>) acceptMessage.readObject();
        return users;
    }
    public ArrayList<Client> showAllClients() throws IOException, ClassNotFoundException {
        send("вывестиКлиентов");
        ArrayList<Client> clients =((ArrayList<Client>) acceptMessage.readObject());
        return clients;
    }
    public void changeInDatabaseClient(Client client) {
        send("изменитьК");
        send(client);
    }

    public void deleteClient(Client client) throws IOException, ClassNotFoundException {
        send("удалитьК");
        //System.out.println(client.getIdClient());
        send(client);
    }
    public void changeCientData(Client client, User user) {
        send("обновитьДанныеК");
        send(client);
        send(user);
    }

    public int saveUserData(User user) throws IOException, ClassNotFoundException {
        send("сохранитьП");
        user.setOnline(1);
        send(user);
        int l = (int) acceptMessage.readObject();
        return l;
    }
    public void saveEmployeeData(Employee employee) throws IOException, ClassNotFoundException {
        send("сохранитьС");
        send(employee);
    }
    public void saveEmployeeAccount(Employee employee) {
        send("обновитьАккС");
        send(employee);
        send(employee);
    }
    public void saveClientData(Client client) {
        send("сохранитьК");
        send(client);
    }
    public ArrayList<Client> showAccountClient(Client client) throws IOException, ClassNotFoundException {
        send("аккаунтК");
        send(client);
        ArrayList<Client> clients = (ArrayList<Client>) acceptMessage.readObject();
        return clients;
    }

    public void setOnlineUser(User user) {
        send("онлайнК");
        send(user);

    }
    public void setOfflineUser(User user) {
        send("офлайнК");
        send(user);
    }


}
