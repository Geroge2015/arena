package com.example.cm.testrv.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.cm.testrv.MyNotificationActivity;
import com.example.cm.testrv.utils.KeyGuardUtils;

/**
 * Created by cm on 2017/12/5.
 */

public class ShortcutBarReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (MyNotificationActivity.ACTION_SHORTCUTBAR_CLICK.equals(action)) {
            Log.d("George999", " Keyguard showing ? :" + KeyGuardUtils.isKeyGuardShow(context));
            Toast.makeText(context, "Keyguardshow : " + KeyGuardUtils.isKeyGuardShow(context), Toast.LENGTH_SHORT).show();
        }
    }
}
