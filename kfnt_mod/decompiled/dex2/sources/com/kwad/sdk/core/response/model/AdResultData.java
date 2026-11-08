package com.kwad.sdk.core.response.model;

import android.text.TextUtils;
import com.kwad.sdk.api.KsScene;
import com.kwad.sdk.core.a.d;
import com.kwad.sdk.core.a.e;
import com.kwad.sdk.core.network.BaseResultData;
import com.kwad.sdk.internal.api.SceneImpl;
import com.kwad.sdk.plugin.DevelopMangerPlugin;
import com.kwad.sdk.plugin.f;
import com.kwad.sdk.utils.at;
import com.kwad.sdk.utils.t;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AdResultData extends BaseResultData implements com.kwad.sdk.core.b {
    private static final String TAG = "AdResultData";
    private static final long serialVersionUID = -818939163644825380L;
    public a entryInfo;
    private Map<Long, SceneImpl> mRequestAdSceneMap;
    public String pcursor;
    public PageInfo pageInfo = new PageInfo();
    public List<AdTemplate> adTemplateList = new ArrayList();

    public AdResultData() {
    }

    public AdResultData(KsScene ksScene) {
        if (ksScene != null) {
            this.mRequestAdSceneMap = new HashMap(1);
            this.mRequestAdSceneMap.put(Long.valueOf(ksScene.getPosId()), (SceneImpl) ksScene);
        }
    }

    public AdResultData(List<KsScene> list) {
        if (list != null) {
            this.mRequestAdSceneMap = new HashMap(list.size());
            for (KsScene ksScene : list) {
                if (ksScene != null) {
                    this.mRequestAdSceneMap.put(Long.valueOf(ksScene.getPosId()), (SceneImpl) ksScene);
                }
            }
        }
    }

    private SceneImpl getAdScene(long j) {
        SceneImpl sceneImpl = this.mRequestAdSceneMap != null ? this.mRequestAdSceneMap.get(Long.valueOf(j)) : null;
        return sceneImpl == null ? new SceneImpl(j) : sceneImpl;
    }

    public boolean isAdResultDataEmpty() {
        String str;
        String str2;
        if (this.adTemplateList.isEmpty()) {
            str = TAG;
            str2 = "adTemplateList is empty";
        } else {
            com.kwad.sdk.core.d.a.a(TAG, "adTemplateList size = " + this.adTemplateList.size());
            List<AdInfo> list = this.adTemplateList.get(0).adInfoList;
            if (list.isEmpty()) {
                str = TAG;
                str2 = "adInfoList is empty";
            } else {
                if (list.get(0) != null) {
                    return false;
                }
                str = TAG;
                str2 = "adInfo is null";
            }
        }
        com.kwad.sdk.core.d.a.e(str, str2);
        return true;
    }

    @Override // com.kwad.sdk.core.network.BaseResultData
    public boolean isDataEmpty() {
        return false;
    }

    @Override // com.kwad.sdk.core.network.BaseResultData, com.kwad.sdk.core.b
    public void parseJson(JSONObject jSONObject) throws Throwable {
        super.parseJson(jSONObject);
        if (jSONObject == null) {
            return;
        }
        try {
            this.pcursor = jSONObject.optString("pcursor");
            e.a(jSONObject.optString("egid"));
            try {
                String strOptString = jSONObject.optString("pageInfo");
                if (!at.a(strOptString)) {
                    this.pageInfo.parseJson(new JSONObject(d.b(strOptString)));
                }
            } catch (Exception e) {
                com.kwad.sdk.core.d.a.a("json bug", e.toString());
                com.kwad.sdk.core.d.a.a(e);
            }
            String strOptString2 = jSONObject.optString("impAdInfo");
            if (!TextUtils.isEmpty(strOptString2)) {
                String strB = d.b(strOptString2);
                if (!at.a(strB)) {
                    JSONArray jSONArray = new JSONArray(strB);
                    if (jSONArray.length() > 0) {
                        for (int i = 0; i < jSONArray.length(); i++) {
                            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                            if (jSONObjectOptJSONObject != null) {
                                AdTemplate adTemplate = new AdTemplate();
                                adTemplate.parseJson(jSONObjectOptJSONObject);
                                adTemplate.llsid = this.llsid;
                                adTemplate.extra = this.extra;
                                adTemplate.mAdScene = getAdScene(adTemplate.posId);
                                adTemplate.mPageInfo = this.pageInfo;
                                this.adTemplateList.add(adTemplate);
                            }
                        }
                    }
                }
            }
            try {
                JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("entryInfo");
                if (jSONObjectOptJSONObject2 == null) {
                    String strOptString3 = jSONObject.optString("entryInfo");
                    if (!at.a(strOptString3)) {
                        DevelopMangerPlugin.DevelopValue developValueA = ((DevelopMangerPlugin) f.a(DevelopMangerPlugin.class)).a("KEY_HOST_ENCRYPT_DISABLE");
                        if (developValueA == null || !((Boolean) developValueA.getValue()).booleanValue()) {
                            String strReplaceAll = d.b(strOptString3).replaceAll("\\\\", "");
                            jSONObjectOptJSONObject2 = new JSONObject(strReplaceAll.substring(1, strReplaceAll.length() - 1));
                        } else {
                            jSONObjectOptJSONObject2 = new JSONObject(strOptString3);
                        }
                    }
                }
                if (jSONObjectOptJSONObject2 != null) {
                    this.entryInfo = new a();
                    this.entryInfo.parseJson(jSONObjectOptJSONObject2);
                    this.entryInfo.k = this.adTemplateList;
                }
            } catch (Exception e2) {
                com.kwad.sdk.core.d.a.a(e2);
            }
            if (com.kwad.sdk.core.d.a.a) {
                com.kwad.sdk.core.d.a.a(TAG, toJson().toString());
            }
        } catch (Exception e3) {
            com.kwad.sdk.core.d.a.a(e3);
            com.kwad.sdk.core.d.a.a("json bug", e3.toString());
        }
    }

    @Override // com.kwad.sdk.core.network.BaseResultData, com.kwad.sdk.core.b
    public JSONObject toJson() throws JSONException {
        JSONObject json = super.toJson();
        t.a(json, "pcursor", this.pcursor);
        t.a(json, "pageInfo", this.pageInfo);
        t.a(json, "impAdInfo", this.adTemplateList);
        t.a(json, "entryInfo", this.entryInfo);
        return json;
    }
}
