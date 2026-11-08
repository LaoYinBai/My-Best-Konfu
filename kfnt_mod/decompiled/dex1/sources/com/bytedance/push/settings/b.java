package com.bytedance.push.settings;

import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static final ConcurrentHashMap<Class, Object> f4932a = new ConcurrentHashMap<>();

    public static <T> T a(Class<T> cls, c cVar) {
        Object obj = (T) f4932a.get(cls);
        if (obj == null) {
            synchronized (b.class) {
                obj = f4932a.get(cls);
                if (obj == null) {
                    Object objA = cVar.a(cls);
                    if (objA != null) {
                        f4932a.put(cls, objA);
                    }
                    obj = (T) objA;
                }
            }
        }
        return (T) obj;
    }
}
