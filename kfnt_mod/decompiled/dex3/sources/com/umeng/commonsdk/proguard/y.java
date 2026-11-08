package com.umeng.commonsdk.proguard;

import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class y {
    public static x a(Class<? extends x> cls, int i) {
        try {
            return (x) cls.getMethod("findByValue", Integer.TYPE).invoke(null, Integer.valueOf(i));
        } catch (IllegalAccessException unused) {
            return null;
        } catch (NoSuchMethodException unused2) {
            return null;
        } catch (InvocationTargetException unused3) {
            return null;
        }
    }
}
