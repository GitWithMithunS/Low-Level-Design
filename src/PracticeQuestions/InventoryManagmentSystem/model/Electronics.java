package PracticeQuestions.InventoryManagmentSystem.model;

import PracticeQuestions.InventoryManagmentSystem.utility.SKUGenerator;

//builder pattern for complex product.
//if we have multiple complex product then we can extend an abstract builder product class form the simple product model class. and then extend that productbuilder class to build those proiducts.
public class Electronics extends Product {

    private final String modelNumber;
    private final String brand;
    private final int warranty;
    private final boolean wirelessConnectivity;

    //super must always be the first line of child constructor
    private Electronics(Builder builder) {
        super(
                builder.name,
                SKUGenerator.generateElectronicsSKU(),
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
                double price,
                int threshold,
                int quantity,
                String brand) {

            this.name = name;
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