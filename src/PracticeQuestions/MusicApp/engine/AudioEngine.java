package PracticeQuestions.MusicApp.engine;

import PracticeQuestions.MusicApp.device.IAudioOutputDevice;
import PracticeQuestions.MusicApp.models.Song;

public class AudioEngine {
    private Song currSong;
    private boolean isPaused;

    public Song getCurrSong() {
        return currSong;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void playSong(IAudioOutputDevice audioOutputDevice , Song song){
        if(song == null){
            throw new RuntimeException("Please select a Song to play");
        }
        if(song == currSong && isPaused){
            isPaused = false;
            System.out.println("Resuming the song " + song.getName());
            return;
        }
        isPaused = false;
        currSong = song;
//        System.out.println("Playing the song " + song.getName());
        audioOutputDevice.playSong(song);
    }

    public void pauseSong(){
        if(currSong == null){
            throw new RuntimeException("No song is selcted to pause");
        }
        if(isPaused){
            throw new RuntimeException(currSong.getName() + " is already paused");
        }
        isPaused = true;
        System.out.println(currSong.getName() + " is paused");
    }

}
