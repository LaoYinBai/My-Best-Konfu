package com.bytedance.news.common.service.manager;

import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private static final ConcurrentHashMap<Class, Object> f4509a = new ConcurrentHashMap<>();

    /* renamed from: b, reason: collision with root package name */
    private static final ConcurrentHashMap<Class, b<?>> f4510b = new ConcurrentHashMap<>();

    /* renamed from: c, reason: collision with root package name */
    private static final ConcurrentHashMap<Class, d> f4511c = new ConcurrentHashMap<>();

    public static <T> T a(Class<T> cls) {
        T t = (T) f4509a.get(cls);
        if (t == null) {
            synchronized (e.class) {
                b<?> bVar = f4510b.get(cls);
                if (bVar != null) {
                    T t2 = (T) bVar.a();
                    f4510b.remove(cls);
                    if (t2 != null) {
                        c(cls, t2);
                        return t2;
                    }
                }
                T t3 = (T) c.a(cls);
                if (t3 != null) {
                    c(cls, t3);
                    return t3;
                }
                synchronized (cls) {
                    t = (T) b(cls);
                    if (t != null) {
                        c(cls, t);
                        return t;
                    }
                }
            }
        }
        return t;
    }

    public static <T> void a(Class<T> cls, T t) {
        f4509a.put(cls, t);
    }

    public static <T> void a(Class<T> cls, b<T> bVar) {
        f4510b.put(cls, bVar);
    }

    public static <T> void b(Class<T> cls, T t) {
        f4509a.remove(cls, t);
    }

    public static <T> void a(Class<T> cls, d<T> dVar) {
        f4511c.put(cls, dVar);
    }

    private static <T> void c(Class<T> cls, T t) {
        f4509a.put(cls, t);
        d dVar = f4511c.get(cls);
        if (dVar != null) {
            dVar.a(t);
            f4511c.remove(cls);
        }
    }

    private static <T> T b(Class<T> cls) throws IllegalAccessException, InstantiationException {
        try {
            Object objNewInstance = Class.forName(String.format("%s__ServiceProxy", cls.getName())).newInstance();
            if (objNewInstance instanceof IServiceProxy) {
                return (T) ((IServiceProxy) objNewInstance).newInstance();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }
}
