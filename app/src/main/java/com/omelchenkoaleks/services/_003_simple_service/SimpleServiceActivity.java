package com.omelchenkoaleks.services._003_simple_service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.omelchenkoaleks.services.R;

public class SimpleServiceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_002_service_lifecycle);
    }

    public void serviceLifecycleOnClick(View view) {

        switch (view.getId()) {

            case R.id.start_service_lifecycle_button:
                startService(new Intent(this, MyService.class));
                break;

            case R.id.stop_service_lifecycle_button:
                stopService(new Intent(this, MyService.class));
                break;

            default:
                break;
        }
    }
}
