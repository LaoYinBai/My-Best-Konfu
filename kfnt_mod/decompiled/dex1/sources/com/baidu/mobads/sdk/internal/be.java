package com.baidu.mobads.sdk.internal;

import com.baidu.mobads.sdk.internal.bq;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class be implements bq.a {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ bd f3156a;

    be(bd bdVar) {
        this.f3156a = bdVar;
    }

    @Override // com.baidu.mobads.sdk.internal.bq.a
    public void a(bf bfVar) {
        this.f3156a.a(bh.k, bfVar, "download apk successfully, downloader exit");
        bd unused = bd.h = null;
    }

    @Override // com.baidu.mobads.sdk.internal.bq.a
    public void b(bf bfVar) {
        this.f3156a.a(bh.l, bfVar, "downloadApk failed");
    }
}
