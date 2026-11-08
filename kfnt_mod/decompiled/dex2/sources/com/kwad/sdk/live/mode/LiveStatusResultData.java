package com.kwad.sdk.live.mode;

import androidx.annotation.Nullable;
import com.kwad.sdk.core.a.d;
import com.kwad.sdk.core.b;
import com.kwad.sdk.core.network.BaseResultData;
import com.kwad.sdk.utils.at;
import com.kwad.sdk.utils.t;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class LiveStatusResultData extends BaseResultData {
    private static final long serialVersionUID = 4029640509861990549L;
    public LiveStatus liveStatus = new LiveStatus();

    public static class LiveStatus implements b, Serializable {
        private static final long serialVersionUID = -5609658944971506312L;
        public String liveStreamId;

        @Override // com.kwad.sdk.core.b
        public void parseJson(@Nullable JSONObject jSONObject) {
            if (jSONObject == null) {
                return;
            }
            this.liveStreamId = jSONObject.optString("liveStreamId");
        }

        @Override // com.kwad.sdk.core.b
        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            t.a(jSONObject, "liveStreamId", this.liveStreamId);
            return jSONObject;
        }
    }

    @Override // com.kwad.sdk.core.network.BaseResultData
    public boolean isDataEmpty() {
        return this.liveStatus == null || at.a(this.liveStatus.liveStreamId);
    }

    @Override // com.kwad.sdk.core.network.BaseResultData, com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) throws Throwable {
        super.parseJson(jSONObject);
        if (jSONObject == null) {
            return;
        }
        try {
            String strOptString = jSONObject.optString("data");
            if (at.a(strOptString)) {
                return;
            }
            this.liveStatus.parseJson(new JSONObject(d.b(strOptString)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.kwad.sdk.core.network.BaseResultData, com.kwad.sdk.core.b
    public JSONObject toJson() throws JSONException {
        JSONObject json = super.toJson();
        t.a(json, "data", this.liveStatus);
        return json;
    }
}
