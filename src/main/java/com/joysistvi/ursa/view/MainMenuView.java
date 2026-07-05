package com.joysistvi.ursa.view;

import java.util.Scanner;

public class MainMenuView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int showMenu() {
        System.out.println("\n+--------------------------+");
        System.out.println("|          URSA            |");
        System.out.println("+--------------------------+");
        System.out.println("| 1. Users                 |");
        System.out.println("| 2. Courses               |");
        System.out.println("| 3. Schedules             |");
        System.out.println("| 4. Guardians             |");
        System.out.println("| 5. Student Schedules     |");
        System.out.println("| 6. Attendance Logs       |");
        System.out.println("| 0. Exit                  |");
        System.out.println("+--------------------------+");
        System.out.print("Select option: ");

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}