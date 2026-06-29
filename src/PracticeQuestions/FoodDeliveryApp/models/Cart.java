package PracticeQuestions.FoodDeliveryApp.models;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Restaurant restaurant;
    private final int userId;
    private final List<MenuItem> items;


    Cart(int userId){
        this.userId = userId;
        items = new ArrayList<>();
    }

    public void selectRestaurant(Restaurant restaurant) {
        if (this.restaurant != null && this.restaurant != restaurant) {
            clearCart();
        }
        this.restaurant = restaurant;
    }

    public void addItem(MenuItem item){
        if (restaurant == null)
            throw new IllegalStateException("Select restaurant first.");

        //if item belongs to restaurant that is not selected then throwing exception
        if (!restaurant.hasItem(item))
            throw new IllegalArgumentException("Item doesn't belong to selected restaurant.");

        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public int getUserId() {
        return userId;
    }

    public Double getCost(){
        Double cost = 0d;
        for(MenuItem i : items){
            cost += i.getPrice();
        }
        return cost;
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public void clearCart(){
        items.clear();
    }

}
