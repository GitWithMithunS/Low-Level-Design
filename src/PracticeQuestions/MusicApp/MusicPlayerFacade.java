package PracticeQuestions.MusicApp;

import PracticeQuestions.MusicApp.ENUM.DeviceType;
import PracticeQuestions.MusicApp.ENUM.PlayStrategyType;
import PracticeQuestions.MusicApp.device.IAudioOutputDevice;
import PracticeQuestions.MusicApp.engine.AudioEngine;
import PracticeQuestions.MusicApp.managers.DeviceManager;
import PracticeQuestions.MusicApp.managers.PlayStrategyManager;
import PracticeQuestions.MusicApp.managers.PlaylistManager;
import PracticeQuestions.MusicApp.models.Playlist;
import PracticeQuestions.MusicApp.models.Song;
import PracticeQuestions.MusicApp.strategies.IPlayStrategy;

public class MusicPlayerFacade {
    private static MusicPlayerFacade instance = null;
    private final AudioEngine audioEngine ;
    private final DeviceManager deviceManager ;
    private final PlayStrategyManager playStrategyManager;
    private final PlaylistManager playlistManager;
    private IPlayStrategy currPlayStrategy;
    private Playlist currPlaylist;

    private MusicPlayerFacade(){
        this.audioEngine = new AudioEngine();
        this.deviceManager = DeviceManager.getDeviceManger();
        this.playStrategyManager= PlayStrategyManager.getInstance();
        this.playlistManager = PlaylistManager.getInstance();
        currPlayStrategy = null;
        currPlaylist = null;
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

    public void setCurrPlayStrategy(PlayStrategyType playStrategyType){
        currPlayStrategy = playStrategyManager.getPlayStrategy(playStrategyType);
    }

    public void setPlaylist(String playlistName){
        if(currPlayStrategy == null){
            throw new RuntimeException("PlayStrategy is not selected yet to play the playlist");
        }
        currPlaylist = playlistManager.getPlaylist(playlistName);
        currPlayStrategy.setPlaylist(currPlaylist);
    }

    public void playNext(){
        if(currPlaylist == null){
            throw new RuntimeException("Playlist not selected yet");
        }
        if(currPlayStrategy.hasNext()){
            Song nxtSong = currPlayStrategy.next();
            IAudioOutputDevice aud = deviceManager.getAudioOutputDevice();
            audioEngine.playSong(aud , nxtSong);
        }else{
            System.out.println("No next song to play");
        }
    }

    public void playPrevious(){
        if(currPlaylist == null){
            throw new RuntimeException("Playlist not selected yet");
        }
        if(!currPlayStrategy.hasPrevious()){
            System.out.println("No Previous song to play");
        }
        Song prevSong = currPlayStrategy.previous();
        IAudioOutputDevice aud = deviceManager.getAudioOutputDevice();
        audioEngine.playSong(aud , prevSong);
    }

    public void playAll(){
        if(currPlaylist == null){
            throw new RuntimeException("Playlist not selected yet");
        }
        IAudioOutputDevice aud = deviceManager.getAudioOutputDevice();
        while(currPlayStrategy.hasNext()){
            Song nxtSong = currPlayStrategy.next();
            audioEngine.playSong(aud , nxtSong);
        }
        System.out.println("End of the Playlist");
    }

    public void addToNext(Song song){
        if(currPlayStrategy.getPlayStrategyType() != PlayStrategyType.CUSTOM){
            System.out.println("Select Custom Play Strategy to enqueue song");
            return;
        }
        currPlayStrategy.addToNext(song);
    }

}
