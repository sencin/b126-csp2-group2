package com.joysistvi.ursa.controller;

import com.joysistvi.ursa.model.AttendanceLog;
import com.joysistvi.ursa.model.Schedule;
import com.joysistvi.ursa.service.AttendanceLogService;
import com.joysistvi.ursa.service.ScheduleService;
import com.joysistvi.ursa.service.UserSession;
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

            int choice = attendanceLogView.menu();

            try {
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
        int studentId = UserSession.getCurrentUser().getId();
        List<Schedule> schedules = scheduleService.getEnrolledSchedulesByStudentId(studentId);
        AttendanceLog attendanceLog = attendanceLogView.addNewAttendanceLog(schedules);
        attendanceLogService.addAttendanceLog(attendanceLog);

        System.out.println("Attendance log added successfully.");
    }

    private void viewAttendanceLogsBySchedule() throws SQLException {
        int studentId = UserSession.getCurrentUser().getId();
        List<Schedule> schedules = scheduleService.getEnrolledSchedulesByStudentId(studentId);

        int scheduleId = attendanceLogView.readScheduleId(schedules);

        List<AttendanceLog> attendanceLogs = attendanceLogService.getAttendanceLogsByScheduleId(scheduleId);

        attendanceLogView.displayAttendanceLogs(attendanceLogs);
    }

    private void viewAttendanceLogById() throws SQLException {

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