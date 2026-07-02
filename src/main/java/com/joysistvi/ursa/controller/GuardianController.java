package com.joysistvi.ursa.controller;

import com.joysistvi.ursa.model.Guardian;
import com.joysistvi.ursa.service.GuardianService;
import com.joysistvi.ursa.view.GuardianView;

import java.sql.SQLException;
import java.util.List;

public class GuardianController {

    private final GuardianService guardianService;
    private final GuardianView guardianView;

    public GuardianController(GuardianService guardianService,
                              GuardianView guardianView) {
        this.guardianService = guardianService;
        this.guardianView = guardianView;
    }

    public void start() {
        boolean running = true;

        while (running) {

            int choice = guardianView.menu();

            try {
                switch (choice) {
                    case 1:
                        addGuardian();
                        break;

                    case 2:
                        viewGuardiansByStudentId();
                        break;

                    case 3:
                        viewGuardianById();
                        break;

                    case 4:
                        updateGuardian();
                        break;

                    case 5:
                        deleteGuardian();
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

    private void addGuardian() throws SQLException {

        Guardian guardian = guardianView.addNewGuardian();

        guardianService.addGuardian(guardian);

        System.out.println("Guardian added successfully.");
    }

    private void viewGuardiansByStudentId() throws SQLException {

        int studentId = guardianView.readStudentId();

        List<Guardian> guardians =
                guardianService.getGuardiansByStudentId(studentId);

        guardianView.displayGuardians(guardians);
    }

    private void viewGuardianById() throws SQLException {

        int id = guardianView.readGuardianId();

        Guardian guardian = guardianService.getGuardianById(id);

        if (guardian == null) {
            System.out.println("Guardian not found.");
            return;
        }

        guardianView.displayGuardian(guardian);
    }

    private void updateGuardian() throws SQLException {

        Guardian guardian = guardianView.updateGuardian();

        guardianService.updateGuardian(guardian);

        System.out.println("Guardian updated successfully.");
    }

    private void deleteGuardian() throws SQLException {

        int id = guardianView.readGuardianId();

        guardianService.deleteGuardian(id);

        System.out.println("Guardian deleted successfully.");
    }
}