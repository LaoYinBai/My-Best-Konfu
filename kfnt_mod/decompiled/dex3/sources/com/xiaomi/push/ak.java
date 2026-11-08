package com.xiaomi.push;

import com.xiaomi.push.ai;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class ak extends ai.b {
    final /* synthetic */ ai a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ak(ai aiVar, ai.a aVar) {
        super(aVar);
        this.a = aiVar;
    }

    @Override // com.xiaomi.push.ai.b
    void b() {
        synchronized (this.a.f107a) {
            this.a.f108a.remove(this.a.mo136a());
        }
    }
}
