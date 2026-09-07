package PracticeQuestions.InventoryManagmentSystem.model;

import PracticeQuestions.InventoryManagmentSystem.utility.SKUGenerator;

public class Clothes extends Product {
    private int size;
    private String color;

    public Clothes( String name, double price, int threshold, int quantity) {
        super(name, SKUGenerator.generateClothesSKU(), price, threshold, quantity);
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
