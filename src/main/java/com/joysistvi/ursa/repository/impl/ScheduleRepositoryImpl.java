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
        String sql = "INSERT INTO schedules (courses_id, instructor_name, date, start_time, end_time) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, schedule.getCourseId());
            stmt.setString(2, schedule.getInstructorName());
            stmt.setDate(3, Date.valueOf(schedule.getDate()));
            stmt.setTime(4, Time.valueOf(schedule.getStartTime()));
            stmt.setTime(5, Time.valueOf(schedule.getEndTime()));

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Schedule> getAllSchedules() throws SQLException {
        List<Schedule> schedules = new ArrayList<>();

        String sql =
                "SELECT s.id, s.courses_id, c.course_title, " +
                        "s.instructor_name, s.date, s.start_time, s.end_time " +
                        "FROM schedules s " +
                        "INNER JOIN courses c ON s.courses_id = c.id " +
                        "ORDER BY s.date ASC, s.start_time ASC";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                schedules.add(new Schedule(
                        rs.getInt("id"),
                        rs.getInt("courses_id"),
                        rs.getString("course_title"),
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
    public Schedule getScheduleById(int id) throws SQLException {
        String sql = "SELECT * FROM schedules WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Schedule(
                            rs.getInt("id"),
                            rs.getInt("courses_id"),
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
        String sql = "UPDATE schedules SET courses_id = ?, instructor_name = ?, date = ?, " +
                "start_time = ?, end_time = ? WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, schedule.getCourseId());
            stmt.setString(2, schedule.getInstructorName());
            stmt.setDate(3, Date.valueOf(schedule.getDate()));
            stmt.setTime(4, Time.valueOf(schedule.getStartTime()));
            stmt.setTime(5, Time.valueOf(schedule.getEndTime()));
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
}