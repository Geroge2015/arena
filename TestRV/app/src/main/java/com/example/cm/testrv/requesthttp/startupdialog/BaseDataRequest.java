package com.example.cm.testrv.requesthttp.startupdialog;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by George 2017/12/13.
 */

public class BaseDataRequest extends JsonObjectRequest {

    public BaseDataRequest(String url, Response.Listener listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, null, listener, errorListener);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        return super.parseNetworkResponse(response);
    }
}
