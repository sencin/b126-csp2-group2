package com.joysistvi.ursa.view;

import com.joysistvi.ursa.model.AttendanceLog;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class AttendanceLogView {

    private final Scanner scanner = new Scanner(System.in);

    public int menu() {
        System.out.println("\n===== ATTENDANCE LOGS =====");
        System.out.println("1. Add");
        System.out.println("2. View By Schedule");
        System.out.println("3. View By ID");
        System.out.println("4. Update");
        System.out.println("5. Delete");
        System.out.println("0. Back");
        System.out.print("Choice: ");

        return Integer.parseInt(scanner.nextLine());
    }

    // CREATE
    public AttendanceLog addNewAttendanceLog() {

        System.out.println("\n===== ADD ATTENDANCE LOG =====");

        System.out.print("Student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        System.out.print("Schedule ID: ");
        int scheduleId = Integer.parseInt(scanner.nextLine());

        System.out.print("Action: ");
        String action = scanner.nextLine();

        return new AttendanceLog(
                0,
                studentId,
                scheduleId,
                LocalDateTime.now(),
                action
        );
    }

    // UPDATE
    public AttendanceLog updateAttendanceLog() {

        System.out.println("\n===== UPDATE ATTENDANCE LOG =====");

        System.out.print("Log ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        System.out.print("Schedule ID: ");
        int scheduleId = Integer.parseInt(scanner.nextLine());

        System.out.print("Timestamp (yyyy-MM-ddTHH:mm:ss): ");
        LocalDateTime timestamp = LocalDateTime.parse(scanner.nextLine());

        System.out.print("Action: ");
        String action = scanner.nextLine();

        return new AttendanceLog(
                id,
                studentId,
                scheduleId,
                timestamp,
                action
        );
    }

    public int readAttendanceLogId() {
        System.out.print("Enter Attendance Log ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int readScheduleId() {
        System.out.print("Enter Schedule ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void displayAttendanceLog(AttendanceLog attendanceLog) {

        System.out.println("----------------------------");
        System.out.println("ID        : " + attendanceLog.getId());
        System.out.println("Student ID: " + attendanceLog.getStudentId());
        System.out.println("Schedule  : " + attendanceLog.getScheduleId());
        System.out.println("Timestamp : " + attendanceLog.getTimestamp());
        System.out.println("Action    : " + attendanceLog.getAction());
        System.out.println("----------------------------");
    }

    public void displayAttendanceLogs(List<AttendanceLog> attendanceLogs) {

        if (attendanceLogs.isEmpty()) {
            System.out.println("No attendance logs found.");
            return;
        }

        System.out.println("\n===== ATTENDANCE LOG LIST =====");

        for (AttendanceLog attendanceLog : attendanceLogs) {
            displayAttendanceLog(attendanceLog);
        }
    }
}