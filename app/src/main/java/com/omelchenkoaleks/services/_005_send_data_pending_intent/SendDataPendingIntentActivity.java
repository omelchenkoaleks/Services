package com.omelchenkoaleks.services._005_send_data_pending_intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.omelchenkoaleks.services.R;

public class SendDataPendingIntentActivity extends AppCompatActivity {
    private static final String TAG = "SendData";

    private final int TASK_1_CODE = 1;
    private final int TASK_2_CODE = 2;
    private final int TASK_3_CODE = 3;

    public static final int STATUS_START = 100;
    public static final int STATUS_FINISH = 200;

    public static final String PARAM_TIME = "time";
    public static final String PARAM_PENDING_INTENT = "pendingIntent";
    public static final String PARAM_RESULT = "result";

    private TextView mTask_1_TextView;
    private TextView mTask_2_TextView;
    private TextView mTask_3_TextView;

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
    }

    public void sendDataPendingIntentOnClick(View view) {
        PendingIntent pendingIntent;
        Intent intent = new Intent(this, SendDataService.class);

        pendingIntent = createPendingResult(TASK_1_CODE, intent, 0);
        intent.putExtra(PARAM_TIME, 7).putExtra(PARAM_PENDING_INTENT, pendingIntent);
        startService(intent);

        pendingIntent = createPendingResult(TASK_2_CODE, intent, 0);
        intent.putExtra(PARAM_TIME, 4).putExtra(PARAM_PENDING_INTENT, pendingIntent);
        startService(intent);

        pendingIntent = createPendingResult(TASK_3_CODE, intent, 0);
        intent.putExtra(PARAM_TIME, 6).putExtra(PARAM_PENDING_INTENT, pendingIntent);
        startService(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult: " + requestCode + ", resultCode = " + resultCode);

        // ловим сообщения о старте задач
        if (resultCode == STATUS_START) {
            switch (requestCode) {
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

        // ловим сообщения об окончании задач
        if (resultCode == STATUS_FINISH) {
            int result = data.getIntExtra(PARAM_RESULT, 0);
            switch (requestCode) {
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
}
