package com.kwad.sdk.core.request.model;

import android.location.Location;
import androidx.annotation.Nullable;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.utils.r;
import com.kwad.sdk.utils.t;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e implements com.kwad.sdk.core.b {
    private static e a;
    private double b;
    private double c;

    public static e a() {
        if (a != null) {
            return a;
        }
        Location locationA = r.a(KsAdSDKImpl.get().getContext());
        if (locationA != null) {
            a = new e();
            a.b = locationA.getLatitude();
            a.c = locationA.getLongitude();
        }
        return a;
    }

    @Override // com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) {
    }

    @Override // com.kwad.sdk.core.b
    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        t.a(jSONObject, "latitude", this.b);
        t.a(jSONObject, "longitude", this.c);
        return jSONObject;
    }
}
