package com.example.cm.testrv.anim;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.cm.testrv.R;

public class MyAnimationActivity extends AppCompatActivity {
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        init1();
//        initPoint();
    }

    private void initPoint() {

        MyPoint point1 = new MyPoint(0, 0);
        MyPoint point2 = new MyPoint(300, 300);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), point1, point2);
        anim.setDuration(5000);
        anim.start();

    }

    private void init1() {
        mTv = (TextView) findViewById(R.id.my_textview);
        ObjectAnimator movein = ObjectAnimator.ofFloat(mTv, "translationX", -500f, 0);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(mTv, "rotation", 0f, 360f);
        ObjectAnimator scale = ObjectAnimator.ofFloat(mTv, "scaleY", 1f, 5f, 1f);
        scale.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
            }
        });
        AnimatorSet set = new AnimatorSet();
        set.play(scale).with(rotate).after(movein);
        set.setDuration(5000);
        set.start();
    }
}
