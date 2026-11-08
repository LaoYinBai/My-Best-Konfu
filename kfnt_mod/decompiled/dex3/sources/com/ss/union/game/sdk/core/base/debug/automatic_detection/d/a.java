package com.ss.union.game.sdk.core.base.debug.automatic_detection.d;

import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.event.PageStater;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static final String a = "ohayoo_sdk_detect";
    private static final String b = "detect_request";

    public static void a() {
        HashMap map = new HashMap();
        map.put(b, ConfigManager.AppConfig.appName());
        PageStater.onEvent(a, map);
    }
}
