package com.example.cm.testrv.persistence.configmanager;

import java.util.Set;

/**
 * Created by cm on 2017/10/24.
 */

public class KBaseConfigMgr implements IServiceConfig {

    @Override
    public void setIntValue(String key, int value) {
        KConfigManager.getInstance().setIntValue(key, value);
    }

    @Override
    public int getIntValue(String key, int defValue) {
        return KConfigManager.getInstance().getIntValue(key, defValue);
    }

    @Override
    public void setLongValue(String key, long value) {
        KConfigManager.getInstance().setLongValue(key, value);
    }

    @Override
    public long getLongValue(String key, long defValue) {
        return KConfigManager.getInstance().getLongValue(key, defValue);
    }

    @Override
    public void setBooleanValue(String key, boolean value) {
        KConfigManager.getInstance().setBooleanValue(key, value);
    }

    @Override
    public boolean getBooleanValue(String key, boolean defValue) {
        return KConfigManager.getInstance().getBooleanValue(key, defValue);
    }

    @Override
    public void setStringValue(String key, String value) {
        KConfigManager.getInstance().setStringValue(key, value);
    }

    @Override
    public String getStringValue(String key, String defValue) {
        return KConfigManager.getInstance().getStringValue(key, defValue);
    }

    @Override
    public void setFloatValue(String key, float value) {
        KConfigManager.getInstance().setFloatValue(key, value);
    }

    @Override
    public float getFloatValue(String key, float defValue) {
        return KConfigManager.getInstance().getFloatValue(key, defValue);
    }

    @Override
    public void setStringSet(String key, Set<String> value) {
        KConfigManager.getInstance().setStringSet(key, value);
    }

    @Override
    public Set<String> getStringSet(String key, Set<String> defValue) {
        return KConfigManager.getInstance().getStringSet(key, defValue);
    }

    @Override
    public void removeKey(String key) {
        KConfigManager.getInstance().removeKey(key);
    }

    @Override
    public boolean hasKey(String key) {
        return KConfigManager.getInstance().hasKey(key);
    }

    @Override
    public void clear() {
        KConfigManager.getInstance().clear();
    }
}
