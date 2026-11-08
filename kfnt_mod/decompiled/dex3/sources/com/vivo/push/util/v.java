package com.vivo.push.util;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class v extends a {
    private static v b;

    public static synchronized v b() {
        if (b == null) {
            b = new v();
        }
        return b;
    }

    public final synchronized void a(Context context) {
        if (this.a == null) {
            this.a = context;
            a(context, "com.vivo.push_preferences");
        }
    }
}
