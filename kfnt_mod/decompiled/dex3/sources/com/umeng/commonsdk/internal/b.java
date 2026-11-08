package com.umeng.commonsdk.internal;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    private static b b;
    private Context a;
    private c c;

    private b(Context context) {
        this.a = context;
        this.c = new c(context);
    }

    public static synchronized b a(Context context) {
        if (b == null) {
            b = new b(context.getApplicationContext());
        }
        return b;
    }

    public c a() {
        return this.c;
    }
}
