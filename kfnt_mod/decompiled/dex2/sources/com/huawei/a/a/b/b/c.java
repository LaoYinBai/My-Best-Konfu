package com.huawei.a.a.b.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001f A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(int r0, java.lang.String r1) {
        /*
            switch(r0) {
                case 1: goto L22;
                case 2: goto L22;
                case 3: goto L1f;
                case 4: goto L22;
                case 5: goto L1f;
                case 6: goto L1f;
                case 7: goto L22;
                case 8: goto L1f;
                case 9: goto L1f;
                case 10: goto L1f;
                case 11: goto L22;
                case 12: goto L1f;
                case 13: goto L1c;
                case 14: goto L1f;
                case 15: goto L1f;
                default: goto L3;
            }
        L3:
            java.lang.String r0 = "TD-SCDMA"
            boolean r0 = r1.equalsIgnoreCase(r0)
            if (r0 != 0) goto L1f
            java.lang.String r0 = "WCDMA"
            boolean r0 = r1.equalsIgnoreCase(r0)
            if (r0 != 0) goto L1f
            java.lang.String r0 = "CDMA2000"
            boolean r0 = r1.equalsIgnoreCase(r0)
            if (r0 == 0) goto L24
            goto L1f
        L1c:
            java.lang.String r1 = "4G"
            goto L24
        L1f:
            java.lang.String r1 = "3G"
            goto L24
        L22:
            java.lang.String r1 = "2G"
        L24:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.a.a.b.b.c.a(int, java.lang.String):java.lang.String");
    }

    public static String a(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) != 0) {
            com.huawei.a.a.b.e.a.c("hmsSdk", "not have network state phone permission!");
            return "";
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) {
            return "";
        }
        if (activeNetworkInfo.getType() == 1) {
            return "WIFI";
        }
        if (activeNetworkInfo.getType() == 0) {
            String subtypeName = activeNetworkInfo.getSubtypeName();
            com.huawei.a.a.b.e.a.b("hmsSdk", "Network getSubtypeName : " + subtypeName);
            return a(activeNetworkInfo.getSubtype(), subtypeName);
        }
        if (activeNetworkInfo.getType() != 16) {
            return "";
        }
        com.huawei.a.a.b.e.a.c("hmsSdk", "type name = COMPANION_PROXY");
        return "COMPANION_PROXY";
    }
}
