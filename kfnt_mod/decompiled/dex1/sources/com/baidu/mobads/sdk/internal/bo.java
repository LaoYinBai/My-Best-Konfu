package com.baidu.mobads.sdk.internal;

import com.baidu.mobads.sdk.internal.af;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class bo implements af.a {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ bh f3173a;

    bo(bh bhVar) {
        this.f3173a = bhVar;
    }

    @Override // com.baidu.mobads.sdk.internal.af.a
    public void a() {
        if (this.f3173a.A) {
            this.f3173a.A = false;
            this.f3173a.a(false, "remote update Network access failed");
        }
    }
}
