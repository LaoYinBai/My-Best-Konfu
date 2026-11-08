package com.kwad.sdk.core.b.kwai;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class dl implements com.kwad.sdk.core.d<com.kwad.sdk.core.report.m> {
    @Override // com.kwad.sdk.core.d
    public void a(com.kwad.sdk.core.report.m mVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        mVar.bN = jSONObject.optString("ua");
        if (jSONObject.opt("ua") == JSONObject.NULL) {
            mVar.bN = "";
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(com.kwad.sdk.core.report.m mVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "ua", mVar.bN);
        return jSONObject;
    }
}
