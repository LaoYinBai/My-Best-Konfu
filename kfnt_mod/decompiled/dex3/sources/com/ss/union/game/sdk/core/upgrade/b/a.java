package com.ss.union.game.sdk.core.upgrade.b;

import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.event.PageStater;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    static final String a = "ohayoo_sdk_update";

    public static void a() {
        HashMap map = new HashMap();
        map.put("update_request", ConfigManager.AppConfig.appName());
        PageStater.onEvent(a, map);
    }

    public static void a(boolean z) {
        HashMap map = new HashMap();
        map.put("update_show", z ? "forced_show" : "non_forced_show");
        PageStater.onEvent(a, map);
    }

    public static void b() {
        HashMap map = new HashMap();
        map.put("update_click", "forced_click");
        PageStater.onEvent(a, map);
    }

    public static void c() {
        HashMap map = new HashMap();
        map.put("update_click", "non_forced_click_yes");
        PageStater.onEvent(a, map);
    }

    public static void d() {
        HashMap map = new HashMap();
        map.put("update_click", "non_forced_click_no");
        PageStater.onEvent(a, map);
    }
}
