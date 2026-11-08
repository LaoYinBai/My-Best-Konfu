package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Context;
import com.xiaomi.push.ai;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class ae extends ai.a {
    final /* synthetic */ int a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ Notification f873a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ Context f874a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ String f875a;
    final /* synthetic */ String b;

    ae(int i, String str, Context context, String str2, Notification notification) {
        this.a = i;
        this.f875a = str;
        this.f874a = context;
        this.b = str2;
        this.f873a = notification;
    }

    @Override // com.xiaomi.push.ai.a
    /* renamed from: a */
    public String mo136a() {
        return ac.b(this.a, this.f875a);
    }

    @Override // java.lang.Runnable
    @TargetApi(19)
    public void run() {
        ac.b(this.f874a, this.b, this.a, this.f875a, this.f873a);
    }
}
