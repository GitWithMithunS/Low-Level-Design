package PracticeQuestions.FoodDeliveryApp;

//public class Main {
//
//    public static void main(String[] args) {
//
//        FoodOrcastrator app = new FoodOrcastrator();
//
//        app.runDemo();
//
//    }
//}

import PracticeQuestions.FoodDeliveryApp.ENUM.OrderType;
import PracticeQuestions.FoodDeliveryApp.models.Restaurant;
import PracticeQuestions.FoodDeliveryApp.models.User;
import PracticeQuestions.FoodDeliveryApp.strategies.CreditCardPaymentStrategy;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        //loading the application with restaurants and their menus
        FoodOrchestrator app = new FoodOrchestrator();

        //creating a user who uses the app to order food
        User user = new User("Mithun", "HSR Layout", "9876543210");

        //searching for restaurants in a specific location to order food
        System.out.println("Searching restaurants in BTM Layout...");
        List<Restaurant> restaurants = app.searchRestaurant("BTM Layout");
        for (int i = 0; i < restaurants.size(); i++) {
            System.out.println((i + 1) + ". "
                    + restaurants.get(i).getName());
        }

        //user selecting a restaurant and choosing items from the menu
        Restaurant selected = restaurants.get(0);
        System.out.println("\nSelected Restaurant : " + selected.getName());
        app.showMenu(selected);

        //user adding items to the cart
        System.out.println("\nAdding Items...");
        app.addItem(user, selected, selected.getMenu().get(1), 2);
        app.addItem(user, selected, selected.getMenu().get(2), 1);

        //user checking the cart before checkout
        System.out.println("\nCart");
        app.showCart(user);

        //user checking out the order with payment strategy
        System.out.println("\nProceeding to Checkout...\n");
        app.checkout(user, OrderType.DELIVERY, new CreditCardPaymentStrategy("XXXX-1234"));
    }
}