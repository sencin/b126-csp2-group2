package com.joysistvi.ursa.repository.impl;

import com.joysistvi.ursa.config.DbConnection;
import com.joysistvi.ursa.model.Course;
import com.joysistvi.ursa.repository.CourseRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryImpl implements CourseRepository {

    @Override
    public void addCourse(Course course) throws SQLException {
        String sql = "INSERT INTO courses (course_code, course_title) VALUES (?, ?)";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getCourseCode());
            stmt.setString(2, course.getCourseTitle());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses ORDER BY course_code ASC";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                courses.add(new Course(rs.getInt("id"), rs.getString("course_code"), rs.getString("course_title")));
            }
        }
        return courses;
    }

    @Override
    public List<Course> getCourseById(int id) throws SQLException {
        String sql = "SELECT * FROM courses WHERE id = ?";

        List<Course> courses = new ArrayList<>();

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    courses.add(new Course(
                            rs.getInt("id"),
                            rs.getString("course_code"),
                            rs.getString("course_title")
                    ));
                }
            }
        }

        return courses;
    }

    @Override
    public void updateCourse(Course course) throws SQLException {
        String sql = "UPDATE courses SET course_code = ?, course_title = ? WHERE id = ?";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getCourseCode());
            stmt.setString(2, course.getCourseTitle());
            stmt.setInt(3, course.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteCourse(int id) throws SQLException {
        String sql = "DELETE FROM courses WHERE id = ?";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
