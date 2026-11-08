package com.huawei.a.a.b.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a {

    /* renamed from: com.huawei.a.a.b.b.a$a, reason: collision with other inner class name */
    private static class C0002a extends Exception {
        C0002a(String str) {
            super(str);
        }
    }

    private static Object a(Class cls, String str, Class[] clsArr, Object[] objArr) throws C0002a {
        String str2;
        String str3;
        if (cls == null) {
            throw new C0002a("class is null in invokeStaticFun");
        }
        if (clsArr == null) {
            if (objArr != null) {
                throw new C0002a("paramsType is null, but params is not null");
            }
        } else {
            if (objArr == null) {
                throw new C0002a("paramsType or params should be same");
            }
            if (clsArr.length != objArr.length) {
                throw new C0002a("paramsType len:" + clsArr.length + " should equal params.len:" + objArr.length);
            }
        }
        try {
        } catch (NoSuchMethodException unused) {
            com.huawei.a.a.b.e.a.c("hmsSdk", "invokeStaticFun(): cls.getMethod(),No Such Method !");
        }
        try {
            return cls.getMethod(str, clsArr).invoke(null, objArr);
        } catch (IllegalAccessException unused2) {
            str2 = "hmsSdk";
            str3 = "invokeStaticFun(): method invoke Exception!";
            com.huawei.a.a.b.e.a.c(str2, str3);
            return null;
        } catch (IllegalArgumentException unused3) {
            str2 = "hmsSdk";
            str3 = "invokeStaticFun(): Illegal Argument!";
            com.huawei.a.a.b.e.a.c(str2, str3);
            return null;
        } catch (InvocationTargetException unused4) {
            str2 = "hmsSdk";
            str3 = "invokeStaticFun(): Invocation Target Exception!";
            com.huawei.a.a.b.e.a.c(str2, str3);
            return null;
        }
    }

    private static Object a(String str, String str2, Class[] clsArr, Object[] objArr) {
        String str3;
        String str4;
        try {
            return a(Class.forName(str), str2, clsArr, objArr);
        } catch (C0002a unused) {
            str3 = "hmsSdk";
            str4 = "invokeStaticFun(): Static function call Exception ";
            com.huawei.a.a.b.e.a.c(str3, str4);
            return null;
        } catch (ClassNotFoundException unused2) {
            str3 = "hmsSdk";
            str4 = "invokeStaticFun() Not found class!";
            com.huawei.a.a.b.e.a.c(str3, str4);
            return null;
        }
    }

    public static String a() {
        return com.huawei.a.a.c.b.c.a("ro.build.version.emui", "");
    }

    public static String a(Context context) {
        return context == null ? "" : context.getPackageName();
    }

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        String strA = a("android.os.SystemProperties", str, str2);
        return TextUtils.isEmpty(strA) ? a("com.huawei.android.os.SystemPropertiesEx", str, str2) : strA;
    }

    private static String a(String str, String str2, String str3) {
        Object objA = a(str, "get", new Class[]{String.class, String.class}, new Object[]{str2, str3});
        return objA != null ? (String) objA : str3;
    }

    public static String b() {
        String strA = a("com.huawei.android.os.SystemPropertiesEx", "ro.huawei.build.display.id", "");
        com.huawei.a.a.b.e.a.b("hmsSdk", "SystemPropertiesEx: get rom_ver: " + strA);
        if (!TextUtils.isEmpty(strA)) {
            return strA;
        }
        String str = Build.DISPLAY;
        com.huawei.a.a.b.e.a.b("hmsSdk", "SystemProperties: get rom_ver: " + str);
        return str;
    }

    public static String b(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(a(context), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            com.huawei.a.a.b.e.a.c("hmsSdk", "getVersion(): The package name is not correct!");
            return "";
        }
    }

    @SuppressLint({"HardwareIds"})
    public static String c(Context context) {
        return context == null ? "" : Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    public static String d(Context context) throws PackageManager.NameNotFoundException {
        Object obj;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null || (obj = applicationInfo.metaData.get("CHANNEL")) == null) {
                return "Unknown";
            }
            String string = obj.toString();
            if (string.length() > 256) {
                string = "Unknown";
            }
            return string;
        } catch (PackageManager.NameNotFoundException unused) {
            com.huawei.a.a.b.e.a.c("hmsSdk", "getChannel(): The packageName is not correct!");
            return "Unknown";
        }
    }
}
