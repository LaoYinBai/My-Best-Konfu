package com.baidu.mobads.sdk.internal;

import com.baidu.mobads.sdk.internal.u;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class as implements u.a {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ ar f3128c;

    as(ar arVar) {
        this.f3128c = arVar;
    }

    @Override // com.baidu.mobads.sdk.internal.u.a
    public void onSuccess() {
        this.f3128c.h.a(ar.f3124a, "AbstractProdTemplate,load-dex请求，回调成功");
        this.f3128c.i();
    }

    @Override // com.baidu.mobads.sdk.internal.u.a
    public void onFailure() {
        this.f3128c.h.a(ar.f3124a, "AbstractProdTemplate,load-dex请求，回调失败");
        this.f3128c.j();
    }
}
