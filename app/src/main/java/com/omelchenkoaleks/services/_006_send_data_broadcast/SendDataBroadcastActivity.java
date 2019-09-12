package com.omelchenkoaleks.services._006_send_data_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.omelchenkoaleks.services.R;

public class SendDataBroadcastActivity extends AppCompatActivity {
    private static final String TAG = "Broadcast";

    private final int TASK_1_CODE = 1;
    private final int TASK_2_CODE = 2;
    private final int TASK_3_CODE = 3;

    public static final int STATUS_START = 100;
    public static final int STATUS_FINISH = 200;

    public static final String PARAM_TIME = "time";
    public static final String PARAM_TASK = "task";
    public static final String PARAM_RESULT = "result";
    public static final String PARAM_STATUS = "status";

    public static final String BROADCAST_ACTION = "com.omelchenkoaleks.broadcast";

    private TextView mTask_1_TextView;
    private TextView mTask_2_TextView;
    private TextView mTask_3_TextView;

    private Button mOnClickStartButton;

    private BroadcastReceiver mBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_005_send_data_pending_intent);

        mTask_1_TextView = findViewById(R.id.text_view_1);
        mTask_1_TextView.setText(getString(R.string.task_1));
        mTask_2_TextView = findViewById(R.id.text_view_2);
        mTask_2_TextView.setText(getString(R.string.task_2));
        mTask_3_TextView = findViewById(R.id.text_view_3);
        mTask_3_TextView.setText(getString(R.string.task_3));

        mOnClickStartButton = findViewById(R.id.start_send_data_intent_button);


        // создаем BroadcastReceiver
        mBroadcastReceiver = new BroadcastReceiver() {

            // действия при получении сообщений
            @Override
            public void onReceive(Context context, Intent intent) {
                int task = intent.getIntExtra(PARAM_TASK, 0);
                int status = intent.getIntExtra(PARAM_STATUS, 0);
                Log.d(TAG, "onReceive: task = " + task + ", status = " + status);

                // ловим сообщения о старте задач
                if (status == STATUS_START) {
                    switch (task) {
                        case TASK_1_CODE:
                            mTask_1_TextView.setText(getString(R.string.task_start_result));
                            break;
                        case TASK_2_CODE:
                            mTask_2_TextView.setText(getString(R.string.task_start_2_result));
                            break;
                        case TASK_3_CODE:
                            mTask_3_TextView.setText(getString(R.string.start_task_3_result));
                            break;
                        default:
                            break;
                    }
                }

                // ловим сообщение о завершении задачи
                if (status == STATUS_FINISH) {
                    int result = intent.getIntExtra(PARAM_RESULT, 0);
                    switch (task) {
                        case TASK_1_CODE:
                            mTask_1_TextView.setText(getString(R.string.finish_1) + result);
                            break;
                        case TASK_2_CODE:
                            mTask_2_TextView.setText(getString(R.string.finish_2) + result);
                            break;
                        case TASK_3_CODE:
                            mTask_3_TextView.setText(getString(R.string.finish_3) + result);
                            break;
                        default:
                            break;
                    }
                }
            }
        };

        // создаем фильтр для BroadcastReceiver
        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        // регистрируем (включаем) BroadcastReceiver
        registerReceiver(mBroadcastReceiver, intentFilter);

        mOnClickStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;

                // создаем интент для вызова сервиса и кладем в него параметр времени и код задачи
                intent = new Intent(SendDataBroadcastActivity.this, SendDataBroadcastService.class)
                        .putExtra(PARAM_TIME, 7).putExtra(PARAM_TASK, TASK_1_CODE);
                // стартуем сервис
                startService(intent);

                intent = new Intent(SendDataBroadcastActivity.this, SendDataBroadcastService.class)
                        .putExtra(PARAM_TIME, 4).putExtra(PARAM_TASK, TASK_2_CODE);
                startService(intent);

                intent = new Intent(SendDataBroadcastActivity.this, SendDataBroadcastService.class)
                        .putExtra(PARAM_TIME, 6).putExtra(PARAM_TASK, TASK_3_CODE);
                startService(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // выключаем BroadcastReceiver
        unregisterReceiver(mBroadcastReceiver);
    }
}
