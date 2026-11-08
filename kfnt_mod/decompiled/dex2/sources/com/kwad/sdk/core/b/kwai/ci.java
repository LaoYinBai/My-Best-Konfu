package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.live.mode.LiveInfo;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ci implements com.kwad.sdk.core.d<LiveInfo.LiveStreamPlayCDNNode> {
    @Override // com.kwad.sdk.core.d
    public void a(LiveInfo.LiveStreamPlayCDNNode liveStreamPlayCDNNode, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        liveStreamPlayCDNNode.cdn = jSONObject.optString("cdn");
        if (jSONObject.opt("cdn") == JSONObject.NULL) {
            liveStreamPlayCDNNode.cdn = "";
        }
        liveStreamPlayCDNNode.url = jSONObject.optString("url");
        if (jSONObject.opt("url") == JSONObject.NULL) {
            liveStreamPlayCDNNode.url = "";
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(LiveInfo.LiveStreamPlayCDNNode liveStreamPlayCDNNode, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "cdn", liveStreamPlayCDNNode.cdn);
        com.kwad.sdk.utils.t.a(jSONObject, "url", liveStreamPlayCDNNode.url);
        return jSONObject;
    }
}
