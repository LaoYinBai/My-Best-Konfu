package com.kwad.sdk.crash.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.kuaishou.weapon.adsdk.DeviceInfo;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class SystemUtil {
    private static volatile String a;
    private static volatile Boolean b;
    private static long c;
    private static long d;
    private static int e;
    private static LEVEL f;

    public enum LEVEL {
        BEST(5),
        HIGH(4),
        MIDDLE(3),
        LOW(2),
        BAD(1),
        UN_KNOW(-1);

        int value;

        LEVEL(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static class a {
        public long a;
        public long b;
        public long c;
        public long d;
        public long e;
        public int f;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x005a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long a() throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L44
            java.lang.String r2 = "/proc/meminfo"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L44
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L44
            r3 = 8192(0x2000, float:1.148E-41)
            r2.<init>(r1, r3)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L44
        Lf:
            java.lang.String r0 = r2.readLine()     // Catch: java.lang.Exception -> L3e java.lang.Throwable -> L57
            if (r0 == 0) goto L3a
            java.lang.String r1 = "MemTotal"
            boolean r1 = r0.contains(r1)     // Catch: java.lang.Exception -> L3e java.lang.Throwable -> L57
            if (r1 == 0) goto Lf
            java.lang.String r1 = "\\s+"
            java.lang.String[] r0 = r0.split(r1)     // Catch: java.lang.Exception -> L3e java.lang.Throwable -> L57
            r1 = 1
            r0 = r0[r1]     // Catch: java.lang.Exception -> L3e java.lang.Throwable -> L57
            java.lang.Long r0 = java.lang.Long.valueOf(r0)     // Catch: java.lang.Exception -> L3e java.lang.Throwable -> L57
            long r0 = r0.longValue()     // Catch: java.lang.Exception -> L3e java.lang.Throwable -> L57
            r3 = 10
            long r0 = r0 << r3
            r2.close()     // Catch: java.io.IOException -> L35
            goto L39
        L35:
            r2 = move-exception
            com.kwad.sdk.core.d.a.b(r2)
        L39:
            return r0
        L3a:
            r2.close()     // Catch: java.io.IOException -> L50
            goto L54
        L3e:
            r0 = move-exception
            goto L47
        L40:
            r1 = move-exception
            r2 = r0
            r0 = r1
            goto L58
        L44:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L47:
            com.kwad.sdk.core.d.a.b(r0)     // Catch: java.lang.Throwable -> L57
            if (r2 == 0) goto L54
            r2.close()     // Catch: java.io.IOException -> L50
            goto L54
        L50:
            r0 = move-exception
            com.kwad.sdk.core.d.a.b(r0)
        L54:
            r0 = 0
            return r0
        L57:
            r0 = move-exception
        L58:
            if (r2 == 0) goto L62
            r2.close()     // Catch: java.io.IOException -> L5e
            goto L62
        L5e:
            r1 = move-exception
            com.kwad.sdk.core.d.a.b(r1)
        L62:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.crash.utils.SystemUtil.a():long");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0033, code lost:
    
        com.kwad.sdk.crash.utils.SystemUtil.a = r1.processName;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(android.content.Context r5) throws java.lang.Throwable {
        /*
            java.lang.String r0 = com.kwad.sdk.crash.utils.SystemUtil.a
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto Lb
            java.lang.String r5 = com.kwad.sdk.crash.utils.SystemUtil.a
            return r5
        Lb:
            int r0 = android.os.Process.myPid()     // Catch: java.lang.Exception -> L38
            java.lang.String r1 = "activity"
            java.lang.Object r5 = r5.getSystemService(r1)     // Catch: java.lang.Exception -> L38
            android.app.ActivityManager r5 = (android.app.ActivityManager) r5     // Catch: java.lang.Exception -> L38
            if (r5 == 0) goto L3c
            java.util.List r5 = r5.getRunningAppProcesses()     // Catch: java.lang.Exception -> L38
            if (r5 == 0) goto L3c
            java.util.Iterator r5 = r5.iterator()     // Catch: java.lang.Exception -> L38
        L23:
            boolean r1 = r5.hasNext()     // Catch: java.lang.Exception -> L38
            if (r1 == 0) goto L3c
            java.lang.Object r1 = r5.next()     // Catch: java.lang.Exception -> L38
            android.app.ActivityManager$RunningAppProcessInfo r1 = (android.app.ActivityManager.RunningAppProcessInfo) r1     // Catch: java.lang.Exception -> L38
            int r2 = r1.pid     // Catch: java.lang.Exception -> L38
            if (r2 != r0) goto L23
            java.lang.String r5 = r1.processName     // Catch: java.lang.Exception -> L38
            com.kwad.sdk.crash.utils.SystemUtil.a = r5     // Catch: java.lang.Exception -> L38
            goto L3c
        L38:
            r5 = move-exception
            com.kwad.sdk.core.d.a.b(r5)
        L3c:
            java.lang.String r5 = com.kwad.sdk.crash.utils.SystemUtil.a
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 == 0) goto L90
            r5 = 0
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L7f java.io.IOException -> L81 java.io.FileNotFoundException -> L86
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L7f java.io.IOException -> L81 java.io.FileNotFoundException -> L86
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L7f java.io.IOException -> L81 java.io.FileNotFoundException -> L86
            java.lang.String r3 = "/proc/self/cmdline"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L7f java.io.IOException -> L81 java.io.FileNotFoundException -> L86
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L7f java.io.IOException -> L81 java.io.FileNotFoundException -> L86
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L7f java.io.IOException -> L81 java.io.FileNotFoundException -> L86
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L75 java.io.FileNotFoundException -> L7a
            r5.<init>()     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L75 java.io.FileNotFoundException -> L7a
        L5b:
            int r1 = r0.read()     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L75 java.io.FileNotFoundException -> L7a
            if (r1 <= 0) goto L66
            char r1 = (char) r1     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L75 java.io.FileNotFoundException -> L7a
            r5.append(r1)     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L75 java.io.FileNotFoundException -> L7a
            goto L5b
        L66:
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L75 java.io.FileNotFoundException -> L7a
            com.kwad.sdk.crash.utils.SystemUtil.a = r5     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L75 java.io.FileNotFoundException -> L7a
            com.kwad.sdk.crash.utils.b.a(r0)
            goto L90
        L70:
            r5 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
            goto L8c
        L75:
            r5 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
            goto L82
        L7a:
            r5 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
            goto L82
        L7f:
            r0 = move-exception
            goto L8c
        L81:
            r0 = move-exception
        L82:
            com.kwad.sdk.core.d.a.b(r0)     // Catch: java.lang.Throwable -> L7f
            goto L88
        L86:
            r0 = move-exception
            goto L82
        L88:
            com.kwad.sdk.crash.utils.b.a(r5)
            goto L90
        L8c:
            com.kwad.sdk.crash.utils.b.a(r5)
            throw r0
        L90:
            java.lang.String r5 = com.kwad.sdk.crash.utils.SystemUtil.a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.crash.utils.SystemUtil.a(android.content.Context):java.lang.String");
    }

    private static String a(String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        int length = str2.length();
        int i = -1;
        while (true) {
            if (length >= str.length()) {
                length = -1;
                break;
            }
            char cCharAt = str.charAt(length);
            if (cCharAt < '0' || cCharAt > '9') {
                if (i != -1) {
                    break;
                }
            } else if (i == -1) {
                i = length;
            }
            length++;
        }
        if (i == -1) {
            return null;
        }
        return length == -1 ? str.substring(i) : str.substring(i, length);
    }

    public static boolean a(int i) {
        return b() >= i;
    }

    public static int b() {
        return Build.VERSION.SDK_INT;
    }

    public static boolean b(Context context) throws Throwable {
        if (b == null) {
            String strA = a(context);
            b = Boolean.valueOf(!TextUtils.isEmpty(strA) && strA.equals(context.getPackageName()));
        }
        return b.booleanValue();
    }

    public static long c(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(PushConstants.INTENT_ACTIVITY_NAME);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static a c() throws Throwable {
        RandomAccessFile randomAccessFile;
        IOException e2;
        String strA;
        a aVar = new a();
        try {
            randomAccessFile = new RandomAccessFile("/proc/self/status", CampaignEx.JSON_KEY_AD_R);
            while (true) {
                try {
                    try {
                        String line = randomAccessFile.readLine();
                        if (line == null) {
                            break;
                        }
                        if (!TextUtils.isEmpty(line)) {
                            if (line.startsWith("VmSize") && line.contains("kB")) {
                                String strA2 = a(line, "VmSize");
                                if (strA2 != null) {
                                    aVar.b = Long.valueOf(strA2).longValue();
                                }
                            } else if (line.startsWith("VmRSS:") && line.contains("kB")) {
                                String strA3 = a(line, "VmRSS:");
                                if (strA3 != null) {
                                    aVar.c = Long.valueOf(strA3).longValue();
                                }
                            } else if (line.startsWith("Threads:") && (strA = a(line, "Threads:")) != null) {
                                aVar.f = Integer.valueOf(strA).intValue();
                            }
                        }
                    } catch (IOException e3) {
                        e2 = e3;
                        com.kwad.sdk.core.d.a.b(e2);
                        b.a(randomAccessFile);
                        return aVar;
                    }
                } catch (Throwable th) {
                    th = th;
                    b.a(randomAccessFile);
                    throw th;
                }
            }
        } catch (IOException e4) {
            randomAccessFile = null;
            e2 = e4;
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile = null;
            b.a(randomAccessFile);
            throw th;
        }
        b.a(randomAccessFile);
        return aVar;
    }

    public static int d(Context context) {
        if (com.kwad.sdk.b.a.booleanValue()) {
            return (int) DeviceInfo.getScreenBrightness(context);
        }
        try {
            return Settings.System.getInt(context.getContentResolver(), "screen_brightness");
        } catch (Exception e2) {
            com.kwad.sdk.core.d.a.b(e2);
            return -1;
        }
    }

    public static int e(Context context) throws PackageManager.NameNotFoundException {
        ApplicationInfo applicationInfo;
        if (context == null) {
            return 0;
        }
        try {
            applicationInfo = context.getApplicationInfo();
        } catch (Exception e2) {
            com.kwad.sdk.core.d.a.b(e2);
        }
        if (applicationInfo != null) {
            return applicationInfo.targetSdkVersion;
        }
        ApplicationInfo applicationInfo2 = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        if (applicationInfo2 != null) {
            return applicationInfo2.targetSdkVersion;
        }
        return 0;
    }
}
