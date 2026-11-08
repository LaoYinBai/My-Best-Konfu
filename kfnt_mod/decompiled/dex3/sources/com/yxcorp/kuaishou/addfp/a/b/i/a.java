package com.yxcorp.kuaishou.addfp.a.b.i;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class a {
    private static Object a;
    private static Class b;

    static {
        try {
            Class<?> cls = Class.forName("com.android.id.impl.IdProviderImpl");
            b = cls;
            a = cls.newInstance();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static String a(Context context) {
        return a(context, "getOAID");
    }

    private static String a(Context context, String str) {
        if (a == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Object objInvoke = b.getMethod(str, Context.class).invoke(a, context);
            if (objInvoke != null) {
                return (String) objInvoke;
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static boolean a() {
        return (b == null || a == null) ? false : true;
    }
}
