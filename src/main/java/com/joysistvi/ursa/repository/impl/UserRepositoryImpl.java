package com.joysistvi.ursa.repository.impl;

import com.joysistvi.ursa.config.DbConnection;
import com.joysistvi.ursa.model.User;
import com.joysistvi.ursa.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public void addUser(User user) throws SQLException {

        String sql = "INSERT INTO users (first_name, last_name, middle_name, email, password, gender, account_status, role, date_of_birth) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getMiddleName());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getAccountStatus());
            stmt.setString(8, user.getRole());
            stmt.setDate(9, Date.valueOf(user.getDateOfBirth()));

            stmt.executeUpdate();
        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users ORDER BY last_name ASC";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("middle_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("gender"),
                        rs.getString("account_status"),
                        rs.getString("role"),
                        rs.getDate("date_of_birth").toLocalDate()
                ));
            }
        }

        return users;
    }

    @Override
    public User getUserById(int id) throws SQLException {

        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("middle_name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("gender"),
                            rs.getString("account_status"),
                            rs.getString("role"),
                            rs.getDate("date_of_birth").toLocalDate()
                    );
                }
            }
        }

        return null;
    }

    @Override
    public void updateUser(User user) throws SQLException {

        String sql = "UPDATE users SET first_name = ?, last_name = ?, middle_name = ?, " +
                "email = ?, gender = ?, account_status = ?, role = ?, " +
                "date_of_birth = ? WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getMiddleName());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getGender());
            stmt.setString(6, user.getAccountStatus());
            stmt.setString(7, user.getRole());
            stmt.setDate(8, Date.valueOf(user.getDateOfBirth()));
            stmt.setInt(9, user.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteUser(int id) throws SQLException {

        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public User getUserByEmail(String email) throws SQLException {

        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("middle_name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("gender"),
                            rs.getString("account_status"),
                            rs.getString("role"),
                            rs.getDate("date_of_birth").toLocalDate()
                    );
                }
            }
        }

        return null;
    }
}