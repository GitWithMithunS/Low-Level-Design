package PracticeQuestions.DiscountCouponSystem.managers;

import PracticeQuestions.DiscountCouponSystem.Cart;
import PracticeQuestions.DiscountCouponSystem.coupons.Coupon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Singleton class
public class CouponManager {
    private Coupon head;
    private final Lock lock = new ReentrantLock();

    private CouponManager(){};

    private static class Holder{
        private static final CouponManager instance = new CouponManager();
    }

    public static CouponManager getInstance(){
        return Holder.instance;
    }

    public void registerCoupon(Coupon coupon){
        lock.lock();
        try{
            if(head == null){
                head = coupon;
            }else{
                Coupon curr = head;
                while(curr.getNext()!=null){
                    curr = curr.getNext();
                }
                curr.setNxt(coupon);
            }
        }finally {
            lock.unlock();
        }
    }

    public double applyAllCoupons(Cart cart){
        lock.lock();
        try{
            if(head == null){
                System.out.println("No Coupon available to Apply Discount");
                return cart.getCurrentTotal();
            }
            Coupon curr = head;
            curr.applyDiscount(cart);
            return cart.getCurrentTotal();
        }finally {
            lock.unlock();
        }
    }

    public List<String> getApplicableCoupons(Cart cart){
        lock.lock();
        try {
            List<String> res = new ArrayList<>();
            Coupon cur = head;
            while (cur != null) {
                if (cur.isApplicable(cart)) {
                    res.add(cur.getName());
                }
                cur = cur.getNext();
            }
            return res;
        } finally {
            lock.unlock();
        }
    }
}
