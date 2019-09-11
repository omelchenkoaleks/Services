package com.omelchenkoaleks.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.omelchenkoaleks.services._001_start_service.StartServiceActivity;
import com.omelchenkoaleks.services._002_service_lifecycle.ServiceLifecycleActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.start_service_button:
                Intent startServiceIntent = new Intent(this, StartServiceActivity.class);
                startActivity(startServiceIntent);
                break;

            case R.id.service_lifecycle_button:
                Intent serviceLifecycleIntent = new Intent(this, ServiceLifecycleActivity.class);
                startActivity(serviceLifecycleIntent);
                break;
        }
    }
}
