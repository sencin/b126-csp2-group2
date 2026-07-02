package com.joysistvi.ursa.view;

import com.joysistvi.ursa.model.Guardian;

import java.util.List;
import java.util.Scanner;

public class GuardianView {

    private final Scanner scanner = new Scanner(System.in);

    public int menu() {
        System.out.println("\n===== GUARDIANS =====");
        System.out.println("1. Add");
        System.out.println("2. View By Student");
        System.out.println("3. View By ID");
        System.out.println("4. Update");
        System.out.println("5. Delete");
        System.out.println("0. Back");
        System.out.print("Choice: ");

        return Integer.parseInt(scanner.nextLine());
    }

    // CREATE
    public Guardian addNewGuardian() {

        System.out.println("\n===== ADD GUARDIAN =====");

        System.out.print("Student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Relationship: ");
        String relationship = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        return new Guardian(
                0,
                studentId,
                firstName,
                lastName,
                relationship,
                phoneNumber
        );
    }

    // UPDATE
    public Guardian updateGuardian() {

        System.out.println("\n===== UPDATE GUARDIAN =====");

        System.out.print("Guardian ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Relationship: ");
        String relationship = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        return new Guardian(
                id,
                studentId,
                firstName,
                lastName,
                relationship,
                phoneNumber
        );
    }

    public int readGuardianId() {
        System.out.print("Enter Guardian ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int readStudentId() {
        System.out.print("Enter Student ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void displayGuardian(Guardian guardian) {

        System.out.println("----------------------------");
        System.out.println("ID: " + guardian.getId());
        System.out.println("Student ID: " + guardian.getStudentId());
        System.out.println("First Name: " + guardian.getFirstName());
        System.out.println("Last Name: " + guardian.getLastName());
        System.out.println("Relationship: " + guardian.getRelationship());
        System.out.println("Phone Number: " + guardian.getPhoneNumber());
        System.out.println("----------------------------");
    }

    public void displayGuardians(List<Guardian> guardians) {

        if (guardians.isEmpty()) {
            System.out.println("No guardians found.");
            return;
        }

        System.out.println("\n===== GUARDIAN LIST =====");

        for (Guardian guardian : guardians) {
            displayGuardian(guardian);
        }
    }
}