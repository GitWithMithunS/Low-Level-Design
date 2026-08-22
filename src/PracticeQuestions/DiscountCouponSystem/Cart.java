package PracticeQuestions.DiscountCouponSystem;

import PracticeQuestions.DiscountCouponSystem.model.CartItem;
import PracticeQuestions.DiscountCouponSystem.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> itemList = new ArrayList<>();
    private boolean loyaltyMember;
    private String paymentBank = null;
    private double originalTotal;
    private double currentTotal;
//    private CouponManager;

    public Cart(boolean loyaltyMember){
      this.loyaltyMember = loyaltyMember;
        currentTotal = 0;
        originalTotal = 0;
    }

    public void addProduct(Product product, int quantity){
        CartItem cartItem = new CartItem(product , quantity);
        originalTotal += cartItem.getTotalPrice();
    }

    public void applyDiscount(double discountAmt){
        currentTotal -= discountAmt;
        if(currentTotal <0) currentTotal = 0;
    }

    //getters and setters
    public double getoriginalTotal(){
        return originalTotal;
    }

    public double getCurrentTotal(){
        return currentTotal;
    }

    public String getPaymentBank() {
        return paymentBank;
    }

    public void setPaymentBank(String paymentBank) {
        this.paymentBank = paymentBank;
    }

    public List<CartItem> getItemList() {
        return itemList;
    }

    public boolean isLoyalityMember() {
        return loyaltyMember;
    }

    public void setLoyalityMember(boolean loyaltyMember) {
        this.loyaltyMember = loyaltyMember;
    }
}
