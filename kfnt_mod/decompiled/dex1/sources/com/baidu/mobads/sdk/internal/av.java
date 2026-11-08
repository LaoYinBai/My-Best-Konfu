package com.baidu.mobads.sdk.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class av {

    /* renamed from: a, reason: collision with root package name */
    public static final String f3132a = "mobads_builds";

    /* renamed from: b, reason: collision with root package name */
    public static final String f3133b = "brand_period";

    /* renamed from: c, reason: collision with root package name */
    public static final String f3134c = "version_period";
    public static final long d = 604800000;
    public static final long e = 172800000;
    public static final String f = "android_id";
    public static final String g = "sdk_int";
    public static final String h = "sdk";
    public static final String i = "release";
    public static final String j = "model";
    public static final String k = "brand";
    public static final String l = "netopera";
    public static final String m = "tags";
    private static long x;
    private static long y;
    private String n;
    private int o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private Context v;
    private SharedPreferences w;

    private av() {
        this.n = "";
        this.o = 0;
        this.p = "";
        this.q = "";
        this.r = "";
        this.s = "";
        this.t = "";
        this.u = "";
    }

    private static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final av f3135a = new av();

        private a() {
        }
    }

    public static av a(Context context) {
        a.f3135a.b(context);
        return a.f3135a;
    }

    public void b(Context context) {
        if (this.v != null || context == null) {
            return;
        }
        this.v = context.getApplicationContext();
        try {
            if (this.w == null) {
                this.w = this.v.getSharedPreferences(f3132a, 0);
                i();
            }
            j();
        } catch (Throwable th) {
            bb.a().c(th.getMessage());
        }
    }

    private void i() {
        k();
        l();
    }

    private void j() {
        if (System.currentTimeMillis() > x) {
            k();
        }
        if (System.currentTimeMillis() > y) {
            l();
        }
    }

    private void k() {
        if (0 == x) {
            x = b(f3133b).longValue();
        }
        try {
            if (System.currentTimeMillis() > x) {
                this.n = Settings.Secure.getString(this.v.getContentResolver(), f);
                this.r = Build.MODEL;
                this.s = Build.BRAND;
                this.t = ((TelephonyManager) this.v.getSystemService("phone")).getNetworkOperator();
                this.u = Build.TAGS;
                a(f, this.n);
                a(j, this.r);
                a(k, this.s);
                a(l, this.t);
                a(m, this.u);
                a(f3133b, Long.valueOf(System.currentTimeMillis() + 604800000));
                return;
            }
            this.n = a(f);
            this.r = a(j);
            this.s = a(k);
            this.t = a(l);
            this.u = a(m);
        } catch (Throwable th) {
            bb.a().c(th.getMessage());
        }
    }

    private void l() {
        if (0 == y) {
            y = b(f3134c).longValue();
        }
        if (System.currentTimeMillis() > y) {
            this.o = Build.VERSION.SDK_INT;
            this.p = Build.VERSION.SDK;
            this.q = Build.VERSION.RELEASE;
            a(g, this.o);
            a("sdk", this.p);
            a("release", this.q);
            a(f3134c, Long.valueOf(System.currentTimeMillis() + e));
            return;
        }
        this.o = c(g);
        this.p = a("sdk");
        this.q = a("release");
    }

    public int a() {
        if (this.o == 0) {
            this.o = Build.VERSION.SDK_INT;
        }
        return this.o;
    }

    public String b() {
        return this.n;
    }

    public String c() {
        if (TextUtils.isEmpty(this.p)) {
            this.p = Build.VERSION.SDK;
        }
        return this.p;
    }

    public String d() {
        return this.q;
    }

    public String e() {
        return this.r;
    }

    public String f() {
        return this.s;
    }

    public String g() {
        return this.t;
    }

    public String h() {
        return this.u;
    }

    private SharedPreferences.Editor m() {
        return this.w.edit();
    }

    private String a(String str) {
        try {
            return this.w.getString(str, "");
        } catch (Throwable th) {
            bb.a().c(th.getMessage());
            return "";
        }
    }

    private Long b(String str) {
        try {
            return Long.valueOf(this.w.getLong(str, 0L));
        } catch (Throwable th) {
            bb.a().c(th.getMessage());
            return 0L;
        }
    }

    private int c(String str) {
        try {
            return this.w.getInt(str, 0);
        } catch (Throwable th) {
            bb.a().c(th.getMessage());
            return 0;
        }
    }

    @TargetApi(9)
    private void a(String str, String str2) {
        try {
            SharedPreferences.Editor editorM = m();
            editorM.putString(str, str2);
            editorM.apply();
        } catch (Throwable th) {
            bb.a().c(th.getMessage());
        }
    }

    @TargetApi(9)
    private void a(String str, Long l2) {
        try {
            SharedPreferences.Editor editorM = m();
            editorM.putLong(str, l2.longValue());
            editorM.apply();
        } catch (Throwable th) {
            bb.a().c(th.getMessage());
        }
    }

    @TargetApi(9)
    private void a(String str, int i2) {
        try {
            SharedPreferences.Editor editorM = m();
            editorM.putInt(str, i2);
            editorM.apply();
        } catch (Throwable th) {
            bb.a().c(th.getMessage());
        }
    }
}
