package com.huawei.hms.aaid.encrypt;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class PushEncrypter {
    public static String encrypter(Context context, String str) {
        return TextUtils.isEmpty(str) ? "" : com.huawei.hms.opendevice.a.a.a(str, c.a(context));
    }

    public static String decrypter(Context context, String str) {
        return TextUtils.isEmpty(str) ? "" : com.huawei.hms.opendevice.a.a.b(str, c.a(context));
    }

    public static String encrypterOld(Context context, String str) {
        return TextUtils.isEmpty(str) ? "" : com.huawei.hms.opendevice.a.a.a(str, c.c(context));
    }
}
