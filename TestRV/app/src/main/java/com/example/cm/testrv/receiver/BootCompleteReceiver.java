package com.example.cm.testrv.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.cm.testrv.MyRecyclerViewActivity;

/**
 * Created by George on 2017/9/21.
 *
 */

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        MyRecyclerViewActivity.startMyRVActivity(context.getApplicationContext());

    }
}
