package com.umeng.commonsdk.statistics.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.sigmob.sdk.common.Constants;
import com.ss.android.download.api.constant.BaseConstants;
import com.ss.android.socialbase.appdownloader.util.RomUtils;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.proguard.o;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.idtracking.i;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.message.MsgConstant;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DeviceConfig {
    public static final int DEFAULT_TIMEZONE = 8;
    private static final String KEY_EMUI_VERSION_CODE = "ro.build.hw_emui_api_level";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    protected static final String LOG_TAG = "com.umeng.commonsdk.statistics.common.DeviceConfig";
    public static final String MOBILE_NETWORK = "2G/3G";
    public static final String UNKNOW = "";
    public static final String WIFI = "Wi-Fi";

    public static String getImei(Context context) throws Throwable {
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null || !checkPermission(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
                return null;
            }
            return telephonyManager.getDeviceId();
        } catch (Exception e) {
            if (!AnalyticsConstants.UM_DEBUG) {
                return null;
            }
            MLog.w("No IMEI.", e);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0057 A[PHI: r0
  0x0057: PHI (r0v2 java.lang.String) = 
  (r0v1 java.lang.String)
  (r0v1 java.lang.String)
  (r0v0 java.lang.String)
  (r0v0 java.lang.String)
  (r0v0 java.lang.String)
  (r0v0 java.lang.String)
  (r0v4 java.lang.String)
 binds: [B:22:0x0050, B:23:0x0052, B:4:0x0007, B:5:0x0009, B:7:0x0013, B:9:0x001b, B:16:0x0041] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0057 -> B:29:0x0058). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getImeiNew(android.content.Context r5) throws java.lang.Throwable {
        /*
            r0 = 0
            java.lang.String r1 = "header_tracking_imei"
            boolean r1 = com.umeng.commonsdk.config.FieldManager.allow(r1)     // Catch: java.lang.Exception -> L4d
            if (r1 == 0) goto L57
            if (r5 == 0) goto L57
            java.lang.String r1 = "phone"
            java.lang.Object r1 = r5.getSystemService(r1)     // Catch: java.lang.Exception -> L4d
            android.telephony.TelephonyManager r1 = (android.telephony.TelephonyManager) r1     // Catch: java.lang.Exception -> L4d
            if (r1 == 0) goto L57
            java.lang.String r2 = "android.permission.READ_PHONE_STATE"
            boolean r5 = checkPermission(r5, r2)     // Catch: java.lang.Exception -> L4d
            if (r5 == 0) goto L57
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L4d
            r2 = 26
            if (r5 < r2) goto L48
            java.lang.Class r5 = r1.getClass()     // Catch: java.lang.Exception -> L3d
            java.lang.String r2 = "getImei"
            r3 = 0
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch: java.lang.Exception -> L3d
            java.lang.reflect.Method r5 = r5.getMethod(r2, r4)     // Catch: java.lang.Exception -> L3d
            r2 = 1
            r5.setAccessible(r2)     // Catch: java.lang.Exception -> L3d
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> L3d
            java.lang.Object r5 = r5.invoke(r1, r2)     // Catch: java.lang.Exception -> L3d
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Exception -> L3d
            r0 = r5
        L3d:
            boolean r5 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L4d
            if (r5 == 0) goto L57
            java.lang.String r5 = r1.getDeviceId()     // Catch: java.lang.Exception -> L4d
            goto L58
        L48:
            java.lang.String r5 = r1.getDeviceId()     // Catch: java.lang.Exception -> L4d
            goto L58
        L4d:
            r5 = move-exception
            boolean r1 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r1 == 0) goto L57
            java.lang.String r1 = "No IMEI."
            com.umeng.commonsdk.statistics.common.MLog.w(r1, r5)
        L57:
            r5 = r0
        L58:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.common.DeviceConfig.getImeiNew(android.content.Context):java.lang.String");
    }

    public static String getAndroidId(Context context) throws Throwable {
        if (FieldManager.allow(com.umeng.commonsdk.utils.b.t) && context != null) {
            try {
                return Settings.Secure.getString(context.getContentResolver(), "android_id");
            } catch (Exception unused) {
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.w("can't read android id");
                }
            }
        }
        return null;
    }

    public static String getSerial() {
        if (!FieldManager.allow(com.umeng.commonsdk.utils.b.y) || Build.VERSION.SDK_INT < 9) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                Class<?> cls = Class.forName("android.os.Build");
                return (String) cls.getMethod("getSerial", new Class[0]).invoke(cls, new Object[0]);
            } catch (Throwable unused) {
                return null;
            }
        }
        return Build.SERIAL;
    }

    public static String getAppVersionCode(Context context) {
        return UMUtils.getAppVersionCode(context);
    }

    public static String getAppVersionName(Context context) {
        return UMUtils.getAppVersionName(context);
    }

    public static boolean checkPermission(Context context, String str) {
        if (context == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                if (((Integer) Class.forName("android.content.Context").getMethod("checkSelfPermission", String.class).invoke(context, str)).intValue() != 0) {
                    return false;
                }
            } catch (Throwable unused) {
                return false;
            }
        } else if (context.getPackageManager().checkPermission(str, context.getPackageName()) != 0) {
            return false;
        }
        return true;
    }

    public static String[] getGPU(GL10 gl10) throws Throwable {
        try {
            return new String[]{gl10.glGetString(7936), gl10.glGetString(7937)};
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.e(LOG_TAG, "Could not read gpu infor:", th);
            }
            return new String[0];
        }
    }

    private static String getMacByJavaAPI() {
        try {
            if (FieldManager.allow(com.umeng.commonsdk.utils.b.h)) {
                Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                    if ("wlan0".equals(networkInterfaceNextElement.getName()) || "eth0".equals(networkInterfaceNextElement.getName())) {
                        byte[] hardwareAddress = networkInterfaceNextElement.getHardwareAddress();
                        if (hardwareAddress != null && hardwareAddress.length != 0) {
                            StringBuilder sb = new StringBuilder();
                            for (byte b : hardwareAddress) {
                                sb.append(String.format("%02X:", Byte.valueOf(b)));
                            }
                            if (sb.length() > 0) {
                                sb.deleteCharAt(sb.length() - 1);
                            }
                            return sb.toString().toLowerCase(Locale.getDefault());
                        }
                        return null;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    private static String getMacShell() {
        String strReaMac;
        try {
            if (!FieldManager.allow(com.umeng.commonsdk.utils.b.h)) {
                return null;
            }
            for (String str : new String[]{"/sys/class/net/wlan0/address", "/sys/class/net/eth0/address", "/sys/devices/virtual/net/wlan0/address"}) {
                try {
                    strReaMac = reaMac(str);
                } catch (Throwable th) {
                    if (AnalyticsConstants.UM_DEBUG) {
                        MLog.e(LOG_TAG, "open file  Failed", th);
                    }
                }
                if (strReaMac != null) {
                    return strReaMac;
                }
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String reaMac(String str) {
        BufferedReader bufferedReader;
        try {
            FileReader fileReader = new FileReader(str);
            try {
                bufferedReader = new BufferedReader(fileReader, com.ss.union.game.sdk.feedback.picture.select.d.d);
                try {
                    String line = bufferedReader.readLine();
                    try {
                        fileReader.close();
                    } catch (Throwable unused) {
                    }
                    try {
                        bufferedReader.close();
                    } catch (Throwable unused2) {
                    }
                    return line;
                } catch (Throwable th) {
                    th = th;
                    try {
                        fileReader.close();
                    } catch (Throwable unused3) {
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                            throw th;
                        } catch (Throwable unused4) {
                            throw th;
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
        } catch (Throwable unused5) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getCPU() throws java.lang.Throwable {
        /*
            java.lang.String r0 = "header_cpu"
            boolean r0 = com.umeng.commonsdk.config.FieldManager.allow(r0)
            if (r0 == 0) goto L4f
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch: java.io.FileNotFoundException -> L34
            java.lang.String r2 = "/proc/cpuinfo"
            r1.<init>(r2)     // Catch: java.io.FileNotFoundException -> L34
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L2b java.io.FileNotFoundException -> L34
            r3 = 1024(0x400, float:1.435E-42)
            r2.<init>(r1, r3)     // Catch: java.lang.Throwable -> L2b java.io.FileNotFoundException -> L34
            java.lang.String r3 = r2.readLine()     // Catch: java.lang.Throwable -> L2b java.io.FileNotFoundException -> L34
            r2.close()     // Catch: java.io.FileNotFoundException -> L23 java.lang.Throwable -> L27
            r1.close()     // Catch: java.io.FileNotFoundException -> L23 java.lang.Throwable -> L27
            r0 = r3
            goto L3c
        L23:
            r0 = move-exception
            r1 = r0
            r0 = r3
            goto L35
        L27:
            r0 = move-exception
            r1 = r0
            r0 = r3
            goto L2c
        L2b:
            r1 = move-exception
        L2c:
            java.lang.String r2 = com.umeng.commonsdk.statistics.common.DeviceConfig.LOG_TAG     // Catch: java.io.FileNotFoundException -> L34
            java.lang.String r3 = "Could not read from file /proc/cpuinfo"
            com.umeng.commonsdk.statistics.common.MLog.e(r2, r3, r1)     // Catch: java.io.FileNotFoundException -> L34
            goto L3c
        L34:
            r1 = move-exception
        L35:
            java.lang.String r2 = com.umeng.commonsdk.statistics.common.DeviceConfig.LOG_TAG
            java.lang.String r3 = "Could not open file /proc/cpuinfo"
            com.umeng.commonsdk.statistics.common.MLog.e(r2, r3, r1)
        L3c:
            if (r0 == 0) goto L4f
            r1 = 58
            int r1 = r0.indexOf(r1)
            int r1 = r1 + 1
            java.lang.String r0 = r0.substring(r1)
            java.lang.String r0 = r0.trim()
            return r0
        L4f:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.common.DeviceConfig.getCPU():java.lang.String");
    }

    public static String getDeviceId(Context context) {
        if (AnalyticsConstants.getDeviceType() == 2) {
            return getDeviceIdForBox(context);
        }
        return getDeviceIdForGeneral(context);
    }

    public static String getDeviceIdUmengMD5(Context context) {
        return HelperUtils.getUmengMD5(getDeviceId(context));
    }

    public static String getMCCMNC(Context context) {
        if (!FieldManager.allow(com.umeng.commonsdk.utils.b.c) || context == null || getImsi(context) == null) {
            return null;
        }
        int i = context.getResources().getConfiguration().mcc;
        int i2 = context.getResources().getConfiguration().mnc;
        if (i != 0) {
            String strValueOf = String.valueOf(i2);
            if (i2 < 10) {
                strValueOf = String.format("%02d", Integer.valueOf(i2));
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(String.valueOf(i));
            stringBuffer.append(strValueOf);
            return stringBuffer.toString();
        }
        return null;
    }

    public static String getImsi(Context context) {
        if (context == null) {
            return null;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (!checkPermission(context, MsgConstant.PERMISSION_READ_PHONE_STATE) || telephonyManager == null) {
            return null;
        }
        return telephonyManager.getSubscriberId();
    }

    public static String getRegisteredOperator(Context context) {
        if (context == null) {
            return null;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (!checkPermission(context, MsgConstant.PERMISSION_READ_PHONE_STATE) || telephonyManager == null) {
            return null;
        }
        return telephonyManager.getNetworkOperator();
    }

    public static String getNetworkOperatorName(Context context) {
        if (context == null || !FieldManager.allow(com.umeng.commonsdk.utils.b.q)) {
            return "";
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return (!checkPermission(context, MsgConstant.PERMISSION_READ_PHONE_STATE) || telephonyManager == null) ? "" : telephonyManager.getNetworkOperatorName();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String getDisplayResolution(Context context) {
        if (context == null) {
            return "";
        }
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager == null) {
                return "";
            }
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            return String.valueOf(displayMetrics.heightPixels) + "*" + String.valueOf(i);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String[] getNetworkAccessMode(Context context) {
        String[] strArr = {"", ""};
        if (context != null && FieldManager.allow(com.umeng.commonsdk.utils.b.r)) {
            try {
                if (!checkPermission(context, MsgConstant.PERMISSION_ACCESS_NETWORK_STATE)) {
                    strArr[0] = "";
                    return strArr;
                }
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager == null) {
                    strArr[0] = "";
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
            } catch (Throwable unused) {
            }
        }
        return strArr;
    }

    public static boolean isWiFiAvailable(Context context) {
        if (context == null) {
            return false;
        }
        return "Wi-Fi".equals(getNetworkAccessMode(context)[0]);
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        if (context == null) {
            return false;
        }
        try {
            if (checkPermission(context, MsgConstant.PERMISSION_ACCESS_NETWORK_STATE) && (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null) {
                return activeNetworkInfo.isConnectedOrConnecting();
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public static int getTimeZone(Context context) throws Throwable {
        if (context != null && FieldManager.allow(com.umeng.commonsdk.utils.b.o)) {
            try {
                Calendar calendar = Calendar.getInstance(getLocale(context));
                if (calendar != null) {
                    return calendar.getTimeZone().getRawOffset() / BaseConstants.Time.HOUR;
                }
            } catch (Throwable th) {
                MLog.i(LOG_TAG, "error in getTimeZone", th);
            }
        }
        return 8;
    }

    public static boolean isChineseAera(Context context) {
        if (context == null) {
            return false;
        }
        String strImprintProperty = UMEnvelopeBuild.imprintProperty(context, o.N, "");
        if (!TextUtils.isEmpty(strImprintProperty)) {
            return strImprintProperty.equals("cn");
        }
        if (getImsi(context) == null) {
            String str = getLocaleInfo(context)[0];
            if (!TextUtils.isEmpty(str) && str.equalsIgnoreCase("cn")) {
                return true;
            }
        } else {
            int i = context.getResources().getConfiguration().mcc;
            if (i == 460 || i == 461) {
                return true;
            }
            if (i == 0) {
                String str2 = getLocaleInfo(context)[0];
                if (!TextUtils.isEmpty(str2) && str2.equalsIgnoreCase("cn")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String[] getLocaleInfo(Context context) throws Throwable {
        String[] strArr = {"Unknown", "Unknown"};
        if (!FieldManager.allow(com.umeng.commonsdk.utils.b.p) || context == null) {
            return strArr;
        }
        try {
            Locale locale = getLocale(context);
            if (locale != null) {
                strArr[0] = locale.getCountry();
                strArr[1] = locale.getLanguage();
            }
            if (TextUtils.isEmpty(strArr[0])) {
                strArr[0] = "Unknown";
            }
            if (TextUtils.isEmpty(strArr[1])) {
                strArr[1] = "Unknown";
            }
            return strArr;
        } catch (Throwable th) {
            MLog.e(LOG_TAG, "error in getLocaleInfo", th);
            return strArr;
        }
    }

    private static Locale getLocale(Context context) throws Throwable {
        Locale locale;
        if (context == null) {
            return Locale.getDefault();
        }
        try {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            Settings.System.getConfiguration(context.getContentResolver(), configuration);
            locale = configuration.locale;
        } catch (Throwable unused) {
            MLog.e(LOG_TAG, "fail to read user config locale");
            locale = null;
        }
        return locale == null ? Locale.getDefault() : locale;
    }

    public static String getMac(Context context) {
        if ((!FieldManager.allow(com.umeng.commonsdk.utils.b.n) && !FieldManager.allow(com.umeng.commonsdk.utils.b.x)) || context == null) {
            return "";
        }
        if (Build.VERSION.SDK_INT < 23) {
            return getMacBySystemInterface(context);
        }
        if (Build.VERSION.SDK_INT == 23) {
            String macByJavaAPI = getMacByJavaAPI();
            if (!TextUtils.isEmpty(macByJavaAPI)) {
                return macByJavaAPI;
            }
            if (AnalyticsConstants.CHECK_DEVICE) {
                return getMacShell();
            }
            return getMacBySystemInterface(context);
        }
        String macByJavaAPI2 = getMacByJavaAPI();
        return TextUtils.isEmpty(macByJavaAPI2) ? getMacBySystemInterface(context) : macByJavaAPI2;
    }

    private static String getMacBySystemInterface(Context context) throws Throwable {
        if (context == null) {
            return "";
        }
        try {
            if (!FieldManager.allow(com.umeng.commonsdk.utils.b.h)) {
                return "";
            }
            WifiManager wifiManager = (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI);
            if (checkPermission(context, MsgConstant.PERMISSION_ACCESS_WIFI_STATE)) {
                return wifiManager != null ? wifiManager.getConnectionInfo().getMacAddress() : "";
            }
            if (!AnalyticsConstants.UM_DEBUG) {
                return "";
            }
            MLog.w(LOG_TAG, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
            return "";
        } catch (Throwable th) {
            if (!AnalyticsConstants.UM_DEBUG) {
                return "";
            }
            MLog.w(LOG_TAG, "Could not get mac address." + th.toString());
            return "";
        }
    }

    public static int[] getResolutionArray(Context context) throws Throwable {
        int iReflectMetrics;
        int iReflectMetrics2;
        if (context != null && FieldManager.allow(com.umeng.commonsdk.utils.b.m)) {
            try {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                WindowManager windowManager = (WindowManager) context.getSystemService("window");
                if (windowManager == null) {
                    return null;
                }
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                if ((context.getApplicationInfo().flags & 8192) == 0) {
                    iReflectMetrics = reflectMetrics(displayMetrics, "noncompatWidthPixels");
                    iReflectMetrics2 = reflectMetrics(displayMetrics, "noncompatHeightPixels");
                } else {
                    iReflectMetrics = -1;
                    iReflectMetrics2 = -1;
                }
                if (iReflectMetrics == -1 || iReflectMetrics2 == -1) {
                    iReflectMetrics = displayMetrics.widthPixels;
                    iReflectMetrics2 = displayMetrics.heightPixels;
                }
                int[] iArr = new int[2];
                if (iReflectMetrics > iReflectMetrics2) {
                    iArr[0] = iReflectMetrics2;
                    iArr[1] = iReflectMetrics;
                } else {
                    iArr[0] = iReflectMetrics;
                    iArr[1] = iReflectMetrics2;
                }
                return iArr;
            } catch (Throwable th) {
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.e(LOG_TAG, "read resolution fail", th);
                }
            }
        }
        return null;
    }

    private static int reflectMetrics(Object obj, String str) {
        try {
            Field declaredField = DisplayMetrics.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.getInt(obj);
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static String getPackageName(Context context) {
        if (context == null) {
            return null;
        }
        return context.getPackageName();
    }

    public static String getAppSHA1Key(Context context) {
        try {
            return byte2HexFormatted(MessageDigest.getInstance("SHA1").digest(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(getPackageName(context), 64).signatures[0].toByteArray()))).getEncoded()));
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getAppHashKey(Context context) {
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(getPackageName(context), 64).signatures;
            if (signatureArr.length <= 0) {
                return null;
            }
            Signature signature = signatureArr[0];
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(signature.toByteArray());
            return Base64.encodeToString(messageDigest.digest(), 0).trim();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String getAppMD5Signature(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return byte2HexFormatted(MessageDigest.getInstance("MD5").digest(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(getPackageName(context), 64).signatures[0].toByteArray()))).getEncoded()));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String byte2HexFormatted(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = Constants.FAIL + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase(Locale.getDefault()));
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    public static String getApplicationLable(Context context) {
        return context == null ? "" : context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
    }

    public static String getAppName(Context context) throws Throwable {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(LOG_TAG, th);
            }
            return null;
        }
    }

    public static String getDeviceIdForGeneral(Context context) throws Throwable {
        if (context == null) {
            return "";
        }
        if (Build.VERSION.SDK_INT < 23) {
            String imei = getIMEI(context);
            if (!TextUtils.isEmpty(imei)) {
                return imei;
            }
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.w(LOG_TAG, "No IMEI.");
            }
            String macBySystemInterface = getMacBySystemInterface(context);
            if (!TextUtils.isEmpty(macBySystemInterface)) {
                return macBySystemInterface;
            }
            if (FieldManager.allow(com.umeng.commonsdk.utils.b.i)) {
                String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.i(LOG_TAG, "getDeviceId, ANDROID_ID: " + string);
                }
                macBySystemInterface = string;
            }
            return TextUtils.isEmpty(macBySystemInterface) ? getSerialNo() : macBySystemInterface;
        }
        if (Build.VERSION.SDK_INT == 23) {
            String imei2 = getIMEI(context);
            if (!TextUtils.isEmpty(imei2)) {
                return imei2;
            }
            String macByJavaAPI = getMacByJavaAPI();
            if (TextUtils.isEmpty(macByJavaAPI)) {
                if (AnalyticsConstants.CHECK_DEVICE) {
                    macByJavaAPI = getMacShell();
                } else {
                    macByJavaAPI = getMacBySystemInterface(context);
                }
            }
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(LOG_TAG, "getDeviceId, MAC: " + macByJavaAPI);
            }
            if (!TextUtils.isEmpty(macByJavaAPI)) {
                return macByJavaAPI;
            }
            if (FieldManager.allow(com.umeng.commonsdk.utils.b.i)) {
                String string2 = Settings.Secure.getString(context.getContentResolver(), "android_id");
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.i(LOG_TAG, "getDeviceId, ANDROID_ID: " + string2);
                }
                macByJavaAPI = string2;
            }
            return TextUtils.isEmpty(macByJavaAPI) ? getSerialNo() : macByJavaAPI;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            String oaid = getOaid(context);
            Log.e("test_oaid", "+++++++" + oaid);
            if (!TextUtils.isEmpty(oaid)) {
                return oaid;
            }
            String idfa = getIdfa(context);
            if (!TextUtils.isEmpty(idfa)) {
                return idfa;
            }
            String androidId = getAndroidId(context);
            if (!TextUtils.isEmpty(androidId)) {
                return androidId;
            }
            String serialNo = getSerialNo();
            if (!TextUtils.isEmpty(serialNo)) {
                return serialNo;
            }
            String macByJavaAPI2 = getMacByJavaAPI();
            return TextUtils.isEmpty(macByJavaAPI2) ? getMacBySystemInterface(context) : macByJavaAPI2;
        }
        String imei3 = getIMEI(context);
        if (!TextUtils.isEmpty(imei3)) {
            return imei3;
        }
        String serialNo2 = getSerialNo();
        if (!TextUtils.isEmpty(serialNo2)) {
            return serialNo2;
        }
        if (FieldManager.allow(com.umeng.commonsdk.utils.b.i)) {
            serialNo2 = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(LOG_TAG, "getDeviceId, ANDROID_ID: " + serialNo2);
            }
        }
        if (!TextUtils.isEmpty(serialNo2)) {
            return serialNo2;
        }
        String macByJavaAPI3 = getMacByJavaAPI();
        if (!TextUtils.isEmpty(macByJavaAPI3)) {
            return macByJavaAPI3;
        }
        String macBySystemInterface2 = getMacBySystemInterface(context);
        if (!AnalyticsConstants.UM_DEBUG) {
            return macBySystemInterface2;
        }
        MLog.i(LOG_TAG, "getDeviceId, MAC: " + macBySystemInterface2);
        return macBySystemInterface2;
    }

    public static String getDeviceIdForBox(Context context) throws Throwable {
        String string = "";
        if (context == null) {
            return "";
        }
        if (Build.VERSION.SDK_INT < 23) {
            if (FieldManager.allow(com.umeng.commonsdk.utils.b.i)) {
                string = Settings.Secure.getString(context.getContentResolver(), "android_id");
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.i(LOG_TAG, "getDeviceId, ANDROID_ID: " + string);
                }
            }
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            String macBySystemInterface = getMacBySystemInterface(context);
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(LOG_TAG, "getDeviceId, MAC: " + macBySystemInterface);
            }
            if (!TextUtils.isEmpty(macBySystemInterface)) {
                return macBySystemInterface;
            }
            String serialNo = getSerialNo();
            return TextUtils.isEmpty(serialNo) ? getIMEI(context) : serialNo;
        }
        if (Build.VERSION.SDK_INT == 23) {
            if (FieldManager.allow(com.umeng.commonsdk.utils.b.i)) {
                string = Settings.Secure.getString(context.getContentResolver(), "android_id");
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.i(LOG_TAG, "getDeviceId, ANDROID_ID: " + string);
                }
            }
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            String macByJavaAPI = getMacByJavaAPI();
            if (TextUtils.isEmpty(macByJavaAPI)) {
                if (AnalyticsConstants.CHECK_DEVICE) {
                    macByJavaAPI = getMacShell();
                } else {
                    macByJavaAPI = getMacBySystemInterface(context);
                }
            }
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(LOG_TAG, "getDeviceId, MAC: " + macByJavaAPI);
            }
            if (!TextUtils.isEmpty(macByJavaAPI)) {
                return macByJavaAPI;
            }
            String serialNo2 = getSerialNo();
            return TextUtils.isEmpty(serialNo2) ? getIMEI(context) : serialNo2;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            String oaid = getOaid(context);
            Log.e("test_oaid", "+++++++" + oaid);
            if (!TextUtils.isEmpty(oaid)) {
                return oaid;
            }
            String idfa = getIdfa(context);
            if (!TextUtils.isEmpty(idfa)) {
                return idfa;
            }
            String androidId = getAndroidId(context);
            if (!TextUtils.isEmpty(androidId)) {
                return androidId;
            }
            String serialNo3 = getSerialNo();
            if (!TextUtils.isEmpty(serialNo3)) {
                return serialNo3;
            }
            String macByJavaAPI2 = getMacByJavaAPI();
            return TextUtils.isEmpty(macByJavaAPI2) ? getMacBySystemInterface(context) : macByJavaAPI2;
        }
        if (FieldManager.allow(com.umeng.commonsdk.utils.b.i)) {
            string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(LOG_TAG, "getDeviceId: ANDROID_ID: " + string);
            }
        }
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String serialNo4 = getSerialNo();
        if (!TextUtils.isEmpty(serialNo4)) {
            return serialNo4;
        }
        String imei = getIMEI(context);
        if (!TextUtils.isEmpty(imei)) {
            return imei;
        }
        String macByJavaAPI3 = getMacByJavaAPI();
        if (!TextUtils.isEmpty(macByJavaAPI3)) {
            return macByJavaAPI3;
        }
        String macBySystemInterface2 = getMacBySystemInterface(context);
        if (!AnalyticsConstants.UM_DEBUG) {
            return macBySystemInterface2;
        }
        MLog.i(LOG_TAG, "getDeviceId, MAC: " + macBySystemInterface2);
        return macBySystemInterface2;
    }

    private static String getOaid(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(i.a, 0);
            return sharedPreferences != null ? sharedPreferences.getString(i.b, "") : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    private static String getIdfa(Context context) {
        try {
            return FieldManager.allow(com.umeng.commonsdk.utils.b.w) ? a.a(context) : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    private static String getIMEI(Context context) throws Throwable {
        TelephonyManager telephonyManager;
        Throwable th;
        String deviceId;
        if (!FieldManager.allow(com.umeng.commonsdk.utils.b.g) || context == null || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
            return "";
        }
        try {
        } catch (Throwable th2) {
            th = th2;
            deviceId = "";
        }
        if (!checkPermission(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
            return "";
        }
        deviceId = telephonyManager.getDeviceId();
        try {
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(LOG_TAG, "getDeviceId, IMEI: " + deviceId);
            }
        } catch (Throwable th3) {
            th = th3;
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.w(LOG_TAG, "No IMEI.", th);
            }
            return deviceId;
        }
        return deviceId;
    }

    private static String getSerialNo() throws Throwable {
        String str = "";
        if (FieldManager.allow(com.umeng.commonsdk.utils.b.j) && Build.VERSION.SDK_INT >= 9) {
            if (Build.VERSION.SDK_INT >= 26) {
                try {
                    Class<?> cls = Class.forName("android.os.Build");
                    str = (String) cls.getMethod("getSerial", new Class[0]).invoke(cls, new Object[0]);
                } catch (Throwable unused) {
                }
            } else {
                str = Build.SERIAL;
            }
        }
        if (AnalyticsConstants.UM_DEBUG) {
            MLog.i(LOG_TAG, "getDeviceId, serial no: " + str);
        }
        return str;
    }

    public static String getSubOSName(Context context) throws Throwable {
        String str;
        if (!FieldManager.allow(com.umeng.commonsdk.utils.b.d)) {
            return null;
        }
        Properties buildProp = getBuildProp();
        try {
            String property = buildProp.getProperty(KEY_MIUI_VERSION_NAME);
            if (!TextUtils.isEmpty(property)) {
                str = RomUtils.ROM_MIUI;
            } else if (isFlyMe()) {
                str = "Flyme";
            } else if (isEmui(buildProp)) {
                str = "Emui";
            } else {
                if (TextUtils.isEmpty(getYunOSVersion(buildProp))) {
                    return property;
                }
                str = "YunOS";
            }
            return str;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String getSubOSVersion(Context context) throws Throwable {
        String yunOSVersion;
        if (!FieldManager.allow(com.umeng.commonsdk.utils.b.e)) {
            return null;
        }
        Properties buildProp = getBuildProp();
        try {
            String property = buildProp.getProperty(KEY_MIUI_VERSION_NAME);
            if (TextUtils.isEmpty(property)) {
                try {
                    if (isFlyMe()) {
                        yunOSVersion = getFlymeVersion(buildProp);
                    } else if (isEmui(buildProp)) {
                        yunOSVersion = getEmuiVersion(buildProp);
                    } else {
                        yunOSVersion = getYunOSVersion(buildProp);
                    }
                    return yunOSVersion;
                } catch (Throwable unused) {
                }
            }
            return property;
        } catch (Throwable unused2) {
            return null;
        }
    }

    private static String getYunOSVersion(Properties properties) {
        String property = properties.getProperty("ro.yunos.version");
        if (TextUtils.isEmpty(property)) {
            return null;
        }
        return property;
    }

    private static String getFlymeVersion(Properties properties) {
        try {
            String lowerCase = properties.getProperty("ro.build.display.id").toLowerCase(Locale.getDefault());
            if (lowerCase.contains("flyme os")) {
                return lowerCase.split(" ")[2];
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String getEmuiVersion(Properties properties) {
        try {
            return properties.getProperty(KEY_EMUI_VERSION_CODE, null);
        } catch (Exception unused) {
            return null;
        }
    }

    private static Properties getBuildProp() throws Throwable {
        FileInputStream fileInputStream;
        Properties properties = new Properties();
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
        }
        try {
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (Throwable th) {
            th = th;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (Throwable unused3) {
                }
            }
            throw th;
        }
        return properties;
    }

    private static boolean isFlyMe() {
        try {
            Build.class.getMethod("hasSmartBar", new Class[0]);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean isEmui(Properties properties) {
        try {
            return properties.getProperty(KEY_EMUI_VERSION_CODE, null) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String getDeviceType(Context context) {
        if (!FieldManager.allow(com.umeng.commonsdk.utils.b.f) || context == null) {
            return "Phone";
        }
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3 ? "Tablet" : "Phone";
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0057 -> B:25:0x0058). Please report as a decompilation issue!!! */
    public static String getDBencryptID(Context context) {
        String deviceId = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null && checkPermission(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
                    deviceId = telephonyManager.getDeviceId();
                }
                if (TextUtils.isEmpty(deviceId)) {
                    String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
                    if (!TextUtils.isEmpty(string) || Build.VERSION.SDK_INT < 9) {
                        deviceId = string;
                    } else if (Build.VERSION.SDK_INT >= 26) {
                        Class<?> cls = Class.forName("android.os.Build");
                        deviceId = (String) cls.getMethod("getSerial", new Class[0]).invoke(cls, new Object[0]);
                    } else {
                        deviceId = Build.SERIAL;
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return deviceId;
    }
}
