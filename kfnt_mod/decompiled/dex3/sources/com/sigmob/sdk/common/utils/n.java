package com.sigmob.sdk.common.utils;

import com.sigmob.sdk.common.utils.m;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class n {

    public static class a {
        private final Object a;
        private final String b;
        private Class<?> c;
        private final List<Class<?>> d;
        private final List<Object> e;
        private boolean f;
        private boolean g;

        public a(Class cls, String str) {
            m.a.a(str);
            this.g = true;
            this.a = null;
            this.b = str;
            this.d = new ArrayList();
            this.e = new ArrayList();
            this.c = cls;
        }

        public a(Object obj, String str) {
            m.a.a(str);
            this.a = obj;
            this.b = str;
            this.d = new ArrayList();
            this.e = new ArrayList();
            this.c = obj != null ? obj.getClass() : null;
        }

        public <T> a a(Class<T> cls, T t) {
            m.a.a(cls);
            this.d.add(cls);
            this.e.add(t);
            return this;
        }

        public Object a() throws NoSuchMethodException {
            Method methodA = n.a(this.c, this.b, (Class[]) this.d.toArray(new Class[this.d.size()]));
            if (this.f) {
                methodA.setAccessible(true);
            }
            return methodA.invoke(this.g ? null : this.a, this.e.toArray());
        }
    }

    public static Method a(Class<?> cls, String str, Class<?>... clsArr) throws NoSuchMethodException {
        m.a.a(str);
        m.a.a(clsArr);
        while (cls != null) {
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (Throwable unused) {
                cls = cls.getSuperclass();
            }
        }
        throw new NoSuchMethodException();
    }

    public static List<Method> a(Class<?> cls) {
        m.a.a(cls);
        try {
            return Arrays.asList(cls.getMethods());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Map<String, String> b(Class cls) {
        Field[] declaredFields = cls.getDeclaredFields();
        HashMap map = new HashMap(declaredFields.length);
        for (Field field : declaredFields) {
            map.put(field.getName(), field.getType().getName());
        }
        return map;
    }
}
