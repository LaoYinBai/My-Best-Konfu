package com.ss.union.game.sdk.core.init.a;

import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.event.PageStater;
import com.ss.union.game.sdk.core.realName.b.a;
import com.ss.union.game.sdk.core.realName.event.RealNameLogEvent;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static final String a = "ohayoo_sdk_initialization";

    public static void a() {
        b();
    }

    private static void b() {
        if (ConfigManager.LoginConfig.isNoUserLogin()) {
            a(a.C0087a.a(), a.C0087a.b());
        } else {
            a(LGAccountDataUtil.isIdentifyValidated(), LGAccountDataUtil.isAdult());
        }
    }

    private static void a(boolean z, boolean z2) {
        if (!z) {
            e();
        } else if (z2) {
            c();
        } else {
            d();
        }
    }

    private static void c() {
        a(RealNameLogEvent.REAL_NAME_EVENT_VALUE_ADULT);
    }

    private static void d() {
        a(RealNameLogEvent.REAL_NAME_EVENT_VALUE_MINOR);
    }

    private static void e() {
        a("unnamed");
    }

    private static void a(String str) {
        HashMap map = new HashMap();
        map.put("initialization_realname_status", str);
        PageStater.onEvent(a, map);
    }
}
