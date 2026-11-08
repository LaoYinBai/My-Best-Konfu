package com.bytedance.common.push.utility;

import android.content.Context;
import android.os.Build;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class j {

    /* renamed from: a, reason: collision with root package name */
    public static final boolean f3848a;

    /* renamed from: b, reason: collision with root package name */
    public static a f3849b;

    /* renamed from: c, reason: collision with root package name */
    private static String f3850c;
    private static int d;

    public static class a {
    }

    static {
        f3848a = Build.VERSION.SDK_INT > 19;
        f3850c = "";
        d = -1;
        f3849b = new a();
    }

    public static int a(Context context) {
        if (d == -1 && context != null) {
            d = context.getApplicationContext().getResources().getDisplayMetrics().densityDpi;
        }
        return d;
    }
}
