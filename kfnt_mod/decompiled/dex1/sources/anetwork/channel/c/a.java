package anetwork.channel.c;

import android.content.Context;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anetwork.channel.e.b;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static volatile boolean f678a = false;

    /* renamed from: b, reason: collision with root package name */
    private static CookieManager f679b = null;

    /* renamed from: c, reason: collision with root package name */
    private static boolean f680c = true;

    public static synchronized void a(Context context) {
        if (f678a) {
            return;
        }
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (Build.VERSION.SDK_INT < 21) {
                CookieSyncManager.createInstance(context);
            }
            f679b = CookieManager.getInstance();
            f679b.setAcceptCookie(true);
            if (Build.VERSION.SDK_INT < 21) {
                f679b.removeExpiredCookie();
            }
            ALog.e("anet.CookieManager", "CookieManager setup. cost:" + (System.currentTimeMillis() - jCurrentTimeMillis), null, new Object[0]);
        } catch (Throwable th) {
            f680c = false;
            ALog.e("anet.CookieManager", "Cookie Manager setup failed!!!", null, th, new Object[0]);
        }
        f678a = true;
    }

    private static boolean a() {
        if (!f678a && b.a() != null) {
            a(b.a());
        }
        return f678a;
    }

    public static synchronized void a(String str, String str2) {
        if (a() && f680c) {
            try {
                f679b.setCookie(str, str2);
                if (Build.VERSION.SDK_INT < 21) {
                    CookieSyncManager.getInstance().sync();
                } else {
                    f679b.flush();
                }
            } catch (Throwable th) {
                ALog.e("anet.CookieManager", "set cookie failed. url=" + str + " cookies=" + str2, null, th, new Object[0]);
            }
        }
    }

    public static void a(String str, Map<String, List<String>> map) {
        if (str == null || map == null) {
            return;
        }
        try {
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key != null && (key.equalsIgnoreCase(HttpConstant.SET_COOKIE) || key.equalsIgnoreCase(HttpConstant.SET_COOKIE2))) {
                    Iterator<String> it = entry.getValue().iterator();
                    while (it.hasNext()) {
                        a(str, it.next());
                    }
                }
            }
        } catch (Exception e) {
            ALog.e("anet.CookieManager", "set cookie failed", null, e, "url", str, "\nheaders", map);
        }
    }

    public static synchronized String a(String str) {
        String cookie;
        if (!a() || !f680c) {
            return null;
        }
        try {
            cookie = f679b.getCookie(str);
        } catch (Throwable th) {
            ALog.e("anet.CookieManager", "get cookie failed. url=" + str, null, th, new Object[0]);
            cookie = null;
        }
        return cookie;
    }
}
