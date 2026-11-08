package com.alipay.sdk.tid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.alipay.sdk.app.OpenAuthTask;
import com.alipay.sdk.encrypt.e;
import com.alipay.sdk.util.c;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    public static final String f2638a = "alipay_tid_storage";

    /* renamed from: b, reason: collision with root package name */
    public static final String f2639b = "tidinfo";

    /* renamed from: c, reason: collision with root package name */
    public static final String f2640c = "tid";
    public static final String d = "client_key";
    public static final String e = "timestamp";
    public static final String f = "vimei";
    public static final String g = "vimsi";
    private static Context h;
    private static a n;
    private String i;
    private String j;
    private long k;
    private String l;
    private String m;
    private boolean o = false;

    private void n() {
    }

    public static synchronized a a(Context context) {
        if (n == null) {
            n = new a();
        }
        if (h == null) {
            n.b(context);
        }
        return n;
    }

    private void b(Context context) {
        if (context != null) {
            h = context.getApplicationContext();
        }
        if (this.o) {
            return;
        }
        this.o = true;
        k();
    }

    public String a() {
        return this.i;
    }

    public String b() {
        return this.j;
    }

    public String c() {
        return this.l;
    }

    public String d() {
        return this.m;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void k() {
        /*
            r9 = this;
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r1 = 0
            java.lang.String r2 = "alipay_tid_storage"
            java.lang.String r3 = "tidinfo"
            r4 = 1
            java.lang.String r2 = com.alipay.sdk.tid.a.C0081a.a(r2, r3, r4)     // Catch: java.lang.Exception -> L61
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Exception -> L61
            if (r3 != 0) goto L5d
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Exception -> L61
            r3.<init>(r2)     // Catch: java.lang.Exception -> L61
            java.lang.String r2 = "tid"
            java.lang.String r4 = ""
            java.lang.String r2 = r3.optString(r2, r4)     // Catch: java.lang.Exception -> L61
            java.lang.String r4 = "client_key"
            java.lang.String r5 = ""
            java.lang.String r4 = r3.optString(r4, r5)     // Catch: java.lang.Exception -> L5a
            java.lang.String r5 = "timestamp"
            long r6 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L57
            long r5 = r3.optLong(r5, r6)     // Catch: java.lang.Exception -> L57
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch: java.lang.Exception -> L57
            java.lang.String r0 = "vimei"
            java.lang.String r6 = ""
            java.lang.String r0 = r3.optString(r0, r6)     // Catch: java.lang.Exception -> L54
            java.lang.String r6 = "vimsi"
            java.lang.String r7 = ""
            java.lang.String r3 = r3.optString(r6, r7)     // Catch: java.lang.Exception -> L4f
            r1 = r2
            r2 = r0
            r0 = r5
            goto L6b
        L4f:
            r3 = move-exception
            r8 = r5
            r5 = r0
            r0 = r8
            goto L65
        L54:
            r3 = move-exception
            r0 = r5
            goto L58
        L57:
            r3 = move-exception
        L58:
            r5 = r1
            goto L65
        L5a:
            r3 = move-exception
            r4 = r1
            goto L64
        L5d:
            r2 = r1
            r3 = r2
            r4 = r3
            goto L6b
        L61:
            r3 = move-exception
            r2 = r1
            r4 = r2
        L64:
            r5 = r4
        L65:
            com.alipay.sdk.util.c.a(r3)
            r3 = r1
            r1 = r2
            r2 = r5
        L6b:
            java.lang.String r5 = "mspl"
            java.lang.String r6 = "tid_str: load"
            com.alipay.sdk.util.c.a(r5, r6)
            boolean r5 = r9.a(r1, r4, r2, r3)
            if (r5 == 0) goto L7c
            r9.l()
            goto L8a
        L7c:
            r9.i = r1
            r9.j = r4
            long r0 = r0.longValue()
            r9.k = r0
            r9.l = r2
            r9.m = r3
        L8a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.a.k():void");
    }

    private boolean a(String str, String str2, String str3, String str4) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4);
    }

    public boolean e() {
        return TextUtils.isEmpty(this.i) || TextUtils.isEmpty(this.j) || TextUtils.isEmpty(this.l) || TextUtils.isEmpty(this.m);
    }

    private void l() {
        this.i = "";
        this.j = f();
        this.k = System.currentTimeMillis();
        this.l = m();
        this.m = m();
        C0081a.b(f2638a, f2639b);
    }

    private String m() {
        return Long.toHexString(System.currentTimeMillis()) + (new Random().nextInt(OpenAuthTask.OK) + 1000);
    }

    public String f() {
        String hexString = Long.toHexString(System.currentTimeMillis());
        return hexString.length() > 10 ? hexString.substring(hexString.length() - 10) : hexString;
    }

    public void g() {
        c.a(com.alipay.sdk.cons.a.x, "tid_str: del");
        l();
    }

    public boolean h() {
        return e();
    }

    public Long i() {
        return Long.valueOf(this.k);
    }

    public void a(String str, String str2) throws JSONException {
        c.a(com.alipay.sdk.cons.a.x, "tid_str: save");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        this.i = str;
        this.j = str2;
        this.k = System.currentTimeMillis();
        o();
        n();
    }

    private void a(String str, String str2, String str3, String str4, Long l) throws JSONException {
        if (a(str, str2, str3, str4)) {
            return;
        }
        this.i = str;
        this.j = str2;
        this.l = str3;
        this.m = str4;
        if (l == null) {
            this.k = System.currentTimeMillis();
        } else {
            this.k = l.longValue();
        }
        o();
    }

    private void o() throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("tid", this.i);
            jSONObject.put("client_key", this.j);
            jSONObject.put("timestamp", this.k);
            jSONObject.put(f, this.l);
            jSONObject.put(g, this.m);
            C0081a.a(f2638a, f2639b, jSONObject.toString(), true);
        } catch (Exception e2) {
            c.a(e2);
        }
    }

    /* renamed from: com.alipay.sdk.tid.a$a, reason: collision with other inner class name */
    public static class C0081a {
        public static boolean a(String str, String str2) {
            if (a.h == null) {
                return false;
            }
            return a.h.getSharedPreferences(str, 0).contains(str2);
        }

        public static void b(String str, String str2) {
            if (a.h == null) {
                return;
            }
            a.h.getSharedPreferences(str, 0).edit().remove(str2).apply();
        }

        public static String c(String str, String str2) {
            return a(str, str2, true);
        }

        public static boolean d(String str, String str2) {
            if (a.h == null) {
                return false;
            }
            return a.h.getSharedPreferences(str, 0).contains(str2);
        }

        public static String a(String str, String str2, boolean z) {
            if (a.h == null) {
                return null;
            }
            String string = a.h.getSharedPreferences(str, 0).getString(str2, null);
            if (!TextUtils.isEmpty(string) && z) {
                string = e.b(a(), string, string);
                if (TextUtils.isEmpty(string)) {
                    c.a(com.alipay.sdk.cons.a.x, "tid_str: pref failed");
                }
            }
            c.a(com.alipay.sdk.cons.a.x, "tid_str: from local");
            return string;
        }

        public static void a(String str, String str2, String str3) {
            a(str, str2, str3, true);
        }

        public static void a(String str, String str2, String str3, boolean z) {
            if (a.h == null) {
                return;
            }
            SharedPreferences sharedPreferences = a.h.getSharedPreferences(str, 0);
            if (z) {
                String strA = a();
                String strA2 = e.a(strA, str3, str3);
                if (TextUtils.isEmpty(strA2)) {
                    String.format("LocalPreference::putLocalPreferences failed %s，%s", str3, strA);
                }
                str3 = strA2;
            }
            sharedPreferences.edit().putString(str2, str3).apply();
        }

        private static String a() {
            String packageName = "";
            try {
                packageName = a.h.getApplicationContext().getPackageName();
            } catch (Throwable th) {
                c.a(th);
            }
            return (packageName + "0000000000000000000000000000").substring(0, 24);
        }
    }
}
