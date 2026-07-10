package PracticeQuestions.MusicApp.device;

import PracticeQuestions.MusicApp.externalApis.SpeakerApi;
import PracticeQuestions.MusicApp.models.Song;

public class SpeakerAdapter implements IAudioOutputDevice{

    private final SpeakerApi speakerApi;

    public SpeakerAdapter(SpeakerApi api){
        this.speakerApi = api;
    }

    @Override
    public void playSong(Song song) {
        String data = song.getName() + " by " + song.getArtist();
        speakerApi.playAudio(data);
    }

}
