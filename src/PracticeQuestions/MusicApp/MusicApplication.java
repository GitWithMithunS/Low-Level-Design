package PracticeQuestions.MusicApp;

import PracticeQuestions.MusicApp.ENUM.DeviceType;
import PracticeQuestions.MusicApp.device.IAudioOutputDevice;
import PracticeQuestions.MusicApp.models.Song;

public class MusicApplication {
    private static MusicApplication instance = null;
    private final MusicPlayerFacade musicPlayerFacade;

    private MusicApplication(){
        musicPlayerFacade = MusicPlayerFacade.getInstance();
    };

    public static synchronized MusicApplication getInstance(){
        if(instance == null){
            instance = new MusicApplication();
        }
        return instance;
    }

    public void connectToAudioOutputDevice(DeviceType deviceType){
        musicPlayerFacade.connectToAudioOutputDevice(deviceType);
    }

    public void playSong(Song song){
        musicPlayerFacade.playSong(song);
    }

    public void pauseSong(Song song){
        musicPlayerFacade.pauseSong(song);
    }
}
