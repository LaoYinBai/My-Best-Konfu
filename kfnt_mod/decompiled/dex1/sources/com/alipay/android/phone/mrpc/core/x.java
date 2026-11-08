package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Proxy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class x {

    /* renamed from: a, reason: collision with root package name */
    private g f2478a;

    /* renamed from: b, reason: collision with root package name */
    private z f2479b = new z(this);

    public x(g gVar) {
        this.f2478a = gVar;
    }

    public final g a() {
        return this.f2478a;
    }

    public final <T> T a(Class<T> cls) {
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new y(this.f2478a, cls, this.f2479b));
    }
}
