package tableClasses;

import java.io.Serializable;

public class CatalogProduct implements Serializable {
    private int CodeofProduct;
    private String brand;
    private String category;
    private String model;
    private String color;
    private int price;
    private String dateOfRelease;
    private String dataOfSale;
    private int techniquebrand_idTechniqueBrand;
    private int categorytechnique_idCategory;
    private int idTechniqueBrand;

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

    public int getIdTechniqueBrand() {
        return idTechniqueBrand;
    }

    public void setIdTechniqueBrand(int idTechniqueBrand) {
        this.idTechniqueBrand = idTechniqueBrand;
    }

    public CatalogProduct(){}
    public String getDataOfSale() {
        return dataOfSale;
    }

    public void setDataOfSale(String dataOfSale) {
        this.dataOfSale = dataOfSale;
    }

    public String getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(String dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public int getCodeofProduct() {
        return CodeofProduct;
    }

    public void setCodeofProduct(int codeofProduct) {
        CodeofProduct = codeofProduct;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



}
