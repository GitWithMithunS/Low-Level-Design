package PracticeQuestions.DiscountCouponSystem.coupons;

import PracticeQuestions.DiscountCouponSystem.Cart;
import PracticeQuestions.DiscountCouponSystem.strategy.IDiscountStrategy;

public class BankCoupon extends Coupon {
    private IDiscountStrategy discountstrategy;
    private String bankName;
    private Double threshold;

    public BankCoupon(IDiscountStrategy discountStrategy , String bankName , Double threshold) {
        this.discountstrategy = discountStrategy;
        this.bankName = bankName;
        this.threshold = threshold;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.getPaymentBank().equalsIgnoreCase(bankName) && cart.getoriginalTotal()>=threshold;
    }

    @Override
    public double getDiscount(Cart cart) {
        return discountstrategy.calculateDiscount(cart.getCurrentTotal());
    }

    @Override
    public String getName() {
        return "Bank Coupon Offer " + discountstrategy.getOffer() + " using " + bankName;
    }

    public void setDiscountstrategy(IDiscountStrategy str){
        this.discountstrategy = str;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public double getThreshold() {
        return threshold;
    }

}
