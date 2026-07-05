package com.joysistvi.ursa.view;

import com.joysistvi.ursa.model.Login;
import com.joysistvi.ursa.utils.ConsoleInput;

import java.util.Scanner;

public class LoginView {

    public Login login() {

        System.out.println("\n===== LOGIN =====");

        System.out.print("Username: ");
        String username = ConsoleInput.SCANNER.nextLine();

        System.out.print("Password: ");
        String password = ConsoleInput.SCANNER.nextLine();

        return new Login(username, password);
    }
}