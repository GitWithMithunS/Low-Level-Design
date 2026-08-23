package PracticeQuestions.DiscountCouponSystem.managers;

import PracticeQuestions.DiscountCouponSystem.enums.DiscountType;
import PracticeQuestions.DiscountCouponSystem.factory.CappedPercentageDiscountStrategyFactory;
import PracticeQuestions.DiscountCouponSystem.factory.FlatDiscountStrategyFactory;
import PracticeQuestions.DiscountCouponSystem.factory.IDiscountStrategyFactory;
import PracticeQuestions.DiscountCouponSystem.factory.PercentageDiscountStrategyFactory;
import PracticeQuestions.DiscountCouponSystem.strategy.CappedPercentageDiscountStrategy;
import PracticeQuestions.DiscountCouponSystem.strategy.FlatDiscountStrategy;
import PracticeQuestions.DiscountCouponSystem.strategy.IDiscountStrategy;
import PracticeQuestions.DiscountCouponSystem.strategy.PercentageDiscountStrategy;

import java.util.EnumMap;
import java.util.Map;

//Singleton
public class DiscountManager {
    private final Map<DiscountType , IDiscountStrategyFactory> registry = new EnumMap<>(DiscountType.class);

    private DiscountManager(){
        registry.put(DiscountType.FLAT , new FlatDiscountStrategyFactory());
        registry.put(DiscountType.PERCENTAGE , new PercentageDiscountStrategyFactory());
        registry.put(DiscountType.CAPPEDPERCENTAGE , new CappedPercentageDiscountStrategyFactory());
    };

    private static class Holder{
        private static final DiscountManager instance = new DiscountManager();
    }

    public static DiscountManager getInstance(){
        return Holder.instance;
    }

    public IDiscountStrategy getDiscountStrategy(DiscountType type , double param1 , double param2){
//        if(type == DiscountType.FLAT) return new FlatDiscountStrategy(param1);
//        else if(type == DiscountType.PERCENTAGE) return new PercentageDiscountStrategy(param1);
//        else if(type == DiscountType.CAPPEDPERCENTAGE) return new CappedPercentageDiscountStrategy(param1 , param2);
//        else return null;
        IDiscountStrategyFactory factory = registry.get(type);
        if(factory == null) throw new IllegalArgumentException("No factory of type " + type);
        return factory.createDiscountStrategy(param1 , param2);
    }
}
