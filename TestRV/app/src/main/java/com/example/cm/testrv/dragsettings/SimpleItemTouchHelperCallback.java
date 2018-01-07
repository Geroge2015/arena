package com.example.cm.testrv.dragsettings;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.List;

/**
 * Created by cm on 2018/1/5.
 *
 */

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapter mItemTouchHelperAdapter;

    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        this.mItemTouchHelperAdapter = adapter;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        // We only want the active item
        if (ItemTouchHelper.ACTION_STATE_IDLE != actionState) {
            if (viewHolder instanceof ItemTouchHelperViewHolder) {
                // Let the view holder know that this item is being moved or dragged
                ItemTouchHelperViewHolder itemViewHolder =
                        ((ItemTouchHelperViewHolder) viewHolder);
                itemViewHolder.onItemSelected();
            }
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (viewHolder instanceof ItemTouchHelperViewHolder) {
            // Tell the view holder it's time to restore the idle state
            ItemTouchHelperViewHolder itemViewHolder =
                    ((ItemTouchHelperViewHolder) viewHolder);
            itemViewHolder.onItemClear();
        }
    }


    /**ItemTouchHelper can be used for drag without swipe (or vice versa),
     * so you must designate exactly what you wish to support.
     * Implementations should return true from isLongPressDragEnabled()
     * in order to support starting drag events from a long press on a RecyclerView item.
     * Alternatively, ItemTouchHelper.startDrag(RecyclerView.ViewHolder)
     * can be called to start a drag from a “handle.” This will be explored further later.*/
    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    /**
     * ItemTouchHelper allows you to easily determine the direction of an event.
     * You must override getMovementFlags() to specify which directions of drags and swipes are supported.
     * Use the helper ItemTouchHelper.makeMovementFlags(int, int) to build the returned flags.
     * We’re enabling dragging and swiping in both directions here.
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    /**
     * onMove() and onSwiped() are needed to notify anything in charge of
     * updating the underlying data. So first we’ll create an interface that
     * allows us to pass these event callbacks back up the chain.
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mItemTouchHelperAdapter.onItemMove(viewHolder, target);
        return true;
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder current, RecyclerView.ViewHolder target) {
        return mItemTouchHelperAdapter.canDropOver(current.getAdapterPosition(), target.getAdapterPosition());
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mItemTouchHelperAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
