package com.kwad.sdk.core.request.model;

import androidx.annotation.Nullable;
import com.kwad.sdk.api.KsScene;
import com.kwad.sdk.internal.api.SceneImpl;
import com.kwad.sdk.utils.t;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f implements com.kwad.sdk.core.b {
    public SceneImpl a;
    public long b = 0;
    public long c = 0;
    public String d;

    public f(KsScene ksScene) {
        this.a = (SceneImpl) ksScene;
    }

    @Nullable
    public com.kwad.sdk.internal.api.a a() {
        if (this.a == null) {
            return null;
        }
        return this.a.mKsAdLabel;
    }

    @Nullable
    public String a(String str) {
        Map<String, String> rewardCallbackExtraData;
        if (str == null || this.a == null || (rewardCallbackExtraData = this.a.getRewardCallbackExtraData()) == null || !rewardCallbackExtraData.containsKey(str)) {
            return null;
        }
        return rewardCallbackExtraData.get(str);
    }

    @Override // com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) {
    }

    @Override // com.kwad.sdk.core.b
    public JSONObject toJson() throws JSONException {
        JSONObject json = this.a.toJson();
        t.a(json, "pageScene", this.b);
        t.a(json, "subPageScene", this.c);
        t.a(json, "sdkExtraData", this.d);
        String strA = a("extraData");
        if (strA != null) {
            t.a(json, "extraData", strA);
        }
        return json;
    }
}
