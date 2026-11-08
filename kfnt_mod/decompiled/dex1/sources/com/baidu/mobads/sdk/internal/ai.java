package com.baidu.mobads.sdk.internal;

import android.content.Context;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ai {

    /* renamed from: c, reason: collision with root package name */
    private static volatile Map<String, ai> f3107c = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    private Class<?> f3108a;

    /* renamed from: b, reason: collision with root package name */
    private Method[] f3109b;

    public static ai a(Context context, String str) {
        if (!f3107c.containsKey(str) || f3107c.get(str).f3108a == null) {
            synchronized (ai.class) {
                if (!f3107c.containsKey(str) || f3107c.get(str).f3108a == null) {
                    f3107c.put(str, new ai(context, str));
                }
            }
        }
        return f3107c.get(str);
    }

    private ai(Context context, String str) {
        this.f3109b = null;
        try {
            this.f3108a = Class.forName(str, true, ba.a(context));
            this.f3109b = this.f3108a.getMethods();
        } catch (Throwable th) {
            bb.a().a(th);
        }
    }

    public Object a(Class<?>[] clsArr, Object... objArr) {
        Constructor<?> constructor;
        if (objArr != null) {
            try {
                if (objArr.length == 0) {
                    constructor = this.f3108a.getConstructor(new Class[0]);
                } else {
                    constructor = this.f3108a.getConstructor(clsArr);
                }
            } catch (Throwable th) {
                bb.a().a(th);
                return null;
            }
        } else {
            constructor = this.f3108a.getConstructor(new Class[0]);
        }
        return constructor.newInstance(objArr);
    }

    public void a(Object obj, String str, Object... objArr) {
        try {
            Method methodA = a(str);
            if (methodA != null) {
                if (objArr == null || objArr.length == 0) {
                    methodA.invoke(obj, new Object[0]);
                } else {
                    methodA.invoke(obj, objArr);
                }
            }
        } catch (Throwable th) {
            bb.a().a(th);
        }
    }

    public Object b(Object obj, String str, Object... objArr) {
        try {
            Method methodA = a(str);
            if (methodA == null) {
                return null;
            }
            if (objArr != null && objArr.length != 0) {
                return methodA.invoke(obj, objArr);
            }
            return methodA.invoke(obj, new Object[0]);
        } catch (Throwable th) {
            bb.a().a(th);
            return null;
        }
    }

    public String c(Object obj, String str, Object... objArr) {
        Object objInvoke;
        try {
            Method methodA = a(str);
            if (methodA == null) {
                return null;
            }
            if (objArr == null || objArr.length == 0) {
                objInvoke = methodA.invoke(obj, new Object[0]);
            } else {
                objInvoke = methodA.invoke(obj, objArr);
            }
            if (objInvoke instanceof String) {
                return (String) objInvoke;
            }
            return null;
        } catch (Throwable th) {
            bb.a().a(th);
            return null;
        }
    }

    private Method a(String str) {
        if (this.f3109b == null) {
            return null;
        }
        for (Method method : this.f3109b) {
            if (method.getName().equals(str)) {
                method.setAccessible(true);
                return method;
            }
        }
        return null;
    }
}
