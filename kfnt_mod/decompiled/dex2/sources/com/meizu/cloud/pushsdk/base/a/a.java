package com.meizu.cloud.pushsdk.base.a;

import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final HashMap<String, Class<?>> a = new HashMap<>();
    private Class<?> b;
    private String c;
    private Object d;

    private a(Object obj) {
        this.d = obj;
    }

    private a(String str) {
        this.c = str;
    }

    public static a a(Object obj) {
        return new a(obj);
    }

    public static a a(String str) {
        return new a(str);
    }

    public b a(Class<?>... clsArr) {
        return new b(this, clsArr);
    }

    public c a(String str, Class<?>... clsArr) {
        return new c(this, str, clsArr);
    }

    Class<?> a() throws ClassNotFoundException {
        if (this.b != null) {
            return this.b;
        }
        if (this.d != null) {
            return this.d.getClass();
        }
        Class<?> cls = a.get(this.c);
        if (cls != null) {
            return cls;
        }
        Class<?> cls2 = Class.forName(this.c);
        a.put(this.c, cls2);
        return cls2;
    }
}
