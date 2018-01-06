package com.example.cm.testrv.dragsettings;

import android.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cm.testrv.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by cm on 2018/1/6.
 */

public class MyDragAdapter extends RecyclerView.Adapter<MyDragAdapter.MyDragViewHolder> implements ItemTouchHelperAdapter {

    private ArrayList<String> datalist;
    private MyOnItemClickListener itemClickListener;

    private OnStartDragListener mDragStartListener= null;

    public MyDragAdapter(ArrayList<String> datalist, @NonNull OnStartDragListener mDragStartListener) {
        this.datalist = datalist;
        this.mDragStartListener = mDragStartListener;
    }

    @Override
    public MyDragViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_drag_recycler_view_layout, parent, false);
        return new MyDragViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyDragViewHolder holder, int position) {
        holder.textView.setText(datalist.get(position));

        // 长按拖动
        holder.handleView.setOnLongClickListener(new View.OnLongClickListener() {
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
        return datalist.size();
    }



    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(datalist, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        datalist.remove(position);
        notifyItemRemoved(position);
    }


    class MyDragViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public final ImageView handleView;
        MyDragViewHolder(View itemView) {
            super(itemView);
            textView = ((TextView) itemView.findViewById(R.id.my_drag_holder_textview));
            handleView = ((ImageView) itemView.findViewById(R.id.my_handle_icon));

        }
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
}
