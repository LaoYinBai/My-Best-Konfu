package com.alibaba.sdk.android.httpdns.b;

import android.content.Context;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class a implements f {

    /* renamed from: a, reason: collision with root package name */
    private final d f2324a;

    a(Context context) {
        this.f2324a = new d(context);
    }

    @Override // com.alibaba.sdk.android.httpdns.b.f
    public List<e> a() {
        return this.f2324a.b();
    }

    @Override // com.alibaba.sdk.android.httpdns.b.f
    public void a(e eVar) {
        this.f2324a.m10a(eVar);
    }

    @Override // com.alibaba.sdk.android.httpdns.b.f
    public void b(e eVar) {
        this.f2324a.b(eVar.i, eVar.h);
    }
}
