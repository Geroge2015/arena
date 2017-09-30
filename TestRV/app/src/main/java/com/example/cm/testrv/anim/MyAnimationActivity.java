package com.example.cm.testrv.anim;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.cm.testrv.R;

public class MyAnimationActivity extends AppCompatActivity {
    private TextView mTv;

    public static void startAnimation(Context context) {
        Intent intent = new Intent(context, MyAnimationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_animation_layout);
        initView();
//        init1();
//        initPoint();
    }

    private void initView() {
        View view = findViewById(R.id.my_custom_title_view);
        MyCircleView circleView = ((MyCircleView) findViewById(R.id.my_custom_circle_view));
        if (null != view) {
            view.setVisibility(View.VISIBLE);
        }
        if (circleView != null) {
            circleView.setVisibility(View.GONE);
        }

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
