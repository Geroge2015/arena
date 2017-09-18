package com.example.cm.testrv;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by cm on 2017/9/18.
 *
 */

public class MyViewPagerAdapter extends PagerAdapter {

    ArrayList<View> mViewList;

    MyViewPagerAdapter(ArrayList<View> list) {
        if (null == list) {
            throw new IllegalArgumentException("PagerAdapter need data !!!");
        } else {
            mViewList = list;
        }
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }


}
