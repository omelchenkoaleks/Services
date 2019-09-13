package com.omelchenkoaleks.services._010_play_service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import com.omelchenkoaleks.services.R;

public class PlayService extends Service {
    private MediaPlayer mMediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service created", Toast.LENGTH_SHORT).show();

        mMediaPlayer = MediaPlayer.create(this, R.raw.play);
        mMediaPlayer.setLooping(false);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service start", Toast.LENGTH_SHORT).show();

        mMediaPlayer.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service destroy", Toast.LENGTH_SHORT).show();

        mMediaPlayer.stop();
    }
}
