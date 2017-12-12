package com.example.cm.testrv.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;

import com.example.cm.testrv.R;
import com.example.cm.testrv.requesthttp.startupdialog.BaseDataBean;

/**
 * Created by George on 2017/12/12.
 */

public class HelloDialog extends MyDialog {

    private FrameLayout insertContentRoot;
    private BaseDataBean mBean;

    public HelloDialog(@NonNull Context context, BaseDataBean bean) {
        super(context, R.style.MyDialogStyle);
        this.mBean = bean;

        init();




    }

    private void init() {
        setContentView(R.layout.layout_hello_dialog_page);

//        insertContentRoot.addView();// FIXME: 2017/12/12

    }


}
