package com.example.cm.testrv.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by cm on 2017/9/21.
 *
 */

public class MyLockerService extends Service {

    public static final String TAG = "MyLockerService";
    private MyBinder mBinder;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate called !");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand called !");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind called !");
        return mBinder;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy executed !");
        super.onDestroy();
    }

    public class MyBinder extends Binder {
        public void startTask() {
            Log.d(TAG, " Downloading Downloading ... ");
        }
    }
}
