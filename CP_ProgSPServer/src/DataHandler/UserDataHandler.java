package DataHandler;

import Database.Database;
import Interfaces.IObjectService;
import sample.Classes.User;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDataHandler implements IObjectService {
    private int idClient;
    private Socket socket;
    private ObjectInputStream messageFromClient;
    private ObjectOutputStream writeMessage;


    private Database database;

    public UserDataHandler(int countUser, Socket socket, Database database, ObjectInputStream messageFromClient, ObjectOutputStream writeMessage) {
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

    @Override
    public void delete() {

    }

    public void setOnlineUser() throws IOException, ClassNotFoundException {
        User user;
        user = ((User) messageFromClient.readObject());
        database.setOnline(user);
    }
    public void setOfflineUser() throws IOException, ClassNotFoundException {
        User user;
        user = ((User) messageFromClient.readObject());
        database.setOffline(user);
    }
    /*public ArrayList<User> changeUser() throws IOException, ClassNotFoundException {
        ArrayList<User> res = database.setUser();
        User user = (User) messageFromClient.readObject();

        return res;
    }*/

    public int roleUserReturn() throws SQLException, IOException, ClassNotFoundException {
        User user;
        user = ((User) messageFromClient.readObject());
        ArrayList<User> result = database.ReturnUser(user);
        for (int i = 0; i < result.size(); i++) {
            user = result.get(i);
        }
        return user.getRole();

    }

    public int saveUserDataAdmin() throws IOException, ClassNotFoundException, SQLException {
        User user;
        user = (User) messageFromClient.readObject();
        int p = database.WriteUser(user);
        return p;
    }
    public ArrayList<User> idUserReturn() {
        try {
            User user;
            user = ((User) messageFromClient.readObject());
            ArrayList<User> result = database.ReturnUser(user);
            return result;
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int addInDatabase() {

        try
        {
            User user;
            user = ((User) messageFromClient.readObject());
            int id = database.WriteUser(user);
            return id;
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void changeInDatabase() {

    }
    public ArrayList<User> сheckInDatabase()
    {
        try
        {
            User user;
            user = ((User) messageFromClient.readObject());
            ArrayList<User> user3 = database.CheckUserInDatabase(user);
            return  user3;
        } catch (IOException | ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public int saveUserData() {
        try {
            User user;
            user = ((User) messageFromClient.readObject());
            ArrayList<User> users = database.SaveUserData(user);
            for (int i = 0; i < users.size(); i++) {
                user = users.get(i);
            }
            return user.getId();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}