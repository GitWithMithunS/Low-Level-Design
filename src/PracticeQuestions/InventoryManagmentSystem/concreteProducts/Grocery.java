package PracticeQuestions.InventoryManagmentSystem.concreteProducts;

import PracticeQuestions.InventoryManagmentSystem.model.Product;

import java.util.Date;

public class Grocery extends Product {
    private boolean isRefregirated;
    private Date expiryDate;

    public Grocery(String name, String sku, double price, int threshold, int quantity) {
        super(name, sku, price, threshold, quantity);
    }

    public boolean isRefregirated() {
        return isRefregirated;
    }

    public void setRefregirated(boolean refregirated) {
        isRefregirated = refregirated;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

}
