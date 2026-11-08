package com.kwad.sdk.core.b.kwai;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.kwad.sdk.core.response.model.AdInfo;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class cl implements com.kwad.sdk.core.d<AdInfo.AdMaterialInfo.MaterialFeature> {
    @Override // com.kwad.sdk.core.d
    public void a(AdInfo.AdMaterialInfo.MaterialFeature materialFeature, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        materialFeature.featureType = jSONObject.optInt("featureType");
        materialFeature.materialUrl = jSONObject.optString("materialUrl");
        if (jSONObject.opt("materialUrl") == JSONObject.NULL) {
            materialFeature.materialUrl = "";
        }
        materialFeature.photoId = jSONObject.optLong("photoId");
        materialFeature.coverUrl = jSONObject.optString("coverUrl");
        if (jSONObject.opt("coverUrl") == JSONObject.NULL) {
            materialFeature.coverUrl = "";
        }
        materialFeature.videoDuration = jSONObject.optInt("videoDuration");
        materialFeature.firstFrame = jSONObject.optString("firstFrame");
        if (jSONObject.opt("firstFrame") == JSONObject.NULL) {
            materialFeature.firstFrame = "";
        }
        materialFeature.blurBackgroundUrl = jSONObject.optString("blurBackgroundUrl");
        if (jSONObject.opt("blurBackgroundUrl") == JSONObject.NULL) {
            materialFeature.blurBackgroundUrl = "";
        }
        materialFeature.webpCoverUrl = jSONObject.optString("webpCoverUrl");
        if (jSONObject.opt("webpCoverUrl") == JSONObject.NULL) {
            materialFeature.webpCoverUrl = "";
        }
        materialFeature.videoWidth = jSONObject.optInt("videoWidth");
        materialFeature.videoHeight = jSONObject.optInt("videoHeight");
        materialFeature.likeCount = jSONObject.optLong("likeCount");
        materialFeature.commentCount = jSONObject.optLong("commentCount");
        materialFeature.source = jSONObject.optInt(FirebaseAnalytics.Param.SOURCE);
        materialFeature.ruleId = jSONObject.optString("ruleId");
        if (jSONObject.opt("ruleId") == JSONObject.NULL) {
            materialFeature.ruleId = "";
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdInfo.AdMaterialInfo.MaterialFeature materialFeature, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "featureType", materialFeature.featureType);
        com.kwad.sdk.utils.t.a(jSONObject, "materialUrl", materialFeature.materialUrl);
        com.kwad.sdk.utils.t.a(jSONObject, "photoId", materialFeature.photoId);
        com.kwad.sdk.utils.t.a(jSONObject, "coverUrl", materialFeature.coverUrl);
        com.kwad.sdk.utils.t.a(jSONObject, "videoDuration", materialFeature.videoDuration);
        com.kwad.sdk.utils.t.a(jSONObject, "firstFrame", materialFeature.firstFrame);
        com.kwad.sdk.utils.t.a(jSONObject, "blurBackgroundUrl", materialFeature.blurBackgroundUrl);
        com.kwad.sdk.utils.t.a(jSONObject, "webpCoverUrl", materialFeature.webpCoverUrl);
        com.kwad.sdk.utils.t.a(jSONObject, "videoWidth", materialFeature.videoWidth);
        com.kwad.sdk.utils.t.a(jSONObject, "videoHeight", materialFeature.videoHeight);
        com.kwad.sdk.utils.t.a(jSONObject, "likeCount", materialFeature.likeCount);
        com.kwad.sdk.utils.t.a(jSONObject, "commentCount", materialFeature.commentCount);
        com.kwad.sdk.utils.t.a(jSONObject, FirebaseAnalytics.Param.SOURCE, materialFeature.source);
        com.kwad.sdk.utils.t.a(jSONObject, "ruleId", materialFeature.ruleId);
        return jSONObject;
    }
}
