package com.huawei.a.a.b.c.a;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    static Map<String, e> a = new HashMap();
    private static a c;
    private d b = new d();

    private a() {
    }

    public static a a() {
        if (c == null) {
            d();
        }
        return c;
    }

    private static synchronized void d() {
        if (c == null) {
            c = new a();
        }
    }

    public e a(String str) {
        return a.get(str);
    }

    public void a(String str, e eVar) {
        a.put(str, eVar);
    }

    public Set<String> b() {
        return a.keySet();
    }

    public d c() {
        return this.b;
    }
}
