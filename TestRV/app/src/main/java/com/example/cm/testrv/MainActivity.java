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
import com.example.cm.testrv.media.MyCameraActivity;
import com.example.cm.testrv.media.MyMediaActivity;
import com.example.cm.testrv.net.MyWallPaperGalleryActivity;
import com.example.cm.testrv.persistence.TestFileActivity;
import com.example.cm.testrv.receiver.ChargeReceiver;
import com.example.cm.testrv.requesthttp.MyEditTextActivity;
import com.example.cm.testrv.requesthttp.MyRetrievePhotoActivity;
import com.example.cm.testrv.requesthttp.startupdialog.BaseDataBean;
import com.example.cm.testrv.requesthttp.startupdialog.BaseDataParseHelper;
import com.example.cm.testrv.requesthttp.startupdialog.KStartUpPageHelper;
import com.example.cm.testrv.service.MyLockerService;
import com.example.cm.testrv.utils.CommonUtils;
import com.example.cm.testrv.utils.MySystemUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements MyCoolAdapter.MyOnItemClickListener{

    private final String RECYCLERVIEW_DEMO = "RecyclerView Demo";
    private final String VIEW_PAGER_DEMO = "ViewPager Demo";
    private final String DEMO_FRAGMENT = "Fragment Demo";
    private final String START_SERVICE = "Start Service";
    private final String STOP_SERVICE = "Stop Service";
    private final String BIND_SERVICE = "Bind Service";
    private final String UNBIND_SERVICE = "UnBind Service";
    private final String CUSTOM_VIEW = "Custom View";
    private final String DEMO_MY_SETTINGS = "My Settings";
    private final String DEMO_REQUEST_INTERNET = "request internet";
    private final String DEMO_READ_CONTACTS_DATA = "read contacts data";
    private final String DEMO_TEST_FILES_DATA = "test file data";
    private final String GO_TO_INTERNET = "Go to internet !!!";
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


    private enum enumItems {
        RECYCLERVIEW_DEMO, VIEW_PAGER_DEMO, DEMO_FRAGMENT, START_SERVICE, STOP_SERVICE,
        BIND_SERVICE, UNBIND_SERVICE, CUSTOM_VIEW, DEMO_MY_SETTINGS, DEMO_REQUEST_INTERNET,
        DEMO_READ_CONTACTS_DATA,DEMO_TEST_FILES_DATA,GO_TO_INTERNET,SEND_NOTIFICATION, CAMERA_PHOTO,
        VIDEO_MUSIC_PLAY, DOWNLOAD_IMAGE_RESIZE, REQUEST_START_UP_PAGE
    }

    private void addRecyclerView() {
        dataList = new ArrayList<>();
        for (enumItems e : enumItems.values()) {
            dataList.add(e.toString());
        }
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
                break;
            case 11:
                testFileData();
                break;
            case 12:
                testHttpRequest();
                break;
            case 13:
                sendNotification();
                break;
            case 14:
                startCamera();
                break;
            case 15:
                playMusicVideo();
                break;
            case 16:
                downloadPhoto();
                break;
            case 17:
                requestStartPage();
                break;
            default:
                break;

        }
    }

    private void requestStartPage() {
        KStartUpPageHelper.requestConfigFileIfNeed(this);
    }

    private void downloadPhoto() {
        MyRetrievePhotoActivity.startActivity(this);
    }

    private void playMusicVideo() {
        MyMediaActivity.startActivity(this);
    }

    private boolean startCamera() {
        return MyCameraActivity.startActivity(this);
    }

    private void sendNotification() {
        MyNotificationActivity.startNotiActivity(this);
    }

    private void testHttpRequest() {
        MyEditTextActivity.startEditActivity(this);
    }

    private void testFileData() {
        TestFileActivity.startActivity(this);
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
        MySystemUtils.getDate();
        ArrayList<String> list = new MyArrayList();
        list.add("111111");
        list.add("222222");
        list.add("aaaaaa");
        list.add("kkkkkk");
        String string = list.toString();
        Log.d("George2018", " array list to string " + string);
        String[] split = string.split(",");
        Log.d("George2018", "split 0 " + split[0]);
        Log.d("George2018", "split 1 " + split[1]);
        ArrayList<String> arraylist = new ArrayList<>(Arrays.asList(split));
        Log.d("George2018", " arraylist 0 " + arraylist.get(0));
        Log.d("George2018", " arraylist 1 " + arraylist.get(1));
    }


    public class MyArrayList extends ArrayList {
        @Override
        public String toString() {
            if (isEmpty()) {
                return "";
            }

            StringBuilder buffer = new StringBuilder(size() * 16);
            Iterator<?> it = iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (next != this) {
                    buffer.append(next);
                } else {
                    buffer.append("(this Collection)");
                }
                if (it.hasNext()) {
                    buffer.append(",");
                }
            }
            return buffer.toString();
        }
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
