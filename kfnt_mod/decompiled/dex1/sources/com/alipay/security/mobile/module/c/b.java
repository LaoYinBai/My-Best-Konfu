package com.alipay.security.mobile.module.c;

import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class b {
    public static String a(String str) {
        String strA = "";
        try {
            strA = f.a(str);
        } catch (Throwable unused) {
        }
        if (!com.alipay.security.mobile.module.a.a.a(strA)) {
            return strA;
        }
        return c.a(".SystemConfig" + File.separator + str);
    }
}
