package model.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static ConnectionDB instance = null;
    private Connection connection = null;
    
    public ConnectionDB() {
        try {
            String driver   = "org.postgresql.Driver";
            String url      = "jdbc:postgresql://localhost:5432/stm";
            String user     = "postgres";
            String password = "admin";
            
            // Load the Driver
            Class.forName(driver);

            if (url.length() != 0) // Connection with user and password
            {
                connection = DriverManager.getConnection(url, user, password);
            } else // Connection without user and password
            {
                connection = DriverManager.getConnection(url);
            }
            System.out.println("PASS - Connection");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("FAIL - Connection ERRO: " + e);
            }
    }
    
    public static ConnectionDB getInstance() {
        if (instance == null) {
            instance = new ConnectionDB();
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            throw new RuntimeException("conexao==null");
        }
        return connection;
    }

    public void shutDown() {
        try {
            connection.close();
            instance = null;
            connection = null;
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}

