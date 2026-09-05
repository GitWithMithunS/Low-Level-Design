package PracticeQuestions.InventoryManagmentSystem.inventory;

import PracticeQuestions.InventoryManagmentSystem.model.Product;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<String , Product> items;  //sku -> product

    public Inventory(){
        items = new HashMap<>();
    }

    public Product addProduct(Product product , int quantity){
        String sku = product.getSku();
        if(items.containsKey(sku)){
            Product p = items.get(sku);
            p.addQuantity(quantity);
        }else{
            product.setQuantity(quantity);
            items.put(sku , product);
        }
        return items.get(product);
    }

    public boolean removeProduct(String sku , int quantity){
        if(items.containsKey(sku)){
            System.out.println("No Product with " + sku + " present in the warehouse" );
            return false;
        }
        Product p = items.get(sku);
        p.removeQuantity(quantity);
        if(p.getQuantity() == 0)  items.remove(sku);

        System.out.println("Product " + p.getName() + " removed from inventory as quantity is now zero.");
        return true;
    }

    public void listAllProducts(){
        System.out.println("----------Listing All Stock in the Warehouse ----------");
        for(Map.Entry<String, Product> p : items.entrySet()){
            System.out.println("Name: " + p.getKey() + " -> quantity: " + p.getValue() + " price: " + p.getValue().getPrice() );
        }
        System.out.println("--------------------------------------------------------");
    }

    public Product getProductBySku(String sku){
        if(items.containsKey(sku)){
            System.out.println("No Product with " + sku + " present in the warehouse" );
            return null;
        }
        return items.get(sku);
    }

    public int getAvailableQuantity(String sku){
        if(!items.containsKey(sku)) return 0;
        return items.get(sku).getQuantity();
    }

    public Map<String , Product> getInventory(){
        return items;
    }

    public boolean isBelowThreshold(String sku){
        Product p = items.get(sku);
        if(p.getQuantity() < p.getThreshold()) return true;
        return false;
    }
}
