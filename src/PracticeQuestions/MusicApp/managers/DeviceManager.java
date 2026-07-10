package PracticeQuestions.MusicApp.managers;

import PracticeQuestions.MusicApp.ENUM.DeviceType;
import PracticeQuestions.MusicApp.device.IAudioOutputDevice;
import PracticeQuestions.MusicApp.factories.DeviceFactory;

//Singleton class - manager
public class DeviceManager {
    private IAudioOutputDevice audioOutputDevice;

    private DeviceManager(){};

    private static class Holder{
        private static final DeviceManager instance = new DeviceManager();
    }

    public static DeviceManager getDeviceManger(){
        return Holder.instance;
    }

    public void connectDevice(DeviceType deviceType) {
        this.audioOutputDevice = DeviceFactory.createDevice(deviceType);
        System.out.println( "Connected to " + deviceType);
    }

    public IAudioOutputDevice getAudioOutputDevice(){
        if(audioOutputDevice == null){
            throw new RuntimeException("No output device is Connected");
        }
        return audioOutputDevice;
    }

    public boolean isConnected(){
        return audioOutputDevice != null;
    }

}
