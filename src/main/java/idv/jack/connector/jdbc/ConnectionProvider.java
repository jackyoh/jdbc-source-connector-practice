package idv.jack.connector.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private String url;
    private String username;
    private String password;
    private Connection connection;

    public ConnectionProvider(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public synchronized Connection getConnection() {
        try {
            if (connection == null) {
                newConnection();
            }
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public synchronized void close() {
        if (connection != null) {
           try {
               connection.close();
           } catch(Exception e) {
               throw new RuntimeException(e);
           } finally {
               connection = null;
           }
        }
    }

    private void newConnection() throws SQLException {
        this.connection = DriverManager.getConnection(url, username, password);
    }
}
