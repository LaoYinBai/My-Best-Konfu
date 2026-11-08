package com.kwad.sdk.e.kwai;

import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import com.tachikoma.core.component.network.delegate.TKBaseResponseInner;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b implements com.kwad.sdk.e.e<TKBaseResponseInner> {
    @Override // com.kwad.sdk.e.e
    public void a(TKBaseResponseInner tKBaseResponseInner, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        tKBaseResponseInner.body = jSONObject.optString(TtmlNode.TAG_BODY);
        tKBaseResponseInner.statusCode = jSONObject.optInt(HiAnalyticsConstant.HaKey.BI_KEY_RESULT);
        tKBaseResponseInner.allHeaderFields = jSONObject.optString("allHeaderFields");
    }

    @Override // com.kwad.sdk.e.e
    public JSONObject b(TKBaseResponseInner tKBaseResponseInner, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.e.f.a(jSONObject, TtmlNode.TAG_BODY, tKBaseResponseInner.body);
        com.kwad.sdk.e.f.a(jSONObject, HiAnalyticsConstant.HaKey.BI_KEY_RESULT, tKBaseResponseInner.statusCode);
        com.kwad.sdk.e.f.a(jSONObject, "allHeaderFields", tKBaseResponseInner.allHeaderFields);
        return jSONObject;
    }
}
