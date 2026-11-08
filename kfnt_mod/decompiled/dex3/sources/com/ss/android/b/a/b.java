package com.ss.android.b.a;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    private static Map<Class, a> a = new ConcurrentHashMap();

    public static <T extends a> void a(Class<T> cls, a aVar) {
        if (aVar != null) {
            a.put(cls, aVar);
        }
    }

    public static <T extends a> T a(Class<T> cls) {
        return (T) a.get(cls);
    }
}
