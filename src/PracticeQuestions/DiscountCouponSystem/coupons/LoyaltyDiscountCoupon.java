package PracticeQuestions.DiscountCouponSystem.coupons;

import PracticeQuestions.DiscountCouponSystem.Cart;
import PracticeQuestions.DiscountCouponSystem.model.CartItem;
import PracticeQuestions.DiscountCouponSystem.strategy.IDiscountStrategy;

public class LoyaltyDiscountCoupon extends Coupon {
    private IDiscountStrategy discountstrategy;

    public LoyaltyDiscountCoupon(IDiscountStrategy discountStrategy) {
        this.discountstrategy = discountStrategy;

    }

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.isLoyalityMember();
    }

    @Override
    public double getDiscount(Cart cart) {
        return discountstrategy.calculateDiscount(cart.getoriginalTotal());
    }

    @Override
    public String getName() {
        return "Loyalty Member Offer " + discountstrategy.getOffer();
    }

    public void setDiscountstrategy(IDiscountStrategy str){
        this.discountstrategy = str;
    }
}
