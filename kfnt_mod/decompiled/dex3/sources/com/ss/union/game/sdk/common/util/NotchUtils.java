package com.ss.union.game.sdk.common.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.TypedValue;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NotchUtils {
    private static final String a = "android.os.SystemProperties";
    private static final String b = "ro.miui.notch";
    private static final String c = "com.huawei.android.util.HwNotchSizeUtil";
    private static final String d = "android.util.FtFeature";
    private static final String e = "com.oppo.feature.screen.heteromorphism";

    public static boolean hasNotchScreen(Activity activity) {
        return activity != null && (a((Context) activity) || b((Context) activity) || d(activity) || c(activity) || a(activity));
    }

    public static boolean hasNotchScreen(View view) {
        return view != null && (a(view.getContext()) || b(view.getContext()) || d(view.getContext()) || c(view.getContext()) || a(view));
    }

    private static boolean a(View view) {
        return b(view) != null;
    }

    private static boolean a(Activity activity) {
        return b(activity) != null;
    }

    private static DisplayCutout b(Activity activity) {
        Window window;
        WindowInsets rootWindowInsets;
        if (Build.VERSION.SDK_INT < 28 || activity == null || (window = activity.getWindow()) == null || (rootWindowInsets = window.getDecorView().getRootWindowInsets()) == null) {
            return null;
        }
        return rootWindowInsets.getDisplayCutout();
    }

    private static DisplayCutout b(View view) {
        WindowInsets rootWindowInsets;
        if (Build.VERSION.SDK_INT < 28 || view == null || (rootWindowInsets = view.getRootWindowInsets()) == null) {
            return null;
        }
        return rootWindowInsets.getDisplayCutout();
    }

    private static boolean a(Context context) throws ClassNotFoundException {
        int iIntValue;
        if ("Xiaomi".equals(Build.MANUFACTURER)) {
            try {
                Class<?> clsLoadClass = context.getClassLoader().loadClass(a);
                iIntValue = ((Integer) clsLoadClass.getMethod("getInt", String.class, Integer.TYPE).invoke(clsLoadClass, b, 0)).intValue();
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            }
        } else {
            iIntValue = 0;
        }
        return iIntValue == 1;
    }

    private static boolean b(Context context) throws ClassNotFoundException {
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass(c);
            return ((Boolean) clsLoadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(clsLoadClass, new Object[0])).booleanValue();
        } catch (ClassNotFoundException | NoSuchMethodException | Exception unused) {
            return false;
        }
    }

    private static boolean c(Context context) throws ClassNotFoundException {
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass(d);
            return ((Boolean) clsLoadClass.getMethod("isFeatureSupport", Integer.TYPE).invoke(clsLoadClass, 32)).booleanValue();
        } catch (ClassNotFoundException | NoSuchMethodException | Exception unused) {
            return false;
        }
    }

    private static boolean d(Context context) {
        try {
            return context.getPackageManager().hasSystemFeature(e);
        } catch (Exception unused) {
            return false;
        }
    }

    public static int getNotchHeight(Activity activity) {
        int statusBarHeight = StatusBarUtils.getStatusBarHeight();
        DisplayCutout displayCutoutB = b(activity);
        if (Build.VERSION.SDK_INT >= 28 && displayCutoutB != null) {
            if (activity.getResources().getConfiguration().orientation == 1) {
                return displayCutoutB.getSafeInsetTop();
            }
            if (displayCutoutB.getSafeInsetLeft() == 0) {
                return displayCutoutB.getSafeInsetRight();
            }
            return displayCutoutB.getSafeInsetLeft();
        }
        int iE = a((Context) activity) ? e(activity) : 0;
        if (b((Context) activity)) {
            iE = f(activity)[1];
        }
        if (c(activity) && (iE = a(activity, 32)) < statusBarHeight) {
            iE = statusBarHeight;
        }
        if (!d(activity)) {
            return iE;
        }
        if (80 < statusBarHeight) {
            return statusBarHeight;
        }
        return 80;
    }

    private static int e(Context context) {
        int identifier = context.getResources().getIdentifier("notch_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private static int[] f(Context context) throws ClassNotFoundException {
        int[] iArr = {0, 0};
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass(c);
            return (int[]) clsLoadClass.getMethod("getNotchSize", new Class[0]).invoke(clsLoadClass, new Object[0]);
        } catch (ClassNotFoundException unused) {
            return iArr;
        } catch (NoSuchMethodException unused2) {
            return iArr;
        } catch (Exception unused3) {
            return iArr;
        }
    }

    private static int a(Context context, int i) {
        return (int) TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
    }
}
