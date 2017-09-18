package com.example.cm.testrv;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyCoolAdapter.MyOnItemClickListener{

    private RecyclerView mListView;
    private ArrayList<String> dataList;

    private ArrayList<View> viewList;
    private int width;
    private int height;
    private MyCoolAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addRecyclerView();
    }

    // test on ViewPager demo
    private void addMyViewPager() {
        View view1 = LayoutInflater.from(this).inflate(R.layout.view_pager1, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.view_pager2, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.view_pager3, null);
        View view4 = LayoutInflater.from(this).inflate(R.layout.view_pager1, null);
        View view5 = LayoutInflater.from(this).inflate(R.layout.view_pager2, null);
        View view6 = LayoutInflater.from(this).inflate(R.layout.view_pager3, null);

        viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
        viewList.add(view5);
        viewList.add(view6);

        ViewPager viewPager = ((ViewPager) findViewById(R.id.my_viewpager));
        PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }
        };
        viewPager.setAdapter(adapter);



    }


    private void addRecyclerView() {
        dataList = new ArrayList<>();

        dataList.add("RecyclerView Demo");
        dataList.add("ViewPager Demo");
        dataList.add("Fragment Demo");
        dataList.add("A");
        dataList.add("B");
        mListView = (RecyclerView) findViewById(R.id.my_recyclerview);
        mListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new MyCoolAdapter(dataList);
        adapter.setOnItemClickListener(this);
        mListView.setAdapter(adapter);
//        mListView.setItemAnimator(new DefaultItemAnimator());
//        mListView.addItemDecoration();



        getPhonePrams(this);

    }

    @Override
    public void onItemClickListener(View view, int position) {
        Toast.makeText(getApplicationContext(), "position : " + position, Toast.LENGTH_SHORT).show();
        switch (position) {
            case 0:
                jumpToRV();
                break;
            case 1:
                jumpToViewPager();
                break;
            default:
                break;

        }
    }

    private void jumpToRV() {
        MyRecyclerViewActivity.startMyRVActivity(this);
    }

    private void jumpToViewPager() {
        MyViewPagerActivity.startMyViewPagerActivity(this);
    }

    public void getPhonePrams(Context context) {
        width = context.getResources().getDisplayMetrics().widthPixels;
        height = context.getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    protected void onDestroy() {
        adapter.setOnItemClickListener(null);
        super.onDestroy();
    }
}
