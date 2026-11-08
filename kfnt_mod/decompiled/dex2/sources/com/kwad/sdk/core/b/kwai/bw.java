package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.HotspotInfo;
import com.kwad.sdk.core.response.model.HotspotListData;
import com.kwad.sdk.core.response.model.TabInfo;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class bw implements com.kwad.sdk.core.d<HotspotListData> {
    @Override // com.kwad.sdk.core.d
    public void a(HotspotListData hotspotListData, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        hotspotListData.trends = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("trends");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                HotspotInfo hotspotInfo = new HotspotInfo();
                hotspotInfo.parseJson(jSONArrayOptJSONArray.optJSONObject(i));
                hotspotListData.trends.add(hotspotInfo);
            }
        }
        hotspotListData.tabList = new ArrayList();
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("tabList");
        if (jSONArrayOptJSONArray2 != null) {
            for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                TabInfo tabInfo = new TabInfo();
                tabInfo.parseJson(jSONArrayOptJSONArray2.optJSONObject(i2));
                hotspotListData.tabList.add(tabInfo);
            }
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(HotspotListData hotspotListData, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "trends", hotspotListData.trends);
        com.kwad.sdk.utils.t.a(jSONObject, "tabList", hotspotListData.tabList);
        return jSONObject;
    }
}
