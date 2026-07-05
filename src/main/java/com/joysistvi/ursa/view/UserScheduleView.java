package com.joysistvi.ursa.view;

import com.joysistvi.ursa.model.Schedule;
import com.joysistvi.ursa.model.StudentSchedule;
import com.joysistvi.ursa.service.UserSession;

import java.util.List;
import java.util.Scanner;

import static com.joysistvi.ursa.utils.ConsoleTableUtils.repeat;

public class UserScheduleView {

    private final Scanner scanner = new Scanner(System.in);
    private  final ScheduleView scheduleView  = new ScheduleView();

    public int menu() {
        System.out.println("\n===== STUDENT SCHEDULES =====");

        String role = UserSession.getCurrentUser().getRole();

        if ("STUDENT".equalsIgnoreCase(role)) {
            System.out.println("1. Add");
            System.out.println("2. View Schedules");

        } else if ("ADMIN".equalsIgnoreCase(role)) {
            System.out.println("1. Add");
            System.out.println("2. View All Schedules");
            System.out.println("3. View By ID");
            System.out.println("4. Update");
            System.out.println("5. Delete");

        }

        System.out.println("0. Back");
        System.out.print("Choice: ");

        return Integer.parseInt(scanner.nextLine());
    }
    public StudentSchedule addNewStudentSchedule(List<Schedule> schedules) {

        System.out.println("\n===== ADD SCHEDULE =====");
        int studentId = UserSession.getCurrentUser().getId();
        scheduleView.displaySchedules(schedules);
        System.out.print("Schedule ID: ");
        int scheduleId = Integer.parseInt(scanner.nextLine());

        System.out.print("Academic Year: ");
        String academicYear = scanner.nextLine();

        System.out.print("Semester: ");
        String semester = scanner.nextLine();

        return new StudentSchedule(
                0,
                studentId, // Pass the session ID here
                scheduleId,
                academicYear,
                semester
        );
    }

    public StudentSchedule updateStudentSchedule() {

        System.out.println("\n===== UPDATE STUDENT SCHEDULE =====");

        System.out.print("Student Schedule ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        System.out.print("Schedule ID: ");
        int scheduleId = Integer.parseInt(scanner.nextLine());

        System.out.print("Academic Year: ");
        String academicYear = scanner.nextLine();

        System.out.print("Semester: ");
        String semester = scanner.nextLine();

        return new StudentSchedule(
                id,
                studentId,
                scheduleId,
                academicYear,
                semester
        );
    }

    public int readStudentId() {
        System.out.print("Enter Student ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int readStudentScheduleId() {
        System.out.print("Enter Student Schedule ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void displayStudentSchedule(StudentSchedule studentSchedule) {

        int idW = 5, stuW = 10, schedW = 10, acadW = 12, semW = 8;

        String border = "+" + repeat("-", idW + 2) + "+" +
                repeat("-", stuW + 2) + "+" +
                repeat("-", schedW + 2) + "+" +
                repeat("-", acadW + 2) + "+" +
                repeat("-", semW + 2) + "+";

        String rowFormat = "| %-" + idW + "s | %-" + stuW + "s | %-" + schedW + "s | %-" + acadW + "s | %-" + semW + "s |%n";

        System.out.println(border);
        System.out.format(rowFormat, "ID", "Student ID", "Schedule", "Acad Year", "Sem");
        System.out.println(border);
        System.out.format(rowFormat,
                studentSchedule.getId(),
                studentSchedule.getStudentId(),
                studentSchedule.getScheduleId(),
                studentSchedule.getAcademicYear(),
                studentSchedule.getSemester()
        );
        System.out.println(border);
    }
    public void displayStudentSchedules(List<StudentSchedule> studentSchedules) {

        if (studentSchedules.isEmpty()) {
            System.out.println("No student schedules found.");
            return;
        }

        System.out.println("\n===== STUDENT SCHEDULE LIST =====");

        // Adjusted column widths to accommodate names and titles
        int idW = 4, crsCdW = 10, crsTitW = 30, teachW = 18, dateW = 10, timeW = 13, termW = 25;

        String border = "+" + repeat("-", idW + 2) + "+" +
                repeat("-", crsCdW + 2) + "+" +
                repeat("-", crsTitW + 2) + "+" +
                repeat("-", teachW + 2) + "+" +
                repeat("-", dateW + 2) + "+" +
                repeat("-", timeW + 2) + "+" +
                repeat("-", termW + 2) + "+";

        String rowFormat = "| %-" + idW + "s | %-" + crsCdW + "s | %-" + crsTitW + "s | %-" + teachW + "s | %-" + dateW + "s | %-" + timeW + "s | %-" + termW + "s |%n";

        // Print Header
        System.out.println(border);
        System.out.format(rowFormat, "ID", "Code", "Course Title", "Teacher", "Date", "Time", "Term");
        System.out.println(border);

        // Print Rows
        for (StudentSchedule ss : studentSchedules) {

            // Format the term (e.g., "2023-2024 / 1st") to fit in one column
            String termStr = ss.getAcademicYear() + " " + ss.getSemester();
            if (termStr.length() > termW) termStr = termStr.substring(0, termW); // Truncate if too long

            // Format course title to not break the table if it's too long
            String titleStr = ss.getCourseTitle();
            if (titleStr.length() > crsTitW) titleStr = titleStr.substring(0, crsTitW - 3) + "...";

            // Combine start and end time (e.g., "08:00 - 09:30")
            String timeStr = ss.getStartTime() + "-" + ss.getEndTime();

            System.out.format(rowFormat,
                    ss.getId(),
                    ss.getCourseCode(),
                    titleStr,
                    ss.getTeacherName(),
                    ss.getDate(),
                    timeStr,
                    termStr
            );
        }

        System.out.println(border);
    }
}