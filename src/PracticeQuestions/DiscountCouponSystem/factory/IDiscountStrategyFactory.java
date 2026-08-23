package PracticeQuestions.DiscountCouponSystem.factory;

import PracticeQuestions.DiscountCouponSystem.strategy.IDiscountStrategy;

public interface IDiscountStrategyFactory{
    public IDiscountStrategy createDiscountStrategy(double param1 , double param2);
}
