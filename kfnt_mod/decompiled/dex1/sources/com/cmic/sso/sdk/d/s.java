package com.cmic.sso.sdk.d;

import android.text.TextUtils;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class s {

    /* renamed from: a, reason: collision with root package name */
    private static char[] f5470a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        char[] cArr = new char[bArr.length * 2];
        int i = 0;
        for (byte b2 : bArr) {
            int i2 = i + 1;
            cArr[i] = f5470a[(b2 >>> 4) & 15];
            i = i2 + 1;
            cArr[i2] = f5470a[b2 & 15];
        }
        return new String(cArr);
    }

    public static String a() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String b() {
        return c().replace("-", "");
    }

    private static String c() {
        return UUID.randomUUID().toString();
    }

    public static void a(com.cmic.sso.sdk.a aVar, String str) {
        if (TextUtils.isEmpty(aVar.b("interfaceType", ""))) {
            aVar.a("interfaceType", str);
            return;
        }
        aVar.a("interfaceType", aVar.b("interfaceType") + com.alipay.sdk.util.f.f2657b + str);
    }

    public static void b(com.cmic.sso.sdk.a aVar, String str) {
        if (TextUtils.isEmpty(aVar.b("interfaceCode", ""))) {
            aVar.a("interfaceCode", str);
            return;
        }
        aVar.a("interfaceCode", aVar.b("interfaceCode") + com.alipay.sdk.util.f.f2657b + str);
    }

    public static void c(com.cmic.sso.sdk.a aVar, String str) {
        if (TextUtils.isEmpty(aVar.b("interfaceElasped", ""))) {
            aVar.a("interfaceElasped", str);
            return;
        }
        aVar.a("interfaceElasped", aVar.b("interfaceElasped") + com.alipay.sdk.util.f.f2657b + str);
    }
}
