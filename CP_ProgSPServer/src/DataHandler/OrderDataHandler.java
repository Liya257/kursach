package DataHandler;

import Database.Database;
import Interfaces.IObjectService;
import sample.Classes.Client;
import tableClasses.CatalogProduct;
import tableClasses.Orders;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDataHandler implements IObjectService {
    private int idClient;
    private Socket socket;
    private ObjectInputStream messageFromClient;
    private ObjectOutputStream writeMessage;
    private Database database;

    public OrderDataHandler(int countUser, Socket socket, Database database, ObjectInputStream messageFromClient, ObjectOutputStream writeMessage) {
        this.idClient = countUser;
        this.socket = socket;
        this.database = database;
        this.messageFromClient = messageFromClient;
        // this.writeMessage = writeMessage;
    }

    @Override
    public ArrayList<?> showAll() {

       try {
           ArrayList<Orders> orders = database.showAllOrders();
           return orders;
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return null;
    }
    public ArrayList<Orders> showOrderClient() throws SQLException {
        ArrayList<Orders> orders = database.showClientOrder();
        return orders;
    }

    @Override
    public void delete() {

    }
    public void deleteOrder() throws SQLException, IOException, ClassNotFoundException {
        Orders orders = (Orders) messageFromClient.readObject();
        database.deleteOrder(orders);
    }

    @Override
    public int addInDatabase() {
        try {
            CatalogProduct product = (CatalogProduct) messageFromClient.readObject();
            database.addOrder(product);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void changeInDatabase() {

    }

    public ArrayList<Integer> getStatisticsTechnique() throws SQLException {
        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<Integer> category = database.numbersOrders();
        int notebook = 0, ultrabook = 0;
        for (int i = 0; i < category.size(); i++) {
            if (category.get(i) == 6) {
                notebook++;
            } else if (category.get(i) == 1){
                ultrabook++;
            }
        }
        System.out.println(notebook);
        System.out.println(ultrabook);
        System.out.println(numbers);
        numbers.add(0, Integer.parseInt(String.valueOf(notebook)));
        numbers.add(1, Integer.parseInt(String.valueOf(ultrabook)));
        return numbers;
    }
}