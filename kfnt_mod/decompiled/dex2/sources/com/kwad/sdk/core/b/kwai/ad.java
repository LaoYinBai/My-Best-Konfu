package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.config.item.AvatarGuiderConfigItem;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ad implements com.kwad.sdk.core.d<AvatarGuiderConfigItem.AvatarGuiderConfig> {
    @Override // com.kwad.sdk.core.d
    public void a(AvatarGuiderConfigItem.AvatarGuiderConfig avatarGuiderConfig, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        avatarGuiderConfig.showByPlayRate = jSONObject.optInt("showByPlayRate", new Integer("70").intValue());
        avatarGuiderConfig.showTimeLength = jSONObject.optInt("showTimeLength", new Integer("3000").intValue());
        avatarGuiderConfig.showMaxTimes = jSONObject.optInt("showMaxTimes", new Integer("7").intValue());
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AvatarGuiderConfigItem.AvatarGuiderConfig avatarGuiderConfig, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "showByPlayRate", avatarGuiderConfig.showByPlayRate);
        com.kwad.sdk.utils.t.a(jSONObject, "showTimeLength", avatarGuiderConfig.showTimeLength);
        com.kwad.sdk.utils.t.a(jSONObject, "showMaxTimes", avatarGuiderConfig.showMaxTimes);
        return jSONObject;
    }
}
