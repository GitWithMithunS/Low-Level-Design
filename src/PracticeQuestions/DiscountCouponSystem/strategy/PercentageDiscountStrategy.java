package PracticeQuestions.DiscountCouponSystem.strategy;

public class PercentageDiscountStrategy implements IDiscountStrategy{
    private double percentageRate;

    public double getPercentageRate() {
        return percentageRate;
    }

    public void setPercentageRate(double percentageRate) {
        this.percentageRate = percentageRate;
    }

    public PercentageDiscountStrategy(double percentageRate){
        this.percentageRate = percentageRate;
    }

    @Override
    public double calculateDiscount(double amt) {
        return percentageRate*amt/100;
    }
}
