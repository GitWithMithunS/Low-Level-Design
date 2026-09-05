package PracticeQuestions.InventoryManagmentSystem.observers;

import PracticeQuestions.InventoryManagmentSystem.model.Product;

public class SupplierObserver implements InventoryObserver{
    private String supplierName;
    private String email;

    public SupplierObserver(String supplierName , String email){
        this.supplierName = supplierName;
        this.email = email;
    }

    @Override
    public void update(Product product) {
        if (product.getQuantity() < product.getThreshold()) {
            // Send email notification to supplier
            System.out.println("Notification sent to " + supplierName
                    + " for low stock of " + product.getName());
        }
    }


}
