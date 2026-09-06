package PracticeQuestions.InventoryManagmentSystem.Warehouse;

import PracticeQuestions.InventoryManagmentSystem.inventory.Inventory;
import PracticeQuestions.InventoryManagmentSystem.model.Product;

import java.util.Collection;
import java.util.Map;

public class Warehouse {

    private final String name;
    private final String location;
    private final int id;

    private final Inventory inventory;

    public Warehouse(String name, String location, int id) {
        this.name = name;
        this.location = location;
        this.id = id;
        this.inventory = new Inventory();
    }

    public void addStock(Product product, int quantity) {
        inventory.addProduct(product, quantity);
    }

    public boolean removeStock(String sku, int quantity) {
        return inventory.removeProduct(sku, quantity);
    }

    public int getQuantity(String sku) {
        return inventory.getAvailableQuantity( sku );
    }

    public Product getProduct(String sku) {
        return inventory.getProductBySku(sku);
    }

    public Map<String, Product> getAllProducts() {
        return inventory.getAllProducts();
    }

    public void listAllProducts(){
        Map<String , Product> items = inventory.getAllProducts();
        System.out.println("------Listing All Stock in the Warehouse [" + name + "] --------");
        for(Map.Entry<String, Product> p : items.entrySet()){
            System.out.println("Name: [" + p.getKey() +  "] "+ p.getValue().getName() + " -> quantity: " + p.getValue().getQuantity() + " -> price: " + p.getValue().getPrice() );
        }
        System.out.println("---------------------------------------------------------------------");
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getId() {
        return id;
    }

    public Inventory getInventory(){
        return inventory;
    }
}