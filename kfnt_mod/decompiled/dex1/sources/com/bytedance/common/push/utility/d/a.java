package com.bytedance.common.push.utility.d;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private final Object f3840a;

    /* renamed from: b, reason: collision with root package name */
    private final boolean f3841b = false;

    /* renamed from: com.bytedance.common.push.utility.d.a$a, reason: collision with other inner class name */
    public static class C0111a {
    }

    public static a a(Object obj) {
        return new a(obj);
    }

    public static <T extends AccessibleObject> T a(T t) throws SecurityException {
        if (t == null) {
            return null;
        }
        if (t instanceof Member) {
            Member member = (Member) t;
            if (Modifier.isPublic(member.getModifiers()) && Modifier.isPublic(member.getDeclaringClass().getModifiers())) {
                return t;
            }
        }
        if (!t.isAccessible()) {
            t.setAccessible(true);
        }
        return t;
    }

    private a(Object obj) {
        this.f3840a = obj;
    }

    public <T> T a() {
        return (T) this.f3840a;
    }

    public a a(String str, Object obj) throws IllegalAccessException, b, IllegalArgumentException {
        try {
            b(str, new Class[0]).set(this.f3840a, b(obj));
            return this;
        } catch (Exception e) {
            throw new b(e);
        }
    }

    public a a(String str, Class<?>... clsArr) throws b {
        try {
            return a(b(str, clsArr).get(this.f3840a));
        } catch (Exception e) {
            throw new b(e);
        }
    }

    private Field b(String str, Class<?>... clsArr) throws b {
        Class<?> clsB = b();
        if (clsArr != null && clsArr.length > 0) {
            do {
                try {
                    for (Field field : clsB.getDeclaredFields()) {
                        Class<?> type = field.getType();
                        if (type != null && type.getName().equals(clsArr[0].getName()) && field.getName().equals(str)) {
                            return (Field) a(field);
                        }
                    }
                } catch (Exception unused) {
                }
                clsB = clsB.getSuperclass();
            } while (clsB != null);
            throw new b("NoSuchFieldException");
        }
        try {
            return clsB.getField(str);
        } catch (NoSuchFieldException e) {
            do {
                try {
                    return (Field) a(clsB.getDeclaredField(str));
                } catch (NoSuchFieldException unused2) {
                    clsB = clsB.getSuperclass();
                    if (clsB == null) {
                        throw new b(e);
                    }
                }
            } while (clsB == null);
            throw new b(e);
        }
    }

    public a a(String str) throws b {
        return a(str, (Class<?>[]) null, new Object[0]);
    }

    public a a(String str, Class<?>[] clsArr, Object... objArr) throws b {
        try {
            try {
                return a(b(str, clsArr, objArr), this.f3840a, objArr);
            } catch (NoSuchMethodException unused) {
                return a(c(str, clsArr), this.f3840a, objArr);
            }
        } catch (NoSuchMethodException e) {
            throw new b(e);
        }
    }

    public Method b(String str, Class<?>[] clsArr, Object... objArr) throws NoSuchMethodException, SecurityException {
        Class<?> clsB = b();
        try {
            return clsB.getMethod(str, clsArr);
        } catch (NoSuchMethodException unused) {
            do {
                try {
                    return clsB.getDeclaredMethod(str, clsArr);
                } catch (NoSuchMethodException unused2) {
                    clsB = clsB.getSuperclass();
                }
            } while (clsB != null);
            Class<?> clsB2 = b();
            for (Method method : clsB2.getMethods()) {
                if (str.equals(method.getName())) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (com.bytedance.common.push.utility.a.b()) {
                        int i = 1;
                        for (Class<?> cls : parameterTypes) {
                            com.bytedance.common.push.utility.a.b("Plugin", "Reflect type = " + clsB2 + " method = " + str + " paramTypes " + i + " = " + cls.getName());
                            i++;
                        }
                    }
                    if (parameterTypes != null && parameterTypes.length == clsArr.length) {
                        return method;
                    }
                }
            }
            throw new NoSuchMethodException();
        }
    }

    private Method c(String str, Class<?>[] clsArr) throws NoSuchMethodException, SecurityException {
        Class<?> clsB = b();
        for (Method method : clsB.getMethods()) {
            if (a(method, str, clsArr)) {
                return method;
            }
        }
        do {
            for (Method method2 : clsB.getDeclaredMethods()) {
                if (a(method2, str, clsArr)) {
                    return method2;
                }
            }
            clsB = clsB.getSuperclass();
        } while (clsB != null);
        throw new NoSuchMethodException("No similar method " + str + " with params " + Arrays.toString(clsArr) + " could be found on type " + b() + ".");
    }

    private boolean a(Method method, String str, Class<?>[] clsArr) {
        return method.getName().equals(str) && a(method.getParameterTypes(), clsArr);
    }

    private boolean a(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length != clsArr2.length) {
            return false;
        }
        for (int i = 0; i < clsArr2.length; i++) {
            if (clsArr2[i] != C0111a.class && !a(clsArr[i]).isAssignableFrom(a(clsArr2[i]))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return this.f3840a.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof a) {
            return this.f3840a.equals(((a) obj).a());
        }
        return false;
    }

    public String toString() {
        return this.f3840a.toString();
    }

    private static a a(Method method, Object obj, Object... objArr) throws IllegalAccessException, b, IllegalArgumentException, InvocationTargetException {
        try {
            a(method);
            if (method.getReturnType() == Void.TYPE) {
                method.invoke(obj, objArr);
                return a(obj);
            }
            return a(method.invoke(obj, objArr));
        } catch (Exception e) {
            throw new b(e);
        }
    }

    private static Object b(Object obj) {
        return obj instanceof a ? ((a) obj).a() : obj;
    }

    public Class<?> b() {
        if (this.f3841b) {
            return (Class) this.f3840a;
        }
        return this.f3840a.getClass();
    }

    public static Class<?> a(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        if (cls.isPrimitive()) {
            if (Boolean.TYPE == cls) {
                return Boolean.class;
            }
            if (Integer.TYPE == cls) {
                return Integer.class;
            }
            if (Long.TYPE == cls) {
                return Long.class;
            }
            if (Short.TYPE == cls) {
                return Short.class;
            }
            if (Byte.TYPE == cls) {
                return Byte.class;
            }
            if (Double.TYPE == cls) {
                return Double.class;
            }
            if (Float.TYPE == cls) {
                return Float.class;
            }
            if (Character.TYPE == cls) {
                return Character.class;
            }
            if (Void.TYPE == cls) {
                return Void.class;
            }
        }
        return cls;
    }
}
