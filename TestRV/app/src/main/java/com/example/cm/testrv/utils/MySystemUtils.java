package com.example.cm.testrv.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.Locale;

/**
 * Created by George on 2017/10/20.
 *
 */

public class MySystemUtils {

    public static String getLanguage() {
        return Locale.getDefault().getLanguage().toLowerCase();
    }

    public static String getCountry() {
        return Locale.getDefault().getCountry().toLowerCase();
    }

    public static String getFullNameLan() {
        return Locale.getDefault().getDisplayName();
    }

    public static int getFullScreenHeight(Context context) {
        WindowManager wm = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE));
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int getFullScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }


    public static int getStartEndHour(String span, boolean isStart, int defTime) {
        if (!TextUtils.isEmpty(span)) {
            try {
                String[] items = span.split(",");
                Log.d("GeorgeGo", "items " + items);
                if (2 == items.length) {
                    int start = Integer.valueOf(items[0]);
                    int end = Integer.valueOf(items[1]);
                    if (start >= 0 && end <= 24 && start <= end) {
                        return isStart ? start : end;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defTime;


    }

}
