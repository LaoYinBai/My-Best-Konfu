package com.ss.union.game.sdk.core.vapp.a;

import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.token.TokenConstants;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    public static final int a = 1;
    public int b;
    public String c;
    public String d;
    public String e;
    public int f;

    public static c a(JSONObject jSONObject) {
        c cVar = new c();
        cVar.b = jSONObject.optInt(AdDownloadModel.JsonKey.ID);
        cVar.c = jSONObject.optString("name");
        cVar.d = jSONObject.optString("icon");
        cVar.e = jSONObject.optString(TokenConstants.ERROR_MSG);
        cVar.f = jSONObject.optInt(com.ss.union.game.sdk.redemptionCode.a.q);
        return cVar;
    }
}
