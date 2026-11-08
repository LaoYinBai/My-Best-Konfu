package com.ss.union.game.sdk.core.deviceInfo.b;

import com.ss.union.game.sdk.common.util.DeviceUtils;
import com.ss.union.game.sdk.core.base.event.PageStater;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static final String a = "ohayoo_sdk_logoff";

    public static void a() {
        HashMap map = new HashMap();
        map.put("logoff_request", DeviceUtils.getAppPackageName());
        map.put("logoff_type", "guest_logoff");
        PageStater.onEvent(a, map);
    }

    public static void b() {
        a("logoff_success");
    }

    public static void c() {
        a("logoff_fail");
    }

    private static void a(String str) {
        HashMap map = new HashMap();
        map.put("logoff_result", str);
        map.put("logoff_type", "guest_logoff");
        PageStater.onEvent(a, map);
    }
}
