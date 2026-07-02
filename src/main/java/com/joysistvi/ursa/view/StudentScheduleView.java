package com.joysistvi.ursa.view;

import com.joysistvi.ursa.model.StudentSchedule;

import java.util.List;
import java.util.Scanner;

public class StudentScheduleView {

    private final Scanner scanner = new Scanner(System.in);

    public int menu() {
        System.out.println("\n===== STUDENT SCHEDULES =====");
        System.out.println("1. Add");
        System.out.println("2. View By Student");
        System.out.println("3. View By ID");
        System.out.println("4. Update");
        System.out.println("5. Delete");
        System.out.println("0. Back");
        System.out.print("Choice: ");

        return Integer.parseInt(scanner.nextLine());
    }

    public StudentSchedule addNewStudentSchedule() {

        System.out.println("\n===== ADD STUDENT SCHEDULE =====");

        System.out.print("Student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        System.out.print("Schedule ID: ");
        int scheduleId = Integer.parseInt(scanner.nextLine());

        System.out.print("Academic Year: ");
        String academicYear = scanner.nextLine();

        System.out.print("Semester: ");
        String semester = scanner.nextLine();

        return new StudentSchedule(
                0,
                studentId,
                scheduleId,
                academicYear,
                semester
        );
    }

    public StudentSchedule updateStudentSchedule() {

        System.out.println("\n===== UPDATE STUDENT SCHEDULE =====");

        System.out.print("Student Schedule ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        System.out.print("Schedule ID: ");
        int scheduleId = Integer.parseInt(scanner.nextLine());

        System.out.print("Academic Year: ");
        String academicYear = scanner.nextLine();

        System.out.print("Semester: ");
        String semester = scanner.nextLine();

        return new StudentSchedule(
                id,
                studentId,
                scheduleId,
                academicYear,
                semester
        );
    }

    public int readStudentId() {
        System.out.print("Enter Student ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int readStudentScheduleId() {
        System.out.print("Enter Student Schedule ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void displayStudentSchedule(StudentSchedule studentSchedule) {

        System.out.println("----------------------------");
        System.out.println("ID            : " + studentSchedule.getId());
        System.out.println("Student ID    : " + studentSchedule.getStudentId());
        System.out.println("Schedule ID   : " + studentSchedule.getScheduleId());
        System.out.println("Academic Year : " + studentSchedule.getAcademicYear());
        System.out.println("Semester      : " + studentSchedule.getSemester());
        System.out.println("----------------------------");
    }

    public void displayStudentSchedules(List<StudentSchedule> studentSchedules) {

        if (studentSchedules.isEmpty()) {
            System.out.println("No student schedules found.");
            return;
        }

        System.out.println("\n===== STUDENT SCHEDULE LIST =====");

        for (StudentSchedule studentSchedule : studentSchedules) {
            displayStudentSchedule(studentSchedule);
        }
    }
}