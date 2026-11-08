package com.cmic.sso.sdk.d;

import android.content.Context;
import android.text.TextUtils;
import com.cmic.sso.sdk.d.k;
import com.cmic.sso.sdk.d.o;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h {

    /* renamed from: b, reason: collision with root package name */
    private static String f5451b;

    /* renamed from: c, reason: collision with root package name */
    private static String f5452c;

    /* renamed from: a, reason: collision with root package name */
    private static boolean f5450a = m.a();
    private static long d = 0;

    public static void a(boolean z, boolean z2) {
        k.a aVarA = k.a();
        aVarA.a("phonescripstarttime");
        aVarA.a("phonescripcache");
        aVarA.a("pre_sim_key");
        if (z2) {
            aVarA.a();
        } else {
            aVarA.b();
        }
        if (z) {
            f5451b = null;
            f5452c = null;
            d = 0L;
        }
    }

    public static boolean a() {
        return f5450a;
    }

    public static void a(final Context context, final String str, final long j, final String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        f5451b = str;
        d = j;
        f5452c = str2;
        if (f5450a) {
            return;
        }
        o.a(new o.a() { // from class: com.cmic.sso.sdk.d.h.1
            @Override // com.cmic.sso.sdk.d.o.a
            protected void a() {
                c.b("PhoneScripUtils", "start save scrip to sp in sub thread");
                h.c(context, str, j, str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context, String str, long j, String str2) {
        String strA = b.a(context, str);
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        k.a aVarA = k.a();
        aVarA.a("phonescripcache", strA);
        aVarA.a("phonescripstarttime", j);
        aVarA.a("pre_sim_key", str2);
        aVarA.b();
    }

    public static String a(Context context) {
        if (TextUtils.isEmpty(f5451b)) {
            String strB = k.b("phonescripcache", "");
            if (TextUtils.isEmpty(strB)) {
                c.a("PhoneScripUtils", "null");
                return null;
            }
            String strB2 = b.b(context, strB);
            f5451b = strB2;
            return strB2;
        }
        return f5451b;
    }

    private static boolean b() {
        if (TextUtils.isEmpty(f5451b)) {
            return !TextUtils.isEmpty(k.b("phonescripcache", "")) && a(k.a("phonescripstarttime", 0L));
        }
        c.b("PhoneScripUtils", f5452c + " " + d);
        return a(d);
    }

    private static boolean a(long j) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        c.b("PhoneScripUtils", j + "");
        c.b("PhoneScripUtils", jCurrentTimeMillis + "");
        return j - jCurrentTimeMillis > com.bytedance.b.a.a.c.g.g;
    }

    private static int a(String str) {
        String strB;
        if (!TextUtils.isEmpty(f5452c)) {
            strB = f5452c;
        } else {
            strB = k.b("pre_sim_key", "");
            f5452c = strB;
        }
        if (TextUtils.isEmpty(strB)) {
            return 0;
        }
        return strB.equals(str) ? 1 : 2;
    }

    public static boolean a(com.cmic.sso.sdk.a aVar) {
        int iA;
        if (!aVar.b("keyIsSimKeyICCID", false)) {
            iA = a(aVar.b("imsi"));
        } else {
            iA = a(aVar.b("iccid"));
        }
        aVar.a("imsiState", iA + "");
        c.b("PhoneScripUtils", "simState = " + iA);
        if (iA != 1) {
            if (iA == 2) {
                a(true, false);
            }
            return false;
        }
        if (f5450a) {
            c.b("PhoneScripUtils", "phone is root");
            a(false, false);
        }
        return b();
    }
}
