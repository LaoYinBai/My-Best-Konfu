package com.ss.union.game.sdk.core.a;

import android.content.Context;
import android.text.TextUtils;
import com.apm.insight.MonitorCrash;
import com.ss.union.game.sdk.common.util.DeviceUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    static final String a = "com.ss.union.game.sdk";
    private static boolean b;
    private static MonitorCrash c;
    private static MonitorCrash d;
    private static Map<String, String> e = new HashMap();

    public static synchronized void a(Context context, String str, String str2, int i, String str3, String str4, Map<String, String> map) {
        if (!b || c == null || d == null) {
            b = true;
            c = MonitorCrash.init(context.getApplicationContext(), str, DeviceUtils.getVersionCode(), DeviceUtils.getVersionName());
            if (c != null && c.config() != null) {
                c.config().setChannel(str4);
            }
            d = MonitorCrash.initSDK(context.getApplicationContext(), str2, i, str3, a).addTags("host_appid", str);
            d.config().setChannel(str4);
            if (map != null && map.size() > 0) {
                for (String str5 : map.keySet()) {
                    c.addTags(str5, map.get(str5));
                    d.addTags(str5, map.get(str5));
                }
            }
            if (e != null && e.size() > 0) {
                for (Map.Entry<String, String> entry : e.entrySet()) {
                    c.addTags(entry.getKey(), entry.getValue());
                    d.addTags(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    private static void a(String str, String str2, Throwable th) {
        if (c != null) {
            c.reportCustomErr(str, str2, th);
        }
    }

    private static void b(String str, String str2, Throwable th) {
        if (d != null) {
            d.reportCustomErr(str, str2, th);
        }
    }

    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        a("customCrash", str, new RuntimeException(str2));
        if (str2.contains(a)) {
            b("customCrash", str, new RuntimeException(str2));
        }
    }

    public static synchronized void b(String str, String str2) {
        if (b && c != null && d != null) {
            d.addTags(str, str2);
            c.addTags(str, str2);
        } else {
            e.put(str, str2);
        }
    }
}
