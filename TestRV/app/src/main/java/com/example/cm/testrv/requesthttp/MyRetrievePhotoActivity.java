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
import com.example.cm.testrv.requesthttp.startupdialog.BaseDataBean;
import com.example.cm.testrv.requesthttp.startupdialog.BaseDataParseHelper;
import com.example.cm.testrv.requesthttp.startupdialog.BastDataRequestHelper;
import com.example.cm.testrv.utils.CommonUtils;
import com.example.cm.testrv.widget.HelloDialog;

import org.json.JSONObject;

/**
 * Created by George on 2017/12/11.
 *
 */

public class MyRetrievePhotoActivity extends AppCompatActivity {
    public static final String EXTRA_KEY_INSERT_DATA_BEAN = "extra_key_insert_data_bean";

    ImageView imageView;
    private Button mButton;
    private HelloDialog mDialog;
    private Button retrieveBtn;

    public static void startActivity(Context context) {
        startActivity(context, null);
    }

    public static void startActivity(Context context, BaseDataBean bean) {
        Intent it = new Intent(context, MyRetrievePhotoActivity.class);
        if (bean != null) {
            it.putExtra(EXTRA_KEY_INSERT_DATA_BEAN, bean);
        }
        CommonUtils.startActivity(context, it);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_image_download_resize_activity);
        initView();
    }

    private void initView() {
        mButton = ((Button) findViewById(R.id.load_button));
        imageView = ((ImageView) findViewById(R.id.resize_imageview));
        retrieveBtn = ((Button) findViewById(R.id.retrieve_data));
        Intent intent = getIntent();
        BaseDataBean bean = intent.getParcelableExtra(EXTRA_KEY_INSERT_DATA_BEAN);
        if (bean != null) {
            mDialog = new HelloDialog(this , bean);
        }
        retrieveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveData(MyRetrievePhotoActivity.this);
            }
        });
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

    public void showHelloDialog(View view) {
        if (mDialog != null) {
            mDialog.show();
        }
    }


    public void retrieveData(Context context) {
        BastDataRequestHelper.requestBaseData(context, requestListener);

    }

    BastDataRequestHelper.DataRequestListener requestListener = new BastDataRequestHelper.DataRequestListener() {
        @Override
        public void onRequestSuccess(JSONObject object) {
            BaseDataBean bean = BaseDataParseHelper.parseBaseData(object);
            mDialog.loadRequestImage(bean, MyRetrievePhotoActivity.this);
        }

        @Override
        public void onResultFailed(String e) {

        }
    };



}
