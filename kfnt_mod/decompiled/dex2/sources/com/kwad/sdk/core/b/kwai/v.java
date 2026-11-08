package com.kwad.sdk.core.b.kwai;

import com.heytap.mcssdk.mode.Message;
import com.kwad.sdk.core.response.model.AdInfo;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class v implements com.kwad.sdk.core.d<AdInfo.AdTrackInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(AdInfo.AdTrackInfo adTrackInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        adTrackInfo.type = jSONObject.optInt(Message.TYPE);
        adTrackInfo.urls = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("url");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                adTrackInfo.urls.add((String) jSONArrayOptJSONArray.opt(i));
            }
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdInfo.AdTrackInfo adTrackInfo, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, Message.TYPE, adTrackInfo.type);
        com.kwad.sdk.utils.t.a(jSONObject, "url", adTrackInfo.urls);
        return jSONObject;
    }
}
