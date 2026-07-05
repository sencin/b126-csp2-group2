package com.joysistvi.ursa;

import com.joysistvi.ursa.config.DbConnection;
import com.joysistvi.ursa.controller.*;
import com.joysistvi.ursa.model.User;
import com.joysistvi.ursa.service.*;
import com.joysistvi.ursa.view.*;

import java.util.Scanner;

import static com.joysistvi.ursa.view.MainMenuView.*;

public class Main {

    public static void main(String[] args) {

        DbConnection.init();

        UserController userController = new UserController(new UserService(), new UserView());
        CourseController courseController = new CourseController(new CourseService(), new CourseView());
        ScheduleController scheduleController = new ScheduleController(new ScheduleService(), new ScheduleView(), new UserService(), new CourseService());

        GuardianController guardianController = new GuardianController(new GuardianService(), new GuardianView());
        UserScheduleController userScheduleController = new UserScheduleController(new UserScheduleService(), new UserScheduleView(), new ScheduleService());

        AttendanceLogController attendanceLogController = new AttendanceLogController(new AttendanceLogService(), new AttendanceLogView(), new ScheduleService());

        LoginController loginController = new LoginController(new LoginService(), new LoginView());

        while (true) {
            User currentUser = loginController.login();

            if (currentUser == null) {
                System.out.println("Login failed.");
                continue;
            }


            switch (currentUser.getRole().toUpperCase()) {
                case "ADMIN":
                    boolean adminRunning = true;
                    while (adminRunning) {
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
                                userScheduleController.start();
                                break;
                            case 6:
                                attendanceLogController.start();
                                break;
                            case 0:
                                UserSession.logout();
                                adminRunning = false;
                                System.out.println("Logged out.");
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                    }
                    break;
                case "TEACHER":
                    boolean teacherRunning = true;
                    while (teacherRunning) {
                        int choice = showTeacherMenu();
                        switch (choice) {
                            case 1:
                                scheduleController.start();
                                break;
                            case 2:
                                attendanceLogController.start();
                                break;
                            case 0:
                                UserSession.logout();
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
                        int choice = showStudentMenu();
                        switch (choice) {
                            case 1:
                                courseController.start();
                                break;
                            case 2:
                                guardianController.start();
                                break;
                            case 3:
                                userScheduleController.start();
                                break;
                            case 4:
                                attendanceLogController.start();
                                break;
                            case 0:
                                UserSession.logout();
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
        }
    }
}