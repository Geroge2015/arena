package com.example.cm.testrv.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/**
 * Created by cm on 2017/10/21.
 */

public class ChargeReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
            Log.d("George2018", "    @@@   @@@@    battery changed !!");
        }

        if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
            Log.d("George2018", "   ACTION_POWER_CONNECTED  !!");

        }

        if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
            Log.d("George2018", "   ACTION_POWER_DISCONNECTED   ");
        }


    }

    private static BroadcastReceiver sReceiver;

    public static void register(Context context) {
        if (context != null) {
            sReceiver = new ChargeReceiver();
            context.registerReceiver(sReceiver, getIntentFilter());
        }

    }

    public static void unRegister(Context context) {
        if (sReceiver != null) {
            context.unregisterReceiver(sReceiver);
            sReceiver = null;
        }
    }

    private static IntentFilter getIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        return filter;
    }




}
