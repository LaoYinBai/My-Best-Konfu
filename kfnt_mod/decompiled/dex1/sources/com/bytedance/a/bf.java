package com.bytedance.a;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import anet.channel.util.HttpConstant;
import com.bytedance.applog.AppLog;
import com.bytedance.applog.network.RangersHttpException;
import com.bytedance.applog.util.SimulateLaunchActivity;
import com.bytedance.sdk.openadsdk.api.plugin.PluginConstants;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bf {

    /* renamed from: b, reason: collision with root package name */
    public static JSONObject f3399b = null;
    public static String f = "https://databyterangers.com.cn";

    /* renamed from: a, reason: collision with root package name */
    public static final String[] f3398a = {"GET", "POST"};

    /* renamed from: c, reason: collision with root package name */
    public static final String[] f3400c = {"aid", "app_version", "tt_data", com.bytedance.b.a.c.h.x};
    public static final String[] d = {"aid", "version_code", "ab_version", "iid", "device_platform"};
    public static final String[] e = {"tt_data", "device_platform"};

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public String f3401a;

        /* renamed from: b, reason: collision with root package name */
        public byte[] f3402b;

        public a(int i) {
        }
    }

    public static String a(int i, String str, HashMap<String, String> map, byte[] bArr, int i2) {
        return a(i, str, map, bArr, 0, i2).f3401a;
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str) || !AppLog.getEncryptAndCompress()) {
            return str;
        }
        Uri uri = Uri.parse(str);
        String query = uri.getQuery();
        ArrayList<Pair> arrayList = new ArrayList();
        for (String str2 : f3400c) {
            String queryParameter = uri.getQueryParameter(str2);
            if (!TextUtils.isEmpty(queryParameter)) {
                arrayList.add(new Pair(str2, queryParameter));
            }
        }
        Uri.Builder builderBuildUpon = uri.buildUpon();
        builderBuildUpon.clearQuery();
        for (Pair pair : arrayList) {
            builderBuildUpon.appendQueryParameter((String) pair.first, (String) pair.second);
        }
        builderBuildUpon.appendQueryParameter("tt_info", new String(Base64.encode(m.a(query), 8)));
        return builderBuildUpon.build().toString();
    }

    public static String a(String str, String[] strArr) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Uri uri = Uri.parse(str);
        HashMap map = new HashMap(strArr.length);
        for (String str2 : strArr) {
            String queryParameter = uri.getQueryParameter(str2);
            if (!TextUtils.isEmpty(queryParameter)) {
                map.put(str2, queryParameter);
            }
        }
        Uri.Builder builderBuildUpon = uri.buildUpon();
        builderBuildUpon.clearQuery();
        for (String str3 : map.keySet()) {
            builderBuildUpon.appendQueryParameter(str3, (String) map.get(str3));
        }
        return builderBuildUpon.build().toString();
    }

    public static HashMap<String, String> a() {
        String str;
        String str2;
        HashMap<String, String> map = new HashMap<>(2);
        if (AppLog.getEncryptAndCompress()) {
            str = "Content-Type";
            str2 = "application/octet-stream;tt-data=a";
        } else {
            str = "Content-Type";
            str2 = com.bytedance.b.a.a.c.e.f3610c;
        }
        map.put(str, str2);
        return map;
    }

    public static JSONObject a(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("aid", str);
        jSONObject.put("os", "Android");
        jSONObject.put("os_version", String.valueOf(Build.VERSION.SDK_INT));
        jSONObject.put(PluginConstants.KEY_SDK_VERSION, "6.3.0");
        jSONObject.put("app_version", str2);
        return jSONObject;
    }

    public static void a(StringBuilder sb, String str, String str2) {
        if (sb == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        sb.append(sb.toString().indexOf(63) < 0 ? "?" : "&");
        sb.append(str);
        sb.append("=");
        sb.append(Uri.encode(str2));
    }

    public static void a(JSONObject jSONObject) throws JSONException {
        try {
            long jOptLong = jSONObject.optLong("server_time");
            if (jOptLong > 0) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("server_time", jOptLong);
                jSONObject2.put("local_time", System.currentTimeMillis() / 1000);
                f3399b = jSONObject2;
            }
        } catch (Exception unused) {
        }
    }

    public static boolean a(int i) {
        return i >= 500 && i < 600;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0191 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(java.lang.String r8, org.json.JSONObject r9) {
        /*
            Method dump skipped, instructions count: 462
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.bf.a(java.lang.String, org.json.JSONObject):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0229 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0248 A[Catch: all -> 0x024b, TRY_ENTER, TryCatch #4 {all -> 0x024b, blocks: (B:94:0x0220, B:99:0x0248, B:100:0x024a), top: B:111:0x0220 }] */
    /* JADX WARN: Type inference failed for: r8v21, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r8v25 */
    /* JADX WARN: Type inference failed for: r8v26 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.bytedance.a.bf.a a(int r5, java.lang.String r6, java.util.HashMap<java.lang.String, java.lang.String> r7, byte[] r8, int r9, int r10) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 601
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.bf.a(int, java.lang.String, java.util.HashMap, byte[], int, int):com.bytedance.a.bf$a");
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int a(java.lang.String[] r10, byte[] r11, com.bytedance.a.az r12) {
        /*
            java.util.HashMap r0 = a()
            int r1 = r10.length
            r2 = 102(0x66, float:1.43E-43)
            r3 = 0
            r4 = 0
            r6 = r3
            r5 = 0
            r7 = 102(0x66, float:1.43E-43)
        Ld:
            if (r5 >= r1) goto L79
            r8 = r10[r5]
            com.bytedance.applog.network.INetworkClient r9 = com.bytedance.applog.AppLog.getNetClient()     // Catch: java.lang.Exception -> L52
            java.lang.String r8 = r9.post(r8, r11, r0)     // Catch: java.lang.Exception -> L52
            boolean r9 = android.text.TextUtils.isEmpty(r8)     // Catch: java.lang.Exception -> L52
            if (r9 != 0) goto L76
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch: java.lang.Exception -> L52
            r9.<init>(r8)     // Catch: java.lang.Exception -> L52
            a(r9)     // Catch: java.lang.Exception -> L50
            java.lang.String r6 = "ss_app_log"
            java.lang.String r8 = "magic_tag"
            java.lang.String r8 = r9.optString(r8)     // Catch: java.lang.Exception -> L50
            boolean r6 = r6.equals(r8)     // Catch: java.lang.Exception -> L50
            if (r6 == 0) goto L4c
            java.lang.String r6 = "success"
            java.lang.String r8 = "message"
            java.lang.String r8 = r9.optString(r8)     // Catch: java.lang.Exception -> L50
            boolean r6 = r6.equals(r8)     // Catch: java.lang.Exception -> L50
            if (r6 == 0) goto L46
            r7 = 200(0xc8, float:2.8E-43)
            goto L7a
        L46:
            r6 = 101(0x65, float:1.42E-43)
            r6 = r9
            r7 = 101(0x65, float:1.42E-43)
            goto L76
        L4c:
            r6 = r9
            r7 = 102(0x66, float:1.43E-43)
            goto L76
        L50:
            r6 = move-exception
            goto L55
        L52:
            r8 = move-exception
            r9 = r6
            r6 = r8
        L55:
            boolean r8 = r6 instanceof com.bytedance.applog.network.RangersHttpException
            if (r8 == 0) goto L70
            com.bytedance.applog.network.RangersHttpException r6 = (com.bytedance.applog.network.RangersHttpException) r6
            int r7 = r6.getResponseCode()
            com.bytedance.applog.InitConfig r6 = r12.f3386b
            boolean r6 = r6.isCongestionControlEnable()
            if (r6 == 0) goto L6e
            boolean r6 = a(r7)
            if (r6 == 0) goto L6e
            goto L7a
        L6e:
            r6 = r9
            goto L76
        L70:
            java.lang.String r8 = "U SHALL NOT PASS!"
            com.bytedance.a.de.a(r8, r6)
            goto L6e
        L76:
            int r5 = r5 + 1
            goto Ld
        L79:
            r9 = r6
        L7a:
            if (r9 == 0) goto Ldc
            r12.b(r9)
            java.lang.String r10 = "blocklist"
            org.json.JSONObject r10 = r9.optJSONObject(r10)
            if (r10 == 0) goto Ldc
            java.lang.String r11 = "v1"
            org.json.JSONArray r11 = r10.optJSONArray(r11)
            if (r11 == 0) goto L94
            int r0 = r11.length()
            goto L95
        L94:
            r0 = 0
        L95:
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>(r0)
            r2 = 0
        L9b:
            if (r2 >= r0) goto Lad
            java.lang.String r5 = r11.optString(r2, r3)
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto Laa
            r1.add(r5)
        Laa:
            int r2 = r2 + 1
            goto L9b
        Lad:
            java.lang.String r11 = "v3"
            org.json.JSONArray r10 = r10.optJSONArray(r11)
            if (r10 == 0) goto Lba
            int r11 = r10.length()
            goto Lbb
        Lba:
            r11 = 0
        Lbb:
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>(r11)
        Lc0:
            if (r4 >= r11) goto Ld2
            java.lang.String r2 = r10.optString(r4, r3)
            boolean r5 = android.text.TextUtils.isEmpty(r2)
            if (r5 != 0) goto Lcf
            r0.add(r2)
        Lcf:
            int r4 = r4 + 1
            goto Lc0
        Ld2:
            java.util.HashSet<java.lang.String> r10 = r12.i
            r10.addAll(r1)
            java.util.HashSet<java.lang.String> r10 = r12.j
            r10.addAll(r0)
        Ldc:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.bf.a(java.lang.String[], byte[], com.bytedance.a.az):int");
    }

    public static boolean a(JSONObject jSONObject, String str) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("header", AppLog.getHeader());
            JSONArray jSONArray = new JSONArray();
            if (jSONObject != null) {
                jSONArray.put(jSONObject);
            }
            jSONObject2.put("event_v3", jSONArray);
        } catch (JSONException unused) {
        }
        HashMap<String, String> mapA = a();
        mapA.put(HttpConstant.COOKIE, str);
        try {
            if (!new JSONObject(a(1, f + "/simulator/mobile/log", mapA, m.a(jSONObject2.toString()), -1)).getJSONObject("data").getBoolean("keep")) {
                AppLog.setRangersEventVerifyEnable(false, str);
            }
            return true;
        } catch (Exception unused2) {
            return false;
        }
    }

    public static JSONObject a(String str, String str2, int i, int i2, String str3, String str4) throws JSONException {
        String strA;
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObjectA = a(str, str2);
            jSONObjectA.put("width", i);
            jSONObjectA.put("height", i2);
            jSONObjectA.put(com.bytedance.b.a.c.h.x, str3);
            jSONObject.put("header", jSONObjectA);
            jSONObject.put(SimulateLaunchActivity.KEY_QR_PARAM, str4);
            try {
                strA = a(1, f + "/simulator/mobile/login", a(), m.a(jSONObject.toString()), -1);
            } catch (RangersHttpException unused) {
                strA = null;
            }
            if (TextUtils.isEmpty(strA)) {
                return null;
            }
            try {
                return new JSONObject(strA);
            } catch (JSONException e2) {
                de.a("U SHALL NOT PASS!", e2);
                return null;
            }
        } catch (JSONException e3) {
            de.a("U SHALL NOT PASS!", e3);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00a1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0033 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.json.JSONObject a(android.os.AsyncTask<?, ?, ?> r7, java.lang.String r8, java.lang.String r9, int r10, int r11, java.lang.String r12) throws org.json.JSONException, java.lang.InterruptedException {
        /*
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            r1 = 0
            org.json.JSONObject r8 = a(r8, r9)     // Catch: org.json.JSONException -> Lc1
            org.json.JSONObject r9 = com.bytedance.applog.AppLog.getHeader()     // Catch: org.json.JSONException -> Lc1
            com.bytedance.a.dj.b(r8, r9)     // Catch: org.json.JSONException -> Lc1
            java.lang.String r9 = "width"
            r8.put(r9, r10)     // Catch: org.json.JSONException -> Lc1
            java.lang.String r9 = "height"
            r8.put(r9, r11)     // Catch: org.json.JSONException -> Lc1
            java.lang.String r9 = "device_id"
            r8.put(r9, r12)     // Catch: org.json.JSONException -> Lc1
            java.lang.String r9 = "device_model"
            java.lang.String r10 = android.os.Build.MODEL     // Catch: org.json.JSONException -> Lc1
            r8.put(r9, r10)     // Catch: org.json.JSONException -> Lc1
            java.lang.String r9 = "header"
            r0.put(r9, r8)     // Catch: org.json.JSONException -> Lc1
            java.util.HashMap r8 = a()
            java.lang.String r9 = ""
            r10 = r1
        L33:
            boolean r11 = r7.isCancelled()
            if (r11 != 0) goto Lad
            long r11 = java.lang.System.currentTimeMillis()
            java.lang.String r2 = "sync_id"
            r0.put(r2, r9)     // Catch: java.lang.Exception -> L90
            r2 = 1
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L90
            r3.<init>()     // Catch: java.lang.Exception -> L90
            java.lang.String r4 = com.bytedance.a.bf.f     // Catch: java.lang.Exception -> L90
            r3.append(r4)     // Catch: java.lang.Exception -> L90
            java.lang.String r4 = "/simulator/limited_mobile/try_link"
            r3.append(r4)     // Catch: java.lang.Exception -> L90
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Exception -> L90
            java.lang.String r4 = r0.toString()     // Catch: java.lang.Exception -> L90
            byte[] r4 = com.bytedance.a.m.a(r4)     // Catch: java.lang.Exception -> L90
            r5 = -1
            java.lang.String r2 = a(r2, r3, r8, r4, r5)     // Catch: java.lang.Exception -> L90
            org.json.JSONObject r10 = new org.json.JSONObject     // Catch: java.lang.Exception -> L8b
            r10.<init>(r2)     // Catch: java.lang.Exception -> L8b
            java.lang.String r3 = "data"
            org.json.JSONObject r3 = r10.getJSONObject(r3)     // Catch: java.lang.Exception -> L8b
            java.lang.String r4 = "retry"
            int r3 = r3.getInt(r4)     // Catch: java.lang.Exception -> L8b
            if (r3 != 0) goto L77
            return r1
        L77:
            r4 = 2
            if (r3 != r4) goto L7c
            r10 = r2
            goto Lad
        L7c:
            java.lang.String r3 = "data"
            org.json.JSONObject r10 = r10.getJSONObject(r3)     // Catch: java.lang.Exception -> L8b
            java.lang.String r3 = "sync_id"
            java.lang.String r10 = r10.getString(r3)     // Catch: java.lang.Exception -> L8b
            r9 = r10
            r10 = r2
            goto L96
        L8b:
            r10 = move-exception
            r6 = r2
            r2 = r10
            r10 = r6
            goto L91
        L90:
            r2 = move-exception
        L91:
            java.lang.String r3 = ""
            com.bytedance.a.de.a(r3, r2)
        L96:
            long r2 = java.lang.System.currentTimeMillis()
            long r2 = r2 - r11
            r11 = 1000(0x3e8, double:4.94E-321)
            int r4 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r4 >= 0) goto L33
            long r11 = r11 - r2
            java.lang.Thread.sleep(r11)     // Catch: java.lang.InterruptedException -> La6
            goto L33
        La6:
            r11 = move-exception
            java.lang.String r12 = ""
            com.bytedance.a.de.a(r12, r11)
            goto L33
        Lad:
            boolean r7 = android.text.TextUtils.isEmpty(r10)
            if (r7 == 0) goto Lb4
            return r1
        Lb4:
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch: org.json.JSONException -> Lba
            r7.<init>(r10)     // Catch: org.json.JSONException -> Lba
            return r7
        Lba:
            r7 = move-exception
            java.lang.String r8 = "U SHALL NOT PASS!"
            com.bytedance.a.de.a(r8, r7)
            return r1
        Lc1:
            r7 = move-exception
            java.lang.String r8 = "U SHALL NOT PASS!"
            com.bytedance.a.de.a(r8, r7)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.bf.a(android.os.AsyncTask, java.lang.String, java.lang.String, int, int, java.lang.String):org.json.JSONObject");
    }
}
