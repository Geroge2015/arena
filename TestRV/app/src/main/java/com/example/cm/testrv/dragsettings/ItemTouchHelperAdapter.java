package com.example.cm.testrv.dragsettings;

import android.support.v7.widget.RecyclerView;

/**
 * Created by cm on 2018/1/5.
 */

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);

    void onItemMove(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target);

    boolean canDropOver(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
