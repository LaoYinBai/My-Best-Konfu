package com.huawei.a.a.b.j.a;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.a.a.a.b;
import com.huawei.a.a.a.c;
import com.huawei.a.a.b.k.e;
import com.huawei.a.a.b.k.g;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static a a;
    private static Map<String, Long> c = new HashMap();
    private Context b;

    public static a a() {
        return b();
    }

    private static synchronized a b() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    private void b(Context context) {
        String str;
        String str2;
        String strB = com.huawei.a.a.b.b.a.b(context);
        b.b(strB);
        if (g.a().b()) {
            String strB2 = com.huawei.a.a.b.i.a.b(context, "global_v2", "app_ver", "");
            com.huawei.a.a.b.i.a.a(context, "global_v2", "app_ver", strB);
            b.c(strB2);
            if (!TextUtils.isEmpty(strB2)) {
                if (strB2.equals(strB)) {
                    return;
                }
                com.huawei.a.a.b.e.a.b("HiAnalyticsEventServer", "the appVers are different!");
                a().a("", "alltype", strB2);
                return;
            }
            str = "HiAnalyticsEventServer";
            str2 = "app ver is first save!";
        } else {
            str = "HiAnalyticsEventServer";
            str2 = "userManager.isUserUnlocked() == false";
        }
        com.huawei.a.a.b.e.a.b(str, str2);
    }

    public void a(Context context) {
        this.b = context;
        b(context);
        com.huawei.a.a.b.c.a.a.a().c().c(com.huawei.a.a.b.b.a.a());
    }

    public void a(String str, int i) {
        if (this.b == null) {
            com.huawei.a.a.b.e.a.c("HiAnalyticsEventServer", "onReport() null context or SDK was not init.");
        } else {
            com.huawei.a.a.b.e.a.b("hmsSdk", "onReport: Before calling runtaskhandler()");
            a(str, e.a(i), b.f());
        }
    }

    public void a(String str, int i, String str2, JSONObject jSONObject) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (2 == i) {
            jCurrentTimeMillis = e.a("yyyy-MM-dd", jCurrentTimeMillis);
        }
        com.huawei.a.a.b.j.c.b.a().a(new com.huawei.a.a.b.j.b.b(str2, jSONObject, str, e.a(i), jCurrentTimeMillis));
    }

    public void a(String str, String str2) {
        if (!c.i(str, str2)) {
            com.huawei.a.a.b.e.a.b("HiAnalyticsEventServer", "auto report is closed tag:" + str);
            return;
        }
        long jN = c.n(str, str2);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - jN <= 30000) {
            com.huawei.a.a.b.e.a.c("HiAnalyticsEventServer", "autoReport timeout. interval < 30s ");
            return;
        }
        com.huawei.a.a.b.e.a.a("HiAnalyticsEventServer", "begin to call onReport!");
        c.a(str, str2, jCurrentTimeMillis);
        a(str, str2, b.f());
    }

    public void a(String str, String str2, String str3) {
        if (c.j(str, str2)) {
            String strA = com.huawei.a.a.b.b.c.a(this.b);
            if (!"WIFI".equals(strA)) {
                com.huawei.a.a.b.e.a.b("HiAnalyticsEventServer", "strNetworkType is :" + strA);
                return;
            }
        }
        com.huawei.a.a.b.j.c.b.a().a(new com.huawei.a.a.b.j.b.c(str, str2, str3));
    }
}
