package com.alipay.sdk.util;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    private static String f2659a;

    public static synchronized boolean a(Context context, String str) {
        boolean zContains;
        try {
            zContains = PreferenceManager.getDefaultSharedPreferences(context).contains(str);
        } catch (Throwable th) {
            c.a(th);
            zContains = false;
        }
        return zContains;
    }

    public static synchronized void b(Context context, String str) {
        try {
            PreferenceManager.getDefaultSharedPreferences(context).edit().remove(str).apply();
        } catch (Throwable th) {
            c.a(th);
        }
    }

    public static synchronized void a(com.alipay.sdk.sys.a aVar, Context context, String str, String str2) {
        try {
            String strA = com.alipay.sdk.encrypt.e.a(a(context), str2, str);
            if (!TextUtils.isEmpty(str2) && TextUtils.isEmpty(strA)) {
                com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2573c, com.alipay.sdk.app.statistic.b.A, String.format("%s,%s", str, str2));
            }
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(str, strA).apply();
        } catch (Throwable th) {
            c.a(th);
        }
    }

    public static synchronized String b(com.alipay.sdk.sys.a aVar, Context context, String str, String str2) {
        String strB;
        try {
            String string = PreferenceManager.getDefaultSharedPreferences(context).getString(str, str2);
            strB = TextUtils.isEmpty(string) ? null : com.alipay.sdk.encrypt.e.b(a(context), string, str);
            if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(strB)) {
                com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2573c, com.alipay.sdk.app.statistic.b.z, String.format("%s,%s", str, string));
            }
        } catch (Exception e) {
            c.a(e);
        }
        return strB;
    }

    private static String a(Context context) {
        String packageName;
        if (TextUtils.isEmpty(f2659a)) {
            try {
                packageName = context.getApplicationContext().getPackageName();
            } catch (Throwable th) {
                c.a(th);
                packageName = "";
            }
            f2659a = (packageName + "0000000000000000000000000000").substring(0, 24);
        }
        return f2659a;
    }
}
