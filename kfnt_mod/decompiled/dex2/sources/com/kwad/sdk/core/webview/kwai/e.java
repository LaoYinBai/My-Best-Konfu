package com.kwad.sdk.core.webview.kwai;

import androidx.annotation.Nullable;
import com.kwad.sdk.utils.t;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e implements com.kwad.sdk.core.b {
    private final int a;
    private final String b;

    public e(int i, String str) {
        this.a = i;
        this.b = str;
    }

    @Override // com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) {
    }

    @Override // com.kwad.sdk.core.b
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        t.a(jSONObject, "result", this.a);
        t.a(jSONObject, "error_msg", this.b);
        return jSONObject;
    }
}
