package com.sigmob.sdk.common.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.SystemClock;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import com.sigmob.devicehelper.DeviceHelper;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.utils.n;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.message.MsgConstant;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DeviceUtils {
    static final String[] a = {"/system/lib/libdroid4x.so", "/system/bin/mount.vboxsf", "/system/lib/vboxguest.ko", "/etc/mumu-configs", "/system/lib/vboxsf.ko", "/system/lib/vboxvideo.ko", "/data/.bluestacks.prop", "/system/bin/microvirt-vbox-sf", "/system/lib/tboxsf.ko", "/system/bin/androVM-vbox-sf", "/system/bin/microvirtd", "/system/bin/windroyed", "/system/lib/libdroid4x.so"};
    private static int b = 0;
    private static String c;
    private static String d;
    private static a e;
    private static boolean f;

    public static class NetBroadcastReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                DeviceUtils.r(context);
            }
        }
    }

    public enum a {
        UNKNOWN(0),
        ETHERNET(101),
        WIFI(100),
        MOBILE(1),
        MOBILE_2G(2),
        MOBILE_3G(3),
        MOBILE_4G(4),
        MOBILE_5G(5);

        private final int i;

        a(int i) {
            this.i = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static a b(Context context, int i) {
            if (i == 9) {
                return ETHERNET;
            }
            switch (i) {
                case 0:
                case 2:
                case 3:
                case 4:
                case 5:
                    return DeviceUtils.v(context);
                case 1:
                    return WIFI;
                default:
                    return UNKNOWN;
            }
        }

        public int a() {
            return this.i;
        }

        @Override // java.lang.Enum
        public String toString() {
            return Integer.toString(this.i);
        }
    }

    public static int A(Context context) {
        if (context == null) {
            return 0;
        }
        return c.c(context);
    }

    public static int B(Context context) {
        if (context == null) {
            return 0;
        }
        return c.a(p(context).heightPixels, context);
    }

    public static DisplayMetrics C(Context context) {
        try {
            return context.getResources().getDisplayMetrics();
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            return null;
        }
    }

    private static boolean D(Context context) {
        return context.checkCallingOrSelfPermission(MsgConstant.PERMISSION_INTERNET) == 0;
    }

    public static int a(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return 0;
        }
        long memoryClass = activityManager.getMemoryClass();
        try {
            if (a(context.getApplicationInfo().flags, ApplicationInfo.class.getDeclaredField("FLAG_LARGE_HEAP").getInt(null))) {
                memoryClass = ((Integer) new n.a(activityManager, "getLargeMemoryClass").a()).intValue();
            }
        } catch (Throwable unused) {
            SigmobLog.d("Unable to reflectively determine large heap size.");
        }
        return (int) Math.min(31457280L, (memoryClass / 8) * DownloadConstants.KB * DownloadConstants.KB);
    }

    public static long a() {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }

    public static String a(Context context, String str) throws PackageManager.NameNotFoundException, NoSuchAlgorithmException {
        Signature signature;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            if (packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length <= 0 || (signature = packageInfo.signatures[0]) == null) {
                return null;
            }
            byte[] byteArray = signature.toByteArray();
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            if (messageDigest == null) {
                return null;
            }
            byte[] bArrDigest = messageDigest.digest(byteArray);
            StringBuilder sb = new StringBuilder();
            for (byte b2 : bArrDigest) {
                sb.append(Integer.toHexString((b2 & UByte.MAX_VALUE) | ApkDownloadComplianceInterface.INSTALL_BITS).substring(1, 3).toUpperCase());
                sb.append(Constants.COLON_SEPARATOR);
            }
            return sb.substring(0, sb.length() - 1);
        } catch (Exception e2) {
            SigmobLog.e(e2.getMessage());
            return null;
        }
    }

    public static String a(String str) {
        try {
            Object objInvoke = Class.forName("android.os.SystemProperties").getMethod(MonitorConstants.CONNECT_TYPE_GET, String.class).invoke(null, str);
            if (objInvoke != null) {
                return (String) objInvoke;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean a(int i, int i2) {
        return (i & i2) != 0;
    }

    public static String b() {
        try {
            return Build.VERSION.SDK_INT >= 21 ? Build.SUPPORTED_ABIS[0] : Build.CPU_ABI;
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            return null;
        }
    }

    public static String b(Context context) {
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        if (Build.VERSION.SDK_INT >= 24) {
            for (StorageVolume storageVolume : storageManager.getStorageVolumes()) {
                if (storageVolume.isRemovable()) {
                    try {
                        return (String) storageVolume.getClass().getMethod("getPath", new Class[0]).invoke(storageVolume, new Object[0]);
                    } catch (Exception e2) {
                        SigmobLog.e(e2.getMessage());
                    }
                }
            }
            return null;
        }
        try {
            Method method = storageManager.getClass().getMethod("getVolumeList", new Class[0]);
            Class<?> cls = Class.forName("android.os.storage.StorageVolume");
            Method method2 = cls.getMethod("getPath", new Class[0]);
            Method method3 = cls.getMethod("isRemovable", new Class[0]);
            Object objInvoke = method.invoke(storageManager, new Object[0]);
            int length = Array.getLength(objInvoke);
            for (int i = 0; i < length; i++) {
                try {
                    Object obj = Array.get(objInvoke, i);
                    Object objInvoke2 = method3.invoke(obj, new Object[0]);
                    if ((objInvoke2 instanceof Boolean) && ((Boolean) objInvoke2).booleanValue()) {
                        Object objInvoke3 = method2.invoke(obj, new Object[0]);
                        if (objInvoke3 instanceof String) {
                            return (String) objInvoke3;
                        }
                        continue;
                    }
                } catch (Throwable unused) {
                }
            }
            return null;
        } catch (Throwable unused2) {
            return null;
        }
    }

    private static boolean b(String str) throws Throwable {
        Process processExec;
        Process process = null;
        try {
            processExec = Runtime.getRuntime().exec("ls -l " + str);
        } catch (Throwable unused) {
            processExec = null;
        }
        try {
            String line = new BufferedReader(new InputStreamReader(processExec.getInputStream())).readLine();
            if (line != null && line.length() >= 4) {
                char cCharAt = line.charAt(3);
                if (cCharAt == 's' || cCharAt == 'x') {
                    if (processExec != null) {
                        processExec.destroy();
                    }
                    return true;
                }
            }
            if (processExec == null) {
                return false;
            }
        } catch (Throwable unused2) {
            if (processExec == null) {
                return false;
            }
            processExec.destroy();
            return false;
        }
        processExec.destroy();
        return false;
    }

    public static WindowManager c(Context context) {
        return (WindowManager) context.getSystemService("window");
    }

    public static boolean c() {
        return (new File("/system/bin/su").exists() && b("/system/bin/su")) || (new File("/system/xbin/su").exists() && b("/system/xbin/su"));
    }

    @SuppressLint({"MissingPermission"})
    public static a d() {
        return e;
    }

    public static String d(Context context) {
        WindowManager windowManagerC = c(context);
        if (windowManagerC == null) {
            return com.sigmob.sdk.common.Constants.FAIL;
        }
        switch (windowManagerC.getDefaultDisplay().getRotation()) {
        }
        return com.sigmob.sdk.common.Constants.FAIL;
    }

    public static String e() {
        return Build.BRAND;
    }

    public static boolean e(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public static WifiManager f(Context context) {
        if (context == null || !DeviceHelper.isCanUseWifiState(context)) {
            return null;
        }
        return (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI);
    }

    public static String f() {
        return Build.SERIAL;
    }

    public static int g() {
        return Build.VERSION.SDK_INT;
    }

    public static int g(Context context) {
        return context.getResources().getConfiguration().orientation;
    }

    public static float h(Context context) {
        BatteryManager batteryManager;
        if (Build.VERSION.SDK_INT < 21 || (batteryManager = (BatteryManager) context.getSystemService("batterymanager")) == null) {
            return 0.0f;
        }
        return batteryManager.getIntProperty(4) / 100.0f;
    }

    public static String h() {
        return Build.MANUFACTURER;
    }

    public static String i() {
        return Build.MODEL;
    }

    public static boolean i(Context context) {
        BatteryManager batteryManager;
        return Build.VERSION.SDK_INT >= 21 && (batteryManager = (BatteryManager) context.getSystemService("batterymanager")) != null && batteryManager.getIntProperty(4) < 16;
    }

    public static int j(Context context) {
        BatteryManager batteryManager;
        if (Build.VERSION.SDK_INT < 21 || (batteryManager = (BatteryManager) context.getSystemService("batterymanager")) == null) {
            return 0;
        }
        switch (Build.VERSION.SDK_INT >= 26 ? batteryManager.getIntProperty(6) : 0) {
        }
        return 0;
    }

    public static String j() {
        return Build.VERSION.RELEASE;
    }

    public static long k(Context context) {
        try {
            if (Build.VERSION.SDK_INT < 16) {
                return 0L;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            if (activityManager == null) {
                return 0L;
            }
            activityManager.getMemoryInfo(memoryInfo);
            return memoryInfo.totalMem;
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            return 0L;
        }
    }

    public static boolean k() {
        if (b > 0) {
            return b > 3;
        }
        try {
            String strA = a("gsm.version.baseband");
            if (TextUtils.isEmpty(strA) || strA.contains("1.0.0.0")) {
                b++;
            }
            String strA2 = a("ro.build.flavor");
            if (TextUtils.isEmpty(strA2)) {
                b++;
            } else if (strA2.contains("vbox") || strA2.contains("sdk_gphone")) {
                b += 10;
            }
            String strA3 = a("ro.product.board");
            if (TextUtils.isEmpty(strA3)) {
                b++;
            } else if (strA3.contains("android") || strA3.contains("goldfish")) {
                b += 10;
            }
            String strA4 = a("ro.board.platform");
            if (TextUtils.isEmpty(strA4) || strA4.contains("android")) {
                b++;
            }
            String strA5 = a("ro.hardware");
            if (strA5 == null) {
                b++;
            } else if (strA5.toLowerCase().contains("ttvm") || strA5.toLowerCase().contains("nox")) {
                b += 10;
            }
            for (String str : a) {
                if (new File(str).exists()) {
                    SigmobLog.e("find emulator " + str);
                    b = b + 10;
                }
            }
        } catch (Throwable unused) {
        }
        return b > 3;
    }

    public static float l(Context context) {
        try {
            return context.getResources().getDisplayMetrics().densityDpi;
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            return 0.0f;
        }
    }

    public static Locale m(Context context) {
        try {
            return context.getResources().getConfiguration().locale;
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            return null;
        }
    }

    public static String n(Context context) {
        if (TextUtils.isEmpty(d)) {
            try {
                d = Settings.Secure.getString(context.getContentResolver(), "bluetooth_name");
            } catch (Throwable th) {
                SigmobLog.e(th.getMessage());
            }
        }
        return d;
    }

    public static String o(Context context) {
        if (Build.VERSION.SDK_INT < 17) {
            return n(context);
        }
        try {
            return Settings.Global.getString(context.getContentResolver(), com.umeng.commonsdk.proguard.o.I);
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            return null;
        }
    }

    public static DisplayMetrics p(Context context) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        WindowManager windowManagerC = c(context);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = windowManagerC.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealMetrics(displayMetrics);
        } else {
            try {
                Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke(defaultDisplay, displayMetrics);
            } catch (Exception e2) {
                SigmobLog.e(e2.getMessage());
            }
        }
        return displayMetrics;
    }

    public static ConnectivityManager q(Context context) {
        if (context != null) {
            return (ConnectivityManager) context.getSystemService("connectivity");
        }
        return null;
    }

    @SuppressLint({"MissingPermission"})
    public static void r(Context context) {
        a aVarB;
        try {
            if (D(context)) {
                if (Build.VERSION.SDK_INT >= 23) {
                    Network activeNetwork = q(context).getActiveNetwork();
                    NetworkInfo activeNetworkInfo = q(context).getActiveNetworkInfo();
                    if (activeNetworkInfo != null) {
                        f = activeNetworkInfo.isAvailable();
                    }
                    NetworkCapabilities networkCapabilities = q(context).getNetworkCapabilities(activeNetwork);
                    if (networkCapabilities == null) {
                        return;
                    } else {
                        aVarB = (networkCapabilities.hasCapability(12) && networkCapabilities.hasCapability(16) && networkCapabilities.hasTransport(1)) ? a.WIFI : (networkCapabilities.hasCapability(12) && networkCapabilities.hasCapability(16) && networkCapabilities.hasTransport(0)) ? v(context) : a.UNKNOWN;
                    }
                } else {
                    NetworkInfo activeNetworkInfo2 = q(context).getActiveNetworkInfo();
                    if (activeNetworkInfo2 != null) {
                        f = activeNetworkInfo2.isAvailable();
                    }
                    aVarB = a.b(context, activeNetworkInfo2 != null ? activeNetworkInfo2.getType() : -1);
                }
                e = aVarB;
            }
        } catch (Exception unused) {
        }
    }

    public static TelephonyManager s(Context context) {
        if (context == null || !DeviceHelper.isCanUsePhoneState(context)) {
            return null;
        }
        return (TelephonyManager) context.getSystemService("phone");
    }

    public static String t(Context context) {
        TelephonyManager telephonyManagerS = s(context);
        if (telephonyManagerS != null) {
            return (telephonyManagerS.getPhoneType() == 2 && telephonyManagerS.getSimState() == 5) ? telephonyManagerS.getSimOperator() : telephonyManagerS.getNetworkOperator();
        }
        return null;
    }

    public static String u(Context context) {
        return t(context);
    }

    @SuppressLint({"MissingPermission"})
    public static a v(Context context) {
        TelephonyManager telephonyManagerS = s(context);
        if (telephonyManagerS == null) {
            return a.UNKNOWN;
        }
        int networkType = telephonyManagerS.getNetworkType();
        if (networkType == 20) {
            return a.MOBILE_5G;
        }
        switch (networkType) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return a.MOBILE_2G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return a.MOBILE_3G;
            case 13:
                return a.MOBILE_4G;
            default:
                return a.MOBILE;
        }
    }

    public static void w(final Context context) {
        if (Build.VERSION.SDK_INT < 21) {
            context.registerReceiver(new NetBroadcastReceiver(), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            return;
        }
        NetworkRequest networkRequestBuild = new NetworkRequest.Builder().addTransportType(0).addTransportType(1).build();
        ConnectivityManager connectivityManagerQ = q(context);
        if (connectivityManagerQ == null) {
            return;
        }
        connectivityManagerQ.registerNetworkCallback(networkRequestBuild, new ConnectivityManager.NetworkCallback() { // from class: com.sigmob.sdk.common.utils.DeviceUtils.1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                DeviceUtils.r(context);
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(@NonNull Network network) {
                super.onLost(network);
                a unused = DeviceUtils.e = a.UNKNOWN;
                boolean unused2 = DeviceUtils.f = false;
            }
        });
    }

    public static String x(Context context) {
        if (TextUtils.isEmpty(c)) {
            try {
                c = Settings.Secure.getString(context.getContentResolver(), "android_id");
            } catch (Throwable th) {
                SigmobLog.e(th.getMessage());
            }
        }
        return c;
    }

    public static int y(Context context) {
        return c.b(context);
    }

    public static int z(Context context) {
        if (context == null) {
            return 0;
        }
        return c.a(p(context).widthPixels, context);
    }
}
