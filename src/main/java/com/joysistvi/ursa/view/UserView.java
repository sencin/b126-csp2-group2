package com.joysistvi.ursa.view;

import com.joysistvi.ursa.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static com.joysistvi.ursa.utils.ConsoleTableUtils.repeat;

public class UserView {

    private final Scanner scanner = new Scanner(System.in);

    public int menu() {
        System.out.println("\n===== USERS =====");
        System.out.println("1. Add");
        System.out.println("2. View All");
        System.out.println("3. View By ID");
        System.out.println("4. Update");
        System.out.println("5. Delete");
        System.out.println("0. Back");
        System.out.print("Choice: ");

        return Integer.parseInt(scanner.nextLine());
    }

    public User addNewUser() {

        System.out.println("\n===== ADD USER =====");

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

        System.out.print("Role: ");
        String role = scanner.nextLine().toUpperCase();

        System.out.print("Date of Birth (yyyy-MM-dd): ");
        LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());

        return new User(
                0,
                firstName,
                lastName,
                middleName,
                email,
                password,
                gender,
                accountStatus,
                role,
                dateOfBirth
        );
    }

    public User updateUser() {

        System.out.println("\n===== UPDATE USER =====");

        System.out.print("User ID: ");
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

        System.out.print("Role: ");
        String role = scanner.nextLine().toUpperCase();

        System.out.print("Date of Birth (yyyy-MM-dd): ");
        LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());

        return new User(
                id,
                firstName,
                lastName,
                middleName,
                email,
                password,
                gender,
                accountStatus,
                role,
                dateOfBirth
        );
    }

    public int readUserId() {
        System.out.print("Enter User ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void displayUsers(List<User> users) {

        if (users == null || users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        int idW = 5;
        int fnW = 12;
        int lnW = 12;
        int mnW = 10;
        int emW = 25;
        int genW = 8;
        int statW = 10;
        int roleW = 10;
        int dobW = 12;

        String border = "+" + repeat("-", idW + 2) + "+" +
                repeat("-", fnW + 2) + "+" +
                repeat("-", lnW + 2) + "+" +
                repeat("-", mnW + 2) + "+" +
                repeat("-", emW + 2) + "+" +
                repeat("-", genW + 2) + "+" +
                repeat("-", statW + 2) + "+" +
                repeat("-", roleW + 2) + "+" +
                repeat("-", dobW + 2) + "+";

        String rowFormat = "| %-" + idW + "s | %-" + fnW + "s | %-" + lnW + "s | %-" + mnW + "s | %-" + emW + "s | %-" + genW + "s | %-" + statW + "s | %-" + roleW + "s | %-" + dobW + "s |%n";

        System.out.println(border);
        System.out.format(rowFormat,
                "ID",
                "First",
                "Last",
                "Middle",
                "Email",
                "Gender",
                "Status",
                "Role",
                "Birth Date");
        System.out.println(border);

        for (User user : users) {
            System.out.format(rowFormat,
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getMiddleName(),
                    user.getEmail(),
                    user.getGender(),
                    user.getAccountStatus(),
                    user.getRole(),
                    user.getDateOfBirth()
            );
        }

        System.out.println(border);
    }
}