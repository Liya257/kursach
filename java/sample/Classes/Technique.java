package sample.Classes;

import java.io.Serializable;
import java.time.LocalDate;

public class Technique implements Serializable {

    private int codeOfProduct;
    private String model;
    private String color;
    private LocalDate dateOfRealese;
    private int price;
    private int techniquebrand_idTechniqueBrand;
    private int categorytechnique_idCategory;

    public Technique() {}

    public int getCodeOfProduct() {
        return codeOfProduct;
    }

    public void setCodeOfProduct(int codeOfProduct) {
        this.codeOfProduct = codeOfProduct;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDate getDateOfRealese() {
        return dateOfRealese;
    }

    public void setDateOfRealese(LocalDate dateOfRealese) {
        this.dateOfRealese = dateOfRealese;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTechniquebrand_idTechniqueBrand() {
        return techniquebrand_idTechniqueBrand;
    }

    public void setTechniquebrand_idTechniqueBrand(int techniquebrand_idTechniqueBrand) {
        this.techniquebrand_idTechniqueBrand = techniquebrand_idTechniqueBrand;
    }

    public int getCategorytechnique_idCategory() {
        return categorytechnique_idCategory;
    }

    public void setCategorytechnique_idCategory(int categorytechnique_idCategory) {
        this.categorytechnique_idCategory = categorytechnique_idCategory;
    }
}
