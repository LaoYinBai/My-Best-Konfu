package com.alipay.security.mobile.module.c;

import android.content.Context;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {
    public static String a(Context context, String str, String str2) {
        String strB;
        String strA;
        synchronized (a.class) {
            if (context != null) {
                try {
                    if (!com.alipay.security.mobile.module.a.a.a(str) && !com.alipay.security.mobile.module.a.a.a(str2)) {
                        try {
                            strA = e.a(context, str, str2, "");
                        } catch (Throwable unused) {
                            strB = null;
                        }
                        if (com.alipay.security.mobile.module.a.a.a(strA)) {
                            return null;
                        }
                        strB = com.alipay.security.mobile.module.a.a.c.b(com.alipay.security.mobile.module.a.a.c.a(), strA);
                        return strB;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return null;
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        synchronized (a.class) {
            if (com.alipay.security.mobile.module.a.a.a(str) || com.alipay.security.mobile.module.a.a.a(str2) || context == null) {
                return;
            }
            try {
                String strA = com.alipay.security.mobile.module.a.a.c.a(com.alipay.security.mobile.module.a.a.c.a(), str3);
                HashMap map = new HashMap();
                map.put(str2, strA);
                e.a(context, str, map);
            } catch (Throwable unused) {
            }
        }
    }
}
