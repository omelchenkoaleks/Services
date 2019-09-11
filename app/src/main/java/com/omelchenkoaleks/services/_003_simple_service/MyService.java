package com.omelchenkoaleks.services._003_simple_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    private static final String TAG = "MyService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        task();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    private void task() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    Log.d(TAG, "task: i = " + i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // с помощью этого метода сервис сам останавливается
                stopSelf();
            }
        }).start(); // поток нужно запустить


        /*
            ПО ПОВОДУ ЭТОГО КОДА: получается, что сервис будет выполняться в этом
            случае в главном потоке, а значит блокируются все дейстия пользователя пока
            не будет выполнена задача -  КОНЕЧНО, ЭТО НЕ ПРАВИЛЬНО!!!

            ЭТО ВСЕ нужно выполнять в другом потоке - пример выше ...
        */

        // с паузой в одну секунду вываодит числа от 1 до 5
//        for (int i = 1; i <= 5; i++) {
//            Log.d(TAG, "task: i = " + i);
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        // с помощью этого метода сервис сам останавливается
//        stopSelf();
    }
}
