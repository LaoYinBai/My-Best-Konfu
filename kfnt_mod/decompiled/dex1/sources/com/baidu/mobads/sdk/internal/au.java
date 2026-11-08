package com.baidu.mobads.sdk.internal;

import android.text.TextUtils;
import com.baidu.mobads.sdk.api.IOAdEvent;
import com.baidu.mobads.sdk.internal.ar;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class au implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ IOAdEvent f3130a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ ar.a f3131b;

    au(ar.a aVar, IOAdEvent iOAdEvent) {
        this.f3131b = aVar;
        this.f3130a = iOAdEvent;
    }

    @Override // java.lang.Runnable
    public void run() {
        HashMap map;
        if (this.f3130a == null || TextUtils.isEmpty(this.f3130a.getType())) {
            return;
        }
        String type = this.f3130a.getType();
        if (r.H.equals(type)) {
            ar.this.a(this.f3130a);
            return;
        }
        if (r.K.equals(type)) {
            ar.this.m = this.f3130a.getMessage();
            ar.this.q();
            return;
        }
        if (r.L.equals(type)) {
            ar.this.e(this.f3130a);
            return;
        }
        if (r.M.equals(type)) {
            ar.this.f(this.f3130a);
            return;
        }
        if (r.U.equals(type)) {
            ar.this.g(this.f3130a);
            return;
        }
        int iIntValue = 0;
        int iIntValue2 = 0;
        zEquals = false;
        boolean zEquals = false;
        if (r.p.equals(type)) {
            HashMap map2 = (HashMap) this.f3130a.getData();
            String str = "";
            if (map2 != null) {
                str = (String) map2.get("error_message");
                Object obj = map2.get("error_code");
                Object obj2 = obj;
                if (obj == null) {
                    obj2 = 0;
                }
                iIntValue = ((Integer) obj2).intValue();
            }
            ar.this.a(str, iIntValue);
            return;
        }
        if (r.J.equals(type)) {
            HashMap map3 = (HashMap) this.f3130a.getData();
            String str2 = "";
            if (map3 != null) {
                str2 = (String) map3.get("error_message");
                Object obj3 = map3.get("error_code");
                Object obj4 = obj3;
                if (obj3 == null) {
                    obj4 = 0;
                }
                iIntValue2 = ((Integer) obj4).intValue();
            }
            ar.this.a(iIntValue2, str2);
            return;
        }
        if (r.F.equals(type)) {
            ar.this.h(this.f3130a);
            return;
        }
        if (r.V.equals(type)) {
            ar.this.e();
            return;
        }
        if (r.W.equals(type)) {
            ar.this.d(this.f3130a);
            return;
        }
        if (r.Y.equals(type)) {
            ar.this.s();
            return;
        }
        if (r.Z.equals(type)) {
            if (this.f3130a != null && (map = (HashMap) this.f3130a.getData()) != null) {
                zEquals = "1".equals((String) map.get("serverVerify"));
            }
            ar.this.b(zEquals);
            return;
        }
        if (r.aa.equals(type)) {
            ar.this.d_();
            return;
        }
        if (r.ab.equals(type)) {
            ar.this.c();
            return;
        }
        if (r.I.equals(type)) {
            ar.this.b(this.f3130a);
            return;
        }
        if (r.ac.equals(type)) {
            ar.this.a(this.f3130a.getMessage(), 1 == this.f3130a.getCode());
            return;
        }
        if (r.ad.equals(type)) {
            ar.this.d(this.f3130a.getMessage());
            return;
        }
        if (r.ae.equals(type)) {
            ar.this.c(this.f3130a.getMessage());
            return;
        }
        if (r.B.equals(type)) {
            ar.this.e(this.f3130a.getMessage());
            return;
        }
        if (r.X.equals(type)) {
            ar.this.t();
            return;
        }
        if (r.af.equals(type)) {
            ar.this.b(this.f3130a.getMessage(), 1 == this.f3130a.getCode());
        } else if (r.ah.equals(type)) {
            ar.this.c(this.f3130a);
        } else if (r.ag.equals(type)) {
            ar.this.u();
        }
    }
}
