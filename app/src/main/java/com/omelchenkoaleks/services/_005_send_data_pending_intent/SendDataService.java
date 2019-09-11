package com.omelchenkoaleks.services._005_send_data_pending_intent;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SendDataService extends Service {
    private static final String TAG = "SendData";
    private ExecutorService mExecutorService;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        mExecutorService = Executors.newFixedThreadPool(2);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        int time = intent.getIntExtra(SendDataPendingIntentActivity.PARAM_TIME, 1);
        PendingIntent pendingIntent = intent.getParcelableExtra(SendDataPendingIntentActivity.PARAM_PENDING_INTENT);

        MyRun myRun = new MyRun(time, startId, pendingIntent);
        mExecutorService.execute(myRun);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    private class MyRun implements Runnable {
        private int time;
        private int startId;
        PendingIntent pendingIntent;

        public MyRun(int time, int startId, PendingIntent pendingIntent) {
            this.time = time;
            this.startId = startId;
            this.pendingIntent = pendingIntent;
            Log.d(TAG, "MyRun: " + startId + " create");
        }

        @Override
        public void run() {
            Log.d(TAG, "run: " + startId + " start, time = " + time);

            try {
                // сообщаем об старте задачи
                pendingIntent.send(SendDataPendingIntentActivity.STATUS_START);

                // начинаем выполнение задачи
                TimeUnit.SECONDS.sleep(time);

                // сообщаем об окончании задачи
                Intent intent = new Intent().putExtra(SendDataPendingIntentActivity.PARAM_RESULT, time * 100);
                pendingIntent.send(SendDataService.this, SendDataPendingIntentActivity.STATUS_FINISH, intent);

            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            stop();
        }

        private void stop() {
            Log.d(TAG, "stop: "
                    + startId + " end, stopSelfResult("
                    + startId + ") - " + stopSelfResult(startId) );
        }
    }
}
