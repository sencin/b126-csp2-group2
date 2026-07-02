package com.joysistvi.ursa.service;

import com.joysistvi.ursa.model.StudentSchedule;
import com.joysistvi.ursa.repository.StudentScheduleRepository;
import com.joysistvi.ursa.repository.impl.StudentScheduleRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class StudentScheduleService {

    private final StudentScheduleRepository studentScheduleRepository;

    public StudentScheduleService() {
        this.studentScheduleRepository = new StudentScheduleRepositoryImpl();
    }

    public void addStudentSchedule(StudentSchedule studentSchedule) throws SQLException {
        studentScheduleRepository.addStudentSchedule(studentSchedule);
    }

    public List<StudentSchedule> getStudentSchedulesByStudentId(int studentId) throws SQLException {
        return studentScheduleRepository.getStudentSchedulesByStudentId(studentId);
    }

    public StudentSchedule getStudentScheduleById(int id) throws SQLException {
        return studentScheduleRepository.getStudentScheduleById(id);
    }

    public void updateStudentSchedule(StudentSchedule studentSchedule) throws SQLException {
        studentScheduleRepository.updateStudentSchedule(studentSchedule);
    }

    public void deleteStudentSchedule(int id) throws SQLException {
        studentScheduleRepository.deleteStudentSchedule(id);
    }
}