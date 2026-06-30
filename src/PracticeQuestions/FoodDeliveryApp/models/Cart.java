package PracticeQuestions.FoodDeliveryApp.models;

import java.awt.*;
import java.util.*;

public class Cart {
    private Restaurant restaurant;
    private final Map<MenuItem, Integer> items;


    Cart(int userId){
        items = new HashMap<>();
    }

    public void selectRestaurant(Restaurant restaurant) {
        if (this.restaurant != null && this.restaurant != restaurant) {
            clearCart();
        }
        this.restaurant = restaurant;
    }

    public void addItem(MenuItem item ){
        if (restaurant == null)
            throw new IllegalStateException("Select restaurant first.");

        //if item belongs to restaurant that is not selected then throwing exception
        if (!restaurant.hasItem(item))
            throw new IllegalArgumentException("Item doesn't belong to selected restaurant.");

        items.put(item , items.getOrDefault(item , 0) + 1);
    }

    public void addItem(MenuItem item , int quantity){
        if (restaurant == null)
            throw new IllegalStateException("Select restaurant first.");

        //if item belongs to restaurant that is not selected then throwing exception
        if (!restaurant.hasItem(item))
            throw new IllegalArgumentException("Item doesn't belong to selected restaurant.");

        items.put(item , items.getOrDefault(item , 0) + quantity);
    }

    public void removeItem(MenuItem item) {
        if(!items.containsKey(item)) return;

        int quantity = items.get(item);

        if(quantity == 1 ) items.remove(item);
        else items.put(item , quantity-1);
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Map<MenuItem, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public Double getCost(){
        double cost = 0;
        for(Map.Entry<MenuItem , Integer> entry : items.entrySet()){
            MenuItem item = entry.getKey();
            int quantity = entry.getValue();
            cost += (quantity*item.getPrice());
        }
        return cost;
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public void clearCart(){
        items.clear();
        restaurant = null;
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

}
