package com.taobao.accs.utl;

import android.content.Intent;
import com.taobao.accs.base.AccsDataListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class e implements Runnable {
    final /* synthetic */ AccsDataListener a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ int d;
    final /* synthetic */ byte[] e;
    final /* synthetic */ Intent f;

    e(AccsDataListener accsDataListener, String str, String str2, int i, byte[] bArr, Intent intent) {
        this.a = accsDataListener;
        this.b = str;
        this.c = str2;
        this.d = i;
        this.e = bArr;
        this.f = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.onResponse(this.b, this.c, this.d, this.e, a.b(this.f));
    }
}
