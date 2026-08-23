package PracticeQuestions.DiscountCouponSystem;

import PracticeQuestions.DiscountCouponSystem.coupons.BankCoupon;
import PracticeQuestions.DiscountCouponSystem.coupons.BulkDiscountCoupon;
import PracticeQuestions.DiscountCouponSystem.coupons.LoyaltyDiscountCoupon;
import PracticeQuestions.DiscountCouponSystem.coupons.SeasonalOfferCoupon;
import PracticeQuestions.DiscountCouponSystem.enums.DiscountType;
import PracticeQuestions.DiscountCouponSystem.managers.CouponManager;
import PracticeQuestions.DiscountCouponSystem.managers.DiscountManager;
import PracticeQuestions.DiscountCouponSystem.model.Product;
import PracticeQuestions.DiscountCouponSystem.strategy.IDiscountStrategy;

import java.util.List;

public class DiscountCouponSystem {
    public static void main(String[] args){
        System.out.println("Discount Coupon System\n");

        //client , chooses Discount Strategy
        DiscountManager discountManager = DiscountManager.getInstance();
        IDiscountStrategy bankDiscount = discountManager.getDiscountStrategy(DiscountType.CAPPEDPERCENTAGE , 10 , 2000);
        IDiscountStrategy bulkDiscount = discountManager.getDiscountStrategy(DiscountType.FLAT , 3000 , 0);
        IDiscountStrategy loyaltyDiscount = discountManager.getDiscountStrategy(DiscountType.PERCENTAGE , 15 , 0);
        IDiscountStrategy seasonalDiscount = discountManager.getDiscountStrategy(DiscountType.CAPPEDPERCENTAGE , 12 , 500);

        //client , setups DiscountCoupons
        CouponManager couponManager = CouponManager.getInstance();
        couponManager.registerCoupon(new BankCoupon(bankDiscount , "SBI" , 10000.0) );
        couponManager.registerCoupon(new LoyaltyDiscountCoupon(loyaltyDiscount));
        couponManager.registerCoupon(new BulkDiscountCoupon(bulkDiscount , 15000));
        couponManager.registerCoupon(new SeasonalOfferCoupon(seasonalDiscount , "Clothing"));

        //just faking a users cart
        Product p1 = new Product("Winter Jacket", "Clothing", 1000.0);
        Product p2 = new Product("Smartphone", "Electronics", 23000.0);
        Product p3 = new Product("Jeans", "Clothing", 1000.0);
        Product p4 = new Product("Headphones", "Electronics", 3000.0);

        Cart cart = new Cart();
        cart.addProduct(p1, 3);
        cart.addProduct(p2, 1);
        cart.addProduct(p3, 2);
        cart.addProduct(p4, 1);
        cart.setLoyalityMember(true);
        cart.setPaymentBank("SBI");
        cart.printCart();


        //applying discount on the user cart
//        System.out.println("Original Cart Total: " + cart.getoriginalTotal() + " Rs");
        System.out.println("--------------------------------------------------------\n");

        List<String> applicable = couponManager.getApplicableCoupons(cart);
        System.out.println("Applicable Coupons:");
        for (String name : applicable) {
            System.out.println(" - " + name );
        }

        System.out.println("--------------------------------------------------------\n");
        double finalTotal = couponManager.applyAllCoupons(cart);

        System.out.println("--------------------------------------------------------");
        System.out.println("[Final Cart Total after discounts] : " + finalTotal + " Rs");

    }
}
