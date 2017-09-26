package com.example.cm.testrv.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.preference.TwoStatePreference;
import android.util.AttributeSet;
import android.view.View;

import com.example.cm.testrv.R;

/**
 * Created by cm on 2017/9/26.
 *
 /**
 * A {@link Preference} that provides a two-state toggleable option.
 * <p>
 * This preference will store a boolean into the SharedPreferences.
 *
 * @attr ref android.R.styleable#SwitchPreference_summaryOff
 * @attr ref android.R.styleable#SwitchPreference_summaryOn
 * @attr ref android.R.styleable#SwitchPreference_switchTextOff
 * @attr ref android.R.styleable#SwitchPreference_switchTextOn
 * @attr ref android.R.styleable#SwitchPreference_disableDependentsState
 */

public class MySwitchPreference extends TwoStatePreference {


    public MySwitchPreference(Context context) {
        this(context, null);
    }

    public MySwitchPreference(Context context, AttributeSet attrs) {
        this(context, attrs, com.android.internal.R.attr.switchPreferenceStyle);
    }

    public MySwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, com.android.internal.R.styleable.SwitchPreference);
        setDisableDependentsState(a.getBoolean(
                com.android.internal.R.styleable.SwitchPreference_disableDependentsState, false));
        a.recycle();
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
    }


    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
    }


    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }
}
