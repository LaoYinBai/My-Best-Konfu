package com.baidu.mobads.sdk.internal;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class aj {

    /* renamed from: a, reason: collision with root package name */
    static bb f3110a = bb.a();

    /* renamed from: b, reason: collision with root package name */
    private static final String f3111b = "com.baidu.mobads.sdk.internal.aj";

    public static Class<?> a(String str, ClassLoader classLoader) {
        if (classLoader == null) {
            return null;
        }
        try {
            return classLoader.loadClass(str);
        } catch (Exception e) {
            f3110a.a(f3111b, Log.getStackTraceString(e));
            return null;
        }
    }

    public static Class<?> a(String str) {
        try {
            return Class.forName(str);
        } catch (Exception e) {
            f3110a.b(f3111b, Log.getStackTraceString(e));
            return null;
        }
    }

    public static Object a(String str, ClassLoader classLoader, String str2, Class[] clsArr, Object[] objArr) throws NoSuchMethodException, SecurityException {
        try {
            Class<?> clsA = a(str, classLoader);
            if (clsA == null) {
                return null;
            }
            Method declaredMethod = clsA.getDeclaredMethod(str2, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(null, objArr);
        } catch (Exception e) {
            f3110a.b(f3111b, Log.getStackTraceString(e));
            return null;
        }
    }

    public static boolean a(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getDeclaredMethod(str, clsArr) != null;
        } catch (Exception e) {
            f3110a.b(f3111b, Log.getStackTraceString(e));
            return false;
        }
    }

    public static Object a(Object obj, String str) {
        Field fieldB = b(obj, str);
        if (fieldB == null) {
            return null;
        }
        fieldB.setAccessible(true);
        try {
            return fieldB.get(obj);
        } catch (Exception e) {
            f3110a.b(f3111b, Log.getStackTraceString(e));
            return null;
        }
    }

    public static Object a(Class cls, String str) {
        Field fieldB = b((Class<?>) cls, str);
        if (fieldB == null) {
            return null;
        }
        fieldB.setAccessible(true);
        try {
            return fieldB.get(cls);
        } catch (Exception e) {
            f3110a.b(f3111b, Log.getStackTraceString(e));
            return null;
        }
    }

    public static Field b(Object obj, String str) {
        for (Class<?> superclass = obj.getClass(); superclass != Object.class; superclass = superclass.getSuperclass()) {
            try {
                return superclass.getDeclaredField(str);
            } catch (Exception e) {
                f3110a.b(f3111b, Log.getStackTraceString(e));
            }
        }
        return null;
    }

    public static Field b(Class<?> cls, String str) {
        while (cls != Object.class) {
            try {
                return cls.getDeclaredField(str);
            } catch (Exception e) {
                f3110a.b(f3111b, Log.getStackTraceString(e));
                cls = cls.getSuperclass();
            }
        }
        return null;
    }

    public static Method b(Class<?> cls, String str, Class<?>... clsArr) throws NoSuchMethodException, SecurityException {
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
            if (declaredMethod == null) {
                return null;
            }
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Exception e) {
            f3110a.b(f3111b, Log.getStackTraceString(e));
            return null;
        }
    }

    public static Object a(Object obj, String str, Object... objArr) {
        try {
            return a(obj.getClass(), obj, str, a(objArr), objArr);
        } catch (Exception e) {
            f3110a.b(f3111b, Log.getStackTraceString(e));
            return null;
        }
    }

    public static Class<?>[] a(Object... objArr) {
        try {
            Class<?>[] clsArr = new Class[objArr.length];
            for (int i = 0; i < clsArr.length; i++) {
                clsArr[i] = objArr[i].getClass();
            }
            return clsArr;
        } catch (Exception e) {
            f3110a.b(f3111b, Log.getStackTraceString(e));
            return null;
        }
    }

    public static Object a(Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        try {
            return a(obj.getClass(), obj, str, clsArr, objArr);
        } catch (Exception e) {
            f3110a.b(f3111b, Log.getStackTraceString(e));
            return null;
        }
    }

    public static Object a(Class<?> cls, Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        try {
            if (a(cls, str, clsArr)) {
                return b(cls, str, clsArr).invoke(obj, objArr);
            }
            return null;
        } catch (Exception e) {
            f3110a.b(f3111b, Log.getStackTraceString(e));
            return null;
        }
    }

    public static Object a(String str, ClassLoader classLoader, Class<?>[] clsArr, Object... objArr) {
        try {
            Class<?> clsA = a(str, classLoader);
            if (clsA != null) {
                return clsA.getDeclaredConstructor(clsArr).newInstance(objArr);
            }
            return null;
        } catch (Exception e) {
            f3110a.b(f3111b, Log.getStackTraceString(e));
            return null;
        }
    }

    public static Object a(String str, Class<?>[] clsArr, Object... objArr) {
        try {
            Class<?> clsA = a(str);
            if (clsA != null) {
                return clsA.getDeclaredConstructor(clsArr).newInstance(objArr);
            }
            return null;
        } catch (Exception e) {
            f3110a.b(f3111b, Log.getStackTraceString(e));
            return null;
        }
    }

    public static Object a(String str, ClassLoader classLoader, InvocationHandler invocationHandler) {
        Class<?> clsA = a(str, classLoader);
        if (clsA != null) {
            return Proxy.newProxyInstance(clsA.getClassLoader(), new Class[]{clsA}, invocationHandler);
        }
        return null;
    }

    public static Object a(String str, InvocationHandler invocationHandler) {
        Class<?> clsA = a(str);
        if (clsA != null) {
            return Proxy.newProxyInstance(clsA.getClassLoader(), new Class[]{clsA}, invocationHandler);
        }
        return null;
    }

    public static Object a(String str, Object obj, ClassLoader classLoader, String str2, Class<?>[] clsArr, Object... objArr) {
        Method declaredMethod;
        try {
            Class<?> clsA = a(str, classLoader);
            if (clsA == null || (declaredMethod = clsA.getDeclaredMethod(str2, clsArr)) == null) {
                return null;
            }
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(obj, objArr);
        } catch (Exception e) {
            f3110a.b(f3111b, Log.getStackTraceString(e));
            return null;
        }
    }

    public static Object a(String str, Object obj, String str2, Class<?>[] clsArr, Object... objArr) {
        Method declaredMethod;
        try {
            Class<?> clsA = a(str);
            if (clsA == null || (declaredMethod = clsA.getDeclaredMethod(str2, clsArr)) == null) {
                return null;
            }
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(obj, objArr);
        } catch (Exception e) {
            f3110a.b(f3111b, Log.getStackTraceString(e));
            return null;
        }
    }

    public static Object a(ClassLoader classLoader, String str, String str2) {
        try {
            return a((Class) Class.forName(str, true, classLoader), str2);
        } catch (Exception e) {
            f3110a.b(f3111b, Log.getStackTraceString(e));
            return null;
        }
    }

    public static ClassLoader a(Context context) {
        return af.a(bh.a(context), context.getFilesDir().getAbsolutePath(), null, aj.class.getClassLoader());
    }
}
