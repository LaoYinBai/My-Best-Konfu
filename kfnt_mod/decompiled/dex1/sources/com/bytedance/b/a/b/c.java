package com.bytedance.b.a.b;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    public static final String f3655a = "UTF-8";

    /* renamed from: b, reason: collision with root package name */
    private static b f3656b = new a();

    /* renamed from: c, reason: collision with root package name */
    private static boolean f3657c;

    public static class a implements b {
        @Override // com.bytedance.b.a.b.c.b
        public boolean a(Context context) {
            return false;
        }
    }

    public interface b {
        boolean a(Context context);
    }

    public static void a(b bVar) {
        if (f3657c) {
            return;
        }
        if (bVar != null) {
            f3656b = bVar;
        }
        f3657c = true;
    }

    static boolean a(Context context) {
        return f3656b.a(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] a(java.lang.String r1) {
        /*
            if (r1 == 0) goto L14
            int r0 = r1.length()     // Catch: java.io.UnsupportedEncodingException -> Lf
            if (r0 <= 0) goto L14
            java.lang.String r0 = "UTF-8"
            byte[] r0 = r1.getBytes(r0)     // Catch: java.io.UnsupportedEncodingException -> Lf
            goto L15
        Lf:
            byte[] r1 = r1.getBytes()
            goto L16
        L14:
            r0 = 0
        L15:
            r1 = r0
        L16:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.b.a.b.c.a(java.lang.String):byte[]");
    }
}
