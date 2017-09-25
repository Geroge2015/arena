package com.example.cm.testrv;

import android.app.Application;
import android.content.Context;

/**
 * Created by cm on 2017/9/25.
 */

public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return mContext;
    }
}
