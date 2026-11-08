package com.bytedance.b.a.c;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    private static String f3689a;

    /* renamed from: b, reason: collision with root package name */
    private static String f3690b;

    public static String a(Context context) {
        if (f3690b == null) {
            f3690b = com.bytedance.b.a.a.e.f.a(context).replace(context.getPackageName(), "p").replace(":", "_");
            f3690b = f3690b.replace(".", "_");
        }
        return f3690b;
    }

    public static String b(Context context) {
        if (f3689a == null) {
            f3689a = com.bytedance.b.a.a.e.f.a(context);
        }
        return f3689a;
    }
}
