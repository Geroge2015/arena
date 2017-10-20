package com.example.cm.testrv.utils;

import java.util.Locale;

/**
 * Created by George on 2017/10/20.
 *
 */

public class SystemUtils {

    public static String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public static String getFullNameLan() {
        return Locale.getDefault().getDisplayName();
    }


}
