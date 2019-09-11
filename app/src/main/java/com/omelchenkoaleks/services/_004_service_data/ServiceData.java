package com.omelchenkoaleks.services._004_service_data;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ServiceData extends Service {
    private static final String TAG = "ServiceDataActivity";
    private ExecutorService mExecutorService;
    private Object mSomeObject;


    public ServiceData() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

        /*
            Когда к нашему сервису подключается несколько процесов одновременно,
            наш сервис завершит свою работу тогда, когда законыит свою работу последний из
            запущенных процессов _ НО ЭТОТ ПРОЦЕСС ПОСЛЕДНИЙ ПО startId (т.е. полсдений по списку
            запущенных), но у него может быть время работы меньше чем у других ранее
                                 (получивших меньшее startId) и поэтому,
            другие могут не успеть доделать свою работу (сервис уже будет остановлен)
         */

        // указываем число потоков и последовательность для запуска
        mExecutorService = Executors.newFixedThreadPool(1);
        
        mSomeObject = new Object();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        
        int time = intent.getIntExtra("time", 1);
        MyRun myRun = new MyRun(time, startId);
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
        mSomeObject = null;
    }
    
    private class MyRun implements Runnable {
        private int time;
        private int startId;

        public MyRun(int time, int startId) {
            this.time = time;
            this.startId = startId;
            Log.d(TAG, "MyRun: " + startId + " create");
        }

        @Override
        public void run() {
            Log.d(TAG, "run: " + startId + " start, time = " + time);

            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Log.d(TAG, "run: " + startId + " mSomeObject = " + mSomeObject.getClass());
            } catch (NullPointerException e) {
                Log.d(TAG, "run: " + startId + " error, null pointer");
            }

            stop();
        }

        private void stop() {
            Log.d(TAG, "stop: " + startId + " end, stopSelf(" + startId + ")");
            stopSelf(startId);
        }
    }
}
