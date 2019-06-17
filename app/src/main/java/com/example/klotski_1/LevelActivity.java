package com.example.klotski_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LevelActivity extends AppCompatActivity {
    private MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        Intent intent = new Intent(LevelActivity.this, MusicService.class);
        startService(intent);
        myApplication = (MyApplication) getApplication();

    }

    public void startLevel(View view) {
        Intent intent = new Intent(LevelActivity.this, GameActivity.class);
        startActivity(intent);
    }
}
