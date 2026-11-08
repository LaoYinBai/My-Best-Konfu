package com.bytedance.pangle.a.a;

import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.FieldUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, Field> f4514a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private static Map<String, Method> f4515b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    private static Map<String, Constructor> f4516c = new HashMap();
    private static Map<String, Class> d = new HashMap();

    static {
        try {
            FieldUtils.writeField(b.class, "classLoader", (Object) null);
            ZeusLogger.w(ZeusLogger.TAG_INIT, "HackHelper HackHelperImpl use BootClassLoader");
        } catch (Exception e) {
            ZeusLogger.e(ZeusLogger.TAG_INIT, "HackHelperinit failed", e);
        }
    }

    public static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        Method method;
        String strB = b(cls, str, clsArr);
        synchronized (f4515b) {
            method = f4515b.get(strB);
        }
        if (method != null) {
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            return method;
        }
        try {
            Method methodA = b.a(cls, str, clsArr);
            if (methodA != null) {
                synchronized (f4515b) {
                    f4515b.put(strB, methodA);
                }
            }
            return methodA;
        } catch (Throwable th) {
            ZeusLogger.w(ZeusLogger.TAG, "HackHelper" + String.format("getMethod %s#%s failed !!!", cls.getName(), str), th);
            return null;
        }
    }

    public static Constructor a(Class<?> cls, Class<?>... clsArr) {
        Constructor constructor;
        String strB = b(cls, "clinit", clsArr);
        synchronized (f4516c) {
            constructor = f4516c.get(strB);
        }
        if (constructor != null) {
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return constructor;
        }
        try {
            Constructor constructorA = b.a(cls, clsArr);
            if (constructorA != null) {
                synchronized (f4516c) {
                    f4516c.put(strB, constructorA);
                }
            }
            return constructorA;
        } catch (Throwable th) {
            ZeusLogger.w(ZeusLogger.TAG, "HackHelper" + String.format("getConstructor %s failed !!!", cls.getName()), th);
            return null;
        }
    }

    private static String b(Class<?> cls, String str, Class<?>... clsArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(cls.getName());
        sb.append("#");
        sb.append(str);
        if (clsArr != null && clsArr.length > 0) {
            for (Class<?> cls2 : clsArr) {
                sb.append(cls2.getName());
                sb.append("#");
            }
        } else {
            sb.append(Void.class.getName());
        }
        return sb.toString();
    }

    public static Field a(Class<?> cls, String str) {
        Field field;
        String str2 = cls.getName() + "#" + str;
        synchronized (f4514a) {
            field = f4514a.get(str2);
        }
        if (field != null) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            return field;
        }
        try {
            Field fieldA = b.a(cls, str);
            if (fieldA != null) {
                synchronized (f4514a) {
                    f4514a.put(str2, fieldA);
                }
            }
            return fieldA;
        } catch (Throwable th) {
            ZeusLogger.w(ZeusLogger.TAG, "HackHelper" + String.format("getField %s#%s failed !!!", cls.getName(), str), th);
            return null;
        }
    }
}
