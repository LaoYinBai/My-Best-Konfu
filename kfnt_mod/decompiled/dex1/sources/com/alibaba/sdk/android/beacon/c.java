package com.alibaba.sdk.android.beacon;

import android.content.Context;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class c {

    /* renamed from: a, reason: collision with root package name */
    private static final char[] f2317a = "0123456789abcdef".toCharArray();

    /* JADX WARN: Unreachable blocks removed: 2, instructions: 4 */
    static String a(Context context) {
        if (context == null) {
            return "";
        }
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            return str == null ? "" : str;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    static String a(String str, String str2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "HmacSHA1");
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKeySpec);
            return a(mac.doFinal(str2.getBytes()));
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    private static String a(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            cArr[i3] = f2317a[i2 >>> 4];
            cArr[i3 + 1] = f2317a[i2 & 15];
        }
        return new String(cArr);
    }
}
