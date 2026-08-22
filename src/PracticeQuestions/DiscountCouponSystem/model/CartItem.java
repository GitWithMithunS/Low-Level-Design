package PracticeQuestions.DiscountCouponSystem.model;

public class CartItem {
    private Product product;
    private Double totalPrice;
    private int quantity;

    public CartItem(Product product , int quantity){
        this.product = product;
        this.quantity = quantity;
    }

    public void decreaseQunatity(int q){
        quantity -= q;
    }

    public void increaseQunatity(int q){
        quantity += q;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return product.getPrice()*quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
