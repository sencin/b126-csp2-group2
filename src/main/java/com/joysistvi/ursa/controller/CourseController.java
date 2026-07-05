package com.joysistvi.ursa.controller;

import com.joysistvi.ursa.model.Course;
import com.joysistvi.ursa.service.CourseService;
import com.joysistvi.ursa.service.UserSession;
import com.joysistvi.ursa.view.CourseView;

import java.sql.SQLException;
import java.util.List;

public class CourseController {

    private final CourseService courseService;
    private final CourseView courseView;

    public CourseController(CourseService courseService,
                            CourseView courseView) {
        this.courseService = courseService;
        this.courseView = courseView;
    }

    public void start() {
        boolean running = true;

        while (running) {

            try {
                int choice = courseView.menu();
                switch (choice) {
                    case 1:
                        addCourse();
                        break;

                    case 2:
                        viewAllCourses();
                        break;

                    case 3:
                        viewCourseById();
                        break;

                    case 4:
                        updateCourse();
                        break;

                    case 5:
                        deleteCourse();
                        break;

                    case 0:
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option.");
                }

            } catch (SQLException e) {
                System.out.println("Database Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addCourse() throws SQLException {

        if (!"ADMIN".equalsIgnoreCase(UserSession.getCurrentUser().getRole())) {
            System.out.println("Access denied. Only administrators can add courses.");
            return;
        }

        Course course = courseView.addNewCourse();

        courseService.addCourse(course);

        System.out.println("Course added successfully.");
    }

    private void viewAllCourses() throws SQLException {

        List<Course> courses = courseService.getAllCourses();

        courseView.displayCourses(courses);
    }

    private void viewCourseById() throws SQLException {
        int id = courseView.readCourseId();
        List<Course> courses = courseService.getCourseById(id);

        if (courses == null || courses.isEmpty()) {
            System.out.println("Course not found.");
            return;
        }
        courseView.displayCourses(courses);
    }

    private void updateCourse() throws SQLException {

        if (!"ADMIN".equalsIgnoreCase(UserSession.getCurrentUser().getRole())) {
            System.out.println("Access denied.");
            return;
        }

        Course course = courseView.updateCourse();

        courseService.updateCourse(course);

        System.out.println("Course updated successfully.");
    }

    private void deleteCourse() throws SQLException {

        if (!"ADMIN".equalsIgnoreCase(UserSession.getCurrentUser().getRole())) {
            System.out.println("Access denied.");
            return;
        }

        int id = courseView.readCourseId();

        courseService.deleteCourse(id);

        System.out.println("Course deleted successfully.");
    }
}