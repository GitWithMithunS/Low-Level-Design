package PracticeQuestions.DiscountCouponSystem.strategy;

public class CappedPercentageDiscountStrategy implements IDiscountStrategy{
    private double percentageRate;
    private double cap;

    public double getCap() {
        return cap;
    }

    public void setCap(double cap) {
        this.cap = cap;
    }

    public CappedPercentageDiscountStrategy(double percentageRate , double cap){
        this.percentageRate = percentageRate;
        this.cap = cap;
    }

    public double getPercentageRate() {
        return percentageRate;
    }

    public void setPercentageRate(double percentageRate) {
        this.percentageRate = percentageRate;
    }


    @Override
    public double calculateDiscount(double amt) {
        return Math.min( percentageRate*amt/100 , cap );
    }

    @Override
    public String getOffer() {
        return "Percentage " + percentageRate + " Off upto " + cap ;
    }
}
