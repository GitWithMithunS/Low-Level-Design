package PracticeQuestions.InventoryManagmentSystem.Warehouse;

import PracticeQuestions.InventoryManagmentSystem.inventory.Inventory;
import PracticeQuestions.InventoryManagmentSystem.model.Product;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private String name;
    private String location;
    private int id;
    private Inventory inventory;

    public Warehouse(String name , String location , int id){
        this.name = name;
        this.location = location;
        this.id = id;
    }

    public void addProduct(Product product , int quantity){
        Product p = inventory.addProduct(product , quantity);
        System.out.println(quantity + " units of " + product.getName()
                + " (SKU: " + p.getSku() + ") added to " + name
                + ". New quantity: " + p.getQuantity() );
    }

    //delegation methods
    public void removeProduct(String sku , int quantity){
        boolean removed = inventory.removeProduct(sku , quantity);
    }

    public void listInventory(){
        inventory.listAllProducts();
    }

    public Inventory getInventory(){
        return inventory;
    }


}
