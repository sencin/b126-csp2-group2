package com.joysistvi.ursa.controller;

import com.joysistvi.ursa.model.User;
import com.joysistvi.ursa.service.UserService;
import com.joysistvi.ursa.view.UserView;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class UserController {

    private final UserService userService;
    private final UserView userView;

    public UserController(UserService userService, UserView userView) {
        this.userService = userService;
        this.userView = userView;
    }

    public void start() {
        boolean running = true;

        while (running) {

            int choice = userView.menu();

            try {
                switch (choice) {
                    case 1:
                        addUser();
                        break;

                    case 2:
                        viewAllUsers();
                        break;

                    case 3:
                        viewUserById();
                        break;

                    case 4:
                        updateUser();
                        break;

                    case 5:
                        deleteUser();
                        break;

                    case 0:
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option.");
                }

            } catch (SQLException e) {
                System.out.println("Database Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addUser() throws SQLException {

        User user = userView.addNewUser();

        userService.addUser(user);

        System.out.println("User added successfully.");
    }

    private void viewAllUsers() throws SQLException {

        List<User> users = userService.getAllUsers();

        userView.displayUsers(users);
    }

    private void viewUserById() throws SQLException {

        int id = userView.readUserId();

        User user = userService.getUserById(id);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        userView.displayUsers(Collections.singletonList(user));
    }

    private void updateUser() throws SQLException {

        User user = userView.updateUser();

        userService.updateUser(user);

        System.out.println("User updated successfully.");
    }

    private void deleteUser() throws SQLException {

        int id = userView.readUserId();

        userService.deleteUser(id);

        System.out.println("User deleted successfully.");
    }
}