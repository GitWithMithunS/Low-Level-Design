MusicApp/
├── Main.java
├── MusicApplication.java
├── MusicPlayerFacade.java
│
├── ENUM/
│   ├── DeviceType.java
│   └── PlayStrategyType.java
│
├── models/
│   ├── Song.java
│   └── Playlist.java
│
├── device/
│   ├── IAudioOutputDevice.java
│   └── BluetoothAdapter.java
│   └── HeadphoneAdapter.java
│   └── SpeakerAdapter.java
│
├── externalApis/
│   ├── BluetoothApi.java
│   └── HeadphoneApi.java
│   └── SpeakerApi.java
│
├── engine/
│   └── AudioEngine.java
│
├── strategies/
│   ├── IPlayStrategy.java
│   ├── SequentialPlayStrategy.java
│   ├── RandomPlayStrategy.java
│   └── CustomPlayStrategy.java
│
├── managers/
│   ├── DeviceManager.java
│   ├── PlaylistManager.java
│   └── PlayStrategyManager.java
│
└── factories/
    ├── DeviceFactory.java
    └── StrategyFactory.java


