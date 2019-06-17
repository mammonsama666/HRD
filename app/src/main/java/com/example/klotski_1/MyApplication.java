package com.example.klotski_1;

import android.app.Application;
import android.content.SharedPreferences;

public class MyApplication extends Application {
    private boolean isOtherActivitiesBehind = false;
    private boolean isGameActivityBehind = false;
    private boolean globalMusicPlaying;
    private SharedPreferences sharedPreferences;
    private String sharedPrefFile = "com.example.android.musicSharedPreference";

    @Override
    public void onCreate() {
        sharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        globalMusicPlaying = sharedPreferences.getBoolean("MUSIC", true);
        super.onCreate();
    }

    public boolean getIsOtherActivitiesBehind() {
        return this.isOtherActivitiesBehind;
    }

    public void setIsOtherActivitiesBehind(boolean isBehind) {
        this.isOtherActivitiesBehind = isBehind;
    }

    public boolean getIsGameActivityBehind() {
        return this.isGameActivityBehind;
    }

    public void setIsGameActivityBehind(boolean isBehind) {
        this.isGameActivityBehind = isBehind;
    }

    public boolean getGlobalMusicStatus() {
        return this.globalMusicPlaying;
    }

    public void setGlobalMusicStatus(boolean globalMusicPlaying) {
        this.globalMusicPlaying = globalMusicPlaying;
    }
}
