package com.joysistvi.ursa;

import com.joysistvi.ursa.config.DbConnection;
import com.joysistvi.ursa.controller.*;
import com.joysistvi.ursa.service.*;
import com.joysistvi.ursa.view.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DbConnection.init();

        StudentController studentController = new StudentController(new StudentService(), new StudentView());

        CourseController courseController = new CourseController(new CourseService(), new CourseView());

        ScheduleController scheduleController = new ScheduleController(new ScheduleService(), new ScheduleView());

        GuardianController guardianController = new GuardianController(new GuardianService(), new GuardianView());

        StudentScheduleController studentScheduleController = new StudentScheduleController(new StudentScheduleService(), new StudentScheduleView());

        AttendanceLogController attendanceLogController = new AttendanceLogController(new AttendanceLogService(), new AttendanceLogView());

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.println("\n========== URSA ==========");
            System.out.println("1. Students");
            System.out.println("2. Courses");
            System.out.println("3. Schedules");
            System.out.println("4. Guardians");
            System.out.println("5. Student Schedules");
            System.out.println("6. Attendance Logs");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    studentController.start();
                    break;

                case 2:
                    courseController.start();
                    break;

                case 3:
                    scheduleController.start();
                    break;

                case 4:
                    guardianController.start();
                    break;

                case 5:
                    studentScheduleController.start();
                    break;

                case 6:
                    attendanceLogController.start();
                    break;

                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}