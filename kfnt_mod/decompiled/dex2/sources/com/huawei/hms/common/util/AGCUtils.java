package com.huawei.hms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.HMSPackageManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AGCUtils {
    public static String getAppId(Context context) {
        if (a(context)) {
            return a(context, "client/app_id");
        }
        String strA = com.huawei.agconnect.a.a.a(context).a("client/app_id");
        return !TextUtils.isEmpty(strA) ? strA : b(context);
    }

    public static String getCpId(Context context) {
        if (a(context)) {
            return a(context, "client/cp_id");
        }
        String strA = com.huawei.agconnect.a.a.a(context).a("client/cp_id");
        return !TextUtils.isEmpty(strA) ? strA : c(context);
    }

    private static boolean a(Context context) {
        return context.getPackageName().equals(HMSPackageManager.getInstance(context).getHMSPackageName());
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0051 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(android.content.Context r5, java.lang.String r6) throws java.lang.Throwable {
        /*
            com.huawei.agconnect.a.a r0 = com.huawei.agconnect.a.a.a(r5)
            java.lang.String r1 = ""
            r2 = 0
            android.content.res.Resources r5 = r5.getResources()     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L28
            android.content.res.AssetManager r5 = r5.getAssets()     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L28
            java.lang.String r3 = "agconnect-services.json"
            java.io.InputStream r5 = r5.open(r3)     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L28
            r0.a(r5)     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L23
            java.lang.String r0 = r0.a(r6)     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L23
            com.huawei.hms.utils.IOUtils.closeQuietly(r5)
            goto L4b
        L20:
            r6 = move-exception
            r2 = r5
            goto L70
        L23:
            r0 = move-exception
            r2 = r5
            goto L29
        L26:
            r6 = move-exception
            goto L70
        L28:
            r0 = move-exception
        L29:
            java.lang.String r5 = "AGCUtils"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L26
            r3.<init>()     // Catch: java.lang.Throwable -> L26
            java.lang.String r4 = "Get "
            r3.append(r4)     // Catch: java.lang.Throwable -> L26
            r3.append(r6)     // Catch: java.lang.Throwable -> L26
            java.lang.String r4 = " failed: "
            r3.append(r4)     // Catch: java.lang.Throwable -> L26
            r3.append(r0)     // Catch: java.lang.Throwable -> L26
            java.lang.String r0 = r3.toString()     // Catch: java.lang.Throwable -> L26
            android.util.Log.e(r5, r0)     // Catch: java.lang.Throwable -> L26
            com.huawei.hms.utils.IOUtils.closeQuietly(r2)
            r0 = r1
        L4b:
            boolean r5 = android.text.TextUtils.isEmpty(r0)
            if (r5 != 0) goto L52
            return r0
        L52:
            java.lang.String r5 = "AGCUtils"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "The "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r6 = " is null."
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            android.util.Log.e(r5, r6)
            java.lang.String r5 = ""
            return r5
        L70:
            com.huawei.hms.utils.IOUtils.closeQuietly(r2)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.common.util.AGCUtils.a(android.content.Context, java.lang.String):java.lang.String");
    }

    private static String b(Context context) {
        Object obj;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            HMSLog.e("AGCUtils", "In getMetaDataAppId, Failed to get 'PackageManager' instance.");
            return "";
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getPackageInfo(context.getPackageName(), 128).applicationInfo;
            if (applicationInfo != null && applicationInfo.metaData != null && (obj = applicationInfo.metaData.get("com.huawei.hms.client.appid")) != null) {
                String strValueOf = String.valueOf(obj);
                return strValueOf.startsWith("appid=") ? strValueOf.substring("appid=".length()) : strValueOf;
            }
            HMSLog.e("AGCUtils", "In getMetaDataAppId, Failed to read meta data for the AppID.");
            return "";
        } catch (PackageManager.NameNotFoundException unused) {
            HMSLog.e("AGCUtils", "In getMetaDataAppId, Failed to read meta data for the AppID.");
            return "";
        }
    }

    private static String c(Context context) {
        Object obj;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            HMSLog.e("AGCUtils", "In getMetaDataCpId, Failed to get 'PackageManager' instance.");
            return "";
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getPackageInfo(context.getPackageName(), 128).applicationInfo;
            if (applicationInfo != null && applicationInfo.metaData != null && (obj = applicationInfo.metaData.get("com.huawei.hms.client.cpid")) != null) {
                String strValueOf = String.valueOf(obj);
                return strValueOf.startsWith("cpid=") ? strValueOf.substring("cpid=".length()) : strValueOf;
            }
            HMSLog.i("AGCUtils", "In getMetaDataCpId, Failed to read meta data for the CpId.");
            return "";
        } catch (PackageManager.NameNotFoundException unused) {
            HMSLog.e("AGCUtils", "In getMetaDataCpId, Failed to read meta data for the CpId.");
            return "";
        }
    }
}
