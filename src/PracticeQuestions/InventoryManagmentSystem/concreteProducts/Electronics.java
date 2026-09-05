package PracticeQuestions.InventoryManagmentSystem.concreteProducts;

import PracticeQuestions.InventoryManagmentSystem.model.Product;

//builder pattern for complex product.
//if we have multiple complex product then we can extend an abstract builder product class form the simple product model class. and then extend that productbuilder class to build those proiducts.
public class Electronics extends Product {

    private final String modelNumber;
    private final String brand;
    private final int warranty;
    private final boolean wirelessConnectivity;

    private Electronics(Builder builder) {

        super(
                builder.name,
                builder.sku,
                builder.price,
                builder.threshold,
                builder.quantity
        );

        this.brand = builder.brand;
        this.modelNumber = builder.modelNumber;
        this.warranty = builder.warranty;
        this.wirelessConnectivity = builder.wirelessConnectivity;
    }

    public static class Builder {

        // Required Product fields
        private String name;
        private String sku;
        private double price;
        private int threshold;
        private int quantity;

        // Required Electronics fields
        private String brand;

        // Optional fields
        private String modelNumber = "";
        private int warranty = 2;
        private boolean wirelessConnectivity = false;

        public Builder(
                String name,
                String sku,
                double price,
                int threshold,
                int quantity,
                String brand) {

            this.name = name;
            this.sku = sku;
            this.price = price;
            this.threshold = threshold;
            this.quantity = quantity;
            this.brand = brand;
        }

        public Builder setModelNumber(String modelNumber) {
            this.modelNumber = modelNumber;
            return this;
        }

        public Builder setWarranty(int warranty) {
            this.warranty = warranty;
            return this;
        }

        public Builder setWirelessConnectivity(boolean wirelessConnectivity) {
            this.wirelessConnectivity = wirelessConnectivity;
            return this;
        }

        public Electronics build() {
            return new Electronics(this);
        }
    }
}