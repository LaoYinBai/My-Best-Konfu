package com.taobao.accs.utl;

import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class m implements Runnable {
    final /* synthetic */ l a;

    m(l lVar) {
        this.a = lVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.j = 2;
        LocalBroadcastManager.getInstance(l.d).sendBroadcast(new Intent(l.ACTION_STATE_DEEPBACK));
    }
}
