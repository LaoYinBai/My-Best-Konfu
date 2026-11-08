package com.huawei.updatesdk.a.a.c.a;

import android.content.Context;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.huawei.hms.common.PackageConstants;
import com.huawei.updatesdk.a.a.c.c;
import com.huawei.updatesdk.a.a.c.e;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static b a = new b();
    private static String d = null;
    private int b;
    private int c;

    private b() {
        this.b = 0;
        this.c = 0;
        int iA = a("ro.maple.enable", 0);
        int iA2 = a("persist.mygote.disable", 0);
        if (iA == 1 && iA2 != 1) {
            this.b = 1;
        }
        this.c = a("ro.build.version.ark", 0);
    }

    public static int a(String str, int i) {
        try {
            return ((Integer) Class.forName("android.os.SystemProperties").getMethod("getInt", String.class, Integer.TYPE).invoke(null, str, Integer.valueOf(i))).intValue();
        } catch (Throwable th) {
            com.huawei.updatesdk.a.a.b.a.a.a.a("DeviceUtil", "Exception while getting system property: ", th);
            return i;
        }
    }

    public static String a() {
        try {
            Object obj = Class.forName("com.huawei.system.BuildEx").getField("DISPLAY").get(null);
            return obj == null ? Build.DISPLAY : obj.toString();
        } catch (ClassNotFoundException unused) {
            return Build.DISPLAY;
        } catch (IllegalAccessException unused2) {
            return Build.DISPLAY;
        } catch (NoSuchFieldException unused3) {
            return Build.DISPLAY;
        } catch (Throwable unused4) {
            return Build.DISPLAY;
        }
    }

    public static String a(Context context) {
        DisplayMetrics displayMetricsJ = j(context);
        return displayMetricsJ != null ? String.valueOf(displayMetricsJ.densityDpi) : "";
    }

    public static String a(String str) {
        String str2;
        StringBuilder sb;
        String string;
        try {
            Object objInvoke = Class.forName("com.huawei.hms.framework.network.grs.GrsApi").getMethod("synGetGrsUrl", String.class, String.class).invoke(null, "com.huawei.update", "ROOT");
            if (!(objInvoke instanceof String)) {
                return str;
            }
            String str3 = (String) objInvoke;
            try {
                Log.i("DeviceUtil", "UpdateSDK Get url from GRS_SDK Success!");
                return str3;
            } catch (ClassNotFoundException e) {
                e = e;
                str = str3;
                str2 = "DeviceUtil";
                sb = new StringBuilder();
                sb.append("UpdateSDK Get url from GRS_SDK error: ");
                string = e.toString();
                sb.append(string);
                Log.w(str2, sb.toString());
                return str;
            } catch (NoSuchMethodError e2) {
                e = e2;
                str = str3;
                str2 = "DeviceUtil";
                sb = new StringBuilder();
                sb.append("UpdateSDK Get url from GRS_SDK error: ");
                string = e.toString();
                sb.append(string);
                Log.w(str2, sb.toString());
                return str;
            } catch (NoSuchMethodException e3) {
                e = e3;
                str = str3;
                str2 = "DeviceUtil";
                sb = new StringBuilder();
                sb.append("UpdateSDK Get url from GRS_SDK error: ");
                string = e.toString();
                sb.append(string);
                Log.w(str2, sb.toString());
                return str;
            } catch (Exception e4) {
                e = e4;
                str = str3;
                str2 = "DeviceUtil";
                sb = new StringBuilder();
                sb.append("UpdateSDK Get url from GRS_SDK error: ");
                string = e.toString();
                sb.append(string);
                Log.w(str2, sb.toString());
                return str;
            } catch (Throwable th) {
                th = th;
                str = str3;
                str2 = "DeviceUtil";
                sb = new StringBuilder();
                sb.append("UpdateSDK Get url from GRS_SDK error: ");
                string = th.toString();
                sb.append(string);
                Log.w(str2, sb.toString());
                return str;
            }
        } catch (ClassNotFoundException e5) {
            e = e5;
        } catch (NoSuchMethodException e6) {
            e = e6;
        } catch (Exception e7) {
            e = e7;
        } catch (NoSuchMethodError e8) {
            e = e8;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Throwable th) {
            com.huawei.updatesdk.a.a.b.a.a.a.a("DeviceUtil", "Exception while getting system property: ", th);
            return str2;
        }
    }

    public static List<String> a(List<String> list, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (!a(str, strArr)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    static boolean a(String str, String[] strArr) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (String str2 : strArr) {
            if (str2.startsWith("config.")) {
                i = 1;
            } else if (!str2.contains(".config.")) {
                i2++;
            }
            if (str2.endsWith("config." + str)) {
                i3++;
            }
        }
        return i + i2 == i3;
    }

    public static String b() {
        String script;
        String language = "en";
        script = "";
        String country = "US";
        Locale locale = Locale.getDefault();
        if (locale != null) {
            language = locale.getLanguage();
            script = Build.VERSION.SDK_INT >= 21 ? locale.getScript() : "";
            country = locale.getCountry();
        }
        if (TextUtils.isEmpty(script)) {
            return language + "_" + country;
        }
        return language + "_" + script + "_" + country;
    }

    static String b(Context context) {
        if (d == null) {
            d = e.a(k(context), ",");
        }
        return d;
    }

    public static List<String> c(Context context) {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 24) {
            LocaleList locales = context.getResources().getConfiguration().getLocales();
            for (int i = 0; i < locales.size(); i++) {
                String language = locales.get(i).getLanguage();
                if (!TextUtils.isEmpty(language) && !arrayList.contains(language)) {
                    arrayList.add(language);
                }
            }
        } else {
            arrayList.add(context.getResources().getConfiguration().locale.getLanguage());
        }
        return arrayList;
    }

    public static String[] c() {
        return Build.VERSION.SDK_INT >= 21 ? Build.SUPPORTED_ABIS == null ? new String[0] : Build.SUPPORTED_ABIS : new String[]{Build.CPU_ABI};
    }

    public static int d() {
        String str;
        StringBuilder sb;
        String string;
        try {
            Class<?> cls = Class.forName("android.os.UserHandle");
            Object objInvoke = cls.getMethod("myUserId", new Class[0]).invoke(cls, new Object[0]);
            if (objInvoke instanceof Integer) {
                return ((Integer) objInvoke).intValue();
            }
            return 0;
        } catch (ClassNotFoundException e) {
            str = "DeviceUtil";
            sb = new StringBuilder();
            sb.append("get current uid ClassNotFoundException! ");
            string = e.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return 0;
        } catch (IllegalAccessException e2) {
            str = "DeviceUtil";
            sb = new StringBuilder();
            sb.append("get current uid IllegalAccessException! ");
            string = e2.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return 0;
        } catch (IllegalArgumentException e3) {
            str = "DeviceUtil";
            sb = new StringBuilder();
            sb.append("get current uid IllegalArgumentException! ");
            string = e3.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return 0;
        } catch (NoSuchMethodException e4) {
            str = "DeviceUtil";
            sb = new StringBuilder();
            sb.append("get current uid NoSuchMethodException! ");
            string = e4.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return 0;
        } catch (InvocationTargetException e5) {
            str = "DeviceUtil";
            sb = new StringBuilder();
            sb.append("get current uid InvocationTargetException! ");
            string = e5.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return 0;
        } catch (Exception e6) {
            str = "DeviceUtil";
            sb = new StringBuilder();
            sb.append("get current uid IllegalAccessException! ");
            string = e6.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return 0;
        } catch (Throwable th) {
            str = "DeviceUtil";
            sb = new StringBuilder();
            sb.append("get current uid IllegalAccessException! ");
            string = th.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return 0;
        }
    }

    public static String d(Context context) {
        DisplayMetrics displayMetricsJ = j(context);
        if (displayMetricsJ == null) {
            return "";
        }
        return String.valueOf(displayMetricsJ.widthPixels) + "_" + String.valueOf(displayMetricsJ.heightPixels);
    }

    public static String e() {
        return Build.VERSION.RELEASE.trim();
    }

    public static boolean e(Context context) throws PackageManager.NameNotFoundException {
        String str;
        String message;
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getPackageInfo("com.google.android.gsf.login", 16);
            packageManager.getPackageInfo("com.google.android.gsf", 16);
        } catch (PackageManager.NameNotFoundException e) {
            str = "DeviceUtil";
            message = e.getMessage();
        }
        try {
            return (packageManager.getPackageInfo("com.google.android.gms", 16).applicationInfo.flags & 1) != 0;
        } catch (Exception e2) {
            str = "DeviceUtil";
            message = e2.getMessage();
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, message);
            return false;
        }
    }

    public static int f(Context context) {
        try {
            return Integer.parseInt(context.getPackageManager().getPackageInfo(context.getPackageName(), 16).versionCode + "");
        } catch (PackageManager.NameNotFoundException | NumberFormatException unused) {
            return 1;
        }
    }

    public static b f() {
        return a;
    }

    public static String g(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(PackageConstants.SERVICES_PACKAGE_APPMARKET, 16).versionName + "";
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public static String h(Context context) {
        String strG = g(context);
        int i = 0;
        int i2 = 0;
        while (i < strG.length() && i2 < 3) {
            if (strG.charAt(i) == '.') {
                i2++;
            }
            i++;
        }
        return 3 == i2 ? strG.substring(0, i - 1) : strG;
    }

    public static String i(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16).versionName + "";
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private static DisplayMetrics j(Context context) {
        Display defaultDisplay;
        Object systemService = context.getSystemService("window");
        WindowManager windowManager = systemService instanceof WindowManager ? (WindowManager) systemService : null;
        if (windowManager == null || (defaultDisplay = windowManager.getDefaultDisplay()) == null) {
            return null;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics;
    }

    private static List<String> k(Context context) {
        ArrayList arrayList = new ArrayList();
        FeatureInfo[] systemAvailableFeatures = context.getPackageManager().getSystemAvailableFeatures();
        if (systemAvailableFeatures != null) {
            for (FeatureInfo featureInfo : systemAvailableFeatures) {
                if (featureInfo.name != null) {
                    arrayList.add(c.a(featureInfo.name));
                }
            }
        }
        return arrayList;
    }

    public int g() {
        return this.b;
    }

    public int h() {
        return this.c;
    }
}
