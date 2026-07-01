package PracticeQuestions.FoodDeliveryApp.models;

import PracticeQuestions.FoodDeliveryApp.ENUM.OrderType;

import java.util.Map;

public class DeliveryOrder extends Order{
    private String deliveryAddress;

    public DeliveryOrder(User user, Restaurant restaurant, Map<MenuItem, Integer> items, double cost , String scheduled, String deilverAddress) {
        super(user, restaurant, items, cost , scheduled);
        this.deliveryAddress = deilverAddress;
    }

    public String getAddress() {
        return "Delivery Address : " + deliveryAddress;
    }
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public OrderType getType() {
        return OrderType.DELIVERY;
    }
}
