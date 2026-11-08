package com.ss.union.game.sdk.pay.a;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public String a;
    public String b;
    public String c;

    public static a a(JSONObject jSONObject) {
        a aVar = new a();
        try {
            aVar.a = jSONObject.optString("order_no", "");
            aVar.b = jSONObject.optString("wx_refer", "");
            aVar.c = jSONObject.optString("cash_desk_h5_url", "");
        } catch (Throwable unused) {
        }
        return aVar;
    }
}
