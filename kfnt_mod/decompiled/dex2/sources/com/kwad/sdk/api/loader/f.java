package com.kwad.sdk.api.loader;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class f {
    static String a = "newversion";
    static String b = "curversion";
    static String c = "apiversion";
    static String d = "_enable";

    static String a(Context context) {
        return a(context, c);
    }

    static String a(Context context, String str) {
        return q.b(context, str, "");
    }

    static void a(Context context, String str, String str2) {
        q.a(context, str, str2);
    }

    static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return false;
        }
        if (!TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return true;
        }
        String[] strArrSplit = str.split("\\.");
        String[] strArrSplit2 = str2.split("\\.");
        for (int i = 0; i < strArrSplit.length && i < strArrSplit2.length; i++) {
            try {
                int i2 = Integer.parseInt(strArrSplit[i]) - Integer.parseInt(strArrSplit2[i]);
                if (i2 > 0) {
                    return true;
                }
                if (i2 < 0) {
                    return false;
                }
            } catch (NumberFormatException unused) {
                return false;
            }
        }
        return strArrSplit.length > strArrSplit2.length;
    }

    static void b(Context context, String str) {
        a(context, c, str);
    }
}
