package com.example.cm.testrv.net;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by George on 2017/9/30.
 *
 */

public class WallpaperItemRequest extends JsonRequest<WallpaperItemRequest.WallpaperItemResult> {


    public static final String TAG = "WallpaperItemRequest";

    public WallpaperItemRequest(String url, Response.Listener<WallpaperItemResult> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, null, listener, errorListener);
        setShouldCache(false);
    }

    public static class WallpaperItemResult {
        public List<WallpaperItem> wallpaperItems;
        public boolean hasMore;
    }

    @Override
    protected Response<WallpaperItemResult> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            JSONObject rootJsonObject = new JSONObject(jsonString);
            JSONArray themeJsonArray = rootJsonObject.optJSONArray("themes");
            int offset = rootJsonObject.optInt("offset");
            boolean hasMore = rootJsonObject.optBoolean("more_themes");
            Log.d(TAG, "jsonArray : " + themeJsonArray + "  offset : " + offset + "  hasMore  : " + hasMore);

            if (themeJsonArray == null) {
                return Response.error(new ParseError());
            }
            List<WallpaperItem> themes = new ArrayList<>();
            for (int i = 0; i < themeJsonArray.length(); i++) {
                JSONObject jsObj = themeJsonArray.optJSONObject(i);
                WallpaperItem wpItem = parseObject(jsObj);
                if (wpItem != null) {
                    themes.add(wpItem);
                }
            }
            WallpaperItemResult result = new WallpaperItemResult();
            result.wallpaperItems = themes;
            result.hasMore = hasMore;

            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }

    }

    private WallpaperItem parseObject(JSONObject jsObj) {
        if (jsObj == null) {
            return null;
        }
        WallpaperItem item = new WallpaperItem();
        item.id = jsObj.optString("id");
        item.packageName = jsObj.optString("pkg_name");
        item.downloadGpUrl = jsObj.optString("gp_url");
        item.lockImageUrl = jsObj.optString("lock_image");
        return item;

    }


}
