package PracticeQuestions.InventoryManagmentSystem.ReplenishStrategy;

import PracticeQuestions.InventoryManagmentSystem.inventory.Inventory;
import PracticeQuestions.InventoryManagmentSystem.model.Product;

import java.util.Map;

public class ThresholdReplenishStrategy implements ReplenishStrategy{

    @Override
    public void replenishStock(Inventory inventory, Product product) {
        System.out.println("Applying threshold Replinsh strategy for " + product.getName() );
        inventory.addProduct(product , 10*product.getThreshold());
        System.out.println("Added " + 10* product.getThreshold() + " quantity supplies through threshold Replinsh strategy for " + product.getName() );
    }

    @Override
    public void checkAndReplenishAll(Inventory inventory) {
//        System.out.println("Applying threshold Replinsh strategy.");
        Map<String , Product> items = inventory.getAllProducts();
        for(Map.Entry<String , Product> productEntry : items.entrySet() ){
            Product p = productEntry.getValue();
            if(p.getQuantity() < p.getThreshold()){
                p.setQuantity(10*p.getThreshold());
            }
        }
    }
}
