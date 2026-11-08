package com.ss.union.game.sdk.core.vapp.a;

import androidx.annotation.Nullable;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public String a;
    public String b;

    public static a a(@Nullable JSONObject jSONObject) {
        a aVar = new a();
        if (jSONObject != null) {
            aVar.a = jSONObject.optString("vUserId");
            aVar.b = jSONObject.optString("error_msg");
        }
        return aVar;
    }
}
