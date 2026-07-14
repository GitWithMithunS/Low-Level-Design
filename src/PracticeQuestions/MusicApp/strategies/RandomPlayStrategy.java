package PracticeQuestions.MusicApp.strategies;

import PracticeQuestions.MusicApp.ENUM.PlayStrategyType;
import PracticeQuestions.MusicApp.models.Playlist;
import PracticeQuestions.MusicApp.models.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class RandomPlayStrategy implements IPlayStrategy{
    private Playlist currPlaylist;
    private final Random random;
    private Stack<Song> history;
    private List<Song> remainingSongs;

    public RandomPlayStrategy(){
        currPlaylist = null;
        random = new Random();
        remainingSongs = new ArrayList<>();
        history = new Stack<>();
    }

    @Override
    public void setPlaylist(Playlist playlist) {
        history.clear();
        currPlaylist = playlist;
        if (currPlaylist == null || currPlaylist.getSize() == 0) return;
        remainingSongs = new ArrayList<>(playlist.getSongs());
    }

    @Override
    public Song next() {
        if(currPlaylist == null || currPlaylist.getSize() == 0 ){
            throw new RuntimeException("Playlist not selected or is empty");
        }
        if(remainingSongs.isEmpty()){
            throw new RuntimeException("No Songs left to play");
        }

        int idx = random.nextInt(remainingSongs.size());
        Song song = remainingSongs.get(idx);
        remainingSongs.remove(idx);
        history.add(song);
        return song;

    }

    @Override
    public Song previous() {
        if(history.isEmpty()){
            throw new RuntimeException("No Previous song to play");
        }
        Song song = history.pop();
        remainingSongs.add(song);
        return song;
    }

    @Override
    public boolean hasNext() {
        return (currPlaylist != null && !remainingSongs.isEmpty());
    }

    @Override
    public boolean hasPrevious() {
        return !history.empty();
    }

    @Override
    public PlayStrategyType getPlayStrategyType() {
        return PlayStrategyType.RANDOM;
    }
}
