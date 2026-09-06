package PracticeQuestions.InventoryManagmentSystem.observers;

import PracticeQuestions.InventoryManagmentSystem.Warehouse.Warehouse;
import PracticeQuestions.InventoryManagmentSystem.model.Product;

public interface InventoryObserver {
    public void update(Warehouse warehouse, Product product , int remainingQuantity);
}

