package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.AdInfo;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class s implements com.kwad.sdk.core.d<AdInfo.AdSplashInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(AdInfo.AdSplashInfo adSplashInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        adSplashInfo.logoPosition = jSONObject.optInt("logoPosition", new Integer("1").intValue());
        adSplashInfo.skipSecond = jSONObject.optInt("skipSecond");
        adSplashInfo.mute = jSONObject.optInt(CampaignEx.JSON_NATIVE_VIDEO_MUTE, new Integer("1").intValue());
        adSplashInfo.skipTips = jSONObject.optString("skipTips");
        if (jSONObject.opt("skipTips") == JSONObject.NULL) {
            adSplashInfo.skipTips = "";
        }
        adSplashInfo.speakerMuteIconUrl = jSONObject.optString("speakerMuteIconUrl");
        if (jSONObject.opt("speakerMuteIconUrl") == JSONObject.NULL) {
            adSplashInfo.speakerMuteIconUrl = "";
        }
        adSplashInfo.speakerIconUrl = jSONObject.optString("speakerIconUrl");
        if (jSONObject.opt("speakerIconUrl") == JSONObject.NULL) {
            adSplashInfo.speakerIconUrl = "";
        }
        adSplashInfo.imageDisplaySecond = jSONObject.optInt("imageDisplaySecond", new Integer(CampaignEx.CLICKMODE_ON).intValue());
        adSplashInfo.countdownShow = jSONObject.optInt("countdownShow");
        adSplashInfo.fullScreenClickSwitch = jSONObject.optInt("fullScreenClickSwitch");
        adSplashInfo.splashCloseButtonNewStyleSwitch = jSONObject.optInt("splashCloseButtonNewStyleSwitch");
        adSplashInfo.splashShowClickButtonSwitch = jSONObject.optInt("splashShowClickButtonSwitch", new Integer("1").intValue());
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdInfo.AdSplashInfo adSplashInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "logoPosition", adSplashInfo.logoPosition);
        com.kwad.sdk.utils.t.a(jSONObject, "skipSecond", adSplashInfo.skipSecond);
        com.kwad.sdk.utils.t.a(jSONObject, CampaignEx.JSON_NATIVE_VIDEO_MUTE, adSplashInfo.mute);
        com.kwad.sdk.utils.t.a(jSONObject, "skipTips", adSplashInfo.skipTips);
        com.kwad.sdk.utils.t.a(jSONObject, "speakerMuteIconUrl", adSplashInfo.speakerMuteIconUrl);
        com.kwad.sdk.utils.t.a(jSONObject, "speakerIconUrl", adSplashInfo.speakerIconUrl);
        com.kwad.sdk.utils.t.a(jSONObject, "imageDisplaySecond", adSplashInfo.imageDisplaySecond);
        com.kwad.sdk.utils.t.a(jSONObject, "countdownShow", adSplashInfo.countdownShow);
        com.kwad.sdk.utils.t.a(jSONObject, "fullScreenClickSwitch", adSplashInfo.fullScreenClickSwitch);
        com.kwad.sdk.utils.t.a(jSONObject, "splashCloseButtonNewStyleSwitch", adSplashInfo.splashCloseButtonNewStyleSwitch);
        com.kwad.sdk.utils.t.a(jSONObject, "splashShowClickButtonSwitch", adSplashInfo.splashShowClickButtonSwitch);
        return jSONObject;
    }
}
