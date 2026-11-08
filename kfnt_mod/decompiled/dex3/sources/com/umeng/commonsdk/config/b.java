package com.umeng.commonsdk.config;

import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b implements f {
    private static d a = new d();
    private static Map<String, Boolean> b = new HashMap();
    private static Object c = new Object();

    private b() {
    }

    private static class a {
        private static final b a = new b();

        private a() {
        }
    }

    public static b a() {
        return a.a;
    }

    public static boolean a(String str) {
        if (!d.a(str)) {
            return false;
        }
        synchronized (c) {
            if (!b.containsKey(str)) {
                return true;
            }
            return b.get(str).booleanValue();
        }
    }

    @Override // com.umeng.commonsdk.config.f
    public void a(String str, Boolean bool) {
        if (d.a(str)) {
            synchronized (c) {
                if (b != null) {
                    b.put(str, bool);
                }
            }
        }
    }
}
