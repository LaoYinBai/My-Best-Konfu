package com.umeng.commonsdk.proguard;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class l {
    private static boolean a;
    private static boolean b;
    private static m c;

    public static void a(Context context) {
        if (c == null) {
            synchronized (l.class) {
                if (c == null) {
                    c = new m();
                }
            }
        }
        b = c.a(context);
        a = true;
    }

    public static boolean a() {
        if (!a) {
            throw new RuntimeException("OpenID SDK Need Init First!");
        }
        return b;
    }

    public static String b(Context context) {
        if (!a) {
            throw new RuntimeException("OpenID SDK Need Init First!");
        }
        return c.a(context, "OUID");
    }
}
