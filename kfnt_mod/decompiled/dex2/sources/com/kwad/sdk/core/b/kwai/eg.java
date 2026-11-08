package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.SdkConfigData;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class eg implements com.kwad.sdk.core.d<SdkConfigData.TemplateConfigMap> {
    @Override // com.kwad.sdk.core.d
    public void a(SdkConfigData.TemplateConfigMap templateConfigMap, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        templateConfigMap.downloadPopWindowConfig = new SdkConfigData.TemplateConfig();
        templateConfigMap.downloadPopWindowConfig.parseJson(jSONObject.optJSONObject("downloadPopWindowConfig"));
        templateConfigMap.interactPageDialogTipsConfig = new SdkConfigData.TemplateConfig();
        templateConfigMap.interactPageDialogTipsConfig.parseJson(jSONObject.optJSONObject("interactPageDialogTipsConfig"));
        templateConfigMap.insertScreenTemplateConfig = new SdkConfigData.TemplateConfig();
        templateConfigMap.insertScreenTemplateConfig.parseJson(jSONObject.optJSONObject("insertScreenTemplateConfig"));
        templateConfigMap.couponOpenConfig = new SdkConfigData.TemplateConfig();
        templateConfigMap.couponOpenConfig.parseJson(jSONObject.optJSONObject("couponOpenConfig"));
        templateConfigMap.couponInfoConfig = new SdkConfigData.TemplateConfig();
        templateConfigMap.couponInfoConfig.parseJson(jSONObject.optJSONObject("couponInfoConfig"));
        templateConfigMap.downloadLandingPageConfig = new SdkConfigData.TemplateConfig();
        templateConfigMap.downloadLandingPageConfig.parseJson(jSONObject.optJSONObject("downloadLandingPageConfig"));
        templateConfigMap.secDownloadPopWindowConfig = new SdkConfigData.TemplateConfig();
        templateConfigMap.secDownloadPopWindowConfig.parseJson(jSONObject.optJSONObject("secDownloadPopWindowConfig"));
        templateConfigMap.rewardReflowConfig = new SdkConfigData.TemplateConfig();
        templateConfigMap.rewardReflowConfig.parseJson(jSONObject.optJSONObject("rewardReflowConfig"));
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(SdkConfigData.TemplateConfigMap templateConfigMap, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "downloadPopWindowConfig", templateConfigMap.downloadPopWindowConfig);
        com.kwad.sdk.utils.t.a(jSONObject, "interactPageDialogTipsConfig", templateConfigMap.interactPageDialogTipsConfig);
        com.kwad.sdk.utils.t.a(jSONObject, "insertScreenTemplateConfig", templateConfigMap.insertScreenTemplateConfig);
        com.kwad.sdk.utils.t.a(jSONObject, "couponOpenConfig", templateConfigMap.couponOpenConfig);
        com.kwad.sdk.utils.t.a(jSONObject, "couponInfoConfig", templateConfigMap.couponInfoConfig);
        com.kwad.sdk.utils.t.a(jSONObject, "downloadLandingPageConfig", templateConfigMap.downloadLandingPageConfig);
        com.kwad.sdk.utils.t.a(jSONObject, "secDownloadPopWindowConfig", templateConfigMap.secDownloadPopWindowConfig);
        com.kwad.sdk.utils.t.a(jSONObject, "rewardReflowConfig", templateConfigMap.rewardReflowConfig);
        return jSONObject;
    }
}
