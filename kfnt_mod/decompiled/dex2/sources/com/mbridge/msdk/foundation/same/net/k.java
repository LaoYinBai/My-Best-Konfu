package com.mbridge.msdk.foundation.same.net;

/* compiled from: Response.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class k<T> {
    public T a;
    public com.mbridge.msdk.foundation.same.net.a.a b;
    public com.mbridge.msdk.foundation.same.net.f.c c;

    public static <T> k<T> a(T t, com.mbridge.msdk.foundation.same.net.f.c cVar) {
        return new k<>(t, cVar);
    }

    public static <T> k<T> a(com.mbridge.msdk.foundation.same.net.a.a aVar) {
        return new k<>(aVar);
    }

    private k(T t, com.mbridge.msdk.foundation.same.net.f.c cVar) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.a = t;
        this.c = cVar;
    }

    private k(com.mbridge.msdk.foundation.same.net.a.a aVar) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.b = aVar;
    }
}
