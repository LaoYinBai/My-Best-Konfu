package com.alipay.apmobilesecuritysdk.e;

import android.content.Context;
import android.content.SharedPreferences;
import com.bytedance.common.utility.date.DateDef;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h {

    /* renamed from: a, reason: collision with root package name */
    private static String f2502a = "";

    public static long a(Context context) {
        String strA = com.alipay.security.mobile.module.c.a.a(context, "vkeyid_settings", "update_time_interval");
        if (!com.alipay.security.mobile.module.a.a.b(strA)) {
            return DateDef.DAY;
        }
        try {
            return Long.parseLong(strA);
        } catch (Exception unused) {
            return DateDef.DAY;
        }
    }

    public static void a(Context context, String str) {
        a(context, "update_time_interval", str);
    }

    public static void a(Context context, String str, long j) {
        com.alipay.security.mobile.module.c.a.a(context, "vkeyid_settings", "vkey_valid" + str, String.valueOf(j));
    }

    private static void a(Context context, String str, String str2) {
        com.alipay.security.mobile.module.c.a.a(context, "vkeyid_settings", str, str2);
    }

    public static void a(Context context, boolean z) {
        a(context, "log_switch", z ? "1" : OnekeyLoginConstants.CU_RESULT_SUCCESS);
    }

    public static String b(Context context) {
        return com.alipay.security.mobile.module.c.a.a(context, "vkeyid_settings", "last_apdid_env");
    }

    public static void b(Context context, String str) {
        a(context, "last_machine_boot_time", str);
    }

    public static void c(Context context, String str) {
        a(context, "last_apdid_env", str);
    }

    public static boolean c(Context context) {
        String strA = com.alipay.security.mobile.module.c.a.a(context, "vkeyid_settings", "log_switch");
        return strA != null && "1".equals(strA);
    }

    public static String d(Context context) {
        return com.alipay.security.mobile.module.c.a.a(context, "vkeyid_settings", "dynamic_key");
    }

    public static void d(Context context, String str) {
        a(context, "agent_switch", str);
    }

    public static String e(Context context) {
        return com.alipay.security.mobile.module.c.a.a(context, "vkeyid_settings", "apse_degrade");
    }

    public static void e(Context context, String str) {
        a(context, "dynamic_key", str);
    }

    public static String f(Context context) {
        String str;
        SharedPreferences.Editor editorEdit;
        synchronized (h.class) {
            if (com.alipay.security.mobile.module.a.a.a(f2502a)) {
                String strA = com.alipay.security.mobile.module.c.e.a(context, "alipay_vkey_random", "random", "");
                f2502a = strA;
                if (com.alipay.security.mobile.module.a.a.a(strA)) {
                    f2502a = com.alipay.security.mobile.module.a.a.b.a(UUID.randomUUID().toString());
                    String str2 = f2502a;
                    if (str2 != null && (editorEdit = context.getSharedPreferences("alipay_vkey_random", 0).edit()) != null) {
                        editorEdit.putString("random", str2);
                        editorEdit.commit();
                    }
                }
            }
            str = f2502a;
        }
        return str;
    }

    public static void f(Context context, String str) {
        a(context, "webrtc_url", str);
    }

    public static void g(Context context, String str) {
        a(context, "apse_degrade", str);
    }

    public static long h(Context context, String str) {
        try {
            String strA = com.alipay.security.mobile.module.c.a.a(context, "vkeyid_settings", "vkey_valid" + str);
            if (com.alipay.security.mobile.module.a.a.a(strA)) {
                return 0L;
            }
            return Long.parseLong(strA);
        } catch (Throwable unused) {
            return 0L;
        }
    }
}
