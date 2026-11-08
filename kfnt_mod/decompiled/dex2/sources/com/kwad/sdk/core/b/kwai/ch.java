package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.report.ReportAction;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ch implements com.kwad.sdk.core.d<ReportAction.LiveLogInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(ReportAction.LiveLogInfo liveLogInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        liveLogInfo.liveStreamId = jSONObject.optString("liveStreamId");
        if (jSONObject.opt("liveStreamId") == JSONObject.NULL) {
            liveLogInfo.liveStreamId = "";
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(ReportAction.LiveLogInfo liveLogInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "liveStreamId", liveLogInfo.liveStreamId);
        return jSONObject;
    }
}
