package com.joysistvi.ursa.view;

import com.joysistvi.ursa.model.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class ScheduleView {

    private final Scanner scanner = new Scanner(System.in);

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

    public Schedule addNewSchedule() {

        System.out.println("\n===== ADD SCHEDULE =====");

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

        System.out.println("----------------------------");
        System.out.println("ID           : " + schedule.getId());
        System.out.println("Course ID    : " + schedule.getCourseId());
        System.out.println("Instructor   : " + schedule.getInstructorName());
        System.out.println("Date         : " + schedule.getDate());
        System.out.println("Start Time   : " + schedule.getStartTime());
        System.out.println("End Time     : " + schedule.getEndTime());
        System.out.println("----------------------------");
    }

    public void displaySchedules(List<Schedule> schedules) {

        if (schedules.isEmpty()) {
            System.out.println("No schedules found.");
            return;
        }

        System.out.println("\n===== SCHEDULE LIST =====");

        for (Schedule schedule : schedules) {
            displaySchedule(schedule);
        }
    }
}