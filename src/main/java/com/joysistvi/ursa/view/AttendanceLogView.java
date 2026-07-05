package com.joysistvi.ursa.view;

import com.joysistvi.ursa.model.AttendanceLog;
import com.joysistvi.ursa.model.Schedule;
import com.joysistvi.ursa.service.UserSession;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static com.joysistvi.ursa.utils.ConsoleTableUtils.repeat;

public class AttendanceLogView {

    private final Scanner scanner = new Scanner(System.in);
    private  final ScheduleView scheduleView = new ScheduleView();

    public int menu() {

        String role = UserSession.getCurrentUser().getRole().toUpperCase();

        if ("STUDENT".equals(role)) {
            System.out.println("1. Time In/Out");
            System.out.println("2. View By Schedule");
            System.out.println("3. View By ID");
            System.out.println("0. Back");
        } else if ("TEACHER".equals(role)) {
            System.out.println("2. View By Schedule");
            System.out.println("3. View By ID");
            System.out.println("0. Back");
        } else { // ADMIN
            System.out.println("1. Add");
            System.out.println("2. View By Schedule");
            System.out.println("3. View By ID");
            System.out.println("4. Update");
            System.out.println("5. Delete");
            System.out.println("0. Back");
        }

        System.out.print("Choice: ");
        return Integer.parseInt(scanner.nextLine());
    }

    // CREATE
    public AttendanceLog addNewAttendanceLog(List<Schedule> schedules) {
        System.out.println("\n===== ADD ATTENDANCE LOG =====");
        int studentId = UserSession.getCurrentUser().getId();

        scheduleView.displaySchedules(schedules);
        System.out.print("Schedule ID: ");
        int scheduleId = Integer.parseInt(scanner.nextLine());

        System.out.print("Action (e.g., IN, OUT): ");
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

    public int readScheduleId(List<Schedule> schedules) {
        scheduleView.displaySchedules(schedules);
        System.out.print("Enter Schedule ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void displayAttendanceLog(AttendanceLog attendanceLog) {

        int idW = 5, stuW = 10, schedW = 10, timeW = 20, actW = 8;

        String border = "+" + repeat("-", idW + 2) + "+" +
                repeat("-", stuW + 2) + "+" +
                repeat("-", schedW + 2) + "+" +
                repeat("-", timeW + 2) + "+" +
                repeat("-", actW + 2) + "+";

        String rowFormat = "| %-" + idW + "s | %-" + stuW + "s | %-" + schedW + "s | %-" + timeW + "s | %-" + actW + "s |%n";

        System.out.println(border);
        System.out.format(rowFormat, "ID", "Student ID", "Schedule", "Timestamp", "Action");
        System.out.println(border);
        System.out.format(rowFormat,
                attendanceLog.getId(),
                attendanceLog.getStudentId(),
                attendanceLog.getScheduleId(),
                attendanceLog.getTimestamp(),
                attendanceLog.getAction()
        );
        System.out.println(border);
    }

    public void displayAttendanceLogs(List<AttendanceLog> attendanceLogs) {
        if (attendanceLogs == null || attendanceLogs.isEmpty()) {
            System.out.println("No attendance logs found.");
            return;
        }

        System.out.println("\n===== ATTENDANCE LOG LIST =====");

        // Adjusted widths to fit 6 columns
        int idW = 4, studW = 15, courseW = 15, teacherW = 15, timeW = 16, actW = 8;

        String border = "+" + repeat("-", idW + 2) + "+" + repeat("-", studW + 2) + "+" +
                repeat("-", courseW + 2) + "+" + repeat("-", teacherW + 2) + "+" +
                repeat("-", timeW + 2) + "+" + repeat("-", actW + 2) + "+";

        String rowFormat = "| %-" + idW + "s | %-" + studW + "s | %-" + courseW + "s | %-" + teacherW + "s | %-" + timeW + "s | %-" + actW + "s |%n";

        System.out.println(border);
        System.out.format(rowFormat, "ID", "Student", "Course", "Teacher", "Time", "Action");
        System.out.println(border);

        for (AttendanceLog log : attendanceLogs) {
            String timeStr = (log.getTimestamp() != null) ? log.getTimestamp().toString().replace("T", " ").substring(0, 16) : "N/A";

            System.out.format(rowFormat,
                    log.getId(),
                    truncate(log.getStudentName(), studW),
                    truncate(log.getCourseTitle(), courseW),
                    truncate(log.getTeacherName(), teacherW),
                    timeStr,
                    log.getAction()
            );
        }
        System.out.println(border);
    }
    private String truncate(String value, int length) {
        if (value == null) return "";
        if (value.length() > length) {
            return value.substring(0, length - 3) + "...";
        }
        return value;
    }
}