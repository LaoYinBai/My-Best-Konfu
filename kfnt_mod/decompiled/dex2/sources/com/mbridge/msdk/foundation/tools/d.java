package com.mbridge.msdk.foundation.tools;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.SensorManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.optimize.SensitiveDataUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: DomainSameDiTool.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    private static String a = "";
    private static String b = "";
    private static int c = 0;
    private static String d = "";
    private static String e = "";
    private static String f = "";
    private static String g = "";
    private static String h = "";
    private static String i = "";
    private static String j;
    private static String k;

    public static int a() {
        return 0;
    }

    public static String a(Context context) {
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_DEVICE_ID) || context == null) {
            return "";
        }
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        try {
            a = SensitiveDataUtil.getOaid(context);
            if (!TextUtils.isEmpty(a)) {
                return a;
            }
        } catch (Throwable th) {
            s.a("DomainSameDiTool", th.getMessage(), th);
        }
        return a;
    }

    public static String b(Context context) {
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a("authority_imei_mac") || context == null) {
            return "";
        }
        try {
            return SensitiveDataUtil.getIMEI(context);
        } catch (Throwable th) {
            s.a("DomainSameDiTool", th.getMessage(), th);
            return "";
        }
    }

    public static String c(Context context) {
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a("authority_imei_mac") || context == null) {
            return "";
        }
        try {
            return SensitiveDataUtil.getImsi(context);
        } catch (Throwable th) {
            s.a("DomainSameDiTool", th.getMessage(), th);
            return "";
        }
    }

    public static String d(Context context) {
        return h;
    }

    public static String e(Context context) {
        return i;
    }

    public static String f(Context context) {
        if (context == null) {
            return d;
        }
        try {
            if (TextUtils.isEmpty(d)) {
                d = n.a(SensitiveDataUtil.getAndroidID(context));
            }
        } catch (Throwable th) {
            s.a("DomainSameDiTool", th.getMessage(), th);
        }
        return d;
    }

    public static String g(Context context) {
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a("authority_android_id")) {
            return "";
        }
        if (context == null) {
            return j;
        }
        try {
            if (TextUtils.isEmpty(j)) {
                j = SensitiveDataUtil.getAndroidID(context);
            }
        } catch (Throwable th) {
            s.a("DomainSameDiTool", th.getMessage(), th);
        }
        return j;
    }

    public static String h(Context context) {
        if (context == null) {
            return "";
        }
        k = null;
        try {
            if (TextUtils.isEmpty(k)) {
                k = g(context);
                k = SameMD5.getUPMD5(k);
            }
        } catch (Throwable th) {
            s.b("DomainSameDiTool", th.getMessage(), th);
        }
        return k;
    }

    public static String i(Context context) {
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a("authority_imei_mac")) {
            return "";
        }
        if (context == null) {
            return e;
        }
        try {
            if (TextUtils.isEmpty(e)) {
                e = SensitiveDataUtil.getMacAddress(context);
            }
        } catch (Throwable th) {
            s.a("DomainSameDiTool", th.getMessage(), th);
        }
        return e;
    }

    public static String j(Context context) {
        if (context == null) {
            return "";
        }
        if (TextUtils.isEmpty(f)) {
            try {
                f = context.getPackageManager().getPackageInfo("com.android.vending", 0).versionName;
                return f;
            } catch (Exception unused) {
                return "";
            }
        }
        return f;
    }

    public static String b() {
        return !com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) ? "" : Build.CPU_ABI2;
    }

    public static String c() {
        return !com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) ? "" : Build.CPU_ABI;
    }

    public static String d() {
        return !com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) ? "" : Build.TAGS;
    }

    public static String e() {
        return !com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) ? "" : Build.USER;
    }

    public static String f() {
        return !com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) ? "" : Build.RADIO;
    }

    public static String g() {
        return !com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) ? "" : Build.BOOTLOADER;
    }

    public static String h() {
        return !com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) ? "" : Build.HARDWARE;
    }

    public static String i() {
        return !com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) ? "" : Build.HOST;
    }

    public static String j() {
        return !com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) ? "" : Build.VERSION.CODENAME;
    }

    public static String k() {
        return !com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) ? "" : Build.VERSION.INCREMENTAL;
    }

    public static String l() {
        return !com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_SERIAL_ID) ? "" : Build.SERIAL;
    }

    public static String m() {
        return !com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) ? "" : Build.DISPLAY;
    }

    public static String n() {
        return !com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) ? "" : Build.BOARD;
    }

    public static String o() {
        return !com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) ? "" : Build.TYPE;
    }

    public static String p() {
        return !com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) ? "" : Build.VERSION.RELEASE;
    }

    public static int q() {
        if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
            return Build.VERSION.SDK_INT;
        }
        return -1;
    }

    public static int r() {
        return !com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) ? -1 : 1;
    }

    public static int k(Context context) {
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) || context == null) {
            return -1;
        }
        try {
            Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (intentRegisterReceiver == null || intentRegisterReceiver.getExtras() == null) {
                return 0;
            }
            int intExtra = intentRegisterReceiver.getIntExtra("status", -1);
            return (intExtra == 2 || intExtra == 5) ? 1 : 0;
        } catch (Exception e2) {
            s.b("DomainSameDiTool", e2.getMessage(), e2);
            return 0;
        }
    }

    public static String l(Context context) {
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
            return "";
        }
        double dDoubleValue = 0.0d;
        if (context != null) {
            return "";
        }
        try {
            dDoubleValue = ((Double) Class.forName("com.android.internal.os.PowerProfile").getMethod("getBatteryCapacity", new Class[0]).invoke(Class.forName("com.android.internal.os.PowerProfile").getConstructor(Context.class).newInstance(context), new Object[0])).doubleValue();
        } catch (Exception e2) {
            s.b("DomainSameDiTool", e2.getMessage(), e2);
        } catch (Throwable th) {
            s.b("DomainSameDiTool", th.getMessage(), th);
        }
        return String.valueOf(dDoubleValue + " mAh");
    }

    public static int m(Context context) {
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) || context == null) {
            return -1;
        }
        try {
            String simOperator = ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
            if (!"46000".equals(simOperator) && !"46002".equals(simOperator) && !"46007".equals(simOperator) && !"46008".equals(simOperator) && !"45412".equals(simOperator)) {
                if (!"46001".equals(simOperator) && !"46006".equals(simOperator) && !"46009".equals(simOperator)) {
                    if (!"46003".equals(simOperator) && !"46005".equals(simOperator) && !"46011".equals(simOperator) && !"45502".equals(simOperator)) {
                        if (!"45507".equals(simOperator)) {
                            return -2;
                        }
                    }
                    return 2;
                }
                return 1;
            }
            return 0;
        } catch (Exception e2) {
            s.b("DomainSameDiTool", e2.getMessage(), e2);
            return -1;
        }
    }

    public static int n(Context context) {
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) || context == null) {
            return -1;
        }
        try {
            return ((SensorManager) context.getSystemService("sensor")).getSensorList(-1).size();
        } catch (Exception e2) {
            s.b("DomainSameDiTool", e2.getMessage(), e2);
            return -1;
        } catch (Throwable th) {
            s.b("DomainSameDiTool", th.getMessage(), th);
            return -1;
        }
    }

    public static String o(Context context) {
        if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) || context == null) {
            return "";
        }
        try {
            List<InputMethodInfo> inputMethodList = ((InputMethodManager) context.getSystemService("input_method")).getInputMethodList();
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < inputMethodList.size(); i2++) {
                sb.append("keybroad" + i2 + ((Object) inputMethodList.get(i2).loadLabel(context.getPackageManager())) + " ");
            }
            return sb.toString();
        } catch (Exception e2) {
            s.b("DomainSameDiTool", e2.getMessage(), e2);
            return "";
        } catch (Throwable th) {
            s.b("DomainSameDiTool", th.getMessage(), th);
            return "";
        }
    }

    public static String s() {
        if (TextUtils.isEmpty(b)) {
            if (Build.VERSION.SDK_INT >= 21) {
                b = (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA) || Build.VERSION.SDK_INT < 21) ? "" : Arrays.asList(Build.SUPPORTED_ABIS).toString();
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(c());
                arrayList.add(b());
                b = arrayList.toString();
            }
        }
        return b;
    }

    protected static void p(Context context) {
        if (context == null) {
            return;
        }
        try {
            Class<?> cls = Class.forName("com.mbridge.msdk.mbjscommon.webEnvCheck.WebEnvCheckEntry");
            cls.getMethod("check", Context.class).invoke(cls.newInstance(), context);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
