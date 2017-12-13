package com.example.cm.testrv.requesthttp.startupdialog;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.cm.testrv.MyApplication;

import org.json.JSONObject;

/**
 * Created by George on 2017/12/13.
 *
 */

public class BastDataRequestHelper {

    private static RequestQueue mQueue = Volley.newRequestQueue(MyApplication.getAppContext());
    private static final int REQUEST_TIMEOUT_MS = 30000;



    public static void requestBaseData(Context context, DataRequestListener listener) {
        BaseDataRequest request = new BaseDataRequest(getUrl(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (listener != null) {
                    listener.onRequestSuccess(response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (listener != null) {
                    listener.onResultFailed(error.getMessage());
                }
            }
        });
        request.setShouldCache(false);
        request.setRetryPolicy(new DefaultRetryPolicy(REQUEST_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getVolleyQueue().add(request);

    }

    private static RequestQueue getVolleyQueue() {
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(MyApplication.getAppContext());
        }
        return mQueue;
    }


    private static String getUrl() {
        StringBuilder builder = new StringBuilder();
        builder.append("mcc=")
                .append("313");
        String url = builder.toString();
        Log.d("George9999", " url : " + url);
        return url;
    }


    public interface DataRequestListener {
        void onRequestSuccess(JSONObject object);

        void onResultFailed(String e);
    }
}
