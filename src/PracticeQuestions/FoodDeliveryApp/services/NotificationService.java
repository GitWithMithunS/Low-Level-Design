package PracticeQuestions.FoodDeliveryApp.services;

import PracticeQuestions.FoodDeliveryApp.models.MenuItem;
import PracticeQuestions.FoodDeliveryApp.models.Order;

import java.util.Map;

public class NotificationService {

    public static void sendNotification(Order order) {

        System.out.println("\nNotification: New " + order.getType() + " order placed!");
        System.out.println("------------------------------------------------");
        System.out.println("Order ID      : " + order.getId());
        System.out.println("Customer      : " + order.getUser().getName());
        System.out.println("Restaurant    : " + order.getRestaurant().getName());

        System.out.println("\nItems Ordered:");

        for (Map.Entry<MenuItem, Integer> entry : order.getItems().entrySet()) {

            MenuItem item = entry.getKey();
            int quantity = entry.getValue();

            System.out.println(
                    " - " + item.getName()
                            + " | Qty: " + quantity
                            + " | Price: ₹" + item.getPrice()
                            + " | Subtotal: ₹" + (item.getPrice() * quantity)
            );
        }

        System.out.println("\nTotal          : ₹" + order.getCost());
        System.out.println("Scheduled Time : " + order.getScheduled());

        System.out.println("Payment        : Successful");
        System.out.println("Payment Status : " + order.getPaymentStatus());
        System.out.println("------------------------------------------------");
    }
}