package com.ss.union.game.sdk.ad.ad_mediation;

import com.ss.union.game.sdk.core.base.event.PageStater;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class e {
    static final String a = "ad_load";
    static final String b = "init_code_id";
    static final String c = "ad_show_callback";
    static final String d = "ad_show";
    static final String e = "ad_close";
    static final String f = "ad_load_callback";
    static final String g = "full";
    static final String h = "reward";
    static final String i = "ohayoo_sdk_personal";
    static final String j = "personal_ads_status";
    private static final String k = "ad";
    private static final String l = "GAME_DEBUG";

    e() {
    }

    public static void a(String str, String str2, String str3, int i2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("category", "ad");
            jSONObject.put("sub_category", str);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("code_id", str2);
            jSONObject2.put("ad_type", str3);
            jSONObject2.put("status", i2);
            jSONObject.put("params", jSONObject2);
            jSONObject.put("tag", l);
            jSONObject.put("event_time", System.currentTimeMillis());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        PageStater.onEvent("ad", jSONObject);
    }

    @Deprecated
    public static void a(boolean z) {
        HashMap map = new HashMap();
        map.put(j, z ? "off" : "on");
        PageStater.onEvent(i, map);
    }

    public static void b(boolean z) {
        HashMap map = new HashMap();
        map.put(j, z ? "on" : "off");
        PageStater.onEvent(i, map);
    }
}
