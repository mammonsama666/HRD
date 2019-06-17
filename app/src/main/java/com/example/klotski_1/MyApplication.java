package com.example.klotski_1;

import android.app.Application;
import android.content.SharedPreferences;

public class MyApplication extends Application {
    // 判断是否返回了桌面
    private boolean isOtherActivitiesBehind = false;
    private boolean isGameActivityBehind = false;
    // 判断游戏设置中的音乐是否开启
    private boolean globalMusicPlaying;
    // 读取sharedPreference中播放音乐的状态
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
