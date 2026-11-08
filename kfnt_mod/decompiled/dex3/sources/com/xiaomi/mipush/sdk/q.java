package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.eh;
import com.xiaomi.push.ho;
import com.xiaomi.push.service.aq;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class q extends aq.a {
    final /* synthetic */ Context a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    q(int i, String str, Context context) {
        super(i, str);
        this.a = context;
    }

    @Override // com.xiaomi.push.service.aq.a
    protected void onCallback() {
        eh.a(this.a).a(com.xiaomi.push.service.aq.a(this.a).a(ho.AwakeInfoUploadWaySwitch.a(), 0));
    }
}
