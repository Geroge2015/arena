package com.example.cm.testrv.net;

/**
 * Created by cm on 2017/9/30.
 *
 */

public class URLUtils {

    public static final String CMS_APPLOCK_DOMAIN = "http://applock.cmcm.com/web/theme/cml_store?cnl=CMLocker";


    public static String getWallpaperList(int offSet) {
        return CMS_APPLOCK_DOMAIN + "&offset=" + offSet;
    }

}

