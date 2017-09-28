package com.example.cm.testrv.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.preference.Preference;
import android.preference.TwoStatePreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.cm.testrv.R;

/**
 * Created by cm on 2017/9/26.
 *
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

    public static final float ALPHA_RATE = 0.4f;
    private View mView;
    private boolean bActive;
    private final Listener mListener = new Listener();

    private class Listener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (!callChangeListener(isChecked)) {
                // Listener didn't like it, change it back.
                // CompoundButton will make sure we don't recurse.
                buttonView.setChecked(!isChecked);
                return;
            }

            MySwitchPreference.this.setChecked(isChecked);
        }
    }

    public MySwitchPreference(Context context) {
        this(context, null);
    }

    public MySwitchPreference(Context context, AttributeSet attrs) {
        this(context, attrs, com.android.internal.R.attr.switchPreferenceStyle);
    }

    public MySwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bActive = true;
        TypedArray a = context.obtainStyledAttributes(attrs,
                com.android.internal.R.styleable.SwitchPreference);
        setDisableDependentsState(a.getBoolean(
                com.android.internal.R.styleable.SwitchPreference_disableDependentsState, false));
        a.recycle();
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        mView = view;
        View checkableView = view.findViewById(R.id.my_switch_button);
        if (checkableView != null && checkableView instanceof Checkable) {
            if (checkableView instanceof Switch) {
                final Switch switchView = (Switch) checkableView;
                switchView.setOnCheckedChangeListener(null);
                switchView.setChecked(isChecked());
                switchView.setOnCheckedChangeListener(mListener);
            }
        }
        updatePreferenceIcon();
        updatePreferenceLayout(view);
        refreshIcon();
    }

    @Override
    public void setChecked(boolean checked) {
        if (bActive) {
            super.setChecked(checked);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }

    @Override
    public boolean isEnabled() {
        if (bActive) {
            return super.isEnabled();
        } else {
            return true;
        }
    }

    public void setActive(boolean b) {
        bActive = b;
    }

    public boolean isActive() {
        return bActive;
    }

    public View getView() {
        return mView;
    }


    private void updatePreferenceIcon() {
        Drawable d = this.getIcon();
        if (null != d) {
            if (this.isEnabled()) {
                d.setAlpha(255);
            } else {
                d.setAlpha((int) (255 * ALPHA_RATE));
            }
        }
    }

    private void updatePreferenceLayout(View view) {
        if (bActive) {
            view.setAlpha(1);
        } else {
            view.setAlpha(ALPHA_RATE);
        }
    }

    private void refreshIcon() {
        if (mView != null) {
            mView.setActivated(bActive);
        }
    }
}
