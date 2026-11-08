package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.reward.j;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class al implements com.kwad.sdk.core.d<j.a> {
    @Override // com.kwad.sdk.core.d
    public void a(j.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        aVar.a = jSONObject.optInt(TtmlNode.TAG_STYLE);
        aVar.b = jSONObject.optString("title");
        if (jSONObject.opt("title") == JSONObject.NULL) {
            aVar.b = "";
        }
        aVar.c = jSONObject.optString("closeBtnText");
        if (jSONObject.opt("closeBtnText") == JSONObject.NULL) {
            aVar.c = "";
        }
        aVar.d = jSONObject.optString("continueBtnText");
        if (jSONObject.opt("continueBtnText") == JSONObject.NULL) {
            aVar.d = "";
        }
        aVar.g = jSONObject.optString("iconUrl");
        if (jSONObject.opt("iconUrl") == JSONObject.NULL) {
            aVar.g = "";
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(j.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, TtmlNode.TAG_STYLE, aVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "title", aVar.b);
        com.kwad.sdk.utils.t.a(jSONObject, "closeBtnText", aVar.c);
        com.kwad.sdk.utils.t.a(jSONObject, "continueBtnText", aVar.d);
        com.kwad.sdk.utils.t.a(jSONObject, "iconUrl", aVar.g);
        return jSONObject;
    }
}
