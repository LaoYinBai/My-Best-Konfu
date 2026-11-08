package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.AdMatrixInfo;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ah implements com.kwad.sdk.core.d<AdMatrixInfo.BottomBannerInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(AdMatrixInfo.BottomBannerInfo bottomBannerInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        bottomBannerInfo.bannerSizeType = jSONObject.optInt("bannerSizeType");
        bottomBannerInfo.bannerAdType = jSONObject.optInt("bannerAdType");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdMatrixInfo.BottomBannerInfo bottomBannerInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "bannerSizeType", bottomBannerInfo.bannerSizeType);
        com.kwad.sdk.utils.t.a(jSONObject, "bannerAdType", bottomBannerInfo.bannerAdType);
        return jSONObject;
    }
}
