package com.joysistvi.ursa.view;
import com.joysistvi.ursa.model.Guardian;
import com.joysistvi.ursa.service.UserSession;

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

    public Guardian addNewGuardian() {
        System.out.println("\n===== ADD GUARDIAN =====");

        int studentId = UserSession.getCurrentUser().getId();

        String firstName = readMandatoryString("First Name: ");
        String lastName = readMandatoryString("Last Name: ");
        String relationship = readMandatoryString("Relationship (e.g., Mother, Father, Guardian): ");
        String phoneNumber = readMandatoryString("Phone Number: ");

        return new Guardian(
                0,
                studentId,
                firstName,
                lastName,
                relationship,
                phoneNumber
        );
    }

    private String readMandatoryString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Error: This field cannot be empty.");
        }
    }

    public Guardian updateGuardian(List<Guardian> guardians) {
        System.out.println("\n===== UPDATE GUARDIAN =====");

        int id = readMandatoryGuardianId(guardians);
        if (id == -1) return null;

        int studentId = UserSession.getCurrentUser().getId();

        String firstName = readOptionalString("First Name (Press Enter to keep existing): ");
        String lastName = readOptionalString("Last Name (Press Enter to keep existing): ");
        String relationship = readOptionalString("Relationship (Press Enter to keep existing): ");
        String phoneNumber = readOptionalString("Phone Number (Press Enter to keep existing): ");

        return new Guardian(
                id,
                studentId,
                firstName,
                lastName,
                relationship,
                phoneNumber
        );
    }

    private int readMandatoryGuardianId(List<Guardian> guardians) {
        displayGuardians(guardians);

        while (true) {
            System.out.print("Guardian ID to update (or press Enter to cancel): ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                return -1;
            }
            try {
                int id = Integer.parseInt(input);
                if (guardians.stream().anyMatch(g -> g.getId() == id)) {
                    return id;
                }
                System.out.println("Error: That Guardian ID does not exist in the list.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid numeric ID.");
            }
        }
    }

    private String readOptionalString(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? null : input;
    }

    public int readGuardianId() {
        System.out.print("Enter Guardian ID: ");
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
        int nameW = 30;
        int contactW = 15;
        int relationW = 15;
        int studentW = 30;

        String border = "+" + repeat("-", idW + 2) + "+" +
                repeat("-", nameW + 2) + "+" +
                repeat("-", contactW + 2) + "+" +
                repeat("-", relationW + 2) + "+" +
                repeat("-", studentW + 2) + "+";

        String rowFormat = "| %-" + idW + "s | %-" + nameW + "s | %-" + contactW + "s | %-" + relationW + "s | %-" + studentW + "s |%n";

        System.out.println("\n===== GUARDIAN LIST =====");
        System.out.println(border);


        System.out.format(rowFormat, "ID", "Guardian Name", "Contact", "Relationship", "Student Name");
        System.out.println(border);

        for (Guardian guardian : guardians) {

            String studentDisplay = guardian.getStudentFullName() != null
                    ? guardian.getStudentFullName()
                    : String.valueOf(guardian.getStudentId());

            System.out.format(rowFormat,
                    guardian.getId(),
                    guardian.getFirstName() + " " + guardian.getLastName(),
                    guardian.getPhoneNumber(),
                    guardian.getRelationship(),
                    studentDisplay
            );
        }

        System.out.println(border);
    }
}