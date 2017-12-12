package com.example.cm.testrv.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.WindowManager;

import com.example.cm.testrv.R;


/**
 * Created by George on 2017/12/12.
 */

public class MyDialog extends Dialog {

    public MyDialog(@NonNull Context context) {
        this(context, R.style.MyDialogStyle);
    }

    public MyDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        setBackground(lp, 0.6f);
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    private void setBackground(WindowManager.LayoutParams lp, float v) {
        lp.dimAmount = v;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

    }

    @Override
    public void setContentView(@NonNull View view) {
        super.setContentView(view);
    }

    @Override
    public void show() {
        super.show();
    }
}
