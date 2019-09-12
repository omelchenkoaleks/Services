package com.omelchenkoaleks.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.omelchenkoaleks.services._001_start_service.StartServiceActivity;
import com.omelchenkoaleks.services._002_service_lifecycle.ServiceLifecycleActivity;
import com.omelchenkoaleks.services._003_simple_service.SimpleServiceActivity;
import com.omelchenkoaleks.services._004_service_data.ServiceDataActivity;
import com.omelchenkoaleks.services._005_send_data_pending_intent.SendDataPendingIntentActivity;
import com.omelchenkoaleks.services._006_send_data_broadcast.SendDataBroadcastActivity;
import com.omelchenkoaleks.services._007_binding.BindingActivity;

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

            case R.id.simple_service_button:
                Intent simpleServiceIntent = new Intent(this, SimpleServiceActivity.class);
                startActivity(simpleServiceIntent);
                break;

            case R.id.service_data_button:
                Intent serviceDataIntent = new Intent(this, ServiceDataActivity.class);
                startActivity(serviceDataIntent);
                break;

            case R.id.send_data_pending_intent_button:
                Intent sendDataPendingIntent = new Intent(this, SendDataPendingIntentActivity.class);
                startActivity(sendDataPendingIntent);
                break;

            case R.id.send_data_broadcast_button:
                Intent sendDataBroadcastIntent = new Intent(this, SendDataBroadcastActivity.class);
                startActivity(sendDataBroadcastIntent);
                break;

            case R.id.binding_button:
                Intent bindingIntent = new Intent(this, BindingActivity.class);
                startActivity(bindingIntent);
                break;
        }
    }
}
