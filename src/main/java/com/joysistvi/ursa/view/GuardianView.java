package com.joysistvi.ursa.view;
import com.joysistvi.ursa.model.Guardian;
import java.util.List;
import java.util.Scanner;

import static com.joysistvi.ursa.utils.ConsoleTableUtils.repeat;

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

        int idW = 5, stuW = 10, nameW = 12, relW = 12, phoneW = 15;

        String border = "+" + repeat("-", idW + 2) + "+" + repeat("-", stuW + 2) + "+" +
                repeat("-", nameW + 2) + "+" + repeat("-", nameW + 2) + "+" +
                repeat("-", relW + 2) + "+" + repeat("-", phoneW + 2) + "+";

        String rowFormat = "| %-" + idW + "s | %-" + stuW + "s | %-" + nameW + "s | %-" + nameW + "s | %-" + relW + "s | %-" + phoneW + "s |%n";

        System.out.println(border);
        System.out.format(rowFormat, "ID", "Student ID", "First", "Last", "Relation", "Phone");
        System.out.println(border);
        System.out.format(rowFormat,
                guardian.getId(),
                guardian.getStudentId(),
                guardian.getFirstName(),
                guardian.getLastName(),
                guardian.getRelationship(),
                guardian.getPhoneNumber()
        );
        System.out.println(border);
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