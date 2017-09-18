package com.example.cm.testrv;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by cm on 2017/9/15.
 *
 */

public class DeviderItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] attrs = new int[]{
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayout.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayout.VERTICAL;


    private Drawable mDivider;
    private int mOrientation;

    public DeviderItemDecoration(Context context, int orientation) {
        final TypedArray array = context.obtainStyledAttributes(attrs);
        mDivider = array.getDrawable(0);
        array.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        if (mOrientation == HORIZONTAL_LIST) {
            drawHorizontal(c, parent);
        } else {
            drawVertical(c, parent);
        }
    }

    public void drawVertical(Canvas canvas, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView recyclerView = new RecyclerView(parent.getContext());
            final RecyclerView.LayoutParams params = ((RecyclerView.LayoutParams) child.getLayoutParams());
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }



    }

    public void drawHorizontal(Canvas canvas, RecyclerView parent) {

    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        super.getItemOffsets(outRect, itemPosition, parent);
    }
}
