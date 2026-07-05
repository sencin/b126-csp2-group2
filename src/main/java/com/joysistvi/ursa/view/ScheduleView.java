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

        int courseId = readValidCourseId(courses);
        int teacherId = UserSession.getCurrentUser().getId();
        LocalDate date = readValidDate();
        LocalTime startTime = readValidTime("Start Time (HH:mm): ");
        LocalTime endTime = readValidEndTime(startTime);

        return new Schedule(0, courseId, teacherId, date, startTime, endTime);
    }

    private int readValidCourseId(List<Course> courses) {
        while (true) {
            System.out.print("Course ID: ");
            try {
                int id = Integer.parseInt(scanner.nextLine().trim());
                // Fast stream check to verify ID exists
                if (courses.stream().anyMatch(c -> c.getId() == id)) return id;
                System.out.println("Error: That Course ID does not exist in the list.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
    }

    // Helper 2: Date Validation
    private LocalDate readValidDate() {
        while (true) {
            System.out.print("Date (yyyy-MM-dd): ");
            try {
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Error: Invalid date format. Use yyyy-MM-dd.");
            }
        }
    }

    // Helper 3: General Time Validation
    private LocalTime readValidTime(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return LocalTime.parse(scanner.nextLine().trim());
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Error: Invalid time format. Use HH:mm.");
            }
        }
    }

    private LocalTime readValidEndTime(LocalTime startTime) {
        while (true) {
            LocalTime endTime = readValidTime("End Time (HH:mm): ");
            if (endTime.isAfter(startTime)) return endTime;
            System.out.println("Error: End time must be strictly AFTER the start time.");
        }
    }

    public Schedule updateSchedule(List<Schedule> existingSchedule, List<Course> courses) {
        System.out.println("\n===== UPDATE SCHEDULE =====");
        displaySchedules(existingSchedule);
        int id = readMandatoryId();

        if (id == -1) return null;

        courseView.displayCourses(courses);
        int courseId = readOptionalCourseId(courses);
        int teacherId = UserSession.getCurrentUser().getId();

        LocalDate date = readOptionalDate();

        // 4. Start & End Times (Optional)
        LocalTime startTime = readOptionalStartTime();
        LocalTime endTime = readOptionalEndTime(startTime);

        return new Schedule(id, courseId, teacherId, date, startTime, endTime);
    }

// --- Adapting your helpers for Optional Inputs ---

    private int readMandatoryId() {
        while (true) {
            System.out.print("Schedule ID to update: ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Error: Schedule ID is required.");
                return -1;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
    }

    private int readOptionalCourseId(List<Course> courses) {
        while (true) {
            System.out.print("Course ID (Press Enter to keep existing): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) return 0;

            try {
                int id = Integer.parseInt(input);
                if (courses.stream().anyMatch(c -> c.getId() == id)) return id;
                System.out.println("Error: That Course ID does not exist in the list.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
    }

    private LocalDate readOptionalDate() {
        while (true) {
            System.out.print("Date (yyyy-MM-dd) (Press Enter to keep existing): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) return null;

            try {
                return LocalDate.parse(input);
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Error: Invalid date format. Use yyyy-MM-dd.");
            }
        }
    }

    private LocalTime readOptionalStartTime() {
        while (true) {
            System.out.print("Start Time (HH:mm) (Press Enter to keep existing): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) return null;

            try {
                return LocalTime.parse(input);
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Error: Invalid time format. Use HH:mm.");
            }
        }
    }

    private LocalTime readOptionalEndTime(LocalTime startTime) {
        while (true) {
            System.out.print("End Time (HH:mm) (Press Enter to keep existing): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) return null;

            try {
                LocalTime endTime = LocalTime.parse(input);
                if (startTime != null && !endTime.isAfter(startTime)) {
                    System.out.println("Error: End time must be strictly AFTER the updated start time.");
                    continue;
                }
                return endTime;
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Error: Invalid time format. Use HH:mm.");
            }
        }
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
                    schedule.getCourseId(),
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