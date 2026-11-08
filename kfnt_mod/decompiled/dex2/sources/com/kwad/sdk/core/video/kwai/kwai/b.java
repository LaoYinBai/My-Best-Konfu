package com.kwad.sdk.core.video.kwai.kwai;

import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.core.request.model.e;
import com.kwad.sdk.core.request.model.h;
import com.kwad.sdk.d;
import com.mbridge.msdk.MBridgeConstans;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends com.kwad.sdk.core.network.b {
    public b(List<c> list) {
        a("actionList", list);
    }

    @Override // com.kwad.sdk.core.network.b, com.kwad.sdk.core.network.g
    public String a() {
        return d.g();
    }

    @Override // com.kwad.sdk.core.network.b
    protected void g() {
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
        a("deviceInfo", com.kwad.sdk.core.request.model.d.a(false));
        a("networkInfo", h.a());
        a("geoInfo", e.a());
    }
}
