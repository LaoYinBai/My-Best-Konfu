package com.bytedance.pangle.util;

import androidx.annotation.Keep;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class FieldUtils {
    private static Map<String, Field> sFieldCache = new HashMap();

    private static String getKey(Class<?> cls, String str) {
        return cls.toString() + "#" + str;
    }

    public static Field getField(Class<?> cls, String str) throws NoSuchFieldException {
        Field field;
        String key = getKey(cls, str);
        synchronized (sFieldCache) {
            field = sFieldCache.get(key);
        }
        if (field != null) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            return field;
        }
        while (cls != null) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                synchronized (sFieldCache) {
                    continue;
                    sFieldCache.put(key, declaredField);
                }
                return declaredField;
            } catch (NoSuchFieldException unused) {
                cls = cls.getSuperclass();
            }
        }
        return null;
    }

    public static Object readField(Field field, Object obj) {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        return field.get(obj);
    }

    public static void writeField(Field field, Object obj, Object obj2) throws IllegalAccessException, IllegalArgumentException {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        field.set(obj, obj2);
    }

    public static Object readField(Object obj, String str) throws NoSuchFieldException {
        Field field = getField(obj.getClass(), str);
        if (field != null) {
            return readField(field, obj);
        }
        return null;
    }

    public static void writeField(Object obj, String str, Object obj2) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Field field = getField(obj.getClass(), str);
        if (field != null) {
            writeField(field, obj, obj2);
        }
    }

    public static Object readStaticField(Field field) {
        return readField(field, (Object) null);
    }

    public static Object readStaticField(Class<?> cls, String str) throws NoSuchFieldException {
        Field field = getField(cls, str);
        if (field != null) {
            return readStaticField(field);
        }
        return null;
    }

    public static void writeStaticField(Field field, Object obj) throws IllegalAccessException, IllegalArgumentException {
        writeField(field, (Object) null, obj);
    }

    public static void writeStaticField(Class<?> cls, String str, Object obj) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Field field = getField(cls, str);
        if (field != null) {
            writeStaticField(field, obj);
        }
    }
}
