package com.meizu.cloud.pushsdk.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.base.j;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MzSystemUtils {
    private static final String PUSH_SERVICE_PROCESS_NAME = "mzservice_v1";
    private static final String TAG = "MzSystemUtils";
    private static String sBssId = null;
    private static boolean sExistAllergySystemUtils = true;
    private static String sSimOperator;
    private static List<String> sWifiList;

    public static boolean compareVersion(String str, String str2) {
        String[] strArrSplit = str.split("\\.");
        String[] strArrSplit2 = str2.split("\\.");
        int iMin = Math.min(strArrSplit.length, strArrSplit2.length);
        int length = 0;
        for (int i = 0; i < iMin; i++) {
            length = strArrSplit[i].length() - strArrSplit2[i].length();
            if (length != 0 || (length = strArrSplit[i].compareTo(strArrSplit2[i])) != 0) {
                break;
            }
        }
        if (length == 0) {
            length = strArrSplit.length - strArrSplit2.length;
        }
        return length >= 0;
    }

    public static boolean compatApi(int i) {
        return Build.VERSION.SDK_INT >= i;
    }

    public static String findReceiver(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                Intent intent = new Intent(str);
                intent.setPackage(str2);
                List<ResolveInfo> listQueryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
                if (listQueryBroadcastReceivers != null && listQueryBroadcastReceivers.size() > 0) {
                    return listQueryBroadcastReceivers.get(0).activityInfo.name;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static int getAppVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getAppVersionName(Context context, String str) {
        try {
            String str2 = context.getPackageManager().getPackageInfo(str, 0).versionName;
            return str2 != null ? str2.length() <= 0 ? "" : str2 : "";
        } catch (Exception e) {
            DebugLogger.e(TAG, "Exception message " + e.getMessage());
            return "";
        }
    }

    public static String getBssId(Context context) {
        if (sBssId != null) {
            return sBssId;
        }
        if (!sExistAllergySystemUtils) {
            return null;
        }
        try {
            sBssId = (String) Class.forName("com.meizu.cloud.pushsdk.util.AllergySystemUtils").getDeclaredMethod("getBssId", Context.class).invoke(null, context);
        } catch (Exception e) {
            sExistAllergySystemUtils = false;
            DebugLogger.e(TAG, "getBssId error " + e.getMessage());
        }
        return sBssId;
    }

    public static String getCurrentLanguage() {
        try {
            return Locale.getDefault().getLanguage();
        } catch (Exception e) {
            DebugLogger.e(TAG, "getCurrentLanguage error " + e.getMessage());
            return null;
        }
    }

    public static String getMzPushServicePackageName(Context context) {
        String packageName = context.getPackageName();
        try {
            String servicesByPackageName = getServicesByPackageName(context, PushConstants.PUSH_PACKAGE_NAME);
            if (!TextUtils.isEmpty(servicesByPackageName)) {
                if (servicesByPackageName.contains(PUSH_SERVICE_PROCESS_NAME)) {
                    return PushConstants.PUSH_PACKAGE_NAME;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DebugLogger.i(TAG, "start service package name " + packageName);
        return packageName;
    }

    public static String getNetWorkType(Context context) {
        String str;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
            if (activeNetworkInfo != null) {
                int type = activeNetworkInfo.getType();
                if (type == 7) {
                    return "BLUETOOTH";
                }
                if (type == 9) {
                    return "ETHERNET";
                }
                switch (type) {
                    case 0:
                        switch (activeNetworkInfo.getSubtype()) {
                            case 1:
                            case 2:
                            case 4:
                            case 7:
                            case 11:
                                str = "MOBILE_2G";
                                break;
                            case 3:
                            case 5:
                            case 6:
                            case 8:
                            case 9:
                            case 10:
                            case 12:
                            case 14:
                            case 15:
                                str = "MOBILE_3G";
                                break;
                            case 13:
                                str = "MOBILE_4G";
                                break;
                            default:
                                str = "MOBILE_XG";
                                break;
                        }
                        return "OTHER";
                    case 1:
                        return "WIFI";
                    default:
                        return "OTHER";
                }
            }
        } catch (Exception e) {
            DebugLogger.e(TAG, "Security exception checking connection: " + e.getMessage());
        }
        return "";
    }

    public static String getOperator(Context context) {
        if (sSimOperator != null) {
            return sSimOperator;
        }
        if (!sExistAllergySystemUtils) {
            return null;
        }
        try {
            sSimOperator = (String) Class.forName("com.meizu.cloud.pushsdk.util.AllergySystemUtils").getDeclaredMethod("getOperator", Context.class).invoke(null, context);
        } catch (Exception e) {
            sExistAllergySystemUtils = false;
            DebugLogger.e(TAG, "getOperator error " + e.getMessage());
        }
        return sSimOperator;
    }

    public static String getProcessName(Context context) {
        try {
            int iMyPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService(PushConstants.INTENT_ACTIVITY_NAME);
            if (activityManager == null) {
                return null;
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                DebugLogger.i(TAG, "processName " + runningAppProcessInfo.processName);
                if (runningAppProcessInfo.pid == iMyPid) {
                    return runningAppProcessInfo.processName;
                }
            }
            return "";
        } catch (Exception e) {
            DebugLogger.e(TAG, "getProcessName error " + e.getMessage());
            return null;
        }
    }

    private static String getServicesByPackageName(Context context, String str) {
        ServiceInfo[] serviceInfoArr;
        try {
            serviceInfoArr = context.getPackageManager().getPackageInfo(str, 4).services;
        } catch (Exception unused) {
            serviceInfoArr = null;
        }
        if (serviceInfoArr == null) {
            return null;
        }
        for (ServiceInfo serviceInfo : serviceInfoArr) {
            if (PushConstants.MZ_PUSH_SERVICE_NAME.equals(serviceInfo.name)) {
                return serviceInfo.processName;
            }
        }
        return null;
    }

    public static List<String> getWifiList(Context context) {
        if (sWifiList != null) {
            return sWifiList;
        }
        if (!sExistAllergySystemUtils) {
            return null;
        }
        try {
            sWifiList = (List) Class.forName("com.meizu.cloud.pushsdk.util.AllergySystemUtils").getDeclaredMethod("getWifiList", Context.class).invoke(null, context);
        } catch (Exception e) {
            sExistAllergySystemUtils = false;
            DebugLogger.e(TAG, "getWifiList error " + e.getMessage());
        }
        return sWifiList;
    }

    public static boolean isApplicationDebug(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isBrandMeizu(Context context) {
        boolean z = !TextUtils.isEmpty(j.a("ro.meizu.product.model")) || "meizu".equalsIgnoreCase(Build.BRAND) || "22c4185e".equalsIgnoreCase(Build.BRAND);
        if (!z) {
            com.meizu.cloud.pushsdk.a.a.b(context.getApplicationContext());
        }
        return z;
    }

    public static boolean isExistReceiver(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return false;
        }
        Intent intent = new Intent(str2);
        intent.setPackage(str);
        List<ResolveInfo> listQueryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        return (listQueryBroadcastReceivers == null || listQueryBroadcastReceivers.size() <= 0 || TextUtils.isEmpty(listQueryBroadcastReceivers.get(0).activityInfo.name)) ? false : true;
    }

    public static boolean isHuaWei() {
        DebugLogger.e(TAG, "huawei eui " + j.a("ro.build.version.emui"));
        return !TextUtils.isEmpty(r0);
    }

    public static boolean isIndiaLocal() {
        return "india".equals(j.a("ro.meizu.locale.region"));
    }

    public static boolean isInteractive(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return true;
        }
        try {
            return Build.VERSION.SDK_INT >= 20 ? powerManager.isInteractive() : powerManager.isScreenOn();
        } catch (Exception e) {
            DebugLogger.e(TAG, "isScreenOn error " + e.getMessage());
            return true;
        }
    }

    public static boolean isInternational() {
        if (com.meizu.cloud.pushsdk.base.a.a().a) {
            return com.meizu.cloud.pushsdk.base.a.a().b.booleanValue();
        }
        return false;
    }

    public static boolean isMeizu(Context context) {
        return isBrandMeizu(context);
    }

    public static boolean isMeizuAndFlyme() {
        if (com.meizu.cloud.pushsdk.base.a.b().a) {
            return !r0.b.booleanValue();
        }
        return false;
    }

    public static boolean isOverseas() {
        return isInternational() || isIndiaLocal();
    }

    public static boolean isPackageInstalled(Context context, String str) throws PackageManager.NameNotFoundException {
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isRunningProcess(Context context, String str) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(PushConstants.INTENT_ACTIVITY_NAME);
            boolean zContains = false;
            if (activityManager == null) {
                return false;
            }
            Iterator<ActivityManager.RunningAppProcessInfo> it = activityManager.getRunningAppProcesses().iterator();
            while (it.hasNext() && !(zContains = it.next().processName.contains(str))) {
            }
            DebugLogger.i(TAG, str + " is running " + zContains);
            return zContains;
        } catch (Exception unused) {
            DebugLogger.e(TAG, "can not get running process info so set running true");
            return true;
        }
    }

    public static boolean isXiaoMi() {
        return "Xiaomi".equals(Build.MODEL) || "Xiaomi".equals(Build.MANUFACTURER);
    }

    public static void sendMessageFromBroadcast(Context context, Intent intent, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            intent.setAction(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            intent.setPackage(str2);
        }
        String strFindReceiver = findReceiver(context, str, str2);
        if (!TextUtils.isEmpty(strFindReceiver)) {
            intent.setClassName(str2, strFindReceiver);
        }
        context.sendBroadcast(intent);
    }
}
