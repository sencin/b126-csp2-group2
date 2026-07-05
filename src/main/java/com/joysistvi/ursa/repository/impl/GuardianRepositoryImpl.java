package com.joysistvi.ursa.repository.impl;

import com.joysistvi.ursa.config.DbConnection;
import com.joysistvi.ursa.model.Guardian;
import com.joysistvi.ursa.repository.GuardianRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuardianRepositoryImpl implements GuardianRepository {

    @Override
    public void addGuardian(Guardian guardian) throws SQLException {
        String sql = "INSERT INTO guardians (user_id, first_name, last_name, relationship, phone_number) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, guardian.getStudentId());
            stmt.setString(2, guardian.getFirstName());
            stmt.setString(3, guardian.getLastName());
            stmt.setString(4, guardian.getRelationship());
            stmt.setString(5, guardian.getPhoneNumber());

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Guardian> getGuardiansByStudentId(int userId) throws SQLException {
        List<Guardian> guardians = new ArrayList<>();

        // SQL join using your explicit users table column layout
        String sql = "SELECT g.*, " +
                "u.first_name AS student_first_name, " +
                "u.middle_name AS student_middle_name, " +
                "u.last_name AS student_last_name " +
                "FROM guardians g " +
                "INNER JOIN users u ON g.user_id = u.id " +
                "WHERE g.user_id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Guardian guardian = new Guardian(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("relationship"),
                            rs.getString("phone_number")
                    );

                    // Construct full name handling optional middle names cleanly
                    String fName = rs.getString("student_first_name");
                    String mName = rs.getString("student_middle_name");
                    String lName = rs.getString("student_last_name");

                    String studentFullName = (mName != null && !mName.trim().isEmpty())
                            ? String.format("%s %s %s", fName, mName, lName)
                            : String.format("%s %s", fName, lName);

                    // Pass the constructed string into your new setter
                    guardian.setStudentFullName(studentFullName);

                    guardians.add(guardian);
                }
            }
        }

        return guardians;
    }
    @Override
    public Guardian getGuardianById(int id) throws SQLException {
        String sql = "SELECT * FROM guardians WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Guardian(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("relationship"),
                            rs.getString("phone_number")
                    );
                }
            }
        }

        return null;
    }

    @Override
    public void updateGuardian(Guardian guardian) throws SQLException {
        String sql = "UPDATE guardians SET " +
                "first_name = COALESCE(?, first_name), " +
                "last_name = COALESCE(?, last_name), " +
                "relationship = COALESCE(?, relationship), " +
                "phone_number = COALESCE(?, phone_number) " +
                "WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, guardian.getFirstName());
            stmt.setString(2, guardian.getLastName());
            stmt.setString(3, guardian.getRelationship());
            stmt.setString(4, guardian.getPhoneNumber());
            stmt.setInt(5, guardian.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteGuardian(int id) throws SQLException {
        String sql = "DELETE FROM guardians WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}