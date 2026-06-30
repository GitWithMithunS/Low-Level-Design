package PracticeQuestions.FoodDeliveryApp.models;

import java.util.Map;

public class PickUpOrder extends Order{
    private String restaurantAddress;

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public PickUpOrder(User user, Restaurant restaurant, Map<MenuItem, Integer> items, double cost,String scheduled, String restaurantAddress) {
        super(user, restaurant, items, cost , scheduled);
        this.restaurantAddress = restaurantAddress;
    }

    @Override
    public String getType(){
        return "Pickup";
    }
}
