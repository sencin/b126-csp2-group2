package com.joysistvi.ursa.view;

import java.util.Scanner;

public class MainMenuView {

    private final Scanner scanner = new Scanner(System.in);

    public int showMenu() {
        System.out.println("\n========== URSA ==========");
        System.out.println("1. Students");
        System.out.println("2. Courses");
        System.out.println("3. Schedules");
        System.out.println("4. Guardians");
        System.out.println("5. Student Schedules");
        System.out.println("6. Attendance Logs");
        System.out.println("0. Exit");
        System.out.print("Select option: ");

        return Integer.parseInt(scanner.nextLine());
    }
}