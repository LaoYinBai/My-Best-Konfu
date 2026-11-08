package com.alipay.sdk.sys;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.alipay.sdk.util.c;
import com.alipay.sdk.util.k;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    public static final String f2630a = "\"&";

    /* renamed from: b, reason: collision with root package name */
    public static final String f2631b = "&";

    /* renamed from: c, reason: collision with root package name */
    public static final String f2632c = "bizcontext=\"";
    public static final String d = "bizcontext=";
    public static final String e = "\"";
    public static final String f = "appkey";
    public static final String g = "ty";
    public static final String h = "sv";
    public static final String i = "an";
    public static final String j = "setting";
    public static final String k = "av";
    public static final String l = "sdk_start_time";
    public static final String m = "extInfo";
    public static final String n = "ap_link_token";
    public static final String o = "act_info";
    public static final String p = "UTF-8";
    public final String q;
    public final long r;
    public final int s;
    public final String t;
    public final com.alipay.sdk.app.statistic.b u;
    private String v;
    private String w;
    private Context x;
    private final ActivityInfo y;

    public static a a() {
        return null;
    }

    /* renamed from: com.alipay.sdk.sys.a$a, reason: collision with other inner class name */
    public static final class C0080a {

        /* renamed from: a, reason: collision with root package name */
        private static final HashMap<UUID, a> f2633a = new HashMap<>();

        /* renamed from: b, reason: collision with root package name */
        private static final HashMap<String, a> f2634b = new HashMap<>();

        /* renamed from: c, reason: collision with root package name */
        private static final String f2635c = "i_uuid_b_c";

        public static void a(a aVar, Intent intent) {
            if (aVar == null || intent == null) {
                return;
            }
            UUID uuidRandomUUID = UUID.randomUUID();
            f2633a.put(uuidRandomUUID, aVar);
            intent.putExtra(f2635c, uuidRandomUUID);
        }

        public static a a(Intent intent) {
            if (intent == null) {
                return null;
            }
            Serializable serializableExtra = intent.getSerializableExtra(f2635c);
            if (serializableExtra instanceof UUID) {
                return f2633a.remove((UUID) serializableExtra);
            }
            return null;
        }

        public static void a(a aVar, String str) {
            if (aVar == null || TextUtils.isEmpty(str)) {
                return;
            }
            f2634b.put(str, aVar);
        }

        public static a a(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return f2634b.remove(str);
        }
    }

    public a(Context context, String str, String str2) throws PackageManager.NameNotFoundException {
        String str3;
        this.v = "";
        this.w = "";
        this.x = null;
        boolean zIsEmpty = TextUtils.isEmpty(str2);
        this.u = new com.alipay.sdk.app.statistic.b(context, zIsEmpty);
        this.q = c(str, this.w);
        this.r = SystemClock.elapsedRealtime();
        this.s = k.d();
        this.y = k.h(context);
        this.t = str2;
        if (!zIsEmpty) {
            com.alipay.sdk.app.statistic.a.b(this, com.alipay.sdk.app.statistic.b.f2572b, "eptyp", str2 + "|" + this.q);
            if (this.y != null) {
                str3 = this.y.name + "|" + this.y.launchMode;
            } else {
                str3 = "null";
            }
            com.alipay.sdk.app.statistic.a.b(this, com.alipay.sdk.app.statistic.b.f2572b, "actInfo", str3);
            com.alipay.sdk.app.statistic.a.b(this, com.alipay.sdk.app.statistic.b.f2572b, NotificationCompat.CATEGORY_SYSTEM, k.a(this));
        }
        try {
            this.x = context.getApplicationContext();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.v = packageInfo.versionName;
            this.w = packageInfo.packageName;
        } catch (Exception e2) {
            c.a(e2);
        }
        if (!zIsEmpty) {
            com.alipay.sdk.app.statistic.a.a(this, com.alipay.sdk.app.statistic.b.f2572b, "u" + k.d());
            com.alipay.sdk.app.statistic.a.b(this, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.K, "" + SystemClock.elapsedRealtime());
            com.alipay.sdk.app.statistic.a.a(context, this, str, this.q);
        }
        if (zIsEmpty || !com.alipay.sdk.data.a.p().n()) {
            return;
        }
        com.alipay.sdk.data.a.p().a(this, this.x);
    }

    public String b() {
        return this.w;
    }

    public String c() {
        return this.v;
    }

    public Context d() {
        return this.x;
    }

    public String a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("new_external_info==")) {
            return str;
        }
        if (b(str)) {
            return c(str);
        }
        return d(str);
    }

    private boolean b(String str) {
        return !str.contains(f2630a);
    }

    private String c(String str) {
        String str2;
        try {
            String strA = a(str, "&", d);
            if (TextUtils.isEmpty(strA)) {
                str2 = str + "&" + b(d, "");
            } else {
                int iIndexOf = str.indexOf(strA);
                str2 = str.substring(0, iIndexOf) + a(strA, d, "", true) + str.substring(iIndexOf + strA.length());
            }
            return str2;
        } catch (Throwable unused) {
            return str;
        }
    }

    private String d(String str) {
        String str2;
        try {
            String strA = a(str, f2630a, f2632c);
            if (TextUtils.isEmpty(strA)) {
                str2 = str + "&" + b(f2632c, "\"");
            } else {
                if (!strA.endsWith("\"")) {
                    strA = strA + "\"";
                }
                int iIndexOf = str.indexOf(strA);
                str2 = str.substring(0, iIndexOf) + a(strA, f2632c, "\"", false) + str.substring(iIndexOf + strA.length());
            }
            return str2;
        } catch (Throwable unused) {
            return str;
        }
    }

    private String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] strArrSplit = str.split(str2);
        for (int i2 = 0; i2 < strArrSplit.length; i2++) {
            if (!TextUtils.isEmpty(strArrSplit[i2]) && strArrSplit[i2].startsWith(str3)) {
                return strArrSplit[i2];
            }
        }
        return null;
    }

    private String b(String str, String str2) throws JSONException, UnsupportedEncodingException {
        return str + a("", "") + str2;
    }

    public String a(String str, String str2) {
        String str3;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appkey", com.alipay.sdk.cons.a.f);
            jSONObject.put(g, "and_lite");
            jSONObject.put(h, "h.a.3.7.9");
            if (!this.w.contains(j) || !k.b(this.x)) {
                jSONObject.put(i, this.w);
            }
            jSONObject.put(k, this.v);
            jSONObject.put(l, System.currentTimeMillis());
            jSONObject.put(m, e());
            if (this.y != null) {
                str3 = this.y.name + "|" + this.y.launchMode;
            } else {
                str3 = "null";
            }
            jSONObject.put(o, str3);
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(str, str2);
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            c.a(th);
            return "";
        }
    }

    private String a(String str, String str2, String str3, boolean z) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject;
        String strSubstring = str.substring(str2.length());
        boolean z2 = false;
        String strSubstring2 = strSubstring.substring(0, strSubstring.length() - str3.length());
        if (strSubstring2.length() >= 2 && strSubstring2.startsWith("\"") && strSubstring2.endsWith("\"")) {
            jSONObject = new JSONObject(strSubstring2.substring(1, strSubstring2.length() - 1));
            z2 = true;
        } else {
            jSONObject = new JSONObject(strSubstring2);
        }
        if (!jSONObject.has("appkey")) {
            jSONObject.put("appkey", com.alipay.sdk.cons.a.f);
        }
        if (!jSONObject.has(g)) {
            jSONObject.put(g, "and_lite");
        }
        if (!jSONObject.has(h)) {
            jSONObject.put(h, "h.a.3.7.9");
        }
        if (!jSONObject.has(i) && (!this.w.contains(j) || !k.b(this.x))) {
            jSONObject.put(i, this.w);
        }
        if (!jSONObject.has(k)) {
            jSONObject.put(k, this.v);
        }
        if (!jSONObject.has(l)) {
            jSONObject.put(l, System.currentTimeMillis());
        }
        if (!jSONObject.has(m)) {
            jSONObject.put(m, e());
        }
        String string = jSONObject.toString();
        if (z2) {
            string = "\"" + string + "\"";
        }
        return str2 + string + str3;
    }

    private JSONObject e() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(n, this.q);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    private static String c(String str, String str2) {
        try {
            Locale locale = Locale.getDefault();
            Object[] objArr = new Object[4];
            if (str == null) {
                str = "";
            }
            objArr[0] = str;
            if (str2 == null) {
                str2 = "";
            }
            objArr[1] = str2;
            objArr[2] = Long.valueOf(System.currentTimeMillis());
            objArr[3] = UUID.randomUUID().toString();
            return String.format("EP%s%s_%s", "1", k.f(String.format(locale, "%s%s%d%s", objArr)), Long.valueOf(System.currentTimeMillis()));
        } catch (Throwable unused) {
            return "-";
        }
    }

    public static HashMap<String, String> a(a aVar) {
        HashMap<String, String> map = new HashMap<>();
        if (aVar != null) {
            map.put("sdk_ver", "15.7.9");
            map.put(TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY, aVar.w);
            map.put("token", aVar.q);
            map.put("call_type", aVar.t);
            map.put("ts_api_invoke", String.valueOf(aVar.r));
        }
        return map;
    }
}
