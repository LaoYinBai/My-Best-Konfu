package com.xiaomi.push;

import com.xiaomi.push.ai;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class aj extends ai.b {
    final /* synthetic */ ai a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ String f110a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ boolean f111a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    aj(ai aiVar, ai.a aVar, boolean z, String str) {
        super(aVar);
        this.a = aiVar;
        this.f111a = z;
        this.f110a = str;
    }

    @Override // com.xiaomi.push.ai.b
    void a() {
        super.a();
    }

    @Override // com.xiaomi.push.ai.b
    void b() {
        if (this.f111a) {
            return;
        }
        this.a.f106a.edit().putLong(this.f110a, System.currentTimeMillis()).commit();
    }
}
