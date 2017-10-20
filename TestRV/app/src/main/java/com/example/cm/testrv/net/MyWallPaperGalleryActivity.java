package com.example.cm.testrv.net;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cm.testrv.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by George on 2017/9/30.
 *
 */

public class MyWallPaperGalleryActivity extends AppCompatActivity {


    public static final String TAG = "George2018";
    private RecyclerView mRecyclerView;
    private MyWallpaperAdapter mAdapter;
    private ImageLoader imageLoader;
    private GridLayoutManager mGridManager;
    private RequestQueue mQueue;
    public static final String PNG_URL = "https://cmscdn.cmcm.com/applock/themes/t500000126/t-s.png";


    public static void startWallpaperActivity(Context context) {
        Intent intent = new Intent(context, MyWallPaperGalleryActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_wallpaper_list_layout);
//        initView();
        testImageLoader();
    }

    private void testImageLoader() {
        final ImageView imageView = ((ImageView) findViewById(R.id.test_image_view));
        mQueue = Volley.newRequestQueue(getApplicationContext());
        ImageLoader loader = new ImageLoader(mQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });

        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, R.drawable.ic_panda, R.drawable.ic_toys);

        loader.get(PNG_URL, listener, 200, 200);



    }

    private void testImageRequest() {
        final ImageView imageView = ((ImageView) findViewById(R.id.test_image_view));
        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        ImageRequest imageRequest = new ImageRequest(PNG_URL,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        if (imageView != null) {
                            imageView.setImageBitmap(response);
                        }
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(imageRequest);
    }

    private void testJsonRequest() {
        String url = URLUtils.getWallpaperList(1);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, " response ： " + response );
                Log.d(TAG, "  response ：  \n \n \n " + response.toString());
                parseJsonObject(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, " error : " + error.getMessage());
            }
        });

        mQueue = Volley.newRequestQueue(getApplicationContext());
        mQueue.add(jsonObjectRequest);

    }

    public void parseJsonObject(JSONObject response) {
    }

    private void testStringRequest() {
        mQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://www.baidu.com/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "response : " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error : " + error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("param1", "value1");
                map.put("param2", "value2");
                return map;
            }
        };
        mQueue.add(stringRequest);

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

//        testHashMap();
    }

    private void testHashMap() {
        Map<String, String> hashMap = new LinkedHashMap<>(16, 0.75f, true);
        hashMap.put("apple", "苹果");
        hashMap.put("orange", "橙子");
        hashMap.put("banana", "香蕉");
        hashMap.put("peach", "桃子");

        hashMap.get("orange");
        hashMap.get("apple");

        Iterator iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = ((Map.Entry) iterator.next());
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

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
            // TODO: 2017/10/9  
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            // TODO: 2017/10/9  
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
