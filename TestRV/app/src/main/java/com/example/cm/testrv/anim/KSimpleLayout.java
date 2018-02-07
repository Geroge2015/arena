package com.example.cm.testrv.anim;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cm on 2018/2/7.
 */

public class KSimpleLayout extends ViewGroup {

    public KSimpleLayout(Context context) {
        super(context);
    }

    public KSimpleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() > 0) {
            View childView = getChildAt(0);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            measureChildren(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        if (getChildCount() > 0) {
            View childView = getChildAt(0);
            childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
        }

    }
}
