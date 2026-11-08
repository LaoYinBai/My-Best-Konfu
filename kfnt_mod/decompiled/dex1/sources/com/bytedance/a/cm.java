package com.bytedance.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class cm extends cw {
    public cm(dg dgVar) {
        super(dgVar);
    }

    @Override // com.bytedance.a.cw
    public boolean a() {
        return true;
    }

    @Override // com.bytedance.a.cw
    public long b() {
        long j = this.f3480a.e.e.getLong("abtest_fetch_interval", 0L);
        if (j < 600000) {
            return 600000L;
        }
        return j;
    }

    @Override // com.bytedance.a.cw
    public long[] c() {
        return dq.g;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a2  */
    @Override // com.bytedance.a.cw
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean d() throws org.json.JSONException {
        /*
            r9 = this;
            com.bytedance.a.dg r0 = r9.f3480a
            com.bytedance.a.az r1 = r0.e
            com.bytedance.a.be r0 = r0.i
            org.json.JSONObject r2 = r0.a()
            int r3 = r0.i()
            r4 = 0
            if (r3 == 0) goto Le2
            if (r2 == 0) goto Le2
            long r2 = java.lang.System.currentTimeMillis()
            org.json.JSONObject r5 = new org.json.JSONObject
            r5.<init>()
            org.json.JSONObject r6 = r0.a()
            java.lang.String r7 = "header"
            r5.put(r7, r6)
            java.lang.String r6 = "magic_tag"
            java.lang.String r7 = "ss_app_log"
            r5.put(r6, r7)
            java.lang.String r6 = "_gen_time"
            r5.put(r6, r2)
            com.bytedance.a.dg r2 = r9.f3480a
            android.app.Application r2 = r2.d
            org.json.JSONObject r3 = r0.a()
            com.bytedance.a.dg r6 = r9.f3480a
            com.bytedance.applog.UriConfig r6 = r6.c()
            java.lang.String r6 = r6.getAbUri()
            com.bytedance.applog.Level r7 = com.bytedance.applog.Level.L1
            r8 = 1
            java.lang.String r2 = com.bytedance.a.bk.a(r2, r3, r6, r8, r7)
            java.lang.String[] r3 = com.bytedance.a.bf.e
            java.lang.String r2 = com.bytedance.a.bf.a(r2, r3)
            r3 = 0
            com.bytedance.applog.network.INetworkClient r6 = com.bytedance.applog.AppLog.getNetClient()     // Catch: java.lang.Exception -> L64
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Exception -> L64
            byte[] r5 = com.bytedance.a.m.a(r5)     // Catch: java.lang.Exception -> L64
            java.lang.String r7 = "application/json; charset=utf-8"
            java.lang.String r2 = r6.post(r2, r5, r7)     // Catch: java.lang.Exception -> L64
            goto L6b
        L64:
            r2 = move-exception
            java.lang.String r5 = ""
            com.bytedance.a.de.a(r5, r2)
            r2 = r3
        L6b:
            if (r2 == 0) goto L80
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch: org.json.JSONException -> L78
            r5.<init>(r2)     // Catch: org.json.JSONException -> L78
            com.bytedance.a.bf.a(r5)     // Catch: org.json.JSONException -> L76
            goto L81
        L76:
            r2 = move-exception
            goto L7a
        L78:
            r2 = move-exception
            r5 = r3
        L7a:
            java.lang.String r6 = ""
            com.bytedance.a.de.a(r6, r2)
            goto L81
        L80:
            r5 = r3
        L81:
            if (r5 == 0) goto L95
            java.lang.String r2 = "message"
            java.lang.String r6 = ""
            java.lang.String r2 = r5.optString(r2, r6)
            java.lang.String r6 = "success"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L95
            r2 = 1
            goto L96
        L95:
            r2 = 0
        L96:
            if (r2 == 0) goto L9f
            java.lang.String r2 = "data"
            org.json.JSONObject r2 = r5.optJSONObject(r2)
            goto La0
        L9f:
            r2 = r3
        La0:
            if (r2 == 0) goto Le2
            org.json.JSONObject r1 = r1.d()
            boolean r1 = com.bytedance.a.dj.a(r1, r2)
            r1 = r1 ^ r8
            boolean r4 = com.bytedance.a.de.f3494c
            if (r4 == 0) goto Lc4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "getAbConfig "
            r4.append(r5)
            r4.append(r2)
        Lbc:
            java.lang.String r4 = r4.toString()
            com.bytedance.a.de.a(r4, r3)
            goto Ld2
        Lc4:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "getAbConfig "
            r4.append(r5)
            r4.append(r1)
            goto Lbc
        Ld2:
            com.bytedance.a.az r3 = r0.d
            r3.a(r2)
            r0.a(r2)
            com.bytedance.a.n r0 = com.bytedance.a.n.a()
            r0.onRemoteAbConfigGet(r1, r2)
            return r8
        Le2:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.cm.d():boolean");
    }

    @Override // com.bytedance.a.cw
    public String e() {
        return "abconfiger";
    }
}
