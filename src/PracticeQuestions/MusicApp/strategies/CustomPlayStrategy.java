package PracticeQuestions.MusicApp.strategies;

import PracticeQuestions.MusicApp.ENUM.PlayStrategyType;
import PracticeQuestions.MusicApp.models.Playlist;
import PracticeQuestions.MusicApp.models.Song;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CustomPlayStrategy implements IPlayStrategy {

    private Playlist currentPlaylist;

    // Position in the original playlist
    private int currentIndex;

    // Currently playing song
    private Song currentSong;

    // Songs manually added to play next
    private final Queue<Song> nextQueue;

    // Playback history
    private final Stack<Song> history;

    public CustomPlayStrategy() {
        currentPlaylist = null;
        currentIndex = -1;
        currentSong = null;
        nextQueue = new LinkedList<>();
        history = new Stack<>();
    }

    @Override
    public void setPlaylist(Playlist playlist) {
        currentPlaylist = playlist;
        currentIndex = -1;
        currentSong = null;
        nextQueue.clear();
        history.clear();
    }

    @Override
    public Song next() {

        if (currentPlaylist == null) {
            throw new RuntimeException("No playlist selected.");
        }

        if (currentSong != null) {
            history.push(currentSong);
        }

        // Play queued songs first
        if (!nextQueue.isEmpty()) {
            currentSong = nextQueue.poll();
            return currentSong;
        }

        // Continue sequential playback
        if (currentIndex + 1 >= currentPlaylist.getSize()) {
            throw new RuntimeException("No next song available.");
        }

        currentIndex++;
        currentSong = currentPlaylist.getSongs().get(currentIndex);

        return currentSong;
    }

    @Override
    public Song previous() {

        if (history.isEmpty()) {
            throw new RuntimeException("No previous song available.");
        }

        currentSong = history.pop();

        // Update playlist index only if this song belongs to playlist
        for (int i = 0; i < currentPlaylist.getSongs().size(); i++) {
            if (currentPlaylist.getSongs().get(i).equals(currentSong)) {
                currentIndex = i;
                break;
            }
        }

        return currentSong;
    }

    @Override
    public boolean hasNext() {

        if (currentPlaylist == null) {
            return false;
        }

        return !nextQueue.isEmpty()
                || currentIndex + 1 < currentPlaylist.getSize();
    }

    @Override
    public boolean hasPrevious() {
        return !history.isEmpty();
    }

    @Override
    public void addToNext(Song song) {

        if (song == null) {
            throw new RuntimeException("Cannot enqueue null song.");
        }

        nextQueue.offer(song);
    }

    @Override
    public PlayStrategyType getPlayStrategyType() {
        return PlayStrategyType.CUSTOM;
    }
}