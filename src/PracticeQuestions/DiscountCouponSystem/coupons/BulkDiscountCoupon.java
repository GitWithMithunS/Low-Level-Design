package PracticeQuestions.DiscountCouponSystem.coupons;

import PracticeQuestions.DiscountCouponSystem.Cart;
import PracticeQuestions.DiscountCouponSystem.strategy.IDiscountStrategy;

public class BulkDiscountCoupon extends Coupon {
    private IDiscountStrategy discountstrategy;
    private double threshold;

    public BulkDiscountCoupon(IDiscountStrategy discountStrategy , double threshold) {
        this.discountstrategy = discountStrategy;
        this.threshold = threshold;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.getoriginalTotal() >= threshold;
    }

    @Override
    public double getDiscount(Cart cart) {
        return discountstrategy.calculateDiscount(cart.getCurrentTotal());
    }

    @Override
    public String getName() {
        return "Bulk Purchase Offer " + discountstrategy.getOffer();
    }

    public void setDiscountstrategy(IDiscountStrategy str){
        this.discountstrategy = str;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public double getThreshold() {
        return threshold;
    }

}
