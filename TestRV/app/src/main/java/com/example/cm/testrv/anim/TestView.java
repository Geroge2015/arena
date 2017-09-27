package com.example.cm.testrv.anim;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.cm.testrv.R;

/**
 * Created by George on 2017/8/27.
 *
 */

public class TestView extends View {

    String myText;
    int myColor;
    int mySize;
    Paint mPaint;
    Rect mBound;

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 构造函数：获得自定义样式和属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TestView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.TestView_myText:
                    myText = a.getString(attr);
                    break;
                case R.styleable.TestView_myTextColor:
                    myColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.TestView_myTextSize:
                    int sp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics());
                    mySize = a.getDimensionPixelSize(attr, sp);
                    break;
            }
        }
        a.recycle();

        /**
         * 获得绘制文本的宽和高  mBound赋值。
         */
        mPaint = new Paint();
        mPaint.setColor(myColor);
        mPaint.setTextSize(mySize);
        mBound = new Rect();
        mPaint.getTextBounds(myText, 0, myText.length(), mBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width;
        int height;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                width = getPaddingLeft() + widthSize + getPaddingRight();
                break;
            case MeasureSpec.AT_MOST:
                width = getPaddingLeft() + mBound.width() + getRight();
                break;
            default:
                width = getPaddingLeft() + widthSize + getPaddingRight();
                break;
        }






    }
}
