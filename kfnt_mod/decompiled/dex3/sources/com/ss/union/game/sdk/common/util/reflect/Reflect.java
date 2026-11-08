package com.ss.union.game.sdk.common.util.reflect;

import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.umeng.commonsdk.proguard.o;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Reflect {
    private final Class<?> a;
    private final Object b;

    @Deprecated
    public static Reflect on(String str) throws ReflectException {
        return onClass(str);
    }

    @Deprecated
    public static Reflect on(String str, ClassLoader classLoader) throws ReflectException {
        return onClass(str, classLoader);
    }

    @Deprecated
    public static Reflect on(Class<?> cls) {
        return onClass(cls);
    }

    public static Reflect onClass(String str) throws ReflectException {
        return onClass(d(str));
    }

    public static Reflect onClass(String str, ClassLoader classLoader) throws ReflectException {
        return onClass(a(str, classLoader));
    }

    public static Reflect onClass(Class<?> cls) {
        return new Reflect(cls);
    }

    public static Reflect on(Object obj) {
        return new Reflect(obj == null ? Object.class : obj.getClass(), obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Reflect b(Class<?> cls, Object obj) {
        return new Reflect(cls, obj);
    }

    public static <T> T initValue(Class<T> cls) {
        if (cls == Boolean.TYPE) {
            return (T) Boolean.FALSE;
        }
        if (cls == Byte.TYPE) {
            return (T) (byte) 0;
        }
        if (cls == Short.TYPE) {
            return (T) (short) 0;
        }
        if (cls == Integer.TYPE) {
            return (T) 0;
        }
        if (cls == Long.TYPE) {
            return (T) 0L;
        }
        if (cls == Double.TYPE) {
            return (T) Double.valueOf(0.0d);
        }
        if (cls == Float.TYPE) {
            return (T) Float.valueOf(0.0f);
        }
        if (cls == Character.TYPE) {
            return (T) (char) 0;
        }
        return null;
    }

    public static <T extends AccessibleObject> T accessible(T t) throws SecurityException {
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

    private Reflect(Class<?> cls) {
        this(cls, cls);
    }

    private Reflect(Class<?> cls, Object obj) {
        this.a = cls;
        this.b = obj;
    }

    public <T> T get() {
        return (T) this.b;
    }

    public Reflect set(String str, Object obj) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException, ReflectException {
        try {
            Field fieldB = b(str);
            if ((fieldB.getModifiers() & 16) == 16) {
                try {
                    Field declaredField = Field.class.getDeclaredField("modifiers");
                    declaredField.setAccessible(true);
                    declaredField.setInt(fieldB, fieldB.getModifiers() & (-17));
                } catch (NoSuchFieldException unused) {
                }
            }
            fieldB.set(this.b, a(obj));
            return this;
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public <T> T get(String str) throws ReflectException {
        return (T) field(str).get();
    }

    public Reflect field(String str) throws ReflectException {
        try {
            Field fieldB = b(str);
            return b(fieldB.getType(), fieldB.get(this.b));
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private Field b(String str) throws ReflectException {
        Class<?> clsType = type();
        try {
            return (Field) accessible(clsType.getField(str));
        } catch (NoSuchFieldException e) {
            do {
                try {
                    return (Field) accessible(clsType.getDeclaredField(str));
                } catch (NoSuchFieldException unused) {
                    clsType = clsType.getSuperclass();
                    if (clsType == null) {
                        throw new ReflectException(e);
                    }
                }
            } while (clsType == null);
            throw new ReflectException(e);
        }
    }

    public Map<String, Reflect> fields() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Class<?> clsType = type();
        do {
            for (Field field : clsType.getDeclaredFields()) {
                if ((this.a != this.b) ^ Modifier.isStatic(field.getModifiers())) {
                    String name = field.getName();
                    if (!linkedHashMap.containsKey(name)) {
                        linkedHashMap.put(name, field(name));
                    }
                }
            }
            clsType = clsType.getSuperclass();
        } while (clsType != null);
        return linkedHashMap;
    }

    public Reflect call(String str) throws ReflectException {
        return call(str, new Object[0]);
    }

    public Reflect call(String str, Object... objArr) throws ReflectException {
        Class<?>[] clsArrA = a(objArr);
        try {
            try {
                return a(a(str, clsArrA), this.b, objArr);
            } catch (NoSuchMethodException e) {
                throw new ReflectException(e);
            }
        } catch (NoSuchMethodException unused) {
            return a(b(str, clsArrA), this.b, objArr);
        }
    }

    private Method a(String str, Class<?>[] clsArr) throws NoSuchMethodException {
        Class<?> clsType = type();
        try {
            return clsType.getMethod(str, clsArr);
        } catch (NoSuchMethodException unused) {
            do {
                try {
                    return clsType.getDeclaredMethod(str, clsArr);
                } catch (NoSuchMethodException unused2) {
                    clsType = clsType.getSuperclass();
                }
            } while (clsType != null);
            throw new NoSuchMethodException();
        }
    }

    private Method b(String str, Class<?>[] clsArr) throws NoSuchMethodException, SecurityException {
        Class<?> clsType = type();
        for (Method method : clsType.getMethods()) {
            if (a(method, str, clsArr)) {
                return method;
            }
        }
        do {
            for (Method method2 : clsType.getDeclaredMethods()) {
                if (a(method2, str, clsArr)) {
                    return method2;
                }
            }
            clsType = clsType.getSuperclass();
        } while (clsType != null);
        throw new NoSuchMethodException("No similar method " + str + " with params " + Arrays.toString(clsArr) + " could be found on type " + type() + ".");
    }

    private boolean a(Method method, String str, Class<?>[] clsArr) {
        return method.getName().equals(str) && a(method.getParameterTypes(), clsArr);
    }

    public Reflect create() throws ReflectException {
        return create(new Object[0]);
    }

    public Reflect create(Object... objArr) throws SecurityException, ReflectException {
        Class<?>[] clsArrA = a(objArr);
        try {
            return a(type().getDeclaredConstructor(clsArrA), objArr);
        } catch (NoSuchMethodException e) {
            for (Constructor<?> constructor : type().getDeclaredConstructors()) {
                if (a(constructor.getParameterTypes(), clsArrA)) {
                    return a(constructor, objArr);
                }
            }
            throw new ReflectException(e);
        }
    }

    public <P> P as(Class<P> cls) {
        final boolean z = this.b instanceof Map;
        return (P) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: com.ss.union.game.sdk.common.util.reflect.Reflect.1
            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                String name = method.getName();
                try {
                    return Reflect.b((Class<?>) Reflect.this.a, Reflect.this.b).call(name, objArr).get();
                } catch (ReflectException e) {
                    if (z) {
                        Map map = (Map) Reflect.this.b;
                        int length = objArr == null ? 0 : objArr.length;
                        if (length == 0 && name.startsWith(MonitorConstants.CONNECT_TYPE_GET)) {
                            return map.get(Reflect.c(name.substring(3)));
                        }
                        if (length == 0 && name.startsWith(o.ab)) {
                            return map.get(Reflect.c(name.substring(2)));
                        }
                        if (length == 1 && name.startsWith("set")) {
                            map.put(Reflect.c(name.substring(3)), objArr[0]);
                            return null;
                        }
                    }
                    throw e;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(String str) {
        int length = str.length();
        if (length == 0) {
            return "";
        }
        if (length == 1) {
            return str.toLowerCase();
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    private boolean a(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length != clsArr2.length) {
            return false;
        }
        for (int i = 0; i < clsArr2.length; i++) {
            if (clsArr2[i] != a.class && !wrapper(clsArr[i]).isAssignableFrom(wrapper(clsArr2[i]))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Reflect) {
            return this.b.equals(((Reflect) obj).get());
        }
        return false;
    }

    public String toString() {
        return String.valueOf(this.b);
    }

    private static Reflect a(Constructor<?> constructor, Object... objArr) throws ReflectException {
        try {
            return b(constructor.getDeclaringClass(), ((Constructor) accessible(constructor)).newInstance(objArr));
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private static Reflect a(Method method, Object obj, Object... objArr) throws IllegalAccessException, IllegalArgumentException, ReflectException, InvocationTargetException {
        try {
            accessible(method);
            if (method.getReturnType() == Void.TYPE) {
                method.invoke(obj, objArr);
                return on(obj);
            }
            return on(method.invoke(obj, objArr));
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private static Object a(Object obj) {
        return obj instanceof Reflect ? ((Reflect) obj).get() : obj;
    }

    private static Class<?>[] a(Object... objArr) {
        if (objArr == null) {
            return new Class[0];
        }
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            clsArr[i] = obj == null ? a.class : obj.getClass();
        }
        return clsArr;
    }

    private static Class<?> d(String str) throws ReflectException {
        try {
            return Class.forName(str);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private static Class<?> a(String str, ClassLoader classLoader) throws ReflectException {
        try {
            return Class.forName(str, true, classLoader);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public Class<?> type() {
        return this.a;
    }

    public static Class<?> wrapper(Class<?> cls) {
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

    private static class a {
        private a() {
        }
    }
}
