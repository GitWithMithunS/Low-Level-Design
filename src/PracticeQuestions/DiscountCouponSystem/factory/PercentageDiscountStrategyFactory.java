package PracticeQuestions.DiscountCouponSystem.factory;

import PracticeQuestions.DiscountCouponSystem.strategy.FlatDiscountStrategy;
import PracticeQuestions.DiscountCouponSystem.strategy.IDiscountStrategy;
import PracticeQuestions.DiscountCouponSystem.strategy.PercentageDiscountStrategy;

public class PercentageDiscountStrategyFactory implements IDiscountStrategyFactory{
    @Override
    public IDiscountStrategy createDiscountStrategy(double param1, double param2) {
        return new PercentageDiscountStrategy(param1);
    }
}
