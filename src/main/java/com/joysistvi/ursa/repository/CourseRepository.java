package com.joysistvi.ursa.repository;

import com.joysistvi.ursa.model.Course;
import java.sql.SQLException;
import java.util.List;

public interface CourseRepository {
    void addCourse(Course course) throws SQLException;
    List<Course> getAllCourses() throws SQLException;
    List<Course> getCourseById(int id) throws SQLException;
    void updateCourse(Course course) throws SQLException;
    void deleteCourse(int id) throws SQLException;
}
