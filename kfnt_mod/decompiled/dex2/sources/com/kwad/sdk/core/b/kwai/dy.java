package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.request.model.TaskStat;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class dy implements com.kwad.sdk.core.d<com.kwad.sdk.core.request.model.j> {
    @Override // com.kwad.sdk.core.d
    public void a(com.kwad.sdk.core.request.model.j jVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        jVar.b = jSONObject.optInt("personalRecommend");
        jVar.c = jSONObject.optInt("programmaticRecommend");
        jVar.d = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("taskStats");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                TaskStat taskStat = new TaskStat();
                taskStat.parseJson(jSONArrayOptJSONArray.optJSONObject(i));
                jVar.d.add(taskStat);
            }
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(com.kwad.sdk.core.request.model.j jVar, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "personalRecommend", jVar.b);
        com.kwad.sdk.utils.t.a(jSONObject, "programmaticRecommend", jVar.c);
        com.kwad.sdk.utils.t.a(jSONObject, "taskStats", jVar.d);
        return jSONObject;
    }
}
