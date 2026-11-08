package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.AdMatrixInfo;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class j implements com.kwad.sdk.core.d<AdMatrixInfo.AdDataV2> {
    @Override // com.kwad.sdk.core.d
    public void a(AdMatrixInfo.AdDataV2 adDataV2, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        adDataV2.templateDataList = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("templateDatas");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                AdMatrixInfo.TemplateData templateData = new AdMatrixInfo.TemplateData();
                templateData.parseJson(jSONArrayOptJSONArray.optJSONObject(i));
                adDataV2.templateDataList.add(templateData);
            }
        }
        adDataV2.bottomBannerInfo = new AdMatrixInfo.BottomBannerInfo();
        adDataV2.bottomBannerInfo.parseJson(jSONObject.optJSONObject("bottomBannerInfo"));
        adDataV2.actionBarInfo = new AdMatrixInfo.ActionBarInfoNew();
        adDataV2.actionBarInfo.parseJson(jSONObject.optJSONObject("actionBarInfo"));
        adDataV2.fullPageActionBarInfo = new AdMatrixInfo.FullPageActionBarInfo();
        adDataV2.fullPageActionBarInfo.parseJson(jSONObject.optJSONObject("fullPageActionBarInfo"));
        adDataV2.halfCardInfo = new AdMatrixInfo.HalfCardInfo();
        adDataV2.halfCardInfo.parseJson(jSONObject.optJSONObject("halfCardInfo"));
        adDataV2.endCardInfo = new AdMatrixInfo.EndCardInfo();
        adDataV2.endCardInfo.parseJson(jSONObject.optJSONObject("endCardInfo"));
        adDataV2.interactionInfo = new AdMatrixInfo.InteractionInfo();
        adDataV2.interactionInfo.parseJson(jSONObject.optJSONObject("interactionInfo"));
        adDataV2.interstitialCardInfo = new AdMatrixInfo.InterstitialCardInfo();
        adDataV2.interstitialCardInfo.parseJson(jSONObject.optJSONObject("interstitialCardInfo"));
        adDataV2.feedInfo = new AdMatrixInfo.FeedInfo();
        adDataV2.feedInfo.parseJson(jSONObject.optJSONObject("feedInfo"));
        adDataV2.splashInfo = new AdMatrixInfo.SplashInfo();
        adDataV2.splashInfo.parseJson(jSONObject.optJSONObject("splashInfo"));
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdMatrixInfo.AdDataV2 adDataV2, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "templateDatas", adDataV2.templateDataList);
        com.kwad.sdk.utils.t.a(jSONObject, "bottomBannerInfo", adDataV2.bottomBannerInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "actionBarInfo", adDataV2.actionBarInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "fullPageActionBarInfo", adDataV2.fullPageActionBarInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "halfCardInfo", adDataV2.halfCardInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "endCardInfo", adDataV2.endCardInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "interactionInfo", adDataV2.interactionInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "interstitialCardInfo", adDataV2.interstitialCardInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "feedInfo", adDataV2.feedInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "splashInfo", adDataV2.splashInfo);
        return jSONObject;
    }
}
