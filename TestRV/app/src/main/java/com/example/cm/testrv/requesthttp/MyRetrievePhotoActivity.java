package com.example.cm.testrv.requesthttp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.cm.testrv.R;
import com.example.cm.testrv.utils.CommonUtils;

/**
 * Created by George on 2017/12/11.
 *
 */

public class MyRetrievePhotoActivity extends AppCompatActivity {


    ImageView imageView;


    public static void startActivity(Context context) {
        Intent it = new Intent(context, MyRetrievePhotoActivity.class);
        CommonUtils.startActivity(context, it);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_image_download_resize_activity);
        initView();
    }

    private void initView() {
        imageView = ((ImageView) findViewById(R.id.resize_imageview));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.evening);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageBitmap(bitmap);

    }


}
