package com.joysistvi.ursa.view;

import com.joysistvi.ursa.model.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static com.joysistvi.ursa.utils.ConsoleTableUtils.repeat;

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

    public void displayStudents(List<Student> students) {
        int idW = 5, fnW = 12, lnW = 12, mnW = 10, emW = 20, genW = 6, statW = 10, yrW = 5, dobW = 10;

        String border = "+" + repeat("-", idW + 2) + "+" +
                repeat("-", fnW + 2) + "+" +
                repeat("-", lnW + 2) + "+" +
                repeat("-", mnW + 2) + "+" +
                repeat("-", emW + 2) + "+" +
                repeat("-", genW + 2) + "+" +
                repeat("-", statW + 2) + "+" +
                repeat("-", yrW + 2) + "+" +
                repeat("-", dobW + 2) + "+";

        String rowFormat = "| %-" + idW + "s | %-" + fnW + "s | %-" + lnW + "s | %-" + mnW + "s | %-" + emW + "s | %-" + genW + "s | %-" + statW + "s | %-" + yrW + "s | %-" + dobW + "s |%n";

        System.out.println(border);
        System.out.format(rowFormat, "ID", "First", "Last", "Middle", "Email", "Gender", "Status", "Year", "Birth Date");
        System.out.println(border);
        for (Student student : students) {
            System.out.format(rowFormat,
                    student.getId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getMiddleName(),
                    student.getEmail(),
                    student.getGender(),
                    student.getAccountStatus(),
                    student.getYearLevel(),
                    student.getDateOfBirth()
            );
        }
        System.out.println(border);
    }
}