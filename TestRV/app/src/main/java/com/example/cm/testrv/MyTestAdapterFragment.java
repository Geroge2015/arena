package com.example.cm.testrv;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 *Created by George on 2017/9/12
 */

public class MyTestAdapterFragment extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public MyTestAdapterFragment(FragmentManager fm, List<Fragment> fms) {
        super(fm);
        this.mFragments = fms;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getClass().getSimpleName();
    }
}
