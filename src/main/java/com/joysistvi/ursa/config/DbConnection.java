package com.joysistvi.ursa.config;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class DbConnection {

    private static final String HOST_URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE = "ursa_db";
    private static final String URL = HOST_URL + DATABASE;

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
        try {
            // Connect to MySQL server
            Connection serverConnection = DriverManager.getConnection(
                    HOST_URL,
                    USERNAME,
                    PASSWORD
            );

            getInstance().ensureMysqlSchema(serverConnection);
            serverConnection.close();

            // Verify database connection
            Connection databaseConnection = getInstance().getConnection();
            databaseConnection.close();

            System.out.println("Database subsystem successfully initialized and verified.");

        } catch (SQLException e) {
            throw new RuntimeException("Could not initialize the database!", e);
        }
    }

    private void ensureMysqlSchema(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        // Create database
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE);

        // Use database
        stmt.execute("USE " + DATABASE);

        // Courses
        stmt.execute(
                "CREATE TABLE IF NOT EXISTS courses (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "course_code VARCHAR(50) NOT NULL UNIQUE, " +
                        "course_title VARCHAR(150) NOT NULL" +
                        ")"
        );

        // Users
        stmt.execute(
                "CREATE TABLE IF NOT EXISTS users (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "first_name VARCHAR(100) NOT NULL, " +
                        "last_name VARCHAR(100) NOT NULL, " +
                        "middle_name VARCHAR(100), " +
                        "email VARCHAR(150) NOT NULL UNIQUE, " +
                        "password VARCHAR(255) NOT NULL, " +
                        "gender VARCHAR(20), " +
                        "account_status VARCHAR(50) DEFAULT 'Active', " +
                        "role VARCHAR(50) NOT NULL, " +
                        "date_of_birth DATE" +
                        ")"
        );

        // Schedules
        stmt.execute(
                "CREATE TABLE IF NOT EXISTS schedules (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "courses_id INT NOT NULL, " +
                        "teacher_id INT NOT NULL, " +
                        "date DATE NOT NULL, " +
                        "start_time TIME NOT NULL, " +
                        "end_time TIME NOT NULL, " +
                        "FOREIGN KEY (courses_id) REFERENCES courses(id), " +
                        "FOREIGN KEY (teacher_id) REFERENCES users(id)" +
                        ")"
        );

        // Guardians
        stmt.execute(
                "CREATE TABLE IF NOT EXISTS guardians (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "user_id INT NOT NULL, " +
                        "first_name VARCHAR(100) NOT NULL, " +
                        "last_name VARCHAR(100) NOT NULL, " +
                        "relationship VARCHAR(50) NOT NULL, " +
                        "phone_number VARCHAR(20), " +
                        "FOREIGN KEY (user_id) REFERENCES users(id)" +
                        ")"
        );

        // Users Schedules
        stmt.execute(
                "CREATE TABLE IF NOT EXISTS users_schedules (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "user_id INT NOT NULL, " +
                        "schedules_id INT NOT NULL, " +
                        "academic_year VARCHAR(20) NOT NULL, " +
                        "semester VARCHAR(50) NOT NULL, " +
                        "FOREIGN KEY (user_id) REFERENCES users(id), " +
                        "FOREIGN KEY (schedules_id) REFERENCES schedules(id)" +
                        ")"
        );

        // Attendance Log
        stmt.execute(
                "CREATE TABLE IF NOT EXISTS attendance_log (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "user_id INT NOT NULL, " +
                        "schedule_id INT NOT NULL, " +
                        "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                        "action VARCHAR(50) NOT NULL, " +
                        "FOREIGN KEY (user_id) REFERENCES users(id), " +
                        "FOREIGN KEY (schedule_id) REFERENCES schedules(id)" +
                        ")"
        );

        String sql =
                "INSERT INTO users " +
                        "(first_name, last_name, middle_name, email, password, gender, account_status, role, date_of_birth) " +
                        "SELECT ?, ?, ?, ?, ?, ?, ?, ?, ? " +
                        "WHERE NOT EXISTS (" +
                        "SELECT 1 FROM users WHERE email = ?" +
                        ")";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "System");
            ps.setString(2, "Administrator");
            ps.setString(3, "");
            ps.setString(4, "admin");
            String hashedAdminPassword = BCrypt.hashpw("admin", BCrypt.gensalt());
            ps.setString(5, hashedAdminPassword);
            ps.setString(6, "");
            ps.setString(7, "ACTIVE");
            ps.setString(8, "ADMIN");
            ps.setString(9, "2002-02-20");
            ps.setString(10, "admin");

            ps.executeUpdate();
        }


        stmt.close();

        System.out.println("[DB] MySQL schema verified and ready.");
    }
}