package com.joysistvi.ursa.repository;

import com.joysistvi.ursa.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentRepository {
    void addStudent(Student student) throws SQLException;
    List<Student> getAllStudents() throws SQLException;
    Student getStudentById(int id) throws SQLException;
    void updateStudent(Student student) throws SQLException;
    void deleteStudent(int id) throws SQLException;
}