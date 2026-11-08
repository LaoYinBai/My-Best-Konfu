package com.xiaomi.mipush.sdk;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class y implements Runnable {
    final /* synthetic */ Context a;

    y(Context context) {
        this.a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        MessageHandleService.c(this.a);
    }
}
