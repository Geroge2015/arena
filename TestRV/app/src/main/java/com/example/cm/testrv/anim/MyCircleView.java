package com.example.cm.testrv.anim;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.cm.testrv.R;
import com.example.cm.testrv.utils.DimenUtils;

/**
 * Created by George on 2017/9/29.
 */

public class MyCircleView extends View {

    private int mBgColor = -1;
    private float radius;
    private boolean bDrawText;
    private int mCrinkleColor = Color.WHITE;
    private float mCircleWidth = 0;
    private boolean bCircleFill = false;
    private int mImageResId;

    private Bitmap mIconBitmap;
    private Canvas mCanvas;
    private Paint mPaint;


    private RectF mRect;
    private float circleDotX;// 圆点X坐标
    private float circleDotY;// 圆点Y坐标

    public MyCircleView(Context context) {
        this(context, null);
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyCircleView);
        mBgColor = a.getColor(R.styleable.MyCircleView_cv_background, mBgColor);
        radius = a.getDimension(R.styleable.MyCircleView_cv_radius, -1);
        bDrawText = a.getBoolean(R.styleable.MyCircleView_cv_drawText, true);
        mCrinkleColor = a.getColor(R.styleable.MyCircleView_cv_crinkleColor, mCrinkleColor);
        mCircleWidth = a.getDimension(R.styleable.MyCircleView_cv_circle_width, mCircleWidth);
        bCircleFill = a.getBoolean(R.styleable.MyCircleView_cv_circle_fill, bCircleFill);
        mImageResId = a.getResourceId(R.styleable.MyCircleView_cv_img, R.mipmap.ic_launcher_round);
        a.recycle();
        init();
    }

    private void init() {
        mRect = new RectF();
        if (radius < 0) {
            radius = getContext().getResources().getDimensionPixelOffset(R.dimen.my_custom_circle_radio);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        cleanInfo();
        mRect.set(0, 0, right - left, bottom - top);

    }

    private void cleanInfo() {
        mIconBitmap = null;
        circleDotX = mRect.centerX();
        circleDotY = mRect.centerY();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRect.set(0, 0, w, h);
        circleDotX = mRect.centerX();
        circleDotY = mRect.centerY();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mCanvas = canvas;
        drawViews(canvas);
    }

    private void drawViews(Canvas canvas) {
        drawBgCircle(canvas);
        drawIcon(canvas);
    }

    private void drawBgCircle(Canvas canvas) {
        if (null == mPaint) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setColor(mBgColor);
            mPaint.setStrokeWidth(mCircleWidth);
            if (bCircleFill) {
                mPaint.setStyle(Paint.Style.FILL);
            } else {
                mPaint.setStyle(Paint.Style.STROKE);
            }
            canvas.drawColor(Color.TRANSPARENT);
        }
        canvas.drawCircle(circleDotX, circleDotY, ((int) (radius * 0.8)), mPaint);
    }

    @Override
    protected void onDetachedFromWindow() {
        if (null != mIconBitmap) {
            mIconBitmap.recycle();
            mIconBitmap = null;
        }
        super.onDetachedFromWindow();
    }

    private void drawIcon(Canvas canvas) {
        if (mIconBitmap == null) {
            mIconBitmap = BitmapFactory.decodeResource(getResources(), mImageResId);
        }
        canvas.save();

        final int bmpWidth = mIconBitmap.getWidth();
        final int bmpHeight = mIconBitmap.getHeight();
        final int maxBmpSide = Math.max(bmpWidth, bmpHeight);

        final float circleSize = ((float) (radius * 0.8 * 2));
        final float centerY = bDrawText ? circleDotY - DimenUtils.dp2px(5) : circleDotY;
        canvas.translate(circleDotX, centerY);
        canvas.translate(-bmpWidth / 2, -bmpHeight / 2 - 5);
        /* icon的尺寸如果超过背景圆圈的一半就太难看了,最大值0.8f是之前的逻辑 */
        final float scale = (float) Math.min(0.8f, 0.5 * circleSize / maxBmpSide);
        canvas.scale(scale, scale, bmpWidth / 2, bmpHeight / 2);
        /* 画bitmap */
        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawBitmap(mIconBitmap, 0, 0, null);
        canvas.restore();

    }










}
