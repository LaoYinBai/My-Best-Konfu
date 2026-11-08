package com.huawei.a.a.f;

import android.content.Context;
import com.heytap.mcssdk.mode.Message;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    private static final String[] a = {"ABTesting", "_default_config_tag", "_openness_config_tag", "_hms_config_tag"};
    private static a b = null;
    private static final Object c = new Object();
    private Context d;

    private a() {
    }

    public static a a() {
        if (b == null) {
            b();
        }
        return b;
    }

    private static synchronized void b() {
        if (b == null) {
            b = new a();
        }
    }

    public void a(Context context) {
        synchronized (c) {
            if (this.d != null) {
                com.huawei.a.a.b.e.a.c("HiAnalyticsDataManager", "DataManager already initialized.");
                return;
            }
            this.d = context;
            com.huawei.a.a.b.c.a.a.a().c().a(this.d);
            com.huawei.a.a.b.c.a.a.a().c().g(context.getPackageName());
            com.huawei.a.a.c.b.a.a().a(context);
        }
    }

    public void a(String str) {
        if (this.d == null) {
            com.huawei.a.a.b.e.a.c("HiAnalyticsDataManager", "clearDataByTag() sdk is not init");
        } else {
            com.huawei.a.a.b.e.a.b("HiAnalyticsDataManager", "HiAnalyticsDataManager.clearDataByTag(String appid) is execute.");
            com.huawei.a.a.b.i.a.c(this.d, str);
        }
    }

    public void b(String str) {
        com.huawei.a.a.b.e.a.b("hmsSdk", "HiAnalyticsDataManager.setAppid(String appid) is execute.");
        if (this.d == null) {
            com.huawei.a.a.b.e.a.c("hmsSdk", "sdk is not init");
        } else {
            com.huawei.a.a.b.c.a.a.a().c().h(com.huawei.a.a.b.k.c.a(Message.APP_ID, str, "[a-zA-Z0-9_][a-zA-Z0-9. _-]{0,255}", this.d.getPackageName()));
        }
    }
}
