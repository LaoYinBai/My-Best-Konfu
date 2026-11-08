package com.taobao.accs.utl;

import com.taobao.accs.base.AccsDataListener;
import com.taobao.accs.base.TaoBaseService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class h implements Runnable {
    final /* synthetic */ AccsDataListener a;
    final /* synthetic */ String b;
    final /* synthetic */ boolean c;
    final /* synthetic */ boolean d;

    h(AccsDataListener accsDataListener, String str, boolean z, boolean z2) {
        this.a = accsDataListener;
        this.b = str;
        this.c = z;
        this.d = z2;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.onConnected(new TaoBaseService.ConnectInfo(this.b, this.c, this.d));
    }
}
