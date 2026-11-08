package com.xiaomi.mipush.sdk;

import com.xiaomi.push.ho;
import com.xiaomi.push.service.aq;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class g extends aq.a {
    final /* synthetic */ f a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    g(f fVar, int i, String str) {
        super(i, str);
        this.a = fVar;
    }

    @Override // com.xiaomi.push.service.aq.a
    protected void onCallback() {
        boolean zA = com.xiaomi.push.service.aq.a(this.a.f89a).a(ho.AggregatePushSwitch.a(), true);
        if (this.a.f92a != zA) {
            this.a.f92a = zA;
            i.b(this.a.f89a);
        }
    }
}
