package PracticeQuestions.InventoryManagmentSystem.ReplenishStrategy;

import PracticeQuestions.InventoryManagmentSystem.inventory.Inventory;
import PracticeQuestions.InventoryManagmentSystem.model.Product;

import java.util.Map;

public class ThresholdReplenishStrategy implements ReplenishStrategy{
    @Override
    public void replenishStock(Inventory inventory ) {
        System.out.println("Applying threshold Replinsh strategy.");
        Map<String , Product> items = inventory.getInventory();
        for(Map.Entry<String , Product> productEntry : items.entrySet() ){
            Product p = productEntry.getValue();
            if(p.getQuantity() < p.getThreshold()){
                p.setQuantity(10*p.getThreshold());
            }
        }
    }
}
