package com.ss.union.game.sdk.push.c;

import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.event.PageStater;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static final String a = "ohayoo_sdk_push";
    public static final String b = "push_request";

    public static void a() {
        HashMap map = new HashMap();
        map.put(b, ConfigManager.AppConfig.appName());
        PageStater.onEvent(a, map);
    }
}
