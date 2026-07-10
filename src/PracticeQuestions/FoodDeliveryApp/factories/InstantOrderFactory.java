package PracticeQuestions.FoodDeliveryApp.factories;

import PracticeQuestions.FoodDeliveryApp.ENUM.OrderType;
import PracticeQuestions.FoodDeliveryApp.models.*;
import PracticeQuestions.FoodDeliveryApp.utils.TimeUtils;

import java.util.Map;

public class InstantOrderFactory implements OrderFactory{

    @Override
    public Order createOrder(User user, Restaurant restaurant, Map<MenuItem, Integer> items, double cost, OrderType orderType) {
        String scheduled = TimeUtils.getCurrentTime();


        if(orderType == OrderType.DELIVERY){
            String userAddress = user.getAddress();
            return new DeliveryOrder(user , restaurant , items , cost ,  scheduled  ,userAddress);
        }else if(orderType == OrderType.PICKUP){
            String restaurantAddress = restaurant.getLocation();
            return new PickUpOrder(user , restaurant , items , cost , scheduled , restaurantAddress);
        }

        return null;
    }
}
