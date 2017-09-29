package com.example.cm.testrv.anim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by cm on 2017/9/29.
 */

public class TestView extends View {

    public static final String TAG = "arrow";
    Paint mPaint;
    Paint bgPaint = new Paint();
    Paint linePaint = new Paint();
    private int px;
    private int py;


    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        px = getMeasuredWidth();
        py = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawArrow(canvas);

    }

    private void drawArrow(Canvas canvas) {
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(0, 0, px, py, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(0, py / 2, px / 2, 0, mPaint);
        canvas.drawLine(px / 2, 0, px / 2, py, mPaint);
        canvas.drawLine(px / 2, 0, px, py / 2, mPaint);
        canvas.save();
        canvas.rotate(90, px / 2, py / 2);
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, py / 2, px / 2, 0, mPaint);
        canvas.drawLine(px / 2, 0, px / 2, py, mPaint);
        canvas.drawLine(px / 2, 0, px, py / 2, mPaint);
        canvas.drawCircle(px - 10, py / 2 - 10, 10, mPaint);
        canvas.save();
        canvas.rotate(90, px / 2, py / 2);
        mPaint.setColor(Color.YELLOW);
        canvas.drawLine(0, py / 2, px / 2, 0, mPaint);
        canvas.drawLine(px / 2, 0, px / 2, py, mPaint);
        canvas.drawLine(px / 2, 0, px, py / 2, mPaint);
        canvas.restore();

        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(px - 10, py/2 - 10, 10, mPaint);

        Log.d(TAG, "drawArrow:  canvas : " + canvas.toString());
    }
}
