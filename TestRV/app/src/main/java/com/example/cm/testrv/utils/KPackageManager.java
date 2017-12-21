package com.example.cm.testrv.utils;

import android.content.Context;
import android.content.pm.PackageInfo;

/**
 * Created by cm on 2017/12/21.
 */

public class KPackageManager {



    public static int getPackageVersion(Context context, final String packageName) {

        int nVer = 0;
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
            nVer = info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nVer;

    }
}
