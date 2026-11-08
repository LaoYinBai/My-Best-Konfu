package com.apm.insight.entity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.apm.insight.h;
import com.apm.insight.l.p;
import com.apm.insight.l.q;
import com.apm.insight.runtime.s;
import com.bytedance.sdk.account.platform.api.IGoogleService;
import com.bytedance.sdk.openadsdk.api.plugin.PluginConstants;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class Header {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f2830a = {"version_code", "manifest_version_code", "aid", "update_version_code"};
    private static String d = null;
    private static int e = -1;
    private static int f = -1;

    /* renamed from: b, reason: collision with root package name */
    private Context f2831b;

    /* renamed from: c, reason: collision with root package name */
    private JSONObject f2832c = new JSONObject();

    public Header(Context context) {
        this.f2831b = context;
    }

    public static Header a(Context context) throws JSONException {
        Header header = new Header(context);
        header.e(header.f());
        return header;
    }

    public static Header a(Context context, long j) throws JSONException {
        Header headerA;
        s sVarA = s.a();
        if (j == 0) {
            j = System.currentTimeMillis();
        }
        JSONObject jSONObjectA = sVarA.a(j);
        if (jSONObjectA == null || jSONObjectA.length() == 0) {
            headerA = a(h.g());
            headerA.c();
            try {
                headerA.f().put("errHeader", 1);
            } catch (Throwable unused) {
            }
        } else {
            headerA = new Header(h.g());
        }
        b(headerA);
        headerA.a(jSONObjectA);
        return headerA;
    }

    public static Header a(Header header) throws JSONException {
        addRuntimeHeader(header.f());
        return header;
    }

    public static boolean a() {
        if (e == -1) {
            e = g().contains("64") ? 1 : 0;
        }
        return e == 1;
    }

    @Keep
    public static void addOtherHeader(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return;
        }
        h(jSONObject);
        f(jSONObject);
        g(jSONObject);
        try {
            jSONObject.put("os", "Android");
            jSONObject.put(com.bytedance.b.a.c.h.x, h.c().a());
            jSONObject.put("os_version", h());
            jSONObject.put("os_api", Build.VERSION.SDK_INT);
            String str = Build.MODEL;
            String str2 = Build.BRAND;
            if (str == null) {
                str = str2;
            } else if (str2 != null && !str.contains(str2)) {
                str = str2 + ' ' + str;
            }
            jSONObject.put("device_model", str);
            jSONObject.put("device_brand", Build.BRAND);
            jSONObject.put("device_manufacturer", Build.MANUFACTURER);
            jSONObject.put("cpu_abi", g());
            Context contextG = h.g();
            String packageName = contextG.getPackageName();
            jSONObject.put("package", packageName);
            PackageInfo packageInfo = contextG.getPackageManager().getPackageInfo(packageName, 0);
            if (packageInfo.applicationInfo != null) {
                int i = packageInfo.applicationInfo.labelRes;
                if (i > 0) {
                    jSONObject.put(IGoogleService.ResponseKey.DISPLAY_NAME, contextG.getString(i));
                } else {
                    jSONObject.put(IGoogleService.ResponseKey.DISPLAY_NAME, contextG.getPackageManager().getApplicationLabel(packageInfo.applicationInfo));
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        d.a(jSONObject);
    }

    public static void addRuntimeHeader(JSONObject jSONObject) throws JSONException {
        i(jSONObject);
        j(jSONObject);
    }

    public static Header b(Context context) throws JSONException {
        Header headerA = a(context);
        a(headerA);
        b(headerA);
        headerA.c();
        headerA.d();
        headerA.e();
        return headerA;
    }

    public static void b(Header header) throws JSONException {
        if (header == null) {
            return;
        }
        addOtherHeader(header.f());
    }

    public static boolean b() {
        if (f == -1) {
            f = g().contains("86") ? 1 : 0;
        }
        return f == 1;
    }

    public static boolean b(JSONObject jSONObject) {
        return jSONObject.optInt("unauthentic_version", 0) == 1;
    }

    public static boolean c(JSONObject jSONObject) {
        return jSONObject == null || jSONObject.length() == 0 || (jSONObject.opt("app_version") == null && jSONObject.opt("version_name") == null) || jSONObject.opt("version_code") == null || jSONObject.opt("update_version_code") == null;
    }

    public static boolean d(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.length() != 0) {
            String strOptString = jSONObject.optString("aid");
            if (TextUtils.isEmpty(strOptString)) {
                return true;
            }
            try {
                return Integer.parseInt(strOptString) <= 0;
            } catch (Throwable unused) {
            }
        }
        return true;
    }

    @SuppressLint({"MissingPermission"})
    private void e(JSONObject jSONObject) throws JSONException {
        try {
            jSONObject.put(PluginConstants.KEY_SDK_VERSION, 1040090);
            jSONObject.put("sdk_version_name", "1.4.0");
        } catch (Exception unused) {
        }
    }

    private static void f(JSONObject jSONObject) throws JSONException {
        try {
            DisplayMetrics displayMetrics = h.g().getResources().getDisplayMetrics();
            int i = displayMetrics.densityDpi;
            String str = i != 120 ? i != 240 ? i != 320 ? "mdpi" : "xhdpi" : "hdpi" : "ldpi";
            jSONObject.put("density_dpi", i);
            jSONObject.put("display_density", str);
            jSONObject.put("resolution", displayMetrics.heightPixels + "x" + displayMetrics.widthPixels);
        } catch (Exception unused) {
        }
    }

    private static String g() {
        if (d == null) {
            try {
                StringBuilder sb = new StringBuilder();
                if (Build.VERSION.SDK_INT < 21 || Build.SUPPORTED_ABIS.length <= 0) {
                    sb = new StringBuilder(Build.CPU_ABI);
                } else {
                    for (int i = 0; i < Build.SUPPORTED_ABIS.length; i++) {
                        sb.append(Build.SUPPORTED_ABIS[i]);
                        if (i != Build.SUPPORTED_ABIS.length - 1) {
                            sb.append(", ");
                        }
                    }
                }
                if (TextUtils.isEmpty(sb.toString())) {
                    d = "unknown";
                }
                d = sb.toString();
            } catch (Exception e2) {
                q.b((Throwable) e2);
                d = "unknown";
            }
        }
        return d;
    }

    private static void g(JSONObject jSONObject) throws JSONException {
        try {
            String language = h.g().getResources().getConfiguration().locale.getLanguage();
            if (!TextUtils.isEmpty(language)) {
                jSONObject.put("language", language);
            }
            String country = Locale.getDefault().getCountry();
            if (!TextUtils.isEmpty(country)) {
                jSONObject.put("region", country);
            }
            int rawOffset = TimeZone.getDefault().getRawOffset() / 3600000;
            if (rawOffset < -12) {
                rawOffset = -12;
            }
            if (rawOffset > 12) {
                rawOffset = 12;
            }
            jSONObject.put("timezone", rawOffset);
        } catch (Exception unused) {
        }
    }

    private static String h() {
        String str = Build.VERSION.RELEASE;
        if (str.contains(".")) {
            return str;
        }
        return str + ".0";
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0040 A[Catch: Throwable -> 0x0052, TryCatch #0 {Throwable -> 0x0052, blocks: (B:3:0x0005, B:6:0x000d, B:16:0x0035, B:18:0x0040, B:19:0x0049, B:7:0x0011, B:10:0x001a, B:12:0x0024, B:13:0x0029, B:15:0x002f), top: B:22:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void h(org.json.JSONObject r3) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            boolean r1 = com.apm.insight.l.d.c()     // Catch: java.lang.Throwable -> L52
            if (r1 == 0) goto L11
            java.lang.String r1 = "MIUI-"
        Ld:
            r0.append(r1)     // Catch: java.lang.Throwable -> L52
            goto L35
        L11:
            boolean r1 = com.apm.insight.l.d.d()     // Catch: java.lang.Throwable -> L52
            if (r1 == 0) goto L1a
            java.lang.String r1 = "FLYME-"
            goto Ld
        L1a:
            java.lang.String r1 = com.apm.insight.l.d.a()     // Catch: java.lang.Throwable -> L52
            boolean r2 = com.apm.insight.l.d.a(r1)     // Catch: java.lang.Throwable -> L52
            if (r2 == 0) goto L29
            java.lang.String r2 = "EMUI-"
            r0.append(r2)     // Catch: java.lang.Throwable -> L52
        L29:
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L52
            if (r2 != 0) goto L35
            r0.append(r1)     // Catch: java.lang.Throwable -> L52
            java.lang.String r1 = "-"
            goto Ld
        L35:
            java.lang.String r1 = android.os.Build.VERSION.INCREMENTAL     // Catch: java.lang.Throwable -> L52
            r0.append(r1)     // Catch: java.lang.Throwable -> L52
            int r1 = r0.length()     // Catch: java.lang.Throwable -> L52
            if (r1 <= 0) goto L49
            java.lang.String r1 = "rom"
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L52
            r3.put(r1, r0)     // Catch: java.lang.Throwable -> L52
        L49:
            java.lang.String r0 = "rom_version"
            java.lang.String r1 = com.apm.insight.l.t.a()     // Catch: java.lang.Throwable -> L52
            r3.put(r0, r1)     // Catch: java.lang.Throwable -> L52
        L52:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apm.insight.entity.Header.h(org.json.JSONObject):void");
    }

    private static void i(JSONObject jSONObject) throws JSONException {
        try {
            jSONObject.put("access", p.a(h.g()));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private static void j(JSONObject jSONObject) throws JSONException {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) h.g().getSystemService("phone");
            if (telephonyManager != null) {
                String networkOperatorName = telephonyManager.getNetworkOperatorName();
                if (!TextUtils.isEmpty(networkOperatorName)) {
                    jSONObject.put(DispatchConstants.CARRIER, networkOperatorName);
                }
                String networkOperator = telephonyManager.getNetworkOperator();
                if (TextUtils.isEmpty(networkOperator)) {
                    return;
                }
                jSONObject.put("mcc_mnc", networkOperator);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public JSONObject a(@Nullable Map<String, Object> map) {
        if (map == null) {
            return this.f2832c;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (!this.f2832c.has(entry.getKey())) {
                this.f2832c.put(entry.getKey(), entry.getValue());
            }
        }
        for (String str : f2830a) {
            if (map.containsKey(str)) {
                try {
                    this.f2832c.put(str, Integer.parseInt(String.valueOf(map.get(str))));
                } catch (Throwable unused) {
                    this.f2832c.put(str, map.get(str));
                }
            }
        }
        if (map.containsKey("version_code") && !map.containsKey("manifest_version_code")) {
            try {
                this.f2832c.put("manifest_version_code", Integer.parseInt(String.valueOf(map.get("version_code"))));
            } catch (Throwable unused2) {
            }
        }
        if (map.containsKey("iid")) {
            this.f2832c.put("udid", map.get("iid"));
            this.f2832c.remove("iid");
        }
        if (map.containsKey("version_name")) {
            this.f2832c.put("app_version", map.get("version_name"));
            this.f2832c.remove("version_name");
        }
        return this.f2832c;
    }

    public JSONObject a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return this.f2832c;
        }
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            try {
                this.f2832c.put(next, jSONObject.opt(next));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return this.f2832c;
    }

    public JSONObject c() {
        return a(h.a().a());
    }

    public JSONObject d() throws JSONException {
        try {
            this.f2832c.put(com.bytedance.b.a.c.h.x, h.c().a());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return this.f2832c;
    }

    public JSONObject e() throws JSONException {
        try {
            long jF = h.a().f();
            if (jF > 0) {
                this.f2832c.put("user_id", jF);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return this.f2832c;
    }

    public JSONObject f() {
        return this.f2832c;
    }
}
