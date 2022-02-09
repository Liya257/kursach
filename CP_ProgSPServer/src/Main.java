import Server.ServerConnection;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        ServerConnection serverConnection = new ServerConnection();
        serverConnection.startServer();
        serverConnection.connectServer();
        serverConnection.closeAll();

    }
}
