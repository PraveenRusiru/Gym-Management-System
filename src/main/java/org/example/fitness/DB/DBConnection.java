package org.example.fitness.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private final Connection connection;


    private DBConnection() throws SQLException {
         String URL ="jdbc:mysql://localhost:3306/Fitness";
         String USER ="root";
         String PASSWORD ="12345678";
        connection = DriverManager.getConnection(URL,USER,PASSWORD);
    }
    public static DBConnection getInstance() throws SQLException {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
