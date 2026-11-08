package com.huawei.updatesdk.service.d.a;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    private static final String a = "a";
    private static String b;

    private a() {
    }

    public static String a() throws PackageManager.NameNotFoundException {
        if (b != null) {
            return b;
        }
        try {
            String packageName = com.huawei.updatesdk.a.b.a.a.a().b().getPackageName();
            PackageInfo packageInfo = com.huawei.updatesdk.a.b.a.a.a().b().getPackageManager().getPackageInfo(com.huawei.updatesdk.a.b.a.a.a().b().getPackageName(), 0);
            if (packageInfo != null) {
                packageName = packageName + packageInfo.versionName;
            }
            String str = Build.BRAND;
            if (TextUtils.isEmpty(str)) {
                str = "other";
            }
            a(packageName + "_" + str);
            return b;
        } catch (PackageManager.NameNotFoundException e) {
            com.huawei.updatesdk.a.a.b.a.a.a.d(a, "getUserAgent() " + e.toString());
            return null;
        }
    }

    private static void a(String str) {
        b = str;
    }
}
