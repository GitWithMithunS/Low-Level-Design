package PracticeQuestions.DiscountCouponSystem.coupons;

import PracticeQuestions.DiscountCouponSystem.Cart;
import PracticeQuestions.DiscountCouponSystem.model.CartItem;
import PracticeQuestions.DiscountCouponSystem.strategy.IDiscountStrategy;

public class SeasonalOffer extends Coupon {
    private String category;
    private IDiscountStrategy discountstrategy;

    public SeasonalOffer(IDiscountStrategy discountStrategy) {
        this.discountstrategy = discountStrategy;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        for(CartItem cartItem : cart.getItemList()){
            if(cartItem.getProduct().getCategory().equals(category) ){
                return true;
            }
        }
        return false;
    }

    @Override
    public double getDiscount(Cart cart) {
        double dis = 0;
        for(CartItem cartItem : cart.getItemList()){
            if(cartItem.getProduct().getCategory().equals(category) ){
                dis += discountstrategy.calculateDiscount(cartItem.getTotalPrice());
            }
        }
        return dis;
    }

    @Override
    public void setIsCombinable() {

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
