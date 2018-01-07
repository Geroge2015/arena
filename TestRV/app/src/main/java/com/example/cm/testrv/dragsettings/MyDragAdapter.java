package com.example.cm.testrv.dragsettings;

import android.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cm.testrv.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by cm on 2018/1/6.
 */

public class MyDragAdapter extends RecyclerView.Adapter<MyDragAdapter.MyDragViewHolder> implements ItemTouchHelperAdapter {
    private static final String TAG = "ItemAdapter";

    private List<ItemBean> mDataList;
    private MyOnItemClickListener itemClickListener;

    private OnStartDragListener mDragStartListener= null;

    public MyDragAdapter(@NonNull List<ItemBean> datalist, OnStartDragListener mDragStartListener) {
        this.mDataList = datalist;
        Collections.sort(mDataList);
        this.mDragStartListener = mDragStartListener;
    }

    @Override
    public MyDragViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_drag_recycler_view_layout, parent, false);
        return new MyDragViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyDragViewHolder holder, int position) {
        ItemBean bean = mDataList.get(position);
        Log.d(TAG, "onBindViewHolder : pos = " + position + ", item desc = " + bean.desc + ", item order = " + bean.order);
        // 长按拖动
        holder.dragBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mDragStartListener.onStartDrag(holder);
                return false;
            }
        });

        if (bean.viewType == ItemBean.TYPE_REMOVED_HEADER) {
            holder.dragBtn.setVisibility(View.GONE);
            holder.itemDesc.setText(bean.desc);
            Log.d(TAG, "onBindViewHolder :  divider line ----------:    pos = " + position + ", item desc = " + bean.desc + ", item order = " + bean.order);
        } else {
            Log.d(TAG, "onBindViewHolder :  :    pos = " + position + ", item desc = " + bean.desc + ", item order = " + bean.order);
            boolean isAdded = bean.viewType == ItemBean.TYPE_ADDED;
            holder.optBtn.setImageResource(isAdded ? R.drawable.ic_remove_circle_black_24dp: R.drawable.ic_add_circle_black_24dp);
            holder.optBtn.setVisibility(bean.canOpt ? View.VISIBLE : View.INVISIBLE);
            holder.itemDesc.setText(bean.desc);
            holder.optBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isAdded) {
                        onItemClickRemove(position);
                    } else {
                        onItemClickAdd(position);
                    }
                }
            });
        }
    }

    private void resetOrder(List<ItemBean> items) {
        Log.d(TAG, "-------begin to resetOrder---------");
        for (int i = 0; i < items.size(); i++) {
            final ItemBean item = items.get(i);
            item.order = i;
            Log.d(TAG, "i = " + i + ", item : desc = " + item.desc + ", order = " + item.order + " , type : " + item.viewType);
        }
    }
    private int findHeaderPositon() {
        for (int i = 0; i < mDataList.size(); i++) {
            if (mDataList.get(i).viewType == ItemBean.TYPE_REMOVED_HEADER) {
                return i;
            }
        }
        Log.e(TAG, "cannot be happened!!");
        return 1;
    }

    private void showEmptyTipsIfNeed() {

    }

    private void onItemClickAdd(int position) {
        Log.d(TAG, "onItemClick ADD : pos = " + position);

        final int headerPos = findHeaderPositon();
        Log.d(TAG, "onItemClick ADD :  divider  pos = " + headerPos);
        ItemBean addItemBean = mDataList.remove(position);    //  remove it  and get the removed object.
        addItemBean.viewType = ItemBean.TYPE_ADDED;
        mDataList.add(headerPos, addItemBean);
        resetOrder(mDataList);
        notifyDataSetChanged();
        showEmptyTipsIfNeed();
    }

    public void onItemClickRemove(int pos) {
        Log.d(TAG, "onItemClick REMOVE : pos = " + pos);
        final ItemBean removeBean = mDataList.remove(pos);
        removeBean.viewType = ItemBean.TYPE_REMOVED;
        mDataList.add(removeBean);
        resetOrder(mDataList);
        notifyDataSetChanged();
        showEmptyTipsIfNeed();
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position).viewType;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mDataList, fromPosition, toPosition);
        resetOrder(mDataList);
///*        *It’s very important to call notifyItemRemoved() and notifyItemMoved()
//         * so the Adapter is aware of the changes. It’s also important to note that
//         * we’re changing the position of the item every time the view is shifted
//         * to a new index, and not at the end of a “drop” event.*/
        notifyItemMoved(fromPosition, toPosition);
        notifyItemChanged(fromPosition, toPosition);
    }

//    @Override
//    public void onItemMove(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//        int fromPostion = viewHolder.getAdapterPosition();
//        int toPosition = target.getAdapterPosition();
//
//        ItemBean bean = mDataList.remove(fromPostion);
//        mDataList.add(toPosition, bean);
//        int dividerPos = findHeaderPositon();
//        Log.d(TAG, "onItemMove ...   fromPosition :" + fromPostion + "toPosition : " + toPosition + " divider line pos: " + dividerPos);
////        if (toPosition > dividerPos) {
////            bean.viewType = ItemBean.TYPE_REMOVED;
//////            ((MyDragViewHolder) target).optBtn.setImageResource(R.drawable.ic_add_circle_black_24dp);
////        } else {
////            bean.viewType = ItemBean.TYPE_ADDED;
//////            ((MyDragViewHolder) target).optBtn.setImageResource(R.drawable.ic_remove_circle_black_24dp);
////        }
//        resetOrder(mDataList);
/////*        *It’s very important to call notifyItemRemoved() and notifyItemMoved()
////         * so the Adapter is aware of the changes. It’s also important to note that
////         * we’re changing the position of the item every time the view is shifted
////         * to a new index, and not at the end of a “drop” event.*/
//        notifyItemMoved(fromPostion, toPosition);
//        notifyItemChanged(fromPostion, toPosition);
//    }

    @Override
    public boolean canDropOver(int fromPosition, int toPosition) {
        return true;
    }



    @Override
    public void onItemDismiss(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
    }

    public void setOnItemClickListener(MyOnItemClickListener listener) {
        itemClickListener = listener;
    }


    /**
     * RecyclerView item点击接口
     */
    public interface MyOnItemClickListener {
        void onItemClickListener(View view, int position);
    }



    /**
     * Drag View Holder
     *
     * */
    class MyDragViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        private ImageView optBtn;
        private TextView itemDesc;
        private ImageView dragBtn;
        private View removeView;
        private SwipeMenuLayout menuLayout;


        MyDragViewHolder(View itemView) {
            super(itemView);
            optBtn = (ImageView) itemView.findViewById(R.id.iv_opt);
            itemDesc = (TextView) itemView.findViewById(R.id.tv_item_desc);
            dragBtn = (ImageView) itemView.findViewById(R.id.iv_drag);
            removeView = itemView.findViewById(R.id.rl_remove);
            menuLayout = (SwipeMenuLayout) itemView.findViewById(R.id.sml);
        }

        @Override
        public void onItemSelected() {
            Log.d(TAG, "onItemSelected...");
            // itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            Log.d(TAG, "onItemClear...");
            // itemView.setBackgroundColor(0);
            int position = this.getAdapterPosition();
            if (position < 0 || position > mDataList.size() - 1) {
                return;
            }
            ItemBean bean = mDataList.get(position);
            int dividerPos = findHeaderPositon();
            if (position > dividerPos) {
                bean.viewType = ItemBean.TYPE_REMOVED;
                this.optBtn.setImageResource(R.drawable.ic_add_circle_black_24dp);
            } else if (position < dividerPos) {
                bean.viewType = ItemBean.TYPE_ADDED;
                this.optBtn.setImageResource(R.drawable.ic_remove_circle_black_24dp);
            }
            printMyLog(mDataList);
            notifyDataSetChanged();
        }
        private void printMyLog(List<ItemBean> items) {
            Log.d("George2018go", "-------begin to resetOrder---------");
            for (int i = 0; i < items.size(); i++) {
                final ItemBean item = items.get(i);
                item.order = i;
                Log.d("George2018go", "i = " + i + ", item : desc = " + item.desc + ", order = " + item.order + " , type : " + item.viewType);
            }
        }
    }

}
