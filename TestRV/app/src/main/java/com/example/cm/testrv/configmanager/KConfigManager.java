package com.example.cm.testrv.configmanager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.cm.testrv.MyApplication;

import java.util.Set;

/**
 * Created by cm on 2017/10/24.
 */

public class KConfigManager implements IServiceConfig {

    private SharedPreferences mSharedPreferences = null;

    public KConfigManager(Context context) {
        String spName = context.getPackageName() + "_preferences";
        mSharedPreferences = MyApplication.getAppContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    private static class InnerConfigManager {
        private static final KConfigManager instance = new KConfigManager(MyApplication.getAppContext());
    }

    public static KConfigManager getInstance() {
        return InnerConfigManager.instance;
    }

    private SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }

    @Override
    public void setIntValue(String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putInt(key, value);
        editor.apply();
    }

    @Override
    public void setLongValue(String key, long value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putLong(key, value);
        editor.apply();
    }

    @Override
    public void setBooleanValue(String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    @Override
    public void setStringValue(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        editor.apply();
    }

    @Override
    public void setFloatValue(String key, float value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    @Override
    public void setStringSet(String key, Set<String> value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putStringSet(key, value);
        editor.apply();
    }


    @Override
    public int getIntValue(String key, int defaultValue) {
        return getSharedPreferences().getInt(key, defaultValue);
    }

    @Override
    public long getLongValue(String key, long defValue) {
        return getSharedPreferences().getLong(key, defValue);
    }

    @Override
    public boolean getBooleanValue(String key, boolean defValue) {
        return getSharedPreferences().getBoolean(key, defValue);
    }

    @Override
    public String getStringValue(String key, String defValue) {
        return getSharedPreferences().getString(key, defValue);
    }

    @Override
    public float getFloatValue(String key, float defValue) {
        return getSharedPreferences().getFloat(key, defValue);
    }

    @Override
    public Set<String> getStringSet(String key, Set<String> defalutValue) {
        return getSharedPreferences().getStringSet(key, defalutValue);
    }

    @Override
    public void removeKey(String key) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.remove(key);
        editor.apply();
    }

    @Override
    public boolean hasKey(String key) {
        return getSharedPreferences().contains(key);
    }

    @Override
    public void clear() {

    }
}
