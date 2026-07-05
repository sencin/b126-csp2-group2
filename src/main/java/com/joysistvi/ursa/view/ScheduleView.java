package com.joysistvi.ursa.view;

import com.joysistvi.ursa.model.Course;
import com.joysistvi.ursa.model.Schedule;

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
        System.out.println("3. View By ID");
        System.out.println("4. Update");
        System.out.println("5. Delete");
        System.out.println("0. Back");
        System.out.print("Choice: ");

        return Integer.parseInt(scanner.nextLine());
    }

    public Schedule addNewSchedule(List<Course> courses) {

        System.out.println("\n===== ADD SCHEDULE =====");
        courseView.displayCourses(courses);
        System.out.print("Course ID: ");
        int courseId = Integer.parseInt(scanner.nextLine());

        System.out.print("Instructor Name: ");
        String instructorName = scanner.nextLine();

        System.out.print("Date (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        System.out.print("Start Time (HH:mm): ");
        LocalTime startTime = LocalTime.parse(scanner.nextLine());

        System.out.print("End Time (HH:mm): ");
        LocalTime endTime = LocalTime.parse(scanner.nextLine());

        return new Schedule(
                0,
                courseId,
                instructorName,
                date,
                startTime,
                endTime
        );
    }

    public Schedule updateSchedule() {

        System.out.println("\n===== UPDATE SCHEDULE =====");

        System.out.print("Schedule ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Course ID: ");
        int courseId = Integer.parseInt(scanner.nextLine());

        System.out.print("Instructor Name: ");
        String instructorName = scanner.nextLine();

        System.out.print("Date (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        System.out.print("Start Time (HH:mm): ");
        LocalTime startTime = LocalTime.parse(scanner.nextLine());

        System.out.print("End Time (HH:mm): ");
        LocalTime endTime = LocalTime.parse(scanner.nextLine());

        return new Schedule(
                id,
                courseId,
                instructorName,
                date,
                startTime,
                endTime
        );
    }

    public int readScheduleId() {
        System.out.print("Enter Schedule ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void displaySchedule(Schedule schedule) {
        int idW = 5, crsW = 8, instW = 15, dateW = 10, startW = 8, endW = 8;


        String border = "+" + repeat("-", idW + 2) + "+" +
                repeat("-", crsW + 2) + "+" +
                repeat("-", instW + 2) + "+" +
                repeat("-", dateW + 2) + "+" +
                repeat("-", startW + 2) + "+" +
                repeat("-", endW + 2) + "+";


        String rowFormat = "| %-" + idW + "s | %-" + crsW + "s | %-" + instW + "s | %-" + dateW + "s | %-" + startW + "s | %-" + endW + "s |%n";

        System.out.println(border);
        System.out.format(rowFormat, "ID", "Course ID", "Instructor", "Date", "Start", "End");
        System.out.println(border);
        System.out.format(rowFormat,
                schedule.getId(),
                schedule.getCourseId(),
                schedule.getInstructorName(),
                schedule.getDate(),
                schedule.getStartTime(),
                schedule.getEndTime()
        );
        System.out.println(border);
    }
    public void displaySchedules(List<Schedule> schedules) {

        if (schedules == null || schedules.isEmpty()) {
            System.out.println("No schedules found.");
            return;
        }

        int idW = 5;
        int crsW = 40;
        int instW = 20;
        int dateW = 12;
        int startW = 8;
        int endW = 8;

        String border = "+" + repeat("-", idW + 2) + "+" +
                repeat("-", crsW + 2) + "+" +
                repeat("-", instW + 2) + "+" +
                repeat("-", dateW + 2) + "+" +
                repeat("-", startW + 2) + "+" +
                repeat("-", endW + 2) + "+";

        String rowFormat = "| %-" + idW + "s | %-" + crsW + "s | %-" + instW + "s | %-" + dateW + "s | %-" + startW + "s | %-" + endW + "s |%n";

        System.out.println("\n===== SCHEDULE LIST =====");
        System.out.println(border);
        System.out.format(rowFormat, "ID", "Course", "Instructor", "Date", "Start", "End");
        System.out.println(border);

        for (Schedule schedule : schedules) {
            System.out.format(rowFormat,
                    schedule.getId(),
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