package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {
    private final String url = "jdbc:postgresql://localhost/adb";
    private final String username = "postgres";
    private final String password = "passw0rd";
    private static Connection connection;
    private static PostgresConnection postgresConnection = new PostgresConnection();

    private PostgresConnection(){
        connect();
    }

    public static Connection getConnection(){
        return connection;
    }

    private void connect() {
        connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
