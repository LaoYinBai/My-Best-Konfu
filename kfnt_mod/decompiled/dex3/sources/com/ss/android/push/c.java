package com.ss.android.push;

import android.content.Context;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    public static String a = "Wakeup";
    public static String b = "label_prevent_wakeup";
    public static String c = "label_wakeup_by_third";
    private static boolean d;

    public static void a(Context context, String str, long j, long j2, JSONObject jSONObject) {
        com.ss.android.message.log.c.a(context, "event_v1", a, str, j, j2, jSONObject);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x000b A[Catch: JSONException -> 0x0009, TryCatch #0 {JSONException -> 0x0009, blocks: (B:3:0x0002, B:5:0x0005, B:9:0x0010, B:8:0x000b), top: B:13:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.content.Context r7, java.lang.String r8, java.lang.String r9, org.json.JSONObject... r10) throws org.json.JSONException {
        /*
            if (r10 == 0) goto Lb
            int r0 = r10.length     // Catch: org.json.JSONException -> L9
            if (r0 <= 0) goto Lb
            r0 = 0
            r10 = r10[r0]     // Catch: org.json.JSONException -> L9
            goto L10
        L9:
            r7 = move-exception
            goto L28
        Lb:
            org.json.JSONObject r10 = new org.json.JSONObject     // Catch: org.json.JSONException -> L9
            r10.<init>()     // Catch: org.json.JSONException -> L9
        L10:
            r6 = r10
            java.lang.String r10 = "package"
            r6.put(r10, r8)     // Catch: org.json.JSONException -> L9
            java.lang.String r8 = "from"
            r6.put(r8, r9)     // Catch: org.json.JSONException -> L9
            java.lang.String r1 = com.ss.android.push.c.b     // Catch: org.json.JSONException -> L9
            long r2 = java.lang.System.currentTimeMillis()     // Catch: org.json.JSONException -> L9
            r4 = 0
            r0 = r7
            a(r0, r1, r2, r4, r6)     // Catch: org.json.JSONException -> L9
            goto L2b
        L28:
            r7.printStackTrace()
        L2b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.push.c.a(android.content.Context, java.lang.String, java.lang.String, org.json.JSONObject[]):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x000b A[Catch: JSONException -> 0x0009, TryCatch #0 {JSONException -> 0x0009, blocks: (B:3:0x0002, B:5:0x0005, B:9:0x0010, B:8:0x000b), top: B:13:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b(android.content.Context r7, java.lang.String r8, java.lang.String r9, org.json.JSONObject... r10) {
        /*
            if (r10 == 0) goto Lb
            int r0 = r10.length     // Catch: org.json.JSONException -> L9
            if (r0 <= 0) goto Lb
            r0 = 0
            r10 = r10[r0]     // Catch: org.json.JSONException -> L9
            goto L10
        L9:
            r7 = move-exception
            goto L28
        Lb:
            org.json.JSONObject r10 = new org.json.JSONObject     // Catch: org.json.JSONException -> L9
            r10.<init>()     // Catch: org.json.JSONException -> L9
        L10:
            r6 = r10
            java.lang.String r10 = "package"
            r6.put(r10, r8)     // Catch: org.json.JSONException -> L9
            java.lang.String r8 = "from"
            r6.put(r8, r9)     // Catch: org.json.JSONException -> L9
            java.lang.String r1 = com.ss.android.push.c.c     // Catch: org.json.JSONException -> L9
            long r2 = java.lang.System.currentTimeMillis()     // Catch: org.json.JSONException -> L9
            r4 = 0
            r0 = r7
            a(r0, r1, r2, r4, r6)     // Catch: org.json.JSONException -> L9
            goto L2b
        L28:
            r7.printStackTrace()
        L2b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.push.c.b(android.content.Context, java.lang.String, java.lang.String, org.json.JSONObject[]):void");
    }

    public static boolean a() {
        return d;
    }

    public static void a(boolean z) {
        d = z;
    }
}
