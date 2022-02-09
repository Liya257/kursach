package DataHandler;


import Database.Database;
import Interfaces.IObjectService;
import sample.Classes.Technique;
import sample.Classes.TechniqueBrand;
import sample.Classes.TechniqueCategory;
import tableClasses.CatalogProduct;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class TechniqueDataHandler implements IObjectService {

    private int idClient;
    private Socket socket;
    private ObjectInputStream messageFromClient;
    private ObjectOutputStream writeMessage;
    private Database database;

    public TechniqueDataHandler(int countUser, Socket socket, Database database, ObjectInputStream messageFromClient, ObjectOutputStream writeMessage) {
        this.idClient = countUser;
        this.socket = socket;
        this.database = database;
        this.messageFromClient = messageFromClient;
        // this.writeMessage = writeMessage;
    }

    @Override
    public ArrayList<?> showAll() {
        try {
            ArrayList<CatalogProduct> products = database.showProducts();
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<TechniqueCategory> showCategories() {
        try {
            ArrayList<TechniqueCategory> categories = database.showCategories();
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<TechniqueBrand> showBrand() {
        try {
            ArrayList<TechniqueBrand> brands = database.showBrands();
            return brands;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete() {

    }
    public void deleteCategory() {
        try {
            TechniqueCategory category;
            category = (TechniqueCategory) messageFromClient.readObject();
            database.deleteCategory(category);
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void deleteBrand() {
        try {
            TechniqueBrand brand;
            brand = (TechniqueBrand) messageFromClient.readObject();
            database.deleteBrand(brand);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addInDatabase() {
        return 0;
    }
    public ArrayList<CatalogProduct> addTechnique() {
        try {
            CatalogProduct product =(CatalogProduct) messageFromClient.readObject();
            ArrayList<CatalogProduct> products = database.addTechnique(product);
            return products;
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<TechniqueCategory> addCategory() {
        try {
            TechniqueCategory category;
            category = (TechniqueCategory) messageFromClient.readObject();
            ArrayList<TechniqueCategory> categories = database.addCategory(category);
            return categories;
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<TechniqueBrand> addBrand() {
        try {
            TechniqueBrand brand;
            brand = (TechniqueBrand) messageFromClient.readObject();
            ArrayList<TechniqueBrand> brands = database.addBrand(brand);
            return brands;
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void changeInDatabase() {

    }
    public void updateCategory() {
        try {
            TechniqueCategory category;
            category = (TechniqueCategory) messageFromClient.readObject();
            database.updateCategory(category);
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
    public void updateBrand() {
        try {
            TechniqueBrand brand;
            brand = (TechniqueBrand) messageFromClient.readObject();
            database.updateBrand(brand);
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}