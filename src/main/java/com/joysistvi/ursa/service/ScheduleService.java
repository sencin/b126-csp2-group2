package com.joysistvi.ursa.service;

import com.joysistvi.ursa.model.Schedule;
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

    public void updateSchedule(Schedule schedule) throws SQLException {
        scheduleRepository.updateSchedule(schedule);
    }

    public void deleteSchedule(int id) throws SQLException {
        scheduleRepository.deleteSchedule(id);
    }
}