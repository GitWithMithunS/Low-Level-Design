package PracticeQuestions.InventoryManagmentSystem.inventory;

import PracticeQuestions.InventoryManagmentSystem.model.Product;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private final Map<String, Product> items;

    public Inventory() {
        this.items = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {

        String sku = product.getSku();

        if(items.containsKey(sku)) {
            items.get(sku).addQuantity(quantity);
        }
        else {
            product.setQuantity(quantity);
            items.put(sku, product);
        }
    }

    public boolean removeProduct(String sku, int quantity) {

        Product product = items.get(sku);

        if(product == null) {
            return false;
        }

        if(product.getQuantity() < quantity) {
            return false;
        }

        product.removeQuantity(quantity);

        if(product.getQuantity() == 0) {
            items.remove(sku);
        }

        return true;
    }

    public Product getProductBySku(String sku) {
        return items.get(sku);
    }

    public int getAvailableQuantity(String sku) {
        Product product = items.get(sku);
        return product == null ? 0 : product.getQuantity();
    }

    //sending the map as is cna be mutated adn may case irregularities. [security threat]
    public Map<String, Product> getAllProducts() {
//        return items;
        return Collections.unmodifiableMap(items);
    }
}