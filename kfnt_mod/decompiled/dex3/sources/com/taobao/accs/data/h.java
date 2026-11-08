package com.taobao.accs.data;

import android.content.Context;
import android.content.Intent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class h implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ com.taobao.accs.net.b b;
    final /* synthetic */ Intent c;

    h(Context context, com.taobao.accs.net.b bVar, Intent intent) {
        this.a = context;
        this.b = bVar;
        this.c = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        g.a().b(this.a, this.b, this.c);
    }
}
