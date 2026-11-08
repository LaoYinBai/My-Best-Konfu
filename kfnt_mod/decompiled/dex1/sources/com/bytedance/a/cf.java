package com.bytedance.a;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class cf implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Context f3463a;

    public cf(Context context) {
        this.f3463a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        ca.f3451b.b(this.f3463a).edit().putBoolean("_install_started_v2", true).apply();
    }
}
