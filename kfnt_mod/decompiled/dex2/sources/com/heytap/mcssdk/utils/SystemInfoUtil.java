package com.heytap.mcssdk.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.SystemProperties;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

@SuppressLint({"DefaultLocale"})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class SystemInfoUtil {
    private static final String MODEL_NULL = "0";
    private static final int SDK_INT_21 = 21;

    public static String getAndroidVersion() {
        return Build.VERSION.RELEASE;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getCarrierName(android.content.Context r1) {
        /*
            java.lang.String r1 = getOperator(r1)
            java.lang.String r1 = r1.toLowerCase()
            int r0 = r1.hashCode()
            switch(r0) {
                case -42227884: goto L61;
                case 278980793: goto L57;
                case 394699659: goto L4d;
                case 507293352: goto L43;
                case 618558396: goto L39;
                case 618596989: goto L2f;
                case 618663094: goto L25;
                case 623012218: goto L1b;
                case 1661280486: goto L10;
                default: goto Lf;
            }
        Lf:
            goto L6b
        L10:
            java.lang.String r0 = "chinanet"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L6b
            r1 = 8
            goto L6c
        L1b:
            java.lang.String r0 = "china unicom"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L6b
            r1 = 4
            goto L6c
        L25:
            java.lang.String r0 = "中国联通"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L6b
            r1 = 3
            goto L6c
        L2f:
            java.lang.String r0 = "中国移动"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L6b
            r1 = 0
            goto L6c
        L39:
            java.lang.String r0 = "中国电信"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L6b
            r1 = 6
            goto L6c
        L43:
            java.lang.String r0 = "chinaunicom"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L6b
            r1 = 5
            goto L6c
        L4d:
            java.lang.String r0 = "china mobile"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L6b
            r1 = 1
            goto L6c
        L57:
            java.lang.String r0 = "chinamobile"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L6b
            r1 = 2
            goto L6c
        L61:
            java.lang.String r0 = "china net"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L6b
            r1 = 7
            goto L6c
        L6b:
            r1 = -1
        L6c:
            switch(r1) {
                case 0: goto L78;
                case 1: goto L78;
                case 2: goto L78;
                case 3: goto L75;
                case 4: goto L75;
                case 5: goto L75;
                case 6: goto L72;
                case 7: goto L72;
                case 8: goto L72;
                default: goto L6f;
            }
        L6f:
            java.lang.String r1 = "none"
            goto L7a
        L72:
            java.lang.String r1 = "China Net"
            goto L7a
        L75:
            java.lang.String r1 = "China Unicom"
            goto L7a
        L78:
            java.lang.String r1 = "China Mobile"
        L7a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.heytap.mcssdk.utils.SystemInfoUtil.getCarrierName(android.content.Context):java.lang.String");
    }

    public static String getModel() {
        if (!isEmpty(Build.MODEL)) {
            return Build.MODEL.toUpperCase();
        }
        LogUtil.w("No MODEL.");
        return "0";
    }

    public static String getOperator(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager != null ? telephonyManager.getNetworkOperatorName() : "";
        } catch (Exception e) {
            LogUtil.e(e);
            return "";
        }
    }

    public static String getRomVersion() {
        return SystemProperties.get("ro.build.display.id", "");
    }

    private static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str) || "null".equals(str);
    }
}
