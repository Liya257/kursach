package sample.Classes;

import java.io.Serializable;

public class TechniqueCategory implements Serializable {
    private int idCategory;
    private String category;

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}
