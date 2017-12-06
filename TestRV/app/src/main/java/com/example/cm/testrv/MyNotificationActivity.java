package com.example.cm.testrv;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.cm.testrv.notification.ShortcutBarReceiver;

import java.io.File;

/**
 * Created by cm on 2017/11/14.
 * 在通知栏上显示
 */

public class MyNotificationActivity extends AppCompatActivity {
    private static final int LAUNCHER_NOTIFY_ID = 6789;
    public static final String ACTION_SHORTCUTBAR_CLICK = "action_shortcutbar_click";

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
                sendMyNotification();
            }
        });

    }

    private void sendMyNotification() {
        NotificationManager nm = ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE));
        Notification notification = buildNotification();
        if (null != nm) {
            nm.notify(LAUNCHER_NOTIFY_ID, notification);
        }
    }

    private Notification buildNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        Intent it = new Intent(this, ShortcutBarReceiver.class);
        it.setAction(ACTION_SHORTCUTBAR_CLICK);
        PendingIntent pi = PendingIntent.getBroadcast(this, 9, it, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setAutoCancel(false)
                .setOngoing(false)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_panda)
                .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
                .setVibrate(new long[]{0, 1000, 1000, 1000})
                .setLights(Color.GREEN, 1000, 1000)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("What good is the warmth of" +
                        " summer, without the cold of winter to give it sweetness. John Steinbeck" +
                        "It is better to look ahead and prepare than to look back and regret." +
                        "The time is always right to do what is right. Martin Luther King, Jr."))
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.ml_ai_cn)))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(pi);

        return builder.build();
    }
}
