package PracticeQuestions.DiscountCouponSystem.factory;

import PracticeQuestions.DiscountCouponSystem.strategy.CappedPercentageDiscountStrategy;
import PracticeQuestions.DiscountCouponSystem.strategy.IDiscountStrategy;
import PracticeQuestions.DiscountCouponSystem.strategy.PercentageDiscountStrategy;

public class CappedPercentageDiscountStrategyFactory implements IDiscountStrategyFactory{
    @Override
    public IDiscountStrategy createDiscountStrategy(double param1, double param2) {
        return new CappedPercentageDiscountStrategy(param1 , param2);
    }
}
