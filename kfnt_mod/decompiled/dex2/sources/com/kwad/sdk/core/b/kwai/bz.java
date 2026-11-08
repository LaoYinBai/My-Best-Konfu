package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.config.item.InsertScreenConfigItem;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class bz implements com.kwad.sdk.core.d<InsertScreenConfigItem.InsertScreenConfig> {
    @Override // com.kwad.sdk.core.d
    public void a(InsertScreenConfigItem.InsertScreenConfig insertScreenConfig, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        insertScreenConfig.firstPosition = jSONObject.optInt("firstPosition", new Integer("1").intValue());
        insertScreenConfig.interval = jSONObject.optInt("interval", new Integer(PushConstants.PUSH_FLYME_3_CHANGE_VERSION_START).intValue());
        insertScreenConfig.threshold = jSONObject.optInt("threshold", new Integer("2").intValue());
        insertScreenConfig.preRequestCount = jSONObject.optInt("preRequestCount", new Integer("2").intValue());
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(InsertScreenConfigItem.InsertScreenConfig insertScreenConfig, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "firstPosition", insertScreenConfig.firstPosition);
        com.kwad.sdk.utils.t.a(jSONObject, "interval", insertScreenConfig.interval);
        com.kwad.sdk.utils.t.a(jSONObject, "threshold", insertScreenConfig.threshold);
        com.kwad.sdk.utils.t.a(jSONObject, "preRequestCount", insertScreenConfig.preRequestCount);
        return jSONObject;
    }
}
