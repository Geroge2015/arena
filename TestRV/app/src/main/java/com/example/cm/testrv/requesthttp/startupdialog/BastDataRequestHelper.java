package com.example.cm.testrv.requesthttp.startupdialog;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.cm.testrv.MyApplication;
import com.example.cm.testrv.utils.KPackageManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by George on 2017/12/13.
 *
 */

public class BastDataRequestHelper {

    private static RequestQueue mQueue = Volley.newRequestQueue(MyApplication.getAppContext());
    private static final int REQUEST_TIMEOUT_MS = 30000;

    private static final String INSERT_BASE_URL = "http://cml.ksmobile.com/";
    public static final String TYPE_INTERFACE = "start-config/getlist?";


    private static final String DEFAULT_CONFIG_FILE = "{\"conf_list\":" +
            "[{\"id\":\"11\",\"pid\":\"3\",\"cid\":\"0\",\"st_time\":\"13\",\"end_time\":\"17\",\"lose_time\":\"\"}," +
            "{\"id\":\"10\",\"pid\":\"2\",\"cid\":\"9\",\"st_time\":\"18\",\"end_time\":\"0\",\"lose_time\":\"\"}," +
            "{\"id\":\"9\",\"pid\":\"2\",\"cid\":\"8\",\"st_time\":\"12\",\"end_time\":\"17\",\"lose_time\":\"\"}," +
            "{\"id\":\"8\",\"pid\":\"2\",\"cid\":\"7\",\"st_time\":\"5\",\"end_time\":\"12\",\"lose_time\":\"\"}," +
            "{\"id\":\"7\",\"pid\":\"1\",\"cid\":\"6\",\"st_time\":\"17\",\"end_time\":\"0\",\"lose_time\":\"\"}," +
            "{\"id\":\"6\",\"pid\":\"1\",\"cid\":\"5\",\"st_time\":\"5\",\"end_time\":\"12\",\"lose_time\":\"\"}]," +
            "\"updatetime\":\"1511883214\",\"next_url\":\"http://cml.ksmobile.com/point-config/getlist\"}";


    public static void requestConfigData(Context context) {
        BaseDataRequest request = new BaseDataRequest(getDataRequestUrl(context), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response != null) {
                    JSONObject defConfigData = null;
                    try {
                        defConfigData = new JSONObject(DEFAULT_CONFIG_FILE);
                        BaseDataParseHelper.saveConfigData(context, defConfigData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    JSONObject defConfigData = new JSONObject(DEFAULT_CONFIG_FILE);
                    BaseDataParseHelper.saveConfigData(context, defConfigData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        request.setShouldCache(false);
        request.setRetryPolicy(new DefaultRetryPolicy(REQUEST_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getVolleyQueue().add(request);
    }

    private static String getDataRequestUrl(Context context) {
        StringBuilder builder = new StringBuilder();
        builder.append(INSERT_BASE_URL).append(TYPE_INTERFACE).append("mcc=").append("310")
                .append("&apkver=").append("51400")
                .append("&aid=").append("9");
        final String url = builder.toString();
        return url;
    }

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
