package com.bytedance.push.settings.storage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class f {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, e> f4960a = new ConcurrentHashMap();

    static {
        a(new a());
    }

    public static e a(String str) {
        return f4960a.get(str);
    }

    public static void a(e eVar) {
        f4960a.put(eVar.a(), eVar);
    }
}
