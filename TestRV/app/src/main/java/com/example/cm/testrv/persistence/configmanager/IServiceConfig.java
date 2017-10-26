package com.example.cm.testrv.persistence.configmanager;

import java.util.Set;

/**
 * Created by cm on 2017/10/24.
 */

public interface IServiceConfig {

    void setIntValue(String key, int value);

    int getIntValue(String key, int defValue);

    void setLongValue(String key, long value);

    long getLongValue(String key, long defValue);

    void setBooleanValue(String key, boolean value);

    boolean getBooleanValue(String key, boolean defValue);

    void setStringValue(String key, String value);

    String getStringValue(String key, String defValue);

    void setFloatValue(String key, float value);

    float getFloatValue(String key, float defValue);

    void setStringSet(String key, Set<String> value);

    Set<String> getStringSet(String key, Set<String> defValue);

    void removeKey(String key);

    boolean hasKey(String key);

    void clear();
}
