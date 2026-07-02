package com.joysistvi.ursa.service;

import com.joysistvi.ursa.model.Course;
import com.joysistvi.ursa.repository.CourseRepository;
import com.joysistvi.ursa.repository.impl.CourseRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService() {
        this.courseRepository = new CourseRepositoryImpl();
    }

    public void addCourse(Course course) throws SQLException {
        courseRepository.addCourse(course);
    }

    public List<Course> getAllCourses() throws SQLException {
        return courseRepository.getAllCourses();
    }

    public Course getCourseById(int id) throws SQLException {
        return courseRepository.getCourseById(id);
    }

    public void updateCourse(Course course) throws SQLException {
        courseRepository.updateCourse(course);
    }

    public void deleteCourse(int id) throws SQLException {
        courseRepository.deleteCourse(id);
    }
}