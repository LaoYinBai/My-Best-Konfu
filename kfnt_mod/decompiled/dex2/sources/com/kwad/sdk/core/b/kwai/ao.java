package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.contentalliance.coupon.model.ActivityInfo;
import com.kwad.sdk.core.response.model.SdkConfigData;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ao implements com.kwad.sdk.core.d<SdkConfigData.CouponActiveConfig> {
    @Override // com.kwad.sdk.core.d
    public void a(SdkConfigData.CouponActiveConfig couponActiveConfig, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        couponActiveConfig.popUpShowTimeSeconds = jSONObject.optInt("popUpShowTimeSeconds");
        couponActiveConfig.title = jSONObject.optString("title");
        if (jSONObject.opt("title") == JSONObject.NULL) {
            couponActiveConfig.title = "";
        }
        couponActiveConfig.secondTitle = jSONObject.optString("secondTitle");
        if (jSONObject.opt("secondTitle") == JSONObject.NULL) {
            couponActiveConfig.secondTitle = "";
        }
        couponActiveConfig.bottomTitle = jSONObject.optString("bottomTitle");
        if (jSONObject.opt("bottomTitle") == JSONObject.NULL) {
            couponActiveConfig.bottomTitle = "";
        }
        couponActiveConfig.videoThreshold = jSONObject.optInt("videoThreshold");
        couponActiveConfig.videoSeconds = jSONObject.optInt("videoSeconds");
        couponActiveConfig.couponOpenConfig = new SdkConfigData.TemplateConfig();
        couponActiveConfig.couponOpenConfig.parseJson(jSONObject.optJSONObject("couponOpenConfig"));
        couponActiveConfig.couponInfoConfig = new SdkConfigData.TemplateConfig();
        couponActiveConfig.couponInfoConfig.parseJson(jSONObject.optJSONObject("couponInfoConfig"));
        couponActiveConfig.activityInfo = new ActivityInfo();
        couponActiveConfig.activityInfo.parseJson(jSONObject.optJSONObject("activityInfo"));
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(SdkConfigData.CouponActiveConfig couponActiveConfig, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "popUpShowTimeSeconds", couponActiveConfig.popUpShowTimeSeconds);
        com.kwad.sdk.utils.t.a(jSONObject, "title", couponActiveConfig.title);
        com.kwad.sdk.utils.t.a(jSONObject, "secondTitle", couponActiveConfig.secondTitle);
        com.kwad.sdk.utils.t.a(jSONObject, "bottomTitle", couponActiveConfig.bottomTitle);
        com.kwad.sdk.utils.t.a(jSONObject, "videoThreshold", couponActiveConfig.videoThreshold);
        com.kwad.sdk.utils.t.a(jSONObject, "videoSeconds", couponActiveConfig.videoSeconds);
        com.kwad.sdk.utils.t.a(jSONObject, "couponOpenConfig", couponActiveConfig.couponOpenConfig);
        com.kwad.sdk.utils.t.a(jSONObject, "couponInfoConfig", couponActiveConfig.couponInfoConfig);
        com.kwad.sdk.utils.t.a(jSONObject, "activityInfo", couponActiveConfig.activityInfo);
        return jSONObject;
    }
}
