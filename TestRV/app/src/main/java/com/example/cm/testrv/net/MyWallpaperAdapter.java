package com.example.cm.testrv.net;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.cm.testrv.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by George on 2017/9/30.
 *
 */

public class MyWallpaperAdapter extends RecyclerView.Adapter {

    public static final int TYPE_ITEM   = 0;
    public static final int TYPE_FOOTER = 1;

    private ArrayList<WallpaperItem> arrayList;
    private ImageLoader mLoader;

    private GridLayoutManager.SpanSizeLookup mSpanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
        @Override
        public int getSpanSize(int position) {
            switch (getItemViewType(position)) {
                case TYPE_ITEM:
                    return 1;
                case TYPE_FOOTER:
                    return 3;
                default:
                    return -1;
            }
        }
    };

    public WallpaperItem getItem(int index) {
        return arrayList.get(index);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = ((LayoutInflater) parent.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE));
            View view = inflater.inflate(R.layout.my_wallpaper_item_recycler_view_item, parent, false);
            return new WallpaperHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof WallpaperHolder) {
            bindLayout(((WallpaperHolder) holder), position);
        }

    }

    private void bindLayout(WallpaperHolder holder, int position) {
        if (arrayList != null && position < arrayList.size()) {
            final WallpaperItem wallpaperItem = arrayList.get(position);
            holder.imageView.setImageUrl(wallpaperItem.lockImageUrl, mLoader);
            holder.imageView.setBackgroundColor(Color.GRAY);

        }

    }

    @Override
    public int getItemCount() {
        return null == arrayList ? 1 : arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
//        if (getItemCount() - 1 == position) {
//            return TYPE_FOOTER;
//        } else {
            return TYPE_ITEM;
//        }
    }

    public void addAllItems(List<WallpaperItem> items) {
        this.arrayList = new ArrayList<>();
        arrayList.addAll(items);


        this.notifyDataSetChanged();
    }

    public void setImageLoader(ImageLoader loader) {
        this.mLoader = loader;
    }

    public GridLayoutManager.SpanSizeLookup getSpanSizeLookup() {
        return mSpanSizeLookup;
    }

    private class WallpaperHolder extends RecyclerView.ViewHolder {

        private NetworkImageView imageView;

        public WallpaperHolder(View view) {
            super(view);
            imageView = ((NetworkImageView) view.findViewById(R.id.my_holder_image_view));

        }

    }

}
