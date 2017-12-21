package com.example.cm.testrv.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.cm.testrv.MyApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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

    public static void getDate() {

        Locale locale;
//        if (Build.VERSION.SDK_INT >= 24) {
//            locale = MyApplication.getAppContext().getResources().getConfiguration().getLocales().get(0);
//        } else {
//        }
        locale = MyApplication.getAppContext().getResources().getConfiguration().locale;
        Log.d("George999", "my locale is: " + locale.toString());
        int offset = TimeZone.getDefault().getRawOffset();
        Log.d("George999", "my Timezone offset is: " + offset);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", locale);
        Locale.getDefault();
        Date date1 = null;


        try {
            date1 = sdf.parse("2007-2-2");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long millis = date1.getTime() + offset;
        long now = System.currentTimeMillis() + offset;

        Log.d("George999", " millis : " + millis);
        Log.d("George999", " now  : " + now);

    }

    public void getCalendar() {
        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH);
        int d = c.get(Calendar.DATE);
    }

    /*
    * @prams beginDate :  "2017-12-15"
    * */
    public boolean checkValidDate(String beginDate, String expireDate) {
        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH);
        int d = c.get(Calendar.DATE);

        Calendar b = Calendar.getInstance();
        c.compareTo(b);
        c.getTime();



        return false;
    }

}
