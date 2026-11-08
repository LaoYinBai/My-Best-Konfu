package com.taobao.accs.utl;

import com.taobao.accs.base.AccsDataListener;
import com.taobao.accs.base.TaoBaseService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class d implements Runnable {
    final /* synthetic */ AccsDataListener a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ byte[] e;
    final /* synthetic */ TaoBaseService.ExtraInfo f;

    d(AccsDataListener accsDataListener, String str, String str2, String str3, byte[] bArr, TaoBaseService.ExtraInfo extraInfo) {
        this.a = accsDataListener;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = bArr;
        this.f = extraInfo;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.onData(this.b, this.c, this.d, this.e, this.f);
    }
}
