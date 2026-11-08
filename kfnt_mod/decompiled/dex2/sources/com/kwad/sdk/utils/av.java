package com.kwad.sdk.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.LocaleList;
import android.os.Process;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kuaishou.weapon.adsdk.DeviceInfo;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.api.SdkConfig;
import com.kwad.sdk.crash.utils.SystemUtil;
import com.mbridge.msdk.playercommon.exoplayer2.util.MimeTypes;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class av {
    private static String a = "";
    private static String b = "";
    private static String c = "";
    private static int d = 0;
    private static boolean e = false;
    private static String j;
    private static String k;
    private static int s;
    private static long t;
    private static final String[] f = {"", ""};
    private static String g = "";
    private static String h = "";
    private static String i = "";
    private static int l = -1;
    private static boolean m = false;
    private static boolean n = false;
    private static boolean o = false;
    private static boolean p = false;
    private static boolean q = false;
    private static boolean r = false;
    private static boolean u = true;
    private static boolean v = true;
    private static final List<String> w = Arrays.asList("a5f5faddde9e9f02", "8e17f7422b35fbea", "b88c3c236923d9d9", "cb36bf76cca443d0", "5d4e49ed381836c5", "cffa38e9136f93e9", "62bd2daa59ea0173", "b7aad49a2d5bc5d9", "f2138912c5e5dd5c", "330a1e81a2bf9f31", "59c0f432ccbef844", "521376155e535f39", "aa5ec6ce14abd680", "5522a09bb500d82f", "6dfe4a96800edfb4", "ecc9a2dded8cdf72", "399f868043955b11", "34dc327c00dbff94", "d1b4e3862c309f8b", "68bdbf71f863ccac", "01558dd995085a35", "351174200a06da52", "fa0988506c76ff4b", "8eb8ef823312c61a", "a72e81be65c4638b", "416d15a015c8f324", "474086ea2d737519", "befdddf908c8d749", "780ee58a6f57aab6", "cfe86fa07cae3601", "704ff4d1534f0ff4", "9298b9e9bbd7cdea", "7b634c42f236c6e8", "11eacf22b9ceab7d", "2941a4f39eec5864", "87d134dc5ba45550", "fdd2313bb1750eb9", "6560ef232d8424bb", "5d876286e1064482", "f66fefb916f4962d", "7baf82d0ac49f596", "57748921d8d88ed4", "120cd57f1a50b8f5", "e164f9610ddd9fc8", "6256f0e8da6389de", "bcb22df712476416", "714fa9aff63f7adb", "cb8252e4da7cf610", "e18f649aa80e140c", "966790a9db5ea8d8", "e1769e681af901dd", "d23f2574a60964a4", "d717e6298d3c9cb2", "f5ea5e8ba730864e", "a8a0a223d1a42232", "6675a4f231f5c8db", "3edb7c2103e5c75a", "8ce6a9a216b326c4", "af606153eb3be0a7", "7ae255c3d760c920", "e50e94c40048c5fd", "55009bca30f9dc4c", "c37566487909214a", "891b74f7e534d14a", "726e190aae663525", "df473127d30fb669", "bfbcc646d92dfd48", "a4a1954c44751936", "da4a44a3d7c4d8be", "5ff5bca4a775dd30", "14917461e1917c53", "14ce20d0a80955fa", "a56a63de4d3f3d39", "f780246adc7bd556", "3495a541aea0da72", "f7f205ce47fed2a5", "f52db3f434279c3a", "dca17088c97dee5e", "dd53a8b3a2a4ccc0", "52e07629290d45e4", "cda522b0f8f50d9a", "b85a1c8bcd51d82c", "e344a00cd3f5e93a", "fa59d8a66d7bdd88", "68fb1f1393a216e8", "4c30ab1fb10af181", "b1376e0578099143", "88752f72d8d305fd", "fddf20078d27bf3c", "dab2120bffa2be8c", "c7c8dde481793471", "e4b1bdbcabfc284d");

    public static int a(Context context) {
        if (com.kwad.sdk.core.config.b.a(512L)) {
            return 0;
        }
        if (com.kwad.sdk.b.a.booleanValue()) {
            return DeviceInfo.getBatteryPercent(context);
        }
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((BatteryManager) context.getApplicationContext().getSystemService("batterymanager")).getIntProperty(4);
            }
        } catch (Exception unused) {
        }
        return 0;
    }

    public static synchronized int a(Context context, @Nullable TelephonyManager telephonyManager) {
        if (!e && d <= 0 && Build.VERSION.SDK_INT >= 23 && (context != null || telephonyManager != null)) {
            if (com.kwad.sdk.b.a.booleanValue()) {
                d = DeviceInfo.getPhoneCount(context);
            } else {
                if (telephonyManager == null) {
                    try {
                        telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    } catch (Exception unused) {
                        e = true;
                    }
                }
                com.kwad.sdk.core.d.a.c("SystemUtils", "getPhoneCount");
                d = telephonyManager.getPhoneCount();
                if (d == 0) {
                    e = true;
                }
            }
            return d;
        }
        return d;
    }

    public static int a(@NonNull Context context, @NonNull String str) {
        if (str != null) {
            return context.checkPermission(str, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }

    public static long a() {
        if (com.kwad.sdk.b.a.booleanValue()) {
            return DeviceInfo.getRomTotalSpace();
        }
        try {
            return a(Environment.getDataDirectory());
        } catch (Exception unused) {
            return 0L;
        }
    }

    private static long a(File file) {
        if (file == null) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(file.getPath());
            return Build.VERSION.SDK_INT < 18 ? statFs.getBlockSize() * statFs.getBlockCount() : statFs.getBlockSizeLong() * statFs.getBlockCountLong();
        } catch (Exception unused) {
            return 0L;
        }
    }

    private static String a(String str, int i2, char c2) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() + str.length() < i2) {
            sb.append(c2);
        }
        sb.append(str);
        return sb.toString();
    }

    public static void a(SdkConfig sdkConfig) {
        u = sdkConfig.canReadMacAddress();
        v = sdkConfig.canReadICCID();
    }

    private static boolean a(String str) {
        return w.contains(str.toLowerCase(Locale.US));
    }

    public static long b() {
        if (com.kwad.sdk.core.config.b.a(1024L)) {
            return 0L;
        }
        if (com.kwad.sdk.b.a.booleanValue()) {
            return DeviceInfo.getRomAvailableSpace();
        }
        try {
            return b(Environment.getDataDirectory());
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static long b(Context context) throws Throwable {
        if (t > 0) {
            return t;
        }
        if (com.kwad.sdk.b.a.booleanValue()) {
            return DeviceInfo.getRamTotalSize(context);
        }
        if (context == null) {
            return 0L;
        }
        try {
            ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService(PushConstants.INTENT_ACTIVITY_NAME);
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            long jV = memoryInfo.totalMem;
            if (jV <= 0) {
                jV = v();
            }
            t = jV;
            return t;
        } catch (Exception unused) {
            return 0L;
        }
    }

    private static long b(File file) {
        try {
            StatFs statFs = new StatFs(file.getPath());
            return Build.VERSION.SDK_INT < 18 ? statFs.getBlockSize() * statFs.getAvailableBlocks() : statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static String b(Context context, String str) throws PackageManager.NameNotFoundException {
        if (TextUtils.isEmpty(str) || context == null) {
            return "";
        }
        try {
            PackageInfo packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(str, 64);
            return packageInfo != null ? packageInfo.versionName : "";
        } catch (Exception unused) {
            return "";
        }
    }

    private static void b(String str) {
        Context context = KsAdSDKImpl.get().getContext();
        if (context == null) {
            return;
        }
        context.getSharedPreferences("ksadsdk_pref", 0).edit().putString("android_id", str).apply();
    }

    public static int c() {
        if (s > 0) {
            return s;
        }
        s = Runtime.getRuntime().availableProcessors();
        return s;
    }

    public static long c(Context context) {
        if (context == null || com.kwad.sdk.core.config.b.a(1024L)) {
            return 0L;
        }
        if (com.kwad.sdk.b.a.booleanValue()) {
            return DeviceInfo.getRamAvailableSize(context);
        }
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(PushConstants.INTENT_ACTIVITY_NAME);
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            return memoryInfo.availMem;
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static String d() {
        Locale locale = Build.VERSION.SDK_INT >= 24 ? LocaleList.getDefault().get(0) : Locale.getDefault();
        if (locale == null) {
            locale = Locale.CHINESE;
        }
        return String.valueOf(locale);
    }

    public static String d(@Nullable Context context) {
        if (ao.c() && !TextUtils.isEmpty(ao.d())) {
            return ao.d();
        }
        String strE = e(context);
        return (TextUtils.isEmpty(strE) && TextUtils.isEmpty(com.kwad.sdk.core.f.a.a(context))) ? com.kwad.sdk.core.config.b.ab() : strE;
    }

    public static String e() {
        return Build.MODEL;
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    public static synchronized String e(@Nullable Context context) {
        boolean zI;
        if (ao.c() && !TextUtils.isEmpty(ao.d())) {
            return ao.d();
        }
        if (m) {
            return b;
        }
        if (TextUtils.isEmpty(b) && context != null) {
            if (Build.VERSION.SDK_INT >= 29) {
                return b;
            }
            if (!ao.c() && !com.kwad.sdk.core.config.b.a(1L)) {
                if (com.kwad.sdk.b.a.booleanValue()) {
                    b = DeviceInfo.getImei(context);
                    return b != null ? b : "";
                }
                try {
                    zI = i(context);
                    if (zI) {
                        try {
                            b = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                            if (TextUtils.isEmpty(b)) {
                                m = true;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            com.kwad.sdk.core.d.a.a(e);
                            if (zI) {
                                m = true;
                            }
                            return b;
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    zI = false;
                }
                return b;
            }
            return b;
        }
        return b;
    }

    public static synchronized long f() {
        long j2;
        j2 = 0;
        if (ao.c() && (!TextUtils.isEmpty(ao.d()) || ao.e() != null)) {
            j2 = 1;
        }
        if (ao.a() && ao.b() != null) {
            j2 |= 64;
        }
        if (ao.c() && !TextUtils.isEmpty(ao.f())) {
            j2 |= 2;
        }
        if (ao.g() && !TextUtils.isEmpty(ao.h())) {
            j2 |= 4;
        }
        if (ao.i() && !TextUtils.isEmpty(ao.j())) {
            j2 |= 2048;
        }
        if (ao.m()) {
            if (ao.n() != null) {
                j2 |= 16;
            }
        }
        return j2;
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    public static synchronized String f(@Nullable Context context) {
        boolean zI;
        if (r) {
            return c;
        }
        if (TextUtils.isEmpty(c) && context != null) {
            if (!ao.c() && !com.kwad.sdk.core.config.b.a(4096L)) {
                if (com.kwad.sdk.b.a.booleanValue()) {
                    c = DeviceInfo.getMeid(context);
                    return c != null ? c : "";
                }
                try {
                    zI = i(context);
                    if (zI) {
                        try {
                            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                            if (telephonyManager != null) {
                                if (Build.VERSION.SDK_INT >= 26) {
                                    c = telephonyManager.getMeid();
                                    if (TextUtils.isEmpty(c)) {
                                        r = true;
                                    }
                                } else {
                                    if (telephonyManager.getPhoneType() == 2) {
                                        c = telephonyManager.getDeviceId();
                                        if (TextUtils.isEmpty(c)) {
                                        }
                                    } else {
                                        c = null;
                                    }
                                    r = true;
                                }
                            }
                        } catch (Exception e2) {
                            e = e2;
                            com.kwad.sdk.core.d.a.a(e);
                            if (zI) {
                                r = true;
                            }
                            return c;
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    zI = false;
                }
                return c;
            }
            return c;
        }
        return c;
    }

    public static String g() {
        return Build.BRAND;
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    public static synchronized String[] g(@Nullable Context context) {
        boolean zI;
        if (ao.c() && ao.e() != null) {
            return ao.e();
        }
        if (q) {
            return f;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            return f;
        }
        if (TextUtils.isEmpty(f[0]) && TextUtils.isEmpty(f[1]) && context != null) {
            if (!ao.c() && !com.kwad.sdk.core.config.b.a(1L)) {
                if (!com.kwad.sdk.b.a.booleanValue()) {
                    try {
                        zI = i(context);
                        if (zI) {
                            try {
                                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                                if (telephonyManager != null) {
                                    if (Build.VERSION.SDK_INT >= 26) {
                                        int iMin = Math.min(a(context, telephonyManager), 2);
                                        for (int i2 = 0; i2 < iMin; i2++) {
                                            f[i2] = telephonyManager.getImei(i2);
                                        }
                                        if (TextUtils.isEmpty(f[0]) && TextUtils.isEmpty(f[1])) {
                                            q = true;
                                        }
                                    } else {
                                        if (telephonyManager.getPhoneType() == 1) {
                                            if (Build.VERSION.SDK_INT >= 23) {
                                                int iMin2 = Math.min(a(context, telephonyManager), 2);
                                                for (int i3 = 0; i3 < iMin2; i3++) {
                                                    f[i3] = telephonyManager.getDeviceId(i3);
                                                }
                                            } else {
                                                f[0] = telephonyManager.getDeviceId();
                                                f[1] = null;
                                            }
                                            if (TextUtils.isEmpty(f[0]) && TextUtils.isEmpty(f[1])) {
                                            }
                                        } else {
                                            f[0] = null;
                                            f[1] = null;
                                        }
                                        q = true;
                                    }
                                }
                            } catch (Exception e2) {
                                e = e2;
                                com.kwad.sdk.core.d.a.b(e);
                                if (zI) {
                                    q = true;
                                }
                                return f;
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                        zI = false;
                    }
                } else if (DeviceInfo.getIMEIs(context) != null && DeviceInfo.getIMEIs(context).length >= 2) {
                    f[0] = DeviceInfo.getIMEIs(context)[0];
                    f[1] = DeviceInfo.getIMEIs(context)[1];
                }
                return f;
            }
            return f;
        }
        return f;
    }

    public static synchronized long h() {
        if (com.kwad.sdk.b.a.booleanValue()) {
            return DeviceInfo.getBootTime();
        }
        return SystemClock.elapsedRealtime() / 1000;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0075 A[Catch: all -> 0x0085, TryCatch #0 {, blocks: (B:4:0x0003, B:8:0x000f, B:10:0x0015, B:13:0x0019, B:15:0x001d, B:18:0x0021, B:20:0x0027, B:23:0x0030, B:25:0x0038, B:39:0x006a, B:43:0x0077, B:42:0x0075, B:28:0x0040, B:30:0x0046, B:36:0x005f, B:38:0x0067, B:34:0x005b, B:46:0x007d, B:49:0x0081), top: B:56:0x0003 }] */
    @android.annotation.SuppressLint({"HardwareIds", "MissingPermission"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized java.lang.String h(android.content.Context r3) {
        /*
            java.lang.Class<com.kwad.sdk.utils.av> r0 = com.kwad.sdk.utils.av.class
            monitor-enter(r0)
            java.lang.String r1 = com.kwad.sdk.utils.av.g     // Catch: java.lang.Throwable -> L85
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L85
            if (r1 == 0) goto L81
            if (r3 != 0) goto Lf
            goto L81
        Lf:
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L85
            r2 = 29
            if (r1 < r2) goto L19
            java.lang.String r3 = com.kwad.sdk.utils.av.g     // Catch: java.lang.Throwable -> L85
            monitor-exit(r0)
            return r3
        L19:
            boolean r1 = com.kwad.sdk.utils.av.n     // Catch: java.lang.Throwable -> L85
            if (r1 == 0) goto L21
            java.lang.String r3 = com.kwad.sdk.utils.av.g     // Catch: java.lang.Throwable -> L85
            monitor-exit(r0)
            return r3
        L21:
            boolean r1 = com.kwad.sdk.utils.ao.c()     // Catch: java.lang.Throwable -> L85
            if (r1 != 0) goto L7d
            r1 = 128(0x80, double:6.3E-322)
            boolean r1 = com.kwad.sdk.core.config.b.a(r1)     // Catch: java.lang.Throwable -> L85
            if (r1 == 0) goto L30
            goto L7d
        L30:
            java.lang.Boolean r1 = com.kwad.sdk.b.a     // Catch: java.lang.Throwable -> L85
            boolean r1 = r1.booleanValue()     // Catch: java.lang.Throwable -> L85
            if (r1 == 0) goto L3f
            java.lang.String r3 = com.kuaishou.weapon.adsdk.DeviceInfo.getIMSI(r3)     // Catch: java.lang.Throwable -> L85
            com.kwad.sdk.utils.av.g = r3     // Catch: java.lang.Throwable -> L85
            goto L6a
        L3f:
            r1 = 0
            boolean r2 = i(r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L85
            if (r2 == 0) goto L5d
            android.content.Context r3 = r3.getApplicationContext()     // Catch: java.lang.Exception -> L5a java.lang.Throwable -> L85
            java.lang.String r1 = "phone"
            java.lang.Object r3 = r3.getSystemService(r1)     // Catch: java.lang.Exception -> L5a java.lang.Throwable -> L85
            android.telephony.TelephonyManager r3 = (android.telephony.TelephonyManager) r3     // Catch: java.lang.Exception -> L5a java.lang.Throwable -> L85
            java.lang.String r3 = r3.getSubscriberId()     // Catch: java.lang.Exception -> L5a java.lang.Throwable -> L85
            com.kwad.sdk.utils.av.g = r3     // Catch: java.lang.Exception -> L5a java.lang.Throwable -> L85
            goto L5d
        L59:
            r2 = 0
        L5a:
            r3 = 0
            com.kwad.sdk.utils.av.g = r3     // Catch: java.lang.Throwable -> L85
        L5d:
            if (r2 == 0) goto L6a
            java.lang.String r3 = com.kwad.sdk.utils.av.g     // Catch: java.lang.Throwable -> L85
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L85
            if (r3 == 0) goto L6a
            r3 = 1
            com.kwad.sdk.utils.av.n = r3     // Catch: java.lang.Throwable -> L85
        L6a:
            java.lang.String r3 = com.kwad.sdk.utils.av.g     // Catch: java.lang.Throwable -> L85
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L85
            if (r3 == 0) goto L75
            java.lang.String r3 = ""
            goto L77
        L75:
            java.lang.String r3 = com.kwad.sdk.utils.av.g     // Catch: java.lang.Throwable -> L85
        L77:
            com.kwad.sdk.utils.av.g = r3     // Catch: java.lang.Throwable -> L85
            java.lang.String r3 = com.kwad.sdk.utils.av.g     // Catch: java.lang.Throwable -> L85
            monitor-exit(r0)
            return r3
        L7d:
            java.lang.String r3 = com.kwad.sdk.utils.av.g     // Catch: java.lang.Throwable -> L85
            monitor-exit(r0)
            return r3
        L81:
            java.lang.String r3 = com.kwad.sdk.utils.av.g     // Catch: java.lang.Throwable -> L85
            monitor-exit(r0)
            return r3
        L85:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.utils.av.h(android.content.Context):java.lang.String");
    }

    public static synchronized long i() {
        if (com.kwad.sdk.b.a.booleanValue()) {
            return DeviceInfo.getRomBuildTimestamp();
        }
        return Build.TIME;
    }

    public static boolean i(Context context) {
        boolean zHasCarrierPrivileges = (Build.VERSION.SDK_INT < 22 || SystemUtil.e(context) <= 28) ? a(context, "android.permission.READ_PHONE_STATE") == 0 : ((TelephonyManager) context.getSystemService("phone")).hasCarrierPrivileges();
        com.kwad.sdk.core.d.a.a("SystemUtils", "hasDeviceIdsPermission: " + zHasCarrierPrivileges);
        return zHasCarrierPrivileges;
    }

    public static synchronized String j() {
        if (com.kwad.sdk.b.a.booleanValue()) {
            return DeviceInfo.getFingerPrint();
        }
        return Build.FINGERPRINT;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0074  */
    @android.annotation.SuppressLint({"HardwareIds", "MissingPermission"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String j(android.content.Context r2) {
        /*
            java.lang.String r0 = com.kwad.sdk.utils.av.h
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L7e
            if (r2 != 0) goto Lc
            goto L7e
        Lc:
            boolean r0 = com.kwad.sdk.utils.av.o
            if (r0 == 0) goto L13
            java.lang.String r2 = com.kwad.sdk.utils.av.h
            return r2
        L13:
            boolean r0 = com.kwad.sdk.utils.av.v
            if (r0 != 0) goto L1a
            java.lang.String r2 = com.kwad.sdk.utils.av.h
            return r2
        L1a:
            boolean r0 = com.kwad.sdk.utils.ao.c()
            if (r0 != 0) goto L7b
            r0 = 128(0x80, double:6.3E-322)
            boolean r0 = com.kwad.sdk.core.config.b.a(r0)
            if (r0 == 0) goto L29
            goto L7b
        L29:
            java.lang.Boolean r0 = com.kwad.sdk.b.a
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L38
            java.lang.String r2 = com.kuaishou.weapon.adsdk.DeviceInfo.getICCID(r2)
            com.kwad.sdk.utils.av.h = r2
            goto L69
        L38:
            r0 = 0
            boolean r1 = i(r2)     // Catch: java.lang.Exception -> L54
            if (r1 == 0) goto L5c
            android.content.Context r2 = r2.getApplicationContext()     // Catch: java.lang.Exception -> L52
            java.lang.String r0 = "phone"
            java.lang.Object r2 = r2.getSystemService(r0)     // Catch: java.lang.Exception -> L52
            android.telephony.TelephonyManager r2 = (android.telephony.TelephonyManager) r2     // Catch: java.lang.Exception -> L52
            java.lang.String r2 = r2.getSimSerialNumber()     // Catch: java.lang.Exception -> L52
            com.kwad.sdk.utils.av.h = r2     // Catch: java.lang.Exception -> L52
            goto L5c
        L52:
            r2 = move-exception
            goto L56
        L54:
            r2 = move-exception
            r1 = 0
        L56:
            com.kwad.sdk.core.d.a.a(r2)
            r2 = 0
            com.kwad.sdk.utils.av.h = r2
        L5c:
            if (r1 == 0) goto L69
            java.lang.String r2 = com.kwad.sdk.utils.av.h
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 == 0) goto L69
            r2 = 1
            com.kwad.sdk.utils.av.o = r2
        L69:
            java.lang.String r2 = com.kwad.sdk.utils.av.h
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 == 0) goto L74
            java.lang.String r2 = ""
            goto L76
        L74:
            java.lang.String r2 = com.kwad.sdk.utils.av.h
        L76:
            com.kwad.sdk.utils.av.h = r2
            java.lang.String r2 = com.kwad.sdk.utils.av.h
            return r2
        L7b:
            java.lang.String r2 = com.kwad.sdk.utils.av.h
            return r2
        L7e:
            java.lang.String r2 = com.kwad.sdk.utils.av.h
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.utils.av.j(android.content.Context):java.lang.String");
    }

    public static synchronized com.kwad.sdk.collector.kwai.f k(Context context) {
        if (!com.kwad.sdk.core.config.b.N()) {
            return null;
        }
        if (!com.kwad.sdk.b.a.booleanValue()) {
            return com.kwad.sdk.collector.i.c();
        }
        com.kwad.sdk.collector.kwai.f fVar = new com.kwad.sdk.collector.kwai.f();
        fVar.a = DeviceInfo.getPhoneCount(context);
        fVar.b = DeviceInfo.getActivePhoneCount(context);
        return fVar;
    }

    public static synchronized String k() {
        if (com.kwad.sdk.b.a.booleanValue()) {
            return DeviceInfo.getBaseBandVersion();
        }
        return Build.getRadioVersion();
    }

    public static synchronized com.kwad.sdk.core.request.model.c l(Context context) {
        if (!com.kwad.sdk.core.config.b.W()) {
            return null;
        }
        if (!com.kwad.sdk.b.a.booleanValue()) {
            return com.kwad.sdk.collector.i.a();
        }
        int[] baseStationInfo = DeviceInfo.getBaseStationInfo(context);
        if (baseStationInfo == null || baseStationInfo.length != 3) {
            return null;
        }
        return new com.kwad.sdk.core.request.model.c(baseStationInfo[0], baseStationInfo[1], baseStationInfo[2]);
    }

    public static synchronized String l() {
        if (com.kwad.sdk.b.a.booleanValue()) {
            return DeviceInfo.getRomName();
        }
        return an.d();
    }

    public static synchronized int m(Context context) {
        if (com.kwad.sdk.b.a.booleanValue()) {
            return DeviceInfo.getRingerMode(context);
        }
        return ((AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO)).getRingerMode();
    }

    public static synchronized String m() {
        if (com.kwad.sdk.b.a.booleanValue()) {
            return DeviceInfo.getRomVersion();
        }
        return an.e();
    }

    public static int n(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.widthPixels;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String n() {
        return Build.MANUFACTURER;
    }

    public static int o() {
        if (l == -1) {
            l = x(KsAdSDKImpl.get().getContext()) ? 4 : 3;
        }
        return l;
    }

    public static int o(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.heightPixels;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static int p(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return (int) (displayMetrics.widthPixels / displayMetrics.density);
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String p() {
        if (TextUtils.isEmpty(k)) {
            k = System.getProperty("os.arch");
        }
        return k;
    }

    public static int q(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return (int) (displayMetrics.heightPixels / displayMetrics.density);
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String q() {
        return Build.VERSION.RELEASE;
    }

    public static int r() {
        return Build.VERSION.SDK_INT;
    }

    public static String r(Context context) {
        if (ao.k() || com.kwad.sdk.core.config.b.a(8L)) {
            return "";
        }
        if (com.kwad.sdk.b.a.booleanValue()) {
            return DeviceInfo.getWifiIpAddress(context);
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            if (wifiManager == null) {
                return "";
            }
            int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
            return (ipAddress & 255) + "." + ((ipAddress >> 8) & 255) + "." + ((ipAddress >> 16) & 255) + "." + ((ipAddress >> 24) & 255);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String s() {
        return Locale.getDefault().getLanguage();
    }

    @NonNull
    public static String s(Context context) {
        NetworkInfo activeNetworkInfo;
        String strT;
        if (ao.k() || com.kwad.sdk.core.config.b.a(8L)) {
            return "";
        }
        String str = "";
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected()) {
                if (1 == activeNetworkInfo.getType()) {
                    strT = r(context);
                } else if (activeNetworkInfo.getType() == 0) {
                    strT = t();
                }
                str = strT;
            }
        } catch (Exception unused) {
        }
        return str == null ? "" : str;
    }

    public static String t() {
        if (ao.k() || com.kwad.sdk.core.config.b.a(8L)) {
            return "";
        }
        if (com.kwad.sdk.b.a.booleanValue()) {
            return DeviceInfo.getIpAddressString() != null ? DeviceInfo.getIpAddressString() : "";
        }
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if ((inetAddressNextElement instanceof Inet4Address) && !inetAddressNextElement.isLoopbackAddress()) {
                        return inetAddressNextElement.getHostAddress();
                    }
                }
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    @SuppressLint({"HardwareIds"})
    public static String t(Context context) {
        if (ao.c() && !TextUtils.isEmpty(ao.f())) {
            return ao.f();
        }
        if (!TextUtils.isEmpty(i) || context == null) {
            return i;
        }
        if (p) {
            return i;
        }
        if (ao.c() || com.kwad.sdk.core.config.b.a(2L)) {
            return i;
        }
        if (com.kwad.sdk.b.a.booleanValue()) {
            i = DeviceInfo.getAndroidId(context);
        } else {
            try {
                i = Settings.Secure.getString(context.getContentResolver(), "android_id");
            } catch (Exception unused) {
            }
        }
        if (TextUtils.isEmpty(i)) {
            p = true;
        }
        return i;
    }

    public static int u(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        if (audioManager != null) {
            return audioManager.getStreamVolume(3);
        }
        return 0;
    }

    public static String u() {
        try {
            if (!TextUtils.isEmpty(j)) {
                return j;
            }
            String strX = x();
            if (!TextUtils.isEmpty(strX)) {
                j = "ANDROID_" + strX;
                return j;
            }
            String strT = t(KsAdSDKImpl.get().getContext());
            if (!TextUtils.isEmpty(strT) && !a(strT)) {
                j = "ANDROID_" + strT;
                return j;
            }
            String strW = w();
            if (TextUtils.isEmpty(strW)) {
                return "ANDROID_";
            }
            j = "ANDROID_" + strW;
            b(strW);
            return j;
        } catch (Exception e2) {
            com.kwad.sdk.core.d.a.b(e2);
            return "ANDROID_";
        }
    }

    private static long v() throws Throwable {
        BufferedReader bufferedReader;
        Throwable th;
        String line;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
        } catch (Exception unused) {
            bufferedReader = null;
        } catch (Throwable th2) {
            bufferedReader = null;
            th = th2;
        }
        do {
            try {
                try {
                    line = bufferedReader.readLine();
                    if (line != null) {
                    }
                } catch (Exception unused2) {
                    if (bufferedReader == null) {
                        return 0L;
                    }
                    bufferedReader.close();
                    return 0L;
                } catch (Throwable th3) {
                    th = th3;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused3) {
                        }
                    }
                    throw th;
                }
                bufferedReader.close();
                return 0L;
            } catch (IOException unused4) {
                return 0L;
            }
        } while (!line.contains("MemTotal"));
        long jLongValue = Long.valueOf(line.split("\\s+")[1]).longValue() << 10;
        try {
            bufferedReader.close();
        } catch (IOException unused5) {
        }
        return jLongValue;
    }

    @SuppressLint({"MissingPermission"})
    public static synchronized String v(@Nullable Context context) {
        if (ao.g() && !TextUtils.isEmpty(ao.h())) {
            return ao.h();
        }
        if (TextUtils.isEmpty(a) && context != null) {
            if (!u) {
                return a;
            }
            if (!ao.g() && !com.kwad.sdk.core.config.b.a(4L)) {
                if (com.kwad.sdk.b.a.booleanValue()) {
                    a = DeviceInfo.getMacAddress(context);
                } else if (TextUtils.isEmpty(a)) {
                    try {
                        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
                        if (connectionInfo != null) {
                            a = connectionInfo.getMacAddress();
                        }
                        if (TextUtils.isEmpty(a) || a.equals("02:00:00:00:00:00")) {
                            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                NetworkInterface networkInterface = (NetworkInterface) it.next();
                                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                                    if (hardwareAddress != null && hardwareAddress.length != 0) {
                                        StringBuilder sb = new StringBuilder();
                                        for (byte b2 : hardwareAddress) {
                                            sb.append(String.format("%02X:", Byte.valueOf(b2)));
                                        }
                                        if (sb.length() > 0) {
                                            sb.deleteCharAt(sb.length() - 1);
                                        }
                                        a = sb.toString();
                                    }
                                }
                            }
                        }
                        if (TextUtils.isEmpty(a) || a.equals("02:00:00:00:00:00")) {
                            a = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ").getInputStream())).readLine();
                        }
                        if (!TextUtils.isEmpty(a)) {
                            a = a.toUpperCase(Locale.US);
                        }
                    } catch (Exception unused) {
                    }
                }
                return a;
            }
            return a;
        }
        return a;
    }

    private static String w() {
        try {
            return a(Long.toHexString(new Random(System.currentTimeMillis()).nextLong()), 16, '0');
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String w(Context context) throws PackageManager.NameNotFoundException {
        try {
            PackageInfo packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 64);
            return packageInfo != null ? packageInfo.versionName : "";
        } catch (Exception unused) {
            return "";
        }
    }

    private static String x() {
        Context context = KsAdSDKImpl.get().getContext();
        if (context == null) {
            return null;
        }
        return context.getSharedPreferences("ksadsdk_pref", 0).getString("android_id", null);
    }

    private static boolean x(Context context) {
        return (context == null || context.getResources() == null || context.getResources().getConfiguration() == null || (context.getResources().getConfiguration().screenLayout & 15) < 3) ? false : true;
    }
}
