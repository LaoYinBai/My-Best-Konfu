package com.xiaomi.push.service;

import com.xiaomi.push.ai;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class ad extends ai.a {
    final /* synthetic */ int a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ ao f871a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ String f872a;

    ad(String str, ao aoVar, int i) {
        this.f872a = str;
        this.f871a = aoVar;
        this.a = i;
    }

    @Override // com.xiaomi.push.ai.a
    /* renamed from: a */
    public String mo136a() {
        return this.f872a;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f871a.a(this.a);
    }
}
