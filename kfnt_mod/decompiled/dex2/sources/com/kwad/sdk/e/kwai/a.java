package com.kwad.sdk.e.kwai;

import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.kuaishou.tachikoma.api.app.TKBaseResponse;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements com.kwad.sdk.e.e<TKBaseResponse> {
    @Override // com.kwad.sdk.e.e
    public void a(TKBaseResponse tKBaseResponse, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        tKBaseResponse.body = jSONObject.optString(TtmlNode.TAG_BODY);
        tKBaseResponse.statusCode = jSONObject.optInt(HiAnalyticsConstant.HaKey.BI_KEY_RESULT);
        tKBaseResponse.allHeaderFields = jSONObject.optString("allHeaderFields");
    }

    @Override // com.kwad.sdk.e.e
    public JSONObject b(TKBaseResponse tKBaseResponse, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.e.f.a(jSONObject, TtmlNode.TAG_BODY, tKBaseResponse.body);
        com.kwad.sdk.e.f.a(jSONObject, HiAnalyticsConstant.HaKey.BI_KEY_RESULT, tKBaseResponse.statusCode);
        com.kwad.sdk.e.f.a(jSONObject, "allHeaderFields", tKBaseResponse.allHeaderFields);
        return jSONObject;
    }
}
