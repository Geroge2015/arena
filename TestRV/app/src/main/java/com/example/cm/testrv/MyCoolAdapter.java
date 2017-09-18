package com.example.cm.testrv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by George on 2017/9/15.
 *
 */

public class MyCoolAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的

    private ArrayList<String> datalist;
    private MyOnItemClickListener itemClickListener;
    private View mHeaderView;
    private View mFooterView;

    public MyCoolAdapter(ArrayList<String> list) {
        datalist = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (TYPE_HEADER == viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_view, parent, false);
            return new MyHeaderHolder(view);
        } else if (TYPE_FOOTER == viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_view, parent, false);
            return new MyFooterHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_view_holder_layout, parent, false);
            return new MyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            if (null == mHeaderView && null == mFooterView) {
                ((MyViewHolder) holder).textView.setText(datalist.get(position));
            } else {
                ((MyViewHolder) holder).textView.setText(datalist.get(position - 1));
            }
        }
        if (null != itemClickListener) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClickListener(holder.itemView, holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (null == mHeaderView) {
            return datalist.size();
        } else {
            return datalist.size() + 2;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (null == mHeaderView && null == mFooterView) {
            return TYPE_NORMAL;
        }
        if (null != mHeaderView && 0 == position) {
            return TYPE_HEADER;
        } else if (null != mFooterView && (getItemCount() - 1) == position) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    public void setOnItemClickListener(MyOnItemClickListener listener) {
        itemClickListener = listener;
    }

    public void setHeaderView(View header) {
        this.mHeaderView = header;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public void setFooterView(View footer) {
        this.mFooterView = footer;
        notifyItemInserted(getItemCount());
    }

    public View getFooterView() {
        return mFooterView;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        MyViewHolder(View itemView) {
            super(itemView);
            textView = ((TextView) itemView.findViewById(R.id.my_holder_textview));
        }
    }

    class MyHeaderHolder extends RecyclerView.ViewHolder {

        public MyHeaderHolder(View itemView) {
            super(itemView);
        }
    }

    class MyFooterHolder extends RecyclerView.ViewHolder {
        public MyFooterHolder(View itemView) {
            super(itemView);
        }
    }


    /**
     * RecyclerView item点击接口
     */
    public interface MyOnItemClickListener {
        void onItemClickListener(View view, int position);
    }

}

