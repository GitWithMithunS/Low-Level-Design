package PracticeQuestions.InventoryManagmentSystem.factory;

import PracticeQuestions.InventoryManagmentSystem.model.Product;

public interface ProductFactory<T> {
     Product createProduct(T request);
}
