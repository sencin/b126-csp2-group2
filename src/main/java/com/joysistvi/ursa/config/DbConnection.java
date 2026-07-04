package com.joysistvi.ursa.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

        // Students
        stmt.execute(
                "CREATE TABLE IF NOT EXISTS students (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "first_name VARCHAR(100) NOT NULL, " +
                        "last_name VARCHAR(100) NOT NULL, " +
                        "middle_name VARCHAR(100), " +
                        "email VARCHAR(150) NOT NULL UNIQUE, " +
                        "password VARCHAR(255) NOT NULL, " +
                        "gender VARCHAR(20), " +
                        "account_status VARCHAR(50) DEFAULT 'Active', " +
                        "year_level INT, " +
                        "date_of_birth DATE" +
                        ")"
        );

        // Schedules
        stmt.execute(
                "CREATE TABLE IF NOT EXISTS schedules (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "courses_id INT NOT NULL, " +
                        "instructor_name VARCHAR(150) NOT NULL, " +
                        "date DATE NOT NULL, " +
                        "start_time TIME NOT NULL, " +
                        "end_time TIME NOT NULL, " +
                        "FOREIGN KEY (courses_id) REFERENCES courses(id)" +
                        ")"
        );

        // Guardians
        stmt.execute(
                "CREATE TABLE IF NOT EXISTS guardians (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "student_id INT NOT NULL, " +
                        "first_name VARCHAR(100) NOT NULL, " +
                        "last_name VARCHAR(100) NOT NULL, " +
                        "relationship VARCHAR(50) NOT NULL, " +
                        "phone_number VARCHAR(20), " +
                        "FOREIGN KEY (student_id) REFERENCES students(id)" +
                        ")"
        );

        // Students Schedules
        stmt.execute(
                "CREATE TABLE IF NOT EXISTS students_schedules (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "student_id INT NOT NULL, " +
                        "schedules_id INT NOT NULL, " +
                        "academic_year VARCHAR(20) NOT NULL, " +
                        "semester VARCHAR(50) NOT NULL, " +
                        "FOREIGN KEY (student_id) REFERENCES students(id), " +
                        "FOREIGN KEY (schedules_id) REFERENCES schedules(id)" +
                        ")"
        );

        // Attendance Log
        stmt.execute(
                "CREATE TABLE IF NOT EXISTS attendance_log (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "student_id INT NOT NULL, " +
                        "schedule_id INT NOT NULL, " +
                        "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                        "action VARCHAR(50) NOT NULL, " +
                        "FOREIGN KEY (student_id) REFERENCES students(id), " +
                        "FOREIGN KEY (schedule_id) REFERENCES schedules(id)" +
                        ")"
        );

        stmt.close();

        System.out.println("[DB] MySQL schema verified and ready.");
    }
}