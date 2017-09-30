package com.example.cm.testrv.net;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.cm.testrv.MyApplication;

import java.util.List;

/**
 * Created by George on 2017/9/30.
 *
 */

public class MyWpItemLoadManager {

    private Context mContext;
    private static MyWpItemLoadManager sInstance;
    private static byte[] mLock = new byte[0];
    private int mCurrentPage;
    private RequestQueue mRequestQueue;


    public MyWpItemLoadManager() {
        mContext = MyApplication.getAppContext();
        mRequestQueue = Volley.newRequestQueue(mContext);
    }

    public static MyWpItemLoadManager getInstance() {
        if (sInstance == null) {
            synchronized (mLock) {
                if (sInstance == null) {
                    sInstance = new MyWpItemLoadManager();
                }
            }
        }


        return sInstance;
    }

    public void getFromNetwork(final WallpaperItemCallback callback) {
            if (callback == null) {
                return;
            }

        String url = URLUtils.getWallpaperList(0);

        WallpaperItemRequest request = new WallpaperItemRequest(url,
                new Response.Listener<WallpaperItemRequest.WallpaperItemResult>() {
            @Override
            public void onResponse(WallpaperItemRequest.WallpaperItemResult response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mRequestQueue.add(request);

    }


    public interface WallpaperItemCallback {
        void onResult(List<WallpaperItem> items);
        void onErrorResponse();
    }

}
