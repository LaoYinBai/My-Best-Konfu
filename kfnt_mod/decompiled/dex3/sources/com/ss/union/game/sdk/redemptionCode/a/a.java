package com.ss.union.game.sdk.redemptionCode.a;

import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.event.PageStater;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static final String a = "code_request";
    public static final String b = "code_result";
    public static final String c = "code_success";
    public static final String d = "code_fail";
    public static final String e = "receive_fail";
    public static final String f = "code_error";
    private static final String g = "ohayoo_sdk_code";

    public static void a() {
        String strAppName = ConfigManager.AppConfig.appName();
        HashMap map = new HashMap();
        map.put(a, strAppName);
        PageStater.onEvent(g, map);
    }

    public static void a(int i) {
        HashMap map = new HashMap();
        map.put(b, d);
        map.put(f, i + "");
        PageStater.onEvent(g, map);
    }

    public static void b() {
        HashMap map = new HashMap();
        map.put(b, c);
        PageStater.onEvent(g, map);
    }

    public static void b(int i) {
        HashMap map = new HashMap();
        map.put(b, e);
        map.put(f, i + "");
        PageStater.onEvent(g, map);
    }
}
