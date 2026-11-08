package com.huawei.hms.support.api.push.b;

import android.content.Context;
import android.content.Intent;
import com.huawei.hms.support.api.push.b.c.d;
import com.huawei.hms.support.log.HMSLog;
import java.net.URISyntaxException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    public void a(Context context, Intent intent) {
        try {
            if (context == null || intent == null) {
                HMSLog.d("PushSelfShowLog", "enter SelfShowReceiver receiver, context or intent is null");
                return;
            }
            String action = intent.getAction();
            if ("com.huawei.intent.action.PUSH".equals(action) || "com.huawei.push.msg.NOTIFY_MSG".equals(action) || "com.huawei.intent.action.PUSH_DELAY_NOTIFY".equals(action)) {
                byte[] byteArrayExtra = intent.getByteArrayExtra("selfshow_info");
                byte[] byteArrayExtra2 = intent.getByteArrayExtra("selfshow_token");
                if (byteArrayExtra.length != 0 && byteArrayExtra2.length != 0) {
                    String stringExtra = intent.getStringExtra("selfshow_event_id");
                    int intExtra = intent.getIntExtra("selfshow_notify_id", 0);
                    HMSLog.i("PushSelfShowLog", "get notifyId:" + intExtra);
                    a(context, intent, byteArrayExtra, byteArrayExtra2, stringExtra, intExtra);
                    return;
                }
                HMSLog.i("PushSelfShowLog", "self show info or token is null.");
            }
        } catch (RuntimeException unused) {
            HMSLog.d("PushSelfShowLog", "onReceive RuntimeException.");
        } catch (Exception unused2) {
            HMSLog.d("PushSelfShowLog", "onReceive Exception.");
        }
    }

    private void a(Context context, Intent intent, byte[] bArr, byte[] bArr2, String str, int i) throws URISyntaxException {
        com.huawei.hms.support.api.push.b.b.a aVar = new com.huawei.hms.support.api.push.b.b.a(bArr, bArr2);
        if (!aVar.b()) {
            HMSLog.d("PushSelfShowLog", "parseMessage failed");
            return;
        }
        HMSLog.i("PushSelfShowLog", " onReceive the msg id = " + aVar.a() + ",and cmd is" + aVar.j() + ",and the eventId is " + str);
        if (str == null) {
            a(context, intent, aVar);
        } else {
            a(context, intent, str, aVar, i);
        }
    }

    private void a(Context context, Intent intent, com.huawei.hms.support.api.push.b.b.a aVar) {
        HMSLog.i("PushSelfShowLog", "receive a selfshow message ,the type is" + aVar.j());
        if (com.huawei.hms.support.api.push.b.a.a.a(aVar.j())) {
            long jA = com.huawei.hms.support.api.push.b.d.a.a(aVar.h());
            if (jA == 0) {
                new d(context, aVar, intent.getStringExtra("extra_encrypt_data")).start();
                return;
            }
            HMSLog.d("PushSelfShowLog", "waiting ……");
            intent.setPackage(context.getPackageName());
            com.huawei.hms.support.api.push.b.d.a.a(context, intent, jA);
        }
    }

    private void a(Context context, Intent intent, String str, com.huawei.hms.support.api.push.b.b.a aVar, int i) throws URISyntaxException {
        HMSLog.d("PushSelfShowLog", "receive a selfshow userhandle message");
        if (!"-1".equals(str)) {
            com.huawei.hms.support.api.push.b.d.a.b(context, intent);
        } else {
            com.huawei.hms.support.api.push.b.d.a.a(context, i);
        }
        if ("1".equals(str)) {
            new com.huawei.hms.support.api.push.b.a.a(context, aVar).a();
        }
    }
}
