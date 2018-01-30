package com.example.cm.testrv.anim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by cm on 2018/1/30.
 */

public class MyDragView extends View {
    private static final int WIDTH = 200;

    private Rect rect = new Rect(0, 0, WIDTH, WIDTH);
    //绘制矩形的区域
    private int deltaX, deltaY;      //点击位置和图形边界的偏移量
    private static Paint mPaint = new Paint();//画笔

    public MyDragView(Context context) {
        this(context, null);
    }

    public MyDragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    public MyDragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(rect, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!rect.contains(x, y)) {
                    //没有在矩形上点击，不处理触摸消息
                    return false;
                }
                deltaX = x - rect.left;
                deltaY = y - rect.top;
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                Rect old = new Rect(rect);
                //更新矩形位置
                rect.left = x - deltaX;
                rect.top = y - deltaY;
                rect.right = rect.left + WIDTH;
                rect.bottom = rect.top + WIDTH;
                old.union(rect);
                invalidate(old);
                break;
            default:
                break;
        }

        return true;
    }
}
