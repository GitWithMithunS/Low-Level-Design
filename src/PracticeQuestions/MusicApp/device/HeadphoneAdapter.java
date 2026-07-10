package PracticeQuestions.MusicApp.device;

import PracticeQuestions.MusicApp.externalApis.HeadPhoneApi;
import PracticeQuestions.MusicApp.models.Song;

public class HeadphoneAdapter implements IAudioOutputDevice{
    private final HeadPhoneApi headPhoneApi;

    public HeadphoneAdapter(HeadPhoneApi api){
        this.headPhoneApi = api;
    }

    @Override
    public void playSong(Song song) {
        String data = song.getName() + " by " + song.getArtist();
        headPhoneApi.playAudio(data);
    }


}
