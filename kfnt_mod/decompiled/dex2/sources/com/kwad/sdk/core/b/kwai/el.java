package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.collector.AppStatusRules;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class el implements com.kwad.sdk.core.d<AppStatusRules.UploadConfig> {
    @Override // com.kwad.sdk.core.d
    public void a(AppStatusRules.UploadConfig uploadConfig, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        uploadConfig.fileMaxSize = jSONObject.optLong("fileMaxSize");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AppStatusRules.UploadConfig uploadConfig, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "fileMaxSize", uploadConfig.fileMaxSize);
        return jSONObject;
    }
}
