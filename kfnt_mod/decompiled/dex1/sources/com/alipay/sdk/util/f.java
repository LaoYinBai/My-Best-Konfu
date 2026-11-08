package com.alipay.sdk.util;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    public static final String f2656a = "pref_trade_token";

    /* renamed from: b, reason: collision with root package name */
    public static final String f2657b = ";";

    /* renamed from: c, reason: collision with root package name */
    public static final String f2658c = "result={";
    public static final String d = "}";
    public static final String e = "trade_token=\"";
    public static final String f = "\"";
    public static final String g = "trade_token=";

    public static void a(com.alipay.sdk.sys.a aVar, Context context, String str) {
        try {
            String strA = a(str);
            c.a(com.alipay.sdk.cons.a.x, "trade token: " + strA);
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            g.a(aVar, context, f2656a, strA);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.C, th);
            c.a(th);
        }
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] strArrSplit = str.split(f2657b);
        String strSubstring = null;
        for (int i = 0; i < strArrSplit.length; i++) {
            if (strArrSplit[i].startsWith(f2658c) && strArrSplit[i].endsWith(d)) {
                String[] strArrSplit2 = strArrSplit[i].substring(f2658c.length(), strArrSplit[i].length() - d.length()).split("&");
                int i2 = 0;
                while (true) {
                    if (i2 >= strArrSplit2.length) {
                        break;
                    }
                    if (strArrSplit2[i2].startsWith(e) && strArrSplit2[i2].endsWith("\"")) {
                        strSubstring = strArrSplit2[i2].substring(e.length(), strArrSplit2[i2].length() - "\"".length());
                        break;
                    }
                    if (strArrSplit2[i2].startsWith(g)) {
                        strSubstring = strArrSplit2[i2].substring(g.length());
                        break;
                    }
                    i2++;
                }
            }
        }
        return strSubstring;
    }

    public static String a(com.alipay.sdk.sys.a aVar, Context context) {
        String strB = g.b(aVar, context, f2656a, "");
        c.a(com.alipay.sdk.cons.a.x, "get trade token: " + strB);
        return strB;
    }
}
