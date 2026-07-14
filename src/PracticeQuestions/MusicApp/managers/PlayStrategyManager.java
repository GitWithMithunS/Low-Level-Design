package PracticeQuestions.MusicApp.managers;

import PracticeQuestions.MusicApp.ENUM.PlayStrategyType;
import PracticeQuestions.MusicApp.factories.DeviceFactory;
import PracticeQuestions.MusicApp.factories.StrategyFactory;
import PracticeQuestions.MusicApp.strategies.IPlayStrategy;

public class PlayStrategyManager {
    
    private PlayStrategyManager(){};

    private static class Holder{
        private static final PlayStrategyManager instance = new PlayStrategyManager();
    }

    public static PlayStrategyManager getInstance(){
        return Holder.instance;
    }

    public IPlayStrategy getPlayStrategy(PlayStrategyType playStrategyType){
        return StrategyFactory.getPlayStrategy(playStrategyType);
    }
}
