package com.mbridge.msdk.foundation.same.net;

/* compiled from: DefaultRetryPolicy.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b implements l {
    private int a;
    private int b;
    private final int c;

    public b() {
        this(30000, 0);
    }

    public b(int i, int i2) {
        this.a = i <= 0 ? 30000 : i;
        this.c = i2;
    }

    @Override // com.mbridge.msdk.foundation.same.net.l
    public final int a() {
        return this.a;
    }

    @Override // com.mbridge.msdk.foundation.same.net.l
    public final int b() {
        return this.b;
    }
}
