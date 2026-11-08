package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do;

import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.d0;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.u;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static String f1934a;

    /* renamed from: b, reason: collision with root package name */
    private static String f1935b;

    public static String a() {
        return d0.a("https://" + a.f().g());
    }

    public static String b() {
        return d0.a(c() + "/api/ad/union/mediation/exchange/");
    }

    private static String c() {
        String strE = e();
        return !TextUtils.isEmpty(strE) ? strE : "https://pangolin.snssdk.com";
    }

    public static String d() {
        return d0.a(c() + "/api/ad/union/mediation/stats/");
    }

    private static String e() {
        String strD = u.a("tt_sdk_settings", a.d()).d("server_dist_host");
        if (TextUtils.isEmpty(strD)) {
            return null;
        }
        if (TextUtils.equals(strD, f1934a) && !TextUtils.isEmpty(f1935b)) {
            return f1935b;
        }
        f1934a = strD;
        f1935b = null;
        if (!TextUtils.isEmpty(f1934a)) {
            f1935b = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.a.a(f1934a, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.b.a());
        }
        if (TextUtils.isEmpty(f1935b)) {
            return null;
        }
        f1935b = "https://" + f1935b;
        return f1935b;
    }

    public static String f() {
        return d0.a(c() + "/api/ad/union/mediation/config/");
    }
}
