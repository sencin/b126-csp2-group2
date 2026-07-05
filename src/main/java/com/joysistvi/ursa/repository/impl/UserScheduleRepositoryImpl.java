package com.joysistvi.ursa.repository.impl;

import com.joysistvi.ursa.config.DbConnection;
import com.joysistvi.ursa.model.StudentSchedule;
import com.joysistvi.ursa.repository.UserScheduleRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserScheduleRepositoryImpl implements UserScheduleRepository {

    @Override
    public void addStudentSchedule(StudentSchedule studentSchedule) throws SQLException {
        // Changed students_schedules to users_schedules and student_id to user_id
        String sql = "INSERT INTO users_schedules (user_id, schedules_id, academic_year, semester) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentSchedule.getStudentId());
            stmt.setInt(2, studentSchedule.getScheduleId());
            stmt.setString(3, studentSchedule.getAcademicYear());
            stmt.setString(4, studentSchedule.getSemester());

            stmt.executeUpdate();
        }
    }

    @Override
    public List<StudentSchedule> getStudentSchedulesByStudentId(int studentId) throws SQLException {
        List<StudentSchedule> studentSchedules = new ArrayList<>();

        String sql =
                "SELECT " +
                        "    us.id, " +
                        "    us.user_id, " +
                        "    CONCAT(st.first_name, ' ', st.last_name) AS student_name, " +
                        "    us.schedules_id, " +
                        "    c.course_code, " +
                        "    c.course_title, " +
                        "    CONCAT(t.first_name, ' ', t.last_name) AS teacher_name, " +
                        "    s.date, " +
                        "    s.start_time, " +
                        "    s.end_time, " +
                        "    us.academic_year, " +
                        "    us.semester " +
                        "FROM users_schedules us " +
                        "INNER JOIN users st ON us.user_id = st.id " +
                        "INNER JOIN schedules s ON us.schedules_id = s.id " +
                        "INNER JOIN courses c ON s.courses_id = c.id " +
                        "INNER JOIN users t ON s.teacher_id = t.id " +
                        "WHERE us.user_id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Use Constructor 2 with all the fetched data
                    studentSchedules.add(new StudentSchedule(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("student_name"),
                            rs.getInt("schedules_id"),
                            rs.getString("course_code"),
                            rs.getString("course_title"),
                            rs.getString("teacher_name"),
                            rs.getDate("date").toLocalDate(),
                            rs.getTime("start_time").toLocalTime(),
                            rs.getTime("end_time").toLocalTime(),
                            rs.getString("academic_year"),
                            rs.getString("semester")
                    ));
                }
            }
        }

        return studentSchedules;
    }
    @Override
    public StudentSchedule getStudentScheduleById(int id) throws SQLException {
        // Changed students_schedules to users_schedules
        String sql = "SELECT * FROM users_schedules WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new StudentSchedule(
                            rs.getInt("id"),
                            rs.getInt("user_id"), // Fetching from user_id column
                            rs.getInt("schedules_id"),
                            rs.getString("academic_year"),
                            rs.getString("semester")
                    );
                }
            }
        }

        return null;
    }

    @Override
    public void updateStudentSchedule(StudentSchedule studentSchedule) throws SQLException {
        // Changed students_schedules to users_schedules and student_id to user_id
        String sql = "UPDATE users_schedules SET user_id = ?, schedules_id = ?, " +
                "academic_year = ?, semester = ? WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentSchedule.getStudentId());
            stmt.setInt(2, studentSchedule.getScheduleId());
            stmt.setString(3, studentSchedule.getAcademicYear());
            stmt.setString(4, studentSchedule.getSemester());
            stmt.setInt(5, studentSchedule.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteStudentSchedule(int id) throws SQLException {
        // Changed students_schedules to users_schedules
        String sql = "DELETE FROM users_schedules WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}