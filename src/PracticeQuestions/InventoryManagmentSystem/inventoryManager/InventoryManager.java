package PracticeQuestions.InventoryManagmentSystem.inventoryManager;

import PracticeQuestions.InventoryManagmentSystem.ReplenishStrategy.ReplenishStrategy;
import PracticeQuestions.InventoryManagmentSystem.ReplenishStrategy.ThresholdReplenishStrategy;
import PracticeQuestions.InventoryManagmentSystem.Warehouse.Warehouse;
import PracticeQuestions.InventoryManagmentSystem.model.Product;
import PracticeQuestions.InventoryManagmentSystem.observers.InventoryObserver;

import java.util.ArrayList;
import java.util.List;

///singleton class
public class InventoryManager {
    private final List<Warehouse> warehouseLst;
    private final List<InventoryObserver> observers;
    private ReplenishStrategy replenishStrategy;

    private InventoryManager(ReplenishStrategy replenishStrategy){
        warehouseLst = new ArrayList<>();
        observers = new ArrayList<>();
        this.replenishStrategy = replenishStrategy;
    }

    public static class Holder{
        public static final InventoryManager instance = new InventoryManager(new ThresholdReplenishStrategy());
    }

    public InventoryManager getInstance(){
        return Holder.instance;
    }

    public void setReplenishStrategy(ReplenishStrategy replenishStrategy){
        this.replenishStrategy = replenishStrategy;
    }

    public void addWareHouse(Warehouse warehouse){
        warehouseLst.add(warehouse);
    }

    public void removeWareHouse(Warehouse warehouse){
        warehouseLst.remove(warehouse);
    }

    public void addInventoryObservers(InventoryObserver inventoryObserver){
        observers.add(inventoryObserver);
    }

    public void removeInventoryObservers(InventoryObserver inventoryObserver){
        observers.remove(inventoryObserver);
    }

    public void notifyObservers(Warehouse warehouse){
    }



}
