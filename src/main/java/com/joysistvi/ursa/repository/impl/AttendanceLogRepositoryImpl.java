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
        String sql = "INSERT INTO attendance_log (user_id, schedule_id, action) VALUES (?, ?, ?)";

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

        String sql =
                "SELECT al.id, al.user_id, CONCAT(u.first_name, ' ', u.last_name) AS student_name, " +
                        "       al.schedule_id, c.course_title, CONCAT(t.first_name, ' ', t.last_name) AS teacher_name, " +
                        "       al.timestamp, al.action " +
                        "FROM attendance_log al " +
                        "INNER JOIN users u ON al.user_id = u.id " +
                        "INNER JOIN schedules s ON al.schedule_id = s.id " +
                        "INNER JOIN courses c ON s.courses_id = c.id " +
                        "INNER JOIN users t ON s.teacher_id = t.id " +
                        "WHERE al.schedule_id = ? " +
                        "ORDER BY al.timestamp DESC";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, scheduleId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    logs.add(new AttendanceLog(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("student_name"),
                            rs.getInt("schedule_id"),
                            rs.getString("course_title"),
                            rs.getString("teacher_name"), // New field
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
        // Table: attendance_log
        String sql = "SELECT * FROM attendance_log WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new AttendanceLog(
                            rs.getInt("id"),
                            rs.getInt("user_id"), // Matches database column
                            rs.getInt("schedule_id"), // Matches database column
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
        String sql = "UPDATE attendance_log SET user_id = ?, schedule_id = ?, action = ? WHERE id = ?";

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
        // Table: attendance_log
        String sql = "DELETE FROM attendance_log WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
