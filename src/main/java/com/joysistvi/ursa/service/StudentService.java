package com.joysistvi.ursa.service;

import com.joysistvi.ursa.model.Student;
import com.joysistvi.ursa.repository.StudentRepository;
import com.joysistvi.ursa.repository.impl.StudentRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService() {
        this.studentRepository = new StudentRepositoryImpl();
    }

    public void addStudent(Student student) throws SQLException {
        studentRepository.addStudent(student);
    }

    public List<Student> getAllStudents() throws SQLException {
        return studentRepository.getAllStudents();
    }

    public Student getStudentById(int id) throws SQLException {
        return studentRepository.getStudentById(id);
    }

    public void updateStudent(Student student) throws SQLException {
        studentRepository.updateStudent(student);
    }

    public void deleteStudent(int id) throws SQLException {
        studentRepository.deleteStudent(id);
    }
}