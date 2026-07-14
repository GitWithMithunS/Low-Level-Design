package PracticeQuestions.MusicApp;

import PracticeQuestions.MusicApp.ENUM.DeviceType;
import PracticeQuestions.MusicApp.ENUM.PlayStrategyType;
import PracticeQuestions.MusicApp.managers.PlaylistManager;
import PracticeQuestions.MusicApp.models.Song;

import java.util.ArrayList;
import java.util.List;

public class MusicApplication {
    private static MusicApplication instance = null;
    private final MusicPlayerFacade musicPlayerFacade;
    private final List<Song> songLibrary;

    private MusicApplication(){
        songLibrary = new ArrayList<>();
        loadPlayer();
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

    public void createSongInLibrary(String title, String artist, String path) {
        Song newSong = new Song(title, artist, path);
        songLibrary.add(newSong);
    }

    public Song findSongByTitle(String title) {
        for (Song s : songLibrary) {
            if (s.getName().equals(title)) {
                return s;
            }
        }
        return null;
    }

    public void createPlaylist(String playlistName) {
        PlaylistManager.getInstance().createPlaylist(playlistName);
    }

    public void addSongToPlaylist(String playlistName, String songTitle) {
        Song song = findSongByTitle(songTitle);
        if (song == null) {
            throw new RuntimeException("Song \"" + songTitle + "\" not found in library.");
        }
        PlaylistManager.getInstance().addSongToPlaylist( song , playlistName);
    }

    public void selectPlayStrategy(PlayStrategyType strategyType) {
        musicPlayerFacade.setCurrPlayStrategy(strategyType);
    }

    public void loadPlaylist(String playlistName) {
        musicPlayerFacade.setPlaylist(playlistName);
    }

    public void playSingleSong(String songTitle) {
        Song song = findSongByTitle(songTitle);
        if (song == null) {
            throw new RuntimeException("Song \"" + songTitle + "\" not found.");
        }
        musicPlayerFacade.playSong(song);
    }

    public void pauseCurrentSong(String songTitle) {
        Song song = findSongByTitle(songTitle);
        if (song == null) {
            throw new RuntimeException("Song \"" + songTitle + "\" not found.");
        }
        musicPlayerFacade.pauseSong(song);
    }


    public void playAll(){
        musicPlayerFacade.playAll();
    }

    public void playNxt(){
        musicPlayerFacade.playNext();
    }

    public void playPrev(){
        musicPlayerFacade.playPrevious();
    }

    public void addSongToNext(String songName){
        Song song = findSongByTitle(songName);
        if(song == null){
            throw  new RuntimeException("No song found with name " + songName);
        }
        musicPlayerFacade.addToNext(song);
    }

    private void loadPlayer(){
        // Populate Song Library
        createSongInLibrary("Naatu Naatu", "Rahul Sipligunj", "/music/naatu_naatu.mp3");
        createSongInLibrary("Pasoori", "Ali Sethi", "/music/pasoori.mp3");
        createSongInLibrary("Malang", "Ved Sharma", "/music/malang.mp3");
        createSongInLibrary("Kaise Hua", "Vishal Mishra", "/music/kaise_hua.mp3");
        createSongInLibrary("Senorita", "Farhan Akhtar", "/music/senorita.mp3");
        createSongInLibrary("The Nights", "Avicii", "/music/the_nights.mp3");
        createSongInLibrary("Thunder", "Imagine Dragons", "/music/thunder.mp3");
        createSongInLibrary("Levitating", "Dua Lipa", "/music/levitating.mp3");
    }
}
