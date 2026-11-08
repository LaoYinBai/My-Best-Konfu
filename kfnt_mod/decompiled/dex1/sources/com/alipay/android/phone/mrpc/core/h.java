package com.alipay.android.phone.mrpc.core;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class h extends w {

    /* renamed from: a, reason: collision with root package name */
    private Context f2453a;

    public h(Context context) {
        this.f2453a = context;
    }

    @Override // com.alipay.android.phone.mrpc.core.w
    public final <T> T a(Class<T> cls, aa aaVar) {
        return (T) new x(new i(this, aaVar)).a(cls);
    }
}
