package com.mbridge.msdk.foundation.tools;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.kwad.v8.Platform;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SameDiTool.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class o extends d {
    private static String a = null;
    private static String b = null;
    private static int c = -1;
    private static int d = -1;
    private static volatile int e = 0;
    private static String f = "";
    private static String g = "";
    private static String h = "";
    private static String i = "";
    private static int j = 0;
    private static String k = "";
    private static String l = "";
    private static int m = -1;
    private static String n = "";
    private static int o = 0;
    private static String p = "";

    public static int a(int i2) {
        switch (i2) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return 2;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return 3;
            case 13:
            case 18:
            case 19:
                return 4;
            case 20:
                return 5;
            default:
                return 0;
        }
    }

    public static void q(Context context) {
        try {
            y();
            z(context);
            u(context);
            t(context);
            s(context);
            t();
            v();
            A();
            r(context);
            x();
            com.mbridge.msdk.foundation.same.a.h = false;
            com.mbridge.msdk.foundation.same.a.g = w.a("android.permission.ACCESS_NETWORK_STATE", context);
            v(context);
            try {
                d.g(context);
                d.b(context);
                d.c(context);
                d.i(context);
                d.j(context);
                try {
                    int iZ = z();
                    if (iZ >= 17 && iZ < 21) {
                        m = Settings.Global.getInt(com.mbridge.msdk.foundation.controller.a.f().j().getContentResolver(), "install_non_market_apps", 0);
                    } else if (iZ >= 21 || (iZ < 17 && iZ > 10)) {
                        m = Settings.Secure.getInt(com.mbridge.msdk.foundation.controller.a.f().j().getContentResolver(), "install_non_market_apps", 0);
                    }
                } catch (Throwable th) {
                    s.b("SameDiTool", th.getMessage(), th);
                }
                s.d("SameDiTool", "getUSSForPe:" + m);
                com.mbridge.msdk.foundation.same.a.j = w.a("android.permission.GET_TASKS", context);
                com.mbridge.msdk.foundation.same.a.i = false;
                d.a(context);
            } catch (Throwable unused) {
            }
            J();
        } catch (Throwable unused2) {
        }
    }

    public static String t() {
        return !com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) ? "" : Build.MODEL;
    }

    public static String u() {
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
            return "";
        }
        return Build.MANUFACTURER + " " + Build.MODEL;
    }

    public static String v() {
        return !com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) ? "" : Build.BRAND;
    }

    public static String r(Context context) {
        Locale locale;
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
            return "";
        }
        if (TextUtils.isEmpty(k)) {
            if (context == null) {
                return "en-US";
            }
            try {
                if (context.getResources() == null || context.getResources().getConfiguration() == null || (locale = context.getResources().getConfiguration().locale) == null) {
                    return "en-US";
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    k = locale.toLanguageTag();
                } else {
                    k = locale.getLanguage() + "-" + locale.getCountry();
                }
                return k;
            } catch (Throwable th) {
                s.a("SameDiTool", th.getMessage());
                k = "en-US";
            }
        }
        return k;
    }

    public static int s(Context context) {
        Configuration configuration;
        if (context == null || context.getResources() == null || (configuration = context.getResources().getConfiguration()) == null) {
            return 1;
        }
        int i2 = configuration.orientation;
        if (i2 == 2) {
            return 2;
        }
        return i2 == 1 ? 1 : 1;
    }

    public static int t(Context context) {
        if (context == null) {
            return j;
        }
        if (j == 0) {
            try {
                j = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
                return j;
            } catch (Exception e2) {
                e2.printStackTrace();
                return -1;
            }
        }
        return j;
    }

    public static String u(Context context) {
        if (context == null) {
            return i;
        }
        try {
            if (TextUtils.isEmpty(i)) {
                i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                return i;
            }
            return i;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static int v(Context context) {
        if (context == null) {
            return o;
        }
        if (o == 0) {
            try {
                o = context.getApplicationInfo().targetSdkVersion;
            } catch (Exception e2) {
                s.d("SameDiTool", e2.getMessage());
            }
        }
        return o;
    }

    public static int w(Context context) {
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) || context == null) {
            return 0;
        }
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            HashMap mapY = y(context);
            return mapY.get("width") == null ? displayMetrics.widthPixels : ((Integer) mapY.get("width")).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static int x(Context context) {
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) || context == null) {
            return 0;
        }
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            HashMap mapY = y(context);
            return mapY.get("height") == null ? displayMetrics.heightPixels : ((Integer) mapY.get("height")).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static HashMap y(Context context) {
        HashMap map = new HashMap();
        if (context == null) {
            return map;
        }
        try {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            if (Build.VERSION.SDK_INT >= 17) {
                defaultDisplay.getRealMetrics(displayMetrics);
            } else {
                defaultDisplay.getMetrics(displayMetrics);
            }
            map.put("height", Integer.valueOf(displayMetrics.heightPixels));
            map.put("width", Integer.valueOf(displayMetrics.widthPixels));
        } catch (Exception e2) {
            s.b("SameDiTool", e2.getMessage(), e2);
        }
        return map;
    }

    public static String z(Context context) {
        if (context == null) {
            return h;
        }
        try {
            if (TextUtils.isEmpty(h)) {
                h = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
                return h;
            }
            return h;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static void A(final Context context) {
        if (context == null) {
            return;
        }
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                p(context);
            } else {
                new Handler(context.getMainLooper()).post(new Runnable() { // from class: com.mbridge.msdk.foundation.tools.o.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        d.p(context);
                    }
                });
            }
        } catch (Exception e2) {
            s.b("SameDiTool", "", e2);
        }
    }

    public static String B(final Context context) {
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
            return null;
        }
        if (TextUtils.isEmpty(f)) {
            try {
                f = y.b(context, "mbridge_ua", "").toString();
            } catch (Throwable th) {
                s.b("SameDiTool", th.getMessage(), th);
            }
        }
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                if (TextUtils.isEmpty(f)) {
                    try {
                        if (Build.VERSION.SDK_INT >= 17) {
                            f = WebSettings.getDefaultUserAgent(context);
                        }
                    } catch (Throwable unused) {
                    }
                    if (TextUtils.isEmpty(f)) {
                        try {
                            Constructor declaredConstructor = WebSettings.class.getDeclaredConstructor(Context.class, WebView.class);
                            declaredConstructor.setAccessible(true);
                            f = ((WebSettings) declaredConstructor.newInstance(context, null)).getUserAgentString();
                            declaredConstructor.setAccessible(false);
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                        if (TextUtils.isEmpty(f)) {
                            try {
                                f = new WebView(context).getSettings().getUserAgentString();
                            } catch (Throwable th3) {
                                th3.printStackTrace();
                            }
                        }
                        if (TextUtils.isEmpty(f)) {
                            L();
                        }
                    }
                } else {
                    try {
                        new Thread(new Runnable() { // from class: com.mbridge.msdk.foundation.tools.o.2
                            @Override // java.lang.Runnable
                            public final void run() {
                                String defaultUserAgent = null;
                                try {
                                    try {
                                        if (Build.VERSION.SDK_INT >= 17) {
                                            defaultUserAgent = WebSettings.getDefaultUserAgent(context);
                                        }
                                    } catch (Throwable th4) {
                                        th4.printStackTrace();
                                        return;
                                    }
                                } catch (Exception unused2) {
                                }
                                if (TextUtils.isEmpty(defaultUserAgent) || defaultUserAgent.equals(o.f)) {
                                    return;
                                }
                                String unused3 = o.f = defaultUserAgent;
                                o.H(context);
                            }
                        }).start();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } else {
                L();
            }
        } catch (Throwable th4) {
            s.b("SameDiTool", th4.getMessage(), th4);
        }
        H(context);
        return f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void H(Context context) {
        try {
            y.a(context, "mbridge_ua", f);
        } catch (Throwable th) {
            s.b("SameDiTool", th.getMessage(), th);
        }
    }

    public static String w() {
        if (TextUtils.isEmpty(f)) {
            B(com.mbridge.msdk.foundation.controller.a.f().j());
        }
        return f;
    }

    private static void L() {
        String str = Build.VERSION.RELEASE;
        String strT = t();
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(strT)) {
            f = "Mozilla/5.0 (Linux; Android " + str + "; " + strT + " Build/) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.133 Mobile Safari/535.19";
            return;
        }
        f = "Mozilla/5.0 (Linux; Android 4.0.4; Galaxy Nexus Build/IMM76B) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.133 Mobile Safari/535.19";
    }

    public static int C(Context context) {
        try {
            final Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
                return e;
            }
            if (contextJ == null) {
                return e;
            }
            if (e == 0) {
                ConnectivityManager connectivityManager = (ConnectivityManager) contextJ.getSystemService("connectivity");
                if (connectivityManager == null) {
                    return e;
                }
                if (com.mbridge.msdk.foundation.same.a.g) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo == null) {
                        e = 0;
                        return e;
                    }
                    if (activeNetworkInfo.getType() == 1) {
                        e = 9;
                        return e;
                    }
                    TelephonyManager telephonyManager = (TelephonyManager) contextJ.getSystemService("phone");
                    if (telephonyManager == null) {
                        e = 0;
                        return e;
                    }
                    e = telephonyManager.getNetworkType();
                    return a(e);
                }
                e = 0;
                return e;
            }
            com.mbridge.msdk.foundation.same.f.b.a().execute(new Runnable() { // from class: com.mbridge.msdk.foundation.tools.o.3
                @Override // java.lang.Runnable
                public final void run() {
                    ConnectivityManager connectivityManager2;
                    try {
                        if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) && contextJ != null && (connectivityManager2 = (ConnectivityManager) contextJ.getSystemService("connectivity")) != null && com.mbridge.msdk.foundation.same.a.g) {
                            NetworkInfo activeNetworkInfo2 = connectivityManager2.getActiveNetworkInfo();
                            if (activeNetworkInfo2 == null) {
                                int unused = o.e = 0;
                                return;
                            }
                            if (activeNetworkInfo2.getType() == 1) {
                                int unused2 = o.e = 9;
                                return;
                            }
                            TelephonyManager telephonyManager2 = (TelephonyManager) contextJ.getSystemService("phone");
                            if (telephonyManager2 == null) {
                                int unused3 = o.e = 0;
                            } else {
                                int unused4 = o.e = o.a(telephonyManager2.getNetworkType());
                            }
                        }
                    } catch (Exception e2) {
                        s.b("SameDiTool", e2.getMessage(), e2);
                        int unused5 = o.e = 0;
                    }
                }
            });
            return e;
        } catch (Exception e2) {
            s.b("SameDiTool", e2.getMessage(), e2);
            e = 0;
            return e;
        }
    }

    public static String a(Context context, int i2) {
        TelephonyManager telephonyManager;
        if (i2 == 0 || i2 == 9) {
            return "";
        }
        try {
            return (!com.mbridge.msdk.foundation.same.a.g || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) ? "" : String.valueOf(telephonyManager.getNetworkType());
        } catch (Throwable th) {
            s.b("SameDiTool", th.getMessage(), th);
            return "";
        }
    }

    public static String x() {
        try {
        } catch (Throwable th) {
            s.b("SameDiTool", th.getMessage(), th);
        }
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_OTHER)) {
            return "";
        }
        if (TextUtils.isEmpty(l)) {
            new Thread(new Runnable() { // from class: com.mbridge.msdk.foundation.tools.o.4
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        String unused = o.l = TimeZone.getDefault().getDisplayName(false, 0, Locale.ENGLISH);
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
            }).start();
            return l;
        }
        return l;
    }

    public static String y() {
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
            return "";
        }
        if (TextUtils.isEmpty(g)) {
            g = z() + "";
        }
        return g;
    }

    public static int z() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static void a(String str) {
        b = n.a(str);
        a = str;
    }

    public static String A() {
        return (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_DEVICE_ID) && a != null) ? a : "";
    }

    public static String B() {
        return (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_DEVICE_ID) && b != null) ? b : "";
    }

    public static UUID C() {
        try {
            return UUID.randomUUID();
        } catch (Throwable th) {
            s.b("SameDiTool", th.getMessage(), th);
            return null;
        }
    }

    public static String a(String str, Context context) {
        try {
        } catch (Exception e2) {
            s.b("SameDiTool", e2.getMessage(), e2);
        }
        if (!TextUtils.isEmpty(n)) {
            return n;
        }
        if (!TextUtils.isEmpty(str) && context != null) {
            n = context.getPackageManager().getInstallerPackageName(str);
            s.a("SameDiTool", "PKGSource:" + n);
        }
        return n;
    }

    public static boolean D(Context context) {
        DisplayMetrics displayMetrics;
        if (context == null) {
            return false;
        }
        try {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
        } catch (Exception e2) {
            s.b("SameDiTool", e2.getMessage(), e2);
        }
        return Math.sqrt(Math.pow((double) (((float) displayMetrics.widthPixels) / displayMetrics.xdpi), 2.0d) + Math.pow((double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi), 2.0d)) >= 6.0d;
    }

    public static String D() {
        return !com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) ? "" : Build.MANUFACTURER;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v7 */
    public static String E() throws Throwable {
        FileReader fileReader;
        Throwable th;
        BufferedReader bufferedReader;
        Exception e2;
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
            return "";
        }
        ?? r0 = "/proc/meminfo";
        try {
            try {
                try {
                    fileReader = new FileReader("/proc/meminfo");
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            } catch (Exception e4) {
                fileReader = null;
                e2 = e4;
                bufferedReader = null;
            } catch (Throwable th2) {
                fileReader = null;
                th = th2;
                bufferedReader = null;
            }
        } catch (Throwable th3) {
            th = th3;
        }
        try {
            bufferedReader = new BufferedReader(fileReader, 8192);
            try {
                String str = bufferedReader.readLine().split("\\s+")[1];
                bufferedReader.close();
                iCeil = str != null ? (int) Math.ceil(new Float(Float.valueOf(str).floatValue() / 1048576.0f).doubleValue()) : 0;
                try {
                    fileReader.close();
                } catch (IOException unused) {
                }
                bufferedReader.close();
            } catch (Exception e5) {
                e2 = e5;
                s.b("SameDiTool", e2.getMessage(), e2);
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException unused2) {
                    }
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return iCeil + "GB";
            } catch (Throwable th4) {
                th = th4;
                s.b("SameDiTool", th.getMessage(), th);
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException unused3) {
                    }
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return iCeil + "GB";
            }
        } catch (Exception e6) {
            e2 = e6;
            bufferedReader = null;
        } catch (Throwable th5) {
            th = th5;
            r0 = 0;
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException unused4) {
                }
            }
            if (r0 == 0) {
                throw th;
            }
            try {
                r0.close();
                throw th;
            } catch (IOException e7) {
                e7.printStackTrace();
                throw th;
            }
        }
        return iCeil + "GB";
    }

    public static String E(Context context) {
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) || context == null) {
            return "";
        }
        try {
            return Settings.System.getString(context.getContentResolver(), "time_12_24");
        } catch (Exception e2) {
            s.b("SameDiTool", e2.getMessage(), e2);
            return "";
        } catch (Throwable th) {
            s.b("SameDiTool", th.getMessage(), th);
            return "";
        }
    }

    public static String F(Context context) throws Throwable {
        FileReader fileReader;
        BufferedReader bufferedReader;
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) || context == null) {
            return "";
        }
        BufferedReader bufferedReader2 = null;
        try {
            try {
                try {
                    fileReader = new FileReader("/proc/meminfo");
                    try {
                        bufferedReader = new BufferedReader(fileReader, 8192);
                    } catch (IOException e2) {
                        e = e2;
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (IOException e3) {
                    s.b("SameDiTool", e3.getMessage(), e3);
                    return "";
                }
            } catch (IOException e4) {
                e = e4;
                fileReader = null;
            } catch (Throwable th2) {
                th = th2;
                fileReader = null;
            }
        } catch (Throwable th3) {
            th = th3;
        }
        try {
            String fileSize = Formatter.formatFileSize(context, Long.valueOf(bufferedReader.readLine().split("\\s+")[1]).longValue() * 1024);
            try {
                bufferedReader.close();
            } catch (IOException e5) {
                s.b("SameDiTool", e5.getMessage(), e5);
            }
            try {
                fileReader.close();
            } catch (IOException e6) {
                s.b("SameDiTool", e6.getMessage(), e6);
            }
            return fileSize;
        } catch (IOException e7) {
            e = e7;
            bufferedReader2 = bufferedReader;
            s.b("SameDiTool", e.getMessage(), e);
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e8) {
                    s.b("SameDiTool", e8.getMessage(), e8);
                }
            }
            if (fileReader == null) {
                return "";
            }
            fileReader.close();
            return "";
        } catch (Throwable th4) {
            th = th4;
            bufferedReader2 = bufferedReader;
            s.b("SameDiTool", th.getMessage(), th);
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e9) {
                    s.b("SameDiTool", e9.getMessage(), e9);
                }
            }
            if (fileReader == null) {
                return "";
            }
            fileReader.close();
            return "";
        }
    }

    public static String F() throws JSONException {
        String strA = "";
        try {
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            long jCurrentTimeMillis = System.currentTimeMillis() - SystemClock.elapsedRealtime();
            long jM = M();
            String str = "app_tki_" + jCurrentTimeMillis + "_" + jM;
            String str2 = (String) y.b(contextJ, str, "");
            try {
                if (!TextUtils.isEmpty(str2)) {
                    return str2;
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("1", "");
                    jSONObject.put("2", String.valueOf(jM));
                    jSONObject.put(PushConstants.PUSH_FLYME_3_CHANGE_VERSION_START, String.valueOf(jCurrentTimeMillis));
                    jSONObject.put(PushConstants.PUSH_TYPE_WITHDRAW_NOTIFICATION, "");
                    jSONObject.put(CampaignEx.CLICKMODE_ON, "");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                strA = a.a(jSONObject.toString());
                y.a(contextJ, str, strA);
                return strA;
            } catch (Exception e3) {
                e = e3;
                strA = str2;
                e.printStackTrace();
                return strA;
            }
        } catch (Exception e4) {
            e = e4;
        }
    }

    public static int G() {
        try {
        } catch (Throwable th) {
            s.b("SameDiTool", th.getMessage(), th);
        }
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
            return 0;
        }
        Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
        long j2 = 0;
        long jLongValue = ((Long) y.b(contextJ, "FreeRamSize", 0L)).longValue();
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - jLongValue > 1800000 || d == -1) {
            Context contextJ2 = com.mbridge.msdk.foundation.controller.a.f().j();
            if (contextJ2 != null) {
                ActivityManager activityManager = (ActivityManager) contextJ2.getSystemService(PushConstants.INTENT_ACTIVITY_NAME);
                ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                activityManager.getMemoryInfo(memoryInfo);
                j2 = memoryInfo.availMem;
            }
            d = Long.valueOf((j2 / 1000) / 1000).intValue();
            y.a(contextJ, "FreeRamSize", Long.valueOf(jCurrentTimeMillis));
        }
        return d;
    }

    private static long M() {
        Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
        if (contextJ == null) {
            return 0L;
        }
        ActivityManager activityManager = (ActivityManager) contextJ.getSystemService(PushConstants.INTENT_ACTIVITY_NAME);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        if (Build.VERSION.SDK_INT >= 16) {
            return memoryInfo.totalMem;
        }
        return 0L;
    }

    public static int H() {
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
            return 0;
        }
        if (c < 1) {
            try {
                Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
                long jLongValue = ((Long) y.b(contextJ, "TotalRamSize", 0L)).longValue();
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - jLongValue > 1800000 || c == -1) {
                    c = Long.valueOf((M() / 1000) / 1000).intValue();
                    y.a(contextJ, "TotalRamSize", Long.valueOf(jCurrentTimeMillis));
                }
            } catch (Throwable th) {
                s.b("SameDiTool", th.getMessage(), th);
            }
        }
        return c;
    }

    public static int I() {
        return m;
    }

    public static String J() {
        if (!TextUtils.isEmpty(p)) {
            return p;
        }
        JSONObject jSONObject = new JSONObject();
        String str = null;
        try {
            Class<?> cls = Class.forName("com.huawei.system.BuildEx");
            str = (String) cls.getMethod("getOsBrand", new Class[0]).invoke(cls, new Object[0]);
        } catch (Throwable th) {
            s.d("SameDiTool", th.getMessage());
        }
        try {
            if (!TextUtils.isEmpty(str) && str.equals("harmony")) {
                jSONObject.put("osType", str);
                try {
                    Class<?> cls2 = Class.forName("ohos.system.version.SystemVersion");
                    jSONObject.put(HiAnalyticsConstant.HaKey.BI_KEY_VERSION, (String) cls2.getMethod("getVersion", new Class[0]).invoke(cls2, new Object[0]));
                } catch (Throwable th2) {
                    s.d("SameDiTool", th2.getMessage());
                }
                try {
                    jSONObject.put("pure_state", Settings.Secure.getInt(com.mbridge.msdk.foundation.controller.a.f().j().getContentResolver(), "pure_mode_state", -1));
                } catch (Throwable th3) {
                    s.d("SameDiTool", th3.getMessage());
                }
                String string = jSONObject.toString();
                if (!TextUtils.isEmpty(string)) {
                    string = n.a(string);
                }
                p = string;
            } else {
                p = Platform.ANDROID;
            }
        } catch (Throwable th4) {
            s.d("SameDiTool", th4.getMessage());
        }
        return p;
    }
}
