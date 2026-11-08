package com.baidu.mobads.sdk.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import anet.channel.strategy.dispatch.DispatchConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ak {

    /* renamed from: a, reason: collision with root package name */
    private static DisplayMetrics f3112a;

    /* renamed from: b, reason: collision with root package name */
    private static float f3113b;

    public static Rect a(Context context) {
        DisplayMetrics displayMetricsF = f(context);
        try {
            if (displayMetricsF.widthPixels > displayMetricsF.heightPixels) {
                return new Rect(0, 0, displayMetricsF.heightPixels, displayMetricsF.widthPixels);
            }
            return new Rect(0, 0, displayMetricsF.widthPixels, displayMetricsF.heightPixels);
        } catch (Exception unused) {
            return null;
        }
    }

    public static int b(Context context) {
        return a(context).width();
    }

    public static int c(Context context) {
        return a(context).height();
    }

    public static Rect d(Context context) {
        DisplayMetrics displayMetricsF = f(context);
        return new Rect(0, 0, displayMetricsF.widthPixels, displayMetricsF.heightPixels);
    }

    public static float e(Context context) {
        if (f3113b < 0.01d) {
            f3113b = f(context).density;
        }
        return f3113b;
    }

    public static DisplayMetrics f(Context context) {
        if (f3112a == null) {
            f3112a = new DisplayMetrics();
        }
        try {
            if (av.a(context).a() >= 17) {
                ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealMetrics(f3112a);
            } else {
                ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(f3112a);
            }
        } catch (Throwable th) {
            bb.a().a(th);
        }
        return f3112a;
    }

    public static int a(Context context, int i) {
        try {
            return (int) (i / e(context));
        } catch (Exception unused) {
            return i;
        }
    }

    public static int b(Context context, int i) {
        try {
            return (int) (i * e(context));
        } catch (Exception unused) {
            return i;
        }
    }

    public static int g(Context context) {
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", DispatchConstants.ANDROID));
    }

    public static int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int b(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
