package PracticeQuestions.InventoryManagmentSystem.observers;

import PracticeQuestions.InventoryManagmentSystem.Warehouse.Warehouse;
import PracticeQuestions.InventoryManagmentSystem.model.Product;

public class SupplierObserver implements InventoryObserver{
    private String supplierName;
    private String email;

    public SupplierObserver(String supplierName , String email){
        this.supplierName = supplierName;
        this.email = email;
    }


    @Override
    public void update(Warehouse warehouse, Product product, int remainingQuantity) {
        if (product.getQuantity() < product.getThreshold()) {
            // Send email notification to supplier
            System.out.println("[Notification] : Notifying " + supplierName
                    + " for low stock of " + product.getName() + " in " + warehouse.getName() + " located at " + warehouse.getLocation());
        }
    }
}
