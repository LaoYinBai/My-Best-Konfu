package com.taobao.accs.utl;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import anet.channel.util.HMacUtil;
import com.alibaba.wireless.security.open.SecurityGuardManager;
import com.alibaba.wireless.security.open.SecurityGuardParamContext;
import com.alibaba.wireless.security.open.dynamicdatastore.IDynamicDataStoreComponent;
import com.alibaba.wireless.security.open.securesignature.ISecureSignatureComponent;
import com.alibaba.wireless.security.open.staticdataencrypt.IStaticDataEncryptComponent;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.AccsClientConfig;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UtilityImpl {
    public static final String NET_TYPE_2G = "2g";
    public static final String NET_TYPE_3G = "3g";
    public static final String NET_TYPE_4G = "4g";
    public static final String NET_TYPE_UNKNOWN = "unknown";
    public static final String NET_TYPE_WIFI = "wifi";
    public static final int TNET_FILE_NUM = 5;
    public static final int TNET_FILE_SIZE = 5242880;
    private static final byte[] a = new byte[0];

    public static String a(Context context) {
        String string = context.getSharedPreferences(Constants.SP_FILE_NAME, 4).getString(Constants.KEY_PROXY_HOST, null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String strB = b();
        if (TextUtils.isEmpty(strB)) {
            return null;
        }
        return strB;
    }

    public static int b(Context context) {
        int i = context.getSharedPreferences(Constants.SP_FILE_NAME, 4).getInt(Constants.KEY_PROXY_PORT, -1);
        if (i > 0) {
            return i;
        }
        if (a(context) == null) {
            return -1;
        }
        try {
            return c();
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public static boolean c(Context context) {
        String str;
        int i;
        synchronized (a) {
            PackageInfo packageInfo = GlobalClientInfo.getInstance(context).getPackageInfo();
            int i2 = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getInt("appVersionCode", -1);
            String string = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getString(Constants.KEY_APP_VERSION_NAME, "");
            if (packageInfo != null) {
                i = packageInfo.versionCode;
                str = packageInfo.versionName;
            } else {
                str = null;
                i = 0;
            }
            if (i2 == i && string.equals(str)) {
                return false;
            }
            t(context);
            ALog.i("UtilityImpl", "appVersionChanged", "oldV", Integer.valueOf(i2), "nowV", Integer.valueOf(i), "oldN", string, "nowN", str);
            return true;
        }
    }

    private static void t(Context context) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
            editorEdit.putInt("appVersionCode", GlobalClientInfo.getInstance(context).getPackageInfo().versionCode);
            editorEdit.putString(Constants.KEY_APP_VERSION_NAME, GlobalClientInfo.getInstance(context).getPackageInfo().versionName);
            editorEdit.apply();
        } catch (Throwable th) {
            ALog.e("UtilityImpl", "saveAppVersion", th, new Object[0]);
        }
    }

    public static void focusDisableService(Context context) {
        try {
            synchronized (a) {
                SharedPreferences.Editor editorEdit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
                editorEdit.putBoolean(Constants.KEY_FOUCE_DISABLE, true);
                editorEdit.apply();
                disableService(context);
            }
        } catch (Throwable th) {
            ALog.e("UtilityImpl", "focusDisableService", th, new Object[0]);
        }
    }

    public static void focusEnableService(Context context) {
        try {
            synchronized (a) {
                SharedPreferences.Editor editorEdit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
                editorEdit.putBoolean(Constants.KEY_FOUCE_DISABLE, false);
                editorEdit.apply();
                enableService(context);
            }
        } catch (Throwable th) {
            ALog.e("UtilityImpl", "focusEnableService", th, new Object[0]);
        }
    }

    public static boolean d(Context context) throws Throwable {
        boolean z;
        if (context == null) {
            return false;
        }
        try {
        } catch (Exception e) {
            e = e;
            context = null;
        }
        try {
            synchronized (a) {
                try {
                    z = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getBoolean(Constants.KEY_FOUCE_DISABLE, false);
                    return z;
                } catch (Throwable th) {
                    th = th;
                    context = null;
                    try {
                        throw th;
                    } catch (Exception e2) {
                        e = e2;
                        ALog.e("UtilityImpl", "getFocusDisableStatus", e, new Object[0]);
                        z = context;
                        return z;
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean e(Context context) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
            ALog.e("UtilityImpl", j.a(e), new Object[0]);
        }
        return context.getPackageManager().getServiceInfo(new ComponentName(context, j.channelService), 128).enabled;
    }

    public static boolean f(Context context) {
        ComponentName componentName = new ComponentName(context, com.taobao.accs.client.a.a(context.getPackageName()));
        PackageManager packageManager = context.getPackageManager();
        if (!componentName.getPackageName().equals("!")) {
            return packageManager.getServiceInfo(componentName, 128).enabled;
        }
        ALog.e("UtilityImpl", "getAgooServiceEnabled,exception,comptName.getPackageName()=" + componentName.getPackageName(), new Object[0]);
        return false;
    }

    public static void enableService(Context context) {
        ComponentName componentName = new ComponentName(context, j.channelService);
        ALog.d("UtilityImpl", "enableService", "comptName", componentName);
        try {
            context.getPackageManager().setComponentEnabledSetting(componentName, 1, 1);
        } catch (Exception e) {
            ALog.w("UtilityImpl", "enableService", e, new Object[0]);
        }
    }

    public static void disableService(Context context) {
        ComponentName componentName = new ComponentName(context, j.channelService);
        PackageManager packageManager = context.getPackageManager();
        try {
            ALog.d("UtilityImpl", "disableService,comptName=" + componentName.toString(), new Object[0]);
            if (packageManager.getServiceInfo(componentName, 128).enabled) {
                packageManager.setComponentEnabledSetting(componentName, 2, 1);
                killService(context);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    public static void killService(Context context) {
        try {
            int iMyUid = Process.myUid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null) {
                return;
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                if (runningAppProcessInfo.uid == iMyUid) {
                    if (!TextUtils.isEmpty(com.taobao.accs.client.a.e) && com.taobao.accs.client.a.e.equals(runningAppProcessInfo.processName)) {
                        ALog.e("UtilityImpl", "killService", "processName", runningAppProcessInfo.processName);
                        Process.killProcess(runningAppProcessInfo.pid);
                        return;
                    } else if (runningAppProcessInfo.processName.endsWith(":channel")) {
                        ALog.e("UtilityImpl", "killService", "processName", runningAppProcessInfo.processName);
                        Process.killProcess(runningAppProcessInfo.pid);
                        return;
                    }
                }
            }
            ALog.e("UtilityImpl", "kill nothing", new Object[0]);
        } catch (Throwable th) {
            ALog.e("UtilityImpl", "killService", th, new Object[0]);
        }
    }

    public static String g(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return NET_TYPE_UNKNOWN;
        }
        if (activeNetworkInfo.getType() == 1) {
            return NET_TYPE_WIFI;
        }
        String subtypeName = activeNetworkInfo.getSubtypeName();
        return !TextUtils.isEmpty(subtypeName) ? subtypeName.replaceAll(" ", "") : "";
    }

    public static String h(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            ALog.e("UtilityImpl", "getNetworkTypeExt", e, new Object[0]);
            return null;
        }
        if (activeNetworkInfo == null) {
            return NET_TYPE_UNKNOWN;
        }
        if (activeNetworkInfo.getType() == 1) {
            return NET_TYPE_WIFI;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return NET_TYPE_2G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return NET_TYPE_3G;
            case 13:
                return NET_TYPE_4G;
            default:
                String subtypeName = activeNetworkInfo.getSubtypeName();
                return TextUtils.isEmpty(subtypeName) ? NET_TYPE_UNKNOWN : (subtypeName.equalsIgnoreCase("td-scdma") || subtypeName.equalsIgnoreCase("td_scdma")) ? NET_TYPE_3G : subtypeName.equalsIgnoreCase("tds_hsdpa") ? NET_TYPE_3G : NET_TYPE_UNKNOWN;
        }
        ALog.e("UtilityImpl", "getNetworkTypeExt", e, new Object[0]);
        return null;
    }

    public static String i(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String a(long j) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Long.valueOf(j));
        } catch (Throwable th) {
            ALog.e("UtilityImpl", "formatDay", th, new Object[0]);
            return "";
        }
    }

    public static boolean j(Context context) {
        long jCurrentTimeMillis;
        String packageName;
        try {
            jCurrentTimeMillis = System.currentTimeMillis();
            packageName = GlobalClientInfo.getInstance(context).getActivityManager().getRunningTasks(1).get(0).topActivity.getPackageName();
        } catch (Throwable th) {
            ALog.e("UtilityImpl", "isForeground error ", th, new Object[0]);
        }
        if (!TextUtils.isEmpty(packageName) && packageName.equals(context.getPackageName())) {
            return true;
        }
        if (ALog.isPrintLog(ALog.Level.D)) {
            ALog.d("UtilityImpl", "isForeground time " + (System.currentTimeMillis() - jCurrentTimeMillis), new Object[0]);
        }
        return false;
    }

    private static boolean d(String str) {
        AccsClientConfig configByTag = AccsClientConfig.getConfigByTag(str);
        return (configByTag == null ? 0 : configByTag.getSecurity()) == 2;
    }

    public static String a(Context context, String str, String str2, String str3, String str4) {
        String strSignRequest;
        if (TextUtils.isEmpty(str)) {
            ALog.e("UtilityImpl", "getAppsign appkey null", new Object[0]);
            return null;
        }
        try {
            if (d(str4)) {
                if (!TextUtils.isEmpty(str2)) {
                    strSignRequest = HMacUtil.hmacSha1Hex(str2.getBytes(), (str + str3).getBytes());
                } else {
                    ALog.e("UtilityImpl", "getAppsign secret null", new Object[0]);
                    return null;
                }
            } else {
                SecurityGuardManager securityGuardManager = SecurityGuardManager.getInstance(context);
                if (securityGuardManager != null) {
                    ALog.d("UtilityImpl", "getAppsign SecurityGuardManager not null!", new Object[0]);
                    ISecureSignatureComponent secureSignatureComp = securityGuardManager.getSecureSignatureComp();
                    SecurityGuardParamContext securityGuardParamContext = new SecurityGuardParamContext();
                    securityGuardParamContext.appKey = str;
                    securityGuardParamContext.paramMap.put("INPUT", str3 + str);
                    securityGuardParamContext.requestType = 3;
                    AccsClientConfig configByTag = AccsClientConfig.getConfigByTag(str4);
                    strSignRequest = secureSignatureComp.signRequest(securityGuardParamContext, configByTag != null ? configByTag.getAuthCode() : null);
                } else {
                    ALog.d("UtilityImpl", "getAppsign SecurityGuardManager is null", new Object[0]);
                    return null;
                }
            }
            return strSignRequest;
        } catch (Throwable th) {
            ALog.e("UtilityImpl", "getAppsign", th, new Object[0]);
            return null;
        }
    }

    public static byte[] a(Context context, String str, String str2, byte[] bArr) {
        IStaticDataEncryptComponent staticDataEncryptComp;
        byte[] bArrStaticBinarySafeDecryptNoB64 = null;
        if (d(str)) {
            return null;
        }
        if (context == null || bArr == null) {
            ALog.e("UtilityImpl", "staticBinarySafeDecryptNoB64 input null!", new Object[0]);
            return null;
        }
        try {
            AccsClientConfig configByTag = AccsClientConfig.getConfigByTag(str);
            String authCode = configByTag != null ? configByTag.getAuthCode() : null;
            ALog.i("UtilityImpl", "staticBinarySafeDecryptNoB64", "appkey", str2, "authcode", authCode);
            SecurityGuardManager securityGuardManager = SecurityGuardManager.getInstance(context);
            if (securityGuardManager != null && (staticDataEncryptComp = securityGuardManager.getStaticDataEncryptComp()) != null) {
                bArrStaticBinarySafeDecryptNoB64 = staticDataEncryptComp.staticBinarySafeDecryptNoB64(16, "tnet_pksg_key", bArr, authCode);
            }
            if (bArrStaticBinarySafeDecryptNoB64 == null) {
                ALog.e("UtilityImpl", "staticBinarySafeDecryptNoB64 null", new Object[0]);
            }
        } catch (Throwable th) {
            ALog.e("UtilityImpl", "staticBinarySafeDecryptNoB64", th, new Object[0]);
        }
        return bArrStaticBinarySafeDecryptNoB64;
    }

    public static int a(Context context, String str, String str2, String str3, byte[] bArr) {
        SecurityGuardManager securityGuardManager;
        IDynamicDataStoreComponent dynamicDataStoreComp;
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || context == null || bArr == null) {
            return -1;
        }
        try {
            if (d(str) || (securityGuardManager = SecurityGuardManager.getInstance(context)) == null || (dynamicDataStoreComp = securityGuardManager.getDynamicDataStoreComp()) == null) {
                return -1;
            }
            return dynamicDataStoreComp.putByteArray("accs_ssl_key2_" + str3, bArr);
        } catch (Throwable th) {
            ALog.e("UtilityImpl", "SecurityGuardPutSslTicket2", th, new Object[0]);
            return -1;
        }
    }

    public static byte[] a(Context context, String str, String str2, String str3) {
        SecurityGuardManager securityGuardManager;
        IDynamicDataStoreComponent dynamicDataStoreComp;
        if (context == null || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str2)) {
            ALog.i("UtilityImpl", "get sslticket input null", new Object[0]);
            return null;
        }
        try {
            if (d(str) || (securityGuardManager = SecurityGuardManager.getInstance(context)) == null || (dynamicDataStoreComp = securityGuardManager.getDynamicDataStoreComp()) == null) {
                return null;
            }
            return dynamicDataStoreComp.getByteArray("accs_ssl_key2_" + str3);
        } catch (Throwable th) {
            ALog.e("UtilityImpl", "SecurityGuardGetSslTicket2", th, new Object[0]);
            return null;
        }
    }

    public static boolean k(Context context) {
        if (context == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = GlobalClientInfo.getInstance(context).getConnectivityManager().getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnected();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String l(Context context) {
        return j.b(context);
    }

    public static boolean a(Context context, String str) throws PackageManager.NameNotFoundException {
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            ALog.e("UtilityImpl", "package not exist", "pkg", str);
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean a(String str, Context context) {
        boolean z;
        try {
            try {
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            str = null;
        }
        synchronized (a) {
            try {
                String strB = j.b(context);
                z = !context.getSharedPreferences(str, 0).getString("utdid", strB).equals(strB);
                return z;
            } catch (Throwable th3) {
                th = th3;
                str = null;
                try {
                    throw th;
                } catch (Throwable th4) {
                    th = th4;
                    ALog.e("UtilityImpl", "utdidChanged", th, new Object[0]);
                    z = str;
                    return z;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean b(String str, Context context) {
        boolean z;
        try {
            try {
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            str = null;
        }
        synchronized (a) {
            try {
                String strD = j.d(context);
                z = !context.getSharedPreferences(str, 0).getString(Constants.SP_KEY_NOTIFICATION_STATE, strD).equals(strD);
                return z;
            } catch (Throwable th3) {
                th = th3;
                str = null;
                try {
                    throw th;
                } catch (Throwable th4) {
                    th = th4;
                    ALog.e("UtilityImpl", "notificationStateChanged", th, new Object[0]);
                    z = str;
                    return z;
                }
            }
        }
    }

    public static void c(String str, Context context) {
        try {
            synchronized (a) {
                SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, 0).edit();
                editorEdit.putString("utdid", j.b(context));
                editorEdit.apply();
            }
        } catch (Throwable th) {
            ALog.e("UtilityImpl", "saveUtdid", th, new Object[0]);
        }
    }

    public static String d(String str, Context context) {
        String string;
        try {
            synchronized (a) {
                string = context.getSharedPreferences(str, 0).getString("utdid", j.b(context));
            }
            return string;
        } catch (Throwable th) {
            ALog.e("UtilityImpl", "getUtdid", th, new Object[0]);
            return "";
        }
    }

    public static String m(Context context) {
        String strI = i(context);
        return (strI == null || strI.length() <= 5) ? "null" : strI.substring(0, 5);
    }

    public static void a(Context context, String str, long j) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(Constants.SP_CHANNEL_FILE_NAME, 0).edit();
            editorEdit.putLong(str, j);
            editorEdit.apply();
            ALog.d("UtilityImpl", "setServiceTime:" + j, new Object[0]);
        } catch (Throwable th) {
            ALog.e("UtilityImpl", "setServiceTime:", th, new Object[0]);
        }
    }

    public static void a(Context context, String str, String str2) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, 4).edit();
            editorEdit.putString(Constants.SP_KEY_NOTIFICATION_STATE, str2);
            editorEdit.apply();
        } catch (Exception e) {
            ALog.e("UtilityImpl", "saveNotificationState fail", e, new Object[0]);
        }
    }

    public static long n(Context context) {
        long j;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SP_CHANNEL_FILE_NAME, 0);
            long j2 = sharedPreferences.getLong(Constants.SP_KEY_SERVICE_START, 0L);
            j = j2 > 0 ? sharedPreferences.getLong(Constants.SP_KEY_SERVICE_END, 0L) - j2 : 0L;
            try {
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.putLong(Constants.SP_KEY_SERVICE_START, 0L);
                editorEdit.putLong(Constants.SP_KEY_SERVICE_END, 0L);
                editorEdit.apply();
            } catch (Throwable th) {
                th = th;
                ALog.e("UtilityImpl", "getServiceAliveTime:", th, new Object[0]);
                return j;
            }
        } catch (Throwable th2) {
            th = th2;
            j = 0;
        }
        return j;
    }

    public static String o(Context context) {
        try {
            return GlobalClientInfo.getInstance(context).getPackageInfo().versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean isMainProcess(Context context) {
        return j.a(context);
    }

    public static int a(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return str.getBytes("utf-8").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String a(Throwable th) {
        return j.a(th);
    }

    public static void b(Context context, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            GlobalClientInfo.c = str;
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(Constants.SP_COOKIE_FILE_NAME, 0).edit();
            editorEdit.putString(Constants.SP_KEY_COOKIE_SEC, str);
            editorEdit.apply();
        } catch (Exception e) {
            ALog.e("UtilityImpl", "storeCookie fail", e, new Object[0]);
        }
    }

    public static String p(Context context) {
        try {
            return context.getSharedPreferences(Constants.SP_COOKIE_FILE_NAME, 4).getString(Constants.SP_KEY_COOKIE_SEC, null);
        } catch (Exception e) {
            ALog.e("UtilityImpl", "reStoreCookie fail", e, new Object[0]);
            return null;
        }
    }

    public static void q(Context context) {
        try {
            GlobalClientInfo.c = null;
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(Constants.SP_COOKIE_FILE_NAME, 0).edit();
            editorEdit.clear();
            editorEdit.apply();
        } catch (Throwable th) {
            ALog.e("UtilityImpl", "clearCookie fail", th, new Object[0]);
        }
    }

    public static long a() {
        return j.a();
    }

    public static String b() {
        if (Build.VERSION.SDK_INT < 11) {
            return Proxy.getDefaultHost();
        }
        return System.getProperty("http.proxyHost");
    }

    public static int c() {
        if (Build.VERSION.SDK_INT < 11) {
            return Proxy.getDefaultPort();
        }
        try {
            return Integer.parseInt(System.getProperty("http.proxyPort"));
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public static String d() {
        String str = b() + com.xiaomi.mipush.sdk.Constants.COLON_SEPARATOR + c();
        if (ALog.isPrintLog(ALog.Level.D)) {
            ALog.d("UtilityImpl", "getProxy:" + str, new Object[0]);
        }
        return str;
    }

    public static String r(Context context) {
        return j.d(context);
    }

    public static String c(Context context, String str) {
        try {
            File externalFilesDir = context.getExternalFilesDir("tnetlogs");
            if (externalFilesDir == null || !externalFilesDir.exists() || !externalFilesDir.canWrite()) {
                externalFilesDir = context.getDir("logs", 0);
            }
            ALog.d("UtilityImpl", "getTnetLogFilePath :" + externalFilesDir, new Object[0]);
            return externalFilesDir + "/" + str.toLowerCase();
        } catch (Throwable th) {
            ALog.e("UtilityImpl", "getTnetLogFilePath", th, new Object[0]);
            return null;
        }
    }

    public static String a(int i) {
        try {
            return String.valueOf(i);
        } catch (Exception e) {
            ALog.e("UtilityImpl", "int2String", e, new Object[0]);
            return null;
        }
    }

    public static int b(String str) {
        try {
            return Integer.valueOf(str).intValue();
        } catch (Exception e) {
            ALog.e("UtilityImpl", "String2Int", e, new Object[0]);
            return 0;
        }
    }

    public static String[] s(Context context) {
        String[] appkey;
        synchronized (a) {
            appkey = ACCSManager.getAppkey(context);
        }
        return appkey;
    }

    public static void d(Context context, String str) {
        try {
            synchronized (a) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SP_FILE_NAME, 0);
                String string = sharedPreferences.getString("appkey", "");
                if (!TextUtils.isEmpty(str) && !string.equals(str) && !string.contains(str)) {
                    if (!TextUtils.isEmpty(string)) {
                        str = string + "|" + str;
                    }
                    SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                    editorEdit.putString("appkey", str);
                    editorEdit.apply();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void clearSharePreferences(Context context) {
        try {
            synchronized (a) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SP_FILE_NAME, 0);
                String string = sharedPreferences.getString("appkey", null);
                String string2 = sharedPreferences.getString("app_sercet", null);
                String string3 = sharedPreferences.getString(Constants.KEY_PROXY_HOST, null);
                int i = sharedPreferences.getInt(Constants.KEY_PROXY_PORT, -1);
                int i2 = sharedPreferences.getInt("version", -1);
                int i3 = sharedPreferences.getInt(Constants.SP_KEY_DEBUG_MODE, 0);
                SharedPreferences.Editor editorEdit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
                editorEdit.clear();
                if (!TextUtils.isEmpty(string)) {
                    editorEdit.putString("appkey", string);
                }
                if (!TextUtils.isEmpty(string2)) {
                    editorEdit.putString("app_sercet", string2);
                }
                if (!TextUtils.isEmpty(string3)) {
                    editorEdit.putString(Constants.KEY_PROXY_HOST, string3);
                }
                if (i > 0) {
                    editorEdit.putInt(Constants.KEY_PROXY_PORT, i);
                }
                if (i2 > 0) {
                    editorEdit.putInt("version", i2);
                }
                if (i3 == 2 || i3 == 1) {
                    editorEdit.putInt(Constants.SP_KEY_DEBUG_MODE, i3);
                }
                editorEdit.apply();
            }
        } catch (Throwable th) {
            ALog.e("UtilityImpl", "clearSharePreferences", th, new Object[0]);
        }
    }

    public static String e() throws ClassNotFoundException {
        String str;
        Class<?>[] clsArr = {String.class};
        Object[] objArr = {"ro.build.version.emui"};
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str = (String) cls.getDeclaredMethod(MonitorConstants.CONNECT_TYPE_GET, clsArr).invoke(cls, objArr);
            ALog.d("UtilityImpl", "getEmuiVersion", "result", str);
        } catch (Exception e) {
            ALog.e("UtilityImpl", "getEmuiVersion", e, new Object[0]);
        }
        return !TextUtils.isEmpty(str) ? str : "";
    }

    public static final Map<String, String> a(Map<String, List<String>> map) {
        HashMap map2 = new HashMap();
        try {
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                String key = entry.getKey();
                if (!TextUtils.isEmpty(key)) {
                    String strA = a(entry.getValue());
                    if (!TextUtils.isEmpty(strA)) {
                        if (!key.startsWith(com.xiaomi.mipush.sdk.Constants.COLON_SEPARATOR)) {
                            key = key.toLowerCase(Locale.US);
                        }
                        map2.put(key, strA);
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return map2;
    }

    public static final String a(List<String> list) {
        StringBuffer stringBuffer = new StringBuffer();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            stringBuffer.append(list.get(i));
            if (i < size - 1) {
                stringBuffer.append(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
        }
        return stringBuffer.toString();
    }

    public static String c(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    public static boolean a(long j, long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date(j2));
        return calendar.get(1) == calendar2.get(1) && calendar.get(2) == calendar2.get(2) && calendar.get(5) == calendar2.get(5);
    }
}
