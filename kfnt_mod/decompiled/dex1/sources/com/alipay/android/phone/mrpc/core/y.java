package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class y implements InvocationHandler {

    /* renamed from: a, reason: collision with root package name */
    protected g f2480a;

    /* renamed from: b, reason: collision with root package name */
    protected Class<?> f2481b;

    /* renamed from: c, reason: collision with root package name */
    protected z f2482c;

    public y(g gVar, Class<?> cls, z zVar) {
        this.f2480a = gVar;
        this.f2481b = cls;
        this.f2482c = zVar;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        return this.f2482c.a(method, objArr);
    }
}
