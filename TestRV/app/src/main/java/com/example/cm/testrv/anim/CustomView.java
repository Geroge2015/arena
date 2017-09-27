package com.example.cm.testrv.anim;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by George on 2017/8/23.
 *
 */

public class CustomView extends View {

    public static final float RADIUS = 50f;
    private MyPoint currentPoint;
    private Paint mPaint;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        if (null == currentPoint) {
            currentPoint = new MyPoint(RADIUS, RADIUS);
            drawCircle(canvas);
            startAnim();
        } else {
            drawCircle(canvas);
            startAnim();

        }
    }

    private void startAnim() {
        MyPoint startP = new MyPoint(RADIUS, RADIUS);
        MyPoint endP = new MyPoint(getWidth() - RADIUS, getHeight() - RADIUS);
        ValueAnimator va = ValueAnimator.ofObject(new PointEvaluator(), startP, endP);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (MyPoint) animation.getAnimatedValue();
                invalidate();
            }
        });
        va.setDuration(5000);
        va.start();
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(currentPoint.getX(), currentPoint.getY(), RADIUS, mPaint);
    }

}
