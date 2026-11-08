package com.taobao.accs.client;

import com.taobao.accs.utl.UtilityImpl;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class c implements Runnable {
    final /* synthetic */ GlobalClientInfo a;

    c(GlobalClientInfo globalClientInfo) {
        this.a = globalClientInfo;
    }

    @Override // java.lang.Runnable
    public void run() {
        GlobalClientInfo.c = UtilityImpl.p(GlobalClientInfo.a);
    }
}
