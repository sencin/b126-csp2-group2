package com.joysistvi.ursa.repository.impl;

import com.joysistvi.ursa.config.DbConnection;
import com.joysistvi.ursa.model.StudentSchedule;
import com.joysistvi.ursa.repository.StudentScheduleRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentScheduleRepositoryImpl implements StudentScheduleRepository {

    @Override
    public void addStudentSchedule(StudentSchedule studentSchedule) throws SQLException {
        String sql = "INSERT INTO students_schedules (student_id, schedules_id, academic_year, semester) " +
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

        String sql = "SELECT * FROM students_schedules WHERE student_id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    studentSchedules.add(new StudentSchedule(
                            rs.getInt("id"),
                            rs.getInt("student_id"),
                            rs.getInt("schedules_id"),
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
        String sql = "SELECT * FROM students_schedules WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new StudentSchedule(
                            rs.getInt("id"),
                            rs.getInt("student_id"),
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
        String sql = "UPDATE students_schedules SET student_id = ?, schedules_id = ?, " +
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
        String sql = "DELETE FROM students_schedules WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}