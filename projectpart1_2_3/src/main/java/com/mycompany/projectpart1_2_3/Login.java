/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectpart1_2_3;

import java.util.ArrayList;

/**
 *
 * @author mohla
 */
class Login {
    private ArrayList<User> users;
    private boolean loginStatus = false;

    Login(ArrayList<User> users) {
        this.users = users;
    }

    // ✅ Check username: must contain "_" and be <= 5 characters
    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    // ✅ Check password complexity:
    // min 8 chars, contains capital letter, number, and special character
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) return false;
        boolean hasCapital = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
        return hasCapital && hasNumber && hasSpecial;
    }

    // ✅ Check cell phone: must start with +27 and have exactly 9 digits after
    public boolean checkCellPhoneNumber(String cellPhone) {
        return cellPhone != null &&
               cellPhone.startsWith("+27") &&
               cellPhone.length() == 12 &&
               cellPhone.substring(3).matches("\\d{9}");
    }

    // ✅ Register user if all checks pass
    public String registerUser(String firstName, String lastName, String username, String password, String cellPhone) {
        if (checkUserName(username) && checkPasswordComplexity(password) && checkCellPhoneNumber(cellPhone)) {
            users.add(new User(firstName, lastName, username, password, cellPhone));
            return "Registration successful!";
        } else {
            return "Registration failed. Please check your details.";
        }
    }

    // ✅ Login user
    public boolean loginUser(String username, String password) {
        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                loginStatus = true;
                return true;
            }
        }
        loginStatus = false;
        return false;
    }

    // ✅ Return Login status message
    public String returnLoginStatus() {
        if (loginStatus) {
            return "Login successful!";
        } else {
            return "Login failed. Invalid username or password.";
        }
    }
}


