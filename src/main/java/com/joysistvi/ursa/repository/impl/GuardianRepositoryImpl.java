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
        String sql = "INSERT INTO guardians (student_id, first_name, last_name, relationship, phone_number) " +
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
    public List<Guardian> getGuardiansByStudentId(int studentId) throws SQLException {
        List<Guardian> guardians = new ArrayList<>();

        String sql = "SELECT * FROM guardians WHERE student_id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    guardians.add(new Guardian(
                            rs.getInt("id"),
                            rs.getInt("student_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("relationship"),
                            rs.getString("phone_number")
                    ));
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
                            rs.getInt("student_id"),
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
        String sql = "UPDATE guardians SET first_name = ?, last_name = ?, relationship = ?, phone_number = ? " +
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