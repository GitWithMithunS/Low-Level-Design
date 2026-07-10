package PracticeQuestions.MusicApp.managers;

import PracticeQuestions.MusicApp.models.Playlist;
import PracticeQuestions.MusicApp.models.Song;

import java.util.HashMap;
import java.util.Map;

public class PlaylistManager {
    private final Map<String , Playlist> playlists = new HashMap<>();

    private PlaylistManager(){};

    private static class Holder{
        private final static PlaylistManager instance = new PlaylistManager();
    }

    public PlaylistManager getPlaylistManager(){
        return Holder.instance;
    }

    public void createPlaylist(String name){
        if(playlists.containsKey(name)){
            throw new RuntimeException("Playlist name " + name + "already exists");
        }

        playlists.put(name, new Playlist(name));
        System.out.println("Created Playlist " + name);
    }

    public void deletePlaylist(String name){
        if(!playlists.containsKey(name)){
            throw new RuntimeException("No Playlist with name " + name + " exists");
        }
        playlists.remove(name);
        System.out.println("Deleted playlist " + name);
    }

    public Playlist getPlaylist(String name){
        if(!playlists.containsKey(name)){
            throw new RuntimeException("Playlist " + name + " not found");
        }
        return playlists.get(name);
    }

    public void addSong(Song song , String playlistName){
        if(!playlists.containsKey(playlistName)){
            throw new RuntimeException("No plalist with name " + playlistName);
        }
        Playlist playlist = playlists.get(playlistName);
        playlist.addSong(song);
    }

    public void removeSong(Song song , String playlistName){
        if(!playlists.containsKey(playlistName)){
            throw new RuntimeException("No plalist with name " + playlistName);
        }
        Playlist playlist = playlists.get(playlistName);
        playlist.removeSong(song);
    }

}
