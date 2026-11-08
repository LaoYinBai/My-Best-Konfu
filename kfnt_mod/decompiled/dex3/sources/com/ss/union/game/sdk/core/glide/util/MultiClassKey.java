package com.ss.union.game.sdk.core.glide.util;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MultiClassKey {
    private Class<?> a;
    private Class<?> b;
    private Class<?> c;

    public MultiClassKey() {
    }

    public MultiClassKey(Class<?> cls, Class<?> cls2) {
        set(cls, cls2);
    }

    public MultiClassKey(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        set(cls, cls2, cls3);
    }

    public void set(Class<?> cls, Class<?> cls2) {
        set(cls, cls2, null);
    }

    public void set(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        this.a = cls;
        this.b = cls2;
        this.c = cls3;
    }

    public String toString() {
        return "MultiClassKey{first=" + this.a + ", second=" + this.b + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MultiClassKey multiClassKey = (MultiClassKey) obj;
        return this.a.equals(multiClassKey.a) && this.b.equals(multiClassKey.b) && Util.bothNullOrEqual(this.c, multiClassKey.c);
    }

    public int hashCode() {
        return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + (this.c != null ? this.c.hashCode() : 0);
    }
}
