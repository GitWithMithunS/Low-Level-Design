package PracticeQuestions.InventoryManagmentSystem;

import PracticeQuestions.InventoryManagmentSystem.Warehouse.Warehouse;
import PracticeQuestions.InventoryManagmentSystem.model.Clothes;
import PracticeQuestions.InventoryManagmentSystem.model.Electronics;
import PracticeQuestions.InventoryManagmentSystem.inventoryManager.InventoryManager;
import PracticeQuestions.InventoryManagmentSystem.observers.DashboardAlertSystem;
import PracticeQuestions.InventoryManagmentSystem.observers.SupplierObserver;

import java.util.Arrays;

public class InventoryManagementDemo {

    public static void main(String[] args) {

        // ==========================
        // Inventory Manager
        // ==========================
        InventoryManager inventoryManager = InventoryManager.getInstance();

        // ==========================
        // Warehouses
        // ==========================
        Warehouse bangaloreWarehouse = new Warehouse("Bangalore Warehouse", "Bangalore", 1);
        Warehouse chennaiWarehouse = new Warehouse("Chennai Warehouse", "Chennai", 102);
        Warehouse mumbaiWarehouse = new Warehouse("Mumbai Warehouse", "Mumbai", 2);

        inventoryManager.addWarehouse(bangaloreWarehouse);
        inventoryManager.addWarehouse(chennaiWarehouse);
        inventoryManager.addWarehouse(mumbaiWarehouse);

        // ==========================
        // Observers
        // ==========================
        SupplierObserver supplierObserver = new SupplierObserver("Dipender Goyal Supplier", "dipender@supplier.com");

        DashboardAlertSystem dashboardObserver = new DashboardAlertSystem(
                                        "HIGH",
                                        Arrays.asList(
                                                "admin1",
                                                "admin2"
                                        )
                                );

        inventoryManager.addObserver(supplierObserver);
        inventoryManager.addObserver(dashboardObserver);

        // ==========================
        // Clothes Product
        // ==========================
        Clothes tshirt = new Clothes("Puma T-Shirt", 999.0, 20,  7);
        tshirt.setColor("Black");
        tshirt.setSize(42);
        Clothes pants = new Clothes("Puma Pants", 1599.0, 30,  22);


        // ==========================
        // Electronics Product
        // ==========================
        Electronics MacBookPro =new Electronics.Builder("MacBook Pro", 180000, 10, 50,"Apple")
                        .setModelNumber("M4-PRO")
                        .setWarranty(3)
                        .setWirelessConnectivity(true)
                        .build();

        // ==========================
        // Add Stock
        // ==========================
        bangaloreWarehouse.addStock(tshirt, tshirt.getQuantity());
        bangaloreWarehouse.addStock(MacBookPro, MacBookPro.getQuantity());
        bangaloreWarehouse.addStock(pants, pants.getQuantity());
        chennaiWarehouse.addStock(pants, pants.getQuantity());
        mumbaiWarehouse.addStock(pants, pants.getQuantity());

        // ==========================
        // Display Inventory
        // ==========================
        inventoryManager.listAllWarehousesInventory();

        // ==========================
        // Check Stock
        // ==========================
        System.out.println("\nMacBookPro Stock: " + inventoryManager.checkStock(bangaloreWarehouse, MacBookPro.getSku()));

        // ==========================
        // Remove Stock
        // Trigger Observer
        // ==========================
        System.out.println("\nAfter Removing 45 MacBookPro");
        inventoryManager.removeStock(bangaloreWarehouse, MacBookPro.getSku(), 45);


        bangaloreWarehouse.listAllProducts();

        // ==========================
        // Remove More Stock
        // Trigger again
        // ==========================
        System.out.println("\nAfter Removing 3 More MacBookPro");
        inventoryManager.removeStock(bangaloreWarehouse, MacBookPro.getSku(), 3);
        bangaloreWarehouse.listAllProducts();


        // ==========================
        // Replenish All Warehouses
        // ==========================
        System.out.println("\nAfter Global Replenishment of Stocks");
        inventoryManager.checkAndReplenishAllWarehouses();

        inventoryManager.listAllWarehousesInventory();
    }
}