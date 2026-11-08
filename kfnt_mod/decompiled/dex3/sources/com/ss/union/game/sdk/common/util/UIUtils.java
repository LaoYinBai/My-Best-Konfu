package com.ss.union.game.sdk.common.util;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.TouchDelegate;
import android.view.View;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UIUtils {
    private static String a = "";

    private UIUtils() {
    }

    public static int dip2Px(float f) {
        Context context = GlobalApplicationUtils.getContext();
        return context == null ? (int) f : (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(float f) {
        Context context = GlobalApplicationUtils.getContext();
        return context == null ? (int) f : (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static float sp2px(float f) {
        Context context = GlobalApplicationUtils.getContext();
        return context == null ? f : TypedValue.applyDimension(2, f, context.getResources().getDisplayMetrics());
    }

    public static int getScreenWidth() {
        DisplayMetrics displayMetrics;
        Context context = GlobalApplicationUtils.getContext();
        if (context == null || (displayMetrics = context.getResources().getDisplayMetrics()) == null) {
            return 0;
        }
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight() {
        DisplayMetrics displayMetrics;
        Context context = GlobalApplicationUtils.getContext();
        if (context == null || (displayMetrics = context.getResources().getDisplayMetrics()) == null) {
            return 0;
        }
        return displayMetrics.heightPixels;
    }

    public static String getScreenResolution() {
        if (TextUtils.isEmpty(a)) {
            int screenWidth = getScreenWidth();
            int screenHeight = getScreenHeight();
            if (screenWidth > 0 && screenHeight > 0) {
                a = screenHeight + "*" + screenWidth;
            }
        }
        return a;
    }

    public static int getDisplayDensityDpi() {
        Context context = GlobalApplicationUtils.getContext();
        if (context == null) {
            return 0;
        }
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    public static String getDisplayDensity() {
        int displayDensityDpi = getDisplayDensityDpi();
        return displayDensityDpi != 120 ? displayDensityDpi != 160 ? displayDensityDpi != 240 ? displayDensityDpi != 320 ? displayDensityDpi != 480 ? displayDensityDpi != 640 ? "mdpi" : "xxxhdpi" : "xxhdpi" : "xhdpi" : "hdpi" : "mdpi" : "ldpi";
    }

    public static boolean isHorizontalScreen() {
        return getScreenWidth() > getScreenHeight();
    }

    public static boolean isVerticalScreen() {
        return getScreenWidth() < getScreenHeight();
    }

    public static void expandClickRegion(View view, int i) {
        float f = i;
        expandClickRegion(view, -dip2Px(f), -dip2Px(f), dip2Px(f), dip2Px(f));
    }

    public static void expandClickRegion(final View view, final int i, final int i2, final int i3, final int i4) {
        view.post(new Runnable() { // from class: com.ss.union.game.sdk.common.util.UIUtils.1
            @Override // java.lang.Runnable
            public void run() {
                Rect rect = new Rect();
                view.getHitRect(rect);
                rect.top += i2;
                rect.bottom += i4;
                rect.left += i;
                rect.right += i3;
                TouchDelegate touchDelegate = new TouchDelegate(rect, view);
                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });
    }
}
