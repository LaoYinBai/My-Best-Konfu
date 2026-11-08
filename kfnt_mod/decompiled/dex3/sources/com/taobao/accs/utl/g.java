package com.taobao.accs.utl;

import com.taobao.accs.base.AccsDataListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class g implements Runnable {
    final /* synthetic */ AccsDataListener a;
    final /* synthetic */ boolean b;

    g(AccsDataListener accsDataListener, boolean z) {
        this.a = accsDataListener;
        this.b = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.onAntiBrush(this.b, null);
    }
}
