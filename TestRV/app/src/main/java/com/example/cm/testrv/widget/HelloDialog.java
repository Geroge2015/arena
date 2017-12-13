package com.example.cm.testrv.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.cm.testrv.R;
import com.example.cm.testrv.requesthttp.startupdialog.BaseDataBean;

/**
 * Created by George on 2017/12/12.
 */

public class HelloDialog extends MyDialog {

    private FrameLayout insertContentRoot;
    private BaseDataBean mBean;
    private ImageView imgBg;
    private View insertRoot;
    private Context mContext;

    public HelloDialog(@NonNull Context context, BaseDataBean bean) {
        super(context, R.style.MyDialogStyle);
        this.mBean = bean;
        this.mContext = context;
        init();




    }

    private void init() {
        setContentView(R.layout.layout_hello_dialog_page);
        insertRoot = findViewById(R.id.insert_root);
        imgBg = (ImageView) findViewById(R.id.insert_root_bg);

        insertRoot.post(mLoadRunnable);

//        insertContentRoot.addView();// FIXME: 2017/12/12

    }


    private Runnable mLoadRunnable = new Runnable() {
        @Override
        public void run() {
            Glide.with(mContext).load(mBean.getCoverUrl()).into(imgBg);
        }
    };

    public void loadRequestImage(BaseDataBean bean, Context context) {
        String url = bean.getCoverUrl();
        Glide.with(context).load(url).into(imgBg);

    }
}
