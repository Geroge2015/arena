package com.example.cm.testrv;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

/**
 * Created by cm on 2017/11/14.
 */

public class MyNotificationActivity extends AppCompatActivity {
    private static final int LAUNCHER_NOTIFY_ID = 6789;

    private Button mNotiBtn;

    public static void startNotiActivity(Context context) {
        Intent intent = new Intent(context, MyNotificationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_notification_test);
        init();

    }

    private void init() {
        mNotiBtn = ((Button) findViewById(R.id.send_notice_btn));
        mNotiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotifications();
            }
        });

    }

    private void sendNotifications() {
        NotificationManager notiMgr = ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE));
        Notification notification = createNotification();
        if (notiMgr != null) {
            notiMgr.notify(LAUNCHER_NOTIFY_ID, notification);
        }

    }

    // from  MemoryAndBatteryNotification  cml
    private Notification createNotification() {
//        Notification notification = new Notification(R.drawable.ic_panda, "This is a ticker text", System.currentTimeMillis());

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, getIntent(), PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.mem_bat_notify_layout);
        builder.setContent(remoteViews);
        remoteViews.setOnClickPendingIntent(R.id.notify_btn, pendingIntent);

        builder.setAutoCancel(false)
                .setWhen(System.currentTimeMillis())
                .setOngoing(false)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setSmallIcon(R.drawable.ic_panda)
                .setContentIntent(pendingIntent);

        return builder.build();
    }
}
