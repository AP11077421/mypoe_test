/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projectpart1_2_3;

/**
 *
 * @author mohla
 */
<<<<<<< Updated upstream
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author mohla
 */
// Represents a user with basic details
=======

import java.util.*;
import java.io.*;
import com.google.gson.Gson;
// User class
>>>>>>> Stashed changes
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



<<<<<<< Updated upstream
=======
// Main class
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
        loginSystem.loginUser(username, password);
=======
        boolean found = false;
        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("\nWelcome to QuickChat");
            quickChatMenu();
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }

    private static void quickChatMenu() {
        boolean chatting = true;
        while (chatting) {
            System.out.println("\n=== QuickChat Menu ===");
            System.out.println("1. Send Messages");
            System.out.println("2. Show Recently Sent Messages");
            System.out.println("3. Quit QuickChat");
            System.out.println("4. Stored Messages");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: sendMultipleMessages(); break;
                case 2: System.out.println(Message.printMessage()); break;
                case 3: chatting = false; break;
                case 4: showStoredMessages(); break;
                default: System.out.println("Invalid choice.PLEASE CHOOSE FROM (1, 2, 3)");
            }
        }
    }
    private static void showStoredMessages() {
        
        try (BufferedReader reader = new BufferedReader(new FileReader("messages.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } 
        catch (IOException e) {
            System.out.println("No stored messages found.");
        }
    }

    private static void sendMultipleMessages() {
        System.out.print("How many messages would you like to send? ");
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= count; i++) {
            System.out.println("\n--- Message " + i + " ---");
            sendMessage();
        }
    }

    private static void sendMessage() {
        System.out.print("Enter recipient (+27 followed by 9 digits): ");
        String recipient = scanner.nextLine();
        if (!(recipient.startsWith("+27") && recipient.length() == 12 && recipient.substring(3).matches("\\d{9}"))) {
            System.out.println("Invalid recipient number.");
            return;
        }

        System.out.print("Enter message (max 250 characters): ");
        String text = scanner.nextLine();
        if (text.length() > 250) {
            System.out.println("Please enter a message of less than 250 characters");
            return;
        }

        Message msg = new Message(recipient, text);
        System.out.println("Message sent");
        System.out.println("Message ID: " + msg.getMessageID());
        System.out.println("Message Number: " + msg.getMessageNumber());
        System.out.println("Message Hash: " + msg.getMessageHash());

        System.out.println("\nChoose option:");
        System.out.println("1. Send now");
        System.out.println("2. Disregard");
        System.out.println("3. Store message");
        System.out.print("Pick option here:");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch(option) {
            case 1: System.out.println("Message successfully sent!"); break;
            case 2: System.out.println("Press 0 to delete the message"); break;
            case 3: System.out.println(msg.storeMessage()); break;
            default: System.out.println("Invalid choice.");
        }
    }

    private static void storeMessage(Message msg) {
        try (FileWriter writer = new FileWriter("messages.json", true)) {
            Gson gson = new Gson();
            writer.write(gson.toJson(msg) + "\n");
            System.out.println("Message successfully stored");
        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
>>>>>>> Stashed changes
    }
    

    // Check password rules
    private static boolean isValidPassword(String password) {
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
        return password.length() >= 8 && hasNumber && hasSpecial;
    }
}
