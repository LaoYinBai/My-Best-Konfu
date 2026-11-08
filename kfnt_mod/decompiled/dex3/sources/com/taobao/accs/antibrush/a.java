package com.taobao.accs.antibrush;

import com.taobao.accs.utl.ALog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class a implements Runnable {
    final /* synthetic */ AntiBrush a;

    a(AntiBrush antiBrush) {
        this.a = antiBrush;
    }

    @Override // java.lang.Runnable
    public void run() {
        ALog.e("AntiBrush", "anti bursh timeout", new Object[0]);
        AntiBrush.onResult(this.a.mContext, false);
    }
}
