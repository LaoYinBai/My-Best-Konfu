package com.taobao.accs.utl;

import android.content.Intent;
import com.taobao.accs.base.AccsDataListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class f implements Runnable {
    final /* synthetic */ AccsDataListener a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ int d;
    final /* synthetic */ Intent e;

    f(AccsDataListener accsDataListener, String str, String str2, int i, Intent intent) {
        this.a = accsDataListener;
        this.b = str;
        this.c = str2;
        this.d = i;
        this.e = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.onSendData(this.b, this.c, this.d, a.b(this.e));
    }
}
