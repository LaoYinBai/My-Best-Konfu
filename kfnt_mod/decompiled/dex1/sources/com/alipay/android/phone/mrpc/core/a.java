package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class a implements v {

    /* renamed from: a, reason: collision with root package name */
    protected Method f2431a;

    /* renamed from: b, reason: collision with root package name */
    protected byte[] f2432b;

    /* renamed from: c, reason: collision with root package name */
    protected String f2433c;
    protected int d;
    protected String e;
    protected boolean f;

    public a(Method method, int i, String str, byte[] bArr, String str2, boolean z) {
        this.f2431a = method;
        this.d = i;
        this.f2433c = str;
        this.f2432b = bArr;
        this.e = str2;
        this.f = z;
    }
}
