package com.ss.android.push.daemon;

import android.content.Context;
import android.os.Build;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface e {
    void a();

    void a(Context context, b bVar);

    void b(Context context, b bVar);

    public static class a {
        private static e a;

        static e a() {
            if (a != null) {
                return a;
            }
            int i = Build.VERSION.SDK_INT;
            if (i >= 26) {
                a = new com.ss.android.push.daemon.a.a();
            } else if (i >= 23) {
                a = new com.ss.android.push.daemon.a.c();
            } else {
                a = new com.ss.android.push.daemon.a.b();
            }
            return a;
        }
    }
}
