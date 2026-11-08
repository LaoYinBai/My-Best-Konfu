package com.huawei.updatesdk.service.e;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hms.common.PackageConstants;
import java.io.File;
import java.lang.reflect.Field;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private static Integer a = null;
    private static boolean b = false;
    private static boolean c = false;
    private static Field d;

    public enum a {
        NOT_INSTALLED,
        INSTALLED,
        INSTALLED_LOWCODE
    }

    public static PackageInfo a(String str, Context context) {
        try {
            return context.getPackageManager().getPackageInfo(str, 64);
        } catch (PackageManager.NameNotFoundException unused) {
            com.huawei.updatesdk.a.a.b.a.a.a.d("PackageUtils", "not found: " + str);
            return null;
        }
    }

    public static a a(Context context) throws PackageManager.NameNotFoundException {
        String str;
        StringBuilder sb;
        String string;
        a aVar = a.NOT_INSTALLED;
        if (context == null) {
            return aVar;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(PackageConstants.SERVICES_PACKAGE_APPMARKET, 0);
            if (packageInfo == null) {
                return aVar;
            }
            Log.i("PackageUtils", "appmarket info,versioncode = " + packageInfo.versionCode + "versioncode = " + packageInfo.versionName);
            return packageInfo.versionCode < 70203000 ? a.INSTALLED_LOWCODE : a.INSTALLED;
        } catch (PackageManager.NameNotFoundException e) {
            str = "PackageUtils";
            sb = new StringBuilder();
            sb.append("isInstallByPackage NameNotFoundException:");
            string = e.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.b(str, sb.toString());
            return aVar;
        } catch (UnsupportedOperationException e2) {
            str = "PackageUtils";
            sb = new StringBuilder();
            sb.append("isInstallByPackage UnsupportedOperationException:");
            string = e2.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.b(str, sb.toString());
            return aVar;
        }
    }

    public static Integer a() throws ClassNotFoundException {
        String str;
        String string;
        StringBuilder sb;
        String string2;
        if (b) {
            return a;
        }
        try {
            Class<?> cls = Class.forName("android.content.pm.PackageParser");
            a = Integer.valueOf(cls.getDeclaredField("PARSE_IS_REMOVABLE_PREINSTALLED_APK").getInt(cls));
        } catch (ClassNotFoundException e) {
            str = "PackageUtils";
            sb = new StringBuilder();
            sb.append("isDelApp error ClassNotFoundException:");
            string2 = e.toString();
            sb.append(string2);
            string = sb.toString();
            com.huawei.updatesdk.a.a.b.a.a.a.a(str, string);
            b = true;
            return a;
        } catch (IllegalAccessException e2) {
            str = "PackageUtils";
            sb = new StringBuilder();
            sb.append("isDelApp error IllegalAccessException:");
            string2 = e2.toString();
            sb.append(string2);
            string = sb.toString();
            com.huawei.updatesdk.a.a.b.a.a.a.a(str, string);
            b = true;
            return a;
        } catch (IllegalArgumentException e3) {
            str = "PackageUtils";
            sb = new StringBuilder();
            sb.append("isDelApp error IllegalArgumentException:");
            string2 = e3.toString();
            sb.append(string2);
            string = sb.toString();
            com.huawei.updatesdk.a.a.b.a.a.a.a(str, string);
            b = true;
            return a;
        } catch (NoSuchFieldException e4) {
            str = "PackageUtils";
            sb = new StringBuilder();
            sb.append("isDelApp error NoSuchFieldException:");
            string2 = e4.toString();
            sb.append(string2);
            string = sb.toString();
            com.huawei.updatesdk.a.a.b.a.a.a.a(str, string);
            b = true;
            return a;
        } catch (Exception e5) {
            str = "PackageUtils";
            string = e5.toString();
            com.huawei.updatesdk.a.a.b.a.a.a.a(str, string);
            b = true;
            return a;
        }
        b = true;
        return a;
    }

    public static boolean a(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                return context.getPackageManager().getPackageInfo(str, 0) != null;
            } catch (Exception e) {
                com.huawei.updatesdk.a.a.b.a.a.a.b("PackageUtils", "isAppInstalled NameNotFoundException:" + e.toString());
            }
        }
        return false;
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!Pattern.compile("(\\.)+[\\\\/]+").matcher(str).find()) {
            return new File(str).delete();
        }
        com.huawei.updatesdk.a.a.b.a.a.a.a("PackageUtils", "remov APK fail. the apk path is not valid");
        return false;
    }

    public static int b(String str) throws PackageManager.NameNotFoundException {
        try {
            PackageInfo packageInfo = com.huawei.updatesdk.a.b.a.a.a().b().getPackageManager().getPackageInfo(str, 128);
            return (packageInfo == null || packageInfo.applicationInfo == null || packageInfo.applicationInfo.metaData == null || !packageInfo.applicationInfo.metaData.containsKey("com.huawei.maple.flag")) ? 0 : 1;
        } catch (PackageManager.NameNotFoundException unused) {
            com.huawei.updatesdk.a.a.b.a.a.a.d("PackageUtils", "getMapleStatus not found: " + str);
            return 0;
        }
    }

    public static Field b() {
        if (c) {
            return d;
        }
        try {
            d = ApplicationInfo.class.getField("hwFlags");
        } catch (NoSuchFieldException unused) {
            com.huawei.updatesdk.a.a.b.a.a.a.a("PackageUtils", "can not find hwFlags");
        }
        c = true;
        return d;
    }

    public static boolean b(Context context) {
        return d(context) > 90000000;
    }

    public static boolean c() {
        return Build.VERSION.SDK_INT >= 23 && new ContextWrapper(com.huawei.updatesdk.a.b.a.a.a().b()).checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    public static boolean c(Context context) {
        return d(context) > 100200000;
    }

    private static int d(Context context) {
        String str;
        StringBuilder sb;
        String string;
        try {
            return context.getPackageManager().getPackageInfo(PackageConstants.SERVICES_PACKAGE_APPMARKET, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            str = "PackageUtils";
            sb = new StringBuilder();
            sb.append("isInstallByPackage NameNotFoundException:");
            string = e.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.b(str, sb.toString());
            return 0;
        } catch (UnsupportedOperationException e2) {
            str = "PackageUtils";
            sb = new StringBuilder();
            sb.append("isInstallByPackage UnsupportedOperationException:");
            string = e2.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.b(str, sb.toString());
            return 0;
        }
    }
}
