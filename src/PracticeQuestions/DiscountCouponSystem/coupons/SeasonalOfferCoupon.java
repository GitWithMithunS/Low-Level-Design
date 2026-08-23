package PracticeQuestions.DiscountCouponSystem.coupons;

import PracticeQuestions.DiscountCouponSystem.Cart;
import PracticeQuestions.DiscountCouponSystem.model.CartItem;
import PracticeQuestions.DiscountCouponSystem.strategy.IDiscountStrategy;

public class SeasonalOfferCoupon extends Coupon {
    private String category;
    private IDiscountStrategy discountstrategy;

    public SeasonalOfferCoupon(IDiscountStrategy discountStrategy , String category) {
        this.discountstrategy = discountStrategy;
        this.category = category;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        for(CartItem cartItem : cart.getItemList()){
            if(cartItem.getProduct().getCategory().equalsIgnoreCase(category) ){
                return true;
            }
        }

        return false;
    }

    @Override
    public double getDiscount(Cart cart) {
        double disapplicables = 0;
        for(CartItem cartItem : cart.getItemList()){
            if(cartItem.getProduct().getCategory().equals(category) ){
                disapplicables += cartItem.getTotalPrice();
            }
        }
        return discountstrategy.calculateDiscount(disapplicables);
    }


    @Override
    public String getName() {
        return "Seasonal Offer " + discountstrategy.getOffer();
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setDiscountstrategy(IDiscountStrategy str){
        this.discountstrategy = str;
    }
}