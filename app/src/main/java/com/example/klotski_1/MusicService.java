package com.example.klotski_1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;
    public static boolean isRunning;

    @Override
    public void onCreate() {
        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.backgroundmusic);
//            mediaPlayer.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        if (mediaPlayer != null) {
            mediaPlayer.start();
            isRunning = true;
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
