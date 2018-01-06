package com.example.cm.testrv.dragsettings;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by George on 2018/1/6.
 */

public class ItemBean implements Serializable, Comparable<ItemBean> {
    private static final long serialVersionUID = 4056392726631386552L;
    public static final int TYPE_ADDED = 0;
    public static final int TYPE_REMOVED_HEADER = 1;
    public static final int TYPE_REMOVED = 2;
    String desc;
    Integer order;
    boolean canOpt;
    int viewType;

    /**
     * @param desc     描述
     * @param order    位置
     * @param canOpt   是否含有左侧的操作按钮
     * @param viewType
     */
    public ItemBean(String desc, Integer order, boolean canOpt, int viewType) {
        this.desc = desc;
        this.order = order;
        this.canOpt = canOpt;
        this.viewType = viewType;
    }

    @Override
    public int compareTo(@NonNull ItemBean another) {
        return this.order.compareTo(another.order);
    }
}
