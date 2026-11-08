package com.alipay.sdk.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    public static final String f2663a = "resultStatus";

    /* renamed from: b, reason: collision with root package name */
    public static final String f2664b = "memo";

    /* renamed from: c, reason: collision with root package name */
    public static final String f2665c = "result";

    public static Map<String, String> a(com.alipay.sdk.sys.a aVar, String str) {
        Map<String, String> mapA = a();
        try {
            return a(str);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.h, th);
            return mapA;
        }
    }

    private static Map<String, String> a() {
        com.alipay.sdk.app.c cVarB = com.alipay.sdk.app.c.b(com.alipay.sdk.app.c.CANCELED.a());
        HashMap map = new HashMap();
        map.put(f2663a, Integer.toString(cVarB.a()));
        map.put(f2664b, cVarB.b());
        map.put("result", "");
        return map;
    }

    public static Map<String, String> a(String str) {
        String[] strArrSplit = str.split(f.f2657b);
        HashMap map = new HashMap();
        for (String str2 : strArrSplit) {
            String strSubstring = str2.substring(0, str2.indexOf("={"));
            map.put(strSubstring, b(str2, strSubstring));
        }
        return map;
    }

    private static String b(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf(f.d));
    }

    public static String a(String str, String str2) {
        try {
            Matcher matcher = Pattern.compile("(^|;)" + str2 + "=\\{([^}]*?)\\}").matcher(str);
            return matcher.find() ? matcher.group(2) : "?";
        } catch (Throwable th) {
            c.a(th);
            return "?";
        }
    }
}
