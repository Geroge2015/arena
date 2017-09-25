package com.example.cm.testrv.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.cm.testrv.MyApplication;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by George on 2017/9/25.
 *
 */


public class DimenUtils {

    public static final int DENSITY_LOW = 120;
    public static final int DENSITY_MEDIUM = 160;
    public static final int DENSITY_HIGH = 240;
    public static final int DENSITY_XHIGH = 320;
    public final static float BASE_SCREEN_HEIGHT = 1280f;
    public final static float BASE_SCREEN_DENSITY = 2f;
    public static Float sScaleW, sScaleH;
    private static float mScreenSize;
    private static Class sClass = null;
    public static float sDensity = 1.0f;
    public static int sDensityDpi;
    public static int sWidthPixels;
    public static int sHeightPixels;
    public static float sFontDensity;
    public static int sRealWidthPixels;
    public static int sRealHeightPixels;
    public static int sNavBarWidth; // 虚拟键宽度
    public static int sNavBarHeight; // 虚拟键高度
    public static final int NAVBAR_LOCATION_RIGHT = 1;
    public static final int NAVBAR_LOCATION_BOTTOM = 2;

    public static int sStatusHeight;
    public static final DisplayMetrics mMetrics = MyApplication.getAppContext().getResources()
            .getDisplayMetrics();

    private static final int DP_TO_PX = TypedValue.COMPLEX_UNIT_DIP;
    private static final int SP_TO_PX = TypedValue.COMPLEX_UNIT_SP;
    private static final int PX_TO_DP = TypedValue.COMPLEX_UNIT_MM + 1;
    private static final int PX_TO_SP = TypedValue.COMPLEX_UNIT_MM + 2;
    private static final int DP_TO_PX_SCALE_H = TypedValue.COMPLEX_UNIT_MM + 3;

    public static final int LDPI = 1;
    public static final int MDPI = 2;
    public static final int HDPI = 3;
    public static final int XHDPI = 4;
    public static final int XXHDPI = 5;
    public static final int XXXHDPI = 6;
    public static final int OTHER_DPI = 7;
    public static final int NULL_DPI = 0;
    // -- dimens convert

    private static float applyDimension(int unit, float value, DisplayMetrics metrics) {
        switch (unit) {
            case DP_TO_PX:
            case SP_TO_PX:
                return TypedValue.applyDimension(unit, value, metrics);
            case PX_TO_DP:
                return value / metrics.density;
            case PX_TO_SP:
                return value / metrics.scaledDensity;
            case DP_TO_PX_SCALE_H:
                return TypedValue.applyDimension(DP_TO_PX, value * getScaleFactorH(), metrics);
        }
        return 0;
    }

    public static int dp2px(float value) {
        return (int) applyDimension(DP_TO_PX, value, mMetrics);
    }

    public static int sp2px(float value) {
        return (int) applyDimension(SP_TO_PX, value, mMetrics);
    }

    public static int px2dp(float value) {
        return (int) applyDimension(PX_TO_DP, value, mMetrics);
    }

    public static int px2sp(float value) {
        return (int) applyDimension(PX_TO_SP, value, mMetrics);
    }

    public static int dp2pxScaleH(float value) {
        return (int) applyDimension(DP_TO_PX_SCALE_H, value, mMetrics);
    }

    public static float getScaleFactorH() {
        if (sScaleH == null) {
            sScaleH = (mMetrics.heightPixels * BASE_SCREEN_DENSITY)
                    / (mMetrics.density * BASE_SCREEN_HEIGHT);
        }
        return sScaleH;
    }
    // -- update layout

    public static void createLayout(View view, int w, int h) {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (w != -3)
            params.width = w;
        if (h != -3)
            params.height = h;
        view.setLayoutParams(params);
    }

    public static void updateLayout(View view, int w, int h) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null)
            return;
        if (w != -3)
            params.width = w;
        if (h != -3)
            params.height = h;
        view.setLayoutParams(params);
    }

    public static void updateLayoutMargin(View view, int l, int t, int r, int b) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null)
            return;
        if (params instanceof RelativeLayout.LayoutParams) {
            updateMargin(view, (RelativeLayout.LayoutParams) params, l, t, r, b);
        } else if (params instanceof LinearLayout.LayoutParams) {
            updateMargin(view, (LinearLayout.LayoutParams) params, l, t, r, b);
        } else if (params instanceof FrameLayout.LayoutParams) {
            updateMargin(view, (FrameLayout.LayoutParams) params, l, t, r, b);
        }
    }

    private static void updateMargin(View view, ViewGroup.MarginLayoutParams params, int l, int t,
                                     int r, int b) {
        if (l != -3)
            params.leftMargin = l;
        if (t != -3)
            params.topMargin = t;
        if (r != -3)
            params.rightMargin = r;
        if (b != -3)
            params.bottomMargin = b;
        view.setLayoutParams(params);
    }

    public static void createListviewLayout(View view, int w, int h) {
        ListView.LayoutParams lp = (ListView.LayoutParams) view.getLayoutParams();
        if (lp == null) {
            if (w == -3)
                w = ListView.LayoutParams.MATCH_PARENT;
            if (h == -3)
                h = ListView.LayoutParams.MATCH_PARENT;
            lp = new ListView.LayoutParams(w, h);
            view.setLayoutParams(lp);
        }
    }

    public static void updateRelativeLeftToRight(View leftview, int leftMargin, View rightview,
                                                 int rightMargin, int between) {
        RelativeLayout.LayoutParams lpLeft = (RelativeLayout.LayoutParams) leftview
                .getLayoutParams();
        lpLeft.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lpLeft.leftMargin = leftMargin;
        lpLeft.rightMargin = between;
        // clear before
        lpLeft.addRule(RelativeLayout.RIGHT_OF, -1);
        leftview.setLayoutParams(lpLeft);

        RelativeLayout.LayoutParams lpRight = (RelativeLayout.LayoutParams) rightview
                .getLayoutParams();
        lpRight.addRule(RelativeLayout.RIGHT_OF, leftview.getId());
        lpRight.rightMargin = rightMargin;
        // clear before
        lpRight.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
        lpRight.leftMargin = 0;
        rightview.setLayoutParams(lpRight);
    }

    // -- window dimens

    public static boolean isLowDensity() {
        float densityDpi = mMetrics.densityDpi;
        if (densityDpi == DENSITY_LOW || densityDpi == DENSITY_MEDIUM) {
            // Log.d("show", "low density");
            return true;
        }
        return false;
    }

    public static int getStatusBarHeight(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    private static int sStatusBarHeight = -1;

    public static int getStatusBarHeight2() {
        if (sStatusBarHeight != -1) {
            return sStatusBarHeight;
        }

        try {
            Class<?> cl = Class.forName("com.android.internal.R$dimen");
            Object obj = cl.newInstance();
            Field field = cl.getField("status_bar_height");

            int x = Integer.parseInt(field.get(obj).toString());
            sStatusBarHeight = MyApplication.getAppContext().getResources()
                    .getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sStatusBarHeight;
    }

    public static int getWindowWidth() {
        return mMetrics.widthPixels;
    }

    public static int getWindowHeight() {
        return mMetrics.heightPixels;
    }

    public static int getContentHeight(Activity activity) {
        int height = mMetrics.heightPixels - getStatusBarHeight(activity);
        return height;
    }

    public static int getContentHeight2() {
        int height = mMetrics.heightPixels - getStatusBarHeight2();
        return height;
    }

    public static void showAll() {
        String text = String.format("%s * %s, densityDpi = %s, density = %s, scaledDensity = %s",
                mMetrics.widthPixels, mMetrics.heightPixels, mMetrics.densityDpi, mMetrics.density,
                mMetrics.scaledDensity);
        Toast.makeText(MyApplication.getAppContext(), text, Toast.LENGTH_LONG).show();
    }

    /**
     * 是否分辨率小于320
     *
     * @return
     */
    public static boolean isBelow320() {
        return getWindowWidth() < 320;
    }

    /**
     * 获取屏幕尺寸
     *
     * @return
     */
    public static float getScreenSize() {
        if (mMetrics.xdpi != 0 && mMetrics.ydpi != 0) {
            double x = Math.pow(getWindowWidth() / mMetrics.xdpi, 2);
            double y = Math.pow(getWindowHeight() / mMetrics.ydpi, 2);
            mScreenSize = (float) (Math.round(Math.sqrt(x + y) * 10) / 10.0);
        }
        return mScreenSize;
    }


    public static int getScreenType() {

        if (mMetrics.densityDpi > 240 && mMetrics.densityDpi <= 320) {
            return XHDPI;

        } else if (mMetrics.densityDpi > 320 && mMetrics.densityDpi <= 480) {
            return XXHDPI;

        } else if (mMetrics.densityDpi > 480 && mMetrics.densityDpi <= 640) {
            return XXXHDPI;

        } else if (mMetrics.densityDpi > 160 && mMetrics.densityDpi <= 240) {
            return HDPI;

        } else if (mMetrics.densityDpi > 120 && mMetrics.densityDpi <= 160) {
            return MDPI;

        } else if (mMetrics.densityDpi <= 120 && mMetrics.densityDpi > 0) {
            return LDPI;

        } else if (mMetrics.densityDpi > 640) {
            return XXXHDPI;

        } else if (mMetrics.densityDpi <= 0) {
            return OTHER_DPI;
        }

        return NULL_DPI;

    }

    public static void resetDensity(Context context) {
        if (context != null && null != context.getResources()) {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            sDensity = metrics.density;
            sFontDensity = metrics.scaledDensity;
            sWidthPixels = metrics.widthPixels;
            sHeightPixels = metrics.heightPixels;
            sDensityDpi = metrics.densityDpi;
            try {
                getStatusBarHeight(context);
            } catch (Error e) {
                Log.i("DrawUtils", "resetDensity has error" + e.getMessage());
            }
        }
        resetNavBarHeight(context);
    }

    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        int top = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            top = context.getResources().getDimensionPixelSize(x);
            sStatusHeight = top;
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return top;
    }

    private static void resetNavBarHeight(Context context) {
        if (context != null) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            try {
                if (sClass == null) {
                    sClass = Class.forName("android.view.Display");
                }
                Point realSize = new Point();
                Method method = sClass.getMethod("getRealSize", Point.class);
                method.invoke(display, realSize);
                sRealWidthPixels = realSize.x;
                sRealHeightPixels = realSize.y;
                sNavBarWidth = realSize.x - sWidthPixels;
                sNavBarHeight = realSize.y - sHeightPixels;
            } catch (Exception e) {
                sRealWidthPixels = sWidthPixels;
                sRealHeightPixels = sHeightPixels;
                sNavBarHeight = 0;
            }
        }
    }

    public static int getRealWidth() {
        if (sWidthPixels <= 0) {
            resetDensity(MyApplication.getAppContext());
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return sRealWidthPixels;
        }
        return sWidthPixels;
    }

    public static int getRealHeight() {
        if (sHeightPixels <= 0) {
            resetDensity(MyApplication.getAppContext());
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return sRealHeightPixels;
        }
        return sHeightPixels;
    }

    public static Rect getScreenSizeRect() {
        Rect rect = new Rect(0, 0, mMetrics.widthPixels, mMetrics.heightPixels);
        return rect;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScanScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metric = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }
}
