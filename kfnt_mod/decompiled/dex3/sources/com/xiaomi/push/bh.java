package com.xiaomi.push;

import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class bh {
    private static final Map<Class<?>, Class<?>> a = new HashMap();

    public static class a<T> {
        public final Class<? extends T> a;

        /* renamed from: a, reason: collision with other field name */
        public final T f152a;
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

    /* JADX WARN: Removed duplicated region for block: B:22:0x0015 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[LOOP:0: B:3:0x0001->B:25:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T> T a(java.lang.Class<? extends java.lang.Object> r3, java.lang.Object r4, java.lang.String r5) throws java.lang.NoSuchFieldException {
        /*
            r0 = 0
        L1:
            r1 = 1
            if (r0 != 0) goto L1b
            java.lang.reflect.Field r2 = r3.getDeclaredField(r5)     // Catch: java.lang.NoSuchFieldException -> Le
            r2.setAccessible(r1)     // Catch: java.lang.NoSuchFieldException -> Ld
            r0 = r2
            goto L12
        Ld:
            r0 = r2
        Le:
            java.lang.Class r3 = r3.getSuperclass()
        L12:
            if (r3 == 0) goto L15
            goto L1
        L15:
            java.lang.NoSuchFieldException r3 = new java.lang.NoSuchFieldException
            r3.<init>()
            throw r3
        L1b:
            r0.setAccessible(r1)
            java.lang.Object r3 = r0.get(r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.bh.a(java.lang.Class, java.lang.Object, java.lang.String):java.lang.Object");
    }

    public static <T> T a(Class<? extends Object> cls, String str) {
        try {
            return (T) a(cls, (Object) null, str);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Meet exception when call getStaticField '");
            sb.append(str);
            sb.append("' in ");
            sb.append(cls != null ? cls.getSimpleName() : "");
            sb.append(", ");
            sb.append(e);
            Log.w("JavaCalls", sb.toString());
            return null;
        }
    }

    public static <T> T a(Class<?> cls, String str, Object... objArr) {
        return (T) a(cls, str, a(objArr)).invoke(null, m128a(objArr));
    }

    public static <T> T a(Object obj, String str) {
        try {
            return (T) a((Class<? extends Object>) obj.getClass(), obj, str);
        } catch (Exception e) {
            Log.w("JavaCalls", "Meet exception when call getField '" + str + "' in " + obj + ", " + e);
            return null;
        }
    }

    public static <T> T a(Object obj, String str, Object... objArr) {
        try {
            return (T) b(obj, str, objArr);
        } catch (Exception e) {
            Log.w("JavaCalls", "Meet exception when call Method '" + str + "' in " + obj + ", " + e);
            return null;
        }
    }

    public static <T> T a(String str, String str2) {
        try {
            return (T) a((Class<? extends Object>) t.a(null, str), (Object) null, str2);
        } catch (Exception e) {
            Log.w("JavaCalls", "Meet exception when call getStaticField '" + str2 + "' in " + str + ", " + e);
            return null;
        }
    }

    public static <T> T a(String str, String str2, Object... objArr) {
        try {
            return (T) a(t.a(null, str), str2, objArr);
        } catch (Exception e) {
            Log.w("JavaCalls", "Meet exception when call Method '" + str2 + "' in " + str + ", " + e);
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

    public static void a(Object obj, String str, Object obj2) {
        try {
            b(obj, str, obj2);
        } catch (Exception e) {
            Log.w("JavaCalls", "Meet exception when call setField '" + str + "' in " + obj + ", " + e);
        }
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
            if (clsArr2[i] != null && !clsArr[i].isAssignableFrom(clsArr2[i]) && (!a.containsKey(clsArr[i]) || !a.get(clsArr[i]).equals(a.get(clsArr2[i])))) {
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

    /* renamed from: a, reason: collision with other method in class */
    private static Object[] m128a(Object... objArr) {
        if (objArr == null || objArr.length <= 0) {
            return null;
        }
        Object[] objArr2 = new Object[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            if (obj == null || !(obj instanceof a)) {
                objArr2[i] = obj;
            } else {
                objArr2[i] = ((a) obj).f152a;
            }
        }
        return objArr2;
    }

    public static <T> T b(Object obj, String str, Object... objArr) {
        return (T) a(obj.getClass(), str, a(objArr)).invoke(obj, m128a(objArr));
    }

    public static void b(Object obj, String str, Object obj2) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Class<?> superclass = obj.getClass();
        Field declaredField = null;
        while (declaredField == null) {
            try {
                declaredField = superclass.getDeclaredField(str);
            } catch (NoSuchFieldException unused) {
                superclass = superclass.getSuperclass();
            }
            if (superclass == null) {
                throw new NoSuchFieldException();
            }
        }
        declaredField.setAccessible(true);
        declaredField.set(obj, obj2);
    }
}
