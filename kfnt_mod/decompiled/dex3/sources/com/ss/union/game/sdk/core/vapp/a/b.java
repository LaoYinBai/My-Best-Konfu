package com.ss.union.game.sdk.core.vapp.a;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    public static final int a = -1;
    public int b;
    public String c;
    public String d;
    public int e;

    public static b a(JSONObject jSONObject) {
        b bVar = new b();
        bVar.b = jSONObject.optInt("tool_id", -1);
        bVar.c = jSONObject.optString("tool_name", "");
        bVar.d = jSONObject.optString("tool_icon", "");
        bVar.e = jSONObject.optInt("remain_count", 0);
        return bVar;
    }
}
