package com.joysistvi.ursa.controller;

import com.joysistvi.ursa.model.Course;
import com.joysistvi.ursa.model.Schedule;
import com.joysistvi.ursa.model.User;
import com.joysistvi.ursa.service.CourseService;
import com.joysistvi.ursa.service.ScheduleService;
import com.joysistvi.ursa.service.UserService;
import com.joysistvi.ursa.service.UserSession;
import com.joysistvi.ursa.view.ScheduleView;

import java.sql.SQLException;
import java.util.List;

public class ScheduleController {

    private final ScheduleService scheduleService;
    private final UserService userService;
    private final ScheduleView scheduleView;
    private  final CourseService courseService;

    public ScheduleController(ScheduleService scheduleService, ScheduleView scheduleView, UserService userService, CourseService courseService) {
        this.scheduleService = scheduleService;
        this.scheduleView = scheduleView;
        this.userService = userService;
        this.courseService = courseService;
    }

    public void start() {
        boolean running = true;

        while (running) {
            try {
                int choice = scheduleView.menu();
                switch (choice) {
                    case 1:
                        addSchedule();
                        break;

                    case 2:
                        viewAllSchedules();
                        break;

                    case 3:
                        viewScheduleById();
                        break;

                    case 4:
                        updateSchedule();
                        break;

                    case 5:
                        deleteSchedule();
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

    private void addSchedule() throws SQLException {
        List<Course> courses = courseService.getAllCourses();
        Schedule schedule = scheduleView.addNewSchedule(courses);
        scheduleService.addSchedule(schedule);

        System.out.println("Schedule added successfully.");
    }

    private void viewAllSchedules() throws SQLException {

        List<Schedule> schedules = scheduleService.getAllSchedules();

        scheduleView.displaySchedules(schedules);
    }

    private void viewScheduleById() throws SQLException {

      int id =  UserSession.getCurrentUser().getId();
        List<Schedule> schedules = scheduleService.getSchedulesByTeacher(id);

        if (schedules.isEmpty()) {
            System.out.println("No schedules found for your profile.");
            return;
        }
        scheduleView.displaySchedules(schedules);
    }

    private void updateSchedule() throws SQLException {
        User currentUser = UserSession.getCurrentUser();
        boolean isAdmin = "admin".equalsIgnoreCase(currentUser.getRole());

        List<Schedule> existingSchedules;

        if (isAdmin) {
            existingSchedules = scheduleService.getAllSchedules();
        } else {
            existingSchedules = scheduleService.getSchedulesByTeacher(currentUser.getId());
        }

        List<Course> courses = courseService.getAllCourses();
        Schedule schedule = scheduleView.updateSchedule(existingSchedules, courses);

        if (schedule == null) {
            System.out.println("Update canceled.");
            return;
        }

        boolean isUpdated = scheduleService.updateSchedule(schedule);

        if (isUpdated) {
            System.out.println("Schedule updated successfully.");
        }
    }

    private void deleteSchedule() throws SQLException {
        User currentUser = UserSession.getCurrentUser();
        boolean isAdmin = "admin".equalsIgnoreCase(currentUser.getRole());
        List<Schedule> schedules;
        if (isAdmin) {
            schedules = scheduleService.getAllSchedules();
        } else {
            schedules = scheduleService.getSchedulesByTeacher(currentUser.getId());
        }

        if (schedules == null || schedules.isEmpty()) {
            System.out.println("No schedules available to delete.");
            return;
        }
        scheduleView.displaySchedules(schedules);
        int id = scheduleView.readValidScheduleId(schedules);

        boolean isDeleted = scheduleService.deleteSchedule(id);

        if (isDeleted) {
            System.out.println("Schedule deleted successfully.");
        }
    }
}