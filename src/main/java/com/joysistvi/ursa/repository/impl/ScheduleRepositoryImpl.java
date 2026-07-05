package com.joysistvi.ursa.repository.impl;

import com.joysistvi.ursa.config.DbConnection;
import com.joysistvi.ursa.model.Schedule;
import com.joysistvi.ursa.repository.ScheduleRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ScheduleRepositoryImpl implements ScheduleRepository {

    @Override
    public void addSchedule(Schedule schedule) throws SQLException {
        // Changed instructor_name to teacher_id
        String sql = "INSERT INTO schedules (courses_id, teacher_id, date, start_time, end_time) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, schedule.getCourseId());
            stmt.setInt(2, schedule.getTeacherId()); // Get the ID instead of string
            stmt.setDate(3, Date.valueOf(schedule.getDate()));
            stmt.setTime(4, Time.valueOf(schedule.getStartTime()));
            stmt.setTime(5, Time.valueOf(schedule.getEndTime()));

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Schedule> getAllSchedules() throws SQLException {
        List<Schedule> schedules = new ArrayList<>();

        // Added JOIN to users table to fetch the teacher's actual name
        String sql =
                "SELECT s.id, s.courses_id, c.course_title, " +
                        "s.teacher_id, CONCAT(u.first_name, ' ', u.last_name) AS instructor_name, " +
                        "s.date, s.start_time, s.end_time " +
                        "FROM schedules s " +
                        "INNER JOIN courses c ON s.courses_id = c.id " +
                        "INNER JOIN users u ON s.teacher_id = u.id " +
                        "ORDER BY s.date ASC, s.start_time ASC";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Using Constructor 2 with all data
                schedules.add(new Schedule(
                        rs.getInt("id"),
                        rs.getInt("courses_id"),
                        rs.getString("course_title"),
                        rs.getInt("teacher_id"),
                        rs.getString("instructor_name"),
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("start_time").toLocalTime(),
                        rs.getTime("end_time").toLocalTime()
                ));
            }
        }

        return schedules;
    }

    @Override
    public List<Schedule> getEnrolledSchedulesByStudentId(int studentId) throws SQLException {
        List<Schedule> schedules = new ArrayList<>();

        // Join with users_schedules to filter by the specific student
        String sql =
                "SELECT s.id, s.courses_id, c.course_title, " +
                        "s.teacher_id, CONCAT(u.first_name, ' ', u.last_name) AS instructor_name, " +
                        "s.date, s.start_time, s.end_time " +
                        "FROM schedules s " +
                        "INNER JOIN courses c ON s.courses_id = c.id " +
                        "INNER JOIN users u ON s.teacher_id = u.id " +
                        "INNER JOIN users_schedules us ON s.id = us.schedules_id " +
                        "WHERE us.user_id = ? " +
                        "ORDER BY s.date ASC, s.start_time ASC";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    schedules.add(new Schedule(
                            rs.getInt("id"),
                            rs.getInt("courses_id"),
                            rs.getString("course_title"),
                            rs.getInt("teacher_id"),
                            rs.getString("instructor_name"),
                            rs.getDate("date").toLocalDate(),
                            rs.getTime("start_time").toLocalTime(),
                            rs.getTime("end_time").toLocalTime()
                    ));
                }
            }
        }

        return schedules;
    }


    @Override
    public Schedule getScheduleById(int id) throws SQLException {
        // Updated to include JOINs so we have course_title and instructor_name for the model
        String sql =
                "SELECT s.id, s.courses_id, c.course_title, " +
                        "s.teacher_id, CONCAT(u.first_name, ' ', u.last_name) AS instructor_name, " +
                        "s.date, s.start_time, s.end_time " +
                        "FROM schedules s " +
                        "INNER JOIN courses c ON s.courses_id = c.id " +
                        "INNER JOIN users u ON s.teacher_id = u.id " +
                        "WHERE s.id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Schedule(
                            rs.getInt("id"),
                            rs.getInt("courses_id"),
                            rs.getString("course_title"),
                            rs.getInt("teacher_id"),
                            rs.getString("instructor_name"),
                            rs.getDate("date").toLocalDate(),
                            rs.getTime("start_time").toLocalTime(),
                            rs.getTime("end_time").toLocalTime()
                    );
                }
            }
        }

        return null;
    }

    @Override
    public void updateSchedule(Schedule schedule) throws SQLException {
        // If the parameter is NULL, COALESCE uses the existing column value
        String sql = "UPDATE schedules SET " +
                "courses_id = COALESCE(?, courses_id), " +
                "teacher_id = COALESCE(?, teacher_id), " +
                "date = COALESCE(?, date), " +
                "start_time = COALESCE(?, start_time), " +
                "end_time = COALESCE(?, end_time) " +
                "WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // If no course record input (0), use the existing record
            if (schedule.getCourseId() > 0) {
                stmt.setInt(1, schedule.getCourseId());
            } else {
                stmt.setNull(1, java.sql.Types.INTEGER);
            }

            // If no teacher record input (0), use the existing record
            if (schedule.getTeacherId() > 0) {
                stmt.setInt(2, schedule.getTeacherId());
            } else {
                stmt.setNull(2, java.sql.Types.INTEGER);
            }

            // If date is null, use the existing record
            if (schedule.getDate() != null) {
                stmt.setDate(3, Date.valueOf(schedule.getDate()));
            } else {
                stmt.setNull(3, java.sql.Types.DATE);
            }

            // If start_time is null, use the existing record
            if (schedule.getStartTime() != null) {
                stmt.setTime(4, Time.valueOf(schedule.getStartTime()));
            } else {
                stmt.setNull(4, java.sql.Types.TIME);
            }

            // If end_time is null, use the existing record
            if (schedule.getEndTime() != null) {
                stmt.setTime(5, Time.valueOf(schedule.getEndTime()));
            } else {
                stmt.setNull(5, java.sql.Types.TIME);
            }

            // Target schedule identifier
            stmt.setInt(6, schedule.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteSchedule(int id) throws SQLException {
        String sql = "DELETE FROM schedules WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Schedule> getSchedulesByTeacherId(int teacherId) throws SQLException {
        List<Schedule> schedules = new ArrayList<>();

        // Join with courses and users to get readable names
        String sql = "SELECT s.id, s.courses_id, c.course_title, s.teacher_id, " +
                "CONCAT(u.first_name, ' ', u.last_name) AS instructor_name, " +
                "s.date, s.start_time, s.end_time " +
                "FROM schedules s " +
                "INNER JOIN courses c ON s.courses_id = c.id " +
                "INNER JOIN users u ON s.teacher_id = u.id " +
                "WHERE s.teacher_id = ? ";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, teacherId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    schedules.add(new Schedule(
                            rs.getInt("id"),
                            rs.getInt("courses_id"),
                            rs.getString("course_title"),
                            rs.getInt("teacher_id"),
                            rs.getString("instructor_name"),
                            rs.getDate("date").toLocalDate(),
                            rs.getTime("start_time").toLocalTime(),
                            rs.getTime("end_time").toLocalTime()
                    ));
                }
            }
        }
        return schedules;
    }
}