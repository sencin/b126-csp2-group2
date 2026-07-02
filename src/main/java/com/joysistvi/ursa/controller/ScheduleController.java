package com.joysistvi.ursa.controller;

import com.joysistvi.ursa.model.Schedule;
import com.joysistvi.ursa.service.ScheduleService;
import com.joysistvi.ursa.view.ScheduleView;

import java.sql.SQLException;
import java.util.List;

public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ScheduleView scheduleView;

    public ScheduleController(ScheduleService scheduleService,
                              ScheduleView scheduleView) {
        this.scheduleService = scheduleService;
        this.scheduleView = scheduleView;
    }

    public void start() {
        boolean running = true;

        while (running) {

            int choice = scheduleView.menu();

            try {
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

        Schedule schedule = scheduleView.addNewSchedule();

        scheduleService.addSchedule(schedule);

        System.out.println("Schedule added successfully.");
    }

    private void viewAllSchedules() throws SQLException {

        List<Schedule> schedules = scheduleService.getAllSchedules();

        scheduleView.displaySchedules(schedules);
    }

    private void viewScheduleById() throws SQLException {

        int id = scheduleView.readScheduleId();

        Schedule schedule = scheduleService.getScheduleById(id);

        if (schedule == null) {
            System.out.println("Schedule not found.");
            return;
        }

        scheduleView.displaySchedule(schedule);
    }

    private void updateSchedule() throws SQLException {

        Schedule schedule = scheduleView.updateSchedule();

        scheduleService.updateSchedule(schedule);

        System.out.println("Schedule updated successfully.");
    }

    private void deleteSchedule() throws SQLException {

        int id = scheduleView.readScheduleId();

        scheduleService.deleteSchedule(id);

        System.out.println("Schedule deleted successfully.");
    }
}