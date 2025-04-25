package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Wait for MySQL to be ready (important for Docker)
                Thread.sleep(5000);
                
                String host = System.getenv("DB_HOST");
                String port = System.getenv("DB_PORT");
                String dbName = System.getenv("DB_NAME");
                String user = System.getenv("DB_USER");
                String password = System.getenv("DB_PASSWORD");
                
                String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName + 
                           "?useSSL=false&allowPublicKeyRetrieval=true" +
                           "&serverTimezone=UTC&autoReconnect=true";
                
                System.out.println("Attempting to connect to: " + url);
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Database connection established!");
            } catch (SQLException | InterruptedException e) {
                System.err.println("Database connection failed:");
                e.printStackTrace();
                throw new RuntimeException("Failed to establish database connection", e);
            }
        }
        return connection;
    }
}