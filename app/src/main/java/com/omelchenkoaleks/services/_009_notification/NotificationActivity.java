package com.omelchenkoaleks.services._009_notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.omelchenkoaleks.services.R;

public class NotificationActivity extends AppCompatActivity {
    private static final String TAG = "Notification";
    public static final String FILE_NAME = "file name";

    private TextView mSomeValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_009_notification);

        mSomeValueTextView = findViewById(R.id.notification_text_view);

        Intent intent = getIntent();

        String fileName = intent.getStringExtra(FILE_NAME);
        if (!TextUtils.isEmpty(fileName)) {
            mSomeValueTextView.setText(fileName);
        }
    }

    public void onClickStartNotification(View view) {
        startService(new Intent(this, NotificationService.class));
    }

    public void onClickStopNotification(View view) {
        stopService(new Intent(this, NotificationService.class));
    }
}
