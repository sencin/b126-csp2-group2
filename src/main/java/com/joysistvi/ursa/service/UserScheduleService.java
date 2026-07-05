package com.joysistvi.ursa.service;

import com.joysistvi.ursa.model.StudentSchedule;
import com.joysistvi.ursa.repository.UserScheduleRepository;
import com.joysistvi.ursa.repository.impl.UserScheduleRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class UserScheduleService {

    private final UserScheduleRepository userScheduleRepository;

    public UserScheduleService() {
        this.userScheduleRepository = new UserScheduleRepositoryImpl();
    }

    public void addStudentSchedule(StudentSchedule studentSchedule) throws SQLException {
        userScheduleRepository.addStudentSchedule(studentSchedule);
    }

    public List<StudentSchedule> getStudentSchedulesByStudentId(int studentId) throws SQLException {
        return userScheduleRepository.getStudentSchedulesByStudentId(studentId);
    }

    public StudentSchedule getStudentScheduleById(int id) throws SQLException {
        return userScheduleRepository.getStudentScheduleById(id);
    }

    public void updateStudentSchedule(StudentSchedule studentSchedule) throws SQLException {
        userScheduleRepository.updateStudentSchedule(studentSchedule);
    }

    public void deleteStudentSchedule(int id) throws SQLException {
        userScheduleRepository.deleteStudentSchedule(id);
    }
}