package PracticeQuestions.InventoryManagmentSystem.observers;

import PracticeQuestions.InventoryManagmentSystem.model.Product;

public interface InventoryObserver {
    public void update(Product product);
}

