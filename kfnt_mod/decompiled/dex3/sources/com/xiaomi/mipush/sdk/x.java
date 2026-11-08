package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class x implements Runnable {
    final /* synthetic */ Context a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ Intent f99a;

    x(Context context, Intent intent) {
        this.a = context;
        this.f99a = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.startService(this.f99a);
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.m22a(e.getMessage());
        }
    }
}
