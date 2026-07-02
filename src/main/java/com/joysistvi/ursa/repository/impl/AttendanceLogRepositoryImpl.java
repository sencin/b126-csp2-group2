package com.joysistvi.ursa.repository.impl;

import com.joysistvi.ursa.config.DbConnection;
import com.joysistvi.ursa.model.AttendanceLog;
import com.joysistvi.ursa.repository.AttendanceLogRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceLogRepositoryImpl implements AttendanceLogRepository {

    @Override
    public void addAttendanceLog(AttendanceLog attendanceLog) throws SQLException {
        String sql = "INSERT INTO attendance_log (student_id, schedule_id, action) VALUES (?, ?, ?)";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, attendanceLog.getStudentId());
            stmt.setInt(2, attendanceLog.getScheduleId());
            stmt.setString(3, attendanceLog.getAction());

            stmt.executeUpdate();
        }
    }

    @Override
    public List<AttendanceLog> getAttendanceLogsByScheduleId(int scheduleId) throws SQLException {
        List<AttendanceLog> logs = new ArrayList<>();

        String sql = "SELECT * FROM attendance_log WHERE schedule_id = ? ORDER BY timestamp DESC";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, scheduleId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    logs.add(new AttendanceLog(
                            rs.getInt("id"),
                            rs.getInt("student_id"),
                            rs.getInt("schedule_id"),
                            rs.getTimestamp("timestamp").toLocalDateTime(),
                            rs.getString("action")
                    ));
                }
            }
        }

        return logs;
    }

    @Override
    public AttendanceLog getAttendanceLogById(int id) throws SQLException {
        String sql = "SELECT * FROM attendance_log WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new AttendanceLog(
                            rs.getInt("id"),
                            rs.getInt("student_id"),
                            rs.getInt("schedule_id"),
                            rs.getTimestamp("timestamp").toLocalDateTime(),
                            rs.getString("action")
                    );
                }
            }
        }

        return null;
    }

    @Override
    public void updateAttendanceLog(AttendanceLog attendanceLog) throws SQLException {
        String sql = "UPDATE attendance_log SET student_id = ?, schedule_id = ?, action = ? WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, attendanceLog.getStudentId());
            stmt.setInt(2, attendanceLog.getScheduleId());
            stmt.setString(3, attendanceLog.getAction());
            stmt.setInt(4, attendanceLog.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteAttendanceLog(int id) throws SQLException {
        String sql = "DELETE FROM attendance_log WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
