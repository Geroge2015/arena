package com.example.cm.testrv.net;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewTreeObserver;

import com.android.volley.toolbox.ImageLoader;
import com.example.cm.testrv.R;

import java.util.List;

/**
 * Created by George on 2017/9/30.
 *
 */

public class MyWallPaperGalleryActivity extends AppCompatActivity {


    public static final String TAG = "WPGallery";
    private RecyclerView mRecyclerView;
    private MyWallpaperAdapter mAdapter;
    private ImageLoader imageLoader;
    private GridLayoutManager mGridManager;


    public static void startWallpaperActivity(Context context) {
        Intent intent = new Intent(context, MyWallPaperGalleryActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_wallpaper_list_layout);
        initView();
    }

    private void initView() {
        mRecyclerView = ((RecyclerView) findViewById(R.id.my_wallpaper_list_rv));
        mAdapter = new MyWallpaperAdapter();
        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.addOnScrollListener(new WallpaperOnScrollListener());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setVerticalFadingEdgeEnabled(false);
        // TODO: 2017/9/30   无网络布局
        loadData();
    }


    private RecyclerView.LayoutManager getLayoutManager() {
        mGridManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        mGridManager.setSpanSizeLookup(mAdapter.getSpanSizeLookup());
        return mGridManager;
    }


    public class WallpaperOnScrollListener extends RecyclerView.OnScrollListener {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    }

    private void loadData() {
        startAsyncTask();
    }

    private void startAsyncTask() {

        MyWpItemLoadManager.getInstance().getFromNetwork(new MyWpItemLoadManager.WallpaperItemCallback() {
            @Override
            public void onResult(List<WallpaperItem> items) {
                Log.d(TAG, "onResult: " + items);
                updateListView(items);

            }



            @Override
            public void onErrorResponse() {

            }
        });

        imageLoader = new ImageLoader(MyWpItemLoadManager.getInstance().getRequestQ(), new BitmapCache());

    }

    private void updateListView(List<WallpaperItem> items) {
        if (mAdapter == null || null == mRecyclerView) {
            return;
        }
        if (items != null && !items.isEmpty()) {
            mRecyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    mRecyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
//                    checkWallpaperItemsShow();
                    return true;
                }
            });
            mAdapter.setImageLoader(imageLoader);
            mAdapter.addAllItems(items);
        }
    }

    private void checkWallpaperItemsShow() {
        GridLayoutManager gridLayoutManager = ((GridLayoutManager) mRecyclerView.getLayoutManager());

    }



}
