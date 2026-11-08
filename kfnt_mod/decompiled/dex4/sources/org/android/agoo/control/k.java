package org.android.agoo.control;

import android.content.Intent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
class k implements Runnable {
    final /* synthetic */ Intent a;
    final /* synthetic */ BaseIntentService b;

    k(BaseIntentService baseIntentService, Intent intent) {
        this.b = baseIntentService;
        this.a = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.onHandleIntent(this.a);
    }
}
