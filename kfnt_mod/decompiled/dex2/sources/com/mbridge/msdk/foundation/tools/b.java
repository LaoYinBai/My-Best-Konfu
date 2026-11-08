package com.mbridge.msdk.foundation.tools;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;

/* compiled from: CommonBatteryStatusUtil.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b {
    private static int a = -1;
    private static int b = -1;
    private static int c = -1;
    private static boolean d = false;
    private static int e = 0;
    private static boolean f = false;
    private static boolean g = false;

    public static int a() {
        try {
            Intent intentRegisterReceiver = com.mbridge.msdk.foundation.controller.a.f().j().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int[] iArr = new int[2];
            if (intentRegisterReceiver != null) {
                iArr[0] = intentRegisterReceiver.getIntExtra(FirebaseAnalytics.Param.LEVEL, 50);
                iArr[1] = intentRegisterReceiver.getIntExtra("plugged", 0);
                a(iArr[1] != 0);
                iArr[0] = a(iArr[0], iArr[1]);
                a(iArr[0]);
            } else {
                iArr[0] = 50;
                iArr[1] = 0;
            }
            return iArr[0];
        } catch (Exception unused) {
            if (a != -1) {
                return a;
            }
            return 50;
        }
    }

    private static int a(int i, int i2) throws NumberFormatException {
        String str = Build.MODEL;
        if (str.equalsIgnoreCase("SCH-i909") || str.equalsIgnoreCase("SCH-I535") || str.equalsIgnoreCase("SCH-W899")) {
            if (i > 100) {
                i /= 10;
            }
        } else if (!str.trim().toUpperCase().contains("XT702") && !str.trim().toUpperCase().contains("XT907") && !str.trim().toUpperCase().contains("XT1058") && !str.trim().toUpperCase().contains("XT1080") && Build.MANUFACTURER.equalsIgnoreCase("motorola")) {
            File file = new File("/sys/class/power_supply/battery/charge_counter");
            int i3 = file.exists() ? Integer.parseInt(a(file)) : 0;
            if (i3 <= 0) {
                File file2 = new File("/sys/class/power_supply/battery/capacity");
                if (file2.exists()) {
                    Integer.parseInt(a(file2));
                }
            }
            if (i3 <= 100 && i3 > 0 && i % 10 == 0) {
                i = i3;
            }
        }
        if (i <= 100) {
            return i;
        }
        if (i2 != 0 && i < 110) {
            return 100;
        }
        do {
            i /= 10;
        } while (i > 100);
        return i;
    }

    public static int b() {
        int intExtra;
        try {
            intExtra = com.mbridge.msdk.foundation.controller.a.f().j().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("status", 1);
        } catch (Throwable th) {
            s.b("BatteryStatusUtil", th.getMessage(), th);
            intExtra = 0;
        }
        return (intExtra == 2 || intExtra == 5) ? 1 : 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003a A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(java.io.File r4) throws java.lang.Throwable {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 1024(0x400, float:1.435E-42)
            char[] r1 = new char[r1]
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L24 java.io.IOException -> L2c
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L24 java.io.IOException -> L2c
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L22 java.io.IOException -> L2d
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L22 java.io.IOException -> L2d
        L14:
            int r2 = r4.read(r1)     // Catch: java.lang.Throwable -> L22 java.io.IOException -> L2d
            if (r2 < 0) goto L1e
            a(r2, r1, r0)     // Catch: java.lang.Throwable -> L22 java.io.IOException -> L2d
            goto L14
        L1e:
            r3.close()     // Catch: java.io.IOException -> L30
            goto L30
        L22:
            r4 = move-exception
            goto L26
        L24:
            r4 = move-exception
            r3 = r2
        L26:
            if (r3 == 0) goto L2b
            r3.close()     // Catch: java.io.IOException -> L2b
        L2b:
            throw r4
        L2c:
            r3 = r2
        L2d:
            if (r3 == 0) goto L30
            goto L1e
        L30:
            java.lang.String r4 = r0.toString()
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 == 0) goto L3c
            java.lang.String r4 = "0"
        L3c:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.tools.b.a(java.io.File):java.lang.String");
    }

    private static void a(int i, char[] cArr, StringBuilder sb) {
        for (int i2 = 0; i2 < i; i2++) {
            if (cArr[i2] != '\n' && cArr[i2] != '\r') {
                sb.append(cArr[i2]);
            }
        }
    }

    private static synchronized void a(int i) {
        e = i;
    }

    private static synchronized void a(boolean z) {
        f = z;
    }
}
