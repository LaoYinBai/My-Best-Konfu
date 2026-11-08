package com.bytedance.sdk.account.platform.api;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IAccountSettingsService extends IAuthorizeService {
    String getAccountSettingsConfig();

    JSONObject getLoginInfoConfig();

    JSONObject getOnekeyLoginConfig();

    JSONObject getThirdPartyConfig();

    void updateSettings(JSONObject jSONObject);
}
