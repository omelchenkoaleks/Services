package com.omelchenkoaleks.services._006_send_data_broadcast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SendDataBroadcastService extends Service {
    private static final String TAG = "Broadcast";
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

        int time = intent.getIntExtra(SendDataBroadcastActivity.PARAM_TIME, 0);
        int task = intent.getIntExtra(SendDataBroadcastActivity.PARAM_TASK, 0);

        MyRun myRun = new MyRun(time, startId, task);
        mExecutorService.execute(myRun);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    private class MyRun implements Runnable {
        private int time;
        private int startId;
        private int task;

        public MyRun(int time, int startId, int task) {
            this.time = time;
            this.startId = startId;
            this.task = task;

            Log.d(TAG, "MyRun: " + startId + " create");
        }

        @Override
        public void run() {
            Intent intent = new Intent(SendDataBroadcastActivity.BROADCAST_ACTION);
            Log.d(TAG, "run: " + startId + " start, time = " + time);

            try {
                // сообщаем о старте задачи
                intent.putExtra(SendDataBroadcastActivity.PARAM_TASK, task);
                intent.putExtra(SendDataBroadcastActivity.PARAM_STATUS, SendDataBroadcastActivity.STATUS_START);
                sendBroadcast(intent);

                // начинаем выполнение задачи
                TimeUnit.SECONDS.sleep(time);

                // сообщаем об окончании задачи
                intent.putExtra(SendDataBroadcastActivity.PARAM_STATUS, SendDataBroadcastActivity.STATUS_FINISH);
                intent.putExtra(SendDataBroadcastActivity.PARAM_RESULT, time * 100);
                sendBroadcast(intent);

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
