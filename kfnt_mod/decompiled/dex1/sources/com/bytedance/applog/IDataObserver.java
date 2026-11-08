package com.bytedance.applog;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IDataObserver {
    void onAbVidsChange(String str, String str2);

    void onIdLoaded(String str, String str2, String str3);

    void onRemoteAbConfigGet(boolean z, JSONObject jSONObject);

    void onRemoteConfigGet(boolean z, JSONObject jSONObject);

    void onRemoteIdGet(boolean z, String str, String str2, String str3, String str4, String str5, String str6);
}
