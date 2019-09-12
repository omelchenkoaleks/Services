package com.omelchenkoaleks.services._007_binding;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.omelchenkoaleks.services.R;

public class BindingActivity extends AppCompatActivity {
    private static final String TAG = "Binding";

    private Intent mIntent;
    private boolean mBound;
    private ServiceConnection mServiceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_007_binding);

        mIntent = new Intent(this, BindingService.class);

        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "onServiceConnected");

                mBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected");

                mBound = false;
            }
        };
    }

    public void startServiceBindingOnClick(View view) {
        startService(mIntent);
    }

    public void stopServiceBindingOnClick(View view) {
        stopService(mIntent);
    }

    public void bindBindingOnClick(View view) {
        /*
            если вместо BIND_AUTO_CREATE указать 0, то присоединение будет происходить
            через подавания заявки, но можно будет отсоединиться а сервер будет
            продолжать работать.
            При этом, если указать true в методе onUnbind можно будет присоединяться
            несколько раз и отключаться, иначе только один.
         */
        bindService(mIntent, mServiceConnection, BIND_AUTO_CREATE);
    }

    public void unbindBindingOnClick(View view) {
        if (mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindBindingOnClick(null);
    }
}
