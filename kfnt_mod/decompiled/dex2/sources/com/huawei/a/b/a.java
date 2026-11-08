package com.huawei.a.b;

import android.content.Context;
import com.huawei.a.a.b.k.g;
import java.util.LinkedHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a {
    private static com.huawei.a.a.f.c a;

    public static void a() {
        if (c() == null || !g.a().b()) {
            return;
        }
        a.a(-1);
    }

    public static void a(int i, String str, LinkedHashMap<String, String> linkedHashMap) {
        if (c() == null || !g.a().b()) {
            return;
        }
        if (i == 1 || i == 0) {
            a.a(i, str, linkedHashMap);
            return;
        }
        com.huawei.a.a.b.e.a.b("hmsSdk", "Data type no longer collects range.type: " + i);
    }

    @Deprecated
    public static void a(Context context, String str, String str2) {
        if (c() != null) {
            a.a(context, str, str2);
        }
    }

    public static boolean b() {
        return c.a().c();
    }

    private static synchronized com.huawei.a.a.f.c c() {
        if (a == null) {
            a = c.a().b();
        }
        return a;
    }
}
