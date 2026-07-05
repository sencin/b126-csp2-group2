package com.joysistvi.ursa.controller;

import com.joysistvi.ursa.model.AttendanceLog;
import com.joysistvi.ursa.model.Schedule;
import com.joysistvi.ursa.model.User;
import com.joysistvi.ursa.service.AttendanceLogService;
import com.joysistvi.ursa.service.ScheduleService;
import com.joysistvi.ursa.service.UserSession;
import com.joysistvi.ursa.utils.ConsoleInput;
import com.joysistvi.ursa.view.AttendanceLogView;

import java.sql.SQLException;
import java.util.List;

public class AttendanceLogController {

    private final AttendanceLogService attendanceLogService;
    private final AttendanceLogView attendanceLogView;
    private  final ScheduleService scheduleService;

    public AttendanceLogController(AttendanceLogService attendanceLogService, AttendanceLogView attendanceLogView, ScheduleService scheduleService) {
        this.attendanceLogService = attendanceLogService;
        this.attendanceLogView = attendanceLogView;
        this.scheduleService = scheduleService;
    }

    public void start() {
        boolean running = true;

        while (running) {
            try {
                int choice = attendanceLogView.menu();
                switch (choice) {
                    case 1:
                        addAttendanceLog();
                        break;

                    case 2:
                        viewAttendanceLogsBySchedule();
                        break;

                    case 3:
                        viewAttendanceLogById();
                        break;

                    case 4:
                        updateAttendanceLog();
                        break;

                    case 5:
                        deleteAttendanceLog();
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

    private void addAttendanceLog() throws SQLException {
        if (!"STUDENT".equalsIgnoreCase(UserSession.getCurrentUser().getRole())) {
            System.out.println("Access denied. Only Students can Add Attendance");
            return;
        }

        int studentId = UserSession.getCurrentUser().getId();
        List<Schedule> schedules = scheduleService.getEnrolledSchedulesByStudentId(studentId);
        AttendanceLog attendanceLog = attendanceLogView.addNewAttendanceLog(schedules);
        attendanceLogService.addAttendanceLog(attendanceLog);

        System.out.println("Attendance log added successfully.");
    }

    private void viewAttendanceLogsBySchedule() throws SQLException {
        User currentUser = UserSession.getCurrentUser();
        int userId = currentUser.getId();
        String role = currentUser.getRole();

        List<Schedule> schedules;

        if ("STUDENT".equalsIgnoreCase(role)) {
            schedules = scheduleService.getEnrolledSchedulesByStudentId(userId);
        } else if ("TEACHER".equalsIgnoreCase(role)) {
            schedules = scheduleService.getSchedulesByTeacher(userId);
        } else {
            schedules = scheduleService.getAllSchedules();
        }
        if (schedules.isEmpty()) {
            System.out.println("No schedules found for your profile account.");
            return;
        }

        int scheduleId = attendanceLogView.readValidScheduleId(schedules);
        List<AttendanceLog> attendanceLogs = attendanceLogService.getAttendanceLogsByScheduleId(scheduleId);
        attendanceLogView.displayAttendanceLogs(attendanceLogs);
    }

    private void viewAttendanceLogById() throws SQLException {
        if (!"ADMIN".equalsIgnoreCase(UserSession.getCurrentUser().getRole())) {
            System.out.println("Access denied.");
            return;
        }

        int id = attendanceLogView.readAttendanceLogId();

        AttendanceLog attendanceLog =
                attendanceLogService.getAttendanceLogById(id);

        if (attendanceLog == null) {
            System.out.println("Attendance log not found.");
            return;
        }

        attendanceLogView.displayAttendanceLog(attendanceLog);
    }

    private void updateAttendanceLog() throws SQLException {

        if (!"ADMIN".equalsIgnoreCase(UserSession.getCurrentUser().getRole())) {
            System.out.println("Access denied.");
            return;
        }

        AttendanceLog attendanceLog =
                attendanceLogView.updateAttendanceLog();

        attendanceLogService.updateAttendanceLog(attendanceLog);

        System.out.println("Attendance log updated successfully.");
    }

    private void deleteAttendanceLog() throws SQLException {

        if (!"ADMIN".equalsIgnoreCase(UserSession.getCurrentUser().getRole())) {
            System.out.println("Access denied.");
            return;
        }

        int id = attendanceLogView.readAttendanceLogId();

        attendanceLogService.deleteAttendanceLog(id);

        System.out.println("Attendance log deleted successfully.");
    }
}