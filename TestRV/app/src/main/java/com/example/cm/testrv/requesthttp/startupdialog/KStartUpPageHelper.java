package com.example.cm.testrv.requesthttp.startupdialog;

import android.content.Context;

/**
 * Created by cm on 2017/12/21.
 */

public class KStartUpPageHelper {


    public static void requestConfigFileIfNeed(Context context) {
        BastDataRequestHelper.requestConfigData(context);
    }
}
