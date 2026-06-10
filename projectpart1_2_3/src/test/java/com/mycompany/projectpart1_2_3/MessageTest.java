/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.projectpart1_2_3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mohla
 */
public class MessageTest {

    @Test
    public void testGetMessageID() {
        Message msg = new Message("+27123456789", "Hello world");
        String id = msg.getMessageID();
        assertNotNull(id, "Message ID should not be null");
        assertEquals(10, id.length(), "Message ID should be 10 digits long");
    }

    @Test
    public void testGetMessageNumber() {
        int before = Message.returnTotalMessages();
        Message msg = new Message("+27123456789", "Test message");
        assertEquals(before + 1, msg.getMessageNumber(), "Message number should increment correctly");
    }

    @Test
    public void testGetRecipient() {
        Message msg = new Message("+27123456789", "Recipient test");
        assertEquals("+27123456789", msg.getRecipient(), "Recipient should match input");
    }

    @Test
    public void testGetText() {
        Message msg = new Message("+27123456789", "Sample text");
        assertEquals("Sample text", msg.getText(), "Text should match input");
    }

    @Test
    public void testGetMessageHash() {
        Message msg = new Message("+27123456789", "Hash test message");
        String hash = msg.getMessageHash();
        assertTrue(hash.contains(":"), "Hash should contain separators");
        assertTrue(hash.equals(hash.toUpperCase()), "Hash should be uppercase");
    }

    @Test
    public void testCheckMessageID() {
        Message msg = new Message("+27123456789", "Check ID");
        assertTrue(msg.checkMessageID(), "Message ID should be valid (≤ 10 chars)");
    }

    @Test
    public void testCheckRecipientCellValid() {
        Message msg = new Message("+27123456789", "Valid recipient");
        assertEquals("Valid recipient number", msg.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCellInvalid() {
        Message msg = new Message("12345", "Invalid recipient");
        assertEquals("Invalid recipient number", msg.checkRecipientCell());
    }

    @Test
    public void testCreateMessageHash() {
        Message msg = new Message("+27123456789", "Hello JUnit");
        String hash = msg.createMessageHash();
        assertTrue(hash.startsWith(msg.getMessageID().substring(0,2)), "Hash should start with ID prefix");
    }

    @Test
    public void testPrintMessage() {
        Message msg = new Message("+27123456789", "Print test");
        String output = Message.printMessage();
        assertTrue(output.contains("Print test"), "Printed messages should include text");
    }

    @Test
    public void testReturnTotalMessages() {
        int before = Message.returnTotalMessages();
        new Message("+27123456789", "Another one");
        int after = Message.returnTotalMessages();
        assertEquals(before + 1, after, "Total messages should increment");
    }

    @Test
    public void testStoreMessage() {
        Message msg = new Message("+27123456789", "Store me");
        String result = msg.storeMessage();
        assertEquals("Message successfully stored", result);
    }
}