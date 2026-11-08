package com.baidu.mobads.sdk.internal;

import com.baidu.mobads.sdk.internal.bv;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class bk implements bv.a {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ bh f3166a;

    bk(bh bhVar) {
        this.f3166a = bhVar;
    }

    @Override // com.baidu.mobads.sdk.internal.bv.a
    public void a(String str) {
        try {
            this.f3166a.b();
            this.f3166a.a(str);
        } catch (Throwable th) {
            bb.a().a(th);
        }
    }
}
