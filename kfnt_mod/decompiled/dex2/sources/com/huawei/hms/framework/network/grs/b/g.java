package com.huawei.hms.framework.network.grs.b;

import android.content.Context;
import com.huawei.hms.framework.common.Logger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g extends b implements Callable<d> {
    private static final String b = "g";

    public g(String str, int i, a aVar, Context context, String str2) {
        super(str, i, aVar, context, str2);
    }

    private String h() throws JSONException {
        HashSet hashSet = new HashSet();
        com.huawei.hms.framework.network.grs.local.model.a aVarA = com.huawei.hms.framework.network.grs.local.b.a(d().getPackageName()).a();
        if (aVarA == null) {
            Logger.w(b, "get local assets appGrs is null.");
            return "";
        }
        Map<String, com.huawei.hms.framework.network.grs.local.model.c> mapB = aVarA.b();
        if (mapB == null || mapB.isEmpty()) {
            return "";
        }
        Iterator<Map.Entry<String, com.huawei.hms.framework.network.grs.local.model.c>> it = mapB.entrySet().iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getValue().a());
        }
        if (hashSet.isEmpty()) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        Iterator it2 = hashSet.iterator();
        while (it2.hasNext()) {
            jSONArray.put((String) it2.next());
        }
        try {
            jSONObject.put("services", jSONArray);
            Logger.v(b, "post service list is:%s,and appName is:%s", jSONObject.toString(), aVarA.a());
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00d0  */
    @Override // java.util.concurrent.Callable
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.hms.framework.network.grs.b.d call() throws java.lang.Throwable {
        /*
            r17 = this;
            r1 = r17
            java.lang.String r0 = com.huawei.hms.framework.network.grs.b.g.b
            java.lang.String r2 = "Post call execute"
            com.huawei.hms.framework.common.Logger.i(r0, r2)
            r2 = 0
            long r4 = android.os.SystemClock.elapsedRealtime()     // Catch: java.io.IOException -> L93
            long r6 = java.lang.System.currentTimeMillis()     // Catch: java.io.IOException -> L90
            java.lang.String r0 = r17.a()     // Catch: java.io.IOException -> L8e
            android.content.Context r2 = r17.d()     // Catch: java.io.IOException -> L8e
            java.lang.String r3 = r17.e()     // Catch: java.io.IOException -> L8e
            javax.net.ssl.HttpsURLConnection r0 = com.huawei.hms.framework.network.grs.c.a.a.a(r0, r2, r3)     // Catch: java.io.IOException -> L8e
            r2 = 0
            if (r0 != 0) goto L2e
            java.lang.String r0 = com.huawei.hms.framework.network.grs.b.g.b     // Catch: java.io.IOException -> L8e
            java.lang.String r3 = "create HttpsURLConnection instance by url return null."
            com.huawei.hms.framework.common.Logger.w(r0, r3)     // Catch: java.io.IOException -> L8e
            return r2
        L2e:
            java.lang.String r3 = "Content-Type"
            java.lang.String r8 = "application/json; charset=UTF-8"
            r0.setRequestProperty(r3, r8)     // Catch: java.io.IOException -> L8e
            java.lang.String r3 = "POST"
            r0.setRequestMethod(r3)     // Catch: java.io.IOException -> L8e
            r3 = 1
            r0.setDoOutput(r3)     // Catch: java.io.IOException -> L8e
            r0.setDoInput(r3)     // Catch: java.io.IOException -> L8e
            r0.connect()     // Catch: java.io.IOException -> L8e
            java.io.OutputStream r3 = r0.getOutputStream()     // Catch: java.io.IOException -> L8e
            java.lang.String r8 = r17.h()     // Catch: java.io.IOException -> L8e
            byte[] r8 = r8.getBytes()     // Catch: java.io.IOException -> L8e
            r3.write(r8)     // Catch: java.io.IOException -> L8e
            r3.close()     // Catch: java.io.IOException -> L8e
            int r10 = r0.getResponseCode()     // Catch: java.io.IOException -> L8e
            r3 = 200(0xc8, float:2.8E-43)
            if (r10 != r3) goto L72
            java.io.InputStream r3 = r0.getInputStream()     // Catch: java.lang.Throwable -> L6d
            byte[] r2 = com.huawei.hms.framework.common.IoUtils.toByteArray(r3)     // Catch: java.lang.Throwable -> L6a
            com.huawei.hms.framework.common.IoUtils.closeSecure(r3)     // Catch: java.io.IOException -> L8e
            goto L72
        L6a:
            r0 = move-exception
            r2 = r3
            goto L6e
        L6d:
            r0 = move-exception
        L6e:
            com.huawei.hms.framework.common.IoUtils.closeSecure(r2)     // Catch: java.io.IOException -> L8e
            throw r0     // Catch: java.io.IOException -> L8e
        L72:
            r12 = r2
            java.util.Map r11 = r0.getHeaderFields()     // Catch: java.io.IOException -> L8e
            r0.disconnect()     // Catch: java.io.IOException -> L8e
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch: java.io.IOException -> L8e
            long r15 = java.lang.System.currentTimeMillis()     // Catch: java.io.IOException -> L8e
            com.huawei.hms.framework.network.grs.b.d r0 = new com.huawei.hms.framework.network.grs.b.d     // Catch: java.io.IOException -> L8e
            r8 = 0
            long r13 = r2 - r4
            r9 = r0
            r9.<init>(r10, r11, r12, r13)     // Catch: java.io.IOException -> L8e
            r1.a = r0     // Catch: java.io.IOException -> L8e
            goto Lad
        L8e:
            r0 = move-exception
            goto L96
        L90:
            r0 = move-exception
            r6 = r2
            goto L96
        L93:
            r0 = move-exception
            r4 = r2
            r6 = r4
        L96:
            long r2 = android.os.SystemClock.elapsedRealtime()
            long r15 = java.lang.System.currentTimeMillis()
            java.lang.String r8 = com.huawei.hms.framework.network.grs.b.g.b
            java.lang.String r9 = "RequestCallableV2 run task catch IOException"
            com.huawei.hms.framework.common.Logger.w(r8, r9, r0)
            com.huawei.hms.framework.network.grs.b.d r8 = new com.huawei.hms.framework.network.grs.b.d
            long r2 = r2 - r4
            r8.<init>(r0, r2)
            r1.a = r8
        Lad:
            r2 = r15
            com.huawei.hms.framework.network.grs.b.d r0 = r1.a
            java.lang.String r4 = r17.a()
            r0.a(r4)
            com.huawei.hms.framework.network.grs.b.d r0 = r1.a
            int r4 = r17.c()
            r0.a(r4)
            com.huawei.hms.framework.network.grs.b.d r0 = r1.a
            r0.a(r6)
            com.huawei.hms.framework.network.grs.b.d r0 = r1.a
            r0.b(r2)
            com.huawei.hms.framework.network.grs.b.a r0 = r17.b()
            if (r0 == 0) goto Ld9
            com.huawei.hms.framework.network.grs.b.a r0 = r17.b()
            com.huawei.hms.framework.network.grs.b.d r2 = r1.a
            r0.a(r2)
        Ld9:
            com.huawei.hms.framework.network.grs.b.d r0 = r1.a
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.b.g.call():com.huawei.hms.framework.network.grs.b.d");
    }
}
