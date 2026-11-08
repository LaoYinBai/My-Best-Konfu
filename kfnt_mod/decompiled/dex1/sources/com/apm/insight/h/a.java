package com.apm.insight.h;

import android.util.Log;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<Class<?>, Class<?>> f2872a = new HashMap();

    /* renamed from: com.apm.insight.h.a$a, reason: collision with other inner class name */
    public static class C0084a<T> {

        /* renamed from: a, reason: collision with root package name */
        public final Class<? extends T> f2873a;

        /* renamed from: b, reason: collision with root package name */
        public final T f2874b;
    }

    static {
        f2872a.put(Boolean.class, Boolean.TYPE);
        f2872a.put(Byte.class, Byte.TYPE);
        f2872a.put(Character.class, Character.TYPE);
        f2872a.put(Short.class, Short.TYPE);
        f2872a.put(Integer.class, Integer.TYPE);
        f2872a.put(Float.class, Float.TYPE);
        f2872a.put(Long.class, Long.TYPE);
        f2872a.put(Double.class, Double.TYPE);
        f2872a.put(Boolean.TYPE, Boolean.TYPE);
        f2872a.put(Byte.TYPE, Byte.TYPE);
        f2872a.put(Character.TYPE, Character.TYPE);
        f2872a.put(Short.TYPE, Short.TYPE);
        f2872a.put(Integer.TYPE, Integer.TYPE);
        f2872a.put(Float.TYPE, Float.TYPE);
        f2872a.put(Long.TYPE, Long.TYPE);
        f2872a.put(Double.TYPE, Double.TYPE);
    }

    public static <T> T a(Class<?> cls, String str, Object... objArr) {
        return (T) a(cls, str, a(objArr)).invoke(null, b(objArr));
    }

    public static <T> T a(String str, String str2, Object... objArr) {
        try {
            return (T) a(Class.forName(str), str2, objArr);
        } catch (Exception e) {
            Log.w("JavaCalls", "Meet exception when call Method '" + str2 + "' in " + str, e);
            return null;
        }
    }

    private static Method a(Class<?> cls, String str, Class<?>... clsArr) throws NoSuchMethodException {
        Method methodA = a(cls.getDeclaredMethods(), str, clsArr);
        if (methodA != null) {
            methodA.setAccessible(true);
            return methodA;
        }
        if (cls.getSuperclass() != null) {
            return a((Class<?>) cls.getSuperclass(), str, clsArr);
        }
        throw new NoSuchMethodException();
    }

    private static Method a(Method[] methodArr, String str, Class<?>[] clsArr) {
        if (str == null) {
            throw new NullPointerException("Method name must not be null.");
        }
        for (Method method : methodArr) {
            if (method.getName().equals(str) && a(method.getParameterTypes(), clsArr)) {
                return method;
            }
        }
        return null;
    }

    private static boolean a(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr == null) {
            return clsArr2 == null || clsArr2.length == 0;
        }
        if (clsArr2 == null) {
            return clsArr.length == 0;
        }
        if (clsArr.length != clsArr2.length) {
            return false;
        }
        for (int i = 0; i < clsArr.length; i++) {
            if (!clsArr[i].isAssignableFrom(clsArr2[i]) && (!f2872a.containsKey(clsArr[i]) || !f2872a.get(clsArr[i]).equals(f2872a.get(clsArr2[i])))) {
                return false;
            }
        }
        return true;
    }

    private static Class<?>[] a(Object... objArr) {
        if (objArr == null || objArr.length <= 0) {
            return null;
        }
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            if (obj == null || !(obj instanceof C0084a)) {
                clsArr[i] = obj == null ? null : obj.getClass();
            } else {
                clsArr[i] = ((C0084a) obj).f2873a;
            }
        }
        return clsArr;
    }

    private static Object[] b(Object... objArr) {
        if (objArr == null || objArr.length <= 0) {
            return null;
        }
        Object[] objArr2 = new Object[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            if (obj == null || !(obj instanceof C0084a)) {
                objArr2[i] = obj;
            } else {
                objArr2[i] = ((C0084a) obj).f2874b;
            }
        }
        return objArr2;
    }
}
