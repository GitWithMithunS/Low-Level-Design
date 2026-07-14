package PracticeQuestions.MusicApp.strategies;

import PracticeQuestions.MusicApp.ENUM.PlayStrategyType;
import PracticeQuestions.MusicApp.models.Playlist;
import PracticeQuestions.MusicApp.models.Song;

public class SequentialPlayStrategy implements IPlayStrategy{
    private int currIndex ;
    private Playlist currPlaylist;

    public SequentialPlayStrategy(){
        currPlaylist = null;
        currIndex = -1;
    }

    @Override
    public void setPlaylist(Playlist playlist) {
        currIndex = -1;
        currPlaylist = playlist;
    }

    @Override
    public Song next() {
        if(!hasNext()){
            System.out.println("no next hereeeeeeee");

        }
        if(currPlaylist == null || currPlaylist.getSize() == 0 ){
            throw new RuntimeException("Playlist not selected or is empty");
        }
        currIndex = currIndex + 1;
        return currPlaylist.getSongs().get(currIndex);
    }

    @Override
    public Song previous() {
        if(currPlaylist == null || currPlaylist.getSize() == 0 ){
            throw new RuntimeException("Playlist not selected or is empty");
        }
        currIndex = currIndex - 1;
        return currPlaylist.getSongs().get(currIndex);
    }

    @Override
    public boolean hasNext() {
        return currPlaylist.getSize() > currIndex+1;
    }

    @Override
    public boolean hasPrevious() {
        return currIndex > 0;
    }

    @Override
    public PlayStrategyType getPlayStrategyType() {
        return PlayStrategyType.SEQUENTIAL;
    }
}
