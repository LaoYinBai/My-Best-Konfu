package com.huawei.a.a.b.h;

import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    private static a a;
    private volatile Map<String, b> b = new HashMap();

    private a() {
    }

    public static a a() {
        if (a == null) {
            b();
        }
        return a;
    }

    private b b(String str) {
        if (!this.b.containsKey(str)) {
            this.b.put(str, new b());
        }
        return this.b.get(str);
    }

    private static synchronized void b() {
        if (a == null) {
            a = new a();
        }
    }

    public b a(String str, long j) {
        b bVarB = b(str);
        bVarB.a(j);
        return bVarB;
    }

    public void a(String str) {
        b(str).c();
    }
}
