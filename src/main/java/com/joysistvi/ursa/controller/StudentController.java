package com.joysistvi.ursa.controller;

import com.joysistvi.ursa.model.Student;
import com.joysistvi.ursa.service.StudentService;
import com.joysistvi.ursa.view.StudentView;

import java.sql.SQLException;
import java.util.List;

public class StudentController {

    private final StudentService studentService;
    private final StudentView studentView;

    public StudentController(StudentService studentService, StudentView studentView) {
        this.studentService = studentService;
        this.studentView = studentView;
    }

    public void start() {
        boolean running = true;

        while (running) {

            int choice = studentView.menu();

            try {
                switch (choice) {
                    case 1:
                        addStudent();
                        break;

                    case 2:
                        viewAllStudents();
                        break;

                    case 3:
                        viewStudentById();
                        break;

                    case 4:
                        updateStudent();
                        break;

                    case 5:
                        deleteStudent();
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

    private void addStudent() throws SQLException {

        Student student = studentView.addNewStudent();

        studentService.addStudent(student);

        System.out.println("Student added successfully.");
    }

    private void viewAllStudents() throws SQLException {

        List<Student> students = studentService.getAllStudents();

        studentView.displayStudents(students);
    }

    private void viewStudentById() throws SQLException {

        int id = studentView.readStudentId();

        Student student = studentService.getStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        studentView.displayStudent(student);
    }

    private void updateStudent() throws SQLException {

        Student student = studentView.updateStudent();

        studentService.updateStudent(student);

        System.out.println("Student updated successfully.");
    }

    private void deleteStudent() throws SQLException {

        int id = studentView.readStudentId();

        studentService.deleteStudent(id);

        System.out.println("Student deleted successfully.");
    }
}