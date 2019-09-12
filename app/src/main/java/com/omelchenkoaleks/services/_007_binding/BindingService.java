package com.omelchenkoaleks.services._007_binding;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BindingService extends Service {
    private static final String TAG = "Binding";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return new Binder();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
