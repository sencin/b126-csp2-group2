package com.joysistvi.ursa;

import com.joysistvi.ursa.config.DbConnection;
import com.joysistvi.ursa.controller.*;
import com.joysistvi.ursa.service.*;
import com.joysistvi.ursa.view.*;

import java.util.Scanner;

import static com.joysistvi.ursa.view.MainMenuView.showMenu;

public class Main {

    public static void main(String[] args) {

        DbConnection.init();

        StudentController studentController = new StudentController(new StudentService(), new StudentView());

        CourseController courseController = new CourseController(new CourseService(), new CourseView());

        ScheduleController scheduleController = new ScheduleController(new ScheduleService(), new ScheduleView(), new StudentService(), new CourseService());

        GuardianController guardianController = new GuardianController(new GuardianService(), new GuardianView());

        StudentScheduleController studentScheduleController = new StudentScheduleController(new StudentScheduleService(), new StudentScheduleView());

        AttendanceLogController attendanceLogController = new AttendanceLogController(new AttendanceLogService(), new AttendanceLogView());

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            int choice = showMenu();

            switch (choice) {
                case 1: studentController.start(); break;
                case 2: courseController.start(); break;
                case 3: scheduleController.start(); break;
                case 4: guardianController.start(); break;
                case 5: studentScheduleController.start(); break;
                case 6: attendanceLogController.start(); break;
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}