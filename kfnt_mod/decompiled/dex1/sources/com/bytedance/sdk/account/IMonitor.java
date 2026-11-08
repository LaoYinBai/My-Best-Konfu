package com.bytedance.sdk.account;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IMonitor {
    void onEvent(String str, JSONObject jSONObject);

    void setAppLogInfo(long j, String str);
}
