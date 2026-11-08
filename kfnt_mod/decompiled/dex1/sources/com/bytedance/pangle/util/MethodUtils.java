package com.bytedance.pangle.util;

import android.text.TextUtils;
import androidx.annotation.Keep;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MethodUtils {
    public static final Class<?>[] EMPTY_CLASS_ARRAY;
    private static Map<String, Method> sMethodCache = new HashMap();
    private static final HashMap<Class<?>, Class<?>> sPrimitiveToWrapperMap;

    static {
        HashMap<Class<?>, Class<?>> map = new HashMap<>();
        sPrimitiveToWrapperMap = map;
        map.put(Boolean.TYPE, Boolean.class);
        sPrimitiveToWrapperMap.put(Byte.TYPE, Byte.class);
        sPrimitiveToWrapperMap.put(Character.TYPE, Character.class);
        sPrimitiveToWrapperMap.put(Short.TYPE, Short.class);
        sPrimitiveToWrapperMap.put(Integer.TYPE, Integer.class);
        sPrimitiveToWrapperMap.put(Long.TYPE, Long.class);
        sPrimitiveToWrapperMap.put(Double.TYPE, Double.class);
        sPrimitiveToWrapperMap.put(Float.TYPE, Float.class);
        sPrimitiveToWrapperMap.put(Void.TYPE, Void.class);
        EMPTY_CLASS_ARRAY = new Class[0];
    }

    private static String getKey(Class<?> cls, String str, Class<?>... clsArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(cls.toString());
        sb.append("#");
        sb.append(str);
        if (clsArr != null && clsArr.length > 0) {
            for (Class<?> cls2 : clsArr) {
                sb.append(cls2.toString());
                sb.append("#");
            }
        } else {
            sb.append(Void.class.toString());
        }
        return sb.toString();
    }

    public static Method getAccessibleMethod(Class<?> cls, String str, Class<?>... clsArr) throws NoSuchMethodException, SecurityException {
        Method declaredMethod;
        String key = getKey(cls, str, clsArr);
        synchronized (sMethodCache) {
            declaredMethod = sMethodCache.get(key);
        }
        if (declaredMethod != null) {
            if (!declaredMethod.isAccessible()) {
                declaredMethod.setAccessible(true);
            }
            return declaredMethod;
        }
        while (cls != null) {
            try {
                declaredMethod = cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException unused) {
            }
            if (declaredMethod == null) {
                try {
                    Method method = declaredMethod;
                    for (Method method2 : cls.getDeclaredMethods()) {
                        try {
                            if (method2 != null && TextUtils.equals(method2.getName(), str)) {
                                Class<?>[] parameterTypes = method2.getParameterTypes();
                                if (clsArr != null && parameterTypes != null && clsArr.length == parameterTypes.length) {
                                    boolean z = true;
                                    for (int i = 0; i < clsArr.length; i++) {
                                        if (!isAssignableFrom(clsArr[i], parameterTypes[i])) {
                                            z = false;
                                        }
                                    }
                                    if (z) {
                                        method = method2;
                                    }
                                }
                            }
                        } catch (Throwable unused2) {
                        }
                    }
                    declaredMethod = method;
                } catch (Throwable unused3) {
                }
            }
            if (declaredMethod != null) {
                declaredMethod.setAccessible(true);
                synchronized (sMethodCache) {
                    sMethodCache.put(key, declaredMethod);
                }
                return declaredMethod;
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    public static boolean isAssignableFrom(Class<?> cls, Class<?> cls2) {
        if (cls2 == null) {
            return false;
        }
        if (cls == null) {
            return !cls2.isPrimitive();
        }
        if (cls.isPrimitive() && !cls2.isPrimitive()) {
            cls = sPrimitiveToWrapperMap.get(cls);
        }
        if (cls2.isPrimitive() && !cls.isPrimitive()) {
            cls2 = sPrimitiveToWrapperMap.get(cls2);
        }
        return cls2.isAssignableFrom(cls);
    }

    public static Object invokeMethod(Object obj, String str, Object[] objArr, Class<?>[] clsArr) throws NoSuchMethodException, SecurityException {
        Method accessibleMethod = getAccessibleMethod(obj.getClass(), str, clsArr);
        if (accessibleMethod != null) {
            return accessibleMethod.invoke(obj, objArr);
        }
        throw new NoSuchMethodException(str);
    }

    public static Object invokeStaticMethod(Class cls, String str, Object[] objArr, Class<?>[] clsArr) throws NoSuchMethodException, SecurityException {
        Method accessibleMethod = getAccessibleMethod(cls, str, clsArr);
        if (accessibleMethod != null) {
            return accessibleMethod.invoke(null, objArr);
        }
        return null;
    }

    public static Object invokeStaticMethod(Class cls, String str, Object... objArr) {
        return invokeStaticMethod(cls, str, objArr, toClass(objArr));
    }

    public static Object invokeMethod(Object obj, String str, Object... objArr) {
        return invokeMethod(obj, str, objArr, toClass(objArr));
    }

    public static <T> T invokeConstructor(Class<T> cls, Object... objArr) {
        return (T) invokeConstructor(cls, objArr, toClass(objArr));
    }

    public static <T> T invokeConstructor(Class<T> cls, Object[] objArr, Class<?>[] clsArr) throws NoSuchMethodException, SecurityException {
        Constructor matchingAccessibleConstructor = getMatchingAccessibleConstructor(cls, clsArr);
        if (matchingAccessibleConstructor != null) {
            return (T) matchingAccessibleConstructor.newInstance(objArr);
        }
        return null;
    }

    public static <T> Constructor<T> getMatchingAccessibleConstructor(Class<T> cls, Class<?>... clsArr) throws NoSuchMethodException, SecurityException {
        try {
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(clsArr);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return declaredConstructor;
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static Class<?>[] toClass(Object... objArr) {
        if (objArr != null && objArr.length != 0) {
            Class<?>[] clsArr = new Class[objArr.length];
            for (int i = 0; i < objArr.length; i++) {
                clsArr[i] = objArr[i] == null ? null : objArr[i].getClass();
            }
            return clsArr;
        }
        return EMPTY_CLASS_ARRAY;
    }
}
