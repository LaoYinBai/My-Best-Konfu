package com.kwad.sdk.core.network;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class BaseRealData extends BaseResultData {
    protected abstract void parseData(@NonNull JSONObject jSONObject);

    @Override // com.kwad.sdk.core.network.BaseResultData, com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) throws Throwable {
        super.parseJson(jSONObject);
        if (jSONObject == null) {
            return;
        }
        try {
            String strB = com.kwad.sdk.core.a.d.b(jSONObject.optString("data"));
            if (strB == null || strB.isEmpty()) {
                this.result = 0;
            } else {
                parseData(new JSONObject(strB));
            }
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
            this.result = 0;
            this.errorMsg = e.getMessage();
        }
    }
}
