package com.joysistvi.ursa.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnection {
    private static class Holder {
        private static final DbConnection INSTANCE = new DbConnection();
    }

    private final HikariDataSource dataSource;

    private DbConnection() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/ursa_db");
        config.setUsername("root");
        config.setPassword("root");

        config.setMaximumPoolSize(5);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);

        this.dataSource = new HikariDataSource(config);
    }
    public static DbConnection getInstance() {
        return Holder.INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

    public void closePool() {
        if (this.dataSource != null && !this.dataSource.isClosed()) {
            this.dataSource.close();
        }
    }

    public static void init() {
        try {
            getInstance().getConnection().close();
            System.out.println("Database subsystem successfully initialized and verified.");
        } catch (SQLException e) {
            throw new RuntimeException("Could not connect to the database!", e);
        }
    }

}