package com.sigmob.sdk.common.utils;

import android.content.Context;
import android.util.TypedValue;
import com.sigmob.sdk.common.utils.m;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    public static float a(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int a(float f, Context context) {
        return (int) (d(f, context) + 0.5f);
    }

    public static int b(float f, Context context) {
        return (int) (e(f, context) + 0.5f);
    }

    public static int b(Context context) {
        m.a.a(context);
        return a(context.getResources().getDisplayMetrics().widthPixels, context);
    }

    public static int c(float f, Context context) {
        return (int) (f(f, context) + 0.5f);
    }

    public static int c(Context context) {
        m.a.a(context);
        return a(context.getResources().getDisplayMetrics().heightPixels, context);
    }

    private static float d(float f, Context context) {
        return f / a(context);
    }

    private static float e(float f, Context context) {
        return f * a(context);
    }

    private static float f(float f, Context context) {
        return TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }
}
