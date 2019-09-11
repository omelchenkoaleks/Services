package com.omelchenkoaleks.services._004_service_data;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.omelchenkoaleks.services.R;

public class ServiceDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_004_service_data);
    }

    public void startServiceDataOnClick(View view) {
        startService(new Intent(this, ServiceData.class).putExtra("time", 6));
        startService(new Intent(this, ServiceData.class).putExtra("time", 2));
        startService(new Intent(this, ServiceData.class).putExtra("time", 1));
    }
}
