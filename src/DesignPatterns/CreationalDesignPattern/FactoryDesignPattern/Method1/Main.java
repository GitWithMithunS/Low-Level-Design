package DesignPatterns.CreationalDesignPattern.FactoryDesignPattern.Method1;/*
 * ===============================================
 * FACTORY METHOD + TEMPLATE METHOD PATTERN
 * ===============================================
 *
 * Key Concepts:
 * 1. Factory Method:
 *    - Subclasses decide which object to create.
 *
 * 2. Template Method:
 *    - Defines a fixed workflow (send()).
 *    - Uses factory method inside it.
 *
 * 3. Goal:
 *    - Decouple object creation from usage
 *    - Centralize workflow logic
 *
 * ===============================================
 * WHEN TO USE:
 * - Same workflow for all objects (logging, validation, etc.)
 * - Only object type changes
 *
 * ===============================================
 */

///////////////////////
// PRODUCT INTERFACE //
///////////////////////

interface Notification {
    void send(String message);
}

/////////////////////////////
// CONCRETE IMPLEMENTATIONS //
/////////////////////////////

class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}

class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

class PushNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending PUSH: " + message);
    }
}

class SlackNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending SLACK: " + message);
    }
}

/////////////////////////
// CREATOR (ABSTRACT)  //
/////////////////////////

abstract class NotificationCreator {

    /*
     * FACTORY METHOD
     * Subclasses will decide which Notification to create
     */
    public abstract Notification createNotification();

    /*
     * TEMPLATE METHOD
     * Defines fixed workflow:
     * 1. Logging
     * 2. Create object
     * 3. Send message
     * 4. Logging
     *
     * NOTE:
     * All subclasses will follow this same flow
     */
    public void send(String message) {
        logStart();

        Notification notification = createNotification();

        notification.send(message);

        logEnd();
    }

    // Common logic (can be reused / extended later)
    private void logStart() {
        System.out.println("[LOG] Starting notification process...");
    }

    private void logEnd() {
        System.out.println("[LOG] Notification sent successfully.");
    }
}

/////////////////////////////
// CONCRETE FACTORY CLASSES //
/////////////////////////////

class EmailNotificationCreator extends NotificationCreator {
    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }
}

class SMSNotificationCreator extends NotificationCreator {
    @Override
    public Notification createNotification() {
        return new SMSNotification();
    }
}

class PushNotificationCreator extends NotificationCreator {
    @Override
    public Notification createNotification() {
        return new PushNotification();
    }
}

class SlackNotificationCreator extends NotificationCreator {
    @Override
    public Notification createNotification() {
        return new SlackNotification();
    }
}

/////////////////////
// CLIENT (MAIN)   //
/////////////////////

public class Main {
    public static void main(String[] args) {

        /*
         * Client only interacts with Creator
         * It does NOT know concrete classes (decoupled)
         */

        NotificationCreator creator;

        // EMAIL
        creator = new EmailNotificationCreator();
        creator.send("Welcome to our platform!");

        System.out.println("--------------------------------");

        // SMS
        creator = new SMSNotificationCreator();
        creator.send("Your OTP is 123456");

        System.out.println("--------------------------------");

        // PUSH
        creator = new PushNotificationCreator();
        creator.send("You have a new follower!");

        System.out.println("--------------------------------");

        // SLACK
        creator = new SlackNotificationCreator();
        creator.send("Standup in 10 minutes!");
    }
}

/*
 * ===============================================
 * FINAL REVISION NOTES (IMPORTANT)
 * ===============================================
 *
 * ✔ Factory Method:
 *   - createNotification() is overridden by subclasses
 *
 * ✔ Template Method:
 *   - send() defines fixed algorithm
 *
 * ✔ Decoupling:
 *   - Client depends on NotificationCreator (NOT concrete classes)
 *
 * ✔ Advantage:
 *   - Easy to add new notification type (just new class)
 *
 * ✔ Disadvantage:
 *   - Less flexibility if workflow differs per type
 *
 * ===============================================
 */

