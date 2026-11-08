package com.alibaba.sdk.android.httpdns;

import com.alibaba.sdk.android.httpdns.probe.IPProbeItem;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class f {

    /* renamed from: a, reason: collision with other field name */
    static String f20a;

    /* renamed from: b, reason: collision with other field name */
    static String[] f22b = {"203.107.1.1"};

    /* renamed from: c, reason: collision with root package name */
    static final String[] f2371c = {"203.107.1.97", "203.107.1.100", "httpdns-sc.aliyuncs.com"};
    static final String[] d = new String[0];

    /* renamed from: b, reason: collision with root package name */
    static String f2370b = "80";
    static String PROTOCOL = BDAccountNetApi.Platform.PROJECT_MODE_SCHEME;

    /* renamed from: a, reason: collision with root package name */
    static int f2369a = 15000;

    /* renamed from: a, reason: collision with other field name */
    static List<IPProbeItem> f21a = null;

    static synchronized void a(List<IPProbeItem> list) {
        f21a = list;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0010  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static synchronized boolean a(java.lang.String[] r2) {
        /*
            java.lang.Class<com.alibaba.sdk.android.httpdns.f> r0 = com.alibaba.sdk.android.httpdns.f.class
            monitor-enter(r0)
            if (r2 == 0) goto L10
            int r1 = r2.length     // Catch: java.lang.Throwable -> Ld
            if (r1 == 0) goto L10
            com.alibaba.sdk.android.httpdns.f.f22b = r2     // Catch: java.lang.Throwable -> Ld
            r2 = 1
        Lb:
            monitor-exit(r0)
            return r2
        Ld:
            r2 = move-exception
            monitor-exit(r0)
            throw r2
        L10:
            r2 = 0
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.f.a(java.lang.String[]):boolean");
    }

    static synchronized void c(String str) {
        f20a = str;
    }

    static synchronized void setHTTPSRequestEnabled(boolean z) {
        String str;
        try {
            if (z) {
                PROTOCOL = "https://";
                str = "443";
            } else {
                PROTOCOL = BDAccountNetApi.Platform.PROJECT_MODE_SCHEME;
                str = "80";
            }
            f2370b = str;
        } catch (Throwable th) {
            throw th;
        }
    }

    static synchronized void setTimeoutInterval(int i) {
        if (i > 0) {
            f2369a = i;
        }
    }
}
