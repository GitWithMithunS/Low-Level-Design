package PracticeQuestions.DiscountCouponSystem.strategy;

public interface IDiscountStrategy {
    double calculateDiscount(double amt);
    String getOffer();
}