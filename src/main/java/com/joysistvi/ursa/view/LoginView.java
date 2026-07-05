package com.joysistvi.ursa.view;

import com.joysistvi.ursa.model.Login;
import com.joysistvi.ursa.utils.ConsoleInput;

import java.util.Scanner;

public class LoginView {

    public Login login() {
        System.out.println("\n===== LOGIN CONSOLE =====");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Choose an option: ");

        String choice = ConsoleInput.SCANNER.nextLine();

        if (choice.equals("2")) {
            System.out.println("Exiting login screen...");
            return null;
        }
        System.out.println("\n===== ENTER CREDENTIALS =====");

        System.out.print("Email: ");
        String email = ConsoleInput.SCANNER.nextLine();

        System.out.print("Password: ");
        String password = ConsoleInput.SCANNER.nextLine();

        return new Login(email, password);
    }
}