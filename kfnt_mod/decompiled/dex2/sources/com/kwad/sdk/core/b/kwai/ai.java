package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.report.ReportAction;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ai implements com.kwad.sdk.core.d<ReportAction.a> {
    @Override // com.kwad.sdk.core.d
    public void a(ReportAction.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        aVar.a = jSONObject.optInt("posIdWidth");
        aVar.b = jSONObject.optInt("posIdHeight");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(ReportAction.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "posIdWidth", aVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "posIdHeight", aVar.b);
        return jSONObject;
    }
}
