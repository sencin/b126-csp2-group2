package com.joysistvi.ursa.view;

import com.joysistvi.ursa.model.Course;

import java.util.List;
import java.util.Scanner;

public class CourseView {

    private final Scanner scanner = new Scanner(System.in);

    public int menu() {
        System.out.println("\n===== COURSES =====");
        System.out.println("1. Add");
        System.out.println("2. View All");
        System.out.println("3. View By ID");
        System.out.println("4. Update");
        System.out.println("5. Delete");
        System.out.println("0. Back");
        System.out.print("Choice: ");

        return Integer.parseInt(scanner.nextLine());
    }

    public Course addNewCourse() {

        System.out.println("\n===== ADD COURSE =====");

        System.out.print("Course Code: ");
        String courseCode = scanner.nextLine();

        System.out.print("Course Title: ");
        String courseTitle = scanner.nextLine();

        return new Course(
                0,
                courseCode,
                courseTitle
        );
    }

    public Course updateCourse() {

        System.out.println("\n===== UPDATE COURSE =====");

        System.out.print("Course ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Course Code: ");
        String courseCode = scanner.nextLine();

        System.out.print("Course Title: ");
        String courseTitle = scanner.nextLine();

        return new Course(
                id,
                courseCode,
                courseTitle
        );
    }

    public int readCourseId() {
        System.out.print("Enter Course ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void displayCourse(Course course) {
        System.out.println("----------------------------");
        System.out.println("ID           : " + course.getId());
        System.out.println("Course Code  : " + course.getCourseCode());
        System.out.println("Course Title : " + course.getCourseTitle());
        System.out.println("----------------------------");
    }

    public void displayCourses(List<Course> courses) {

        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        System.out.println("\n===== COURSE LIST =====");

        for (Course course : courses) {
            displayCourse(course);
        }
    }
}