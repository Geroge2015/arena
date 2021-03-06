package com.example.cm.testrv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.cm.testrv.dragsettings.SimpleItemTouchHelperCallback;
import com.example.cm.testrv.utils.MySystemUtils;

import java.util.ArrayList;

/**
 * Created by cm on 2017/9/18.
 *
 */

public class MyRecyclerViewActivity extends Activity implements MyCoolAdapter.MyOnItemClickListener{
    private RecyclerView mRecyclerView;
    private ArrayList<String> dataList;
    private MyCoolAdapter adapter;

    public static void startMyRVActivity(Context context) {
        if (null != context) {
            Intent it = new Intent(context, MyRecyclerViewActivity.class);
            context.startActivity(it);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_recyclerview_layout);
        init();
    }

    private void init() {


        dataList = new ArrayList<>();
            for (char i = 'A'; i <= 'z'; i++) {
                dataList.add(i + "");
        }
        mRecyclerView = ((RecyclerView) findViewById(R.id.my_demo_recyclerview));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyCoolAdapter(dataList);
        adapter.setOnItemClickListener(this);
        setHeaderView(mRecyclerView);
        setFooterView(mRecyclerView);
        mRecyclerView.setAdapter(adapter);
        String language = MySystemUtils.getLanguage();
        Toast.makeText(this, language, Toast.LENGTH_SHORT).show();

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);

    }

    private void setHeaderView(RecyclerView view) {
        View header = LayoutInflater.from(this).inflate(R.layout.header_view, view, false);
        adapter.setHeaderView(header);
    }

    private void setFooterView(RecyclerView view) {
        View footer = LayoutInflater.from(this).inflate(R.layout.footer_view, view, false);
        adapter.setFooterView(footer);
    }

    @Override
    public void onItemClickListener(View view, int position) {
        Toast.makeText(getApplicationContext(), "position : " + position, Toast.LENGTH_SHORT).show();

        switch (position) {


        }
    }


}
