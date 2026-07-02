package com.joysistvi.ursa.repository;

import com.joysistvi.ursa.model.StudentSchedule;

import java.sql.SQLException;
import java.util.List;

public interface StudentScheduleRepository {

    void addStudentSchedule(StudentSchedule studentSchedule) throws SQLException;

    List<StudentSchedule> getStudentSchedulesByStudentId(int studentId) throws SQLException;

    StudentSchedule getStudentScheduleById(int id) throws SQLException;

    void updateStudentSchedule(StudentSchedule studentSchedule) throws SQLException;

    void deleteStudentSchedule(int id) throws SQLException;
}