package com.example.cm.testrv.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.cm.testrv.R;


/**
 * Created by George on 2017/9/26.
 */

public class MyTextView extends View {

    private static final String TAG = "MyTextView";

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        String text = ta.getString(R.styleable.MyTextView_text);
        int textAttr = ta.getInteger(R.styleable.MyTextView_testAttr, -1);
        Log.d(TAG, "Text = " + text + ",  textAttr = " + textAttr);
        ta.recycle();
    }



}
