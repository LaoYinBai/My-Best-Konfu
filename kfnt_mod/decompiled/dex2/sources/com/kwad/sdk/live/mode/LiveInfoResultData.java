package com.kwad.sdk.live.mode;

import androidx.annotation.Nullable;
import com.kwad.sdk.core.network.BaseResultData;
import com.kwad.sdk.utils.t;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class LiveInfoResultData extends BaseResultData {
    private static final long serialVersionUID = -2695767546208947458L;
    public List<LiveInfo> liveInfoList = new ArrayList();
    public String pcursor;

    @Override // com.kwad.sdk.core.network.BaseResultData, com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) throws Throwable {
        super.parseJson(jSONObject);
        if (jSONObject == null) {
            return;
        }
        this.pcursor = jSONObject.optString("pcursor");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("feeds");
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
            return;
        }
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                LiveInfo liveInfo = new LiveInfo();
                liveInfo.pcursor = this.pcursor;
                liveInfo.parseJson(jSONObjectOptJSONObject);
                this.liveInfoList.add(liveInfo);
            }
        }
    }

    @Override // com.kwad.sdk.core.network.BaseResultData, com.kwad.sdk.core.b
    public JSONObject toJson() throws JSONException {
        JSONObject json = super.toJson();
        t.a(json, "pcursor", this.pcursor);
        t.a(json, "feeds", this.liveInfoList);
        return json;
    }
}
