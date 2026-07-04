package com.joysistvi.ursa.view;

import com.joysistvi.ursa.model.Course;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static com.joysistvi.ursa.utils.ConsoleTableUtils.repeat;

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


    public void displayCourses(List<Course> courses) {

        int idW = 5;
        int codeW = 12;
        int titleW = 50;

        String border = "+" + repeat("-", idW + 2) + "+" +
                repeat("-", codeW + 2) + "+" +
                repeat("-", titleW + 2) + "+";

        String rowFormat = "| %-" + idW + "s | %-" + codeW + "s | %-" + titleW + "s |%n";

        System.out.println(border);
        System.out.format(rowFormat, "ID", "Code", "Title");
        System.out.println(border);

        for (Course course : courses) {
            System.out.format(rowFormat,
                    course.getId(),
                    course.getCourseCode(),
                    course.getCourseTitle()
            );
        }

        System.out.println(border);
    }
}