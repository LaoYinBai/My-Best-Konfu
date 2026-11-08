package com.kwad.sdk.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class s {
    private static final Map<Class<?>, Class<?>> a = new HashMap();

    public static class a<T> {
        public final Class<? extends T> a;
        public final T b;
    }

    static {
        a.put(Boolean.class, Boolean.TYPE);
        a.put(Byte.class, Byte.TYPE);
        a.put(Character.class, Character.TYPE);
        a.put(Short.class, Short.TYPE);
        a.put(Integer.class, Integer.TYPE);
        a.put(Float.class, Float.TYPE);
        a.put(Long.class, Long.TYPE);
        a.put(Double.class, Double.TYPE);
        a.put(Boolean.TYPE, Boolean.TYPE);
        a.put(Byte.TYPE, Byte.TYPE);
        a.put(Character.TYPE, Character.TYPE);
        a.put(Short.TYPE, Short.TYPE);
        a.put(Integer.TYPE, Integer.TYPE);
        a.put(Float.TYPE, Float.TYPE);
        a.put(Long.TYPE, Long.TYPE);
        a.put(Double.TYPE, Double.TYPE);
    }

    private static <T> T a(Class<?> cls, String str, Object... objArr) {
        return (T) a(cls, str, a(objArr)).invoke(null, b(objArr));
    }

    public static <T> T a(Object obj, String str) {
        try {
            return (T) b(obj, str);
        } catch (Throwable th) {
            throw a(th);
        }
    }

    public static <T> T a(Object obj, String str, Object... objArr) {
        try {
            return (T) b(obj, str, objArr);
        } catch (Throwable th) {
            throw a(th);
        }
    }

    public static <T> T a(String str, String str2, Object... objArr) {
        try {
            return (T) a(Class.forName(str), str2, objArr);
        } catch (Throwable th) {
            throw a(th);
        }
    }

    private static RuntimeException a(Throwable th) {
        return th instanceof RuntimeException ? (RuntimeException) th : new RuntimeException(th);
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
            if (!clsArr[i].isAssignableFrom(clsArr2[i]) && (!a.containsKey(clsArr[i]) || !a.get(clsArr[i]).equals(a.get(clsArr2[i])))) {
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
            if (obj == null || !(obj instanceof a)) {
                clsArr[i] = obj == null ? null : obj.getClass();
            } else {
                clsArr[i] = ((a) obj).a;
            }
        }
        return clsArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0019 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[LOOP:0: B:3:0x0005->B:25:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static <T> T b(java.lang.Object r4, java.lang.String r5) throws java.lang.NoSuchFieldException {
        /*
            java.lang.Class r0 = r4.getClass()
            r1 = 0
        L5:
            r2 = 1
            if (r1 != 0) goto L1f
            java.lang.reflect.Field r3 = r0.getDeclaredField(r5)     // Catch: java.lang.NoSuchFieldException -> L12
            r3.setAccessible(r2)     // Catch: java.lang.NoSuchFieldException -> L11
            r1 = r3
            goto L16
        L11:
            r1 = r3
        L12:
            java.lang.Class r0 = r0.getSuperclass()
        L16:
            if (r0 == 0) goto L19
            goto L5
        L19:
            java.lang.NoSuchFieldException r4 = new java.lang.NoSuchFieldException
            r4.<init>()
            throw r4
        L1f:
            r1.setAccessible(r2)
            java.lang.Object r4 = r1.get(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.utils.s.b(java.lang.Object, java.lang.String):java.lang.Object");
    }

    private static <T> T b(Object obj, String str, Object... objArr) {
        return (T) a(obj.getClass(), str, a(objArr)).invoke(obj, b(objArr));
    }

    private static Object[] b(Object... objArr) {
        if (objArr == null || objArr.length <= 0) {
            return null;
        }
        Object[] objArr2 = new Object[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            if (obj == null || !(obj instanceof a)) {
                objArr2[i] = obj;
            } else {
                objArr2[i] = ((a) obj).b;
            }
        }
        return objArr2;
    }
}
