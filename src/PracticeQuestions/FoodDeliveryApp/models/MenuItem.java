package PracticeQuestions.FoodDeliveryApp.models;

import java.util.concurrent.atomic.AtomicInteger;

public class MenuItem {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private final int id;
    private String name;
    private Double price;

    public MenuItem(String name, Double price) {
        this.id = COUNTER.getAndIncrement();
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
