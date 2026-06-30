package PracticeQuestions.FoodDeliveryApp.factories;

import PracticeQuestions.FoodDeliveryApp.ENUM.OrderType;
import PracticeQuestions.FoodDeliveryApp.models.MenuItem;
import PracticeQuestions.FoodDeliveryApp.models.Order;
import PracticeQuestions.FoodDeliveryApp.models.Restaurant;
import PracticeQuestions.FoodDeliveryApp.models.User;

import java.util.Map;

public interface OrderFactory {
    Order createOrder(User user , Restaurant restaurant, Map<MenuItem, Integer> items, double cost , OrderType orderType);
}
