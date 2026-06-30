package PracticeQuestions.FoodDeliveryApp.managers;

import PracticeQuestions.FoodDeliveryApp.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

//Singleton class
public class RestaurantManager {
    private final List<Restaurant> restaurants = new ArrayList<>();

    private RestaurantManager(){
    }

    //thread safe initialization on demand holder - Singleton creation
    private static class Holder{
        private static final RestaurantManager instance = new RestaurantManager();
    }

    public static RestaurantManager getInstance() {
        return Holder.instance;
    }

    public void addRestaurant(Restaurant restaurant){
        restaurants.add(restaurant);
    }

    public void removeRestaurant(Restaurant restaurant){
        restaurants.remove(restaurant);
    }

    public List<Restaurant> getAllResturants(){
        return restaurants;
    }

    public List<Restaurant> getRestaurantsByLocation(String location){
        List<Restaurant> restaurantList = new ArrayList<>();
        for(Restaurant r : restaurants){
            String loc = r.getLocation();
            if(loc.equalsIgnoreCase(location)){
                restaurantList.add(r);
            }
        }
        return restaurantList;
    }

}
