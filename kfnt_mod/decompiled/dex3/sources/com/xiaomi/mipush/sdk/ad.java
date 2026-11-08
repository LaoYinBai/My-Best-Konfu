package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.ai;
import com.xiaomi.push.hj;
import com.xiaomi.push.hp;
import com.xiaomi.push.ht;
import com.xiaomi.push.hw;
import com.xiaomi.push.ib;
import com.xiaomi.push.ii;
import com.xiaomi.push.it;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ad extends ai.a {
    private Context a;

    public ad(Context context) {
        this.a = context;
    }

    @Override // com.xiaomi.push.ai.a
    /* renamed from: a */
    public String mo136a() {
        return "2";
    }

    @Override // java.lang.Runnable
    public void run() {
        com.xiaomi.push.service.aq aqVarA = com.xiaomi.push.service.aq.a(this.a);
        ib ibVar = new ib();
        ibVar.a(com.xiaomi.push.service.ar.a(aqVarA, hp.MISC_CONFIG));
        ibVar.b(com.xiaomi.push.service.ar.a(aqVarA, hp.PLUGIN_CONFIG));
        ii iiVar = new ii("-1", false);
        iiVar.c(ht.DailyCheckClientConfig.f509a);
        iiVar.a(it.a(ibVar));
        an.a(this.a).a((an) iiVar, hj.Notification, (hw) null);
    }
}
