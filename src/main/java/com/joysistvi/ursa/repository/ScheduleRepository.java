package com.joysistvi.ursa.repository;

import com.joysistvi.ursa.model.Schedule;

import java.sql.SQLException;
import java.util.List;

public interface ScheduleRepository {

    void addSchedule(Schedule schedule) throws SQLException;

    List<Schedule> getAllSchedules() throws SQLException;

    List<Schedule> getEnrolledSchedulesByStudentId(int studentId) throws SQLException;

    Schedule getScheduleById(int id) throws SQLException;

    void updateSchedule(Schedule schedule) throws SQLException;

    void deleteSchedule(int id) throws SQLException;
}