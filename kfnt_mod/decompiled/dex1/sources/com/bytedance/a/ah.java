package com.bytedance.a;

import android.accounts.Account;
import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ah implements bq {

    /* renamed from: a, reason: collision with root package name */
    public static String f3347a;

    /* renamed from: b, reason: collision with root package name */
    public static String f3348b;

    /* renamed from: c, reason: collision with root package name */
    public static String f3349c;
    public static JSONArray d;
    public static volatile String e;
    public static String[] f;
    public static String g;
    public final Context h;
    public bz i;
    public final bp j;
    public final String k;
    public final az l;

    public ah(Context context, az azVar, bp bpVar) {
        this.l = azVar;
        this.k = azVar.f3386b.getLocalTest() ? "_local" : "";
        this.h = context.getApplicationContext();
        ac acVar = new ac();
        this.j = bpVar;
        this.i = new ds(this.h, azVar.f3386b.getSpName());
        this.i.d = this.j;
        if (!azVar.f3386b.getAnonymous()) {
            new Thread(new x(acVar)).start();
        }
        a(azVar.f3386b.getAccount());
    }

    public String a() throws Throwable {
        if (!TextUtils.isEmpty(f3348b)) {
            return f3348b;
        }
        try {
            SharedPreferences sharedPreferences = this.h.getSharedPreferences("snssdk_openudid", 0);
            String string = sharedPreferences.getString("clientudid", null);
            if (dj.a(string)) {
                this.j.c(string, null);
            } else {
                string = UUID.randomUUID().toString();
                String strC = this.j.c(a("clientudid.dat", (String) null), string);
                if (dj.a(strC)) {
                    string = strC;
                }
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.putString("clientudid", string);
                editorEdit.commit();
            }
            if (!TextUtils.isEmpty(string)) {
                string = string + this.k;
            }
            f3348b = string;
            return string;
        } catch (Exception e2) {
            de.a("", e2);
            return "";
        }
    }

    public void a(Account account) {
        bp bpVar = this.j;
        if (bpVar != null) {
            bpVar.a(account);
        }
    }

    public void a(String str) {
        if (!dj.b(str) || dj.a(str, e)) {
            return;
        }
        e = this.i.g(str, e);
    }

    public String b() {
        if (!TextUtils.isEmpty(g)) {
            return g;
        }
        try {
            String strD = this.i.d(null, m.f(this.h));
            if (!TextUtils.isEmpty(strD)) {
                strD = strD + this.k;
            }
            g = strD;
            return strD;
        } catch (Exception e2) {
            de.a("", e2);
            return null;
        }
    }

    public void b(String str) {
        this.i.c(str);
        de.a("DeviceParamsProvider#clear clearKey=" + str + " sDeviceId=" + e + " mCacheHandler.loadDeviceId()=" + this.i.g("", ""), null);
    }

    public String[] c() {
        String[] strArr = f;
        if (strArr != null && strArr.length > 0) {
            return strArr;
        }
        try {
            String[] strArrA = this.i.a((String[]) null, m.c(this.h));
            if (strArrA == null) {
                strArrA = new String[0];
            }
            for (int i = 0; i < strArrA.length; i++) {
                strArrA[i] = strArrA[i] + this.k;
            }
            f = strArrA;
            return strArrA;
        } catch (Exception e2) {
            de.a("", e2);
            return null;
        }
    }

    public String d() {
        String deviceId;
        if (!TextUtils.isEmpty(f3349c)) {
            return f3349c;
        }
        try {
            if (this.l.f3386b.isImeiEnable()) {
                Context context = this.h;
                if (context == null) {
                    deviceId = null;
                } else {
                    try {
                        deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                    } catch (Exception e2) {
                        de.a("", e2);
                    }
                }
            } else {
                deviceId = this.l.f3386b.getAppImei();
            }
            String strE = this.i.e(null, deviceId);
            if (!TextUtils.isEmpty(strE)) {
                strE = strE + this.k;
            }
            f3349c = strE;
            return strE;
        } catch (Exception e3) {
            de.a("", e3);
            return null;
        }
    }

    public JSONArray e() throws JSONException {
        JSONArray jSONArray = d;
        if (jSONArray != null) {
            return jSONArray;
        }
        try {
            JSONArray jSONArray2 = new JSONArray(this.i.f(null, m.e(this.h).toString()));
            if (!TextUtils.isEmpty(this.k)) {
                String str = this.k;
                if (jSONArray2.length() != 0) {
                    for (int i = 0; i < jSONArray2.length(); i++) {
                        JSONObject jSONObjectOptJSONObject = jSONArray2.optJSONObject(i);
                        if (jSONObjectOptJSONObject != null) {
                            String strOptString = jSONObjectOptJSONObject.optString("id");
                            if (!TextUtils.isEmpty(strOptString)) {
                                jSONObjectOptJSONObject.remove("id");
                                jSONObjectOptJSONObject.put("id", strOptString + str);
                            }
                        }
                    }
                }
            }
            d = jSONArray2;
            return d;
        } catch (Exception e2) {
            de.a("", e2);
            return null;
        }
    }

    public String f() {
        throw new RuntimeException("请不要调用这个方法");
    }

    public String g() {
        if (!TextUtils.isEmpty(e)) {
            return e;
        }
        e = this.i.g("", "");
        return e;
    }

    public void a(Context context, String str) {
        String str2;
        de.a("DeviceParamsProvider#clearDidAndIid clearKey=" + str + " sDeviceId=" + e + " mCacheHandler.loadDeviceId()=" + this.i.g("", ""), null);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        e = null;
        String str3 = "clear_key_prefix" + str;
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.l.f3386b.getSpName(), 0);
        if (!sharedPreferences.getBoolean(str3, false)) {
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putBoolean(str3, true);
            if (sharedPreferences.contains(com.bytedance.b.a.c.h.x)) {
                editorEdit.remove(com.bytedance.b.a.c.h.x);
            }
            if (sharedPreferences.contains("install_id")) {
                editorEdit.remove("install_id");
            }
            editorEdit.apply();
            this.i.c(com.bytedance.b.a.c.h.x);
            if (!de.f3494c) {
                return;
            }
            str2 = "clearKey : " + str + " :clear installId and deviceId finish";
        } else {
            if (!de.f3494c) {
                return;
            }
            str2 = "clearKey : " + str + " : is already cleared";
        }
        de.a(str2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x009e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String a(boolean r8) throws java.lang.Throwable {
        /*
            r7 = this;
            java.lang.String r0 = com.bytedance.a.ah.f3347a
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto Lb
            java.lang.String r8 = com.bytedance.a.ah.f3347a
            return r8
        Lb:
            android.content.Context r0 = r7.h
            r1 = 0
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch: java.lang.Exception -> L19
            java.lang.String r2 = "android_id"
            java.lang.String r0 = android.provider.Settings.Secure.getString(r0, r2)     // Catch: java.lang.Exception -> L19
            goto L20
        L19:
            r0 = move-exception
            java.lang.String r2 = ""
            com.bytedance.a.de.a(r2, r0)
            r0 = r1
        L20:
            boolean r2 = com.bytedance.a.dj.a(r0)     // Catch: java.lang.Exception -> Lb3
            if (r2 == 0) goto L37
            java.lang.String r2 = "9774d56d682e549c"
            boolean r2 = r2.equals(r0)     // Catch: java.lang.Exception -> Lb3
            if (r2 == 0) goto L2f
            goto L37
        L2f:
            com.bytedance.a.bz r8 = r7.i     // Catch: java.lang.Exception -> Lb3
            java.lang.String r8 = r8.b(r1, r0)     // Catch: java.lang.Exception -> Lb3
            goto Lba
        L37:
            android.content.Context r2 = r7.h     // Catch: java.lang.Exception -> Lb3
            java.lang.String r3 = "snssdk_openudid"
            r4 = 0
            android.content.SharedPreferences r2 = r2.getSharedPreferences(r3, r4)     // Catch: java.lang.Exception -> Lb3
            java.lang.String r3 = "openudid"
            java.lang.String r3 = r2.getString(r3, r1)     // Catch: java.lang.Exception -> Lb3
            boolean r5 = com.bytedance.a.dj.a(r3)     // Catch: java.lang.Exception -> Lb3
            if (r5 != 0) goto Lac
            java.security.SecureRandom r3 = new java.security.SecureRandom     // Catch: java.lang.Exception -> Lb3
            r3.<init>()     // Catch: java.lang.Exception -> Lb3
            java.math.BigInteger r5 = new java.math.BigInteger     // Catch: java.lang.Exception -> Lb3
            r6 = 80
            r5.<init>(r6, r3)     // Catch: java.lang.Exception -> Lb3
            r3 = 16
            java.lang.String r3 = r5.toString(r3)     // Catch: java.lang.Exception -> Lb3
            char r4 = r3.charAt(r4)     // Catch: java.lang.Exception -> Lb3
            r5 = 45
            if (r4 != r5) goto L6b
            r4 = 1
            java.lang.String r3 = r3.substring(r4)     // Catch: java.lang.Exception -> Lb3
        L6b:
            int r4 = r3.length()     // Catch: java.lang.Exception -> Lb3
            int r4 = 13 - r4
            if (r4 <= 0) goto L89
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb3
            r5.<init>()     // Catch: java.lang.Exception -> Lb3
        L78:
            if (r4 <= 0) goto L82
            r6 = 70
            r5.append(r6)     // Catch: java.lang.Exception -> Lb3
            int r4 = r4 + (-1)
            goto L78
        L82:
            r5.append(r3)     // Catch: java.lang.Exception -> Lb3
            java.lang.String r3 = r5.toString()     // Catch: java.lang.Exception -> Lb3
        L89:
            if (r8 == 0) goto L9e
            java.lang.String r8 = "openudid.dat"
            java.lang.String r8 = a(r8, r1)     // Catch: java.lang.Exception -> Lb3
            com.bytedance.a.bp r1 = r7.j     // Catch: java.lang.Exception -> Lb3
            java.lang.String r8 = r1.b(r8, r3)     // Catch: java.lang.Exception -> Lb3
            boolean r1 = com.bytedance.a.dj.a(r8)     // Catch: java.lang.Exception -> Lb3
            if (r1 == 0) goto L9e
            goto L9f
        L9e:
            r8 = r3
        L9f:
            android.content.SharedPreferences$Editor r1 = r2.edit()     // Catch: java.lang.Exception -> Lb3
            java.lang.String r2 = "openudid"
            r1.putString(r2, r8)     // Catch: java.lang.Exception -> Lb3
            r1.commit()     // Catch: java.lang.Exception -> Lb3
            goto Lba
        Lac:
            com.bytedance.a.bp r8 = r7.j     // Catch: java.lang.Exception -> Lb3
            r8.b(r3, r1)     // Catch: java.lang.Exception -> Lb3
            r8 = r3
            goto Lba
        Lb3:
            r8 = move-exception
            java.lang.String r1 = ""
            com.bytedance.a.de.a(r1, r8)
            r8 = r0
        Lba:
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            if (r0 != 0) goto Lcd
            java.lang.StringBuilder r8 = com.bytedance.a.a.a(r8)
            java.lang.String r0 = r7.k
            r8.append(r0)
            java.lang.String r8 = r8.toString()
        Lcd:
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            if (r0 != 0) goto Ld5
            com.bytedance.a.ah.f3347a = r8
        Ld5:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.ah.a(boolean):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:104:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00ed A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(java.lang.String r6, java.lang.String r7) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 241
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.ah.a(java.lang.String, java.lang.String):java.lang.String");
    }
}
