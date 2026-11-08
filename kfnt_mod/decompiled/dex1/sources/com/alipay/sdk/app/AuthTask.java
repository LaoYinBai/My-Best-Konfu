package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alipay.sdk.data.a;
import com.alipay.sdk.sys.a;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.i;
import com.alipay.sdk.util.k;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AuthTask {

    /* renamed from: a, reason: collision with root package name */
    static final Object f2526a = e.class;

    /* renamed from: b, reason: collision with root package name */
    private Activity f2527b;

    /* renamed from: c, reason: collision with root package name */
    private com.alipay.sdk.widget.a f2528c;

    public AuthTask(Activity activity) {
        this.f2527b = activity;
        com.alipay.sdk.sys.b.a().a(this.f2527b);
        this.f2528c = new com.alipay.sdk.widget.a(activity, com.alipay.sdk.widget.a.f2676c);
    }

    private e.c a() {
        return new e.c() { // from class: com.alipay.sdk.app.AuthTask.1
            @Override // com.alipay.sdk.util.e.c
            public void a() {
            }

            @Override // com.alipay.sdk.util.e.c
            public void b() {
                AuthTask.this.c();
            }
        };
    }

    private void b() {
        if (this.f2528c != null) {
            this.f2528c.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.f2528c != null) {
            this.f2528c.c();
        }
    }

    public synchronized Map<String, String> authV2(String str, boolean z) {
        com.alipay.sdk.sys.a aVar;
        aVar = new com.alipay.sdk.sys.a(this.f2527b, str, "authV2");
        return i.a(aVar, innerAuth(aVar, str, z));
    }

    public synchronized String auth(String str, boolean z) {
        return innerAuth(new com.alipay.sdk.sys.a(this.f2527b, str, "auth"), str, z);
    }

    public synchronized String innerAuth(com.alipay.sdk.sys.a aVar, String str, boolean z) {
        String strC;
        if (z) {
            try {
                b();
            } catch (Throwable th) {
                throw th;
            }
        }
        com.alipay.sdk.sys.b.a().a(this.f2527b);
        strC = b.c();
        a.a("");
        try {
            try {
                String strA = a(this.f2527b, str, aVar);
                com.alipay.sdk.app.statistic.a.b(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.P, "" + SystemClock.elapsedRealtime());
                com.alipay.sdk.app.statistic.a.b(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.Q, i.a(strA, i.f2663a) + "|" + i.a(strA, i.f2664b));
                if (!com.alipay.sdk.data.a.p().n()) {
                    com.alipay.sdk.data.a.p().a(aVar, this.f2527b);
                }
                c();
                com.alipay.sdk.app.statistic.a.b(this.f2527b, aVar, str, aVar.q);
                strC = strA;
            } catch (Throwable th2) {
                com.alipay.sdk.app.statistic.a.b(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.P, "" + SystemClock.elapsedRealtime());
                com.alipay.sdk.app.statistic.a.b(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.Q, i.a(strC, i.f2663a) + "|" + i.a(strC, i.f2664b));
                if (!com.alipay.sdk.data.a.p().n()) {
                    com.alipay.sdk.data.a.p().a(aVar, this.f2527b);
                }
                c();
                com.alipay.sdk.app.statistic.a.b(this.f2527b, aVar, str, aVar.q);
                throw th2;
            }
        } catch (Exception e) {
            com.alipay.sdk.util.c.a(e);
            com.alipay.sdk.app.statistic.a.b(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.P, "" + SystemClock.elapsedRealtime());
            com.alipay.sdk.app.statistic.a.b(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.Q, i.a(strC, i.f2663a) + "|" + i.a(strC, i.f2664b));
            if (!com.alipay.sdk.data.a.p().n()) {
                com.alipay.sdk.data.a.p().a(aVar, this.f2527b);
            }
            c();
            com.alipay.sdk.app.statistic.a.b(this.f2527b, aVar, str, aVar.q);
        }
        return strC;
    }

    private String a(Activity activity, String str, com.alipay.sdk.sys.a aVar) {
        String strA = aVar.a(str);
        List<a.C0078a> listO = com.alipay.sdk.data.a.p().o();
        if (!com.alipay.sdk.data.a.p().f2585a || listO == null) {
            listO = a.f2557a;
        }
        if (k.b(aVar, this.f2527b, listO)) {
            String strA2 = new e(activity, aVar, a()).a(strA);
            if (!TextUtils.equals(strA2, e.f2649a) && !TextUtils.equals(strA2, e.f2650b)) {
                return TextUtils.isEmpty(strA2) ? b.c() : strA2;
            }
            com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.ac);
            return b(activity, strA, aVar);
        }
        com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.ad);
        return b(activity, strA, aVar);
    }

    private String b(Activity activity, String str, com.alipay.sdk.sys.a aVar) {
        c cVarB;
        b();
        try {
            try {
                List<com.alipay.sdk.protocol.b> listA = com.alipay.sdk.protocol.b.a(new com.alipay.sdk.packet.impl.a().a(aVar, activity, str).c().optJSONObject(com.alipay.sdk.cons.c.f2582c).optJSONObject(com.alipay.sdk.cons.c.d));
                c();
                for (int i = 0; i < listA.size(); i++) {
                    if (listA.get(i).b() == com.alipay.sdk.protocol.a.WapPay) {
                        return a(aVar, listA.get(i));
                    }
                }
            } catch (IOException e) {
                c cVarB2 = c.b(c.NETWORK_ERROR.a());
                com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2571a, e);
                c();
                cVarB = cVarB2;
            } catch (Throwable th) {
                com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.w, th);
            }
            c();
            cVarB = null;
            if (cVarB == null) {
                cVarB = c.b(c.FAILED.a());
            }
            return b.a(cVarB.a(), cVarB.b(), "");
        } finally {
            c();
        }
    }

    private String a(com.alipay.sdk.sys.a aVar, com.alipay.sdk.protocol.b bVar) {
        String[] strArrC = bVar.c();
        Bundle bundle = new Bundle();
        bundle.putString("url", strArrC[0]);
        Intent intent = new Intent(this.f2527b, (Class<?>) H5AuthActivity.class);
        intent.putExtras(bundle);
        a.C0080a.a(aVar, intent);
        this.f2527b.startActivity(intent);
        synchronized (f2526a) {
            try {
                f2526a.wait();
            } catch (InterruptedException unused) {
                return b.c();
            }
        }
        String strA = b.a();
        return TextUtils.isEmpty(strA) ? b.c() : strA;
    }
}
