/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projectpart1_2_3;

/**
 *
 * @author mohla
 */
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author mohla
 */
// Represents a user with basic details
class User {
    String firstName;
    String lastName;
    String username;
    String password;
    String cellPhone;

    // Constructor: builds a new User object
    User(String firstName, String lastName, String username, String password, String cellPhone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
    }
}



public class Projectpart1_2_3 {
    private static ArrayList<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Login loginSystem = new Login(users);

    public static void main(String[] args) {
        boolean running = true;

        // Repeat until user chooses to exit
        do {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear newline

            if (choice == 1) {
                registerUser();
            } else if (choice == 2) {
                loginUser();
            } else if (choice == 3) {
                System.out.println("Goodbye!");
                running = false;
            } else {
                System.out.println("Invalid option, try again.");
            }
        } while (running);
    }

    // Display menu options
    private static void showMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1. Register new user");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Enter choice: ");
    }

    // Register a new user
    private static void registerUser() {
        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        String username = askForUsername();
        String password = askForPassword();
        String cellPhone = askForCellPhone();

        users.add(new User(firstName, lastName, username, password, cellPhone));
        System.out.println("User registered successfully!");
    }

    // Ask for username until valid
    private static String askForUsername() {
        String username;
        do {
            System.out.print("Username (must contain '_' and max 5 chars): ");
            username = scanner.nextLine();
        } while (!(username.contains("_") && username.length() <= 5));
        return username;
    }

    // Ask for password until valid
    private static String askForPassword() {
        String password;
        do {
            System.out.print("Password (min 8 chars, must include number and special char): ");
            password = scanner.nextLine();
        } while (!isValidPassword(password));
        return password;
    }

    // Ask for cell phone until valid (+27 + 9 digits)
    private static String askForCellPhone() {
        String cellPhone;
        do {
            System.out.print("Cell phone (+27 followed by 9 digits): ");
            cellPhone = scanner.nextLine();
        } while (!(cellPhone.startsWith("+27") && cellPhone.length() == 12 && cellPhone.substring(3).matches("\\d{9}")));
        return cellPhone;
    }

    // Handle login
    private static void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        loginSystem.loginUser(username, password);
    }

    // Check password rules
    private static boolean isValidPassword(String password) {
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
        return password.length() >= 8 && hasNumber && hasSpecial;
    }
}
