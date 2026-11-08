package com.baidu.mobads.sdk.api;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IXAdContainerFactory {
    Object getRemoteParam(String str, Object... objArr);

    double getRemoteVersion();

    void initCommonModuleObj(Object obj);

    void initConfig(JSONObject jSONObject);

    void onTaskDistribute(String str, JSONObject jSONObject);
}
