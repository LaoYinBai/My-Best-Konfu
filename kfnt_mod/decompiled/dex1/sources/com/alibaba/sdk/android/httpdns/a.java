package com.alibaba.sdk.android.httpdns;

import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class a {

    /* renamed from: a, reason: collision with root package name */
    private static long f2318a;
    private static String sSecretKey;

    static String a(String str, String str2) {
        if (!k.b(str)) {
            return "";
        }
        try {
            return k.a(str + "-" + sSecretKey + "-" + str2);
        } catch (Exception unused) {
            return "";
        }
    }

    static boolean a() {
        return !TextUtils.isEmpty(sSecretKey);
    }

    static String getTimestamp() {
        return String.valueOf((System.currentTimeMillis() / 1000) + f2318a + 600);
    }

    static void setAuthCurrentTime(long j) {
        f2318a = j - (System.currentTimeMillis() / 1000);
    }

    static void setSecretKey(String str) {
        sSecretKey = str;
    }
}
