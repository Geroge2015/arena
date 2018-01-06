package com.example.cm.testrv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cm.testrv.dragsettings.ItemBean;
import com.example.cm.testrv.dragsettings.MyDragAdapter;
import com.example.cm.testrv.dragsettings.OnStartDragListener;
import com.example.cm.testrv.dragsettings.SimpleItemTouchHelperCallback;
import com.example.cm.testrv.utils.MySystemUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by George on 2018/1/6.
 */

public class MyDragViewActivity extends AppCompatActivity implements
        MyDragAdapter.MyOnItemClickListener, OnStartDragListener {
    private static final String TAG = "MyDragActivity";

    private RecyclerView mRecyclerView;
    private List<ItemBean> mDataList = new ArrayList<>();
    private MyDragAdapter adapter;

    ItemTouchHelper mTouchHelper;

    public static void startMyDragActivity(Context context) {
        Intent intent = new Intent(context, MyDragViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_recyclerview_layout);
        init();

        mRecyclerView = ((RecyclerView) findViewById(R.id.my_demo_recyclerview));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyDragAdapter(mDataList, this);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
        String language = MySystemUtils.getLanguage();
        Toast.makeText(this, language, Toast.LENGTH_SHORT).show();

        // for drag items
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mTouchHelper = new ItemTouchHelper(callback);
        mTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void init() {


        //todo read from file

        Log.d(TAG, "init Data from new user!");

        ItemBean item1 = new ItemBean("Eren Yeager", 0, true, ItemBean.TYPE_ADDED);
        ItemBean item2 = new ItemBean("Mikasa Ackerman", 1, false, ItemBean.TYPE_ADDED);
        ItemBean item3 = new ItemBean("Armin Arlert", 2, true, ItemBean.TYPE_ADDED);
        ItemBean header = new ItemBean("Investigations divided Line ", 3, false, ItemBean.TYPE_REMOVED_HEADER);
        ItemBean item4 = new ItemBean("Bertolt Hoover", 4, true, ItemBean.TYPE_REMOVED);
        ItemBean item5 = new ItemBean("BAnnie Leonhart", 5, true, ItemBean.TYPE_REMOVED);
        mDataList.add(header);
        mDataList.add(item2);
        mDataList.add(item4);
        mDataList.add(item3);
        mDataList.add(item5);
        mDataList.add(item1);
    }


    @Override
    public void onItemClickListener(View view, int position) {
        Toast.makeText(getApplicationContext(), "position : " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mTouchHelper.startDrag(viewHolder);
    }
}
