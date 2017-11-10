package com.example.cm.testrv.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by cm on 2017/11/7.
 */

public class CampaignTrackingReceiver extends BroadcastReceiver {
    static final String INSTALL_ACTION = "com.android.vending.INSTALL_REFERRER";
    static final String CAMPAIGN_KEY = "referrer";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && context != null && INSTALL_ACTION.equals(intent.getAction())) {
            String campaign = intent.getStringExtra(CAMPAIGN_KEY);
            Log.d("GeorgeGo", "CampaignTrackingReceiver receive  com.android.vending.INSTALL_REFERRER  :) ");

        }
    }

}


