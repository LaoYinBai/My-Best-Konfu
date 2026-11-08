package com.ss.sys.ces;

import android.content.Context;
import c.a.a.a.a.bg;
import c.a.a.a.a.bm;
import c.a.a.a.a.bn;
import c.a.a.a.a.f;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    public static int a = 0;
    public static long b = 3600;
    public static long c;

    private static void a(Context context) {
        try {
            new f(context, b.h).b();
        } catch (Throwable unused) {
            new bn(context, b.h).a(2, 2, (byte[]) null);
        }
    }

    public static synchronized void b(Context context) {
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        if (jCurrentTimeMillis - c >= b) {
            int i = 0;
            b.l = false;
            a = 0;
            c = jCurrentTimeMillis;
            while (i < 3) {
                bg.c("SETTINGS loop=" + i + "\tsettings=" + b.l + "\tretry_time=" + a);
                if (!b.l && a < 3) {
                    a(context);
                    a++;
                }
                i++;
                bm.a(i * 5000);
                if (b.l) {
                    break;
                }
            }
        }
    }
}
