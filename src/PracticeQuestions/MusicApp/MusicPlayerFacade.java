package PracticeQuestions.MusicApp;

import PracticeQuestions.MusicApp.ENUM.DeviceType;
import PracticeQuestions.MusicApp.device.IAudioOutputDevice;
import PracticeQuestions.MusicApp.engine.AudioEngine;
import PracticeQuestions.MusicApp.managers.DeviceManager;
import PracticeQuestions.MusicApp.managers.PlaylistManager;
import PracticeQuestions.MusicApp.models.Song;

public class MusicPlayerFacade {
    private static MusicPlayerFacade instance = null;
    private final AudioEngine audioEngine ;
    private final DeviceManager deviceManager ;


    private MusicPlayerFacade(){
        this.audioEngine = new AudioEngine();
        this.deviceManager = DeviceManager.getDeviceManger();
    }

    public static synchronized MusicPlayerFacade getInstance(){
        if(instance == null){
            instance = new MusicPlayerFacade();
        }
        return instance;
    }

    public void connectToAudioOutputDevice(DeviceType deviceType){
        deviceManager.connectDevice(deviceType);
    }

    public void playSong(Song song){
        if(!deviceManager.isConnected()){
            throw new RuntimeException("No Audio output device is Connected ");
        }
        IAudioOutputDevice audioOutputDevice = deviceManager.getAudioOutputDevice();
        audioEngine.playSong(audioOutputDevice , song);
    }

    public void pauseSong(Song song){
        if (!audioEngine.getCurrSong().getName().equals(song.getName())) {
            throw new RuntimeException("Cannot pause \"" + song.getName() + "\"; not currently playing.");
        }
        audioEngine.pauseSong();
    }



}
