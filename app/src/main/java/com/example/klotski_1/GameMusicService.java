package com.example.klotski_1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


public class GameMusicService extends Service {
    private MediaPlayer mediaPlayer;
    public static boolean isRunning;

    @Override
    public void onCreate() {
        // 初始化音乐资源
        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.gamemusic);
            mediaPlayer.setVolume(0.4f, 0.4f);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        //开始播放音乐
        if (mediaPlayer != null) {
            mediaPlayer.start();
            isRunning = true;
            //注册回调函数，音乐播放完毕之后，音乐播放完毕的事件处理
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    try {
                        mp.start();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }

                }
            });

            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    try {
                        mp.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            });
        }
        super.onStart(intent, startId);
    }


    @Override
    public void onDestroy() {
        // 服务停止时停止播放器并且释放资源
        if (mediaPlayer != null) {
            isRunning = false;
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
