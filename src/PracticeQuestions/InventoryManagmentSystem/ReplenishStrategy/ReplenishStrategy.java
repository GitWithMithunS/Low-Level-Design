package PracticeQuestions.InventoryManagmentSystem.ReplenishStrategy;

import PracticeQuestions.InventoryManagmentSystem.Warehouse.Warehouse;
import PracticeQuestions.InventoryManagmentSystem.inventory.Inventory;

public interface ReplenishStrategy {
    void replenishStock(Inventory inventory);
}
