package com.umeng.commonsdk.internal.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.message.MsgConstant;
import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {

    /* renamed from: com.umeng.commonsdk.internal.utils.a$a, reason: collision with other inner class name */
    public static class C0140a {
        public String a;
        public String b;
    }

    public static class b {
        public int a;
        public String b;
        public String c;
        public int d;
        public int e;
        public int f;
        public int g;
        public String h;
        public int i;
        public int j;
        public int k;
        public long l;
    }

    public static String p(Context context) {
        return context == null ? null : null;
    }

    public static String q(Context context) {
        return null;
    }

    public static long a(Context context, String str) throws Throwable {
        if (context == null) {
            return 0L;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).firstInstallTime;
        } catch (PackageManager.NameNotFoundException e) {
            UMCrashManager.reportCrash(context, e);
            ULog.e("getAppFirstInstallTime" + e.getMessage());
            return 0L;
        }
    }

    public static long b(Context context, String str) throws Throwable {
        if (context == null) {
            return 0L;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            UMCrashManager.reportCrash(context, e);
            ULog.e("getAppLastUpdateTime:" + e.getMessage());
            return 0L;
        }
    }

    public static String c(Context context, String str) throws Throwable {
        try {
            return context.getPackageManager().getInstallerPackageName(str);
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
            ULog.e("getAppInstaller:" + e.getMessage());
            return null;
        }
    }

    public static int d(Context context, String str) throws Throwable {
        if (context == null) {
            return 0;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getPackageInfo(str, 0).applicationInfo;
            if (applicationInfo != null) {
                return applicationInfo.uid;
            }
            return 0;
        } catch (PackageManager.NameNotFoundException e) {
            UMCrashManager.reportCrash(context, e);
            ULog.e("getAppUid:" + e.getMessage());
            return 0;
        }
    }

    public static boolean a() {
        return h.a();
    }

    public static String b() {
        return new SimpleDateFormat().format(new Date());
    }

    public static float a(Context context) throws Throwable {
        if (context == null) {
            return 0.0f;
        }
        Configuration configuration = new Configuration();
        try {
            configuration.updateFrom(context.getResources().getConfiguration());
            return configuration.fontScale;
        } catch (Exception e) {
            ULog.e("getFontSize:" + e.getMessage());
            return 0.0f;
        }
    }

    public static List<ScanResult> b(Context context) {
        WifiManager wifiManager;
        List<ScanResult> scanResults = null;
        if (context == null || (wifiManager = (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)) == null) {
            return null;
        }
        return (DeviceConfig.checkPermission(context, MsgConstant.PERMISSION_ACCESS_WIFI_STATE) && (DeviceConfig.checkPermission(context, "android.permission.ACCESS_COARSE_LOCATION") || DeviceConfig.checkPermission(context, "android.permission.ACCESS_FINE_LOCATION")) && ((scanResults = wifiManager.getScanResults()) == null || scanResults.size() == 0)) ? scanResults : scanResults;
    }

    public static WifiInfo c(Context context) {
        WifiManager wifiManager;
        if (context == null || !DeviceConfig.checkPermission(context, MsgConstant.PERMISSION_ACCESS_WIFI_STATE) || (wifiManager = (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)) == null) {
            return null;
        }
        return wifiManager.getConnectionInfo();
    }

    public static void d(Context context) throws Throwable {
        WifiInfo wifiInfoC;
        if (context == null || (wifiInfoC = c(context)) == null) {
            return;
        }
        b bVar = new b();
        bVar.a = wifiInfoC.describeContents();
        bVar.b = wifiInfoC.getBSSID();
        bVar.c = wifiInfoC.getSSID();
        if (Build.VERSION.SDK_INT >= 21) {
            bVar.d = wifiInfoC.getFrequency();
        } else {
            bVar.d = -1;
        }
        boolean z = false;
        if (wifiInfoC.getHiddenSSID()) {
            bVar.e = 1;
        } else {
            bVar.e = 0;
        }
        bVar.f = wifiInfoC.getIpAddress();
        bVar.g = wifiInfoC.getLinkSpeed();
        bVar.h = DeviceConfig.getMac(context);
        bVar.i = wifiInfoC.getNetworkId();
        bVar.j = wifiInfoC.getRssi();
        bVar.k = g(context);
        bVar.l = System.currentTimeMillis();
        if (wifiInfoC != null) {
            try {
                JSONArray jSONArrayA = f.a(context);
                if (jSONArrayA != null && jSONArrayA.length() > 0) {
                    int i = 0;
                    while (true) {
                        if (i < jSONArrayA.length()) {
                            String strOptString = jSONArrayA.optJSONObject(i).optString("ssid", null);
                            if (strOptString != null && strOptString.equals(bVar.c)) {
                                z = true;
                                break;
                            }
                            i++;
                        } else {
                            break;
                        }
                    }
                }
                if (z) {
                    return;
                }
                f.a(context, bVar);
            } catch (Exception e) {
                ULog.e("wifiChange:" + e.getMessage());
            }
        }
    }

    public static JSONArray e(Context context) {
        if (context == null) {
            return null;
        }
        return f.a(context);
    }

    public static void f(Context context) {
        if (context == null) {
            return;
        }
        f.b(context);
    }

    public static int g(Context context) {
        WifiManager wifiManager;
        if (context == null || !DeviceConfig.checkPermission(context, MsgConstant.PERMISSION_ACCESS_WIFI_STATE) || (wifiManager = (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)) == null) {
            return -1;
        }
        return wifiManager.getWifiState();
    }

    public static int h(Context context) {
        if (context == null) {
            return 0;
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    public static int i(Context context) {
        if (context == null) {
            return 0;
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
    }

    public static DisplayMetrics j(Context context) {
        if (context == null) {
            return null;
        }
        return context.getResources().getDisplayMetrics();
    }

    private static boolean j() {
        String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
    }

    public static long c() {
        if (!j() || Build.VERSION.SDK_INT < 9) {
            return 0L;
        }
        return Environment.getExternalStorageDirectory().getFreeSpace();
    }

    public static long d() {
        if (!j() || Build.VERSION.SDK_INT < 9) {
            return 0L;
        }
        return Environment.getExternalStorageDirectory().getTotalSpace();
    }

    public static String e() {
        return g.a("df");
    }

    public static String k(Context context) {
        TelephonyManager telephonyManager;
        if (context == null || !DeviceConfig.checkPermission(context, MsgConstant.PERMISSION_READ_PHONE_STATE) || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
            return null;
        }
        return telephonyManager.getSubscriberId();
    }

    public static String l(Context context) {
        TelephonyManager telephonyManager;
        if (context == null || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null || !DeviceConfig.checkPermission(context, MsgConstant.PERMISSION_READ_PHONE_STATE) || telephonyManager == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 26) {
            return telephonyManager.getDeviceId();
        }
        try {
            String strR = r(context);
            try {
                if (TextUtils.isEmpty(strR)) {
                    return telephonyManager.getDeviceId();
                }
            } catch (Throwable unused) {
            }
            return strR;
        } catch (Throwable unused2) {
            return null;
        }
    }

    private static String r(Context context) throws Throwable {
        if (context == null) {
            return null;
        }
        try {
            Object objInvoke = Class.forName("android.telephony.TelephonyManager").getMethod("getMeid", new Class[0]).invoke(null, new Object[0]);
            if (objInvoke == null || !(objInvoke instanceof String)) {
                return null;
            }
            return (String) objInvoke;
        } catch (Exception e) {
            ULog.e("meid:" + e.getMessage());
            return null;
        }
    }

    public static List<InputMethodInfo> m(Context context) {
        InputMethodManager inputMethodManager;
        if (context == null || (inputMethodManager = (InputMethodManager) context.getSystemService("input_method")) == null) {
            return null;
        }
        return inputMethodManager.getInputMethodList();
    }

    public static List<C0140a> n(Context context) throws Throwable {
        String[] list;
        if (context == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            File file = new File(Environment.getExternalStorageDirectory() + "/Android/data/");
            if (file.isDirectory() && (list = file.list()) != null && list.length > 0) {
                for (String str : list) {
                    if (str != null && !str.startsWith(".")) {
                        C0140a c0140a = new C0140a();
                        c0140a.a = str;
                        c0140a.b = e(context, str);
                        arrayList.add(c0140a);
                    }
                }
            }
        } catch (Exception e) {
            ULog.e("getAppList:" + e.getMessage());
        }
        return arrayList;
    }

    private static String e(Context context, String str) throws Throwable {
        if (context == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            if (applicationInfo != null) {
                return (String) applicationInfo.loadLabel(context.getPackageManager());
            }
            return null;
        } catch (Exception e) {
            ULog.e("getLabel:" + e.getMessage());
            return null;
        }
    }

    public static ActivityManager.MemoryInfo o(Context context) {
        ActivityManager activityManager;
        if (context == null || (activityManager = (ActivityManager) context.getSystemService("activity")) == null) {
            return null;
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo;
    }

    public static long f() {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }

    public static String g() throws Throwable {
        String str = null;
        try {
            Method declaredMethod = Build.class.getDeclaredMethod("getString", String.class);
            declaredMethod.setAccessible(true);
            String string = declaredMethod.invoke(null, "net.hostname").toString();
            if (string != null) {
                try {
                    if (!string.equalsIgnoreCase("")) {
                        return HelperUtils.getUmengMD5(string);
                    }
                } catch (Exception e) {
                    e = e;
                    str = string;
                    ULog.e("getHostName:" + e.getMessage());
                    return str;
                }
            }
            return string;
        } catch (Exception e2) {
            e = e2;
        }
    }

    public static long h() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return statFs.getBlockSize() * statFs.getBlockCount();
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static long i() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return statFs.getBlockSize() * statFs.getAvailableBlocks();
        } catch (Exception unused) {
            return 0L;
        }
    }
}
