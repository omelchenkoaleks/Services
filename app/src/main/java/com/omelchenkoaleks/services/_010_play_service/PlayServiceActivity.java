package com.omelchenkoaleks.services._010_play_service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.omelchenkoaleks.services.R;

public class PlayServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_010_play_service);
    }

    public void onClickStartPlay(View view) {
        startService(new Intent(this, PlayService.class));
    }

    public void onClickStopPlay(View view) {
        stopService(new Intent(this, PlayService.class));
    }
}
