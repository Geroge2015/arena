package com.example.cm.testrv.anim;

import android.animation.TypeEvaluator;

/**
 * Created by George on 2017/7/18.
 *
 */

public class PointEvaluator implements TypeEvaluator {

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        MyPoint startPoint = (MyPoint) startValue;
        MyPoint endPoint = (MyPoint) endValue;
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        MyPoint point = new MyPoint(x,y);
        return point;
    }
}
