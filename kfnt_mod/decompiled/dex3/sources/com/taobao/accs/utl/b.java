package com.taobao.accs.utl;

import android.content.Intent;
import com.taobao.accs.base.AccsDataListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class b implements Runnable {
    final /* synthetic */ AccsDataListener a;
    final /* synthetic */ String b;
    final /* synthetic */ int c;
    final /* synthetic */ Intent d;

    b(AccsDataListener accsDataListener, String str, int i, Intent intent) {
        this.a = accsDataListener;
        this.b = str;
        this.c = i;
        this.d = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.onBind(this.b, this.c, a.b(this.d));
    }
}
