package com.mbridge.msdk.foundation.same.net;

import android.content.Context;

/* compiled from: VolleyManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class n {
    private static n c;
    private j a;
    private com.mbridge.msdk.foundation.same.net.d.b b;

    private n() {
    }

    public static void a(Context context) {
        if (c == null) {
            c = new n();
            c.a = new j(context.getApplicationContext());
            c.b = new com.mbridge.msdk.foundation.same.net.d.b(b(), 3);
        }
    }

    public static void a(i iVar) {
        b().a(iVar);
    }

    private static j b() {
        if (c.a != null) {
            return c.a;
        }
        throw new IllegalStateException("RequestQueue not initialized");
    }

    public static com.mbridge.msdk.foundation.same.net.d.b a() {
        if (c.b != null) {
            return c.b;
        }
        throw new IllegalStateException("FileDownloader not initialized");
    }
}
