package com.alipay.security.mobile.module.b;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import androidx.core.app.NotificationCompat;
import com.baidu.mobads.sdk.internal.av;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private static b f2711a = new b();

    private b() {
    }

    public static b a() {
        return f2711a;
    }

    public static String a(Context context) {
        if (a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        String deviceId = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    deviceId = telephonyManager.getDeviceId();
                }
            } catch (Throwable unused) {
            }
        }
        return deviceId == null ? "" : deviceId;
    }

    private static boolean a(Context context, String str) {
        return !(context.getPackageManager().checkPermission(str, context.getPackageName()) == 0);
    }

    public static String b() {
        long availableBlocks;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            availableBlocks = statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Throwable unused) {
            availableBlocks = 0;
        }
        return String.valueOf(availableBlocks);
    }

    public static String b(Context context) {
        String subscriberId = "";
        if (a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    subscriberId = telephonyManager.getSubscriberId();
                }
            } catch (Throwable unused) {
            }
        }
        return subscriberId == null ? "" : subscriberId;
    }

    public static String c() {
        long blockSize = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(com.alipay.security.mobile.module.a.a.a().getPath());
                blockSize = statFs.getBlockSize() * statFs.getAvailableBlocks();
            }
        } catch (Throwable unused) {
        }
        return String.valueOf(blockSize);
    }

    public static String c(Context context) {
        int i;
        try {
            i = Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0);
        } catch (Throwable unused) {
            i = 0;
        }
        return i == 1 ? "1" : OnekeyLoginConstants.CU_RESULT_SUCCESS;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0040, code lost:
    
        r0 = r6.substring(r6.indexOf(":") + 1, r6.length()).trim();
     */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007f A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:83:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String d() throws java.lang.Throwable {
        /*
            java.lang.String r0 = "0000000000000000"
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L5b java.lang.Throwable -> L6e
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L5b java.lang.Throwable -> L6e
            java.lang.String r4 = "/proc/cpuinfo"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L5b java.lang.Throwable -> L6e
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L5b java.lang.Throwable -> L6e
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L59
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L59
            java.io.LineNumberReader r4 = new java.io.LineNumberReader     // Catch: java.lang.Throwable -> L54 java.lang.Throwable -> L70
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L54 java.lang.Throwable -> L70
            r1 = 1
            r5 = 1
        L1b:
            r6 = 100
            if (r5 >= r6) goto L4a
            java.lang.String r6 = r4.readLine()     // Catch: java.lang.Throwable -> L45 java.lang.Throwable -> L48
            if (r6 == 0) goto L4a
            java.lang.String r7 = "Serial"
            int r7 = r6.indexOf(r7)     // Catch: java.lang.Throwable -> L45 java.lang.Throwable -> L48
            if (r7 < 0) goto L42
            java.lang.String r5 = ":"
            int r5 = r6.indexOf(r5)     // Catch: java.lang.Throwable -> L45 java.lang.Throwable -> L48
            int r5 = r5 + r1
            int r1 = r6.length()     // Catch: java.lang.Throwable -> L45 java.lang.Throwable -> L48
            java.lang.String r1 = r6.substring(r5, r1)     // Catch: java.lang.Throwable -> L45 java.lang.Throwable -> L48
            java.lang.String r1 = r1.trim()     // Catch: java.lang.Throwable -> L45 java.lang.Throwable -> L48
            r0 = r1
            goto L4a
        L42:
            int r5 = r5 + 1
            goto L1b
        L45:
            r0 = move-exception
            r1 = r4
            goto L5e
        L48:
            r1 = r4
            goto L70
        L4a:
            r4.close()     // Catch: java.lang.Throwable -> L4d
        L4d:
            r3.close()     // Catch: java.lang.Throwable -> L50
        L50:
            r2.close()     // Catch: java.lang.Throwable -> L7d
            goto L7d
        L54:
            r0 = move-exception
            goto L5e
        L56:
            r0 = move-exception
            r3 = r1
            goto L5e
        L59:
            r3 = r1
            goto L70
        L5b:
            r0 = move-exception
            r2 = r1
            r3 = r2
        L5e:
            if (r1 == 0) goto L63
            r1.close()     // Catch: java.lang.Throwable -> L63
        L63:
            if (r3 == 0) goto L68
            r3.close()     // Catch: java.lang.Throwable -> L68
        L68:
            if (r2 == 0) goto L6d
            r2.close()     // Catch: java.lang.Throwable -> L6d
        L6d:
            throw r0
        L6e:
            r2 = r1
            r3 = r2
        L70:
            if (r1 == 0) goto L75
            r1.close()     // Catch: java.lang.Throwable -> L75
        L75:
            if (r3 == 0) goto L7a
            r3.close()     // Catch: java.lang.Throwable -> L7a
        L7a:
            if (r2 == 0) goto L7d
            goto L50
        L7d:
            if (r0 != 0) goto L81
            java.lang.String r0 = ""
        L81:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.b.b.d():java.lang.String");
    }

    public static String d(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            int i = audioManager.getRingerMode() == 0 ? 1 : 0;
            int streamVolume = audioManager.getStreamVolume(0);
            int streamVolume2 = audioManager.getStreamVolume(1);
            int streamVolume3 = audioManager.getStreamVolume(2);
            int streamVolume4 = audioManager.getStreamVolume(3);
            int streamVolume5 = audioManager.getStreamVolume(4);
            jSONObject.put("ringermode", String.valueOf(i));
            jSONObject.put("call", String.valueOf(streamVolume));
            jSONObject.put("system", String.valueOf(streamVolume2));
            jSONObject.put("ring", String.valueOf(streamVolume3));
            jSONObject.put("music", String.valueOf(streamVolume4));
            jSONObject.put(NotificationCompat.CATEGORY_ALARM, String.valueOf(streamVolume5));
        } catch (Throwable unused) {
        }
        return jSONObject.toString();
    }

    public static String e(Context context) {
        String networkOperatorName = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    networkOperatorName = telephonyManager.getNetworkOperatorName();
                }
            } catch (Throwable unused) {
            }
        }
        return (networkOperatorName == null || "null".equals(networkOperatorName)) ? "" : networkOperatorName;
    }

    public static String f() throws Throwable {
        String strV = v();
        return !com.alipay.security.mobile.module.a.a.a(strV) ? strV : w();
    }

    public static String f(Context context) throws NoSuchAlgorithmException {
        List<Sensor> sensorList;
        String strE = null;
        if (context != null) {
            try {
                SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
                if (sensorManager != null && (sensorList = sensorManager.getSensorList(-1)) != null && sensorList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (Sensor sensor : sensorList) {
                        sb.append(sensor.getName());
                        sb.append(sensor.getVersion());
                        sb.append(sensor.getVendor());
                    }
                    strE = com.alipay.security.mobile.module.a.a.e(sb.toString());
                }
            } catch (Throwable unused) {
            }
        }
        return strE == null ? "" : strE;
    }

    public static String g() throws Throwable {
        BufferedReader bufferedReader;
        Throwable th;
        FileReader fileReader;
        String[] strArrSplit;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                fileReader = new FileReader("/proc/cpuinfo");
                try {
                    bufferedReader = new BufferedReader(fileReader);
                } catch (Throwable th2) {
                    bufferedReader = null;
                    th = th2;
                }
            } catch (Throwable unused) {
                return "";
            }
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
            fileReader = null;
        }
        try {
            strArrSplit = bufferedReader.readLine().split(":\\s+", 2);
        } catch (Throwable unused2) {
            bufferedReader2 = bufferedReader;
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Throwable unused3) {
                }
            }
            if (bufferedReader2 == null) {
                return "";
            }
            bufferedReader2.close();
            return "";
        }
        if (strArrSplit == null || strArrSplit.length <= 1) {
            try {
                fileReader.close();
            } catch (Throwable unused4) {
            }
            bufferedReader.close();
            return "";
        }
        String str = strArrSplit[1];
        try {
            fileReader.close();
        } catch (Throwable unused5) {
        }
        try {
            bufferedReader.close();
        } catch (Throwable unused6) {
        }
        return str;
    }

    public static String g(Context context) {
        List<Sensor> sensorList;
        JSONArray jSONArray = new JSONArray();
        if (context != null) {
            try {
                SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
                if (sensorManager != null && (sensorList = sensorManager.getSensorList(-1)) != null && sensorList.size() > 0) {
                    for (Sensor sensor : sensorList) {
                        if (sensor != null) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("name", sensor.getName());
                            jSONObject.put("version", sensor.getVersion());
                            jSONObject.put("vendor", sensor.getVendor());
                            jSONArray.put(jSONObject);
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return jSONArray.toString();
    }

    public static String h() throws Throwable {
        FileReader fileReader;
        Throwable th;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                fileReader = new FileReader("/proc/meminfo");
                try {
                    bufferedReader = new BufferedReader(fileReader, 8192);
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
            }
            try {
                j = bufferedReader.readLine() != null ? Integer.parseInt(r1.split("\\s+")[1]) : 0L;
                try {
                    fileReader.close();
                } catch (Throwable unused3) {
                }
                bufferedReader.close();
            } catch (Throwable th2) {
                th = th2;
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (Throwable unused4) {
                    }
                }
                if (bufferedReader == null) {
                    throw th;
                }
                try {
                    bufferedReader.close();
                    throw th;
                } catch (Throwable unused5) {
                    throw th;
                }
            }
        } catch (Throwable unused6) {
            fileReader = null;
        }
        return String.valueOf(j);
    }

    public static String h(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return Integer.toString(displayMetrics.widthPixels) + "*" + Integer.toString(displayMetrics.heightPixels);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String i() {
        long blockCount;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            blockCount = statFs.getBlockCount() * statFs.getBlockSize();
        } catch (Throwable unused) {
            blockCount = 0;
        }
        return String.valueOf(blockCount);
    }

    public static String i(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.widthPixels);
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String j() {
        long blockSize = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                blockSize = statFs.getBlockSize() * statFs.getBlockCount();
            }
        } catch (Throwable unused) {
        }
        return String.valueOf(blockSize);
    }

    public static String j(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.heightPixels);
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String k() {
        String str = "";
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str = (String) cls.getMethod(ApiRequest.METHOD_GET, String.class, String.class).invoke(cls.newInstance(), "gsm.version.baseband", "no message");
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    public static String k(Context context) {
        if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
            return "";
        }
        try {
            String macAddress = ((WifiManager) context.getSystemService(OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR)).getConnectionInfo().getMacAddress();
            if (macAddress != null) {
                try {
                    if (macAddress.length() != 0 && !"02:00:00:00:00:00".equals(macAddress)) {
                        return macAddress;
                    }
                } catch (Throwable unused) {
                    return macAddress;
                }
            }
            return u();
        } catch (Throwable unused2) {
            return "";
        }
    }

    public static String l() {
        String string = "";
        try {
            string = Locale.getDefault().toString();
        } catch (Throwable unused) {
        }
        return string == null ? "" : string;
    }

    public static String l(Context context) {
        if (a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        String str = "";
        try {
            String simSerialNumber = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
            if (simSerialNumber != null) {
                if (simSerialNumber == null) {
                    return simSerialNumber;
                }
                try {
                    if (simSerialNumber.length() != 0) {
                        return simSerialNumber;
                    }
                } catch (Throwable unused) {
                    return simSerialNumber;
                }
            }
            str = "";
        } catch (Throwable unused2) {
        }
        return str;
    }

    public static String m() {
        String displayName = "";
        try {
            displayName = TimeZone.getDefault().getDisplayName(false, 0);
        } catch (Throwable unused) {
        }
        return displayName == null ? "" : displayName;
    }

    public static String m(Context context) {
        String string;
        try {
            string = Settings.Secure.getString(context.getContentResolver(), av.f);
        } catch (Throwable unused) {
            string = "";
        }
        return string == null ? "" : string;
    }

    public static String n() {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis() - SystemClock.elapsedRealtime();
            StringBuilder sb = new StringBuilder();
            sb.append(jCurrentTimeMillis - (jCurrentTimeMillis % 1000));
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String n(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager != null ? String.valueOf(telephonyManager.getNetworkType()) : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String o() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(SystemClock.elapsedRealtime());
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String o(Context context) {
        String bssid = "";
        if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
            return "";
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR);
            if (wifiManager.isWifiEnabled()) {
                bssid = wifiManager.getConnectionInfo().getBSSID();
            }
        } catch (Throwable unused) {
        }
        return bssid == null ? "" : bssid;
    }

    public static String p() {
        try {
            StringBuilder sb = new StringBuilder();
            String[] strArr = {"/dev/qemu_pipe", "/dev/socket/qemud", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/genyd", "/dev/socket/baseband_genyd"};
            sb.append("00:");
            for (int i = 0; i < 7; i++) {
                sb.append(new File(strArr[i]).exists() ? "1" : OnekeyLoginConstants.CU_RESULT_SUCCESS);
            }
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String p(Context context) {
        String serial;
        try {
            serial = Build.VERSION.SDK_INT >= 29 ? "" : (Build.VERSION.SDK_INT < 26 || context.getApplicationInfo().targetSdkVersion < 28) ? Build.SERIAL : Build.getSerial();
        } catch (Throwable unused) {
            serial = "";
        }
        return serial == null ? "" : serial;
    }

    public static String q() {
        String[] strArr = {"dalvik.system.Taint"};
        StringBuilder sb = new StringBuilder();
        sb.append("00");
        sb.append(":");
        for (int i = 0; i <= 0; i++) {
            try {
                Class.forName(strArr[0]);
                sb.append("1");
            } catch (Throwable unused) {
                sb.append(OnekeyLoginConstants.CU_RESULT_SUCCESS);
            }
        }
        return sb.toString();
    }

    public static String q(Context context) {
        try {
            String strT = t(context);
            String strX = x();
            if (!com.alipay.security.mobile.module.a.a.b(strT) || !com.alipay.security.mobile.module.a.a.b(strX)) {
                return "";
            }
            return strT + ":" + x();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String r() throws Throwable {
        StringBuilder sb = new StringBuilder();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("/system/build.prop", "ro.product.name=sdk");
        linkedHashMap.put("/proc/tty/drivers", "goldfish");
        linkedHashMap.put("/proc/cpuinfo", "goldfish");
        sb.append("00:");
        for (String str : linkedHashMap.keySet()) {
            LineNumberReader lineNumberReader = null;
            char c2 = '0';
            try {
                LineNumberReader lineNumberReader2 = new LineNumberReader(new InputStreamReader(new FileInputStream(str)));
                while (true) {
                    try {
                        String line = lineNumberReader2.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.toLowerCase().contains((CharSequence) linkedHashMap.get(str))) {
                            c2 = '1';
                            break;
                        }
                    } catch (Throwable th) {
                        th = th;
                        lineNumberReader = lineNumberReader2;
                        sb.append('0');
                        if (lineNumberReader != null) {
                            try {
                                lineNumberReader.close();
                            } catch (Throwable unused) {
                            }
                        }
                        throw th;
                    }
                }
                sb.append(c2);
                try {
                    lineNumberReader2.close();
                } catch (Throwable unused2) {
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return sb.toString();
    }

    public static String r(Context context) {
        try {
            long jMax = 0;
            if (!((KeyguardManager) context.getSystemService("keyguard")).isKeyguardSecure()) {
                return "0:0";
            }
            String[] strArr = {"/data/system/password.key", "/data/system/gesture.key", "/data/system/gatekeeper.password.key", "/data/system/gatekeeper.gesture.key", "/data/system/gatekeeper.pattern.key"};
            for (int i = 0; i < 5; i++) {
                long jLastModified = -1;
                try {
                    jLastModified = new File(strArr[i]).lastModified();
                } catch (Throwable unused) {
                }
                jMax = Math.max(jLastModified, jMax);
            }
            return "1:" + jMax;
        } catch (Throwable unused2) {
            return "";
        }
    }

    public static String s() {
        StringBuilder sb = new StringBuilder();
        sb.append("00:");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("BRAND", "generic");
        linkedHashMap.put("BOARD", "unknown");
        linkedHashMap.put("DEVICE", "generic");
        linkedHashMap.put("HARDWARE", "goldfish");
        linkedHashMap.put("PRODUCT", "sdk");
        linkedHashMap.put("MODEL", "sdk");
        for (String str : linkedHashMap.keySet()) {
            char c2 = '0';
            try {
                String str2 = (String) Build.class.getField(str).get(null);
                String str3 = (String) linkedHashMap.get(str);
                String lowerCase = str2 != null ? str2.toLowerCase() : null;
                if (lowerCase != null && lowerCase.contains(str3)) {
                    c2 = '1';
                }
            } catch (Throwable unused) {
            }
            sb.append(c2);
        }
        return sb.toString();
    }

    public static String s(Context context) {
        try {
            Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = intentRegisterReceiver.getIntExtra("level", -1);
            int intExtra2 = intentRegisterReceiver.getIntExtra("status", -1);
            boolean z = intExtra2 == 2 || intExtra2 == 5;
            StringBuilder sb = new StringBuilder();
            sb.append(z ? "1" : OnekeyLoginConstants.CU_RESULT_SUCCESS);
            sb.append(":");
            sb.append(intExtra);
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String t() {
        StringBuilder sb = new StringBuilder();
        sb.append("00:");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("ro.hardware", "goldfish");
        linkedHashMap.put("ro.kernel.qemu", "1");
        linkedHashMap.put("ro.product.device", "generic");
        linkedHashMap.put("ro.product.model", "sdk");
        linkedHashMap.put("ro.product.brand", "generic");
        linkedHashMap.put("ro.product.name", "sdk");
        linkedHashMap.put("ro.build.fingerprint", "test-keys");
        linkedHashMap.put("ro.product.manufacturer", "unknow");
        for (String str : linkedHashMap.keySet()) {
            char c2 = '0';
            String str2 = (String) linkedHashMap.get(str);
            String strB = com.alipay.security.mobile.module.a.a.b(str, "");
            if (strB != null && strB.contains(str2)) {
                c2 = '1';
            }
            sb.append(c2);
        }
        return sb.toString();
    }

    private static String t(Context context) {
        if (a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return "";
        }
        String str = null;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 1) {
                    return "WIFI";
                }
                if (activeNetworkInfo.getType() == 0) {
                    int subtype = activeNetworkInfo.getSubtype();
                    if (subtype != 4 && subtype != 1 && subtype != 2 && subtype != 7 && subtype != 11) {
                        if (subtype != 3 && subtype != 5 && subtype != 6 && subtype != 8 && subtype != 9 && subtype != 10 && subtype != 12 && subtype != 14 && subtype != 15) {
                            if (subtype == 13) {
                                return "4G";
                            }
                            str = "UNKNOW";
                        }
                        return "3G";
                    }
                    return "2G";
                }
            }
        } catch (Throwable unused) {
        }
        return str;
    }

    private static String u() {
        try {
            ArrayList<NetworkInterface> list = Collections.list(NetworkInterface.getNetworkInterfaces());
            if (list == null) {
                return "02:00:00:00:00:00";
            }
            for (NetworkInterface networkInterface : list) {
                if (networkInterface != null && networkInterface.getName() != null && networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "02:00:00:00:00:00";
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b2 : hardwareAddress) {
                        sb.append(String.format("%02X:", Integer.valueOf(b2 & 255)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return "02:00:00:00:00:00";
        } catch (Throwable unused) {
            return "02:00:00:00:00:00";
        }
    }

    private static String v() throws Throwable {
        FileReader fileReader;
        BufferedReader bufferedReader;
        String line;
        BufferedReader bufferedReader2 = null;
        try {
            fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
            try {
                bufferedReader = new BufferedReader(fileReader, 8192);
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            fileReader = null;
        }
        try {
            line = bufferedReader.readLine();
        } catch (Throwable th) {
            bufferedReader2 = bufferedReader;
            th = th;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (Throwable unused3) {
                }
            }
            if (fileReader == null) {
                throw th;
            }
            try {
                fileReader.close();
                throw th;
            } catch (Throwable unused4) {
                throw th;
            }
        }
        if (com.alipay.security.mobile.module.a.a.a(line)) {
            try {
                bufferedReader.close();
            } catch (Throwable unused5) {
            }
            fileReader.close();
            return "";
        }
        String strTrim = line.trim();
        try {
            bufferedReader.close();
        } catch (Throwable unused6) {
        }
        try {
            fileReader.close();
        } catch (Throwable unused7) {
        }
        return strTrim;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x003a, code lost:
    
        r1 = r2[1].trim();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String w() throws java.lang.Throwable {
        /*
            java.lang.String r0 = "/proc/cpuinfo"
            java.lang.String r1 = ""
            r2 = 0
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L49 java.lang.Throwable -> L57
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L49 java.lang.Throwable -> L57
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L46 java.lang.Throwable -> L58
            r4 = 8192(0x2000, float:1.148E-41)
            r0.<init>(r3, r4)     // Catch: java.lang.Throwable -> L46 java.lang.Throwable -> L58
        L11:
            java.lang.String r2 = r0.readLine()     // Catch: java.lang.Throwable -> L42 java.lang.Throwable -> L44
            if (r2 == 0) goto L3b
            boolean r4 = com.alipay.security.mobile.module.a.a.a(r2)     // Catch: java.lang.Throwable -> L42 java.lang.Throwable -> L44
            if (r4 != 0) goto L11
            java.lang.String r4 = ":"
            java.lang.String[] r2 = r2.split(r4)     // Catch: java.lang.Throwable -> L42 java.lang.Throwable -> L44
            if (r2 == 0) goto L11
            int r4 = r2.length     // Catch: java.lang.Throwable -> L42 java.lang.Throwable -> L44
            r5 = 1
            if (r4 <= r5) goto L11
            r4 = 0
            r4 = r2[r4]     // Catch: java.lang.Throwable -> L42 java.lang.Throwable -> L44
            java.lang.String r6 = "BogoMIPS"
            boolean r4 = r4.contains(r6)     // Catch: java.lang.Throwable -> L42 java.lang.Throwable -> L44
            if (r4 == 0) goto L11
            r2 = r2[r5]     // Catch: java.lang.Throwable -> L42 java.lang.Throwable -> L44
            java.lang.String r2 = r2.trim()     // Catch: java.lang.Throwable -> L42 java.lang.Throwable -> L44
            r1 = r2
        L3b:
            r3.close()     // Catch: java.lang.Throwable -> L3e
        L3e:
            r0.close()     // Catch: java.lang.Throwable -> L62
            goto L62
        L42:
            r1 = move-exception
            goto L4c
        L44:
            r2 = r0
            goto L58
        L46:
            r1 = move-exception
            r0 = r2
            goto L4c
        L49:
            r1 = move-exception
            r0 = r2
            r3 = r0
        L4c:
            if (r3 == 0) goto L51
            r3.close()     // Catch: java.lang.Throwable -> L51
        L51:
            if (r0 == 0) goto L56
            r0.close()     // Catch: java.lang.Throwable -> L56
        L56:
            throw r1
        L57:
            r3 = r2
        L58:
            if (r3 == 0) goto L5d
            r3.close()     // Catch: java.lang.Throwable -> L5d
        L5d:
            if (r2 == 0) goto L62
            r2.close()     // Catch: java.lang.Throwable -> L62
        L62:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.b.b.w():java.lang.String");
    }

    private static String x() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress() && (inetAddressNextElement instanceof Inet4Address)) {
                        return inetAddressNextElement.getHostAddress().toString();
                    }
                }
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public final String e() {
        try {
            return String.valueOf(new File("/sys/devices/system/cpu/").listFiles(new c(this)).length);
        } catch (Throwable unused) {
            return "1";
        }
    }
}
