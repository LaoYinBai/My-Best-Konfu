package com.xiaomi.mipush.sdk;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.ss.android.token.TokenConstants;
import com.xiaomi.push.ai;
import com.xiaomi.push.eh;
import com.xiaomi.push.ej;
import com.xiaomi.push.ho;
import com.xiaomi.push.ht;
import com.xiaomi.push.ii;
import com.xiaomi.push.it;
import com.xiaomi.push.iu;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class o {
    public static void a(Context context, Intent intent, Uri uri) {
        eh ehVarA;
        ej ejVar;
        if (context == null) {
            return;
        }
        an.a(context).m54a();
        if (eh.a(context.getApplicationContext()).m289a() == null) {
            eh.a(context.getApplicationContext()).a(b.m63a(context.getApplicationContext()).m64a(), context.getPackageName(), com.xiaomi.push.service.aq.a(context.getApplicationContext()).a(ho.AwakeInfoUploadWaySwitch.a(), 0), new c());
            com.xiaomi.push.service.aq.a(context).a(new q(102, "awake online config", context));
        }
        if ((context instanceof Activity) && intent != null) {
            ehVarA = eh.a(context.getApplicationContext());
            ejVar = ej.ACTIVITY;
        } else {
            if (!(context instanceof Service) || intent == null) {
                if (uri == null || TextUtils.isEmpty(uri.toString())) {
                    return;
                }
                eh.a(context.getApplicationContext()).a(ej.PROVIDER, context, (Intent) null, uri.toString());
                return;
            }
            if ("com.xiaomi.mipush.sdk.WAKEUP".equals(intent.getAction())) {
                ehVarA = eh.a(context.getApplicationContext());
                ejVar = ej.SERVICE_COMPONENT;
            } else {
                ehVarA = eh.a(context.getApplicationContext());
                ejVar = ej.SERVICE_ACTION;
            }
        }
        ehVarA.a(ejVar, context, intent, (String) null);
    }

    private static void a(Context context, ii iiVar) {
        boolean zA = com.xiaomi.push.service.aq.a(context).a(ho.AwakeAppPingSwitch.a(), false);
        int iA = com.xiaomi.push.service.aq.a(context).a(ho.AwakeAppPingFrequency.a(), 0);
        if (iA >= 0 && iA < 30) {
            com.xiaomi.channel.commonutils.logger.b.c("aw_ping: frquency need > 30s.");
            iA = 30;
        }
        if (iA < 0) {
            zA = false;
        }
        if (!com.xiaomi.push.l.m547a()) {
            a(context, iiVar, zA, iA);
        } else if (zA) {
            com.xiaomi.push.ai.a(context.getApplicationContext()).a((ai.a) new p(iiVar, context), iA);
        }
    }

    public static final <T extends iu<T, ?>> void a(Context context, T t, boolean z, int i) {
        byte[] bArrA = it.a(t);
        if (bArrA == null) {
            com.xiaomi.channel.commonutils.logger.b.m22a("send message fail, because msgBytes is null.");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("action_help_ping");
        intent.putExtra("extra_help_ping_switch", z);
        intent.putExtra("extra_help_ping_frequency", i);
        intent.putExtra("mipush_payload", bArrA);
        intent.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
        an.a(context).m55a(intent);
    }

    public static void a(Context context, String str) {
        com.xiaomi.channel.commonutils.logger.b.m22a("aw_ping : send aw_ping cmd and content to push service from 3rd app");
        HashMap map = new HashMap();
        map.put("awake_info", str);
        map.put("event_type", String.valueOf(9999));
        map.put(TokenConstants.ERROR_MSG, "ping message");
        ii iiVar = new ii();
        iiVar.b(b.m63a(context).m64a());
        iiVar.d(context.getPackageName());
        iiVar.c(ht.AwakeAppResponse.f509a);
        iiVar.a(com.xiaomi.push.service.at.a());
        iiVar.f650a = map;
        a(context, iiVar);
    }

    public static void a(Context context, String str, int i, String str2) {
        ii iiVar = new ii();
        iiVar.b(str);
        iiVar.a(new HashMap());
        iiVar.m477a().put("extra_aw_app_online_cmd", String.valueOf(i));
        iiVar.m477a().put("extra_help_aw_info", str2);
        iiVar.a(com.xiaomi.push.service.at.a());
        byte[] bArrA = it.a(iiVar);
        if (bArrA == null) {
            com.xiaomi.channel.commonutils.logger.b.m22a("send message fail, because msgBytes is null.");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("action_aw_app_logic");
        intent.putExtra("mipush_payload", bArrA);
        an.a(context).m55a(intent);
    }
}
