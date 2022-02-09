package DataHandler;

import Database.Database;
import Interfaces.IObjectService;
import sample.Classes.Client;
import sample.Classes.Employee;
import sample.Classes.User;
import tableClasses.AccountEmployee;
import tableClasses.Orders;
import tableClasses.Salary;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeDataHandler implements IObjectService {

    private int idClient;
    private Socket socket;
    private ObjectInputStream messageFromClient;
    private ObjectOutputStream writeMessage;
    private Database database;

    public EmployeeDataHandler(int countUser, Socket socket, Database database, ObjectInputStream messageFromClient, ObjectOutputStream writeMessage) {
        this.idClient = countUser;
        this.socket = socket;
        this.database = database;
        this.messageFromClient = messageFromClient;
        // this.writeMessage = writeMessage;
    }


    @Override
    public ArrayList<?> showAll() {
        return null;
    }

    public ArrayList<Orders> notAcceptOrder() throws SQLException, IOException, ClassNotFoundException {
        //Orders orders = (Orders) messageFromClient.readObject();
        ArrayList<Orders> orders1 = database.showNotOrderAccept();
        return orders1;
    }

    public void acceptOrder() throws IOException, ClassNotFoundException, SQLException {
        Orders orders = (Orders) messageFromClient.readObject();
        database.acceptOrder(orders);
    }
    @Override
    public void delete() {

    }

    public ArrayList<Employee> returnEmployee() {
        try{
            Employee employee;
            employee = ((Employee) messageFromClient.readObject());
            ArrayList<Employee> employees = database.returnemployee(employee);
            return employees;
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int addInDatabase() {
        try
        {
            Employee employee;
            employee = ((Employee) messageFromClient.readObject());
            System.out.println(employee.getPosition());
            database.WriteEmployee(employee);
            return 1;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setOfflineEmployee() throws IOException, ClassNotFoundException {
        User user;
        user = ((User) messageFromClient.readObject());
        database.setOfflineEmployee(user);
    }
    @Override
    public void changeInDatabase() {

    }
    public void saveEmployeeData() {
        try {
            Employee employee;
            employee = ((Employee) messageFromClient.readObject());
            database.SaveEmployeeData(employee);

        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<AccountEmployee> accountEmployee() {
        try {
            ArrayList<AccountEmployee> acc = database.accountEmployee();
            AccountEmployee accountEmployee = new AccountEmployee();
            for (int i = 0; i < acc.size(); i++) {
                accountEmployee = acc.get(i);

            }
            return acc;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void blockedClient() throws IOException, ClassNotFoundException, SQLException {
        Client client = (Client) messageFromClient.readObject();
        database.blockedClient(client);
    }
    public void saveEmployeeAccount() {
        try {
            Employee employee;
            employee = (Employee) messageFromClient.readObject();
            database.SaveEmployeeAccount(employee);
        } catch (IOException | ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    public ArrayList<Salary> returnSalary() throws SQLException {
        Map<Integer, Integer> num = countOrders();
        ArrayList<Integer> value = new ArrayList<>();
        ArrayList<Integer> key = new ArrayList<>();

        //for (int i = 0; i < values.size(); i++) {
           // System.out.println(values.get(i));
       // }
            for (Map.Entry<Integer, Integer> entry : num.entrySet()) {
                Integer m = entry.getKey();
                value.add(entry.getValue());
                //System.out.println("val" + value);
                key.add(m);
            }

       // System.out.println("key^" + key);
        //System.out.println("value^" + value);
        ArrayList<Salary> values = database.informEmpl(key,value);
        return values;
    }
    public Map<Integer, Integer> countOrders() throws SQLException {
       // ArrayList<Integer> count = new ArrayList<>();
        ArrayList<Integer> list = database.showEmplOrder();
        Map<Integer, Integer> frequency = list.stream()
                .collect(Collectors.toMap(
                        e -> e,
                        e -> 1,
                        Integer::sum
                ));
        frequency.forEach((k,v)->
                System.out.println(k + "pop: " + v ));
       /* for (Map.Entry<Integer, Integer> entry: frequency.entrySet()) {
            Integer m = entry.getKey();
            count.add(m);
        }
        return count;*/
        return frequency;
    }
    public ArrayList<Salary> bonusesCount() throws IOException, ClassNotFoundException {
        Salary salary = (Salary) messageFromClient.readObject();
        ArrayList<Salary> salaries = new ArrayList<>();
       if (salary.getSalary() <= 1000) {
           int poop = salary.getSalary();
           poop +=(int) (poop * 0.35);
           salary.setSalary(poop);
           salaries.add(salary);
       } else if (salary.getSalary() > 1000 && salary.getSalary() <= 1200) {
           int poop = salary.getSalary();
           poop += (int) (poop * 0.4);
           salary.setSalary(poop);
           salaries.add(salary);
       } else {
           int poop = salary.getSalary();
           poop += (int) (poop * 0.45);
           salary.setSalary(poop);
           salaries.add(salary);
       }
       return salaries;

    }
    public ArrayList<Salary> countSalary() throws IOException, ClassNotFoundException {
        ArrayList<Salary> sal = new ArrayList<>();
        Salary salary = (Salary) messageFromClient.readObject();
        if (salary.getOrders() <= 2) {//расчет в зависимости от количества заказов и вычет подоходного налога;-
            int pop = salary.getSalary();
            pop += (int) (pop * 0.5) - (int) (pop *0.13);
            salary.setSalary(pop);
            sal.add(salary);
        }else if (salary.getOrders() >2 && salary.getOrders() <= 7) {
            int pop = salary.getSalary();
            pop += (int) (pop * 0.75)- (int) (pop *0.13);
            salary.setSalary(pop);
            sal.add(salary);
        }else if (salary.getOrders() > 7 && salary.getOrders() <=16) {
            int pop = salary.getSalary();
            pop += (int) (pop * 0.75)- (int) (pop *0.13);
            salary.setSalary(pop);
            sal.add(salary);
        } else {
            int pop = salary.getSalary();
            pop = (int) (pop * 1.25)- (int) (pop *0.13);
            salary.setSalary(pop);
            sal.add(salary);
        }
        return sal;
    }
}