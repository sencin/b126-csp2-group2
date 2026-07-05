package com.joysistvi.ursa.controller;

import com.joysistvi.ursa.model.Login;
import com.joysistvi.ursa.model.User;
import com.joysistvi.ursa.service.LoginService;
import com.joysistvi.ursa.view.LoginView;

import java.sql.SQLException;

public class LoginController {

    private final LoginService loginService;
    private final LoginView loginView;

    public LoginController(LoginService loginService, LoginView loginView) {
        this.loginService = loginService;
        this.loginView = loginView;
    }

    public User login() {
        try {
            Login login = loginView.login();
            User user = loginService.authenticate(login);

            if (user == null) {
                System.out.println("Invalid email or password.");
                return null;
            }

            System.out.println("Welcome, " + user.getFirstName() + "!");
            return user;

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
            return null;
        }
    }
}