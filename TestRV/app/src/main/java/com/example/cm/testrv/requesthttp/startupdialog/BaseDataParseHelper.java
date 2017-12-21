package com.example.cm.testrv.requesthttp.startupdialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * Created by cm on 2017/11/30.
 */

public class BaseDataParseHelper {
    private static final String TAG = "BaseData";
    //以下是总结口配置信息的字段
    private static final String KEY_CONFIG_LIST = "conf_list";
    private static final String KEY_CONFIG_ID = "id";
    private static final String KEY_CONFIG_PID = "pid";
    private static final String KEY_CONFIG_CID = "cid";
    private static final String KEY_CONFIG_ST_TIME = "st_time";
    private static final String KEY_CONFIG_END_TIME = "end_time";
    private static final String KEY_CONFIG_LOST_TIME = "lose_time";
    private static final String KEY_CONFIG_UPDATE_TIME = "updatetime";
    private static final String KEY_CONFIG_NEXT_URL = "next_url";
    //以下是分接口的解析字段
    private static final String KEY_SUB_COVER_URL = "coverUrl";
    private static final String KEY_SUB_THUMB_URL = "thumbUrl";

    private static List<ReqDataBean> localInsertList = new ArrayList<>();
    private static boolean updateInsertConfig = true;
    private static int status = 1;

    public static void saveConfigData(Context context, final JSONObject jsonObject) {
        if (jsonObject != null) {

            Runnable runnable = new Runnable() {

                @Override
                public void run() {
                    List<BaseConfigBean> configList = new ArrayList<>();

                    try {
                        String updateTime = jsonObject.optString(KEY_CONFIG_UPDATE_TIME);

                        String nextUrl = jsonObject.optString(KEY_CONFIG_NEXT_URL);

                        JSONArray list = jsonObject.getJSONArray(KEY_CONFIG_LIST);
                        if (list == null || list.length() == 0) {
                            return;
                        }
                        int lenth = list.length();
                        for (int i = 0; i < lenth; i++) {
                            JSONObject object = ((JSONObject) list.get(i));
                            int id = object.optInt(KEY_CONFIG_ID);
                            int pid = object.optInt(KEY_CONFIG_PID);
                            int cid = object.optInt(KEY_CONFIG_CID);
                            int st = object.optInt(KEY_CONFIG_ST_TIME);




                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } finally {
                    }
                }
            };

            ThreadFactory threadFactory = new ThreadFactory() {
                @Override
                public Thread newThread(@NonNull Runnable r) {
                    return null;
                }

            };
        }
    }

    public class MyThreadFactory implements ThreadFactory {

        private int counter;
        private String prefix;

        public MyThreadFactory(String prefix) {
            this.prefix = prefix;
            counter = 1;
        }

        @Override
        public Thread newThread(@NonNull Runnable r) {
            return null;
        }
    }




    public static BaseDataBean parseBaseData(JSONObject object) {
        return parseBaseData(object, 0, 0, 0);
    }

    public static BaseDataBean parseBaseData(JSONObject object, int type, int startTime, int endTime) {

        BaseDataBean bean = new BaseDataBean();
        bean.setType(type);
        bean.setStartTime(startTime);
        bean.setEndTime(endTime);
        String cover_url = object.optString(KEY_SUB_COVER_URL);
        String thumb_url = object.optString(KEY_SUB_THUMB_URL);
        bean.setCoverUrl(cover_url);
        bean.setThumbUrl(thumb_url);

        return bean;

    }





    public static void saveBaseDataToFile(Context context, List<BaseDataBean> items) {
        String path = getFilePath(context);
        Log.d("George", " the path is :" + path);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(items);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getFilePath(Context context) {
        if (context != null) {
            return context.getFilesDir().getAbsolutePath() + File.separator + "my_data_file";
        }
        return "";
    }


    private static class BaseDataComparator implements Comparator<BaseConfigBean> {
        @Override
        public int compare(BaseConfigBean lhs, BaseConfigBean rhs) {
            if (lhs.startTime != rhs.startTime) {
                return lhs.startTime - rhs.startTime;
            } else {
                if (lhs.endTime != rhs.endTime) {
                    return lhs.endTime - rhs.endTime;
                } else {
                    return lhs.subClassId - rhs.subClassId;
                }
            }
        }
    }




}
