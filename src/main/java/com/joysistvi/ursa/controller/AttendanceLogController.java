package com.joysistvi.ursa.controller;

import com.joysistvi.ursa.model.AttendanceLog;
import com.joysistvi.ursa.service.AttendanceLogService;
import com.joysistvi.ursa.view.AttendanceLogView;

import java.sql.SQLException;
import java.util.List;

public class AttendanceLogController {

    private final AttendanceLogService attendanceLogService;
    private final AttendanceLogView attendanceLogView;

    public AttendanceLogController(AttendanceLogService attendanceLogService,
                                   AttendanceLogView attendanceLogView) {
        this.attendanceLogService = attendanceLogService;
        this.attendanceLogView = attendanceLogView;
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

        AttendanceLog attendanceLog = attendanceLogView.addNewAttendanceLog();

        attendanceLogService.addAttendanceLog(attendanceLog);

        System.out.println("Attendance log added successfully.");
    }

    private void viewAttendanceLogsBySchedule() throws SQLException {

        int scheduleId = attendanceLogView.readScheduleId();

        List<AttendanceLog> attendanceLogs =
                attendanceLogService.getAttendanceLogsByScheduleId(scheduleId);

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

        AttendanceLog attendanceLog =
                attendanceLogView.updateAttendanceLog();

        attendanceLogService.updateAttendanceLog(attendanceLog);

        System.out.println("Attendance log updated successfully.");
    }

    private void deleteAttendanceLog() throws SQLException {

        int id = attendanceLogView.readAttendanceLogId();

        attendanceLogService.deleteAttendanceLog(id);

        System.out.println("Attendance log deleted successfully.");
    }
}