package com.meizu.cloud.pushsdk.base;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class i<T> {
    private T a;
    private T b;

    protected i(T t) {
        if (t == null) {
            throw new RuntimeException("proxy must be has a default implementation");
        }
        this.b = t;
    }

    protected T c() {
        return this.a != null ? this.a : this.b;
    }
}
