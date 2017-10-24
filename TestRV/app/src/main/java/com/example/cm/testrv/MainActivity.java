package com.example.cm.testrv;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cm.testrv.anim.MyAnimationActivity;
import com.example.cm.testrv.net.MyWallPaperGalleryActivity;
import com.example.cm.testrv.receiver.ChargeReceiver;
import com.example.cm.testrv.service.MyLockerService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyCoolAdapter.MyOnItemClickListener{

    private RecyclerView mListView;
    private ArrayList<String> dataList;

    private ArrayList<View> viewList;
    private int width;
    private int height;
    private MyCoolAdapter adapter;
    private MyLockerService.MyBinder mBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = ((MyLockerService.MyBinder) service);
            mBinder.startTask();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addRecyclerView();
        registerMyReceiver();
    }

    private void registerMyReceiver() {
        ChargeReceiver.register(MyApplication.getAppContext());
    }

    private void addRecyclerView() {
        dataList = new ArrayList<>();

        dataList.add("RecyclerView Demo");
        dataList.add("ViewPager Demo");
        dataList.add("Fragment Demo");
        dataList.add("Start Service");
        dataList.add("Stop Service");
        dataList.add("Bind Service");
        dataList.add("UnBind Service");
        dataList.add("Custom View");
        dataList.add("My Settings");
        dataList.add("request internet");
        dataList.add("read contacts data");
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
                int time = ((int) (System.currentTimeMillis() /1000));
                Log.d("George889", "time  now :" + time);
                jumpToRV();
                break;
            case 1:
                jumpToViewPager();
                break;
            case 2:
                goToCustomView();
                break;
            case 3:
                startLockerService();
                break;
            case 4:
                stopService();
                break;
            case 5:
                bindService();
                break;
            case 6:
                unbindService();
                break;
            case 7:
                goToCustomView(); 
                break;
            case 8:
                gotoSettings();
                break;
            case 9:
                goInternet();
                break;
            case 10:
                readContacts();

            default:
                break;

        }
    }

    private void readContacts() {
        KReadContactActivity.startActivity(this);
    }

    private void gotoSettings() {
        MySettingActivity.startSettingActivity(this);
    }

    private void goToCustomView() {
        MyAnimationActivity.startAnimation(this);
    }

    private void startLockerService() {
        Intent start = new Intent(this, MyLockerService.class);
        startService(start);
    }

    private void stopService() {
        Intent stopIntent = new Intent(this, MyLockerService.class);
        stopService(stopIntent);
    }

    private void bindService() {
        Intent intent = new Intent(this, MyLockerService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    private void unbindService() {
        unbindService(connection);
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


    private void goInternet() {
        MyWallPaperGalleryActivity.startWallpaperActivity(this);

    }

    @Override
    protected void onDestroy() {
        adapter.setOnItemClickListener(null);
        ChargeReceiver.unRegister(MyApplication.getAppContext());
        super.onDestroy();
    }
}
