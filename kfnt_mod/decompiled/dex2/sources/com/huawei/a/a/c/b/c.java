package com.huawei.a.a.c.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.Pair;
import com.kwad.v8.Platform;
import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends com.huawei.a.a.b.b.a {
    public static String c() {
        String str;
        String str2;
        String str3;
        try {
            str = (String) Class.forName("com.huawei.android.os.BuildEx").getMethod("getUDID", new Class[0]).invoke(null, new Object[0]);
            try {
                com.huawei.a.a.b.e.a.b("hmsSdk", "getUDID success");
            } catch (AndroidRuntimeException unused) {
                str2 = "hmsSdk";
                str3 = "getUDID getudid failed, RuntimeException is AndroidRuntimeException";
                com.huawei.a.a.b.e.a.c(str2, str3);
                return str;
            } catch (ClassNotFoundException unused2) {
                str2 = "hmsSdk";
                str3 = "getUDID method invoke failed";
                com.huawei.a.a.b.e.a.c(str2, str3);
                return str;
            } catch (IllegalAccessException unused3) {
                str2 = "hmsSdk";
                str3 = "getUDID method invoke failed : Illegal AccessException";
                com.huawei.a.a.b.e.a.c(str2, str3);
                return str;
            } catch (IllegalArgumentException unused4) {
                str2 = "hmsSdk";
                str3 = "getUDID method invoke failed : Illegal ArgumentException";
                com.huawei.a.a.b.e.a.c(str2, str3);
                return str;
            } catch (NoSuchMethodException unused5) {
                str2 = "hmsSdk";
                str3 = "getUDID method invoke failed : NoSuchMethodException";
                com.huawei.a.a.b.e.a.c(str2, str3);
                return str;
            } catch (InvocationTargetException unused6) {
                str2 = "hmsSdk";
                str3 = "getUDID method invoke failed : InvocationTargetException";
                com.huawei.a.a.b.e.a.c(str2, str3);
                return str;
            }
        } catch (AndroidRuntimeException unused7) {
            str = "";
        } catch (ClassNotFoundException unused8) {
            str = "";
        } catch (IllegalAccessException unused9) {
            str = "";
        } catch (IllegalArgumentException unused10) {
            str = "";
        } catch (NoSuchMethodException unused11) {
            str = "";
        } catch (InvocationTargetException unused12) {
            str = "";
        }
        return str;
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    public static String e(Context context) {
        if (com.huawei.a.a.b.k.b.a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager != null ? telephonyManager.getDeviceId() : "";
        } catch (SecurityException unused) {
            com.huawei.a.a.b.e.a.d("hmsSdk", "getDeviceID Incorrect permissions!");
            return "";
        }
    }

    public static String f(Context context) {
        String str = Build.SERIAL;
        return (TextUtils.isEmpty(str) || str.equalsIgnoreCase(Platform.UNKNOWN)) ? h(context) : str;
    }

    public static Pair<String, String> g(Context context) {
        if (com.huawei.a.a.b.k.b.a(context, "android.permission.READ_PHONE_STATE")) {
            com.huawei.a.a.b.e.a.c("hmsSdk", "getMccAndMnc() Pair value is empty");
            return new Pair<>("", "");
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null && telephonyManager.getSimState() == 5) {
            String networkOperator = telephonyManager.getNetworkOperator();
            return (TextUtils.isEmpty(networkOperator) || TextUtils.equals(networkOperator, "null")) ? new Pair<>("", "") : networkOperator.length() > 3 ? new Pair<>(networkOperator.substring(0, 3), networkOperator.substring(3)) : new Pair<>("", "");
        }
        return new Pair<>("", "");
    }

    private static String h(Context context) {
        com.huawei.a.a.b.e.a.a("hmsSdk", "getSerial : is executed.");
        if (context == null || com.huawei.a.a.b.k.b.a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        try {
            return Build.VERSION.SDK_INT >= 26 ? Build.getSerial() : "";
        } catch (SecurityException unused) {
            com.huawei.a.a.b.e.a.c("hmsSdk", "getSerial() Incorrect permissions!");
            return "";
        }
    }
}
