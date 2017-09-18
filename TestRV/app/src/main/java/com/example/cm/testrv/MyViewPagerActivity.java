package com.example.cm.testrv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by cm on 2017/9/18.
 *
 */

public class MyViewPagerActivity extends Activity {
    ViewPager viewPager;
    MyViewPagerAdapter viewPagerAdapter;
    ArrayList<View> viewList;

    public static void startMyViewPagerActivity(Context ctx) {
        if (null != ctx) {
            Intent intent = new Intent(ctx, MyViewPagerActivity.class);
            ctx.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_view_pager_layout);
        init();
    }

    private void init() {
        viewPager = ((ViewPager) findViewById(R.id.test_view_pager));
        viewList = new ArrayList<>();
        viewList.add(LayoutInflater.from(this).inflate(R.layout.view_pager1, null));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.view_pager2, null));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.view_pager3, null));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.view_pager1, null));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.view_pager2, null));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.view_pager3, null));
        viewPagerAdapter = new MyViewPagerAdapter(viewList);
        viewPager.setAdapter(viewPagerAdapter);

    }


}
