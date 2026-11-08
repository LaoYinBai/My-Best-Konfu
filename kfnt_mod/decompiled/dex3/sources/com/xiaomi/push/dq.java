package com.xiaomi.push;

import android.content.Context;
import android.os.Build;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class dq {
    private static void a(byte[] bArr) {
        if (bArr.length >= 2) {
            bArr[0] = 99;
            bArr[1] = 100;
        }
    }

    public static boolean a(Context context, String str, long j) {
        if (com.xiaomi.push.service.aq.a(context).a(ho.DCJobMutualSwitch.a(), false)) {
            return (Build.VERSION.SDK_INT < 29 || context.getApplicationInfo().targetSdkVersion < 29) && !ag.a(context, str, j);
        }
        return false;
    }

    public static byte[] a(String str, byte[] bArr) {
        byte[] bArrM132a = bj.m132a(str);
        try {
            a(bArrM132a);
            return h.a(bArrM132a, bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] b(String str, byte[] bArr) {
        byte[] bArrM132a = bj.m132a(str);
        try {
            a(bArrM132a);
            return h.b(bArrM132a, bArr);
        } catch (Exception unused) {
            return null;
        }
    }
}
