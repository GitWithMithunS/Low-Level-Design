package PracticeQuestions.InventoryManagmentSystem.inventoryManager;

import PracticeQuestions.InventoryManagmentSystem.ReplenishStrategy.ReplenishStrategy;
import PracticeQuestions.InventoryManagmentSystem.ReplenishStrategy.ThresholdReplenishStrategy;
import PracticeQuestions.InventoryManagmentSystem.Warehouse.Warehouse;
import PracticeQuestions.InventoryManagmentSystem.model.Product;
import PracticeQuestions.InventoryManagmentSystem.observers.InventoryObserver;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    private final List<Warehouse> warehouses;
    private final List<InventoryObserver> observers;

    private ReplenishStrategy replenishStrategy;

    private InventoryManager( ReplenishStrategy replenishStrategy) {
        this.warehouses = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.replenishStrategy = replenishStrategy;
    }

    private static class Holder {
        private static final InventoryManager INSTANCE = new InventoryManager(new ThresholdReplenishStrategy());
    }

    public static InventoryManager getInstance() {
        return Holder.INSTANCE;
    }

    public void setReplenishStrategy( ReplenishStrategy replenishStrategy) {
        this.replenishStrategy = replenishStrategy;
    }

    public void addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
    }

    public void removeWarehouse(Warehouse warehouse) {
        warehouses.remove(warehouse);
    }

    public void addObserver( InventoryObserver observer) {
        observers.add(observer);
    }

    public void removeObserver( InventoryObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers( Warehouse warehouse, Product product, int remainingQty) {
        for(InventoryObserver observer : observers) {
            observer.update( warehouse, product, remainingQty);
        }
    }

    public void addStock(Warehouse warehouse, String sku, int qty) {
        Product product = warehouse.getProduct(sku);
        warehouse.addStock(product, qty);
    }

    public void removeStock(Warehouse warehouse, String sku , int qty) {
        boolean removed = warehouse.removeStock(sku, qty);

        if(!removed) return;

        Product product = warehouse.getProduct(sku);
        int currentQty = product.getQuantity();

        if(currentQty < product.getThreshold()) {
            notifyObservers( warehouse, product, currentQty);
            replenishStrategy.replenishStock( warehouse.getInventory(), product);
        }
    }

    public int checkStock(Warehouse warehouse, String sku) {
        return warehouse.getQuantity(sku);
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void checkAndReplenishAllWarehouses(){
        for(Warehouse warehouse : warehouses){
            replenishStrategy.checkAndReplenishAll(warehouse.getInventory());
        }
    }

    public void listAllWarehousesInventory(){
        for(Warehouse w : warehouses){
            w.listAllProducts();
        }
    }
}