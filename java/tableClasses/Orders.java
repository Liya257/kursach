package tableClasses;

import java.io.Serializable;
import java.time.LocalDate;

public class Orders implements Serializable {
    private int idOrder;
    private String brand;
    private int idTechniqueBrand;
    private int idCategory;
    private String category;
    private int CodeofProduct;
    private String nameClient;
    private String surnameClient;
    private String model;
    private String color;
    private String dateOfRelease;
    private int price;
    private int idClient;
    private String dateOfSale;
    private int countOrders;



    public Orders() {}
    public int getCountOrders() {
        return countOrders;
    }

    public void setCountOrders(int countOrders) {
        this.countOrders = countOrders;
    }
    public String getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(String dateOfSale) {
        this.dateOfSale = dateOfSale;
    }
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getSurnameClient() {
        return surnameClient;
    }

    public void setSurnameClient(String surnameClient) {
        this.surnameClient = surnameClient;
    }



    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setIdTechniqueBrand(int idTechniqueBrand) {
        this.idTechniqueBrand = idTechniqueBrand;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCodeofProduct(int codeofProduct) {
        CodeofProduct = codeofProduct;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDateOfRelease(String dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIdTechniqueBrand() {
        return idTechniqueBrand;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public String getCategory() {
        return category;
    }

    public int getCodeofProduct() {
        return CodeofProduct;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getDateOfRelease() {
        return dateOfRelease;
    }

    public int getPrice() {
        return price;
    }
}
