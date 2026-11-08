package com.umeng.message.common;

import android.content.Context;
import android.os.Build;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.proguard.o;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.proguard.k;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    public static final String a = "umid";
    private static final String ak = "Android";
    public static final String b = "Android";
    private static final String c = "com.umeng.message.common.b";
    private int A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private String G;
    private String H;
    private final String I = "appkey";
    private final String J = "channel";
    private final String K = "device_id";
    private final String L = "idmd5";
    private final String M = o.z;
    private final String N = "din";
    private final String O = "push_switch";
    private final String P = o.T;
    private final String Q = "android_id";
    private final String R = "serial_number";
    private final String S = o.C;
    private final String T = "os";
    private final String U = o.x;
    private final String V = o.y;
    private final String W = o.v;
    private final String X = "gpu_vender";
    private final String Y = "gpu_renderer";
    private final String Z = "app_version";
    private final String aa = "version_code";
    private final String ab = "package_name";
    private final String ac = o.t;
    private final String ad = "sdk_version";
    private final String ae = o.L;
    private final String af = o.N;
    private final String ag = o.M;
    private final String ah = o.P;
    private final String ai = o.Q;
    private final String aj = o.O;
    private final String al = o.h;
    private final String am = o.i;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private long l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    public b() {
    }

    public b(String str, String str2) {
        this.d = str;
        this.e = str2;
    }

    private void d(JSONObject jSONObject) throws Exception {
        this.d = jSONObject.getString("appkey");
        this.f = jSONObject.getString("device_id");
        this.g = jSONObject.getString("idmd5");
        if (jSONObject.has(o.z)) {
            this.h = jSONObject.getString(o.z);
        }
        if (jSONObject.has("channel")) {
            this.e = jSONObject.getString("channel");
        }
        if (jSONObject.has(o.T)) {
            this.l = jSONObject.getLong(o.T);
        }
    }

    private void e(JSONObject jSONObject) throws Exception {
        this.o = jSONObject.has(o.C) ? jSONObject.getString(o.C) : null;
        this.p = jSONObject.has("os") ? jSONObject.getString("os") : null;
        this.q = jSONObject.has(o.x) ? jSONObject.getString(o.x) : null;
        this.r = jSONObject.has(o.y) ? jSONObject.getString(o.y) : null;
        this.s = jSONObject.has(o.v) ? jSONObject.getString(o.v) : null;
        this.t = jSONObject.has("gpu_vender") ? jSONObject.getString("gpu_vender") : null;
        this.u = jSONObject.has("gpu_renderer") ? jSONObject.getString("gpu_renderer") : null;
        this.m = jSONObject.has("android_id") ? jSONObject.getString("android_id") : null;
        this.n = jSONObject.has("serial_number") ? jSONObject.getString("serial_number") : null;
    }

    private void f(JSONObject jSONObject) throws Exception {
        this.v = jSONObject.has("app_version") ? jSONObject.getString("app_version") : null;
        this.w = jSONObject.has("version_code") ? jSONObject.getString("version_code") : null;
        this.x = jSONObject.has("package_name") ? jSONObject.getString("package_name") : null;
    }

    private void g(JSONObject jSONObject) throws Exception {
        this.y = jSONObject.getString(o.t);
        this.z = jSONObject.getString("sdk_version");
    }

    private void h(JSONObject jSONObject) throws Exception {
        this.A = jSONObject.has(o.L) ? jSONObject.getInt(o.L) : 8;
        this.B = jSONObject.has(o.N) ? jSONObject.getString(o.N) : null;
        this.C = jSONObject.has(o.M) ? jSONObject.getString(o.M) : null;
    }

    private void i(JSONObject jSONObject) throws Exception {
        this.D = jSONObject.has(o.P) ? jSONObject.getString(o.P) : null;
        this.E = jSONObject.has(o.Q) ? jSONObject.getString(o.Q) : null;
        this.F = jSONObject.has(o.O) ? jSONObject.getString(o.O) : null;
    }

    private void j(JSONObject jSONObject) throws Exception {
        this.G = jSONObject.has(o.h) ? jSONObject.getString(o.h) : null;
        this.H = jSONObject.has(o.i) ? jSONObject.getString(o.i) : null;
    }

    public void a(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return;
        }
        d(jSONObject);
        e(jSONObject);
        f(jSONObject);
        g(jSONObject);
        h(jSONObject);
        i(jSONObject);
        j(jSONObject);
    }

    private void k(JSONObject jSONObject) throws Exception {
        jSONObject.put("appkey", this.d);
        if (this.d == null || 24 != this.d.length()) {
            this.f = com.umeng.message.proguard.c.a(this.f, "utf-8");
            this.j = com.umeng.message.proguard.c.a(this.j, "utf-8");
        } else {
            this.f = com.umeng.message.proguard.c.a(this.f, "utf-8", this.d.substring(0, 16));
            this.j = com.umeng.message.proguard.c.a(this.j, "utf-8", this.d.substring(0, 16));
        }
        jSONObject.put("device_id", this.f);
        jSONObject.put("idmd5", this.g);
        if (this.e != null) {
            jSONObject.put("channel", this.e);
        }
        if (this.h != null) {
            jSONObject.put(o.z, this.h);
        }
        if (this.l > 0) {
            jSONObject.put(o.T, this.l);
        }
        if (this.m != null) {
            jSONObject.put("android_id", this.m);
        }
        if (this.n != null) {
            jSONObject.put("serial_number", this.n);
        }
        jSONObject.put("umid", this.i);
        jSONObject.put("din", this.j);
        jSONObject.put("push_switch", this.k);
    }

    private void l(JSONObject jSONObject) throws Exception {
        jSONObject.put("appkey", this.d);
        if (this.d == null || 24 != this.d.length()) {
            this.j = com.umeng.message.proguard.c.a(this.j, "utf-8");
        } else {
            this.j = com.umeng.message.proguard.c.a(this.j, "utf-8", this.d.substring(0, 16));
        }
        if (this.e != null) {
            jSONObject.put("channel", this.e);
        }
        jSONObject.put("umid", this.i);
        jSONObject.put("din", this.j);
        jSONObject.put("push_switch", this.k);
    }

    private void m(JSONObject jSONObject) throws Exception {
        if (this.o != null) {
            jSONObject.put(o.C, this.o);
        }
        if (this.p != null) {
            jSONObject.put("os", this.p);
        }
        if (this.q != null) {
            jSONObject.put(o.x, this.q);
        }
        if (this.r != null) {
            jSONObject.put(o.y, this.r);
        }
        if (this.s != null) {
            jSONObject.put(o.v, this.s);
        }
        if (this.t != null) {
            jSONObject.put("gpu_vender", this.t);
        }
        if (this.u != null) {
            jSONObject.put("gpu_vender", this.u);
        }
    }

    private void n(JSONObject jSONObject) throws Exception {
        if (this.o != null) {
            jSONObject.put(o.C, this.o);
        }
        if (this.p != null) {
            jSONObject.put("os", this.p);
        }
        if (this.q != null) {
            jSONObject.put(o.x, this.q);
        }
    }

    private void o(JSONObject jSONObject) throws Exception {
        if (this.v != null) {
            jSONObject.put("app_version", this.v);
        }
        if (this.w != null) {
            jSONObject.put("version_code", this.w);
        }
        if (this.x != null) {
            jSONObject.put("package_name", this.x);
        }
    }

    private void p(JSONObject jSONObject) throws Exception {
        if (this.v != null) {
            jSONObject.put("app_version", this.v);
        }
        if (this.w != null) {
            jSONObject.put("version_code", this.w);
        }
    }

    private void q(JSONObject jSONObject) throws Exception {
        jSONObject.put(o.t, this.y);
        jSONObject.put("sdk_version", this.z);
    }

    private void r(JSONObject jSONObject) throws Exception {
        jSONObject.put(o.L, this.A);
        if (this.B != null) {
            jSONObject.put(o.N, this.B);
        }
        if (this.C != null) {
            jSONObject.put(o.M, this.C);
        }
    }

    private void s(JSONObject jSONObject) throws Exception {
        if (this.D != null) {
            jSONObject.put(o.P, this.D);
        }
        if (this.E != null) {
            jSONObject.put(o.Q, this.E);
        }
        if (this.F != null) {
            jSONObject.put(o.O, this.F);
        }
    }

    private void t(JSONObject jSONObject) throws Exception {
        if (this.G != null) {
            jSONObject.put(o.h, this.G);
        }
        if (this.H != null) {
            jSONObject.put(o.i, this.H);
        }
    }

    public void b(JSONObject jSONObject) throws Exception {
        k(jSONObject);
        m(jSONObject);
        o(jSONObject);
        q(jSONObject);
        r(jSONObject);
        s(jSONObject);
        t(jSONObject);
    }

    public void c(JSONObject jSONObject) throws Exception {
        l(jSONObject);
        n(jSONObject);
        p(jSONObject);
        q(jSONObject);
        s(jSONObject);
    }

    public boolean a() {
        if (this.d == null) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(c, 0, "missing appkey");
            return false;
        }
        if (this.f != null && this.g != null) {
            return true;
        }
        UMLog uMLog2 = UMConfigure.umDebugLog;
        UMLog.mutlInfo(c, 0, "missing device id");
        return false;
    }

    public void a(Context context, String... strArr) {
        if (strArr != null && strArr.length == 2) {
            this.d = strArr[0];
            this.e = strArr[1];
        }
        if (this.d == null) {
            this.d = PushAgent.getInstance(context).getMessageAppkey();
        }
        if (this.e == null) {
            this.e = PushAgent.getInstance(context).getMessageChannel();
        }
        this.f = UmengMessageDeviceConfig.getDeviceId(context);
        this.g = UmengMessageDeviceConfig.getDeviceIdMD5(context);
        this.h = UmengMessageDeviceConfig.getDummyId(context);
        this.j = UmengMessageDeviceConfig.getDIN(context);
        this.i = UmengMessageDeviceConfig.getUmid(context);
        this.k = UmengMessageDeviceConfig.isNotificationEnabled(context);
        if ("false".equals(this.k)) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.aq(k.c, 0, "\\|");
        }
    }

    private void a(Context context) {
        this.o = Build.MODEL;
        this.p = "Android";
        this.q = Build.VERSION.RELEASE;
        this.r = UmengMessageDeviceConfig.getResolution(context);
        this.s = UmengMessageDeviceConfig.getCPU();
        this.m = UmengMessageDeviceConfig.getAndroidId(context);
        this.n = UmengMessageDeviceConfig.getSerial_number();
    }

    private void b(Context context) {
        this.v = UmengMessageDeviceConfig.getAppVersionName(context);
        this.w = UmengMessageDeviceConfig.getAppVersionCode(context);
        this.x = UmengMessageDeviceConfig.getPackageName(context);
    }

    private void c(Context context) {
        this.y = "Android";
        this.z = MsgConstant.SDK_VERSION;
    }

    private void d(Context context) {
        this.A = UmengMessageDeviceConfig.getTimeZone(context);
        String[] localeInfo = UmengMessageDeviceConfig.getLocaleInfo(context);
        this.B = localeInfo[0];
        this.C = localeInfo[1];
    }

    private void e(Context context) {
        String[] networkAccessMode = UmengMessageDeviceConfig.getNetworkAccessMode(context);
        this.D = networkAccessMode[0];
        this.E = networkAccessMode[1];
        this.F = UmengMessageDeviceConfig.getOperator(context);
    }

    public void b(Context context, String... strArr) {
        a(context, strArr);
        a(context);
        b(context);
        c(context);
        d(context);
        e(context);
    }

    public void c(Context context, String... strArr) {
        a(context, strArr);
        a(context);
        b(context);
        c(context);
        e(context);
    }

    public boolean b() {
        return (this.d == null || this.f == null) ? false : true;
    }
}
