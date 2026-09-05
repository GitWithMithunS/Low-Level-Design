package PracticeQuestions.InventoryManagmentSystem.concreteProducts;

import PracticeQuestions.InventoryManagmentSystem.model.Product;

public class Clothes extends Product {
    private int size;
    private String color;

    public Clothes( String name, String sku, double price, int threshold, int quantity) {
        super(name, sku, price, threshold, quantity);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


}
