package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.report.CommercialAction;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ea implements com.kwad.sdk.core.d<CommercialAction.TKDownloadMsg> {
    @Override // com.kwad.sdk.core.d
    public void a(CommercialAction.TKDownloadMsg tKDownloadMsg, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        tKDownloadMsg.downloadState = jSONObject.optInt("download_state");
        tKDownloadMsg.downloadTime = jSONObject.optLong("download_time");
        tKDownloadMsg.preload = jSONObject.optInt("preload");
        tKDownloadMsg.errorReason = jSONObject.optString("error_reason");
        if (jSONObject.opt("error_reason") == JSONObject.NULL) {
            tKDownloadMsg.errorReason = "";
        }
        tKDownloadMsg.templateId = jSONObject.optString("template_id");
        if (jSONObject.opt("template_id") == JSONObject.NULL) {
            tKDownloadMsg.templateId = "";
        }
        tKDownloadMsg.versionCode = jSONObject.optString("version_code");
        if (jSONObject.opt("version_code") == JSONObject.NULL) {
            tKDownloadMsg.versionCode = "";
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(CommercialAction.TKDownloadMsg tKDownloadMsg, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "download_state", tKDownloadMsg.downloadState);
        com.kwad.sdk.utils.t.a(jSONObject, "download_time", tKDownloadMsg.downloadTime);
        com.kwad.sdk.utils.t.a(jSONObject, "preload", tKDownloadMsg.preload);
        com.kwad.sdk.utils.t.a(jSONObject, "error_reason", tKDownloadMsg.errorReason);
        com.kwad.sdk.utils.t.a(jSONObject, "template_id", tKDownloadMsg.templateId);
        com.kwad.sdk.utils.t.a(jSONObject, "version_code", tKDownloadMsg.versionCode);
        return jSONObject;
    }
}
