package com.cmic.sso.sdk.d;

import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class p {
    public static String a() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date(System.currentTimeMillis()));
    }

    private static String a(Date date) {
        return a("yyyyMMdd", date);
    }

    static String b() {
        return a(new Date());
    }

    private static String a(String str, Date date) {
        if (date == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return new SimpleDateFormat(str).format(date);
    }
}
