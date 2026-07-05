package com.joysistvi.ursa.controller;

import com.joysistvi.ursa.model.Schedule;
import com.joysistvi.ursa.model.StudentSchedule;
import com.joysistvi.ursa.service.ScheduleService;
import com.joysistvi.ursa.service.UserScheduleService;
import com.joysistvi.ursa.service.UserSession;
import com.joysistvi.ursa.view.UserScheduleView;

import java.sql.SQLException;
import java.util.List;

public class UserScheduleController {

    private final UserScheduleService userScheduleService;
    private final UserScheduleView userScheduleView;
    private  final ScheduleService scheduleService;


    public UserScheduleController(UserScheduleService userScheduleService, UserScheduleView userScheduleView, ScheduleService scheduleService) {
        this.userScheduleService = userScheduleService;
        this.userScheduleView = userScheduleView;
        this.scheduleService = scheduleService;
    }

    public void start() {
        boolean running = true;

        while (running) {
            try {
                int choice = userScheduleView.menu();

                switch (choice) {
                    case 1:
                        addStudentSchedule();
                        break;

                    case 2:
                        viewStudentSchedulesByStudent();
                        break;

                    case 3:
                        viewStudentScheduleById();
                        break;

                    case 4:
                        updateStudentSchedule();
                        break;

                    case 5:
                        deleteStudentSchedule();
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

    private void addStudentSchedule() throws SQLException {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        StudentSchedule studentSchedule = userScheduleView.addNewStudentSchedule(schedules);
        userScheduleService.addStudentSchedule(studentSchedule);
        System.out.println("Student schedule added successfully.");
    }

    private void viewStudentSchedulesByStudent() throws SQLException {
        int studentId = UserSession.getCurrentUser().getId();
        List<StudentSchedule> studentSchedules = userScheduleService.getStudentSchedulesByStudentId(studentId);
        userScheduleView.displayStudentSchedules(studentSchedules);
    }

    private void viewStudentScheduleById() throws SQLException {

        if (!"ADMIN".equalsIgnoreCase(UserSession.getCurrentUser().getRole())) {
            System.out.println("Access denied.");
            return;
        }

        int id = userScheduleView.readStudentScheduleId();

        StudentSchedule studentSchedule =
                userScheduleService.getStudentScheduleById(id);

        if (studentSchedule == null) {
            System.out.println("Student schedule not found.");
            return;
        }

        userScheduleView.displayStudentSchedule(studentSchedule);
    }

    private void updateStudentSchedule() throws SQLException {

        if (!"ADMIN".equalsIgnoreCase(UserSession.getCurrentUser().getRole())) {
            System.out.println("Access denied.");
            return;
        }

        StudentSchedule studentSchedule =
                userScheduleView.updateStudentSchedule();

        userScheduleService.updateStudentSchedule(studentSchedule);

        System.out.println("Student schedule updated successfully.");
    }

    private void deleteStudentSchedule() throws SQLException {

        if (!"ADMIN".equalsIgnoreCase(UserSession.getCurrentUser().getRole())) {
            System.out.println("Access denied.");
            return;
        }

        int id = userScheduleView.readStudentScheduleId();

        userScheduleService.deleteStudentSchedule(id);

        System.out.println("Student schedule deleted successfully.");
    }
}