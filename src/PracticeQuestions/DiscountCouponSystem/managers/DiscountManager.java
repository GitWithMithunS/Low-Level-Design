package PracticeQuestions.DiscountCouponSystem.managers;

import PracticeQuestions.DiscountCouponSystem.enums.DiscountType;
import PracticeQuestions.DiscountCouponSystem.strategy.CappedPercentageDiscountStrategy;
import PracticeQuestions.DiscountCouponSystem.strategy.FlatDiscountStrategy;
import PracticeQuestions.DiscountCouponSystem.strategy.IDiscountStrategy;
import PracticeQuestions.DiscountCouponSystem.strategy.PercentageDiscountStrategy;

//Singleton
public class DiscountManager {
    private DiscountManager(){};

    private static class Holder{
        private static final DiscountManager instance = new DiscountManager();
    }

    public static DiscountManager getInstance(){
        return Holder.instance;
    }

    public IDiscountStrategy getDiscountStrategy(DiscountType type , double param1 , double param2){
        if(type == DiscountType.FLAT) return new FlatDiscountStrategy(param1);
        else if(type == DiscountType.PERCENTAGE) return new PercentageDiscountStrategy(param1);
        else if(type == DiscountType.CAPPEDPERCENTAGE) return new CappedPercentageDiscountStrategy(param1 , param2);
        else return null;
    }
}
