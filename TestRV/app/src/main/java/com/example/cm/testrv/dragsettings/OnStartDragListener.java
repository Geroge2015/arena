package com.example.cm.testrv.dragsettings;

import android.support.v7.widget.RecyclerView;

/**
 * Created by cm on 2018/1/6.
 */

public interface OnStartDragListener {
    /**
     * Called when a view is requesting a start of a drag.
     *
     * @param viewHolder The holder of the view to drag.
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
