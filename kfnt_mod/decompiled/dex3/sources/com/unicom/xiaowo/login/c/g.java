package com.unicom.xiaowo.login.c;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class g implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ j c;
    final /* synthetic */ e d;

    g(e eVar, Context context, String str, j jVar) {
        this.d = eVar;
        this.a = context;
        this.b = str;
        this.c = jVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        com.unicom.xiaowo.login.d.a.a().a(this.a, this.b, new h(this));
    }
}
