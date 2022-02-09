package Database;

import Constants.Constants;
import sample.Classes.*;
import tableClasses.AccountEmployee;
import tableClasses.CatalogProduct;
import tableClasses.Orders;
import tableClasses.Salary;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    Connection dbConnection;

    public Database() throws SQLException {
        connectDB();
    }

    public void connectDB() throws SQLException {
        String connectionDB = "jdbc:mysql://" + Constants.HOST_DATABASE + ":"
                + Constants.PORT_DATABASE + "/" + Constants.NAME_DATABASE;
        dbConnection = DriverManager.getConnection(connectionDB, Constants.USER_DATABASE, Constants.PASSWORD_DATABASE);
    }


    public ArrayList<User> CheckUserInDatabase(User user) throws SQLException {
        ArrayList<User> res = new ArrayList<>();
        User user1 = new User();
        String select = "SELECT * FROM `shopcomputer`.`user` WHERE login= ? AND password= ?";
            PreparedStatement prSt = dbConnection.prepareStatement(select);
           prSt.setString(1, user.getLogin());
           prSt.setString(2,user.getPassword());
            ResultSet rs = prSt.executeQuery();
            while (rs.next()){
                user1.setRole(Integer.parseInt(rs.getString(Constants.USER_ROLE)));
                user1.setId(Integer.parseInt(rs.getString(Constants.USER_IDUSER)));
                res.add(user1);
            }
            return res;
    }

    public void ChangeUserData(User user, Client client) throws SQLException {
        Client client1 = new Client();
        int k = client.getIdClient();
        String request1 = "SELECT * FROM `shopcomputer`.`client` WHERE idClient=?";
        PreparedStatement st1 = dbConnection.prepareStatement(request1);
        st1.setInt(1, k);
        ResultSet res = st1.executeQuery();
        while (res.next()) {
            client1.setUser_idUser(Integer.parseInt(res.getString(Constants.CLIENT_IDUSER)));
        }
        String request = "UPDATE `shopcomputer`.`user` SET login=?,password=? WHERE idUser=?";
        PreparedStatement st = dbConnection.prepareStatement(request);
        st.setString(1, user.getLogin());
        st.setString(2, user.getPassword());
        st.setInt(3, client1.getUser_idUser());
        st.executeUpdate();
    }
    public int WriteUser(User user) throws SQLException {
        String insert = "INSERT INTO `shopcomputer`.`user`(login,password,role) " +
                "VALUES (?,?,?)";

        try
        {
            PreparedStatement prSt = dbConnection.prepareStatement(insert);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            prSt.setInt(3, user.getRole());
            prSt.executeUpdate();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        User user1 = new User();
        String select = "SELECT * FROM `shopcomputer`.`user` WHERE login= ? AND password= ?";
        PreparedStatement prS = dbConnection.prepareStatement(select);
        prS.setString(1, user.getLogin());
        prS.setString(2, user.getPassword());
        ResultSet rs = prS.executeQuery();
        while (rs.next()) {
            user1.setId(Integer.parseInt(rs.getString(Constants.USER_IDUSER)));
            System.out.println(user1.getId());
        }
        return user1.getId();
    }
    public ArrayList<User> SaveUserData(User user) throws SQLException {
        ArrayList<User> res = new ArrayList<>();
        String request = "UPDATE `shopcomputer`.`user` SET login=?,password=? WHERE online=?";
        PreparedStatement pr = dbConnection.prepareStatement(request);
        pr.setString(1, user.getLogin());
        pr.setString(2, user.getPassword());
        pr.setInt(3, user.getOnline());
        pr.executeUpdate();
        User user1 = new User();
        String str = "SELECT * FROM `shopcomputer`.`user` WHERE online=?";
        PreparedStatement prS = dbConnection.prepareStatement(str);
        prS.setInt(1,1);
        ResultSet result = prS.executeQuery();
        while (result.next()) {
            user1.setId(Integer.parseInt(result.getString(Constants.USER_IDUSER)));
            user1.setRole(Integer.parseInt(result.getString(Constants.USER_ROLE)));
            user1.setLogin(result.getString(Constants.USER_LOGIN));
            user1.setPassword(result.getString(Constants.USER_PASSWORD));
            res.add(user1);
        }

        return res;
    }

    public ArrayList<User> ReturnUser(User user) throws SQLException {
        ArrayList<User> result = new ArrayList<>();
        User user1 = new User();
        String select = "SELECT * FROM `shopcomputer`.`user` WHERE online=?";

        PreparedStatement prS = dbConnection.prepareStatement(select);
        prS.setInt(1, 1);
        ResultSet rs = prS.executeQuery();
        while (rs.next()) {
            user1.setLogin(rs.getString(Constants.USER_LOGIN));
            user1.setPassword(rs.getString(Constants.USER_PASSWORD));
            user1.setId(Integer.parseInt(rs.getString(Constants.USER_IDUSER)));
            user1.setRole(Integer.parseInt(rs.getString(Constants.USER_ROLE)));
            result.add(user1);
        }
        return result;
    }

    public void setOffline(User user) {
        //User user1 = new User();
        String request = "UPDATE `shopcomputer`.`user` SET online=? WHERE online=? ";
        try {
            PreparedStatement pr = dbConnection.prepareStatement(request);
            pr.setInt(1,user.getOnline());
            pr.setInt(2, 1);
            pr.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void setOnline(User user) {
        //User user1 = new User();
        String request = "UPDATE `shopcomputer`.`user` SET online=? WHERE login=? AND password=? ";
        try {
            PreparedStatement pr = dbConnection.prepareStatement(request);
            pr.setInt(1,user.getOnline());
            pr.setString(2, user.getLogin());
            pr.setString(3, user.getPassword());
            pr.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    ///////////////////////////////////////////////////////////////////////////////

    public ArrayList<CatalogProduct> showProducts() throws SQLException {
        ArrayList<CatalogProduct> products = new ArrayList<>();
        String request = "SELECT * FROM shopcomputer.technique join shopcomputer.categorytechnique " +
                "on shopcomputer.technique.categorytechnique_idCategory=" +
                "shopcomputer.categorytechnique.idCategory " +
                "join shopcomputer.techniquebrand on " +
                "shopcomputer.technique.techniquebrand_idTechniqueBrand=" +
                "shopcomputer.techniquebrand.idTechniqueBrand";
        PreparedStatement st = dbConnection.prepareStatement(request);
        ResultSet result = st.executeQuery();
        while (result.next()) {
            CatalogProduct products1 = new CatalogProduct();
            products1.setCodeofProduct(result.getInt("CodeofProduct"));
            products1.setBrand(result.getString("brand"));
            products1.setColor(result.getString("color"));
            products1.setPrice(result.getInt("price"));
            products1.setCategory(result.getString("category"));
            products1.setModel(result.getString("model"));
            products1.setDateOfRelease(result.getString("dateOfRelease"));
            products.add(products1);
        }
        return products;
    }
    public ArrayList<CatalogProduct> addTechnique(CatalogProduct product) throws SQLException {
        ArrayList<CatalogProduct> products = new ArrayList<>();
        CatalogProduct product1 = new CatalogProduct();
        String request = "SELECT * FROM shopcomputer.techniquebrand WHERE brand=?";
        PreparedStatement st = dbConnection.prepareStatement(request);
        st.setString(1, product.getBrand());
        ResultSet result = st.executeQuery();
        while (result.next()) {

            product1.setIdTechniqueBrand(result.getInt("idTechniqueBrand"));
        }
        String request1 = "SELECT * FROM shopcomputer.categorytechnique WHERE category=?";
        PreparedStatement st1 = dbConnection.prepareStatement(request1);
        st1.setString(1, product.getCategory());
        ResultSet result1 = st1.executeQuery();
        while (result1.next()) {
            product1.setCategorytechnique_idCategory(result1.getInt("categorytechnique_idCategory"));
        }
        String request2 = "INSERT INTO shopcomputer.technique(model,color,dateOfRelease,price,techniquebrand_idTechniqueBrand,categorytechnique_idCategory) VALUES(?,?,?,?,?,?)";
        PreparedStatement st2 = dbConnection.prepareStatement(request2);
        st2.setString(1, product.getModel());
        st2.setString(2, product.getColor());
        st2.setString(3, product.getDateOfRelease());
        st2.setInt(4, product.getPrice());
        st2.setInt(5, product1.getTechniquebrand_idTechniqueBrand());
        st2.setInt(6, product1.getCategorytechnique_idCategory());
        ResultSet result2 = st2.executeQuery();
        while (result2.next()) {
            product1.setCodeofProduct(result2.getInt("CodeofProduct"));
            products.add(product1);
        }
        return products;
    }

    ///////////////////////////////////////////////////////////////////////////////
    public ArrayList<TechniqueBrand> showBrands() throws SQLException{
        ArrayList<TechniqueBrand> brands = new ArrayList<>();
        String request = "SELECT * FROM shopcomputer.techniquebrand";
        PreparedStatement st = dbConnection.prepareStatement(request);
        ResultSet result = st.executeQuery();
        while (result.next()) {
            TechniqueBrand brand = new TechniqueBrand();
            brand.setIdTechniqueBrand(result.getInt("idTechniqueBrand"));
            brand.setBrand(result.getString("brand"));
            brands.add(brand);
        }
        return brands;
    }

    public ArrayList<TechniqueBrand> addBrand(TechniqueBrand brand) throws SQLException {
        ArrayList<TechniqueBrand> brands = new ArrayList<>();
        String request = "INSERT INTO shopcomputer.techniquebrand (brand) VALUE (?)";
        PreparedStatement st = dbConnection.prepareStatement(request);
        st.setString(1, brand.getBrand());
        st.execute();
        String request1 = "SELECT * FROM shopcomputer.techniquebrand";
        PreparedStatement st1 = dbConnection.prepareStatement(request1);
        ResultSet result = st1.executeQuery();
        while (result.next()) {
            TechniqueBrand brand1 = new TechniqueBrand();
            brand1.setBrand(result.getString("brand"));
            brand1.setIdTechniqueBrand(result.getInt("idTechniqueBrand"));
            brands.add(brand1);
        }
        return brands;
    }
    public void deleteBrand(TechniqueBrand brand) throws SQLException {
        String request = "DELETE FROM shopcomputer.techniquebrand WHERE idTechniqueBrand=?";
        PreparedStatement st = dbConnection.prepareStatement(request);
        st.setInt(1, brand.getIdTechniqueBrand());
        st.executeUpdate();
    }
    public void updateBrand(TechniqueBrand brand) throws SQLException {
        String request = "UPDATE shopcomputer.techniquebrand SET brand=? WHERE idTechniqueBrand=?";
        PreparedStatement st = dbConnection.prepareStatement(request);
        st.setString(1, brand.getBrand());
        st.setInt(2, brand.getIdTechniqueBrand());
        st.executeUpdate();
    }
    ///////////////////////////////////////////////////////////////////////////////
    public ArrayList<TechniqueCategory> showCategories() throws SQLException{
        ArrayList<TechniqueCategory> categories = new ArrayList<>();
        String request = "SELECT * FROM shopcomputer.categorytechnique";
        PreparedStatement st = dbConnection.prepareStatement(request);
        ResultSet result = st.executeQuery();
        while (result.next()) {
            TechniqueCategory category = new TechniqueCategory();
            category.setIdCategory(result.getInt("idCategory"));
            category.setCategory(result.getString("category"));
            categories.add(category);
        }
        return categories;
    }

    public ArrayList<TechniqueCategory> addCategory(TechniqueCategory category) throws SQLException {
        ArrayList<TechniqueCategory> categories = new ArrayList<>();
        String request = "INSERT INTO shopcomputer.categorytechnique (category) VALUE (?)";
        PreparedStatement st = dbConnection.prepareStatement(request);
        st.setString(1, category.getCategory());
        st.execute();
        String request1 = "SELECT * FROM shopcomputer.categorytechnique";
        PreparedStatement st1 = dbConnection.prepareStatement(request1);
        ResultSet result = st1.executeQuery();
        while (result.next()) {
            TechniqueCategory category1 = new TechniqueCategory();
            category1.setCategory(result.getString("category"));
            category1.setIdCategory(result.getInt("idCategory"));
            categories.add(category1);
        }
        return categories;
    }
    public void deleteCategory(TechniqueCategory category) throws SQLException {
        String request = "DELETE FROM shopcomputer.categorytechnique WHERE idCategory=?";
        PreparedStatement st = dbConnection.prepareStatement(request);
        st.setInt(1, category.getIdCategory());
        st.executeUpdate();
    }
    public void updateCategory(TechniqueCategory category) throws SQLException {
        String request = "UPDATE shopcomputer.categorytechnique SET category=? WHERE idCategory=?";
        PreparedStatement st = dbConnection.prepareStatement(request);
        st.setString(1, category.getCategory());
        st.setInt(2, category.getIdCategory());
        st.executeUpdate();
    }
    /////////////////////////////////////////////////////////////////////////////
    public ArrayList<Client> showAllClients() throws SQLException {
        ArrayList<Client> result = new ArrayList<>();
        String request = "SELECT * FROM `shopcomputer`.`client`";
        PreparedStatement pr = dbConnection.prepareStatement(request);
        ResultSet res = pr.executeQuery();
        while (res.next()) {
            Client client  = new Client();
            client.setIdClient(res.getInt(Constants.CLIENT_IDCLIENT));
            client.setSurnameClient(res.getString(Constants.CLIENT_SURNAME));
            client.setNameClient(res.getString(Constants.CLIENT_NAME));
            client.setEmail(res.getString(Constants.ClIENT_EMAIL));
            client.setUser_idUser(res.getInt("user_idUser"));
            client.setEmail(res.getString("email"));
            result.add(client);
        }
        return result;

    }

    public void DeleteClient(Client client) throws SQLException{
        Client client1 = new Client();
        int k = client.getIdClient();
        String request1 = "SELECT * FROM `shopcomputer`.`client` WHERE idClient=?";
        PreparedStatement st1 = dbConnection.prepareStatement(request1);
        st1.setInt(1, k);
        ResultSet res = st1.executeQuery();
        while (res.next()) {
            client1.setUser_idUser(Integer.parseInt(res.getString(Constants.CLIENT_IDUSER)));
        }
        String request = "DELETE FROM `shopcomputer`.`client` WHERE user_idUser=?";
        PreparedStatement st = dbConnection.prepareStatement(request);
        st.setInt(1, client1.getUser_idUser());
        st.executeUpdate();
        String request2 = "DELETE FROM `shopcomputer`.`user` WHERE idUser=?";
        PreparedStatement st2 = dbConnection.prepareStatement(request2);
        st2.setInt(1, client1.getUser_idUser());
        st2.executeUpdate();

    }
    public void SaveClientData(Client client) throws SQLException {
        String request = "UPDATE `shopcomputer`.`client` SET email=?,surnameClient=?,nameClient=?,lastnameClient=?,address=?,phone=? WHERE user_idUser=?";
        PreparedStatement pr = dbConnection.prepareStatement(request);
        pr.setString(1, client.getEmail());
        pr.setString(2, client.getSurnameClient());
        pr.setString(3, client.getNameClient());
        pr.setString(4,client.getLastnameClient());
        pr.setString(5, client.getAddress());
        pr.setString(6, client.getPhone());
        pr.setInt(7, client.getUser_idUser());
        pr.executeUpdate();

    }

    public void SaveClientChange(Client client) throws SQLException {
        String request = "UPDATE `shopcomputer`.`client` SET surnameClient=?,nameClient=?,email=? WHERE idClient=?";
        PreparedStatement pr = dbConnection.prepareStatement(request);
        pr.setString(1, client.getSurnameClient());
        pr.setString(2, client.getNameClient());
        pr.setString(3, client.getEmail());
        pr.setInt(4, client.getIdClient());
        pr.executeUpdate();

    }
    public ArrayList<Client> ReturnClient(Client client) throws  SQLException{
        ArrayList<Client> result = new ArrayList<>();
        Client client1 = new Client();
        String request = "SELECT * FROM `shopcomputer`.`client` WHERE user_idUser=?";
        PreparedStatement pr = dbConnection.prepareStatement(request);
        pr.setInt(1, client.getUser_idUser());
        ResultSet res = pr.executeQuery();
        while (res.next()) {
            client1.setEmail(res.getString("email"));
            client1.setSurnameClient(res.getString("surnameClient"));
            client1.setNameClient(res.getString("nameClient"));
            client1.setLastnameClient(res.getString("lastnameClient"));
            client1.setAddress(res.getString("address"));
            client1.setPhone(res.getString("phone"));
            result.add(client1);
        }
        return result;
    }
    public void WriteClient(Client client)
    {
        String insert = "INSERT INTO `shopcomputer`.`client`(email,surnameClient,nameClient," +
                "lastnameClient,address,phone,user_idUser) VALUES (?,?,?,?,?,?,?)";

        try
        {
            PreparedStatement prSt = dbConnection.prepareStatement(insert);
            prSt.setString(1, client.getEmail());
            prSt.setString(2, client.getSurnameClient());
            prSt.setString(3, client.getNameClient());
            prSt.setString(4, client.getLastnameClient());
            prSt.setString(5, client.getAddress());
            prSt.setString(6, client.getPhone());
            prSt.setInt(7, client.getUser_idUser());
            prSt.executeUpdate();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }
    public void blockedClient (Client client) throws SQLException {
        String request = "UPDATE shopcomputer.user SET role=? WHERE idUser=?";
        PreparedStatement st = dbConnection.prepareStatement(request);
        st.setInt(1, -3);
        st.setInt(2, client.getUser_idUser());
        st.executeUpdate();
    }
    //////////////////////////////////////////////////////////////////////////////
    public ArrayList<Orders> showAllOrders() throws SQLException {
        ArrayList<Orders> orders = new ArrayList<>();

        String request = "SELECT * FROM shopcomputer.order JOIN shopcomputer.client " +
                "ON shopcomputer.order.client_idClient=shopcomputer.client.idClient " +
                "JOIN shopcomputer.technique " +
                "ON shopcomputer.order.technique_CodeofProduct=shopcomputer.technique.CodeofProduct " +
                "JOIN shopcomputer.techniquebrand " +
                "ON shopcomputer.technique.techniquebrand_idTechniqueBrand=shopcomputer.techniquebrand.idTechniqueBrand " +
                "JOIN shopcomputer.categorytechnique " +
                "ON shopcomputer.technique.categorytechnique_idCategory=shopcomputer.categorytechnique.idCategory";
        PreparedStatement st = dbConnection.prepareStatement(request);
        ResultSet result = st.executeQuery();
        while (result.next()) {
            Orders orders1 = new Orders();
            orders1.setNameClient(result.getString("nameClient"));
            orders1.setSurnameClient(result.getString("surnameClient"));
            orders1.setCategory(result.getString("category"));
            orders1.setIdOrder(result.getInt("idOrder"));
            orders1.setColor(result.getString("color"));
            orders1.setBrand(result.getString("brand"));
            orders1.setPrice(result.getInt("price"));
            orders1.setModel(result.getString("model"));
            orders1.setDateOfRelease(result.getString("dateOfRelease"));
            orders1.setIdClient(result.getInt("idClient"));
            orders1.setDateOfSale(result.getString("dataOfSale"));
            orders.add(orders1);
        }
        return orders;
    }

    public ArrayList<Integer> numbersOrders() throws SQLException {
        ArrayList<Integer> numbers = new ArrayList<>();

        String request = "SELECT * FROM shopcomputer.order JOIN shopcomputer.technique ON technique_CodeofProduct=CodeofProduct";
        PreparedStatement st = dbConnection.prepareStatement(request);
        ResultSet resultSet = st.executeQuery();
        while (resultSet.next()) {
            Orders orders = new Orders();
            orders.setIdCategory(resultSet.getInt("categorytechnique_idCategory"));
            System.out.println(orders.getIdCategory());
            numbers.add(orders.getIdCategory());
            System.out.println(numbers);
        }
        return numbers;
    }

    public void deleteOrder(Orders orders) throws SQLException {
        String request = "DELETE FROM shopcomputer.order WHERE idOrder=?";
        PreparedStatement st = dbConnection.prepareStatement(request);
        st.setInt(1, orders.getIdOrder());
        st.executeUpdate();
    }
    public void acceptOrder(Orders orders) throws SQLException {
        ArrayList<Orders> orders1 = new ArrayList<>();
        User user = new User();
        String request1 = "SELECT * FROM shopcomputer.user WHERE online=?";
        PreparedStatement st = dbConnection.prepareStatement(request1);
        st.setInt(1, 1);
        ResultSet result = st.executeQuery();
        while (result.next()) {
            user.setId(result.getInt("idUser"));
        }
        Employee employee = new Employee();
        String request2 = "SELECT * FROM shopcomputer.employee WHERE user_idUser=?";
        PreparedStatement st1 = dbConnection.prepareStatement(request2);
        st1.setInt(1, user.getId());
        ResultSet result1 = st1.executeQuery();
        while (result1.next()) {
            employee.setIdEmployee(result1.getInt("idEmployee"));
        }
        String request = "UPDATE shopcomputer.order SET employee_idEmployee=? WHERE idOrder=?";
        PreparedStatement st2 = dbConnection.prepareStatement(request);
        st2.setInt(1, employee.getIdEmployee());
        st2.setInt(2, orders.getIdOrder());
        st2.executeUpdate();
    }
    public ArrayList<Orders> showNotOrderAccept() throws SQLException {
        ArrayList<Orders> orders = new ArrayList<>();
        String request = "SELECT * FROM shopcomputer.order JOIN shopcomputer.client " +
                "ON shopcomputer.order.client_idClient=shopcomputer.client.idClient " +
                "JOIN shopcomputer.technique " +
                "ON shopcomputer.order.technique_CodeofProduct=shopcomputer.technique.CodeofProduct " +
                "JOIN shopcomputer.techniquebrand " +
                "ON shopcomputer.technique.techniquebrand_idTechniqueBrand=shopcomputer.techniquebrand.idTechniqueBrand " +
                "JOIN shopcomputer.categorytechnique " +
                "ON shopcomputer.technique.categorytechnique_idCategory=shopcomputer.categorytechnique.idCategory WHERE employee_idEmployee=-3";
        PreparedStatement st = dbConnection.prepareStatement(request);
        //st.setInt(1, -3);
        ResultSet result = st.executeQuery();
        while (result.next()) {
            Orders orders5 = new Orders();
            orders5.setNameClient(result.getString("nameClient"));
            orders5.setSurnameClient(result.getString("surnameClient"));
            orders5.setCategory(result.getString("category"));
            orders5.setIdOrder(result.getInt("idOrder"));
            orders5.setColor(result.getString("color"));
            orders5.setBrand(result.getString("brand"));
            orders5.setPrice(result.getInt("price"));
            orders5.setModel(result.getString("model"));
            orders5.setDateOfRelease(result.getString("dateOfRelease"));
            orders5.setIdClient(result.getInt("idClient"));
            orders5.setDateOfSale(result.getString("dataOfSale"));
            orders.add(orders5);
        }
        return orders;
    }
    public int addOrder1() throws SQLException {
        User user1 = new User();
        String str = "SELECT * FROM shopcomputer.user WHERE shopcomputer.user.online=?";
        PreparedStatement ps = dbConnection.prepareStatement(str);
        ps.setInt(1, 1);
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            user1.setLogin(result.getString(Constants.USER_LOGIN));
            user1.setPassword(result.getString(Constants.USER_PASSWORD));
            user1.setId(result.getInt(Constants.USER_IDUSER));
            // res.add(user1);

        }
        System.out.println(user1.getId());
        return user1.getId();
    }
    public int addOrder2() throws SQLException {
        int p = addOrder1();
        Client client = new Client();
        String request1 = "SELECT * FROM shopcomputer.client WHERE shopcomputer.client.user_idUser=?";
        PreparedStatement st1 = dbConnection.prepareStatement(request1);
        st1.setInt(1, p);
        ResultSet result1 = st1.executeQuery();
        while (result1.next()) {
            client.setIdClient(result1.getInt("idClient"));
        }
        System.out.println(client.getIdClient());
        return client.getIdClient();

    }
    public void addOrder(CatalogProduct catalogProduct) throws SQLException {
        int d = addOrder2();
        String request3 = "INSERT INTO shopcomputer.order (dataOfSale,client_idClient,employee_idEmployee,technique_CodeofProduct) VALUES(?,?,?,?)";
        PreparedStatement st3 = dbConnection.prepareStatement(request3);
        st3.setString(1, catalogProduct.getDataOfSale());
        st3.setInt(2, d);
        st3.setInt(3, -3);
        st3.setInt(4, catalogProduct.getCodeofProduct());
        st3.executeUpdate();
    }
    public ArrayList<Orders> showClientOrder() throws SQLException {
        User user = new User();
        ArrayList<Orders> orders = new ArrayList<>();
        String request = "SELECT * FROM shopcomputer.user WHERE online=1";
        PreparedStatement st = dbConnection.prepareStatement(request);
        ResultSet result = st.executeQuery();
        while (result.next()) {

            user.setId(result.getInt("idUser"));
        }
        Client client = new Client();
        String request2 = "SELECT * FROM shopcomputer.client WHERE user_idUser=?";
        PreparedStatement st2 = dbConnection.prepareStatement(request2);
        st2.setInt(1, user.getId());
        ResultSet result2 = st2.executeQuery();
        while (result2.next()) {
            client.setIdClient(result2.getInt("idClient"));
        }

        String request3 = "SELECT * FROM shopcomputer.order JOIN shopcomputer.client " +
                "ON shopcomputer.order.client_idClient=shopcomputer.client.idClient " +
                "JOIN shopcomputer.technique " +
                "ON shopcomputer.order.technique_CodeofProduct=shopcomputer.technique.CodeofProduct " +
                "JOIN shopcomputer.techniquebrand " +
                "ON shopcomputer.technique.techniquebrand_idTechniqueBrand=shopcomputer.techniquebrand.idTechniqueBrand " +
                "JOIN shopcomputer.categorytechnique " +
                "ON shopcomputer.technique.categorytechnique_idCategory=shopcomputer.categorytechnique.idCategory WHERE client_idClient=?";
        PreparedStatement st3 = dbConnection.prepareStatement(request3);
        st3.setInt(1, client.getIdClient());
        ResultSet result3 = st3.executeQuery();
        while (result3.next()) {
            Orders orders1 = new Orders();
            orders1.setIdOrder(result3.getInt("idOrder"));
            orders1.setDateOfSale(result3.getString("dataOfSale"));
            orders1.setCategory(result3.getString("category"));
            orders1.setBrand(result3.getString("brand"));
            orders1.setModel(result3.getString("model"));
            orders1.setPrice(result3.getInt("price"));
            orders.add(orders1);
        }
        return orders;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<Integer> showEmplOrder() throws SQLException {
        ArrayList<Integer> numbers = new ArrayList<>();
        Order order = new Order();
        String request = "SELECT * FROM shopcomputer.order JOIN shopcomputer.employee ON employee_idEmployee=idEmployee";
        PreparedStatement st = dbConnection.prepareStatement(request);
        ResultSet result = st.executeQuery();
        while (result.next()) {
            order.setEmployee_idEmployee(result.getInt("idEmployee"));
            numbers.add(order.getEmployee_idEmployee());
        }
        return numbers;
    }
    public ArrayList<Salary> informEmpl(ArrayList<Integer> key, ArrayList<Integer> value) throws SQLException {
        ArrayList<Salary> employees = new ArrayList<>();

        for (int i = 0; i < key.size(); i++) {
            String request = "SELECT * FROM shopcomputer.employee WHERE idEmployee=?";
            PreparedStatement st = dbConnection.prepareStatement(request);
            System.out.println(key.get(i));
            st.setInt(1, key.get(i));
            System.out.println("iii: " + key.get(i));
            ResultSet result  = st.executeQuery();
            while (result.next()) {
                Salary salary = new Salary();
                salary.setIdEmployee(result.getInt("idEmployee"));
                salary.setNameEmployee(result.getString("nameEmployee"));
                salary.setSurnameEmployee(result.getString("surnameEmployee"));
                salary.setSalary(result.getInt("salary"));
                salary.setOrders(value.get(i));
                employees.add(salary);
                System.out.println("empl" + employees);
            }
            //st.clearParameters();

        }
        return employees;
    }

    public ArrayList<Employee> returnemployee(Employee employee) throws SQLException {
        ArrayList<Employee> result = new ArrayList<>();
        String request = "SELECT * FROM `shopcomputer`.`employee` WHERE idUser=?";
        PreparedStatement st = dbConnection.prepareStatement(request);
        st.setInt(1, employee.getIdUser());
        ResultSet res = st.executeQuery();
        while (res.next()) {
            Employee employee1 = new Employee();
            employee1.setSurnameEmployee(Constants.EMPLOYEE_SURNAME);
            employee1.setNameEmployee(Constants.EMPLOYEE_NAME);
            employee1.setLastnameEmployee(Constants.EMPLOYEE_LASTNAME);
            employee1.setPosition(Constants.EMPLOYEE_POSITION);
            employee1.setSalary(Integer.parseInt(Constants.EMPLOYEE_SALARY));
            employee1.setPhone(Constants.EMPLOYEE_PHONE);
            result.add(employee);
        }
        return result;
    }
    public void WriteEmployee(Employee employee) {
        String request = "INSERT INTO `shopcomputer`.`employee`(position,salary,surnameEmployee,nameEmployee,lastnameEmployee,phone,user_idUser) " +
                "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = dbConnection.prepareStatement(request);
            pr.setString(1, employee.getPosition());
            pr.setInt(2, employee.getSalary());
            pr.setString(3, employee.getSurnameEmployee());
            pr.setString(4, employee.getNameEmployee());
            pr.setString(5, employee.getLastnameEmployee());
            pr.setString(6, employee.getPhone());
            pr.setInt(7, employee.getIdUser());
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public ArrayList<AccountEmployee> accountEmployee() throws SQLException {
        ArrayList<AccountEmployee> acc = new ArrayList<>();
        User user = new User();
        AccountEmployee accountEmployee = new AccountEmployee();
        String request = "SELECT * FROM `shopcomputer`.`user` JOIN `shopcomputer`.`employee` ON `shopcomputer`.`user`.`idUser`=`shopcomputer`.`employee`.`user_idUser` WHERE `shopcomputer`.`user`.`online`=1";
        PreparedStatement st = dbConnection.prepareStatement(request);
       // st.setInt(1, 1);
        ResultSet resultSet = st.executeQuery();
        while (resultSet.next()) {
            accountEmployee.setIdEmployee(Integer.parseInt(resultSet.getString("idEmployee")));
            accountEmployee.setSurnameEmployee(resultSet.getString("surnameEmployee"));
            accountEmployee.setNameEmployee(resultSet.getString("nameEmployee"));
            accountEmployee.setPosition(resultSet.getString("position"));
            accountEmployee.setSalary(resultSet.getInt("salary"));
            accountEmployee.setLastnameEmployee(resultSet.getString("lastnameEmployee"));
            accountEmployee.setPhone(resultSet.getString("phone"));
            accountEmployee.setIdUser(resultSet.getInt("idUser"));
            accountEmployee.setLogin(resultSet.getString("login"));
            accountEmployee.setPassword(resultSet.getString("password"));
            accountEmployee.setRole(resultSet.getInt("role"));
            accountEmployee.setOnline(resultSet.getInt("online"));
            acc.add(accountEmployee);
        }
        return acc;
    }

    public void SaveEmployeeAccount(Employee employee) throws SQLException {
        String request = "UPDATE `shopcomputer`.`employee` SET surnameEmployee=?,nameEmployee=?,lastnameEmployee=?,phone=? WHERE user_idUser=?";
        PreparedStatement pr = dbConnection.prepareStatement(request);
        pr.setString(1, employee.getSurnameEmployee());
        pr.setString(2, employee.getNameEmployee());
        pr.setString(3, employee.getLastnameEmployee());
        pr.setString(4, employee.getPhone());
        pr.setInt(5, employee.getIdUser());
        pr.executeUpdate();
    }
    public void SaveEmployeeData(Employee employee) throws SQLException {
        String request = "UPDATE `shopcomputer`.`employee` SET position=?,salary=?,surnameEmployee=?,nameEmployee=?,lastnameEmployee=?,phone=? WHERE user_idUser=?";
        PreparedStatement pr = dbConnection.prepareStatement(request);
        pr.setString(1, employee.getPosition());
        pr.setInt(2, employee.getSalary());
        pr.setString(3, employee.getSurnameEmployee());
        pr.setString(4, employee.getNameEmployee());
        pr.setString(5, employee.getLastnameEmployee());
        pr.setString(6, employee.getPhone());
        pr.setInt(7, employee.getIdUser());
        pr.executeUpdate();

    }

    public void setOfflineEmployee(User user) {
        String request = "UPDATE `shopcomputer`.`user` SET online=? WHERE login=? AND password=? ";
        try {
            PreparedStatement pr = dbConnection.prepareStatement(request);
            pr.setInt(1,user.getOnline());
            pr.setString(2, user.getLogin());
            pr.setString(3, user.getPassword());
            pr.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<User> setUser() throws SQLException {
        ArrayList<User> res = new ArrayList<>();
        User user1 = new User();
        String str = "SELECT * FROM `shopcomputer`.`user` WHERE online=?";
            PreparedStatement ps = dbConnection.prepareStatement(str);
            ps.setInt(1, 1);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                user1.setLogin(result.getString(Constants.USER_LOGIN));
                user1.setPassword(result.getString(Constants.USER_PASSWORD));
                user1.setId(Integer.parseInt(Constants.USER_IDUSER));
                res.add(user1);

            }
            ps.close();
        return res;
    }
}