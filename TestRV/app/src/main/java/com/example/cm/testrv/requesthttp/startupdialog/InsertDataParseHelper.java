package com.example.cm.testrv.requesthttp.startupdialog;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cm on 2017/11/23.
 */

public class InsertDataParseHelper {
    private static final String TAG = "InsertData";
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


    // 把 bean列表保存到文件
    public static void saveInsertConfigToFile(Context context, List<ReqDataBean> items) {
        final String filePath = getSaveInsertConfigFilePath(context);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(filePath);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(items);
            updateInsertConfig = true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("George" , " saveinsertConfigToFile reqeust io exception !");
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

    private static String getSaveInsertConfigFilePath(Context context) {
        return context.getApplicationContext().getFilesDir().getAbsolutePath() + File.separator + "insert_config_data.ser";
    }

    private boolean isInsertConfigFileExists(Context context) {
        File file = new File(getSaveInsertConfigFilePath(context));
        return file.exists();
    }

    public static List<ReqDataBean> getInsertConfigFromFile(Context context) {
        if (!updateInsertConfig) {
            return localInsertList;
        }
        final File file = new File(getSaveInsertConfigFilePath(context));
        if (!file.exists()) {
            return new ArrayList<>();
        }
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);

            List<ReqDataBean> items = (List<ReqDataBean>) ois.readObject();
            if (items != null && !items.isEmpty()) {
                localInsertList.clear();
                localInsertList.addAll(items);
                updateInsertConfig = false;
                return localInsertList;
            }

        } catch (IOException e) {
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


}
