package Constants;

public class Constants {
    public static final int PORT = 1024;
    public static final String HOST = "127.0.0.1";

    public static final String HOST_DATABASE = "localhost";
    public static final String NAME_DATABASE = "shopcomputer";
    public static final String USER_DATABASE = "root";
    public static final String PORT_DATABASE = "3306";
    public static final String PASSWORD_DATABASE = "LIya_2575)";


    public static final String USER_TABLE = "user";
    public static final String USER_IDUSER = "idUser";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE = "role";

    public static final String CLIENT_TABLE = "client";
    public static final String CLIENT_IDCLIENT = "idClient";
    public static final String ClIENT_EMAIL = "email";
    public static final String CLIENT_SURNAME = "surnameClient";
    public static final String CLIENT_NAME = "nameClient";
    public static final String CLIENT_LASTNAME = "lastnameClient";
    public static final String CLIENT_ADDRESS = "address";
    public static final String CLIENT_PHONE = "phone";
    public static final String CLIENT_IDUSER = "user_idUser";

    public static final String EMPLOYEE_TABLE = "employee";
    public static final String EMPLOYEE_ID = "idEmployee";
    public static final String EMPLOYEE_POSITION = "position";
    public static final String EMPLOYEE_SALARY = "salary";
    public static final String EMPLOYEE_NAME = "nameEmployee";
    public static final String EMPLOYEE_SURNAME = "surnameEmployee";
    public static final String EMPLOYEE_LASTNAME = "lastnameEmployee";
    public static final String EMPLOYEE_ADDRESS = "address";
    public static final String EMPLOYEE_PHONE = "phone";
    public static final String EMPLOYEE_IDUSER = "user_idUser";

    public static final String ORDER_TABLE = "order";
    public static final String ORDER_ID = "idOrder";
    public static final String ORDER_DATA = "dataOfSale";
    public static final String ORDER_IDCLIENT  =  "client_idClient";

    public static final String TECHNIQUEBRAND_TABLE = "techniquebrand";
    public static final String TECHNIQUEBRAND_ID = "idTechniqueBrand";
    public static final String TECHNIQUEBRAND_BRAND = "brand";
    public static final String TECHNIQUEBRAND_IDORDER = "order_idOrder";
    public static final String TECHNIQUEBRAND_IDCLIENT = "order_client_idClient";

    public static final String CATEGORY_TABLE = "category";
    public static final String CATEGORY_ID = "idCategory";
    public static final String CATEGORY_NAME = "category";
    public static final String CATEGORY_MODEL = "model";
    public static final String CATEGORY_IDTECHBRAND = "techniquebrand_idTechniqueBrand";
    public static final String CATEGORY_IDORDEER= "techniquebrand_order_idOrder";
    public static final String CATEGORY_IDCLIENT = "techniquebrand_order_client_idClient";

    public static final String TECHNIQUE_TABLE = "technique";
    public static final String TECHNIQUE_CODE = "CodeOfProducts";
    public static final String TECHNIQUE_COLOR = "color";
    public static final String TECHNIQUE_PRICE = "price";
    public static final String TECHNIQUE_IDCAT = "categorytechnique_idCategory";
    public static final String TECHNIQUE_IDTECHBRAND = "categorytechnique_techniquebrand_idTechniqueBrand";
    public static final String TECHNIQUE_IDORDER = "categorytechnique_techniquebrand_order_idOrder";
    public static final String TECHNIQUE_IDCLIENT = "categorytechnique_techniquebrand_order_client_idClient";

 }
