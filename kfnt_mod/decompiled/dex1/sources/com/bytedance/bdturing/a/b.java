package com.bytedance.bdturing.a;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.bdturing.a.a;
import com.bytedance.bdturing.c;
import com.bytedance.bdturing.g;
import com.bytedance.bdturing.j;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {
    private static final int H = 30000;

    /* renamed from: a, reason: collision with root package name */
    private static final String f3754a = "SettingsManager";

    /* renamed from: b, reason: collision with root package name */
    private static final String f3755b = "http://unpkg.pstatp.com/byted/secsdk-captcha/1.1.0-beta.45/build/index.html";

    /* renamed from: c, reason: collision with root package name */
    private static final String f3756c = "http://unpkg.pstatp.com/byted/secsdk-mobile-original/1.1.0-beta.45/build/index.html";
    private static final String d = "https://verify-sg.tiktokv.com";
    private static final String e = "https://verify-va.musical.ly";
    private static final String f = "https://verify-sg.byteoversea.com/";
    private static final String g = "https://verification-va.byteoversea.com/";
    private static final String h = "https://verify.snssdk.com/";
    private static final String i = "/";
    private static final String j = "captcha/setting";
    private static final String k = ".boe-gateway.byted.org";
    private static final String l = "available_time";
    private static final String m = "period";
    private static final String n = "verify_va";
    private static final String o = "verify_sg";
    private static final String p = "verify_cn";
    private static final String q = "h5_va";
    private static final String r = "h5_sg";
    private static final String s = "h5_cn";
    private static final String t = "sms_va";
    private static final String u = "sms_sg";
    private static final String v = "sms_cn";
    private static final String w = "setting";
    private static final String x = "h5url";
    private static final String y = "smsurl";
    private com.bytedance.bdturing.b.b A;
    private boolean L;
    private String M;
    private static c.b z = c.b.REGION_CHINA;
    private static int F = TTAdConstant.DEFAULT_LIVE_SHOW_TIME_MAX;
    private static int G = 331;
    private static double I = 0.5d;
    private String B = "{  \"h5_sg\": \"https://unpkg.pstatp.com/byted/secsdk-captcha/1.2.6/build/index.html\",  \"h5_va\": \"https://unpkg.pstatp.com/byted/secsdk-captcha/1.2.6/build/index.html\",  \"h5_cn\": \"https://unpkg.pstatp.com/byted/secsdk-captcha/1.2.6/build/index.html\",  \"sms_cn\": \"https://unpkg.pstatp.com/byted/secsdk-mobile-original/1.4.9/build/index.html\",  \"sms_va\": \"https://unpkg.pstatp.com/byted/secsdk-mobile-original/1.4.9/build/index.html\",  \"sms_sg\": \"https://unpkg.pstatp.com/byted/secsdk-mobile-original/1.4.9/build/index.html\",  \"rgb\": \"000000\",  \"alpha\": \"0.5\",  \"width\": 300,  \"height\": 331,  \"verify_sg\": \"https://verify-sg.byteoversea.com/\",  \"verify_va\": \"https://verification-va.byteoversea.com/\",  \"verify_cn\": \"https://verify.snssdk.com/\",  \"mobile_cdn\": \"\",  \"captcha_cdn\": \"//unpkg.pstatp.com/byted/secsdk-captcha/1.2.6/build/captcha.js\",  \"verify_cdn\": \"\",  \"period\": 300000}";
    private String C = h;
    private String D = f3755b;
    private String E = f3756c;
    private long J = 0;
    private long K = 0;
    private a.InterfaceC0107a N = new a.InterfaceC0107a() { // from class: com.bytedance.bdturing.a.b.1
        @Override // com.bytedance.bdturing.a.a.InterfaceC0107a
        public void a(a aVar, int i2, String str) throws JSONException {
            StringBuilder sb = new StringBuilder("VerifyUrl update ");
            boolean z2 = false;
            if (i2 == 200 && !TextUtils.isEmpty(str)) {
                g.a(b.f3754a, "responseContent = " + str);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    b.this.K = jSONObject.getLong(b.m);
                    b.this.J = System.currentTimeMillis() + b.this.K;
                    jSONObject.put(b.l, b.this.J);
                    b.this.B = jSONObject.toString();
                    b.this.h();
                    z2 = true;
                    sb.append("SUCCESS");
                } catch (JSONException unused) {
                    sb.append("FAIL cause JSON parse exception");
                }
            } else {
                b.this.J = System.currentTimeMillis() + b.this.K;
                sb.append("FAIL ");
                sb.append(i2);
            }
            b.this.l();
            if (z2) {
                j.a().a(10, b.this.B);
            }
            g.a(b.f3754a, sb.toString());
        }
    };

    public b(String str, boolean z2) {
        this.M = str;
        this.L = z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        Bundle bundleB = b(z);
        try {
            JSONObject jSONObject = new JSONObject(this.B);
            this.C = c(jSONObject.getString(bundleB.getString("setting")));
            this.D = jSONObject.optString(bundleB.getString(x), this.D);
            this.E = jSONObject.optString(bundleB.getString(y), this.E);
            F = jSONObject.optInt("width", F);
            G = jSONObject.optInt("height", G);
            I = jSONObject.optDouble("alpha", I);
        } catch (JSONException e2) {
            g.a(e2);
        }
    }

    public static int a() {
        return F;
    }

    public static int b() {
        return G;
    }

    public static double c() {
        return I;
    }

    public void a(Context context, c.b bVar) throws JSONException {
        this.A = com.bytedance.bdturing.b.b.a();
        this.A.a(context.getApplicationContext());
        String strB = this.A.b();
        if (strB != null) {
            this.B = strB;
        }
        z = bVar;
        h();
        if (b(this.B)) {
            i();
            return;
        }
        l();
        g.a(f3754a, "VerifyUrl do not need update : " + this.D);
    }

    public void a(c.b bVar) {
        if (z != bVar) {
            z = bVar;
            h();
        }
    }

    private void i() throws JSONException {
        String strJ = j();
        g.a(f3754a, "settingUrl = " + strJ);
        new a(strJ, this.N).a();
    }

    private Bundle b(c.b bVar) {
        Bundle bundle = new Bundle();
        switch (bVar) {
            case REGION_USA_EAST:
                bundle.putString("setting", n);
                bundle.putString(x, q);
                bundle.putString(y, t);
                return bundle;
            case REGION_SINGAPOER:
                bundle.putString("setting", o);
                bundle.putString(x, r);
                bundle.putString(y, u);
                return bundle;
            case REGION_CHINA:
                bundle.putString("setting", p);
                bundle.putString(x, s);
                bundle.putString(y, v);
                return bundle;
            default:
                bundle.putString("setting", p);
                bundle.putString(x, s);
                bundle.putString(y, v);
                return bundle;
        }
    }

    private boolean b(String str) {
        if (str == null) {
            return true;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.J = jSONObject.optLong(l);
            this.K = jSONObject.optLong(m, com.bytedance.b.a.a.c.g.e);
            long jCurrentTimeMillis = System.currentTimeMillis();
            g.a(f3754a, "isNeedUpdate " + (this.J - jCurrentTimeMillis));
            return this.J <= jCurrentTimeMillis;
        } catch (JSONException e2) {
            g.a(e2);
            return true;
        }
    }

    private String j() {
        String str;
        String strF = f();
        if (strF.endsWith("/")) {
            str = strF + j;
        } else {
            str = strF + "/" + j;
        }
        com.bytedance.bdturing.c cVarC = com.bytedance.bdturing.a.a().c();
        if (cVarC == null) {
            return str;
        }
        return (str + "?") + "aid=" + cVarC.c() + "&lang=" + cVarC.f() + "&locale=" + cVarC.v() + "&app_name=" + cVarC.d() + "&ch=" + cVarC.i() + "&os_type=" + cVarC.a() + "&sdk_version=" + cVarC.g() + "&app_key=" + cVarC.h() + "&iid=" + cVarC.r() + "&vc=" + cVarC.e() + "&os_name=" + cVarC.m() + "&os_version=" + cVarC.l() + "&did=" + cVarC.s() + "&user_id=" + cVarC.t() + "&session_id=" + cVarC.u() + "&region=" + cVarC.b().a() + "&device_brand=" + cVarC.j() + "&device_model=" + cVarC.k();
    }

    private void k() {
        long jCurrentTimeMillis = this.J - System.currentTimeMillis();
        if (jCurrentTimeMillis > this.K) {
            jCurrentTimeMillis = this.K;
        }
        if (jCurrentTimeMillis < com.bytedance.b.a.a.c.g.e) {
            jCurrentTimeMillis = 30000;
        }
        j.a().a(2);
        j.a().a(2, null, jCurrentTimeMillis);
    }

    public String d() {
        return this.D;
    }

    public String e() {
        return this.E;
    }

    public String f() {
        if (this.L) {
            return a(this.C);
        }
        return this.C;
    }

    private String c(String str) {
        switch (z) {
            case REGION_USA_EAST:
                if ("1233".equals(this.M)) {
                }
                break;
            case REGION_SINGAPOER:
                if ("1180".equals(this.M)) {
                }
                break;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        k();
    }

    public void g() throws JSONException {
        this.L = com.bytedance.bdturing.a.a().c().o();
        i();
    }

    public static String a(String str) {
        try {
            return BDAccountNetApi.Platform.PROJECT_MODE_SCHEME + new URL(str).getHost() + k;
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            return str;
        }
    }
}
