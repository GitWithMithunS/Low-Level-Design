package PracticeQuestions.MusicApp;

import PracticeQuestions.MusicApp.ENUM.DeviceType;
import PracticeQuestions.MusicApp.ENUM.PlayStrategyType;

public class Main {

    public static void main(String args[]){
         try {
            MusicApplication application = MusicApplication.getInstance();

            // Create Playlist - Travel Beats
            application.createPlaylist("Travel Beats");
            application.addSongToPlaylist("Travel Beats", "The Nights");
            application.addSongToPlaylist("Travel Beats", "Senorita");
            application.addSongToPlaylist("Travel Beats", "Naatu Naatu");
            application.addSongToPlaylist("Travel Beats", "Thunder");


            // Create Playlist - Evening Chill
            application.createPlaylist("Evening Chill");
            application.addSongToPlaylist("Evening Chill", "Pasoori");
            application.addSongToPlaylist("Evening Chill", "Kaise Hua");
            application.addSongToPlaylist("Evening Chill", "Malang");
            application.addSongToPlaylist("Evening Chill", "Levitating");

            // Connect device
            application.connectToAudioOutputDevice(DeviceType.BLUETOOTH);

            //Play/pause a single song
            application.playSingleSong("Pasoori");
            application.pauseCurrentSong("Pasoori");
            application.playSingleSong("Pasoori");  // resume

            //playing a plylist
            System.out.println("\n-- Sequential Playback --\n");
            application.selectPlayStrategy(PlayStrategyType.SEQUENTIAL);
            application.loadPlaylist("Travel Beats");
            application.playAll();

            System.out.println("\n-- Random Playback --\n");
            application.selectPlayStrategy(PlayStrategyType.RANDOM);
            application.loadPlaylist("Travel Beats");
            application.playAll();

            System.out.println("\n-- Custom Queue Playback --\n");
            application.selectPlayStrategy(PlayStrategyType.CUSTOM);
            application.loadPlaylist("Travel Beats");
            application.addSongToNext("Thunder");
            application.addSongToNext("Senorita");
            application.playAll();

            System.out.println("\n-- Play Previous in Sequential --\n");
            application.selectPlayStrategy(PlayStrategyType.SEQUENTIAL);
            application.loadPlaylist("Evening Chill");
            application.playAll();

            application.playNxt();

            application.playPrev();

         } catch (Exception error) {
            System.err.println("Error: " + error.getMessage());
        }
    }
}
