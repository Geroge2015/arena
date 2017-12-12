package com.example.cm.testrv.requesthttp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.cm.testrv.R;
import com.example.cm.testrv.utils.CommonUtils;

/**
 * Created by George on 2017/12/11.
 *
 */

public class MyRetrievePhotoActivity extends AppCompatActivity {


    ImageView imageView;
    private Button mButton;


    public static void startActivity(Context context) {
        Intent it = new Intent(context, MyRetrievePhotoActivity.class);
        CommonUtils.startActivity(context, it);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_image_download_resize_activity);
        initView();
        scaleTypeView();
    }

    private void initView() {
        mButton = ((Button) findViewById(R.id.load_button));
        imageView = ((ImageView) findViewById(R.id.resize_imageview));


    }

    private void scaleTypeView() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.evening);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageBitmap(bitmap);

    }

    public void loadImage(View view) {
            String gifUrl = "http://p1.pstatp.com/large/166200019850062839d3";

        String imageUrl = "";
            Glide.with(this).load(imageUrl).into(imageView);
    }


}
