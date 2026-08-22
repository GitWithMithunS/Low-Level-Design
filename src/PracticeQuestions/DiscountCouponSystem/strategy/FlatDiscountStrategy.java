package PracticeQuestions.DiscountCouponSystem.strategy;

public class FlatDiscountStrategy implements IDiscountStrategy{
    private double flatRate ;

    public double getFlatRate() {
        return flatRate;
    }

    public void setFlatRate(double flatRate) {
        this.flatRate = flatRate;
    }

    public FlatDiscountStrategy(double flatRate){
        this.flatRate = flatRate;
    }

    @Override
    public double calculateDiscount(double amt) {
        return Math.min(amt, flatRate);
    }

    @Override
    public String getOffer(){
        return "Flat " + flatRate + " Off";
    }
}
