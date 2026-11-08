package com.umeng.message.common;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.ss.android.download.api.constant.BaseConstants;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.ta.utdid2.device.UTDevice;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.proguard.h;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UmengMessageDeviceConfig {
    public static final int DEFAULT_TIMEZONE = 8;
    protected static final String a = "Unknown";
    private static final String b = "UmengMessageDeviceConfig";
    private static final String c = "2G/3G";
    private static final String d = "Wi-Fi";
    private static boolean e;

    public static String getDummyId(Context context) {
        return "02:00:00:00:00:00";
    }

    public static boolean isCommonConfig(String str) {
        try {
            return FieldManager.allow(str);
        } catch (Throwable unused) {
            return true;
        }
    }

    public static boolean isCommonConfigNew(String str) {
        Method method;
        try {
            Class<?> cls = Class.forName("com.umeng.commonsdk.config.FieldManager");
            if (cls != null && (method = cls.getMethod("allow", String.class)) != null) {
                return ((Boolean) method.invoke(null, str)).booleanValue();
            }
        } catch (Throwable unused) {
        }
        return true;
    }

    public static String getAppVersionCode(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException unused) {
            return a;
        }
    }

    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return a;
        }
    }

    public static boolean checkPermission(Context context, String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getCPU() throws java.io.IOException {
        /*
            java.lang.String r0 = "push_cpu"
            boolean r0 = isCommonConfigNew(r0)
            r1 = 0
            if (r0 == 0) goto L68
            r0 = 2
            r2 = 1
            r3 = 0
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.io.FileNotFoundException -> L43
            java.lang.String r5 = "/proc/cpuinfo"
            r4.<init>(r5)     // Catch: java.io.FileNotFoundException -> L43
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.io.IOException -> L2e java.io.FileNotFoundException -> L43
            r6 = 1024(0x400, float:1.435E-42)
            r5.<init>(r4, r6)     // Catch: java.io.IOException -> L2e java.io.FileNotFoundException -> L43
            java.lang.String r6 = r5.readLine()     // Catch: java.io.IOException -> L2e java.io.FileNotFoundException -> L43
            r5.close()     // Catch: java.io.FileNotFoundException -> L26 java.io.IOException -> L2a
            r4.close()     // Catch: java.io.FileNotFoundException -> L26 java.io.IOException -> L2a
            r1 = r6
            goto L57
        L26:
            r1 = move-exception
            r4 = r1
            r1 = r6
            goto L44
        L2a:
            r1 = move-exception
            r4 = r1
            r1 = r6
            goto L2f
        L2e:
            r4 = move-exception
        L2f:
            com.umeng.commonsdk.debug.UMLog r5 = com.umeng.commonsdk.UMConfigure.umDebugLog     // Catch: java.io.FileNotFoundException -> L43
            java.lang.String r5 = com.umeng.message.common.UmengMessageDeviceConfig.b     // Catch: java.io.FileNotFoundException -> L43
            java.lang.String[] r6 = new java.lang.String[r0]     // Catch: java.io.FileNotFoundException -> L43
            java.lang.String r7 = "Could not read from file /proc/cpuinfo"
            r6[r3] = r7     // Catch: java.io.FileNotFoundException -> L43
            java.lang.String r4 = r4.getMessage()     // Catch: java.io.FileNotFoundException -> L43
            r6[r2] = r4     // Catch: java.io.FileNotFoundException -> L43
            com.umeng.commonsdk.debug.UMLog.mutlInfo(r5, r3, r6)     // Catch: java.io.FileNotFoundException -> L43
            goto L57
        L43:
            r4 = move-exception
        L44:
            com.umeng.commonsdk.debug.UMLog r5 = com.umeng.commonsdk.UMConfigure.umDebugLog
            java.lang.String r5 = com.umeng.message.common.UmengMessageDeviceConfig.b
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.String r6 = "Could not open file /proc/cpuinfo"
            r0[r3] = r6
            java.lang.String r4 = r4.getMessage()
            r0[r2] = r4
            com.umeng.commonsdk.debug.UMLog.mutlInfo(r5, r3, r0)
        L57:
            if (r1 == 0) goto L68
            r0 = 58
            int r0 = r1.indexOf(r0)
            int r0 = r0 + r2
            java.lang.String r0 = r1.substring(r0)
            java.lang.String r1 = r0.trim()
        L68:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.common.UmengMessageDeviceConfig.getCPU():java.lang.String");
    }

    public static String getDeviceId(Context context) {
        String serial_number = "";
        if (isCommonConfigNew(a.b)) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(b, 1, "No IMEI.");
            }
            try {
                if (checkPermission(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
                    serial_number = telephonyManager.getDeviceId();
                }
            } catch (Exception e2) {
                UMLog uMLog2 = UMConfigure.umDebugLog;
                UMLog.mutlInfo(b, 1, "No IMEI.", e2.getMessage());
            }
        }
        if (TextUtils.isEmpty(serial_number) && TextUtils.isEmpty(serial_number)) {
            UMLog uMLog3 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(b, 1, "Failed to take imei as IMEI. Try to use Secure.ANDROID_ID instead.");
            if (isCommonConfigNew(a.d)) {
                serial_number = Settings.Secure.getString(context.getContentResolver(), "android_id");
            }
            UMLog uMLog4 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(b, 2, "getDeviceId: Secure.ANDROID_ID: " + serial_number);
            if (TextUtils.isEmpty(serial_number)) {
                UMLog uMLog5 = UMConfigure.umDebugLog;
                UMLog.mutlInfo(b, 1, "Failed to take Secure.ANDROID_ID as IMEI. Try to use Serial_number instead.");
                if (isCommonConfigNew(a.e)) {
                    serial_number = getSerial_number();
                }
                UMLog uMLog6 = UMConfigure.umDebugLog;
                UMLog.mutlInfo(b, 2, "getDeviceId: Serial_number: " + serial_number);
                return serial_number;
            }
        }
        return serial_number;
    }

    public static String getDIN(Context context) {
        if (!isCommonConfigNew(a.b)) {
            return "";
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(b, 1, "No IMEI.");
        }
        try {
            if (!checkPermission(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
                return "";
            }
            String deviceId = telephonyManager.getDeviceId();
            return !TextUtils.isEmpty(deviceId) ? deviceId : "";
        } catch (Exception e2) {
            UMLog uMLog2 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(b, 1, "No IMEI.", e2.getMessage());
            return "";
        }
    }

    public static String getDINAes(Context context) {
        String din;
        String strA;
        try {
            din = getDIN(context);
        } catch (Exception e2) {
            e = e2;
            din = "";
        }
        try {
            String messageAppkey = PushAgent.getInstance(context).getMessageAppkey();
            if (messageAppkey != null && 24 == messageAppkey.length()) {
                strA = com.umeng.message.proguard.c.a(din, "utf-8", messageAppkey.substring(0, 16));
            } else {
                strA = com.umeng.message.proguard.c.a(din, "utf-8");
            }
            return strA;
        } catch (Exception e3) {
            e = e3;
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(b, 1, "getDINAes:" + e.getMessage());
            return din;
        }
    }

    public static String getAndroidId(Context context) {
        if (!isCommonConfigNew(a.f)) {
            return null;
        }
        String string = Settings.System.getString(context.getContentResolver(), "android_id");
        return string == null ? "" : string;
    }

    public static String getSerial_number() {
        String str;
        if (!isCommonConfigNew(a.e)) {
            return "";
        }
        if (Build.VERSION.SDK_INT <= 25) {
            str = Build.SERIAL;
        } else {
            try {
                Class<?> cls = Class.forName("android.os.Build");
                str = (String) cls.getMethod("getSerial", new Class[0]).invoke(cls, new Object[0]);
            } catch (Throwable unused) {
                str = "";
            }
        }
        return str == null ? "" : str;
    }

    public static String getDeviceIdMD5(Context context) {
        return h.a(getDeviceId(context));
    }

    public static String[] getNetworkAccessMode(Context context) {
        String[] strArr = {a, a};
        if (isCommonConfigNew(a.g)) {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager == null) {
                    return strArr;
                }
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
                if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    strArr[0] = "Wi-Fi";
                    return strArr;
                }
                NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
                if (networkInfo2 != null && networkInfo2.getState() == NetworkInfo.State.CONNECTED) {
                    strArr[0] = "2G/3G";
                    strArr[1] = networkInfo2.getSubtypeName();
                    return strArr;
                }
            } catch (Exception unused) {
            }
        }
        return strArr;
    }

    public static boolean isOnline(Context context) {
        try {
            NetworkInfo activeNetworkInfo = isCommonConfigNew(a.h) ? ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo() : null;
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnectedOrConnecting();
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public static int getTimeZone(Context context) {
        if (!isCommonConfigNew(a.i)) {
            return 8;
        }
        try {
            Calendar calendar = Calendar.getInstance(a(context));
            if (calendar != null) {
                return calendar.getTimeZone().getRawOffset() / BaseConstants.Time.HOUR;
            }
            return 8;
        } catch (Exception e2) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(b, 2, "error in getTimeZone", e2.getMessage());
            return 8;
        }
    }

    public static String[] getLocaleInfo(Context context) {
        String[] strArr = new String[2];
        if (isCommonConfigNew(a.j)) {
            try {
                Locale localeA = a(context);
                if (localeA != null) {
                    strArr[0] = localeA.getCountry();
                    strArr[1] = localeA.getLanguage();
                }
                if (TextUtils.isEmpty(strArr[0])) {
                    strArr[0] = a;
                }
                if (TextUtils.isEmpty(strArr[1])) {
                    strArr[1] = a;
                }
                return strArr;
            } catch (Exception e2) {
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(b, 0, "error in getLocaleInfo", e2.getMessage());
            }
        }
        return strArr;
    }

    private static Locale a(Context context) {
        Locale locale;
        try {
            Configuration configuration = new Configuration();
            Settings.System.getConfiguration(context.getContentResolver(), configuration);
            locale = configuration.locale;
        } catch (Exception unused) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(b, 0, "fail to read user config locale");
            locale = null;
        }
        return locale == null ? Locale.getDefault() : locale;
    }

    public static String getMetaData(Context context, String str) throws PackageManager.NameNotFoundException {
        String string;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && (string = applicationInfo.metaData.getString(str)) != null) {
                return string.trim();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(b, 0, String.format("Could not read meta-data %s from AndroidManifest.xml.", str));
        return null;
    }

    public static String getUmid(Context context) {
        String strB = b(context);
        return strB == null ? "" : strB;
    }

    private static String b(Context context) {
        return UMUtils.getUMId(context);
    }

    public static String getResolution(Context context) {
        int iA;
        int iA2;
        if (!isCommonConfigNew(a.k)) {
            return a;
        }
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            if ((context.getApplicationInfo().flags & 8192) == 0) {
                iA = a(displayMetrics, "noncompatWidthPixels");
                iA2 = a(displayMetrics, "noncompatHeightPixels");
            } else {
                iA = -1;
                iA2 = -1;
            }
            if (iA == -1 || iA2 == -1) {
                iA = displayMetrics.widthPixels;
                iA2 = displayMetrics.heightPixels;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(iA);
            stringBuffer.append("*");
            stringBuffer.append(iA2);
            return stringBuffer.toString();
        } catch (Exception e2) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(b, 0, "read resolution fail", e2.getMessage());
            return a;
        }
    }

    private static int a(Object obj, String str) throws NoSuchFieldException {
        try {
            Field declaredField = DisplayMetrics.class.getDeclaredField(str);
            if (declaredField == null) {
                return -1;
            }
            declaredField.setAccessible(true);
            return declaredField.getInt(obj);
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static String getOperator(Context context) {
        if (!isCommonConfigNew(a.l)) {
            return a;
        }
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
        } catch (Exception e2) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(b, 2, "read carrier fail", e2.getMessage());
            return a;
        }
    }

    public static String getChannel(Context context) throws PackageManager.NameNotFoundException {
        Object obj;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null || (obj = applicationInfo.metaData.get("UMENG_CHANNEL")) == null) {
                return a;
            }
            String string = obj.toString();
            if (string != null) {
                return string;
            }
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(b, 2, "在AndroidManifest.xml中读取不到UMENG_CHANNEL meta-data");
            return a;
        } catch (Exception e2) {
            UMLog uMLog2 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(b, 2, "在AndroidManifest.xml中读取不到UMENG_CHANNEL meta-data");
            e2.printStackTrace();
            return a;
        }
    }

    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    public static String getApplicationLable(Context context) {
        return context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
    }

    public static String getUtdid(Context context) {
        if (!isCommonConfigNew(a.m)) {
            return "";
        }
        try {
            return UTDevice.getUtdid(context);
        } catch (Throwable th) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(b, 0, "获取utdid失败. " + th.getMessage());
            return "";
        }
    }

    public static boolean isServiceWork(Context context, String str, String str2) throws SecurityException {
        if (!isCommonConfigNew(a.n)) {
            return false;
        }
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
        if (runningServices.size() <= 0) {
            return false;
        }
        for (int i = 0; i < runningServices.size(); i++) {
            String string = runningServices.get(i).service.getClassName().toString();
            String string2 = runningServices.get(i).service.getPackageName().toString();
            if (string.equals(str) && string2.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public static String getServiceName(Context context, String str, String str2) {
        if (!isCommonConfigNew(a.o)) {
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction(str);
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        ArrayList arrayList = new ArrayList();
        if (listQueryIntentServices != null && listQueryIntentServices.size() > 0) {
            for (ResolveInfo resolveInfo : listQueryIntentServices) {
                if (resolveInfo.serviceInfo.packageName.equals(str2)) {
                    arrayList.add(resolveInfo);
                }
            }
        }
        if (arrayList.size() > 0) {
            return ((ResolveInfo) arrayList.get(0)).serviceInfo.name;
        }
        return null;
    }

    public static boolean isIntentExist(Context context, String str, String str2) {
        if (!isCommonConfigNew(a.p)) {
            return false;
        }
        List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(str)), 0);
        if (listQueryIntentActivities.isEmpty()) {
            return false;
        }
        for (int i = 0; i < listQueryIntentActivities.size(); i++) {
            if (listQueryIntentActivities.get(i).activityInfo.packageName.equalsIgnoreCase(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean getDataData(String str) {
        if (!isCommonConfigNew(a.q)) {
            return false;
        }
        boolean zExists = new File("/data/app/" + str + "-1.apk").exists();
        if (!zExists) {
            zExists = new File("/data/app/" + str + "-2.apk").exists();
        }
        if (!zExists) {
            zExists = new File("/data/app/" + str + Constants.APK_SUFFIX).exists();
        }
        if (!zExists) {
            zExists = new File("/data/app/" + str + "-1").exists();
        }
        if (zExists) {
            return zExists;
        }
        return new File("/data/app/" + str + "-2").exists();
    }

    public static boolean isMiui8() {
        try {
            String strA = com.umeng.message.util.a.g().a("ro.miui.ui.version.name");
            if (strA != null) {
                return strA.contains("8");
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String isNotificationEnabled(Context context) throws ClassNotFoundException {
        if (isCommonConfigNew(a.r) && Build.VERSION.SDK_INT >= 19) {
            try {
                AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
                ApplicationInfo applicationInfo = context.getApplicationInfo();
                String packageName = context.getApplicationContext().getPackageName();
                int i = applicationInfo.uid;
                Class<?> cls = Class.forName(AppOpsManager.class.getName());
                boolean z = true;
                if (((Integer) cls.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class).invoke(appOpsManager, Integer.valueOf(((Integer) cls.getDeclaredField("OP_POST_NOTIFICATION").get(appOpsManager)).intValue()), Integer.valueOf(i), packageName)).intValue() != 0) {
                    z = false;
                }
                return String.valueOf(z);
            } catch (Exception unused) {
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(b, 0, "通知开关是否打开异常");
            }
        }
        return UtilityImpl.NET_TYPE_UNKNOWN;
    }
}
