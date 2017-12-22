package com.example.cm.testrv.requesthttp.startupdialog;

import android.content.Context;
import android.util.Log;

import com.example.cm.testrv.utils.mythread.MyThreadFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
                    Log.d("George999", "runnable: configlist is array list");
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
                            int et = object.optInt(KEY_CONFIG_END_TIME);

                            BaseConfigBean.Builder builder = new BaseConfigBean.Builder();
                            builder.setId(id)
                                    .setClassId(pid)
                                    .setSubClassId(cid)
                                    .setStartTime(st)
                                    .setEndTime(et)
                                    .setBeginDate(0)
                                    .setExpireDate(Long.MAX_VALUE);
                            final BaseConfigBean baseConfigBean = builder.build();
                            configList.add(baseConfigBean);
                        }
                        Collections.sort(configList, new BaseBeanComparator());
                        writeConfigDataToFile(context, configList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } finally {
                    }
                }
            };

            MyThreadFactory myThreadFactory = new MyThreadFactory("MyTestThreadFactory");
            Thread thread = myThreadFactory.newThread(runnable);
            thread.start();
        }
    }

    private static void writeConfigDataToFile(Context context, List<BaseConfigBean> configList) {
        final String path = getFilePath(context);
        Log.d("George999", "path   " + path);

        FileOutputStream fos = null;
        BufferedOutputStream buffer = null;
        ObjectOutputStream oos = null;
        Log.d("George999", "config list:  " + configList);

        try {
            //序列化文件輸出流
            fos = new FileOutputStream(path);
            //构建缓冲流
            buffer = new BufferedOutputStream(fos);
            //构建字符输出的对象流
            oos = new ObjectOutputStream(buffer);
            oos.writeObject(configList);
            Log.d("George999", "writeConfigDataToFile  succeed :) ");
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
            if (buffer != null) {
                try {
                    buffer.close();
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

    public static class BaseBeanComparator implements Comparator<BaseConfigBean> {
        @Override
        public int compare(BaseConfigBean lhs, BaseConfigBean rhs) {
            if (lhs.startTime == rhs.startTime) {
                if (lhs.endTime == rhs.endTime) {
                    return lhs.subClassId - rhs.subClassId;
                } else {
                    return lhs.endTime - rhs.endTime;
                }
            } else {
                return lhs.startTime - rhs.startTime;
            }
        }
    }

    public static List<BaseConfigBean> getConfigDataFromFile(Context context) {
        String path = getFilePath(context);
        Log.d("George999", "get from file path : " + path);

        final File file = new File(path);
        Log.d("George999", "   file  : " + file.toString());
        Log.d("George999", " file  : " + file.length());

        if (!file.exists()) {
            Log.d("George999", "   EMPTY : ");
            return new ArrayList<>();
        }
        FileInputStream fis = null;
        BufferedInputStream buffer = null;
        ObjectInputStream ois = null;
        List<BaseConfigBean> configList = new ArrayList<>();
        try {
            fis = new FileInputStream(file);
            buffer = new BufferedInputStream(fis);
            ois = new ObjectInputStream(buffer);
            Log.d("George999", "new ObjectInputStream : " + ois);
            Object o = ois.readObject();
            return configList = ((List<BaseConfigBean>) o);
        } catch (IOException e) {
            Log.d("George999", "new ObjectInputStream : " + ois);

            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ArrayList<>();
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

    public static String getFilePath(Context context) {
        if (context != null) {
            String path = context.getFilesDir().getAbsolutePath() + File.separator + "my_data_file.ser";
            return path;
        }
        return "";
    }



}
