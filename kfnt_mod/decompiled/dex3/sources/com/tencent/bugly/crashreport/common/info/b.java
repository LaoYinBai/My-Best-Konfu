package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import com.umeng.message.MsgConstant;
import com.umeng.message.proguard.l;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: BUGLY */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class b {
    private static String a;
    private static String b;

    public static String a() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            if (x.a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    public static String b() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Throwable th) {
            if (x.a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    public static int c() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (Throwable th) {
            if (x.a(th)) {
                return -1;
            }
            th.printStackTrace();
            return -1;
        }
    }

    public static String a(Context context) {
        String deviceId;
        if (context == null) {
            return null;
        }
        if (!AppInfo.a(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
            x.d("no READ_PHONE_STATE permission to get IMEI", new Object[0]);
            return null;
        }
        try {
            deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            if (deviceId == null) {
                return deviceId;
            }
            try {
                return deviceId.toLowerCase();
            } catch (Throwable unused) {
                x.a("Failed to get IMEI.", new Object[0]);
                return deviceId;
            }
        } catch (Throwable unused2) {
            deviceId = null;
        }
    }

    public static String b(Context context) {
        String subscriberId;
        if (context == null) {
            return null;
        }
        if (!AppInfo.a(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
            x.d("no READ_PHONE_STATE permission to get IMSI", new Object[0]);
            return null;
        }
        try {
            subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
            if (subscriberId == null) {
                return subscriberId;
            }
            try {
                return subscriberId.toLowerCase();
            } catch (Throwable unused) {
                x.a("Failed to get IMSI.", new Object[0]);
                return subscriberId;
            }
        } catch (Throwable unused2) {
            subscriberId = null;
        }
    }

    public static String c(Context context) {
        String str = "fail";
        if (context == null) {
            return "fail";
        }
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (string == null) {
                return "null";
            }
            try {
                return string.toLowerCase();
            } catch (Throwable th) {
                str = string;
                th = th;
                if (x.a(th)) {
                    return str;
                }
                x.a("Failed to get Android ID.", new Object[0]);
                return str;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String d(Context context) {
        WifiInfo connectionInfo;
        String str = "fail";
        if (context == null) {
            return "fail";
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI);
            if (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
                return "fail";
            }
            String macAddress = connectionInfo.getMacAddress();
            if (macAddress == null) {
                return "null";
            }
            try {
                return macAddress.toLowerCase();
            } catch (Throwable th) {
                str = macAddress;
                th = th;
                if (x.a(th)) {
                    return str;
                }
                th.printStackTrace();
                return str;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static boolean o() {
        try {
            return Environment.getExternalStorageState().equals("mounted");
        } catch (Throwable th) {
            if (x.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:79:0x00b2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00a2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String p() throws java.lang.Throwable {
        /*
            java.lang.String r0 = "/system/build.prop"
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> L72
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> L72
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L65 java.lang.Throwable -> L6a
            r3 = 2048(0x800, float:2.87E-42)
            r0.<init>(r2, r3)     // Catch: java.lang.Throwable -> L65 java.lang.Throwable -> L6a
        Lf:
            java.lang.String r3 = r0.readLine()     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L9f
            r4 = 0
            if (r3 == 0) goto L3b
            java.lang.String r5 = "="
            r6 = 2
            java.lang.String[] r3 = r3.split(r5, r6)     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L9f
            int r5 = r3.length     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L9f
            if (r5 != r6) goto Lf
            r5 = r3[r4]     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L9f
            java.lang.String r6 = "ro.product.cpu.abilist"
            boolean r5 = r5.equals(r6)     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L9f
            r6 = 1
            if (r5 == 0) goto L2e
            r3 = r3[r6]     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L9f
            goto L3c
        L2e:
            r5 = r3[r4]     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L9f
            java.lang.String r7 = "ro.product.cpu.abi"
            boolean r5 = r5.equals(r7)     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L9f
            if (r5 == 0) goto Lf
            r3 = r3[r6]     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L9f
            goto L3c
        L3b:
            r3 = r1
        L3c:
            if (r3 == 0) goto L46
            java.lang.String r5 = ","
            java.lang.String[] r3 = r3.split(r5)     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L9f
            r3 = r3[r4]     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L9f
        L46:
            r0.close()     // Catch: java.io.IOException -> L4a
            goto L54
        L4a:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L54
            r0.printStackTrace()
        L54:
            r2.close()     // Catch: java.io.IOException -> L58
            goto L62
        L58:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L62
            r0.printStackTrace()
        L62:
            return r3
        L63:
            r3 = move-exception
            goto L75
        L65:
            r0 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
            goto La0
        L6a:
            r3 = move-exception
            r0 = r1
            goto L75
        L6d:
            r0 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
            goto La0
        L72:
            r3 = move-exception
            r0 = r1
            r2 = r0
        L75:
            boolean r4 = com.tencent.bugly.proguard.x.a(r3)     // Catch: java.lang.Throwable -> L9f
            if (r4 != 0) goto L7e
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L9f
        L7e:
            if (r0 == 0) goto L8e
            r0.close()     // Catch: java.io.IOException -> L84
            goto L8e
        L84:
            r0 = move-exception
            boolean r3 = com.tencent.bugly.proguard.x.a(r0)
            if (r3 != 0) goto L8e
            r0.printStackTrace()
        L8e:
            if (r2 == 0) goto L9e
            r2.close()     // Catch: java.io.IOException -> L94
            goto L9e
        L94:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.proguard.x.a(r0)
            if (r2 != 0) goto L9e
            r0.printStackTrace()
        L9e:
            return r1
        L9f:
            r1 = move-exception
        La0:
            if (r0 == 0) goto Lb0
            r0.close()     // Catch: java.io.IOException -> La6
            goto Lb0
        La6:
            r0 = move-exception
            boolean r3 = com.tencent.bugly.proguard.x.a(r0)
            if (r3 != 0) goto Lb0
            r0.printStackTrace()
        Lb0:
            if (r2 == 0) goto Lc0
            r2.close()     // Catch: java.io.IOException -> Lb6
            goto Lc0
        Lb6:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.proguard.x.a(r0)
            if (r2 != 0) goto Lc0
            r0.printStackTrace()
        Lc0:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.p():java.lang.String");
    }

    public static String a(boolean z) {
        String strP = null;
        if (z) {
            try {
                strP = p();
            } catch (Throwable th) {
                if (x.a(th)) {
                    return "fail";
                }
                th.printStackTrace();
                return "fail";
            }
        }
        if (strP == null) {
            strP = System.getProperty("os.arch");
        }
        return strP;
    }

    public static long d() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return statFs.getBlockCount() * statFs.getBlockSize();
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return -1L;
        }
    }

    public static long e() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return -1L;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.BufferedReader] */
    public static long f() throws Throwable {
        FileReader fileReader;
        Throwable th;
        Throwable th2;
        BufferedReader bufferedReader;
        ?? r0 = "/proc/meminfo";
        try {
            try {
                fileReader = new FileReader("/proc/meminfo");
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            fileReader = null;
            th = th4;
            r0 = 0;
        }
        try {
            bufferedReader = new BufferedReader(fileReader, 2048);
            try {
                String line = bufferedReader.readLine();
                if (line != null) {
                    long j = Long.parseLong(line.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10;
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        if (!x.a(e)) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        fileReader.close();
                    } catch (IOException e2) {
                        if (!x.a(e2)) {
                            e2.printStackTrace();
                        }
                    }
                    return j;
                }
                try {
                    bufferedReader.close();
                } catch (IOException e3) {
                    if (!x.a(e3)) {
                        e3.printStackTrace();
                    }
                }
                try {
                    fileReader.close();
                    return -1L;
                } catch (IOException e4) {
                    if (x.a(e4)) {
                        return -1L;
                    }
                    e4.printStackTrace();
                    return -1L;
                }
            } catch (Throwable th5) {
                th2 = th5;
                if (!x.a(th2)) {
                    th2.printStackTrace();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e5) {
                        if (!x.a(e5)) {
                            e5.printStackTrace();
                        }
                    }
                }
                if (fileReader == null) {
                    return -2L;
                }
                try {
                    fileReader.close();
                    return -2L;
                } catch (IOException e6) {
                    if (x.a(e6)) {
                        return -2L;
                    }
                    e6.printStackTrace();
                    return -2L;
                }
            }
        } catch (Throwable th6) {
            th2 = th6;
            bufferedReader = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:117:0x014e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x013e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long g() throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 349
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.g():long");
    }

    public static long h() {
        if (!o()) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return statFs.getBlockCount() * statFs.getBlockSize();
        } catch (Throwable th) {
            if (x.a(th)) {
                return -2L;
            }
            th.printStackTrace();
            return -2L;
        }
    }

    public static long i() {
        if (!o()) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Throwable th) {
            if (x.a(th)) {
                return -2L;
            }
            th.printStackTrace();
            return -2L;
        }
    }

    public static String j() {
        try {
            return Locale.getDefault().getCountry();
        } catch (Throwable th) {
            if (x.a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    public static String k() {
        try {
            return Build.BRAND;
        } catch (Throwable th) {
            if (x.a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    public static String e(Context context) {
        NetworkInfo activeNetworkInfo;
        TelephonyManager telephonyManager;
        String str = UtilityImpl.NET_TYPE_UNKNOWN;
        try {
            activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            if (!x.a(e)) {
                e.printStackTrace();
            }
        }
        if (activeNetworkInfo == null) {
            return null;
        }
        if (activeNetworkInfo.getType() == 1) {
            return "WIFI";
        }
        if (activeNetworkInfo.getType() == 0 && (telephonyManager = (TelephonyManager) context.getSystemService("phone")) != null) {
            int networkType = telephonyManager.getNetworkType();
            switch (networkType) {
                case 1:
                    return "GPRS";
                case 2:
                    return "EDGE";
                case 3:
                    return "UMTS";
                case 4:
                    return "CDMA";
                case 5:
                    return "EVDO_0";
                case 6:
                    return "EVDO_A";
                case 7:
                    return "1xRTT";
                case 8:
                    return "HSDPA";
                case 9:
                    return "HSUPA";
                case 10:
                    return "HSPA";
                case 11:
                    return "iDen";
                case 12:
                    return "EVDO_B";
                case 13:
                    return "LTE";
                case 14:
                    return "eHRPD";
                case 15:
                    return "HSPA+";
                default:
                    str = "MOBILE(" + networkType + l.t;
                    break;
            }
        }
        return str;
    }

    public static String f(Context context) throws Throwable {
        String strA = z.a(context, "ro.miui.ui.version.name");
        if (!z.a(strA) && !strA.equals("fail")) {
            return "XiaoMi/MIUI/" + strA;
        }
        String strA2 = z.a(context, "ro.build.version.emui");
        if (!z.a(strA2) && !strA2.equals("fail")) {
            return "HuaWei/EMOTION/" + strA2;
        }
        String strA3 = z.a(context, "ro.lenovo.series");
        if (!z.a(strA3) && !strA3.equals("fail")) {
            return "Lenovo/VIBE/" + z.a(context, "ro.build.version.incremental");
        }
        String strA4 = z.a(context, "ro.build.nubia.rom.name");
        if (!z.a(strA4) && !strA4.equals("fail")) {
            return "Zte/NUBIA/" + strA4 + "_" + z.a(context, "ro.build.nubia.rom.code");
        }
        String strA5 = z.a(context, "ro.meizu.product.model");
        if (!z.a(strA5) && !strA5.equals("fail")) {
            return "Meizu/FLYME/" + z.a(context, "ro.build.display.id");
        }
        String strA6 = z.a(context, "ro.build.version.opporom");
        if (!z.a(strA6) && !strA6.equals("fail")) {
            return "Oppo/COLOROS/" + strA6;
        }
        String strA7 = z.a(context, "ro.vivo.os.build.display.id");
        if (!z.a(strA7) && !strA7.equals("fail")) {
            return "vivo/FUNTOUCH/" + strA7;
        }
        String strA8 = z.a(context, "ro.aa.romver");
        if (!z.a(strA8) && !strA8.equals("fail")) {
            return "htc/" + strA8 + "/" + z.a(context, "ro.build.description");
        }
        String strA9 = z.a(context, "ro.lewa.version");
        if (!z.a(strA9) && !strA9.equals("fail")) {
            return "tcl/" + strA9 + "/" + z.a(context, "ro.build.display.id");
        }
        String strA10 = z.a(context, "ro.gn.gnromvernumber");
        if (!z.a(strA10) && !strA10.equals("fail")) {
            return "amigo/" + strA10 + "/" + z.a(context, "ro.build.display.id");
        }
        String strA11 = z.a(context, "ro.build.tyd.kbstyle_version");
        if (!z.a(strA11) && !strA11.equals("fail")) {
            return "dido/" + strA11;
        }
        return z.a(context, "ro.build.fingerprint") + "/" + z.a(context, "ro.build.rom.id");
    }

    public static String g(Context context) {
        return z.a(context, "ro.board.platform");
    }

    public static boolean h(Context context) throws Throwable {
        boolean zExists;
        try {
            zExists = new File("/system/app/Superuser.apk").exists();
        } catch (Throwable th) {
            if (!x.b(th)) {
                th.printStackTrace();
            }
            zExists = false;
        }
        Boolean bool = null;
        ArrayList<String> arrayListA = z.a(context, new String[]{"/system/bin/sh", "-c", "type su"});
        if (arrayListA != null && arrayListA.size() > 0) {
            Iterator<String> it = arrayListA.iterator();
            while (it.hasNext()) {
                String next = it.next();
                x.c(next, new Object[0]);
                if (next.contains("not found")) {
                    bool = false;
                }
            }
            if (bool == null) {
                bool = true;
            }
        }
        return (Build.TAGS != null && Build.TAGS.contains("test-keys")) || zExists || Boolean.valueOf(bool == null ? false : bool.booleanValue()).booleanValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x009c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String l() throws java.lang.Throwable {
        /*
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L97 java.lang.Throwable -> La5
            r1.<init>()     // Catch: java.lang.Throwable -> L97 java.lang.Throwable -> La5
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L97 java.lang.Throwable -> La5
            java.lang.String r3 = "/sys/block/mmcblk0/device/type"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L97 java.lang.Throwable -> La5
            boolean r2 = r2.exists()     // Catch: java.lang.Throwable -> L97 java.lang.Throwable -> La5
            if (r2 == 0) goto L2c
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L97 java.lang.Throwable -> La5
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L97 java.lang.Throwable -> La5
            java.lang.String r4 = "/sys/block/mmcblk0/device/type"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L97 java.lang.Throwable -> La5
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L97 java.lang.Throwable -> La5
            java.lang.String r3 = r2.readLine()     // Catch: java.lang.Throwable -> L95 java.lang.Throwable -> La6
            if (r3 == 0) goto L28
            r1.append(r3)     // Catch: java.lang.Throwable -> L95 java.lang.Throwable -> La6
        L28:
            r2.close()     // Catch: java.lang.Throwable -> L95 java.lang.Throwable -> La6
            goto L2d
        L2c:
            r2 = r0
        L2d:
            java.lang.String r3 = ","
            r1.append(r3)     // Catch: java.lang.Throwable -> L95 java.lang.Throwable -> La6
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L95 java.lang.Throwable -> La6
            java.lang.String r4 = "/sys/block/mmcblk0/device/name"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L95 java.lang.Throwable -> La6
            boolean r3 = r3.exists()     // Catch: java.lang.Throwable -> L95 java.lang.Throwable -> La6
            if (r3 == 0) goto L5e
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L95 java.lang.Throwable -> La6
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L95 java.lang.Throwable -> La6
            java.lang.String r5 = "/sys/block/mmcblk0/device/name"
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L95 java.lang.Throwable -> La6
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L95 java.lang.Throwable -> La6
            java.lang.String r2 = r3.readLine()     // Catch: java.lang.Throwable -> L59 java.lang.Throwable -> L5c
            if (r2 == 0) goto L54
            r1.append(r2)     // Catch: java.lang.Throwable -> L59 java.lang.Throwable -> L5c
        L54:
            r3.close()     // Catch: java.lang.Throwable -> L59 java.lang.Throwable -> L5c
            r2 = r3
            goto L5e
        L59:
            r0 = move-exception
            r2 = r3
            goto L9a
        L5c:
            r2 = r3
            goto La6
        L5e:
            java.lang.String r3 = ","
            r1.append(r3)     // Catch: java.lang.Throwable -> L95 java.lang.Throwable -> La6
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L95 java.lang.Throwable -> La6
            java.lang.String r4 = "/sys/block/mmcblk0/device/cid"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L95 java.lang.Throwable -> La6
            boolean r3 = r3.exists()     // Catch: java.lang.Throwable -> L95 java.lang.Throwable -> La6
            if (r3 == 0) goto L86
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L95 java.lang.Throwable -> La6
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L95 java.lang.Throwable -> La6
            java.lang.String r5 = "/sys/block/mmcblk0/device/cid"
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L95 java.lang.Throwable -> La6
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L95 java.lang.Throwable -> La6
            java.lang.String r2 = r3.readLine()     // Catch: java.lang.Throwable -> L59 java.lang.Throwable -> L5c
            if (r2 == 0) goto L85
            r1.append(r2)     // Catch: java.lang.Throwable -> L59 java.lang.Throwable -> L5c
        L85:
            r2 = r3
        L86:
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L95 java.lang.Throwable -> La6
            if (r2 == 0) goto L94
            r2.close()     // Catch: java.io.IOException -> L90
            goto L94
        L90:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)
        L94:
            return r1
        L95:
            r0 = move-exception
            goto L9a
        L97:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L9a:
            if (r2 == 0) goto La4
            r2.close()     // Catch: java.io.IOException -> La0
            goto La4
        La0:
            r1 = move-exception
            com.tencent.bugly.proguard.x.a(r1)
        La4:
            throw r0
        La5:
            r2 = r0
        La6:
            if (r2 == 0) goto Lb0
            r2.close()     // Catch: java.io.IOException -> Lac
            goto Lb0
        Lac:
            r1 = move-exception
            com.tencent.bugly.proguard.x.a(r1)
        Lb0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.l():java.lang.String");
    }

    public static String i(Context context) throws Throwable {
        StringBuilder sb = new StringBuilder();
        String strA = z.a(context, "ro.genymotion.version");
        if (strA != null) {
            sb.append("ro.genymotion.version");
            sb.append("|");
            sb.append(strA);
            sb.append("\n");
        }
        String strA2 = z.a(context, "androVM.vbox_dpi");
        if (strA2 != null) {
            sb.append("androVM.vbox_dpi");
            sb.append("|");
            sb.append(strA2);
            sb.append("\n");
        }
        String strA3 = z.a(context, "qemu.sf.fake_camera");
        if (strA3 != null) {
            sb.append("qemu.sf.fake_camera");
            sb.append("|");
            sb.append(strA3);
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String j(android.content.Context r5) throws java.lang.Throwable {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = com.tencent.bugly.crashreport.common.info.b.a
            if (r1 != 0) goto L11
            java.lang.String r1 = "ro.secure"
            java.lang.String r1 = com.tencent.bugly.proguard.z.a(r5, r1)
            com.tencent.bugly.crashreport.common.info.b.a = r1
        L11:
            java.lang.String r1 = com.tencent.bugly.crashreport.common.info.b.a
            if (r1 == 0) goto L29
            java.lang.String r1 = "ro.secure"
            r0.append(r1)
            java.lang.String r1 = "|"
            r0.append(r1)
            java.lang.String r1 = com.tencent.bugly.crashreport.common.info.b.a
            r0.append(r1)
            java.lang.String r1 = "\n"
            r0.append(r1)
        L29:
            java.lang.String r1 = com.tencent.bugly.crashreport.common.info.b.b
            if (r1 != 0) goto L35
            java.lang.String r1 = "ro.debuggable"
            java.lang.String r5 = com.tencent.bugly.proguard.z.a(r5, r1)
            com.tencent.bugly.crashreport.common.info.b.b = r5
        L35:
            java.lang.String r5 = com.tencent.bugly.crashreport.common.info.b.b
            if (r5 == 0) goto L4d
            java.lang.String r5 = "ro.debuggable"
            r0.append(r5)
            java.lang.String r5 = "|"
            r0.append(r5)
            java.lang.String r5 = com.tencent.bugly.crashreport.common.info.b.b
            r0.append(r5)
            java.lang.String r5 = "\n"
            r0.append(r5)
        L4d:
            r5 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L90 java.lang.Throwable -> L94
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L90 java.lang.Throwable -> L94
            java.lang.String r3 = "/proc/self/status"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L90 java.lang.Throwable -> L94
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L90 java.lang.Throwable -> L94
        L5a:
            java.lang.String r5 = r1.readLine()     // Catch: java.lang.Throwable -> L8e java.lang.Throwable -> Laa
            if (r5 == 0) goto L68
            java.lang.String r2 = "TracerPid:"
            boolean r2 = r5.startsWith(r2)     // Catch: java.lang.Throwable -> L8e java.lang.Throwable -> Laa
            if (r2 == 0) goto L5a
        L68:
            if (r5 == 0) goto L81
            r2 = 10
            java.lang.String r5 = r5.substring(r2)     // Catch: java.lang.Throwable -> L8e java.lang.Throwable -> Laa
            java.lang.String r5 = r5.trim()     // Catch: java.lang.Throwable -> L8e java.lang.Throwable -> Laa
            java.lang.String r2 = "tracer_pid"
            r0.append(r2)     // Catch: java.lang.Throwable -> L8e java.lang.Throwable -> Laa
            java.lang.String r2 = "|"
            r0.append(r2)     // Catch: java.lang.Throwable -> L8e java.lang.Throwable -> Laa
            r0.append(r5)     // Catch: java.lang.Throwable -> L8e java.lang.Throwable -> Laa
        L81:
            java.lang.String r5 = r0.toString()     // Catch: java.lang.Throwable -> L8e java.lang.Throwable -> Laa
            r1.close()     // Catch: java.io.IOException -> L89
            goto L8d
        L89:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)
        L8d:
            return r5
        L8e:
            r5 = move-exception
            goto L98
        L90:
            r0 = move-exception
            r1 = r5
            r5 = r0
            goto Lab
        L94:
            r1 = move-exception
            r4 = r1
            r1 = r5
            r5 = r4
        L98:
            com.tencent.bugly.proguard.x.a(r5)     // Catch: java.lang.Throwable -> Laa
            if (r1 == 0) goto La5
            r1.close()     // Catch: java.io.IOException -> La1
            goto La5
        La1:
            r5 = move-exception
            com.tencent.bugly.proguard.x.a(r5)
        La5:
            java.lang.String r5 = r0.toString()
            return r5
        Laa:
            r5 = move-exception
        Lab:
            if (r1 == 0) goto Lb5
            r1.close()     // Catch: java.io.IOException -> Lb1
            goto Lb5
        Lb1:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)
        Lb5:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.j(android.content.Context):java.lang.String");
    }

    public static String m() throws Throwable {
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader2 = null;
        try {
            try {
                if (new File("/sys/class/power_supply/ac/online").exists()) {
                    bufferedReader = new BufferedReader(new FileReader("/sys/class/power_supply/ac/online"));
                    try {
                        String line = bufferedReader.readLine();
                        if (line != null) {
                            sb.append("ac_online");
                            sb.append("|");
                            sb.append(line);
                        }
                        bufferedReader.close();
                        bufferedReader2 = bufferedReader;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e) {
                                x.a(e);
                            }
                        }
                        throw th;
                    }
                }
                sb.append("\n");
                if (new File("/sys/class/power_supply/usb/online").exists()) {
                    BufferedReader bufferedReader3 = new BufferedReader(new FileReader("/sys/class/power_supply/usb/online"));
                    String line2 = bufferedReader3.readLine();
                    if (line2 != null) {
                        sb.append("usb_online");
                        sb.append("|");
                        sb.append(line2);
                    }
                    bufferedReader3.close();
                    bufferedReader2 = bufferedReader3;
                }
                sb.append("\n");
                if (new File("/sys/class/power_supply/battery/capacity").exists()) {
                    bufferedReader = new BufferedReader(new FileReader("/sys/class/power_supply/battery/capacity"));
                    String line3 = bufferedReader.readLine();
                    if (line3 != null) {
                        sb.append("battery_capacity");
                        sb.append("|");
                        sb.append(line3);
                    }
                    bufferedReader.close();
                    bufferedReader2 = bufferedReader;
                }
            } catch (IOException e2) {
                x.a(e2);
            }
        } catch (Throwable th2) {
            th = th2;
        }
        if (bufferedReader2 != null) {
            bufferedReader2.close();
        }
        return sb.toString();
    }

    public static String k(Context context) throws Throwable {
        StringBuilder sb = new StringBuilder();
        String strA = z.a(context, "gsm.sim.state");
        if (strA != null) {
            sb.append("gsm.sim.state");
            sb.append("|");
            sb.append(strA);
        }
        sb.append("\n");
        String strA2 = z.a(context, "gsm.sim.state2");
        if (strA2 != null) {
            sb.append("gsm.sim.state2");
            sb.append("|");
            sb.append(strA2);
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x004a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0030 -> B:31:0x0045). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long n() throws java.lang.Throwable {
        /*
            r0 = 0
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L36 java.lang.Throwable -> L39
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L36 java.lang.Throwable -> L39
            java.lang.String r4 = "/proc/uptime"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L36 java.lang.Throwable -> L39
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L36 java.lang.Throwable -> L39
            java.lang.String r1 = r2.readLine()     // Catch: java.lang.Throwable -> L34 java.lang.Throwable -> L47
            if (r1 == 0) goto L2b
            java.lang.String r3 = " "
            java.lang.String[] r1 = r1.split(r3)     // Catch: java.lang.Throwable -> L34 java.lang.Throwable -> L47
            r3 = 0
            r1 = r1[r3]     // Catch: java.lang.Throwable -> L34 java.lang.Throwable -> L47
            long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L34 java.lang.Throwable -> L47
            r5 = 1000(0x3e8, double:4.94E-321)
            long r3 = r3 / r5
            float r3 = (float) r3     // Catch: java.lang.Throwable -> L34 java.lang.Throwable -> L47
            float r1 = java.lang.Float.parseFloat(r1)     // Catch: java.lang.Throwable -> L34 java.lang.Throwable -> L47
            float r3 = r3 - r1
            r0 = r3
        L2b:
            r2.close()     // Catch: java.io.IOException -> L2f
            goto L45
        L2f:
            r1 = move-exception
            com.tencent.bugly.proguard.x.a(r1)
            goto L45
        L34:
            r1 = move-exception
            goto L3d
        L36:
            r0 = move-exception
            r2 = r1
            goto L48
        L39:
            r2 = move-exception
            r7 = r2
            r2 = r1
            r1 = r7
        L3d:
            com.tencent.bugly.proguard.x.a(r1)     // Catch: java.lang.Throwable -> L47
            if (r2 == 0) goto L45
            r2.close()     // Catch: java.io.IOException -> L2f
        L45:
            long r0 = (long) r0
            return r0
        L47:
            r0 = move-exception
        L48:
            if (r2 == 0) goto L52
            r2.close()     // Catch: java.io.IOException -> L4e
            goto L52
        L4e:
            r1 = move-exception
            com.tencent.bugly.proguard.x.a(r1)
        L52:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.n():long");
    }
}
