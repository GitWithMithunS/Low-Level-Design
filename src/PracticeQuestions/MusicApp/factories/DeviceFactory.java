package PracticeQuestions.MusicApp.factories;

import PracticeQuestions.MusicApp.ENUM.DeviceType;
import PracticeQuestions.MusicApp.device.BluetoothAdapter;
import PracticeQuestions.MusicApp.device.HeadphoneAdapter;
import PracticeQuestions.MusicApp.device.IAudioOutputDevice;
import PracticeQuestions.MusicApp.device.SpeakerAdapter;
import PracticeQuestions.MusicApp.externalApis.BuletoothApi;
import PracticeQuestions.MusicApp.externalApis.HeadPhoneApi;
import PracticeQuestions.MusicApp.externalApis.SpeakerApi;

public class DeviceFactory {

    public static IAudioOutputDevice createDevice(DeviceType deviceType){
        switch(deviceType){
            case BLUETOOTH -> {
                return new BluetoothAdapter(new BuletoothApi());
            }
            case HEADPHONES -> {
                return new HeadphoneAdapter(new HeadPhoneApi());
            }
            case SPEAKER ->  {
                return new SpeakerAdapter(new SpeakerApi());
            }
            default -> {
                throw new IllegalArgumentException("Invalid device type: " + deviceType);
            }
        }
    }

}
