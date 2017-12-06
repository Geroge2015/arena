package com.example.cm.testrv.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.cm.testrv.BuildConfig;

/**
 * Created by Geroge on 2017/12/6.
 */

public class CommonUtils {


    public static boolean startActivity(Context context, Intent intent) {
        if (context == null || null == intent) {
            return false;
        }
        boolean bRes = true;

        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            bRes = false;
            if (BuildConfig.DEBUG) Log.d("George", "Oh... start activity exception : "+ e.getMessage());
        }
        return bRes;
    }




}
