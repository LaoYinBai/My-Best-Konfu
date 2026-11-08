package com.unicom.xiaowo.login.d;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class b implements Runnable {
    private /* synthetic */ Context a;
    private /* synthetic */ String b;
    private /* synthetic */ e c;
    private /* synthetic */ a d;

    b(a aVar, Context context, String str, e eVar) {
        this.d = aVar;
        this.a = context;
        this.b = str;
        this.c = eVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws InterruptedException {
        boolean zA = this.d.a(this.a, this.b);
        if (this.c != null) {
            this.c.a(zA, null);
        }
    }
}
