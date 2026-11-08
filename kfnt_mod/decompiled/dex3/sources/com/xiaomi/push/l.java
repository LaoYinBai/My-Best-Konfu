package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.tachikoma.core.component.anim.AnimationProperty;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class l {
    private static int a = 0;

    /* renamed from: a, reason: collision with other field name */
    private static Map<String, o> f819a = null;
    private static int b = -1;

    public static synchronized int a() {
        if (a == 0) {
            try {
                int i = 1;
                if (!((TextUtils.isEmpty(m545a("ro.miui.ui.version.code")) && TextUtils.isEmpty(m545a("ro.miui.ui.version.name"))) ? false : true)) {
                    i = 2;
                }
                a = i;
            } catch (Throwable th) {
                com.xiaomi.channel.commonutils.logger.b.a("get isMIUI failed", th);
                a = 0;
            }
            com.xiaomi.channel.commonutils.logger.b.b("isMIUI's value is: " + a);
        }
        return a;
    }

    public static int a(Context context) {
        String strM545a = m545a("ro.miui.ui.version.code");
        if (TextUtils.isEmpty(strM545a) || !TextUtils.isDigitsOnly(strM545a)) {
            return 0;
        }
        return Integer.parseInt(strM545a);
    }

    public static o a(String str) {
        o oVarB = b(str);
        return oVarB == null ? o.Global : oVarB;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static synchronized String m544a() {
        int iA = t.a();
        return (!m547a() || iA <= 0) ? "" : iA < 2 ? AnimationProperty.OPACITY : iA < 3 ? "development" : "stable";
    }

    /* renamed from: a, reason: collision with other method in class */
    public static String m545a(String str) {
        try {
            try {
                return (String) bh.a("android.os.SystemProperties", MonitorConstants.CONNECT_TYPE_GET, str, "");
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.d("fail to get property. " + e);
                return null;
            }
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    private static void m546a() {
        if (f819a != null) {
            return;
        }
        f819a = new HashMap();
        f819a.put("CN", o.China);
        f819a.put("FI", o.Europe);
        f819a.put("SE", o.Europe);
        f819a.put("NO", o.Europe);
        f819a.put("FO", o.Europe);
        f819a.put("EE", o.Europe);
        f819a.put("LV", o.Europe);
        f819a.put("LT", o.Europe);
        f819a.put("BY", o.Europe);
        f819a.put("MD", o.Europe);
        f819a.put("UA", o.Europe);
        f819a.put("PL", o.Europe);
        f819a.put("CZ", o.Europe);
        f819a.put("SK", o.Europe);
        f819a.put("HU", o.Europe);
        f819a.put("DE", o.Europe);
        f819a.put("AT", o.Europe);
        f819a.put("CH", o.Europe);
        f819a.put("LI", o.Europe);
        f819a.put("GB", o.Europe);
        f819a.put("IE", o.Europe);
        f819a.put("NL", o.Europe);
        f819a.put("BE", o.Europe);
        f819a.put("LU", o.Europe);
        f819a.put("FR", o.Europe);
        f819a.put("RO", o.Europe);
        f819a.put("BG", o.Europe);
        f819a.put("RS", o.Europe);
        f819a.put("MK", o.Europe);
        f819a.put("AL", o.Europe);
        f819a.put("GR", o.Europe);
        f819a.put("SI", o.Europe);
        f819a.put("HR", o.Europe);
        f819a.put("IT", o.Europe);
        f819a.put("SM", o.Europe);
        f819a.put("MT", o.Europe);
        f819a.put("ES", o.Europe);
        f819a.put("PT", o.Europe);
        f819a.put("AD", o.Europe);
        f819a.put("CY", o.Europe);
        f819a.put("DK", o.Europe);
        f819a.put("RU", o.Russia);
        f819a.put("IN", o.India);
    }

    /* renamed from: a, reason: collision with other method in class */
    public static synchronized boolean m547a() {
        return a() == 1;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m548a(Context context) {
        return context != null && m549a(context.getPackageName());
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m549a(String str) {
        return "com.xiaomi.xmsf".equals(str);
    }

    private static o b(String str) {
        m546a();
        return f819a.get(str.toUpperCase());
    }

    public static String b() {
        String strA = s.a("ro.miui.region", "");
        if (TextUtils.isEmpty(strA)) {
            strA = s.a("persist.sys.oppo.region", "");
        }
        if (TextUtils.isEmpty(strA)) {
            strA = s.a("ro.oppo.regionmark", "");
        }
        if (TextUtils.isEmpty(strA)) {
            strA = s.a("ro.hw.country", "");
        }
        if (TextUtils.isEmpty(strA)) {
            strA = s.a("ro.csc.countryiso_code", "");
        }
        if (TextUtils.isEmpty(strA)) {
            strA = s.a("ro.product.country.region", "");
        }
        if (TextUtils.isEmpty(strA)) {
            strA = s.a("gsm.vivo.countrycode", "");
        }
        if (TextUtils.isEmpty(strA)) {
            strA = s.a("persist.sys.oem.region", "");
        }
        if (TextUtils.isEmpty(strA)) {
            strA = s.a("ro.product.locale.region", "");
        }
        if (TextUtils.isEmpty(strA)) {
            strA = s.a("persist.sys.country", "");
        }
        if (!TextUtils.isEmpty(strA)) {
            com.xiaomi.channel.commonutils.logger.b.m22a("get region from system, region = " + strA);
        }
        if (!TextUtils.isEmpty(strA)) {
            return strA;
        }
        String country = Locale.getDefault().getCountry();
        com.xiaomi.channel.commonutils.logger.b.m22a("locale.default.country = " + country);
        return country;
    }

    /* renamed from: b, reason: collision with other method in class */
    public static synchronized boolean m550b() {
        return a() == 2;
    }

    public static boolean c() {
        if (b < 0) {
            Object objA = bh.a("miui.external.SdkHelper", "isMiuiSystem", new Object[0]);
            b = 0;
            if (objA != null && (objA instanceof Boolean) && !((Boolean) Boolean.class.cast(objA)).booleanValue()) {
                b = 1;
            }
        }
        return b > 0;
    }

    public static boolean d() {
        return !o.China.name().equalsIgnoreCase(a(b()).name());
    }
}
