package PracticeQuestions.DiscountCouponSystem.coupons;

import PracticeQuestions.DiscountCouponSystem.Cart;

public abstract class Coupon {
    private Coupon nxt;

    public void setNxt(Coupon nxt){
        this.nxt = nxt;
    }

    //Template method
    public void applyDiscount(Cart cart){
        if(isApplicable(cart)){
            System.out.println(getName() + " is Applicable on your cart");
            double dis = getDiscount(cart);
            cart.applyDiscount(dis);
            System.out.println(getName() + " applied on Cart ");
        }

        if(!isCombinable()){
            return;
        }

        if(nxt != null){
            nxt.applyDiscount(cart);
        }
    }

    public boolean isCombinable() {
        return true;
    };

    public abstract boolean isApplicable(Cart cart);
    public abstract double getDiscount(Cart cart);
    public abstract void setIsCombinable();
    public abstract String getName();

}
