package PracticeQuestions.InventoryManagmentSystem.ReplenishStrategy;

import PracticeQuestions.InventoryManagmentSystem.inventory.Inventory;
import PracticeQuestions.InventoryManagmentSystem.model.Product;
import java.util.Map;

public class BulkReplenishStrategy implements ReplenishStrategy{
    private int stockQuantity;

    public BulkReplenishStrategy(int stockQuantity){
        this.stockQuantity = stockQuantity;
    }

    @Override
    public void replenishStock(Inventory inventory, Product product) {
        inventory.addProduct(product , stockQuantity);
    }

    @Override
    public void checkAndReplenishAll(Inventory inventory) {
//        System.out.println("Applying Bulk Replinsh strategy.");
        Map<String , Product> items = inventory.getAllProducts();
        for(Map.Entry<String , Product> productEntry : items.entrySet() ){
            Product p = productEntry.getValue();
            p.addQuantity(stockQuantity);
        }
    }
}
