package com.joysistvi.ursa.view;

import com.joysistvi.ursa.model.Course;
import com.joysistvi.ursa.model.Schedule;
import com.joysistvi.ursa.service.UserSession;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import static com.joysistvi.ursa.utils.ConsoleTableUtils.repeat;

public class ScheduleView {

    private final Scanner scanner = new Scanner(System.in);
    private final UserView userView = new UserView();
    private final CourseView courseView = new CourseView();


    public int menu() {
        System.out.println("\n===== SCHEDULES =====");
        System.out.println("1. Add");
        System.out.println("2. View All");
        System.out.println("3. View My Schedules");
        System.out.println("4. Update");
        System.out.println("5. Delete");
        System.out.println("0. Back");
        System.out.print("Choice: ");

        return Integer.parseInt(scanner.nextLine());
    }

    public Schedule addNewSchedule(List<Course> courses) {
        System.out.println("\n===== Available Courses =====");
        courseView.displayCourses(courses);
        System.out.print("Course ID: ");
        int courseId = Integer.parseInt(scanner.nextLine());

        int teacherId = UserSession.getCurrentUser().getId();

        System.out.print("Date (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        System.out.print("Start Time (HH:mm): ");
        LocalTime startTime = LocalTime.parse(scanner.nextLine());

        System.out.print("End Time (HH:mm): ");
        LocalTime endTime = LocalTime.parse(scanner.nextLine());
        // Only pass the IDs
        return new Schedule(
                0,
                courseId,
                teacherId,
                date,
                startTime,
                endTime
        );
    }

    public Schedule updateSchedule() {
        System.out.println("\n===== UPDATE SCHEDULE =====");

        System.out.print("Schedule ID to update: ");
        String idInput = scanner.nextLine().trim();
        if (idInput.isEmpty()) {
            System.out.println("Error: Schedule ID is required.");
            return null;
        }
        int id = Integer.parseInt(idInput);

        System.out.print("Course ID (Press Enter to keep existing): ");
        String courseInput = scanner.nextLine().trim();
        int courseId = courseInput.isEmpty() ? 0 : Integer.parseInt(courseInput);

        int teacherId = UserSession.getCurrentUser().getId();

        System.out.print("Date (yyyy-MM-dd) (Press Enter to keep existing): ");
        String dateInput = scanner.nextLine().trim();
        LocalDate date = dateInput.isEmpty() ? null : LocalDate.parse(dateInput);

        System.out.print("Start Time (HH:mm) (Press Enter to keep existing): ");
        String startInput = scanner.nextLine().trim();
        LocalTime startTime = startInput.isEmpty() ? null : LocalTime.parse(startInput);

        System.out.print("End Time (HH:mm) (Press Enter to keep existing): ");
        String endInput = scanner.nextLine().trim();
        LocalTime endTime = endInput.isEmpty() ? null : LocalTime.parse(endInput);

        return new Schedule(
                id,
                courseId,
                teacherId,
                date,
                startTime,
                endTime
        );
    }
    public int readScheduleId() {
        System.out.print("Enter Schedule ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void displaySchedules(List<Schedule> schedules) {

        if (schedules == null || schedules.isEmpty()) {
            System.out.println("No schedules found.");
            return;
        }

        int idW = 5;
        int crsIdW = 9;
        int crsW = 30;
        int instW = 20;
        int dateW = 12;
        int startW = 8;
        int endW = 8;

        // Added crsIdW layout spacer to the border string
        String border = "+" + repeat("-", idW + 2) + "+" +
                repeat("-", crsIdW + 2) + "+" +
                repeat("-", crsW + 2) + "+" +
                repeat("-", instW + 2) + "+" +
                repeat("-", dateW + 2) + "+" +
                repeat("-", startW + 2) + "+" +
                repeat("-", endW + 2) + "+";

        // Inserted %-crsIdWs specifier into the row template formatting
        String rowFormat = "| %-" + idW + "s | %-" + crsIdW + "s | %-" + crsW + "s | %-" + instW + "s | %-" + dateW + "s | %-" + startW + "s | %-" + endW + "s |%n";

        System.out.println("\n===== SCHEDULE LIST =====");
        System.out.println(border);
        // Added headers matching the parameters mapping
        System.out.format(rowFormat, "ID", "Course ID", "Course", "Instructor", "Date", "Start", "End");
        System.out.println(border);

        for (Schedule schedule : schedules) {
            System.out.format(rowFormat,
                    schedule.getId(),
                    schedule.getCourseId(), // Injected Course ID logic
                    schedule.getCourseTitle(),
                    schedule.getInstructorName(),
                    schedule.getDate(),
                    schedule.getStartTime(),
                    schedule.getEndTime()
            );
        }

        System.out.println(border);
    }
}