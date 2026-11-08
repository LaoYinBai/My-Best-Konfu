package com.huawei.a.a.b.b;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.a.a.b.c.a.d;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b {
    public static String a(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(com.huawei.a.a.a.a.c(str, str2))) {
            return com.huawei.a.a.a.a.c(str, str2);
        }
        com.huawei.a.a.b.e.a.b("hmsSdk", "getAndroidId(): to getConfigByType()");
        return c(context, str, str2);
    }

    public static String b(Context context, String str, String str2) {
        if (!str2.equals("oper") && !str2.equals("maint") && !str2.equals("diffprivacy") && !str2.equals("preins")) {
            com.huawei.a.a.b.e.a.c("hmsSdk", "getChannel(): Invalid type: " + str2);
            return "";
        }
        return d(context, str, str2);
    }

    private static String c(Context context, String str, String str2) {
        if (!com.huawei.a.a.a.a.i(str, str2)) {
            return "";
        }
        if (TextUtils.isEmpty(com.huawei.a.a.a.b.c())) {
            com.huawei.a.a.b.c.a.a.a().c().f(com.huawei.a.a.c.b.c.c(context));
        }
        return com.huawei.a.a.a.b.c();
    }

    private static String d(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(com.huawei.a.a.a.c.b(str, str2))) {
            return com.huawei.a.a.a.c.b(str, str2);
        }
        d dVarC = com.huawei.a.a.b.c.a.a.a().c();
        if (TextUtils.isEmpty(dVarC.e())) {
            String strD = com.huawei.a.a.c.b.c.d(context);
            if (!com.huawei.a.a.b.k.c.a("channel", strD, 256)) {
                strD = "";
            }
            dVarC.b(strD);
        }
        return dVarC.e();
    }
}
