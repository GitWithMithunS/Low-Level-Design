package PracticeQuestions.FoodDeliveryApp;

import PracticeQuestions.FoodDeliveryApp.ENUM.OrderType;
import PracticeQuestions.FoodDeliveryApp.managers.RestaurantManager;
import PracticeQuestions.FoodDeliveryApp.models.*;
import PracticeQuestions.FoodDeliveryApp.services.OrderService;
import PracticeQuestions.FoodDeliveryApp.strategies.CreditCardPaymentStrategy;
import PracticeQuestions.FoodDeliveryApp.strategies.PaymentStrategy;

import java.util.List;

public class FoodOrchestrator {
    private final RestaurantManager restaurantManager;
    private final OrderService orderService;

    FoodOrchestrator(){
        System.out.println("=================================================");
        System.out.println("WELCOME TO FOOD DELIVERY APP");
        System.out.println("=================================================\n");
        loadRestaurants();
        restaurantManager = RestaurantManager.getInstance();
        orderService = OrderService.getInstance();
    }

    public List<Restaurant> searchRestaurant(String location) {
        return restaurantManager.getRestaurantsByLocation(location);
    }

    public void showMenu(Restaurant restaurant) {
        System.out.println("\nMenu of " + restaurant.getName());

        int i = 1;
        for (MenuItem item : restaurant.getMenu()) {
            System.out.println(i++ + ". " +
                    item.getName() +
                    " - $" +
                    item.getPrice());
        }
    }

    public void addItem(User user, Restaurant restaurant, MenuItem item, int qty) {
        user.getCart().selectRestaurant(restaurant);
        user.getCart().addItem(item, qty);
    }

    public void showCart(User user) {
        user.getCart().listOrderItems();
        System.out.println("Total : $" + user.getCart().getCost());
    }

    public Order checkout(User user, OrderType orderType, PaymentStrategy paymentStrategy) {
        return orderService.checkoutNow(user, orderType, paymentStrategy);
    }

    private void loadRestaurants(){

        RestaurantManager manager =
                RestaurantManager.getInstance();

        Restaurant dominos =
                new Restaurant("Domino's Pizza","BTM Layout");

        dominos.addMenuItem(new MenuItem("Margherita",199.0));
        dominos.addMenuItem(new MenuItem("Farmhouse",349.0));
        dominos.addMenuItem(new MenuItem("Garlic Bread",149.0));
        dominos.addMenuItem(new MenuItem("Pepsi",60.0));

        Restaurant burgerKing =
                new Restaurant("Burger King","BTM Layout");

        burgerKing.addMenuItem(new MenuItem("Veg Whopper",229.0));
        burgerKing.addMenuItem(new MenuItem("French Fries",119.0));
        burgerKing.addMenuItem(new MenuItem("Coke",60.0));

        Restaurant meghana =
                new Restaurant("Meghana Foods","Koramangala");

        meghana.addMenuItem(new MenuItem("Veg Biryani",299.0));
        meghana.addMenuItem(new MenuItem("Paneer Curry",269.0));
        meghana.addMenuItem(new MenuItem("Butter Naan",45.0));

        Restaurant empire =
                new Restaurant("Empire Restaurant","Indiranagar");

        empire.addMenuItem(new MenuItem("Veg Fried Rice",189.0));
        empire.addMenuItem(new MenuItem("Gobi Manchurian",199.0));
        empire.addMenuItem(new MenuItem("Lassi",90.0));

        manager.addRestaurant(dominos);
        manager.addRestaurant(burgerKing);
        manager.addRestaurant(meghana);
        manager.addRestaurant(empire);
    }

}