package com.alipay.android.phone.mrpc.core;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class p extends u {

    /* renamed from: c, reason: collision with root package name */
    private int f2469c;
    private String d;
    private long e;
    private long f;
    private String g;
    private HttpUrlHeader h;

    public p(HttpUrlHeader httpUrlHeader, int i, String str, byte[] bArr) {
        this.h = httpUrlHeader;
        this.f2469c = i;
        this.d = str;
        this.f2476a = bArr;
    }

    public final HttpUrlHeader a() {
        return this.h;
    }

    public final void a(long j) {
        this.e = j;
    }

    public final void a(String str) {
        this.g = str;
    }

    public final void b(long j) {
        this.f = j;
    }
}
