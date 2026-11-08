package com.huawei.hms.framework.network.grs.b;

import android.content.Context;
import java.util.concurrent.Callable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f extends b implements Callable<d> {
    private static final String b = "f";

    public f(String str, int i, a aVar, Context context, String str2) {
        super(str, i, aVar, context, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00af  */
    @Override // java.util.concurrent.Callable
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.hms.framework.network.grs.b.d call() throws java.lang.Throwable {
        /*
            r16 = this;
            r1 = r16
            java.lang.String r0 = com.huawei.hms.framework.network.grs.b.f.b
            java.lang.String r2 = "Get call execute"
            com.huawei.hms.framework.common.Logger.i(r0, r2)
            r2 = 0
            long r4 = android.os.SystemClock.elapsedRealtime()     // Catch: java.io.IOException -> L73
            long r6 = java.lang.System.currentTimeMillis()     // Catch: java.io.IOException -> L70
            java.lang.String r0 = r16.a()     // Catch: java.io.IOException -> L6e
            android.content.Context r2 = r16.d()     // Catch: java.io.IOException -> L6e
            java.lang.String r3 = r16.e()     // Catch: java.io.IOException -> L6e
            javax.net.ssl.HttpsURLConnection r0 = com.huawei.hms.framework.network.grs.c.a.a.a(r0, r2, r3)     // Catch: java.io.IOException -> L6e
            r2 = 0
            if (r0 != 0) goto L2e
            java.lang.String r0 = com.huawei.hms.framework.network.grs.b.f.b     // Catch: java.io.IOException -> L6e
            java.lang.String r3 = "create HttpsURLConnection instance by url return null."
            com.huawei.hms.framework.common.Logger.w(r0, r3)     // Catch: java.io.IOException -> L6e
            return r2
        L2e:
            java.lang.String r3 = "GET"
            r0.setRequestMethod(r3)     // Catch: java.io.IOException -> L6e
            r0.connect()     // Catch: java.io.IOException -> L6e
            int r9 = r0.getResponseCode()     // Catch: java.io.IOException -> L6e
            r3 = 200(0xc8, float:2.8E-43)
            if (r9 != r3) goto L52
            java.io.InputStream r3 = r0.getInputStream()     // Catch: java.lang.Throwable -> L4d
            byte[] r2 = com.huawei.hms.framework.common.IoUtils.toByteArray(r3)     // Catch: java.lang.Throwable -> L4a
            com.huawei.hms.framework.common.IoUtils.closeSecure(r3)     // Catch: java.io.IOException -> L6e
            goto L52
        L4a:
            r0 = move-exception
            r2 = r3
            goto L4e
        L4d:
            r0 = move-exception
        L4e:
            com.huawei.hms.framework.common.IoUtils.closeSecure(r2)     // Catch: java.io.IOException -> L6e
            throw r0     // Catch: java.io.IOException -> L6e
        L52:
            r11 = r2
            java.util.Map r10 = r0.getHeaderFields()     // Catch: java.io.IOException -> L6e
            r0.disconnect()     // Catch: java.io.IOException -> L6e
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch: java.io.IOException -> L6e
            long r14 = java.lang.System.currentTimeMillis()     // Catch: java.io.IOException -> L6e
            com.huawei.hms.framework.network.grs.b.d r0 = new com.huawei.hms.framework.network.grs.b.d     // Catch: java.io.IOException -> L6e
            r8 = 0
            long r12 = r2 - r4
            r8 = r0
            r8.<init>(r9, r10, r11, r12)     // Catch: java.io.IOException -> L6e
            r1.a = r0     // Catch: java.io.IOException -> L6e
            goto L8d
        L6e:
            r0 = move-exception
            goto L76
        L70:
            r0 = move-exception
            r6 = r2
            goto L76
        L73:
            r0 = move-exception
            r4 = r2
            r6 = r4
        L76:
            long r2 = android.os.SystemClock.elapsedRealtime()
            long r14 = java.lang.System.currentTimeMillis()
            java.lang.String r8 = com.huawei.hms.framework.network.grs.b.f.b
            java.lang.String r9 = "RequestCallable run task catch IOException"
            com.huawei.hms.framework.common.Logger.w(r8, r9, r0)
            com.huawei.hms.framework.network.grs.b.d r8 = new com.huawei.hms.framework.network.grs.b.d
            long r2 = r2 - r4
            r8.<init>(r0, r2)
            r1.a = r8
        L8d:
            com.huawei.hms.framework.network.grs.b.d r0 = r1.a
            java.lang.String r2 = r16.a()
            r0.a(r2)
            com.huawei.hms.framework.network.grs.b.d r0 = r1.a
            int r2 = r16.c()
            r0.a(r2)
            com.huawei.hms.framework.network.grs.b.d r0 = r1.a
            r0.a(r6)
            com.huawei.hms.framework.network.grs.b.d r0 = r1.a
            r0.b(r14)
            com.huawei.hms.framework.network.grs.b.a r0 = r16.b()
            if (r0 == 0) goto Lb8
            com.huawei.hms.framework.network.grs.b.a r0 = r16.b()
            com.huawei.hms.framework.network.grs.b.d r2 = r1.a
            r0.a(r2)
        Lb8:
            com.huawei.hms.framework.network.grs.b.d r0 = r1.a
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.b.f.call():com.huawei.hms.framework.network.grs.b.d");
    }
}
