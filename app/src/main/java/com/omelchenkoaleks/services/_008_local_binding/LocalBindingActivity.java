package com.omelchenkoaleks.services._008_local_binding;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.omelchenkoaleks.services.R;

public class LocalBindingActivity extends AppCompatActivity {
    private static final String TAG = "Binding";

    private boolean mBound;
    private ServiceConnection mServiceConnection;
    private Intent mIntent;
    private LocalBindingService mLocalBindingService;
    private TextView mIntervalTextView;
    private long mInterval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_008_local_binding);

        mIntervalTextView = findViewById(R.id.interval_text_view);

        mIntent = new Intent(this, LocalBindingService.class);

        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "onServiceConnected");
                mLocalBindingService = ((LocalBindingService.MyBinder) service).getService();
                mBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected");
                mBound = false;
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        // соединяем при запуске нашей Активности
        bindService(mIntent, mServiceConnection, 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
    }

    public void startServiceLocalBindingOnClick(View view) {
        startService(mIntent);
    }

    public void upLocalBindingOnClick(View view) {
        if (mBound) {
            mInterval = mLocalBindingService.upInterval(500);
            mIntervalTextView.setText("interval = " + mInterval);
        }
    }

    public void downLocalBindingOnClick(View view) {
        if (mBound) {
            mInterval = mLocalBindingService.downInterval(500);
            mIntervalTextView.setText("interval = " + mInterval);
        }
    }

    public void stopServiceLocalBindingOnClick(View view) {
        stopService(mIntent);
    }
}
