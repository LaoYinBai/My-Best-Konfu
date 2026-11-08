package com.bytedance.pangle.util;

import android.os.Build;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class g {
    public static boolean a() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 23;
    }

    public static boolean c() {
        return Build.VERSION.SDK_INT > 23;
    }

    public static boolean d() {
        if (Build.VERSION.SDK_INT < 28) {
            return Build.VERSION.SDK_INT == 27 && Build.VERSION.PREVIEW_SDK_INT > 0;
        }
        return true;
    }

    public static boolean e() {
        return Build.VERSION.SDK_INT == 29;
    }
}
