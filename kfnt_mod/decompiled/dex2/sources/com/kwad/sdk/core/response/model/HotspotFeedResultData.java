package com.kwad.sdk.core.response.model;

import androidx.annotation.NonNull;
import com.kwad.sdk.core.a.d;
import com.kwad.sdk.core.a.e;
import com.kwad.sdk.core.network.BaseResultData;
import com.kwad.sdk.internal.api.SceneImpl;
import com.kwad.sdk.utils.t;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class HotspotFeedResultData extends BaseResultData implements com.kwad.sdk.core.b {
    private static final String TAG = "TrendsResultData";
    private static final long serialVersionUID = -1619392774105464372L;

    @NonNull
    public List<AdTemplate> adTemplateList = new ArrayList();
    private final HotspotInfo mHotspotInfo;
    private final SceneImpl mScene;

    public HotspotFeedResultData(@NonNull SceneImpl sceneImpl, @NonNull HotspotInfo hotspotInfo) {
        this.mScene = sceneImpl;
        this.mHotspotInfo = hotspotInfo;
    }

    @Override // com.kwad.sdk.core.network.BaseResultData
    public boolean isDataEmpty() {
        if (!this.adTemplateList.isEmpty()) {
            return false;
        }
        com.kwad.sdk.core.d.a.e(TAG, "adTemplateList is empty");
        return true;
    }

    @Override // com.kwad.sdk.core.network.BaseResultData, com.kwad.sdk.core.b
    public void parseJson(JSONObject jSONObject) throws Throwable {
        super.parseJson(jSONObject);
        if (jSONObject == null) {
            return;
        }
        e.a(jSONObject.optString("egid"));
        try {
            JSONObject jSONObject2 = new JSONObject(d.b(jSONObject.optString("data")));
            long jOptLong = jSONObject2.optLong("llsid");
            String strOptString = jSONObject2.optString(PushConstants.EXTRA);
            JSONArray jSONArrayOptJSONArray = jSONObject2.optJSONArray("feeds");
            if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                return;
            }
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject != null) {
                    AdTemplate adTemplate = new AdTemplate();
                    adTemplate.parseJson(jSONObjectOptJSONObject);
                    adTemplate.llsid = jOptLong;
                    adTemplate.extra = strOptString;
                    adTemplate.photoInfo.mHotspotInfo = this.mHotspotInfo;
                    adTemplate.mAdScene = this.mScene;
                    this.adTemplateList.add(adTemplate);
                }
            }
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    @Override // com.kwad.sdk.core.network.BaseResultData, com.kwad.sdk.core.b
    public JSONObject toJson() throws JSONException {
        JSONObject json = super.toJson();
        t.a(json, "impAdInfo", this.adTemplateList);
        return json;
    }
}
