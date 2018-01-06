package com.example.cm.testrv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.cm.testrv.dragsettings.MyDragAdapter;
import com.example.cm.testrv.dragsettings.OnStartDragListener;
import com.example.cm.testrv.dragsettings.SimpleItemTouchHelperCallback;
import com.example.cm.testrv.utils.MySystemUtils;

import java.util.ArrayList;

/**
 * Created by cm on 2018/1/6.
 */

public class MyDragViewActivity extends AppCompatActivity implements
        MyDragAdapter.MyOnItemClickListener, OnStartDragListener {

    private RecyclerView mRecyclerView;
    private ArrayList<String> dataList;
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

    }

    private void init() {

        dataList = new ArrayList<>();
        for (char i = 'A'; i <= 'z'; i++) {
            dataList.add(i + "");
        }
        mRecyclerView = ((RecyclerView) findViewById(R.id.my_demo_recyclerview));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyDragAdapter(dataList, this);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
        String language = MySystemUtils.getLanguage();
        Toast.makeText(this, language, Toast.LENGTH_SHORT).show();

        // for drag items
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mTouchHelper = new ItemTouchHelper(callback);
        mTouchHelper.attachToRecyclerView(mRecyclerView);

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
