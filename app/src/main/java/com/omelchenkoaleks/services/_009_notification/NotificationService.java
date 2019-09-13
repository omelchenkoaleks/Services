package com.omelchenkoaleks.services._009_notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;

import com.omelchenkoaleks.services.R;

import java.util.concurrent.TimeUnit;

public class NotificationService extends Service {
    private static final String TAG = "Notification";

    private NotificationManager mNotificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sendNotification();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void sendNotification() {
        Notification.Builder builder = new Notification.Builder(this);

        Intent intent = new Intent(this, NotificationActivity.class);
        intent.putExtra(NotificationActivity.FILE_NAME, "Some Value");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("Title")
                .setContentText("Text")
                .setTicker("New notification");

        Notification notification = builder.build();


        // flag - чтобы уведомление пропало после нажатия
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        // отправляем
        mNotificationManager.notify(1, notification);
    }
}
