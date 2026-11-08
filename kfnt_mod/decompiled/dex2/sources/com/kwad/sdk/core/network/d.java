package com.kwad.sdk.core.network;

import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.plugin.DevelopMangerPlugin;
import com.mbridge.msdk.MBridgeConstans;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class d extends b {
    public d() {
        this(0);
    }

    public d(int i) {
        a("deviceInfo", com.kwad.sdk.core.request.model.d.a(j(), i));
    }

    @Override // com.kwad.sdk.core.network.b
    protected void g() throws JSONException {
        String strA = a();
        if (!com.kwad.sdk.b.c.booleanValue() || strA == null || strA.contains(com.kwad.sdk.d.d())) {
            return;
        }
        DevelopMangerPlugin.DevelopValue developValueA = ((DevelopMangerPlugin) com.kwad.sdk.plugin.f.a(DevelopMangerPlugin.class)).a("KEY_AD_UNIVERSE");
        if (developValueA == null) {
            a("trace-context", "{\"laneId\":\"ad_test.universe\"}");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("laneId", developValueA.getValue().toString());
            a("trace-context", jSONObject.toString());
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.b(e);
        }
    }

    @Override // com.kwad.sdk.core.network.b
    protected void h() {
        b("protocolVersion", MBridgeConstans.NATIVE_VIDEO_VERSION);
        b("SDKVersion", "3.3.17");
        a("SDKVersionCode", 3031700);
        b("sdkApiVersion", KsAdSDKImpl.get().getApiVersion());
        a("sdkApiVersionCode", KsAdSDKImpl.get().getApiVersionCode());
        a("sdkType", 1);
        a("appInfo", com.kwad.sdk.core.request.model.b.a());
        a("networkInfo", com.kwad.sdk.core.request.model.h.a());
        a("geoInfo", com.kwad.sdk.core.request.model.e.a());
        a("ext", com.kwad.sdk.core.request.model.i.a());
        a("userInfo", com.kwad.sdk.core.request.model.l.a());
        a("statusInfo", i());
        b("requestSessionData", l.a().a(a()));
    }

    protected com.kwad.sdk.core.request.model.j i() {
        return new com.kwad.sdk.core.request.model.j();
    }

    protected boolean j() {
        return false;
    }
}
