package PracticeQuestions.FoodDeliveryApp;

import PracticeQuestions.FoodDeliveryApp.ENUM.OrderType;
import PracticeQuestions.FoodDeliveryApp.managers.RestaurantManager;
import PracticeQuestions.FoodDeliveryApp.models.*;
import PracticeQuestions.FoodDeliveryApp.services.OrderService;
import PracticeQuestions.FoodDeliveryApp.strategies.CreditCardPaymentStrategy;

import java.util.List;

public class FoodOrcastrator {

    public void runDemo() {

        loadRestaurants();

        User user = new User(
                "Mithun",
                "HSR Layout, Bangalore",
                "9876543210"
        );

        System.out.println("=================================================");
        System.out.println("WELCOME TO FOOD DELIVERY APP");
        System.out.println("=================================================\n");

        //---------------------------
        // Search Restaurant
        //---------------------------
        System.out.println("Searching restaurants in BTM Layout...\n");

        List<Restaurant> restaurants =
                RestaurantManager.getInstance()
                        .getRestaurantsByLocation("BTM Layout");

        for(int i=0;i<restaurants.size();i++){
            System.out.println((i+1)+". "+restaurants.get(i).getName());
        }

        //---------------------------
        // Select Restaurant
        //---------------------------

        Restaurant selected = restaurants.get(0);

        System.out.println("\nSelected Restaurant : "
                + selected.getName());

        user.getCart().selectRestaurant(selected);

        //---------------------------
        // Display Menu
        //---------------------------

        System.out.println("\nMenu");

        List<MenuItem> menu = selected.getMenu();

        for(int i=0;i<menu.size();i++){
            MenuItem item = menu.get(i);

            System.out.println(
                    (i+1)+". "
                            + item.getName()
                            + "  ₹"
                            + item.getPrice()
            );
        }

        //---------------------------
        // Add Items
        //---------------------------

        System.out.println("\nAdding Items...");

        user.getCart().addItem(menu.get(1),2); //Farmhouse x2
        user.getCart().addItem(menu.get(2),1); //Garlic Bread

        //---------------------------
        // Cart
        //---------------------------

        System.out.println("\nCart");

        user.getCart().listOrderItems();

        System.out.println("\nTotal : ₹"+user.getCart().getCost());

        //---------------------------
        // Checkout
        //---------------------------

        System.out.println("\nProceeding to Checkout...\n");

        OrderService.getInstance().checkoutNow(
                user,
                OrderType.DELIVERY,
                new CreditCardPaymentStrategy("XXXX-XXXX-2345")
        );

        System.out.println("\nDemo Completed.");
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