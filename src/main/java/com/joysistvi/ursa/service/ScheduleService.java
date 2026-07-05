package com.joysistvi.ursa.service;

import com.joysistvi.ursa.model.Schedule;
import com.joysistvi.ursa.model.User;
import com.joysistvi.ursa.repository.ScheduleRepository;
import com.joysistvi.ursa.repository.impl.ScheduleRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService() {
        this.scheduleRepository = new ScheduleRepositoryImpl();
    }

    public void addSchedule(Schedule schedule) throws SQLException {
        scheduleRepository.addSchedule(schedule);
    }

    public List<Schedule> getAllSchedules() throws SQLException {
        return scheduleRepository.getAllSchedules();
    }

    public Schedule getScheduleById(int id) throws SQLException {
        return scheduleRepository.getScheduleById(id);
    }

    public List<Schedule> getEnrolledSchedulesByStudentId(int id) throws SQLException{
        return scheduleRepository.getEnrolledSchedulesByStudentId(id);
    }

    public boolean updateSchedule(Schedule schedule) throws SQLException {
        User currentUser = UserSession.getCurrentUser();

        if (currentUser == null) {
            System.out.println("Error: No user is logged in.");
            return false;
        }

        boolean isAdmin = "admin".equalsIgnoreCase(currentUser.getRole());

        if (!isAdmin) {
            Schedule existingSchedule = scheduleRepository.getScheduleById(schedule.getId());

            if (existingSchedule == null) {
                System.out.println("Error: Schedule not found.");
                return false;
            }

            if (existingSchedule.getTeacherId() != currentUser.getId()) {
                System.out.println("Access Denied: You can only update schedules you created.");
                return false;
            }
        }
        scheduleRepository.updateSchedule(schedule);
        return true;
    }

    public boolean deleteSchedule(int id) throws SQLException {
        User currentUser = UserSession.getCurrentUser();

        if (currentUser == null) {
            System.out.println("Error: No user is logged in.");
            return false;
        }

        boolean isAdmin = "admin".equalsIgnoreCase(currentUser.getRole());

        if (!isAdmin) {
            Schedule schedule = scheduleRepository.getScheduleById(id);

            if (schedule == null) {
                System.out.println("Error: Schedule not found.");
                return false;
            }

            if (schedule.getTeacherId() != currentUser.getId()) {
                System.out.println("Access Denied: You can only delete schedules you created.");
                return false;
            }
        }

        scheduleRepository.deleteSchedule(id);
        return true;
    }
    public List<Schedule> getSchedulesByTeacher(int teacherId) throws SQLException {
        return scheduleRepository.getSchedulesByTeacherId(teacherId);
    }
}