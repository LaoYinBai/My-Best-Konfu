package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.push.ec;
import com.xiaomi.push.eh;
import com.xiaomi.push.el;
import com.xiaomi.push.hh;
import com.xiaomi.push.hj;
import com.xiaomi.push.ht;
import com.xiaomi.push.ii;
import com.xiaomi.push.it;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class bb implements el {
    @Override // com.xiaomi.push.el
    public void a(Context context, HashMap<String, String> map) {
        ii iiVar = new ii();
        iiVar.b(eh.a(context).m290a());
        iiVar.d(eh.a(context).b());
        iiVar.c(ht.AwakeAppResponse.f509a);
        iiVar.a(at.a());
        iiVar.f650a = map;
        byte[] bArrA = it.a(y.a(iiVar.c(), iiVar.b(), iiVar, hj.Notification));
        if (!(context instanceof XMPushService)) {
            com.xiaomi.channel.commonutils.logger.b.m22a("MoleInfo : context is not correct in pushLayer " + iiVar.m476a());
            return;
        }
        com.xiaomi.channel.commonutils.logger.b.m22a("MoleInfo : send data directly in pushLayer " + iiVar.m476a());
        ((XMPushService) context).a(context.getPackageName(), bArrA, true);
    }

    @Override // com.xiaomi.push.el
    public void b(Context context, HashMap<String, String> map) {
        hh hhVarA = hh.a(context);
        if (hhVarA != null) {
            hhVarA.a("category_awake_app", "wake_up_app", 1L, ec.a(map));
        }
    }

    @Override // com.xiaomi.push.el
    public void c(Context context, HashMap<String, String> map) {
        com.xiaomi.channel.commonutils.logger.b.m22a("MoleInfo：\u3000" + ec.b(map));
    }
}
