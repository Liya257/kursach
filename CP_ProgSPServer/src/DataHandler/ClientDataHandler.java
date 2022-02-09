package DataHandler;

import Database.Database;
import Interfaces.IObjectService;
import sample.Classes.Client;
import sample.Classes.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientDataHandler implements IObjectService {
    private int idClient;
    private Socket socket;
    private ObjectInputStream messageFromClient;
    private ObjectOutputStream writeMessage;


    private Database database;

    public ClientDataHandler(int countUser, Socket socket, Database database, ObjectInputStream messageFromClient, ObjectOutputStream writeMessage) {
        this.idClient = countUser;
        this.socket = socket;
        this.database = database;
        this.messageFromClient = messageFromClient;
        // this.writeMessage = writeMessage;
    }

    @Override
    public ArrayList<Client> showAll() {
        try {

            ArrayList<Client> clients = database.showAllClients();
            return clients;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete() {
        try {
            Client client;
            client = (Client) messageFromClient.readObject();

            System.out.println(client.getIdClient());
            database.DeleteClient(client);
        }
        catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int addInDatabase() {
        try
        {
            Client client;

            client = ((Client) messageFromClient.readObject());

            database.WriteClient(client);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void changeInDatabase() {

        Client client = null;
        try {
            client = (Client) messageFromClient.readObject();
            database.SaveClientChange(client);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void ChangeUserData() throws SQLException, IOException, ClassNotFoundException {
            Client client = ((Client) messageFromClient.readObject());
            User user = ((User) messageFromClient.readObject());
            database.ChangeUserData(user,client);

    }
    public ArrayList<Client> clientReturn() {
        try {
            Client client = ((Client) messageFromClient.readObject());
            ArrayList<Client> result = database.ReturnClient(client);
            return result;
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveClientData() {
        try {
            Client client;
            client = ((Client) messageFromClient.readObject());
            database.SaveClientData(client);

        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}