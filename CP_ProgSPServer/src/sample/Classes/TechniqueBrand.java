package sample.Classes;

import java.io.Serializable;

public class TechniqueBrand  implements Serializable {
    private int idTechniqueBrand;
    private String brand;

    public int getIdTechniqueBrand() {
        return idTechniqueBrand;
    }

    public void setIdTechniqueBrand(int idTechniqueBrand) {
        this.idTechniqueBrand = idTechniqueBrand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
