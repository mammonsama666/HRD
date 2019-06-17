package com.example.klotski_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static com.example.klotski_1.R.layout.activity_main;


public class MainActivity extends AppCompatActivity {
    private MyApplication myApplication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        myApplication = (MyApplication) getApplication();
        Intent intent2 = new Intent(MainActivity.this, GameMusicService.class);
        stopService(intent2);
        Intent intent = new Intent(MainActivity.this, MusicService.class);
        startService(intent);
    }


    public void startGame(View view) {
        Intent intent = new Intent(MainActivity.this, LevelActivity.class);
        startActivity(intent);
    }

    public void jumpToAbout(View view) {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    public void exitGame(View view) {
        finish();
    }
}
