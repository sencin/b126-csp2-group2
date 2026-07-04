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

        if (guardians == null || guardians.isEmpty()) {
            System.out.println("No guardians found.");
            return;
        }

        int idW = 5;
        int nameW = 40;
        int contactW = 15;
        int relationW = 15;
        int studentW = 10;

        String border = "+" + repeat("-", idW + 2) + "+" +
                repeat("-", nameW + 2) + "+" +
                repeat("-", contactW + 2) + "+" +
                repeat("-", relationW + 2) + "+" +
                repeat("-", studentW + 2) + "+";

        String rowFormat = "| %-" + idW + "s | %-" + nameW + "s | %-" + contactW + "s | %-" + relationW + "s | %-" + studentW + "s |%n";

        System.out.println("\n===== GUARDIAN LIST =====");
        System.out.println(border);
        System.out.format(rowFormat, "ID", "Name", "Contact", "Relationship", "Student");
        System.out.println(border);

        for (Guardian guardian : guardians) {
            System.out.format(rowFormat,
                    guardian.getId(),
                    guardian.getFirstName() + " " + guardian.getLastName(),
                    guardian.getPhoneNumber(),
                    guardian.getRelationship(),
                    guardian.getStudentId()
            );
        }

        System.out.println(border);
    }
}