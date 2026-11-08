package com.bytedance.msdk.adapter.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.Nullable;
import anet.channel.strategy.dispatch.DispatchConstants;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.t;
import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class UIUtils {
    public static final int VIVO_FILLET = 8;
    public static final int VIVO_NOTCH = 32;

    /* renamed from: a, reason: collision with root package name */
    private static float f4251a = -1.0f;

    /* renamed from: b, reason: collision with root package name */
    private static int f4252b = -1;

    /* renamed from: c, reason: collision with root package name */
    private static float f4253c = -1.0f;
    private static int d = -1;
    private static int e = -1;

    static {
        init(a.d());
    }

    private static boolean a() {
        return f4251a < 0.0f || f4252b < 0 || f4253c < 0.0f || d < 0 || e < 0;
    }

    private static boolean a(int i) {
        return i == 0 || i == 8 || i == 4;
    }

    @SuppressLint({"ObsoleteSdkInt"})
    public static boolean canShow(Context context, View view, int i) {
        if (view == null) {
            Logger.d("adView is null.");
            return false;
        }
        if (view.getParent() == null) {
            Logger.d("adView has no parent.");
            return false;
        }
        if (view.getWindowVisibility() != 0) {
            Logger.d("adView window is not set to VISIBLE.");
            return false;
        }
        if (view.getVisibility() != 0) {
            Logger.d("adView is not set to VISIBLE.");
            return false;
        }
        if (view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0) {
            Logger.d("adView has invisible dimensions (w=" + view.getMeasuredWidth() + ", h=" + view.getMeasuredHeight());
        } else {
            if (Build.VERSION.SDK_INT >= 11 && view.getAlpha() < 0.9f) {
                Logger.d("adView is transparent.");
                return false;
            }
            int width = view.getWidth();
            int height = view.getHeight();
            int[] iArr = new int[2];
            try {
                view.getLocationOnScreen(iArr);
                int screenWidth = getScreenWidth(context);
                int screenHeight = getScreenHeight(context);
                if (iArr[0] >= 0 && screenWidth - iArr[0] >= width) {
                    int i2 = (int) ((height * (100.0d - i)) / 100.0d);
                    if (iArr[1] < 0 && Math.abs(iArr[1]) > i2) {
                        Logger.d("adView is not visible from the top.");
                        return false;
                    }
                    if ((height + iArr[1]) - screenHeight > i2) {
                        Logger.d("adView is not visible from the bottom.");
                        return false;
                    }
                    Logger.d("adView is visible.");
                    return isScreenOnAndNotInKeygaurdInputMode(context);
                }
                Logger.d("adView is not fully on screen horizontally.");
            } catch (Exception unused) {
                Logger.d("Cannot get location on screen.");
                return false;
            }
        }
        return false;
    }

    public static float dip2Px(Context context, float f) {
        init(context);
        return (f * getDensity(context)) + 0.5f;
    }

    public static int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    @Nullable
    public static int[] getCoordinate(View view) {
        if (view == null || view.getVisibility() != 0) {
            return null;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr;
    }

    public static float getDensity(Context context) {
        init(context);
        return f4251a;
    }

    public static int getDensityDpi(Context context) {
        init(context);
        return f4252b;
    }

    public static int getInt(String str, Activity activity) throws ClassNotFoundException {
        if (t.t()) {
            try {
                Class<?> clsLoadClass = activity.getClassLoader().loadClass("android.os.SystemProperties");
                return ((Integer) clsLoadClass.getMethod("getInt", String.class, Integer.TYPE).invoke(clsLoadClass, new String(str), new Integer(0))).intValue();
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            } catch (IllegalAccessException e3) {
                e3.printStackTrace();
            } catch (IllegalArgumentException e4) {
                e4.printStackTrace();
            } catch (NoSuchMethodException e5) {
                e5.printStackTrace();
            } catch (InvocationTargetException e6) {
                e6.printStackTrace();
            }
        }
        return 0;
    }

    public static int getRealHeight(Context context) {
        if (context == null) {
            context = a.d();
        }
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealMetrics(displayMetrics);
        } else {
            defaultDisplay.getMetrics(displayMetrics);
        }
        return displayMetrics.heightPixels;
    }

    public static int getRealWidth(Context context) {
        if (context == null) {
            context = a.d();
        }
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealMetrics(displayMetrics);
        } else {
            defaultDisplay.getMetrics(displayMetrics);
        }
        return displayMetrics.widthPixels;
    }

    public static float getScaledDensity(Context context) {
        init(context);
        return f4253c;
    }

    public static int getScreenHeight(Context context) {
        init(context);
        return e;
    }

    public static Bitmap getScreenShot(View view) {
        if (view == null) {
            return null;
        }
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

    public static int getScreenWidth(Context context) {
        init(context);
        return d;
    }

    public static float getStatusBarHeight(Context context) {
        if (context == null) {
            context = a.d();
        }
        if (context.getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", DispatchConstants.ANDROID) > 0) {
            return context.getApplicationContext().getResources().getDimensionPixelSize(r1);
        }
        return 0.0f;
    }

    @Nullable
    public static int[] getViewSize(View view) {
        if (view != null) {
            return new int[]{view.getWidth(), view.getHeight()};
        }
        return null;
    }

    public static boolean hasNotchAtHuawei(Context context) {
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            return ((Boolean) clsLoadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(clsLoadClass, new Object[0])).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean hasNotchAtOPPO(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    public static boolean hasNotchAtVivo(Context context) {
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.util.FtFeature");
            return ((Boolean) clsLoadClass.getMethod("isFeatureSupport", Integer.TYPE).invoke(clsLoadClass, 32)).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean hasNotchScreen(Activity activity) {
        return getInt("ro.miui.notch", activity) == 1 || hasNotchAtHuawei(activity) || hasNotchAtOPPO(activity) || hasNotchAtVivo(activity) || isAndroidPHasNotch(activity);
    }

    public static void hideBottomUIMenu(Activity activity) {
        if (activity == null) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
                activity.getWindow().getDecorView().setSystemUiVisibility(8);
            } else if (Build.VERSION.SDK_INT >= 19) {
                activity.getWindow().getDecorView().setSystemUiVisibility(3842);
                activity.getWindow().addFlags(134217728);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void init(Context context) {
        Context contextD = context == null ? a.d() : context;
        if (contextD == null) {
            return;
        }
        if (a()) {
            DisplayMetrics displayMetrics = contextD.getResources().getDisplayMetrics();
            f4251a = displayMetrics.density;
            f4252b = displayMetrics.densityDpi;
            f4253c = displayMetrics.scaledDensity;
            d = displayMetrics.widthPixels;
            e = displayMetrics.heightPixels;
        }
        if (context == null || context.getResources() == null || context.getResources().getConfiguration() == null) {
            return;
        }
        if (context.getResources().getConfiguration().orientation == 1) {
            int i = d;
            int i2 = e;
            if (i > i2) {
                d = i2;
                e = i;
                return;
            }
            return;
        }
        int i3 = d;
        int i4 = e;
        if (i3 < i4) {
            d = i4;
            e = i3;
        }
    }

    public static boolean isAndroidPHasNotch(Activity activity) throws ClassNotFoundException {
        if (Build.VERSION.SDK_INT < 28) {
            return false;
        }
        try {
            Class<?> cls = Class.forName("android.view.WindowInsets");
            return cls.getMethod("getDisplayCutout", new Class[0]).invoke(cls, new Object[0]) != null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean isScreenOnAndNotInKeygaurdInputMode(Context context) {
        KeyguardManager keyguardManager;
        try {
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            if (powerManager == null || !powerManager.isScreenOn() || (keyguardManager = (KeyguardManager) context.getSystemService("keyguard")) == null) {
                return false;
            }
            return !keyguardManager.inKeyguardRestrictedInputMode();
        } catch (Exception e2) {
            Logger.d("adView exception:" + e2.getMessage());
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean isViewVisible(View view) {
        return view != null && view.getVisibility() == 0;
    }

    public static int px2dip(Context context, float f) {
        init(context);
        float density = getDensity(context);
        if (density <= 0.0f) {
            density = 1.0f;
        }
        return (int) ((f / density) + 0.5f);
    }

    public static int px2sp(Context context, float f) {
        init(context);
        float scaledDensity = getScaledDensity(context);
        if (scaledDensity <= 0.0f) {
            scaledDensity = 1.0f;
        }
        return (int) ((f / scaledDensity) + 0.5f);
    }

    public static void setClickListener(boolean z, View view, View.OnClickListener onClickListener) {
        if (view == null) {
            return;
        }
        if (z) {
            view.setOnClickListener(onClickListener);
            view.setClickable(true);
        } else {
            view.setOnClickListener(null);
            view.setClickable(false);
        }
    }

    public static void setOnClickListener(View view, View.OnClickListener onClickListener, String str) {
        if (view != null) {
            view.setOnClickListener(onClickListener);
            return;
        }
        Logger.e("OnclickListener ", str + " is null , can not set OnClickListener !!!");
    }

    public static void setOnTouchListener(View view, View.OnTouchListener onTouchListener, String str) {
        if (view != null) {
            view.setOnTouchListener(onTouchListener);
            return;
        }
        Logger.e("OnTouchListener ", str + " is null , can not set OnTouchListener !!!");
    }

    public static void setText(TextView textView, CharSequence charSequence) {
        if (textView == null || TextUtils.isEmpty(charSequence)) {
            return;
        }
        textView.setText(charSequence);
    }

    public static void setViewVisibility(View view, int i) {
        if (view == null || view.getVisibility() == i || !a(i)) {
            return;
        }
        view.setVisibility(i);
    }

    public static float sp2px(Context context, float f) {
        init(context);
        return TypedValue.applyDimension(2, f, context.getResources().getDisplayMetrics());
    }

    public static void updateLayoutMargin(View view, int i, int i2, int i3, int i4) {
        ViewGroup.LayoutParams layoutParams;
        if (view == null || (layoutParams = view.getLayoutParams()) == null || !(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        a(view, (ViewGroup.MarginLayoutParams) layoutParams, i, i2, i3, i4);
    }

    public int getVirtualBarHeight(Context context) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (context == null) {
            context = a.d();
        }
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke(defaultDisplay, displayMetrics);
            return displayMetrics.heightPixels - defaultDisplay.getHeight();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    private static void a(View view, ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2, int i3, int i4) {
        if (view == null || marginLayoutParams == null) {
            return;
        }
        if (marginLayoutParams.leftMargin == i && marginLayoutParams.topMargin == i2 && marginLayoutParams.rightMargin == i3 && marginLayoutParams.bottomMargin == i4) {
            return;
        }
        if (i != -3) {
            marginLayoutParams.leftMargin = i;
        }
        if (i2 != -3) {
            marginLayoutParams.topMargin = i2;
        }
        if (i3 != -3) {
            marginLayoutParams.rightMargin = i3;
        }
        if (i4 != -3) {
            marginLayoutParams.bottomMargin = i4;
        }
        view.setLayoutParams(marginLayoutParams);
    }
}
