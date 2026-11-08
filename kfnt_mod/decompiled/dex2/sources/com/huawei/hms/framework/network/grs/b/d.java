package com.huawei.hms.framework.network.grs.b;

import android.text.TextUtils;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.framework.common.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    private static final String a = "d";
    private Map<String, List<String>> b;
    private byte[] c;
    private int d;
    private long e;
    private long f;
    private long g;
    private String h;
    private int i;
    private int j;
    private String k;
    private String l;
    private String m;
    private long n;
    private Exception o;
    private String p;
    private int q;

    public d(int i, Map<String, List<String>> map, byte[] bArr, long j) throws JSONException, NumberFormatException {
        this.d = 0;
        this.i = 2;
        this.j = ConnectionResult.RESOLUTION_REQUIRED;
        this.k = "";
        this.l = "";
        this.m = "";
        this.n = 0L;
        this.d = i;
        this.b = map;
        this.c = bArr;
        this.e = j;
        n();
    }

    public d(Exception exc, long j) {
        this.d = 0;
        this.i = 2;
        this.j = ConnectionResult.RESOLUTION_REQUIRED;
        this.k = "";
        this.l = "";
        this.m = "";
        this.n = 0L;
        this.o = exc;
        this.e = j;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(java.util.Map<java.lang.String, java.lang.String> r12) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 251
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.b.d.a(java.util.Map):void");
    }

    private void b(int i) {
        this.i = i;
    }

    private void b(String str) {
        this.l = str;
    }

    private void b(Map<String, String> map) throws NumberFormatException {
        if (map == null || map.size() <= 0) {
            Logger.w(a, "getExpireTime {headers == null} or {headers.size() <= 0}");
            return;
        }
        long j = 0;
        if (map.containsKey("Retry-After")) {
            String str = map.get("Retry-After");
            if (!TextUtils.isEmpty(str)) {
                try {
                    j = Long.parseLong(str);
                } catch (NumberFormatException e) {
                    Logger.w(a, "getRetryAfter addHeadersToResult NumberFormatException", e);
                }
            }
        }
        long j2 = j * 1000;
        Logger.v(a, "convert retry-afterTime{%s}", Long.valueOf(j2));
        c(j2);
    }

    private void c(int i) {
        this.j = i;
    }

    private void c(long j) {
        this.n = j;
    }

    private void c(String str) {
        this.h = str;
    }

    private void d(String str) {
        this.m = str;
    }

    private void e(String str) {
        this.k = str;
    }

    private void n() throws JSONException, NumberFormatException {
        o();
        q();
    }

    private void o() throws NumberFormatException {
        if (h() || i()) {
            Map<String, String> mapP = p();
            try {
                if (h()) {
                    a(mapP);
                }
                if (i()) {
                    b(mapP);
                }
            } catch (JSONException e) {
                Logger.w(a, "parseHeader catch JSONException", e);
            }
        }
    }

    private Map<String, String> p() {
        HashMap map = new HashMap(16);
        if (this.b == null || this.b.size() <= 0) {
            Logger.v(a, "parseRespHeaders {respHeaders == null} or {respHeaders.size() <= 0}");
            return map;
        }
        for (Map.Entry<String, List<String>> entry : this.b.entrySet()) {
            map.put(entry.getKey(), entry.getValue().get(0));
        }
        return map;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0060 A[Catch: JSONException -> 0x00a5, TryCatch #0 {JSONException -> 0x00a5, blocks: (B:6:0x0012, B:9:0x0028, B:14:0x003f, B:16:0x0045, B:23:0x0055, B:25:0x0060, B:27:0x0069, B:29:0x0074, B:31:0x007d, B:32:0x0081, B:34:0x0090, B:36:0x0098, B:38:0x00a1, B:10:0x002d, B:13:0x0038), top: B:43:0x0012 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0074 A[Catch: JSONException -> 0x00a5, TryCatch #0 {JSONException -> 0x00a5, blocks: (B:6:0x0012, B:9:0x0028, B:14:0x003f, B:16:0x0045, B:23:0x0055, B:25:0x0060, B:27:0x0069, B:29:0x0074, B:31:0x007d, B:32:0x0081, B:34:0x0090, B:36:0x0098, B:38:0x00a1, B:10:0x002d, B:13:0x0038), top: B:43:0x0012 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0090 A[Catch: JSONException -> 0x00a5, TryCatch #0 {JSONException -> 0x00a5, blocks: (B:6:0x0012, B:9:0x0028, B:14:0x003f, B:16:0x0045, B:23:0x0055, B:25:0x0060, B:27:0x0069, B:29:0x0074, B:31:0x007d, B:32:0x0081, B:34:0x0090, B:36:0x0098, B:38:0x00a1, B:10:0x002d, B:13:0x0038), top: B:43:0x0012 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void q() throws org.json.JSONException {
        /*
            r6 = this;
            boolean r0 = r6.h()
            r1 = 2
            if (r0 != 0) goto L12
            java.lang.String r0 = com.huawei.hms.framework.network.grs.b.d.a
            java.lang.String r2 = "GRSSDK parse server body all failed."
            com.huawei.hms.framework.common.Logger.i(r0, r2)
            r6.b(r1)
            return
        L12:
            byte[] r0 = r6.c     // Catch: org.json.JSONException -> La5
            java.lang.String r0 = com.huawei.hms.framework.common.StringUtils.byte2Str(r0)     // Catch: org.json.JSONException -> La5
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> La5
            r2.<init>(r0)     // Catch: org.json.JSONException -> La5
            r3 = -1
            java.lang.String r4 = "isSuccess"
            boolean r4 = r2.has(r4)     // Catch: org.json.JSONException -> La5
            if (r4 == 0) goto L2d
            java.lang.String r3 = "isSuccess"
        L28:
            int r3 = r2.getInt(r3)     // Catch: org.json.JSONException -> La5
            goto L3f
        L2d:
            java.lang.String r4 = "resultCode"
            boolean r4 = r2.has(r4)     // Catch: org.json.JSONException -> La5
            if (r4 == 0) goto L38
            java.lang.String r3 = "resultCode"
            goto L28
        L38:
            java.lang.String r4 = com.huawei.hms.framework.network.grs.b.d.a     // Catch: org.json.JSONException -> La5
            java.lang.String r5 = "sth. wrong because server errorcode's key."
            com.huawei.hms.framework.common.Logger.e(r4, r5)     // Catch: org.json.JSONException -> La5
        L3f:
            r6.b(r3)     // Catch: org.json.JSONException -> La5
            r4 = 1
            if (r3 != 0) goto L4f
            java.lang.String r5 = "services"
            boolean r0 = r0.contains(r5)     // Catch: org.json.JSONException -> La5
            if (r0 == 0) goto L4f
            r0 = 1
            goto L50
        L4f:
            r0 = 0
        L50:
            if (r3 == r4) goto L81
            if (r0 == 0) goto L55
            goto L81
        L55:
            r6.b(r1)     // Catch: org.json.JSONException -> La5
            java.lang.String r0 = "errorCode"
            boolean r0 = r2.has(r0)     // Catch: org.json.JSONException -> La5
            if (r0 == 0) goto L67
            java.lang.String r0 = "errorCode"
            int r0 = r2.getInt(r0)     // Catch: org.json.JSONException -> La5
            goto L69
        L67:
            r0 = 9001(0x2329, float:1.2613E-41)
        L69:
            r6.c(r0)     // Catch: org.json.JSONException -> La5
            java.lang.String r0 = "errorDesc"
            boolean r0 = r2.has(r0)     // Catch: org.json.JSONException -> La5
            if (r0 == 0) goto L7b
            java.lang.String r0 = "errorDesc"
            java.lang.String r0 = r2.getString(r0)     // Catch: org.json.JSONException -> La5
            goto L7d
        L7b:
            java.lang.String r0 = ""
        L7d:
            r6.e(r0)     // Catch: org.json.JSONException -> La5
            goto Lb0
        L81:
            java.lang.String r3 = "services"
            org.json.JSONObject r3 = r2.getJSONObject(r3)     // Catch: org.json.JSONException -> La5
            java.lang.String r3 = r3.toString()     // Catch: org.json.JSONException -> La5
            r6.c(r3)     // Catch: org.json.JSONException -> La5
            if (r0 == 0) goto Lb0
            java.lang.String r0 = "errorList"
            boolean r0 = r2.has(r0)     // Catch: org.json.JSONException -> La5
            if (r0 == 0) goto L9f
            java.lang.String r0 = "errorList"
            java.lang.String r0 = r2.getString(r0)     // Catch: org.json.JSONException -> La5
            goto La1
        L9f:
            java.lang.String r0 = ""
        La1:
            r6.b(r0)     // Catch: org.json.JSONException -> La5
            goto Lb0
        La5:
            r0 = move-exception
            java.lang.String r2 = com.huawei.hms.framework.network.grs.b.d.a
            java.lang.String r3 = "GrsResponse GrsResponse(String result) JSONException"
            com.huawei.hms.framework.common.Logger.w(r2, r3, r0)
            r6.b(r1)
        Lb0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.b.d.q():void");
    }

    public long a() {
        return this.f;
    }

    public void a(int i) {
        this.q = i;
    }

    public void a(long j) {
        this.f = j;
    }

    public void a(String str) {
        this.p = str;
    }

    public long b() {
        return this.g;
    }

    public void b(long j) {
        this.g = j;
    }

    public String c() {
        return this.h;
    }

    public String d() {
        return this.m;
    }

    public long e() {
        return this.n;
    }

    public int f() {
        return this.i;
    }

    public int g() {
        return this.j;
    }

    public boolean h() {
        return this.d == 200;
    }

    public boolean i() {
        return this.d == 503;
    }

    public Exception j() {
        return this.o;
    }

    public String k() {
        return this.p;
    }

    public int l() {
        return this.d;
    }

    public long m() {
        return this.e;
    }
}
