package com.joysistvi.ursa.controller;

import com.joysistvi.ursa.model.Schedule;
import com.joysistvi.ursa.model.StudentSchedule;
import com.joysistvi.ursa.service.ScheduleService;
import com.joysistvi.ursa.service.StudentScheduleService;
import com.joysistvi.ursa.service.UserSession;
import com.joysistvi.ursa.view.StudentScheduleView;

import java.sql.SQLException;
import java.util.List;

public class StudentScheduleController {

    private final StudentScheduleService studentScheduleService;
    private final StudentScheduleView studentScheduleView;
    private  final ScheduleService scheduleService;


    public StudentScheduleController(StudentScheduleService studentScheduleService, StudentScheduleView studentScheduleView, ScheduleService scheduleService) {
        this.studentScheduleService = studentScheduleService;
        this.studentScheduleView = studentScheduleView;
        this.scheduleService = scheduleService;
    }

    public void start() {
        boolean running = true;

        while (running) {

            int choice = studentScheduleView.menu();

            try {
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
        StudentSchedule studentSchedule = studentScheduleView.addNewStudentSchedule(schedules);
        studentScheduleService.addStudentSchedule(studentSchedule);
        System.out.println("Student schedule added successfully.");
    }

    private void viewStudentSchedulesByStudent() throws SQLException {

        int studentId = studentScheduleView.readStudentId();

        List<StudentSchedule> studentSchedules =
                studentScheduleService.getStudentSchedulesByStudentId(studentId);

        studentScheduleView.displayStudentSchedules(studentSchedules);
    }

    private void viewStudentScheduleById() throws SQLException {

        int id = studentScheduleView.readStudentScheduleId();

        StudentSchedule studentSchedule =
                studentScheduleService.getStudentScheduleById(id);

        if (studentSchedule == null) {
            System.out.println("Student schedule not found.");
            return;
        }

        studentScheduleView.displayStudentSchedule(studentSchedule);
    }

    private void updateStudentSchedule() throws SQLException {

        if (!"ADMIN".equalsIgnoreCase(UserSession.getCurrentUser().getRole())) {
            System.out.println("Access denied.");
            return;
        }

        StudentSchedule studentSchedule =
                studentScheduleView.updateStudentSchedule();

        studentScheduleService.updateStudentSchedule(studentSchedule);

        System.out.println("Student schedule updated successfully.");
    }

    private void deleteStudentSchedule() throws SQLException {

        if (!"ADMIN".equalsIgnoreCase(UserSession.getCurrentUser().getRole())) {
            System.out.println("Access denied.");
            return;
        }

        int id = studentScheduleView.readStudentScheduleId();

        studentScheduleService.deleteStudentSchedule(id);

        System.out.println("Student schedule deleted successfully.");
    }
}