package com.huawei.hms.support.api.push.b.c;

import android.content.Context;
import android.content.Intent;
import com.huawei.hms.support.log.HMSLog;
import java.net.URISyntaxException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d extends Thread {
    private Context a;
    private com.huawei.hms.support.api.push.b.b.a b;
    private String c;

    public d(Context context, com.huawei.hms.support.api.push.b.b.a aVar, String str) {
        this.a = context;
        this.b = aVar;
        this.c = str;
    }

    private static Intent a(Context context, com.huawei.hms.support.api.push.b.b.a aVar) throws URISyntaxException {
        if (aVar == null) {
            return null;
        }
        Intent intentB = com.huawei.hms.support.api.push.b.d.a.b(context, aVar.o());
        if (aVar.g() != null) {
            try {
                Intent uri = Intent.parseUri(aVar.g(), 0);
                HMSLog.d("PushSelfShowLog", "Intent.parseUri(msg.intentUri, 0)，" + uri.toURI());
                return com.huawei.hms.support.api.push.b.d.a.a(context, aVar.o(), uri).booleanValue() ? uri : intentB;
            } catch (Exception e) {
                HMSLog.w("PushSelfShowLog", "intentUri error" + e.toString());
                return intentB;
            }
        }
        if (aVar.p() != null) {
            Intent intent = new Intent(aVar.p());
            if (com.huawei.hms.support.api.push.b.d.a.a(context, aVar.o(), intent).booleanValue()) {
                intentB = intent;
            }
        }
        intentB.setPackage(aVar.o());
        return intentB;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        HMSLog.i("PushSelfShowLog", "enter run()");
        try {
            if (!a(this.a) || b(this.a, this.b)) {
                return;
            }
            c.a(this.a, this.b, this.c);
        } catch (Exception e) {
            HMSLog.e("PushSelfShowLog", e.toString());
        }
    }

    private boolean a(Context context) {
        if ("cosa".equals(this.b.j())) {
            return b(context);
        }
        return true;
    }

    private boolean b(Context context) {
        return com.huawei.hms.support.api.push.b.d.a.c(context, this.b.o());
    }

    private boolean b(Context context, com.huawei.hms.support.api.push.b.b.a aVar) throws URISyntaxException {
        boolean z = false;
        if (!"cosa".equals(aVar.j())) {
            return false;
        }
        Intent intentA = a(context, aVar);
        if (intentA == null) {
            HMSLog.d("PushSelfShowLog", "launchCosaApp,intent == null");
            z = true;
        }
        if (com.huawei.hms.support.api.push.b.d.a.a(context, intentA)) {
            return z;
        }
        HMSLog.i("PushSelfShowLog", "no permission to start activity");
        return true;
    }
}
