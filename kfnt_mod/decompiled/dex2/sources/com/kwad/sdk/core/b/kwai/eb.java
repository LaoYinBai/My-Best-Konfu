package com.kwad.sdk.core.b.kwai;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.kwad.sdk.core.report.CommercialAction;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class eb implements com.kwad.sdk.core.d<CommercialAction.TKPerformMsg> {
    @Override // com.kwad.sdk.core.d
    public void a(CommercialAction.TKPerformMsg tKPerformMsg, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        tKPerformMsg.source = jSONObject.optInt(FirebaseAnalytics.Param.SOURCE);
        tKPerformMsg.renderState = jSONObject.optInt("render_state");
        tKPerformMsg.errorReason = jSONObject.optString("error_reason");
        if (jSONObject.opt("error_reason") == JSONObject.NULL) {
            tKPerformMsg.errorReason = "";
        }
        tKPerformMsg.renderTime = jSONObject.optLong("render_time");
        tKPerformMsg.templateId = jSONObject.optString("template_id");
        if (jSONObject.opt("template_id") == JSONObject.NULL) {
            tKPerformMsg.templateId = "";
        }
        tKPerformMsg.versionCode = jSONObject.optString("version_code");
        if (jSONObject.opt("version_code") == JSONObject.NULL) {
            tKPerformMsg.versionCode = "";
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(CommercialAction.TKPerformMsg tKPerformMsg, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, FirebaseAnalytics.Param.SOURCE, tKPerformMsg.source);
        com.kwad.sdk.utils.t.a(jSONObject, "render_state", tKPerformMsg.renderState);
        com.kwad.sdk.utils.t.a(jSONObject, "error_reason", tKPerformMsg.errorReason);
        com.kwad.sdk.utils.t.a(jSONObject, "render_time", tKPerformMsg.renderTime);
        com.kwad.sdk.utils.t.a(jSONObject, "template_id", tKPerformMsg.templateId);
        com.kwad.sdk.utils.t.a(jSONObject, "version_code", tKPerformMsg.versionCode);
        return jSONObject;
    }
}
