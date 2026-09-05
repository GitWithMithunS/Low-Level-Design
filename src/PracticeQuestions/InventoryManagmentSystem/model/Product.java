package PracticeQuestions.InventoryManagmentSystem.model;

//this is an abstract class and not a simple class because we dont want someone to create object of this class [ product = new product()] - wrong
//y an abstract class instead of interface bcoz - we cna store data/variables in an interface like name,quantity and we would have to write this variables in the child class making it messer.
//prefer abstract when u have some abstract methods to be implemented and concrete method also present. or when all share comomon data/varibles etc.. - when classes are related.
//prefer interface when u want classes to want them to have same behaviour.

public abstract class Product {
    private String name;
    private String sku;
    private int quantity;
    private double price;
    private int threshold;

    public Product(String name, String sku, double price, int threshold, int quantity) {
        this.name = name;
        this.sku = sku;
        this.price = price;
        this.threshold = threshold;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }

    public void removeQuantity(int quantity){
        if(this.quantity <= quantity){
            this.quantity = 0;
            return;
        }
        this.quantity -= quantity;
    }
}
