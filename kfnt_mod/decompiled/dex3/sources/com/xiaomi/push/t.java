package com.xiaomi.push;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.sigmob.sdk.common.Constants;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class t {
    private static Context a;

    /* renamed from: a, reason: collision with other field name */
    private static String f983a;

    public static int a() {
        try {
            Class<?> clsA = a(null, "miui.os.Build");
            if (clsA.getField("IS_STABLE_VERSION").getBoolean(null)) {
                return 3;
            }
            return clsA.getField("IS_DEVELOPMENT_VERSION").getBoolean(null) ? 2 : 1;
        } catch (Exception unused) {
            return 0;
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public static Context m643a() {
        return a;
    }

    public static Class<?> a(Context context, String str) throws ClassNotFoundException {
        if (str == null || str.trim().length() == 0) {
            throw new ClassNotFoundException("class is empty");
        }
        boolean z = context != null;
        if (z && Build.VERSION.SDK_INT >= 29) {
            try {
                return context.getClassLoader().loadClass(str);
            } catch (ClassNotFoundException unused) {
            }
        }
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            com.xiaomi.channel.commonutils.logger.b.m22a(String.format("loadClass fail hasContext= %s, errMsg = %s", Boolean.valueOf(z), e.getLocalizedMessage()));
            throw new ClassNotFoundException("loadClass fail ", e);
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public static synchronized String m644a() {
        String strB;
        if (f983a != null) {
            return f983a;
        }
        String strValueOf = Build.VERSION.INCREMENTAL;
        if (a() <= 0) {
            strB = b();
            if (TextUtils.isEmpty(strB)) {
                strB = c();
                if (TextUtils.isEmpty(strB)) {
                    strB = d();
                    if (TextUtils.isEmpty(strB)) {
                        strValueOf = String.valueOf(s.a("ro.product.brand", "Android") + "_" + strValueOf);
                        strB = strValueOf;
                    }
                }
            }
        } else {
            strB = strValueOf;
        }
        f983a = strB;
        return strB;
    }

    public static String a(Context context) {
        if (l.m550b()) {
            return "";
        }
        String str = (String) bh.a("com.xiaomi.xmsf.helper.MIIDAccountHelper", "getMIID", context);
        return TextUtils.isEmpty(str) ? Constants.FAIL : str;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static void m645a(Context context) {
        a = context.getApplicationContext();
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m646a() {
        return TextUtils.equals((String) bh.a("android.os.SystemProperties", MonitorConstants.CONNECT_TYPE_GET, "sys.boot_completed"), "1");
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m647a(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            return false;
        }
    }

    private static String b() {
        f983a = s.a("ro.build.version.emui", "");
        return f983a;
    }

    /* renamed from: b, reason: collision with other method in class */
    public static boolean m648b() {
        try {
            return a(null, "miui.os.Build").getField("IS_GLOBAL_BUILD").getBoolean(false);
        } catch (ClassNotFoundException unused) {
            com.xiaomi.channel.commonutils.logger.b.d("miui.os.Build ClassNotFound");
            return false;
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            return false;
        }
    }

    private static String c() {
        String strA = s.a("ro.build.version.opporom", "");
        if (!TextUtils.isEmpty(strA) && !strA.startsWith("ColorOS_")) {
            f983a = "ColorOS_" + strA;
        }
        return f983a;
    }

    private static String d() {
        String strA = s.a("ro.vivo.os.version", "");
        if (!TextUtils.isEmpty(strA) && !strA.startsWith("FuntouchOS_")) {
            f983a = "FuntouchOS_" + strA;
        }
        return f983a;
    }
}
