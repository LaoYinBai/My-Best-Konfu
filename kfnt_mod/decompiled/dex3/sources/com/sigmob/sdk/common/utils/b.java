package com.sigmob.sdk.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    public static PackageManager a(Context context) {
        if (context != null) {
            return context.getPackageManager();
        }
        return null;
    }

    public static List<PackageInfo> b(Context context) {
        try {
            return a(context).getInstalledPackages(0);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String c(Context context) {
        if (context == null) {
            return null;
        }
        return context.getPackageName();
    }

    public static String d(Context context) {
        try {
            String strC = c(context);
            if (TextUtils.isEmpty(strC)) {
                return null;
            }
            return a(context).getPackageInfo(strC, 0).versionName;
        } catch (Throwable unused) {
            SigmobLog.d("Failed to retrieve PackageInfo#versionName.");
            return null;
        }
    }
}
