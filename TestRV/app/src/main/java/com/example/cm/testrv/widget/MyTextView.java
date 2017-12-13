package com.example.cm.testrv.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;

import com.example.cm.testrv.R;


/**
 * Created by George on 2017/9/26.
 */

public class MyTextView extends AppCompatTextView {
    private static final String TAG = "MyTextView";
    private String familyName;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        String text = ta.getString(R.styleable.MyTextView_text);
        int textAttr = ta.getInteger(R.styleable.MyTextView_testAttr, -1);
        familyName = ta.getString(R.styleable.MyTextView_familyName);
        Log.d(TAG, "Text = " + text + ",  textAttr = " + textAttr);
        ta.recycle();
        setType();
    }

    private void setType() {
        if (!TextUtils.isEmpty(familyName)) {
            try {
                Typeface tf = Typeface.create(familyName, Typeface.NORMAL);
                this.setTypeface(tf);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
