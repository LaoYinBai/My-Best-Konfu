package com.bytedance.gameprotect;

import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class t {
    public static void a() {
        com.bytedance.b.a.c.k kVarA = com.bytedance.b.a.c.l.a("3206");
        kVarA.p();
        kVarA.q();
    }

    public static void a(String str, Map<String, String> map) {
        a(str, map != null ? new JSONObject(map) : null);
    }

    public static void a(String str, Map<String, Object> map, Map<String, Object> map2) {
        a(str, map != null ? new JSONObject(map) : null, map2 != null ? new JSONObject(map2) : null, null);
    }

    public static void a(String str, JSONObject jSONObject) {
        com.bytedance.b.a.c.l.a("3206").a(str, jSONObject);
    }

    public static void a(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        com.bytedance.b.a.c.l.a("3206").a(str, jSONObject, jSONObject2, jSONObject3);
    }
}
