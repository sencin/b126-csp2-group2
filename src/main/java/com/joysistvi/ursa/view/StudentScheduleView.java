package com.joysistvi.ursa.view;

import com.joysistvi.ursa.model.StudentSchedule;

import java.util.List;
import java.util.Scanner;

import static com.joysistvi.ursa.utils.ConsoleTableUtils.repeat;

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

        int idW = 5, stuW = 10, schedW = 10, acadW = 12, semW = 8;

        String border = "+" + repeat("-", idW + 2) + "+" +
                repeat("-", stuW + 2) + "+" +
                repeat("-", schedW + 2) + "+" +
                repeat("-", acadW + 2) + "+" +
                repeat("-", semW + 2) + "+";

        String rowFormat = "| %-" + idW + "s | %-" + stuW + "s | %-" + schedW + "s | %-" + acadW + "s | %-" + semW + "s |%n";

        System.out.println(border);
        System.out.format(rowFormat, "ID", "Student ID", "Schedule", "Acad Year", "Sem");
        System.out.println(border);
        System.out.format(rowFormat,
                studentSchedule.getId(),
                studentSchedule.getStudentId(),
                studentSchedule.getScheduleId(),
                studentSchedule.getAcademicYear(),
                studentSchedule.getSemester()
        );
        System.out.println(border);
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