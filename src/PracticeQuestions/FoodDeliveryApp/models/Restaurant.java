package PracticeQuestions.FoodDeliveryApp.models;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;

public class Restaurant {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    private final int id;
    private String name;
    private String location;
    private final List<MenuItem> menu;

    public Restaurant(String name , String location ){
        this.id = COUNTER.getAndIncrement();
        this.name = name;
        this.location = location;
        this.menu = new ArrayList<>();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

    public void addMenuItem(MenuItem item){
        menu.add(item);
    }

    public boolean hasItem(MenuItem item) {
        for(MenuItem i : menu){
            if(i.equals(item)) return true;
        }
        return false;
    }
}
