package com.joysistvi.ursa.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/ursa_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final DbConnection INSTANCE = new DbConnection();

    private DbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }
    }

    public static DbConnection getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void init() {
        try (Connection connection = getInstance().getConnection()) {
            System.out.println("Database subsystem successfully initialized and verified.");
        } catch (SQLException e) {
            throw new RuntimeException("Could not connect to the database!", e);
        }
    }
}