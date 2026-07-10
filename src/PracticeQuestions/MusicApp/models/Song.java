package PracticeQuestions.MusicApp.models;

public class Song {
    private String name;
    private String artist;
    private String path;

    public Song(String name, String artist, String path){
        this.name = name;
        this.artist = artist;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getPath() {
        return path;
    }

}
