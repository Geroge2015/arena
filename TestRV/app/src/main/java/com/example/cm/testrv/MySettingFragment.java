package com.example.cm.testrv;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

/**
 * Created by cm on 2017/9/25.
 */

public class MySettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.my_setting_preference);

    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        return false;
    }
}
