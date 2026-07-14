package PracticeQuestions.MusicApp.factories;

import PracticeQuestions.MusicApp.ENUM.PlayStrategyType;
import PracticeQuestions.MusicApp.strategies.CustomPlayStrategy;
import PracticeQuestions.MusicApp.strategies.IPlayStrategy;
import PracticeQuestions.MusicApp.strategies.RandomPlayStrategy;
import PracticeQuestions.MusicApp.strategies.SequentialPlayStrategy;

public class StrategyFactory {
    public static IPlayStrategy getPlayStrategy(PlayStrategyType playStrategyType){
        if(playStrategyType == PlayStrategyType.SEQUENTIAL){
            return new SequentialPlayStrategy();
        }else if(playStrategyType == PlayStrategyType.RANDOM){
            return new RandomPlayStrategy();
        }else if(playStrategyType == PlayStrategyType.CUSTOM){
            return new CustomPlayStrategy();
        }else {
            throw new RuntimeException("Selected Play strategy type is not available");
        }
    }
}
