package sample.Classes;

import java.io.Serializable;
import java.time.LocalDate;

public class Order implements Serializable {
    private int idOrder;
    private LocalDate dataOfSale;
    private int client_idClient;
    private int employee_idEmployee;
    private int technique_CodeofProduct;


    public Order(){}

    public int getIdOrder() {return idOrder;}
    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public void setDataOfSale(LocalDate dataOfSale) {
        this.dataOfSale = dataOfSale;
    }

    public void setClient_idClient(int client_idClient) {
        this.client_idClient = client_idClient;
    }

    public void setEmployee_idEmployee(int employee_idEmployee) {
        this.employee_idEmployee = employee_idEmployee;
    }

    public void setTechnique_CodeofProduct(int technique_CodeofProduct) {
        this.technique_CodeofProduct = technique_CodeofProduct;
    }

    public int getIdClient() {return client_idClient;}
    public void setIdClient(int client_idClient) {this.client_idClient = client_idClient;}

    public LocalDate getDataOfSale() {
        return dataOfSale;
    }

    public int getClient_idClient() {
        return client_idClient;
    }

    public int getEmployee_idEmployee() {
        return employee_idEmployee;
    }

    public int getTechnique_CodeofProduct() {
        return technique_CodeofProduct;
    }
}
