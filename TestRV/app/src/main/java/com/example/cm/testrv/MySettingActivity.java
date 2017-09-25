package com.example.cm.testrv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by George on 2017/9/23.
 *
 */

public class MySettingActivity extends AppCompatActivity {

    private MySettingFragment mySettingFragment;

    public static void startSettingActivity(Context context) {
        Intent intent = new Intent(context, MySettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_setting_activity_layout);
        initView();
    }

    private void initView() {
        mySettingFragment = new MySettingFragment();
        FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.my_setting_content_layout, mySettingFragment);
        transaction.commitAllowingStateLoss();
    }
}
