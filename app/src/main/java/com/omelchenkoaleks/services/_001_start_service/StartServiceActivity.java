package com.omelchenkoaleks.services._001_start_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.omelchenkoaleks.services.R;

public class StartServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_001_start_service);
    }

    public void startServiceOnClick(View view) {
        // запускаем просто методом из Context-a
        startService(new Intent(this, MyService.class));
    }
}
