package PracticeQuestions.FoodDeliveryApp.models;

import PracticeQuestions.FoodDeliveryApp.ENUM.OrderType;
import PracticeQuestions.FoodDeliveryApp.ENUM.PaymentStatus;
import PracticeQuestions.FoodDeliveryApp.strategies.PaymentStrategy;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Order {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    private int id;
    private final User user;


    private final Restaurant restaurant;
    private final Map<MenuItem , Integer> items;
    private final double cost;
    protected String scheduled;
    private PaymentStatus status;


    Order(User user , Restaurant  restaurant, Map<MenuItem, Integer> items, double cost , String scheduled){
        this.user = user;
        this.restaurant = restaurant;
        this.items = items;
        this.cost = cost;
        this.scheduled = scheduled;
    }

    public PaymentStatus getPaymentStatus() {
        return status;
    }

    public void setPaymentStatus(PaymentStatus status) {
        this.status = status;
    }

    public double getCost() {
        return cost;
    }

    public Map<MenuItem, Integer> getItems() {
        return items;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public String getScheduled() {
        return scheduled;
    }

    public void listOrderItems(){
        for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {

            MenuItem item = entry.getKey();
            int quantity = entry.getValue();

            System.out.println(
                    " - " + item.getName()
                            + " | Qty: " + quantity
                            + " | Price: ₹" + item.getPrice()
                            + " | Subtotal: ₹" + (item.getPrice() * quantity)
            );
        }
    }

    //to be implemented by the children
    public abstract OrderType getType();

    public abstract String getAddress();
}
