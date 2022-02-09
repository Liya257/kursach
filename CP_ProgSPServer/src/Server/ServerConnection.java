package Server;

import Constants.Constants;
import Database.Database;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.LinkedList;

public class ServerConnection {
    public static LinkedList<ClientHandler> userConnected = new LinkedList<>();
    private int idClient = 0;
    ServerSocket socketServ = null;


    public void startServer(){
        try {
            socketServ = new ServerSocket(Constants.PORT);
            System.out.println("сервер запущен....\nожидаем подключение....");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void connectServer() throws SQLException {
        Database database = new Database();
        try {
            while (true) {
                Socket socket = socketServ.accept();
                System.out.println("Клиент подключился\n");
                userConnected.add(new ClientHandler(socket, database, idClient++));
                System.out.println("Клиент подключился\n" + "Общее количество клиентов: " + idClient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeAll(){
        try {

            socketServ.close();
            System.out.println("Сервер остановился !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}