package com.taobao.accs.net;

import android.text.TextUtils;
import com.taobao.accs.utl.ALog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class p implements Runnable {
    final /* synthetic */ j a;

    p(j jVar) {
        this.a = jVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.a.d == null || TextUtils.isEmpty(this.a.i())) {
                return;
            }
            ALog.i(this.a.d(), "mTryStartServiceRunable bindapp", new Object[0]);
            this.a.k();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
