package com.ss.union.game.sdk.core.age_tips;

import com.ss.union.game.sdk.core.base.event.PageStater;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    private static final String a = "ohayoo_sdk_age";

    public static void a(String str) {
        HashMap map = new HashMap();
        map.put("age_request", str);
        a(map);
    }

    public static void b(String str) {
        HashMap map = new HashMap();
        map.put("age_show", str);
        a(map);
    }

    public static void c(String str) {
        HashMap map = new HashMap();
        map.put("age_click", str);
        a(map);
    }

    private static void a(Map<String, String> map) {
        PageStater.onEvent(a, map);
    }
}
