package com.joysistvi.ursa;

import com.joysistvi.ursa.config.DbConnection;
import com.joysistvi.ursa.controller.*;
import com.joysistvi.ursa.model.User;
import com.joysistvi.ursa.service.*;
import com.joysistvi.ursa.view.*;

import java.util.Scanner;

import static com.joysistvi.ursa.view.MainMenuView.showMenu;

public class Main {

    public static void main(String[] args) {

        DbConnection.init();

        UserController userController = new UserController(new UserService(), new UserView());
        CourseController courseController = new CourseController(new CourseService(), new CourseView());
        ScheduleController scheduleController = new ScheduleController(new ScheduleService(), new ScheduleView(), new UserService(), new CourseService());
        GuardianController guardianController = new GuardianController(new GuardianService(), new GuardianView());
        StudentScheduleController studentScheduleController = new StudentScheduleController(new StudentScheduleService(), new StudentScheduleView());
        AttendanceLogController attendanceLogController = new AttendanceLogController(new AttendanceLogService(), new AttendanceLogView());
        LoginController loginController =  new LoginController(new LoginService(), new LoginView());
        Scanner scanner = new Scanner(System.in);

        User currentUser = loginController.login();

        if (currentUser == null) {
            System.out.println("Login failed.");
            return;
        }

        switch (currentUser.getRole().toUpperCase()) {

            case "ADMIN":
                boolean running = true;

                while (running) {
                    int choice = showMenu();

                    switch (choice) {
                        case 1:
                            userController.start();
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
                break;

            case "TEACHER":

                boolean teacherRunning = true;

                while (teacherRunning) {

                    System.out.println("\n===== TEACHER MENU =====");
                    System.out.println("1. Schedules");
                    System.out.println("2. Attendance Logs");
                    System.out.println("0. Logout");
                    System.out.print("Choice: ");

                    int choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1:
                            scheduleController.start();
                            break;

                        case 2:
                            attendanceLogController.start();
                            break;

                        case 0:
                            teacherRunning = false;
                            System.out.println("Logged out.");
                            break;

                        default:
                            System.out.println("Invalid option.");
                    }
                }
                break;

            case "STUDENT":

                boolean studentRunning = true;

                while (studentRunning) {

                    System.out.println("\n===== STUDENT MENU =====");
                    System.out.println("1. Courses");
                    System.out.println("2. Guardians");
                    System.out.println("3. My Schedule");
                    System.out.println("4. Attendance Logs");
                    System.out.println("0. Logout");
                    System.out.print("Choice: ");

                    int choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1:
                            courseController.start();
                            break;
                        case 2:
                            guardianController.start();
                            break;
                        case 3:
                            studentScheduleController.start();
                            break;
                        case 4:
                            attendanceLogController.start();
                            break;
                        case 0:
                            studentRunning = false;
                            System.out.println("Logged out.");
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                }
                break;

            default:
                System.out.println("Unknown role: " + currentUser.getRole());
        }



        boolean running = true;

        while (running) {
            int choice = showMenu();

            switch (choice) {
                case 1: userController.start(); break;
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