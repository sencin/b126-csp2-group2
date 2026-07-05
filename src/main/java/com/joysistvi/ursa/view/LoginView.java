package com.joysistvi.ursa.view;

import com.joysistvi.ursa.model.Login;
import com.joysistvi.ursa.utils.ConsoleInput;
public class LoginView {

    public Login login() {
        System.out.println("\n===== LOGIN CONSOLE =====");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Choose an option: ");

        String choice = ConsoleInput.SCANNER.nextLine();
        String email = "";
        String password = "";

        if (choice.equals("2")) {
            System.out.println("Exiting login screen...");
            return null;
        }

        if(choice.equals("1")){
            System.out.println("\n===== ENTER CREDENTIALS =====");
            System.out.print("Email: ");
            email = ConsoleInput.SCANNER.nextLine();
            System.out.print("Password: ");
            password = ConsoleInput.SCANNER.nextLine();
        }
        return new Login(email, password);
    }
}