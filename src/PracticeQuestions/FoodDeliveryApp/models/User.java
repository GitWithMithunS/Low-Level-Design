package PracticeQuestions.FoodDeliveryApp.models;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    private final int id;
    private String name;
    private String address;
    private String phoneNumber;
    private Cart cart;

    User(String name , String address , String phoneNumber){
        if (phoneNumber == null || phoneNumber.length() != 10) {
            throw new IllegalArgumentException("Phone number must contain exactly 10 digits.");
        }

        this.id = COUNTER.getAndIncrement();
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.cart = new Cart(id);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

}
