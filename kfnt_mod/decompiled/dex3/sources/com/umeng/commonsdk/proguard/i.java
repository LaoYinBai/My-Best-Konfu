package com.umeng.commonsdk.proguard;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class i implements e {
    private static Object a;
    private static Class<?> b;
    private static Method c;

    public i() {
        try {
            b = Class.forName("com.android.id.impl.IdProviderImpl");
            a = b.newInstance();
            c = b.getMethod("getOAID", Context.class);
        } catch (Exception unused) {
        }
    }

    @Override // com.umeng.commonsdk.proguard.e
    public String a(Context context) {
        return a(context, c);
    }

    private static String a(Context context, Method method) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (a == null || method == null) {
            return null;
        }
        try {
            Object objInvoke = method.invoke(a, context);
            if (objInvoke != null) {
                return (String) objInvoke;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }
}
