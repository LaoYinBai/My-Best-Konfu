package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import bykvm_19do.bykvm_19do.bykvm_19do.a1;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class l1 implements a1 {

    /* renamed from: a, reason: collision with root package name */
    private static Object f1357a;

    /* renamed from: b, reason: collision with root package name */
    private static Class<?> f1358b;

    /* renamed from: c, reason: collision with root package name */
    private static Method f1359c;

    static {
        try {
            f1358b = Class.forName("com.android.id.impl.IdProviderImpl");
            f1357a = f1358b.newInstance();
            f1359c = f1358b.getMethod("getOAID", Context.class);
        } catch (Exception unused) {
        }
    }

    l1() {
    }

    static boolean a() {
        return (f1358b == null || f1357a == null || f1359c == null) ? false : true;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.a1
    public a1.a b(Context context) {
        try {
            a1.a aVar = new a1.a();
            aVar.f1312a = a(context, f1359c);
            return aVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.a1
    public boolean a(Context context) {
        return a();
    }

    private static String a(Context context, Method method) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object obj = f1357a;
        if (obj == null || method == null) {
            return null;
        }
        try {
            Object objInvoke = method.invoke(obj, context);
            if (objInvoke != null) {
                return (String) objInvoke;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }
}
