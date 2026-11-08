package com.xiaomi.push.service.receivers;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class a implements Runnable {
    final /* synthetic */ Context a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ NetworkStatusReceiver f972a;

    a(NetworkStatusReceiver networkStatusReceiver, Context context) {
        this.f972a = networkStatusReceiver;
        this.a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f972a.a(this.a);
    }
}
