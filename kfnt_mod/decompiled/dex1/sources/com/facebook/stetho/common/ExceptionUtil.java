package com.facebook.stetho.common;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ExceptionUtil {
    public static <T extends Throwable> void propagateIfInstanceOf(Throwable th, Class<T> cls) throws Throwable {
        if (cls.isInstance(th)) {
            throw th;
        }
    }

    public static RuntimeException propagate(Throwable th) throws Throwable {
        propagateIfInstanceOf(th, Error.class);
        propagateIfInstanceOf(th, RuntimeException.class);
        throw new RuntimeException(th);
    }

    public static <T extends Throwable> void sneakyThrow(Throwable th) throws Throwable {
        throw th;
    }
}
