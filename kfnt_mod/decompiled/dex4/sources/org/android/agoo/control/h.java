package org.android.agoo.control;

import android.content.Intent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
class h implements Runnable {
    final /* synthetic */ Intent a;
    final /* synthetic */ g b;

    h(g gVar, Intent intent) {
        this.b = gVar;
        this.a = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.a.onHandleIntent(this.a);
    }
}
