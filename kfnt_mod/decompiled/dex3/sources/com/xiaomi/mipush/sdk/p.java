package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.ai;
import com.xiaomi.push.hj;
import com.xiaomi.push.hw;
import com.xiaomi.push.ii;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class p extends ai.a {
    final /* synthetic */ Context a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ ii f95a;

    p(ii iiVar, Context context) {
        this.f95a = iiVar;
        this.a = context;
    }

    @Override // com.xiaomi.push.ai.a
    /* renamed from: a */
    public String mo136a() {
        return "22";
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f95a != null) {
            this.f95a.a(com.xiaomi.push.service.at.a());
            an.a(this.a.getApplicationContext()).a((an) this.f95a, hj.Notification, true, (hw) null, true);
        }
    }
}
