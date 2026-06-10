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
import com.google.gson.reflect.TypeToken;

public class Message {
    private static int totalMessages = 0;
    private static ArrayList<Message> sentMessages = new ArrayList<>();

    private int messageNumber;
    private String messageID;
    private String recipient;
    private String text;
    private String messageHash;

    // Constructor: auto-generate ID if not provided
    public Message(String recipient, String text) {
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
    public String getRecipient() { return recipient; }
    public String getText() { return text; }

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

    public static String printMessage() {
        StringBuilder sb = new StringBuilder();
        for (Message m : sentMessages) {
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
    // String storeMessage()
    public String storeMessage() {
        try {
            Gson gson = new Gson();
            List<Message> messages = new ArrayList<>();
            File file = new File("messages.json");

            if (file.exists()) {
                messages = gson.fromJson(new FileReader(file), new TypeToken<List<Message>>(){}.getType());
                if (messages == null) messages = new ArrayList<>();
            }

            messages.add(this);

            try (FileWriter writer = new FileWriter(file)) {
                gson.toJson(messages, writer);
            }
            return "Message successfully stored";
        } catch (IOException e) {
            return "Error storing message: " + e.getMessage();
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
}

