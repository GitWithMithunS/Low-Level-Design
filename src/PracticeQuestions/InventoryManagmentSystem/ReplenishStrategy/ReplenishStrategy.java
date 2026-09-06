package PracticeQuestions.InventoryManagmentSystem.ReplenishStrategy;

import PracticeQuestions.InventoryManagmentSystem.Warehouse.Warehouse;
import PracticeQuestions.InventoryManagmentSystem.inventory.Inventory;
import PracticeQuestions.InventoryManagmentSystem.model.Product;

public interface ReplenishStrategy {
    void replenishStock(Inventory inventory , Product product);
    void checkAndReplenishAll(Inventory inventory);
}
