package com.kwad.sdk.core.webview.kwai;

import androidx.annotation.Nullable;
import com.kwad.sdk.utils.t;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f implements com.kwad.sdk.core.b {
    public final int a = 1;
    public final com.kwad.sdk.core.b b;

    public f(com.kwad.sdk.core.b bVar) {
        this.b = bVar;
    }

    @Override // com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) {
    }

    @Override // com.kwad.sdk.core.b
    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        t.a(jSONObject, "result", this.a);
        t.a(jSONObject, "data", this.b);
        return jSONObject;
    }
}
