package com.bytedance.sdk.adtnc.f;

import android.content.SharedPreferences;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d extends a {

    /* renamed from: c, reason: collision with root package name */
    private int f5112c;
    private long d;

    public d(com.bytedance.sdk.adtnc.sdk.b.a aVar) {
        super(aVar);
        this.f5112c = 0;
        this.d = 19700101000L;
        a();
    }

    private void a() {
        SharedPreferences sharedPreferences = this.f5108a.a().getSharedPreferences(com.bytedance.sdk.adtnc.g.a.a(this.f5108a.c()), 0);
        this.f5112c = sharedPreferences.getInt("tnc_probe_cmd", this.f5112c);
        this.d = sharedPreferences.getLong("tnc_probe_version", this.d);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0064 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.bytedance.sdk.adtnc.sdk.a.b r8, com.bytedance.sdk.adtnc.a.a r9) throws java.lang.NumberFormatException {
        /*
            r7 = this;
            if (r8 != 0) goto Lc
            java.lang.String r8 = "NetHeaderStrategy"
            java.lang.String r9 = "handleTncProbe"
            java.lang.String r0 = "response null"
        L8:
            com.bytedance.sdk.adtnc.g.b.a(r8, r9, r0)
            return
        Lc:
            if (r9 != 0) goto L15
            java.lang.String r8 = "NetHeaderStrategy"
            java.lang.String r9 = "handleTncProbe"
            java.lang.String r0 = "config null"
            goto L8
        L15:
            java.lang.String r9 = "tnc-cmd"
            r0 = 0
            java.lang.String r8 = r8.a(r9, r0)
            java.lang.String r9 = "NetHeaderStrategy"
            java.lang.String r0 = "handleTncProbe"
            java.lang.String r1 = "probe header"
            com.bytedance.sdk.adtnc.g.b.a(r9, r0, r1, r8)
            boolean r9 = android.text.TextUtils.isEmpty(r8)
            if (r9 == 0) goto L32
            java.lang.String r8 = "NetHeaderStrategy"
            java.lang.String r9 = "handleTncProbe"
            java.lang.String r0 = "probe header is empty"
            goto L8
        L32:
            java.lang.String r9 = "@"
            java.lang.String[] r8 = r8.split(r9)
            if (r8 == 0) goto La1
            int r9 = r8.length
            r0 = 2
            if (r9 == r0) goto L3f
            goto La1
        L3f:
            r0 = 0
            r9 = 1
            r2 = 0
            r3 = r8[r2]     // Catch: java.lang.Throwable -> L53
            int r3 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.Throwable -> L53
            r8 = r8[r9]     // Catch: java.lang.Throwable -> L51
            long r4 = java.lang.Long.parseLong(r8)     // Catch: java.lang.Throwable -> L51
            r0 = r4
            goto L5e
        L51:
            r8 = move-exception
            goto L55
        L53:
            r8 = move-exception
            r3 = 0
        L55:
            java.lang.String r4 = "NetHeaderStrategy"
            java.lang.String r5 = "handleTncProbe"
            java.lang.String r6 = "probeProto except"
            com.bytedance.sdk.adtnc.g.b.a(r4, r5, r6, r8)
        L5e:
            long r4 = r7.d
            int r8 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r8 > 0) goto L65
            return
        L65:
            r7.f5112c = r3
            r7.d = r0
            com.bytedance.sdk.adtnc.sdk.b.a r8 = r7.f5108a
            android.content.Context r8 = r8.a()
            com.bytedance.sdk.adtnc.sdk.b.a r4 = r7.f5108a
            java.lang.String r4 = r4.c()
            java.lang.String r4 = com.bytedance.sdk.adtnc.g.a.a(r4)
            android.content.SharedPreferences r8 = r8.getSharedPreferences(r4, r2)
            android.content.SharedPreferences$Editor r8 = r8.edit()
            java.lang.String r2 = "tnc_probe_cmd"
            android.content.SharedPreferences$Editor r8 = r8.putInt(r2, r3)
            java.lang.String r2 = "tnc_probe_version"
            android.content.SharedPreferences$Editor r8 = r8.putLong(r2, r0)
            r8.apply()
            r8 = 10000(0x2710, float:1.4013E-41)
            if (r8 != r3) goto La0
            java.lang.String r8 = "NetHeaderStrategy"
            java.lang.String r0 = "handleTncProbe"
            java.lang.String r1 = "strategy trigger"
            com.bytedance.sdk.adtnc.g.b.a(r8, r0, r1)
            r7.a(r9)
        La0:
            return
        La1:
            java.lang.String r8 = "NetHeaderStrategy"
            java.lang.String r9 = "handleTncProbe"
            java.lang.String r0 = "probeProto length error"
            goto L8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.adtnc.f.d.a(com.bytedance.sdk.adtnc.sdk.a.b, com.bytedance.sdk.adtnc.a.a):void");
    }
}
