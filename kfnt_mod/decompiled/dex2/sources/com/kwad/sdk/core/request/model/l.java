package com.kwad.sdk.core.request.model;

import androidx.annotation.Nullable;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.export.proxy.AdRequestExtentParamsProxy;
import com.kwad.sdk.utils.t;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class l implements com.kwad.sdk.core.b {
    public int a;
    public int b;
    public String c;
    private String d;
    private String e;

    public static l a() {
        Map<String, String> extentParams;
        l lVar = new l();
        AdRequestExtentParamsProxy requestExtentParamsProxy = KsAdSDKImpl.get().getRequestExtentParamsProxy();
        if (requestExtentParamsProxy != null && (extentParams = requestExtentParamsProxy.getExtentParams()) != null) {
            lVar.d = extentParams.get("user_id");
        }
        return lVar;
    }

    public void a(String str) {
        this.e = str;
    }

    @Override // com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) {
    }

    @Override // com.kwad.sdk.core.b
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        t.a(jSONObject, "user_id", this.d);
        t.a(jSONObject, "thirdUserId", this.e);
        if (this.a != 0) {
            t.a(jSONObject, "thirdAge", this.a);
        }
        if (this.b != 0) {
            t.a(jSONObject, "thirdGender", this.b);
        }
        t.a(jSONObject, "thirdInterest", this.c);
        return jSONObject;
    }
}
