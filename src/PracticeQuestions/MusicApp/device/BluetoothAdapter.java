package PracticeQuestions.MusicApp.device;

import PracticeQuestions.MusicApp.externalApis.BuletoothApi;
import PracticeQuestions.MusicApp.models.Song;

public class BluetoothAdapter implements IAudioOutputDevice{

    private final BuletoothApi bluetoothApi;

    public BluetoothAdapter(BuletoothApi api){
        this.bluetoothApi = api;
    }

    @Override
    public void playSong(Song song) {
        String data = song.getName() + " by " + song.getArtist();
        bluetoothApi.playSong(data);
    }

}
