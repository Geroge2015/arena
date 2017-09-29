package com.example.cm.testrv.anim;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.cm.testrv.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/*
 * Created by George on 2017/8/26.
 */

public class MyRandomView extends View {


    private String mTestText;
    private int mTestColor;
    private int mTestTextSize;

    private Rect mBound;
    private Paint mPaint;


    public MyRandomView(Context context) {
        this(context, null);
    }

    public MyRandomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRandomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyRandomView, defStyleAttr, 0);
//        int n = a.getIndexCount();
//        for (int i = 0; i < n; i++) {
//            int attr = a.getIndex(i);
//            switch (attr) {
//                case R.styleable.CustomTitleView_testText:
//                    mTestText = a.getString(attr);
//                    break;
//                case R.styleable.CustomTitleView_testTextColor:
//                    // 默认颜色设置为黑色
//                    mTestColor = a.getColor(attr, Color.BLACK);
//                    break;
//                case R.styleable.CustomTitleView_testTextSize:
//                    // 默认设置为16sp，TypeValue也可以把sp转化为px
//                    mTestTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
//                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
//                    break;
//
//            }
//        }
        if (null != a) {
            mTestText = a.getString(R.styleable.MyRandomView_testText);
            mTestColor = a.getColor(R.styleable.MyRandomView_testTextColor, Color.BLACK);
            mTestTextSize = a.getDimensionPixelSize(R.styleable.MyRandomView_testTextSize,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
            a.recycle();
            /*
             * 获得绘制文本的宽和高
             */
            mPaint = new Paint();
            mPaint.setTextSize(mTestTextSize);
            mPaint.setColor(mTestColor);
            mBound = new Rect();
            mPaint.getTextBounds(mTestText, 0, mTestText.length(), mBound);
        }

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTestText = randomText();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        postInvalidate();
                    }
                }).start();
            }
        });
    }

    private String randomText() {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < 4) {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }

        StringBuffer sb = new StringBuffer();
        for (Integer i : set) {
            sb.append("" + i);
        }
        return sb.toString();
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
                width = getPaddingLeft() + getPaddingRight() + widthSize;
                break;
            case MeasureSpec.AT_MOST:
                width = getPaddingLeft() + mBound.width() + getPaddingRight();
                break;
            default:
                width = getPaddingLeft() + getPaddingRight() + widthSize;
                break;
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = getPaddingTop() + getPaddingBottom() + heightSize;
                break;
            case MeasureSpec.AT_MOST:
                height = getPaddingTop() + mBound.height() + getPaddingBottom();
                break;
            default:
                height = getPaddingTop() + getPaddingBottom() + heightSize;
                break;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        mPaint.setColor(mTestColor);
        canvas.drawText(mTestText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }
}
