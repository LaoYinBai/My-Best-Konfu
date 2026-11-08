package com.kwad.sdk.core.response.model;

import androidx.annotation.Nullable;
import com.kwad.sdk.core.a.d;
import com.kwad.sdk.core.network.BaseResultData;
import com.kwad.sdk.utils.t;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class PhotoShareInfo extends BaseResultData implements com.kwad.sdk.core.b, Serializable {
    private static final long serialVersionUID = 1941263519515554943L;
    public ShareUrlInfo data = new ShareUrlInfo();

    public static class ShareUrlInfo extends com.kwad.sdk.core.response.kwai.a implements Serializable {
        private static final long serialVersionUID = -7887599282226106153L;
        public String mediaShareItem;
        public String shareUrl;
    }

    public String getMediaShareItem() {
        return this.data != null ? this.data.mediaShareItem : "";
    }

    public String getShareUrl() {
        return this.data != null ? this.data.shareUrl : "";
    }

    @Override // com.kwad.sdk.core.network.BaseResultData
    public boolean isDataEmpty() {
        return this.data == null;
    }

    @Override // com.kwad.sdk.core.network.BaseResultData, com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) throws Throwable {
        super.parseJson(jSONObject);
        if (jSONObject == null) {
            return;
        }
        try {
            this.data.parseJson(new JSONObject(d.b(jSONObject.optString("data"))));
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    @Override // com.kwad.sdk.core.network.BaseResultData, com.kwad.sdk.core.b
    public JSONObject toJson() throws JSONException {
        JSONObject json = super.toJson();
        t.a(json, "data", this.data);
        return json;
    }
}
