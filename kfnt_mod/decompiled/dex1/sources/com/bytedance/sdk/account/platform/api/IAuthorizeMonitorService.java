package com.bytedance.sdk.account.platform.api;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IAuthorizeMonitorService extends IAuthorizeService {
    void onEvent(String str, JSONObject jSONObject);
}
