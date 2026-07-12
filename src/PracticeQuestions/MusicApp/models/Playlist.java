package PracticeQuestions.MusicApp.models;

import java.util.*;

public class Playlist {
    private final String name;
    private final List<Song> songs;

    public Playlist(String name){
        this.name = name;
        songs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public int getSize(){
        return songs.size();
    }

    public void addSong(Song song){
        if(song == null){
            throw new RuntimeException("Song cant be null or empty");
        }
        songs.add(song);
    }

    public void removeSong(Song song){
        songs.remove(song);
    }

}
