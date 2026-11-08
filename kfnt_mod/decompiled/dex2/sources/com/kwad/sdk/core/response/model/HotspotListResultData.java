package com.kwad.sdk.core.response.model;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.kwad.sdk.core.a.d;
import com.kwad.sdk.core.a.e;
import com.kwad.sdk.core.network.BaseResultData;
import com.kwad.sdk.utils.t;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class HotspotListResultData extends BaseResultData implements com.kwad.sdk.core.b {
    private static final String TAG = "HotspotListResultData";
    private static final long serialVersionUID = 5203393014166710381L;
    public final HotspotListData hotspotListData = new HotspotListData();

    @Override // com.kwad.sdk.core.network.BaseResultData
    public boolean isDataEmpty() {
        if (!this.hotspotListData.trends.isEmpty()) {
            return false;
        }
        com.kwad.sdk.core.d.a.e(TAG, "trends list is empty");
        return true;
    }

    @Override // com.kwad.sdk.core.network.BaseResultData, com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) throws Throwable {
        super.parseJson(jSONObject);
        if (jSONObject == null) {
            return;
        }
        String strOptString = jSONObject.optString("data");
        e.a(jSONObject.optString("egid"));
        if (TextUtils.isEmpty(strOptString)) {
            return;
        }
        try {
            this.hotspotListData.parseJson(new JSONObject(d.b(strOptString)));
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    @Override // com.kwad.sdk.core.network.BaseResultData, com.kwad.sdk.core.b
    public JSONObject toJson() throws JSONException {
        t.a(new JSONObject(), "data", this.hotspotListData);
        return new JSONObject();
    }
}
