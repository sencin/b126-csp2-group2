package com.joysistvi.ursa.view;

import com.joysistvi.ursa.model.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class StudentView {

    private final Scanner scanner = new Scanner(System.in);

    public int menu() {
        System.out.println("\n===== STUDENTS =====");
        System.out.println("1. Add");
        System.out.println("2. View All");
        System.out.println("3. View By ID");
        System.out.println("4. Update");
        System.out.println("5. Delete");
        System.out.println("0. Back");
        System.out.print("Choice: ");

        return Integer.parseInt(scanner.nextLine());
    }

    public Student addNewStudent() {

        System.out.println("\n===== ADD STUDENT =====");

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Middle Name: ");
        String middleName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Account Status: ");
        String accountStatus = scanner.nextLine();

        System.out.print("Year Level: ");
        int yearLevel = Integer.parseInt(scanner.nextLine());

        System.out.print("Date of Birth (yyyy-MM-dd): ");
        LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());

        return new Student(
                0,
                firstName,
                lastName,
                middleName,
                email,
                password,
                gender,
                accountStatus,
                yearLevel,
                dateOfBirth
        );
    }

    public Student updateStudent() {

        System.out.println("\n===== UPDATE STUDENT =====");

        System.out.print("Student ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Middle Name: ");
        String middleName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Account Status: ");
        String accountStatus = scanner.nextLine();

        System.out.print("Year Level: ");
        int yearLevel = Integer.parseInt(scanner.nextLine());

        System.out.print("Date of Birth (yyyy-MM-dd): ");
        LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());

        return new Student(
                id,
                firstName,
                lastName,
                middleName,
                email,
                password,
                gender,
                accountStatus,
                yearLevel,
                dateOfBirth
        );
    }

    public int readStudentId() {
        System.out.print("Enter Student ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void displayStudent(Student student) {

        System.out.println("----------------------------");
        System.out.println("ID             : " + student.getId());
        System.out.println("First Name     : " + student.getFirstName());
        System.out.println("Last Name      : " + student.getLastName());
        System.out.println("Middle Name    : " + student.getMiddleName());
        System.out.println("Email          : " + student.getEmail());
        System.out.println("Gender         : " + student.getGender());
        System.out.println("Account Status : " + student.getAccountStatus());
        System.out.println("Year Level     : " + student.getYearLevel());
        System.out.println("Date of Birth  : " + student.getDateOfBirth());
        System.out.println("----------------------------");
    }

    public void displayStudents(List<Student> students) {

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n===== STUDENT LIST =====");

        for (Student student : students) {
            displayStudent(student);
        }
    }
}