package com.ss.union.game.sdk.account.e;

import com.ss.union.game.sdk.account.e.c;
import com.ss.union.game.sdk.common.mvp.BasePresenter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d extends BasePresenter<c.b> implements c.a {
    private f a;
    private b b = new b();
    private h c;

    public d(int i) {
        this.a = new f(i);
        this.c = new h(i);
    }

    @Override // com.ss.union.game.sdk.common.mvp.BasePresenter, com.ss.union.game.sdk.common.mvp.IPresenter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void attach(c.b bVar) {
        super.attach(bVar);
        this.a.attach(bVar);
        this.b.attach(bVar);
        this.c.attach(bVar);
    }

    @Override // com.ss.union.game.sdk.common.mvp.BasePresenter, com.ss.union.game.sdk.common.mvp.IPresenter
    public void detach() {
        super.detach();
        this.a.detach();
        this.b.detach();
        this.c.detach();
    }

    @Override // com.ss.union.game.sdk.account.e.e.a
    public void a(String str, String str2) {
        this.a.a(str, str2);
    }

    @Override // com.ss.union.game.sdk.account.e.e.a
    public void a(String str, String str2, String str3) {
        this.a.a(str, str2, str3);
    }

    @Override // com.ss.union.game.sdk.account.e.a.InterfaceC0049a
    public void a() {
        this.b.a();
    }

    @Override // com.ss.union.game.sdk.account.e.g.b
    public void b() {
        this.c.b();
    }
}
