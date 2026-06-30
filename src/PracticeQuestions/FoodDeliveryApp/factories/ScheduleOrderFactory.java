package PracticeQuestions.FoodDeliveryApp.factories;

import PracticeQuestions.FoodDeliveryApp.ENUM.OrderType;
import PracticeQuestions.FoodDeliveryApp.models.*;

import java.util.Map;

public class ScheduleOrderFactory  implements OrderFactory{
    private final String scheduled;

    public ScheduleOrderFactory(String scheduled){
        this.scheduled = scheduled;
    }

    @Override
    public Order createOrder(User user, Restaurant restaurant, Map<MenuItem, Integer> items, double cost, OrderType orderType) {
        if(orderType == OrderType.DELIVERY){
            String userAddress = user.getAddress();
            return new DeliveryOrder(user , restaurant , items , cost , userAddress , scheduled);
        }else if(orderType == OrderType.PICKUP){
            String restaurantAddress = restaurant.getLocation();
            return new PickUpOrder(user , restaurant , items , cost , restaurantAddress , scheduled);
        }
        return null;
    }
}
