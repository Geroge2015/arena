package com.example.cm.testrv.dragsettings;

import android.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
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

        holder.itemDesc.setText(bean.desc);


        // 长按拖动
        holder.dragBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mDragStartListener.onStartDrag(holder);
                return false;
            }
        });




//        ((MyDragViewHolder) holder).handleView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
//                    mDragStartListener.onStartDrag(holder);
//                }
//                return false;
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }



    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mDataList, fromPosition, toPosition);

///*        *It’s very important to call notifyItemRemoved() and notifyItemMoved()
//         * so the Adapter is aware of the changes. It’s also important to note that
//         * we’re changing the position of the item every time the view is shifted
//         * to a new index, and not at the end of a “drop” event.*/
        notifyItemMoved(fromPosition, toPosition);
    }


    // fixme 是否能放过去
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
        }
    }

}
