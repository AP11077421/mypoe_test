/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectpart1_2_3;

/**
 *
 * @author mohla
 */
import java.util.*;
import java.io.*;
import com.google.gson.Gson;

public class message {
    private static int totalMessages = 0;
    private static ArrayList<message> sentMessages = new ArrayList<>();

    private int messageNumber;
    private String messageID;
    private String recipient;
    private String text;
    private String messageHash;

    // Constructor: auto-generate ID if not provided
    public message(String recipient, String text) {
        this.messageNumber = ++totalMessages;
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.text = text;
        this.messageHash = createMessageHash();
        sentMessages.add(this);
    }

    private String generateMessageID() {
        Random rand = new Random();
        long id = (long)(1000000000L + rand.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    // === Getter methods (for Project_part123) ===
    public String getMessageID() { return messageID; }
    public int getMessageNumber() { return messageNumber; }
    public String getMessageHash() { return messageHash; }

    // === Validation methods ===
    public Boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipient.startsWith("+27") && recipient.length() == 12 && recipient.substring(3).matches("\\d{9}")) {
            return "Valid recipient number";
        } else {
            return "Invalid recipient number";
        }
    }

    public String createMessageHash() {
        String[] words = text.split(" ");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        return (messageID.substring(0,2) + ":" + messageNumber + ":" + firstWord + ":" + lastWord).toUpperCase();
    }

    public String sendMessage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nChoose option:");
        System.out.println("1. Send now");
        System.out.println("2. Disregard");
        System.out.println("3. Store for later");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch(option) {
            case 1: return "Message successfully sent";
            case 2: return "Message disregarded";
            case 3: return storeMessage();
            default: return "Invalid choice";
        }
    }

    public String storeMessage() {
        try (FileWriter writer = new FileWriter("messages.json", true)) {
            Gson gson = new Gson();
            writer.write(gson.toJson(this) + "\n");
            return "Message successfully stored";
        } catch (IOException e) {
            return "Error storing message: " + e.getMessage();
        }
    }

    public static String printMessage() {
        StringBuilder sb = new StringBuilder();
        for (message m : sentMessages) {
            sb.append("Message #").append(m.messageNumber)
              .append(" | ID: ").append(m.messageID)
              .append(" | Recipient: ").append(m.recipient)
              .append(" | Text: ").append(m.text)
              .append(" | Hash: ").append(m.messageHash)
              .append("\n");
        }
        return sb.toString();
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }
}


