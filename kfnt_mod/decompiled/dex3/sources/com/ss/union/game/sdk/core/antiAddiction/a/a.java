package com.ss.union.game.sdk.core.antiAddiction.a;

import com.ss.union.game.sdk.core.base.event.PageStater;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static final String a = "window_show";
    private static final String b = "window_close_or_not";
    private static final String c = "click_button";
    private static final String d = "anti_addiction_window";
    private static final String e = "click_confirm_anti_addiction_window";
    private static final String f = "inform_cp_anti_addiction";
    private static final String g = "inform_time_up";
    private static final String h = "inform_cp";
    private static Map<Integer, Integer> i = new HashMap();

    static {
        i.put(201, 1);
    }

    public static void a() throws JSONException {
        a("window_show", d, "", 1L);
    }

    public static void b() throws JSONException {
        a("click_button", e, "", 1L);
    }

    public static void c() throws JSONException {
        a(h, f, g, 0L);
    }

    private static void a(String str, String str2, String str3, long j) throws JSONException {
        PageStater.V1.onEvent(str, str2, str3, 0, j);
    }
}
