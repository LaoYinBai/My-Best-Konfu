package com.umeng.commonsdk.proguard;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class af implements Serializable {
    private static Map<Class<? extends t>, Map<? extends aa, af>> d = new HashMap();
    public final String a;
    public final byte b;
    public final ag c;

    public af(String str, byte b, ag agVar) {
        this.a = str;
        this.b = b;
        this.c = agVar;
    }

    public static void a(Class<? extends t> cls, Map<? extends aa, af> map) {
        d.put(cls, map);
    }

    public static Map<? extends aa, af> a(Class<? extends t> cls) throws IllegalAccessException, InstantiationException {
        if (!d.containsKey(cls)) {
            try {
                cls.newInstance();
            } catch (IllegalAccessException e) {
                throw new RuntimeException("IllegalAccessException for TBase class: " + cls.getName() + ", message: " + e.getMessage());
            } catch (InstantiationException e2) {
                throw new RuntimeException("InstantiationException for TBase class: " + cls.getName() + ", message: " + e2.getMessage());
            }
        }
        return d.get(cls);
    }
}
