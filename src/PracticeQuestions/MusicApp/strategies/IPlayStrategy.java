package PracticeQuestions.MusicApp.strategies;

import PracticeQuestions.MusicApp.models.Playlist;
import PracticeQuestions.MusicApp.models.Song;

public interface IPlayStrategy {
    void setPlaylist(Playlist playlist);
    Song next();
    Song previous();
    boolean hasNext();
    boolean hasPrevious();
    default void addToNext(Song song){};
}
