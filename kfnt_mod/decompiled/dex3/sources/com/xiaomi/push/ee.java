package com.xiaomi.push;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class ee implements Runnable {
    final /* synthetic */ int a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ Context f316a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ String f317a;
    final /* synthetic */ String b;

    ee(Context context, String str, int i, String str2) {
        this.f316a = context;
        this.f317a = str;
        this.a = i;
        this.b = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        ed.c(this.f316a, this.f317a, this.a, this.b);
    }
}
