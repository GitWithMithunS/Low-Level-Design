package PracticeQuestions.DiscountCouponSystem.factory;

import PracticeQuestions.DiscountCouponSystem.strategy.FlatDiscountStrategy;
import PracticeQuestions.DiscountCouponSystem.strategy.IDiscountStrategy;

public class FlatDiscountStrategyFactory implements IDiscountStrategyFactory{
    @Override
    public IDiscountStrategy createDiscountStrategy(double param1, double param2) {
        return new FlatDiscountStrategy(param1);
    }
}
