package b.b.a.i;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import anet.channel.util.HttpConstant;
import com.bytedance.b.a.a.c.e;
import com.bytedance.b.a.c.h;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: b, reason: collision with root package name */
    public static JSONObject f1096b;

    /* renamed from: a, reason: collision with root package name */
    public static final String[] f1095a = {"GET", "POST"};

    /* renamed from: c, reason: collision with root package name */
    public static final String[] f1097c = {"aid", "app_version", "tt_data", h.x};
    public static final String[] d = {"aid", "version_code", "ab_version", "iid", "device_platform"};

    static {
        new String[]{"tt_data", "device_platform"};
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str) || !com.apm.applog.a.f()) {
            return str;
        }
        Uri uri = Uri.parse(str);
        String query = uri.getQuery();
        ArrayList<Pair> arrayList = new ArrayList();
        for (String str2 : f1097c) {
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
        builderBuildUpon.appendQueryParameter("tt_info", new String(Base64.encode(b.b.a.a.b.a(query), 8)));
        return builderBuildUpon.build().toString();
    }

    public static boolean a(int i) {
        return i >= 500 && i < 600;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01b3 A[Catch: all -> 0x01b6, TRY_ENTER, TryCatch #1 {all -> 0x01b6, blocks: (B:72:0x019e, B:76:0x01b3, B:77:0x01b5), top: B:83:0x019e }] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v11, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v2, types: [java.io.Closeable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(int r5, java.lang.String r6, java.util.HashMap<java.lang.String, java.lang.String> r7, byte[] r8) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 452
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.i.a.a(int, java.lang.String, java.util.HashMap, byte[]):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x007a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int a(java.lang.String[] r10, byte[] r11, b.b.a.f.g r12) {
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
            if (r5 >= r1) goto L77
            r8 = r10[r5]
            b.b.a.h.b r9 = com.apm.applog.a.b()     // Catch: java.lang.Exception -> L54
            b.b.a.k.d r9 = (b.b.a.k.d) r9
            java.lang.String r8 = r9.a(r8, r11, r0)     // Catch: java.lang.Exception -> L54
            boolean r9 = android.text.TextUtils.isEmpty(r8)     // Catch: java.lang.Exception -> L54
            if (r9 != 0) goto L74
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch: java.lang.Exception -> L54
            r9.<init>(r8)     // Catch: java.lang.Exception -> L54
            a(r9)     // Catch: java.lang.Exception -> L52
            java.lang.String r6 = "ss_app_log"
            java.lang.String r8 = "magic_tag"
            java.lang.String r8 = r9.optString(r8)     // Catch: java.lang.Exception -> L52
            boolean r6 = r6.equals(r8)     // Catch: java.lang.Exception -> L52
            if (r6 == 0) goto L4e
            java.lang.String r6 = "success"
            java.lang.String r8 = "message"
            java.lang.String r8 = r9.optString(r8)     // Catch: java.lang.Exception -> L52
            boolean r6 = r6.equals(r8)     // Catch: java.lang.Exception -> L52
            if (r6 == 0) goto L48
            r7 = 200(0xc8, float:2.8E-43)
            goto L78
        L48:
            r6 = 101(0x65, float:1.42E-43)
            r6 = r9
            r7 = 101(0x65, float:1.42E-43)
            goto L74
        L4e:
            r6 = r9
            r7 = 102(0x66, float:1.43E-43)
            goto L74
        L52:
            r6 = move-exception
            goto L57
        L54:
            r8 = move-exception
            r9 = r6
            r6 = r8
        L57:
            boolean r8 = r6 instanceof b.b.a.h.c
            if (r8 == 0) goto L70
            b.b.a.h.c r6 = (b.b.a.h.c) r6
            int r7 = r6.f1091a
            com.apm.applog.d r6 = r12.f1070b
            boolean r6 = r6.N()
            if (r6 == 0) goto L6e
            boolean r6 = a(r7)
            if (r6 == 0) goto L6e
            goto L78
        L6e:
            r6 = r9
            goto L74
        L70:
            b.b.a.k.v.a(r6)
            goto L6e
        L74:
            int r5 = r5 + 1
            goto Ld
        L77:
            r9 = r6
        L78:
            if (r9 == 0) goto Lda
            r12.a(r9)
            java.lang.String r10 = "blocklist"
            org.json.JSONObject r10 = r9.optJSONObject(r10)
            if (r10 == 0) goto Lda
            java.lang.String r11 = "v1"
            org.json.JSONArray r11 = r10.optJSONArray(r11)
            if (r11 == 0) goto L92
            int r0 = r11.length()
            goto L93
        L92:
            r0 = 0
        L93:
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>(r0)
            r2 = 0
        L99:
            if (r2 >= r0) goto Lab
            java.lang.String r5 = r11.optString(r2, r3)
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto La8
            r1.add(r5)
        La8:
            int r2 = r2 + 1
            goto L99
        Lab:
            java.lang.String r11 = "v3"
            org.json.JSONArray r10 = r10.optJSONArray(r11)
            if (r10 == 0) goto Lb8
            int r11 = r10.length()
            goto Lb9
        Lb8:
            r11 = 0
        Lb9:
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>(r11)
        Lbe:
            if (r4 >= r11) goto Ld0
            java.lang.String r2 = r10.optString(r4, r3)
            boolean r5 = android.text.TextUtils.isEmpty(r2)
            if (r5 != 0) goto Lcd
            r0.add(r2)
        Lcd:
            int r4 = r4 + 1
            goto Lbe
        Ld0:
            java.util.HashSet<java.lang.String> r10 = r12.h
            r10.addAll(r1)
            java.util.HashSet<java.lang.String> r10 = r12.i
            r10.addAll(r0)
        Lda:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.i.a.a(java.lang.String[], byte[], b.b.a.f.g):int");
    }

    public static void a(JSONObject jSONObject) throws JSONException {
        try {
            long jOptLong = jSONObject.optLong("server_time");
            if (jOptLong > 0) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("server_time", jOptLong);
                jSONObject2.put("local_time", System.currentTimeMillis() / 1000);
                f1096b = jSONObject2;
            }
        } catch (Exception unused) {
        }
    }

    public static HashMap<String, String> a() {
        HashMap<String, String> map = new HashMap<>(2);
        if (com.apm.applog.a.f()) {
            map.put("Content-Type", "application/octet-stream;tt-data=a");
            map.put("Content-Encoding", HttpConstant.GZIP);
        } else {
            map.put("Content-Type", e.f3610c);
        }
        return map;
    }

    public static boolean a(b.b.a.d.b bVar, JSONObject jSONObject, String str) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("header", bVar.g.a());
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
            StringBuilder sb = new StringBuilder();
            sb.append("https://databyterangers.com.cn");
            sb.append("/simulator/mobile/log");
            if (!new JSONObject(a(1, sb.toString(), mapA, b.b.a.a.b.a(jSONObject2.toString()))).getJSONObject("data").getBoolean("keep")) {
                com.apm.applog.a.a(bVar.g.f()).a(false, str);
            }
            return true;
        } catch (Exception unused2) {
            return false;
        }
    }
}
