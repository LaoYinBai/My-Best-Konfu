package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class ak implements Runnable {
    final /* synthetic */ Context a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ Intent f62a;

    ak(Context context, Intent intent) {
        this.a = context;
        this.f62a = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        PushMessageHandler.b(this.a, this.f62a);
    }
}
