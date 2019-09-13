package com.omelchenkoaleks.services._008_local_binding;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class LocalBindingService extends Service {
    private static final String TAG = "Binding";

    private MyBinder mBinder = new MyBinder();

    private Timer mTimer;
    private TimerTask mTimerTask;
    private long mInterval = 1000;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        mTimer = new Timer();
        schedule();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return mBinder;
    }

    private void schedule() {
        if (mTimerTask != null)
            mTimerTask.cancel();

        if (mInterval > 0) {
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    Log.d(TAG, "run");
                }
            };
            mTimer.schedule(mTimerTask, 1000, mInterval);
        }
    }

    public long upInterval(long gap) {
        mInterval = mInterval + gap;
        schedule();
        return mInterval;
    }

    public long downInterval(long gap) {
        mInterval = mInterval - gap;
        if (mInterval < 0) {
            mInterval = 0;
        }
        schedule();
        return mInterval;
    }

    public class MyBinder extends Binder {
        public LocalBindingService getService() {
            return LocalBindingService.this;
        }
    }
}
